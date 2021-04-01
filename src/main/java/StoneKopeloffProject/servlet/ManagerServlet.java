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
import java.util.Map;

@WebServlet(urlPatterns = "/manager")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password")  == null) {
            writer.println("Invalid user credentials");
            return;
        }
        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"),req.getParameter("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
        } else {
            List<Reimbursement> returnList = ReimbursementService.getInstance().fetchAllReimbursements();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnList);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.print(json);
        }
        writer.flush();
    }

    //ToDo : check for duplicate users
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password")  == null) {
            writer.println("Invalid user credentials");
            return;
        }
        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"),req.getParameter("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
        } else if (u.getRole_id() == 0) {
            writer.println("You do not have permission to perform this action");
        } else {
            if (req.getParameter("newusername") == null || req.getParameter("newusername").length() < 1) {
                writer.println("Invalid username");
                return;
            }
            if (req.getParameter("newuserpassword") == null || (req.getParameter("newuserpassword")).length() < 1) {
                writer.println("Invalid password");
                return;
            }
            if (req.getParameter("firstname") == null || (req.getParameter("firstname")).length() < 1) {
                writer.println("Invalid first name");
                return;
            }
            if (req.getParameter("lastname") == null || (req.getParameter("lastname")).length() < 1) {
                writer.println("Invalid last name");
                return;
            }
            //credit http://emailregex.com/ for the regex used below
            if (req.getParameter("email") == null || (req.getParameter("email")).length() < 1
                    || !((String) req.getParameter("email")).matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
                    "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                writer.println("Invalid email");
                return;
            }
            UserService.getInstance().addUser((String) req.getParameter("newusername"), req.getParameter("newuserpassword"),
                     req.getParameter("firstname"), req.getParameter("lastname"), (String) req.getParameter("email"));
            writer.println("Successfully created user");
        }
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password")  == null) {
            writer.println("Invalid user credentials");
            return;
        }
        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
        } else if (u.getRole_id() == 0) {
            writer.println("You do not have permission to perform this action");
        } else {
            if (req.getParameter("id") == null || (req.getParameter("id")).length() < 1) {
                writer.println("Invalid reimbursement Id");
                return;
            }
            if (req.getParameter("newstatus") == null || Integer.parseInt(req.getParameter("newstatus")) < 1 || Integer.parseInt(req.getParameter("newstatus")) > 2 ) {
                writer.println("Invalid reimbursement status");
                return;
            }
            ReimbursementService.getInstance().updateReimbursement(Integer.parseInt(req.getParameter("id")) ,u.getUserIDPK(),(Integer.parseInt(req.getParameter("newstatus"))));
            writer.println("Reimbursement successfully updated");
        }
        writer.flush();
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Unsupported Operation");
        writer.flush();
    }
}
