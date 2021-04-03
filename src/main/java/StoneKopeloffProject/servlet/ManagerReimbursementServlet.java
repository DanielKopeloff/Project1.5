package StoneKopeloffProject.servlet;

import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.ReimbursementService;
import StoneKopeloffProject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;


/**
 * A custom servlet to handle all the logic done by the managers in regards to single reimbursements
 */
@WebServlet(urlPatterns = "/manager/reimbursement")
public class ManagerReimbursementServlet extends HttpServlet {

    /**
     * Gets either all the reimbursements associated with this manager
     * Or if their is a Id parameter then it will look up that reimbursement
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password") == null) {
            writer.println("Invalid user credentials");
            return;
        }
        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
        } else if (u.getRole_id() == 0) {
            writer.println("You do not have permission to perform this action");
            return;
        }

        if (req.getParameter("reimId") == null) {
            List<Reimbursement> returnList = ReimbursementService.getInstance().getReimbursementsByUserID(u.getUserIDPK());
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnList);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.print(json);
        } else {
            int id = 0;
            try {
                id = Integer.parseInt(req.getParameter("reimId"));
            } catch (NumberFormatException e) {
                writer.println("Invalid id");
                return;
            }
            if (id == 0) {
                writer.println("Id was not parsed ");
                return;
            }

            Reimbursement r = ReimbursementService.getInstance().getbyReimbursementID(id);
            ObjectMapper objectMapper = new ObjectMapper();
            String json;
            if (r == null) {

                json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new HashMap<>());

            } else {
                json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.print(json);


        }
        writer.flush();
    }

    /**
     * Update a reimbursement
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password") == null) {
            writer.println("Invalid user credentials");
            return;
        }
        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password"));

        if (req.getParameter("reimId") != null) {
            try {
                Integer.parseInt(req.getParameter("reimId"));
            } catch (NullPointerException e) {
            } catch (NumberFormatException e) {
                writer.println("Invalid reimbursement Id");
                return;
            }

        }

        Reimbursement r = ReimbursementService.getInstance().getbyReimbursementID(Integer.parseInt(req.getParameter("reimId")));
        if (u == null) {
            writer.println("Invalid user credentials");
            return;
        } else if (u.getRole_id() == 0) {
            writer.println("You do not have permission to perform this action");
            return;
            // If the reimbursement has been settled then it can not be altered
        } else if (r.getStatus_id() > 0) {
            writer.println("Reimbursement has already been settled");
        } else {

            if (req.getParameter("type_id") != null) {
                try {
                    Integer.parseInt(req.getParameter("type_id"));
                } catch (NullPointerException e) {
                } catch (NumberFormatException e) {
                    writer.println("Invalid reimbursement type");
                    return;
                }

            }

            if (req.getParameter("newstatus") != null) {
                try {
                    Integer.parseInt(req.getParameter("newstatus"));
                } catch (NumberFormatException e) {
                    writer.println("Invalid new status");
                    return;
                } catch (NullPointerException e) {}

            }

            if (!(req.getParameter("amount") == null)) {
                if (Float.parseFloat(req.getParameter("amount")) >= 0.0) {
                    DecimalFormat df = new DecimalFormat("#.00");
                    df.setRoundingMode(RoundingMode.DOWN);
                    r.setAmount(Float.parseFloat(df.format(Float.parseFloat(req.getParameter("amount")))));
                } else {
                    writer.println("Invalid reimbursement amount");
                    return;
                }

            }
            if (!(req.getParameter("type_id") == null)) {
                if (Integer.parseInt(req.getParameter("type_id")) >= 0 && Integer.parseInt(req.getParameter("type_id")) <= 5) {
                    r.setType_id(Integer.parseInt(req.getParameter("type_id")));
                } else {
                    writer.println("Invalid reimbursement type");
                    return;
                }

            }
            if (!(req.getParameter("description") == null)) {
                if ((req.getParameter(("description")).length() > 1)) {
                    r.setDescription(req.getParameter("description"));
                } else {
                    writer.println("Invalid reimbursement description");
                    return;
                }
            }

            if (!(req.getParameter("newstatus") == null)) {
                if (r.getAuthor().equals(u)) {
                    writer.println("Not allowed to edit your own reimbursements");
                    return;
                }

                int status = Integer.parseInt(req.getParameter("newstatus"));

                if (status >= 0 && status <= 3) {
                    r.setStatus_id(status);
                    // If it was updated to accepted or rejected
                    if (status == 1 || status == 2) {
                        r.setResolver(u);
                        r.setResolved(Timestamp.from(Instant.now()));
                    }
                }
                else{
                    writer.println("Invalid new status");
                    return;
                }
            }
            ReimbursementService.getInstance().updateReimbursement(r);
            writer.println("Reimbursement successfully updated");
        }
        writer.flush();
    }

    /**
     * This method will be where the user can create a reimbursement
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password") == null) {
            writer.println("Invalid user credentials");
            return;
        }

        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password"));

        if (u == null) {
            writer.println("Invalid user credentials");
            return;
        } else if (u.getRole_id() == 0) {
            writer.println("You do not have permission to perform this action");
            return;
        }


        try {
            Float.parseFloat(req.getParameter("amount"));
        } catch (NumberFormatException e) {
            writer.println("Invalid reimbursement amount");
            return;
        } catch (NullPointerException e) {
        }

        try {
            Integer.parseInt(req.getParameter("type_id"));
        } catch (NumberFormatException e) {
            writer.println("Invalid reimbursement type");
            return;
        } catch (NullPointerException e) {
        }


        if (req.getParameter("amount") == null || Float.parseFloat(req.getParameter("amount")) <= 0.0) {
            writer.println("Invalid reimbursement amount");
            return;
        }
        if (req.getParameter("type_id") == null || Integer.parseInt(req.getParameter("type_id")) <= 0 || Integer.parseInt(req.getParameter("type_id")) >= 5) {
            writer.println("Invalid reimbursement type");
            return;
        }
        if (req.getParameter("description") == null || (req.getParameter(("description")).length() < 1)) {
            writer.println("Invalid reimbursement description");
            return;
        }
        float temp;
        DecimalFormat df = new DecimalFormat("#.00");
        df.setRoundingMode(RoundingMode.DOWN);
        temp = (Float.parseFloat(df.format(Float.parseFloat(req.getParameter("amount")))));
        ReimbursementService.getInstance().createReimbursement(temp, req.getParameter("description"), u.getUserIDPK(),
                Reimbursement.getExpense_Value(Integer.parseInt(req.getParameter("type_id"))));
        writer.println("Successfully submitted");
        writer.flush();

    }

}
