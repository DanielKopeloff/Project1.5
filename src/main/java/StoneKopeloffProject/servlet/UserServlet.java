package StoneKopeloffProject.servlet;

import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.UserService;
import StoneKopeloffProject.utililty.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A custom servlet to deal with all the logic behind the user endpoint
 */
@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    /**
     * This method should be how the user can see their current account details
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
            writer.flush();
            return;
        }
        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password"));
        if (u == null) {
            writer.println("Invalid user credentials");
            writer.flush();
            return;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(u.toString());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.print(json);
            writer.flush();
        }
    }

    /**
     * This method should be a way for the user to update their information
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
            writer.flush();
            return;
        }
        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password"));
        if (u == null) {
            writer.println("Unable to login");
            writer.flush();
            return;
        }

        if (UserService.getInstance().getUserByUsername(req.getParameter("newusername")) != null) {
            writer.println("Username already taken");
            writer.flush();
            return;
        }
        if (req.getParameter("newusername") != null && req.getParameter("newusername").length() > 1) {
            u.setUsername(req.getParameter("newusername"));
        } else {
            writer.println("Invalid username");
            writer.flush();
        }
        if (req.getParameter("newpassword") != null && req.getParameter("newpassword").length() > 1) {
            u.setPassword(req.getParameter("newpassword"));
        } else {
            writer.println("Invalid password");
            writer.flush();
            return;
        }
        if (req.getParameter("firstname") != null && req.getParameter("firstname").length() > 0
                && Validation.validateString(req.getParameter("firstname"))) {
            u.setFirstname(req.getParameter("firstname"));
        } else {
            writer.println("Invalid first name");
            writer.flush();
            return;
        }

        if (req.getParameter("lastname") != null && req.getParameter("lastname").length() > 0
                && Validation.validateString(req.getParameter("lastname"))) {
            u.setFirstname(req.getParameter("lastname"));
        } else {
            writer.println("Invalid last name");
            writer.flush();
            return;
        }
        if (req.getParameter("email") != null && Validation.validateEmail(req.getParameter("email"))) {
            u.setEmail(req.getParameter("email"));
            UserService.getInstance().updateUser(u);
            writer.println("Successfully updated");
        } else {
            writer.println("Invalid email");
        }
        writer.flush();
    }

    /**
     * This method will create a new  user
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password") == null) {
            writer.println("Invalid user credentials");
            writer.flush();
            return;
        }
        // If the username is already linked to a user name then it is not unique and therefore can not be used
        if (UserService.getInstance().getUserByUsername(req.getParameter("username")) != null) {
            writer.println("Username already taken");
            writer.flush();
            return;
        }
        User u = new User();
        if (req.getParameter("username") != null && req.getParameter("username").length() > 0) {
            u.setUsername(req.getParameter("username"));
        } else {
            writer.println("Invalid username");
            writer.flush();
            return;
        }
        if (req.getParameter("password") != null && req.getParameter("password").length() > 0) {
            u.setPassword(req.getParameter("password"));
        } else {
            writer.println("Invalid password");
            writer.flush();
            return;
        }

        if (req.getParameter("firstname") != null && req.getParameter("firstname").length() > 0
                && Validation.validateString(req.getParameter("firstname"))) {
            u.setFirstname(req.getParameter("firstname"));
        } else {
            writer.println("Invalid first name");
            writer.flush();
            return;
        }

        if (req.getParameter("lastname") != null && req.getParameter("lastname").length() > 0
                && Validation.validateString(req.getParameter("lastname"))) {
            u.setLastname(req.getParameter("lastname"));
        } else {
            writer.println("Invalid last name");
            writer.flush();
            return;
        }

        if (req.getParameter("email") != null && (Validation.validateEmail(req.getParameter("email")))) {
            u.setEmail(req.getParameter("email"));
            u.setActive(true);
            UserService.getInstance().addUser(u);
            writer.println("Successfully created user");
        } else {
            writer.println("Invalid email");
        }
        writer.flush();
    }

    /**
     * This method will deactivate the user
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password") == null) {
            writer.println("Invalid user credentials");
            return;
        } else {
            User u = UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password"));
            if (u == null) {
                writer.println("Invalid user credentials");
                writer.flush();
                return;
            } else {
                u.setActive(false);
                UserService.getInstance().updateUser(u);
            }
        }
        writer.println("User deleted");
        writer.flush();
    }
}
