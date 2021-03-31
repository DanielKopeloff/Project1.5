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

@WebServlet(value = "/manager")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map m = req.getParameterMap();
        PrintWriter writer = resp.getWriter();
        User u = UserService.getInstance().getUserByLogin((String) m.get("username"), (String) m.get("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
        } else if (u.getRole_id() == 0) {
            writer.println("You do not have permission to perform this action");
        } else {
            List<Reimbursement> printList = ReimbursementService.getInstance().fetchAllReimbursements();
            for (Reimbursement r : printList) {
                writer.println(UserFriendlyPrint.printReimbursement(r, true));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map m = req.getParameterMap();
        PrintWriter writer = resp.getWriter();
        User u = UserService.getInstance().getUserByLogin((String) m.get("username"), (String) m.get("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
        } else if (u.getRole_id() == 0) {
            writer.println("You do not have permission to perform this action");
        } else {
            if (m.get("newusername") == null || ((String) m.get("newusername")).length() < 1) {
                writer.println("Invalid username");
                return;
            }
            if (m.get("newuserpassword") == null || ((String) m.get("newuserpassword")).length() < 1) {
                writer.println("Invalid password");
                return;
            }
            if (m.get("firstname") == null || ((String) m.get("firstname")).length() < 1) {
                writer.println("Invalid first name");
                return;
            }
            if (m.get("lastname") == null || ((String) m.get("lastname")).length() < 1) {
                writer.println("Invalid last name");
                return;
            }
            //credit http://emailregex.com/ for the regex used below
            if (m.get("email") == null || ((String) m.get("email")).length() < 1
                    || !((String) m.get("email")).matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                writer.println("Invalid email");
                return;
            }
            UserService.getInstance().addUser((String) m.get("newusername"), (String) m.get("newuserpassword"),
                    (String) m.get("firstname"), (String) m.get("lastname"), (String) m.get("email"));
            writer.println("Successfully created user");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map m = req.getParameterMap();
        PrintWriter writer = resp.getWriter();
        User u = UserService.getInstance().getUserByLogin((String) m.get("username"), (String) m.get("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
        } else if (u.getRole_id() == 0) {
            writer.println("You do not have permission to perform this action");
        } else {
            if (m.get("id") == null || ((String) m.get("id")).length() < 1) {
                writer.println("Invalid reimbursement Id");
                return;
            }
            if (m.get("newstatus") == null || (int) m.get("newstatus") < 1 || (int) m.get("newstatus") > 2 ) {
                writer.println("Invalid reimbursement status");
                return;
            }
            ReimbursementService.getInstance().updateReimbursement(u.getUser_id(),(int) m.get("newstatus"));
        }
    }
}
