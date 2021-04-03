package StoneKopeloffProject.servlet;

import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.ReimbursementService;
import StoneKopeloffProject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * A custom servlet to handle all the managers reimbursements
 * This end point is sole purpose to be able to see the data based on its status
 * Managerrs can also see all of the reimbursments by entering the speical Id value;
 */
@WebServlet(urlPatterns = "/manager/reimbursement/list")
public class ManagerReimbursementsListServlet extends HttpServlet {

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

        if(u.getRole_id() != 1){
            writer.println("You do not have permission to perform this action");
            return;
        }

        if (req.getParameter("statusId") == null) {
            List<Reimbursement> returnList = ReimbursementService.getInstance().getReimbursementsByUserID(u.getUserIDPK());
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnList);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.print(json);
        } else {
            int id = -1;
            try {
                id = Integer.parseInt(req.getParameter("statusId"));
            } catch (NumberFormatException e) {
                writer.println("Invalid id");
                return;
            }catch (NullPointerException e){}

            String json;
            ObjectMapper objectMapper = new ObjectMapper();

            if(id == -1) {
                List<Reimbursement> r = ReimbursementService.getInstance().fetchAllReimbursements();

                 json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);


            }else {
                if(id >=0 && id <=2){
                    List<Reimbursement> r = ReimbursementService.getInstance().getReimbursementsByStatus(id, u.getUserIDPK());
                    json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);
                }
                else{
                    writer.println("Invalid status");
                    return;
                }

            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.print(json);
        }
        writer.flush();
    }
}








