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


@WebServlet(urlPatterns = "/user/reimbursement/list")
public class UserReimbursementListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                writer.println("Invalid status");
                return;
            }
            if (id == -1) {
                if (u.getRole_id() == 1) {
                    writer.println("need to use manager URL ");
                    return;
                } else {
                    writer.println("Invalid status");
                    return;
                }

            } else if (id >= 0 && id <= 2) {
                List<Reimbursement> r = ReimbursementService.getInstance().getReimbursementsByStatus(id, u.getUserIDPK());
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                writer.print(json);
            } else {
                writer.println("Invalid status");
                return;
            }

        }
        writer.flush();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Unsupported Operation");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Unsupported Operation");
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Unsupported Operation");
        writer.flush();
    }
}
