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
 * A custom servlet to deal with all the logic behind the manager endpoint
 */
@WebServlet(urlPatterns = "/manager")
public class ManagerServlet extends HttpServlet {
    /**
     * Display the info about the manager
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
        } else if (u.getRole_id() == 0) {
            writer.println("You do not have permission to perform this action");
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
     * Create a new manager
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
        if(UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password")) == null){
            writer.println("Invalid user credentials");
            writer.flush();
            return;
        }
        if ( UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password")).getRole_id() != 1) {
            writer.println("You do not have permission to perform this action");
            writer.flush();
            return;
        }

        // If the username is already linked to a user name then it is not unique and therefore can not be used
        if (UserService.getInstance().getUserByUsername(req.getParameter("newusername")) != null) {
            writer.println("Username already taken");
            writer.flush();
            return;
        }
        if (req.getParameter("newusername") == null || req.getParameter("newusername").length() < 1) {
            // If the get user by user name method returns null that means that the username does not exist
            writer.println("Invalid username");
            writer.flush();
            return;
        }
        if (req.getParameter("newpassword") == null || (req.getParameter("newpassword")).length() < 1) {
            writer.println("Invalid password");
            writer.flush();
            return;
        }
        if (req.getParameter("firstname") == null || (req.getParameter("firstname")).length() < 1 || !(Validation.validateString(req.getParameter("firstname")))) {
            writer.println("Invalid first name");
            writer.flush();
            return;
        }
        if (req.getParameter("lastname") == null || (req.getParameter("lastname")).length() < 1 || !(Validation.validateString(req.getParameter("lastname")))) {
            writer.println("Invalid last name");
            writer.flush();
            return;
        }
        if (req.getParameter("email") == null || (!Validation.validateEmail(req.getParameter("email")))) {
            writer.println("Invalid email");
            writer.flush();
            return;
        }
        UserService.getInstance().addManager(req.getParameter("newusername"), req.getParameter("newpassword"),
                req.getParameter("firstname"), req.getParameter("lastname"), req.getParameter("email"));
        writer.println("Successfully created Manager");
        writer.flush();
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
        } else if (u.getRole_id() != 1) {
            writer.println("You do not have permission to perform this action");
            writer.flush();
            return;
        }



        if (UserService.getInstance().getUserByUsername(req.getParameter("newusername")) != null) {
            writer.println("Username already taken");
            writer.flush();
            return;
        }
        if (req.getParameter("newusername") != null) {
            if (req.getParameter("newusername").length() > 1) {
                u.setUsername(req.getParameter("newusername"));
            } else {
                writer.println("Invalid username");
                writer.flush();
                return;
            }
        }
        if (req.getParameter("newuserpassword") != null) {
            if ((req.getParameter("newuserpassword")).length() > 1) {
                u.setPassword(req.getParameter("newuserpassword"));
            } else {
                writer.println("Invalid password");
                writer.flush();
                return;
            }
        }
        if (!(req.getParameter("firstname") == null)) {

            if ((req.getParameter("firstname")).length() > 1) {
                if(Validation.validateString(req.getParameter("firstname"))){
                    u.setLastname(req.getParameter("firstname"));
                }
                else{
                    writer.println("Invalid first name");
                    writer.flush();
                    return;
                }
            } else {
                writer.println("Invalid first name");
                writer.flush();
                return;
            }
        }
        if (!(req.getParameter("lastname") == null)) {

            if ((req.getParameter("lastname")).length() > 1) {
                if(Validation.validateString(req.getParameter("lastname"))){
                    u.setLastname(req.getParameter("lastname"));
                }
                else{
                    writer.println("Invalid Last name");
                    writer.flush();
                    return;
                }

            } else {
                writer.println("Invalid Last name");
                writer.flush();
                return;
            }
        }
        if (!(req.getParameter("role") == null)) {
            try {
                Integer.parseInt(req.getParameter("role"));
            } catch (NumberFormatException e) {
                writer.println("Invalid role");
                writer.flush();
                return;
            }

            if (Integer.parseInt(req.getParameter("role")) == 0 || Integer.parseInt(req.getParameter("role")) == 0) {
                u.setRole_id(Integer.parseInt(req.getParameter("role")));
            } else {
                writer.println("Invalid role");
                writer.flush();
                return;
            }
        }
        if (req.getParameter("email") != null) {
            if (Validation.validateEmail(req.getParameter("email"))) {
                u.setEmail(req.getParameter("email"));
            } else {
                writer.println("Invalid email");
                writer.flush();
                return;
            }
        }
        UserService.getInstance().updateUser(u);
        writer.println("Successfully updated");
        writer.flush();
    }


    /**
     * Should delete a user
     * Manager has power to either de-activate himself or a user
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
            if (req.getParameter("userID") == null) {
                User u = UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password"));
                if (u == null) {
                    writer.println("Invalid user credentials");
                    writer.flush();
                    return;
                } else {
                    u.setActive(false);
                    UserService.getInstance().updateUser(u);
                    writer.println("User deleted");
                    writer.flush();
                }
            } else {
                int id = -1;
                try {
                    id = Integer.parseInt(req.getParameter("userID"));
                } catch (NumberFormatException e) {
                    writer.println("Invalid ID");
                    writer.flush();
                    return;
                }
                if (id == -1) {
                    writer.println("Invalid ID");
                    writer.flush();
                    return;
                } else {
                    User u = UserService.getInstance().getUserById(id);
                    u.setActive(false);
                    UserService.getInstance().updateUser(u);
                    writer.println("Successfully deleted user");
                }
            }
        }
        writer.flush();
    }
}
