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

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map m = req.getParameterMap();
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password")  == null) {
            writer.println("Invalid user credentials");
            return;
        }
        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"),req.getParameter("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
        } else {
            List<Reimbursement> returnList = ReimbursementService.getInstance().getReimbursementsByUserID(u.getUserIDPK());
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnList);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.print(json);
        }
        writer.flush();
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
        ReimbursementService.getInstance().createReimbursement((float) m.get("amount"),(String) m.get("description"),u.getUserIDPK(),(int) m.get("type_id"));
        writer.println("Successfully submitted");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Unsupported Operation");
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Unsupported Operation");
        writer.flush();
    }
}
