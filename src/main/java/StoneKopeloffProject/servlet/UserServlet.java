package StoneKopeloffProject.servlet;

import StoneKopeloffProject.model.Reimbursement;
import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.ReimbursementService;
import StoneKopeloffProject.service.UserService;
import StoneKopeloffProject.ui.UserFriendlyPrint;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map m = req.getParameterMap();
        PrintWriter writer = resp.getWriter();
        User u = UserService.getInstance().getUserByLogin((String) m.get("username"),(String) m.get("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
        } else {
            List<Reimbursement> printList = ReimbursementService.getInstance().getReimbursementsByUserID(u.getUser_id());
            for (Reimbursement r : printList) {
                writer.println(UserFriendlyPrint.printReimbursement(r, false));
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map m = req.getParameterMap();
        PrintWriter writer = resp.getWriter();
        User u = UserService.getInstance().getUserByUsername((String) m.get("username"));
        if (u == null || u.getPassword() != m.get("password")) {
            writer.println("Invalid user credentials");
            return;
        }
        if (m.get("amount") == null || (float) m.get("amount") <= 0.0) {
            writer.println("Invalid reimbursement amount");
            return;
        }
        if (m.get("type_id") == null || (int) m.get("type_id") < 0 ||  (int) m.get("type_id") > 2) {
            writer.println("Invalid reimbursement type");
            return;
        }
        if (m.get("description") == null || ((String) m.get("description")).length() < 1) {
            writer.println("Invalid reimbursement description");
            return;
        }
        ReimbursementService.getInstance().createReimbursement((float) m.get("amount"),(String) m.get("description"),u.getUser_id(),(int) m.get("type_id"));
        writer.println("Successfully submitted");
    }
}
