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
import java.util.List;


/**
 * A servlet that will handle the Crud operations of the User reimbursements
 * such as creating a reimbursement , reading their reimbursements , updating their reimbursement, deleting their reimbursement
 */
@WebServlet(urlPatterns = "/user/reimbursement")
public class UserReimbursementServlet extends HttpServlet {

    /**
     * This method will display all of the reimbursements of the current user
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
            if(!r.getAuthor().equals(u)){
                writer.println("Do not have access to this reimbursement ");
                return;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.print(json);

        }
        writer.flush();
    }

    /**
     * This method should be how to user updates their reimbursement
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
        int id = -1;
        try {
            id = Integer.parseInt(req.getParameter("reimId"));
        } catch (NumberFormatException e) {
            writer.println("Not a valid id");
            return;
        }
        if(id == -1){
            writer.println("Not a valid id");
            writer.println("Id not parsed");
            return;
        }

        Reimbursement r = ReimbursementService.getInstance().getbyReimbursementID(id);
        if(r.getAuthor() != u){
            writer.println("Do not have access to this ");
            return;
        }
        if (u == null) {
            writer.println("Invalid user credentials");
            return;
        }
        if (r == null) {
            writer.println("Invalid reimbursement number");
            return;
        }


        try {
            Float.parseFloat(req.getParameter("amount"));
        } catch (NumberFormatException e) {
            writer.println("Invalid amount");
            return;
        }

        try {
            Integer.parseInt(req.getParameter("type_id"));
        } catch (NumberFormatException e) {
            writer.println("Invalid type ");
            return;
        }


        if (!(req.getParameter("amount") == null)) {
            if (Float.parseFloat(req.getParameter("amount")) >= 0.0) {
                r.setAmount(Float.parseFloat(req.getParameter("amount")));
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
//        ReimbursementService.getInstance().createReimbursement(Float.parseFloat(req.getParameter("amount")), req.getParameter("description"), u.getUserIDPK() ,
//                Reimbursement.getExpense_Value(Integer.parseInt(req.getParameter("type_id"))));
        ReimbursementService.getInstance().updateReimbursement(r);
        writer.println("Updated submitted");
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
        }
        /**
         * Where we are checking if the input is valid
         */
        try {
            Float.parseFloat(req.getParameter("amount"));
        } catch (NumberFormatException e) {
            writer.println("Invalid amount");
            return;
        }

        try {
            Integer.parseInt(req.getParameter("type_id"));
        } catch (NumberFormatException e) {
            writer.println("Invalid type ");
            return;
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
        ReimbursementService.getInstance().createReimbursement(Float.parseFloat(req.getParameter("amount")), req.getParameter("description"), u.getUserIDPK(),
                Reimbursement.getExpense_Value(Integer.parseInt(req.getParameter("type_id"))));
        writer.println("Successfully submitted");
        writer.flush();

    }

    /**
     * TODO : Implement the method that will only delete if the reimbursement is still pending
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Unsupported Operation");
        writer.flush();
    }

    public boolean validtaeString(String str) {
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z')) {
                return false;
            }
        }
        return true;
    }
}
