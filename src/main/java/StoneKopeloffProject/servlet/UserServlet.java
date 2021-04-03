package StoneKopeloffProject.servlet;

import StoneKopeloffProject.model.User;
import StoneKopeloffProject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    /**
     * This method should be how the user can see their current account details
     * TODO:Make it not display PK
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
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(u);
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
    //TODO: Question if the user enters invalid information do we want to keep going through the input and tell them what else they did wrong or
    // if we find an invalid entry then we immediately leave
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("username") == null || req.getParameter("password") == null) {
            writer.println("Invalid user credentials");
            return;
        }
        User u = UserService.getInstance().getUserByLogin(req.getParameter("username"), req.getParameter("password"));
        if (u == null) {
            writer.println("Unable to login");
            return;
        }

        if (!(UserService.getInstance().getUserByUsername(req.getParameter("newusername")) == null)) {
            writer.println("Username already taken");
            return;
        }
        if (!(req.getParameter("newusername") == null)) {
            if (req.getParameter("newusername").length() > 1) {
                u.setUsername(req.getParameter("newusername"));
            } else {
                writer.println("Invalid username");
            }
        }
        if (!(req.getParameter("newuserpassword") == null)) {
            if ((req.getParameter("newuserpassword")).length() > 1) {
                u.setPassword(req.getParameter("newuserpassword"));
            } else {
                writer.println("Invalid password");
                return;
            }
        }
        if (!(req.getParameter("firstname") == null)) {

            if ((req.getParameter("firstname")).length() > 1) {
                if (validateString(req.getParameter("firstname"))) {
                    u.setFirstname(req.getParameter("firstname"));
                } else {
                    writer.println("Invalid first name");
                    return;
                }
            } else {
                writer.println("Invalid first name");
                return;
            }

        }
        if (!(req.getParameter("lastname") == null)) {

            if ((req.getParameter("lastname")).length() > 1) {
                if (validateString(req.getParameter("lastname"))) {
                    u.setFirstname(req.getParameter("lastname"));
                } else {
                    writer.println("Invalid last name");
                    return;
                }
            } else {
                writer.println("Invalid Last name");
                return;
            }
        }
        //credit http://emailregex.com/ for the regex used below
        if (!(req.getParameter("email") == null)) {
            if ((req.getParameter("email")).length() > 1
                    && !(req.getParameter("email")).matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
                    "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {

                u.setEmail(req.getParameter("email"));
            } else {
                writer.println("Invalid email");
                return;
            }

        }
        UserService.getInstance().updateUser(u);
        writer.println("Successfully updated");
        writer.flush();
    }

    /**
     * This method will create a new  user
     * TODO: Do we want to implement a way that the user only has to input the bare bones i.e Username password and email
     * TODO: and leave everything else optional
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
            return;
        }
        // If the username is already linked to a user name then it is not unique and therefore can not be used
        if (!(UserService.getInstance().getUserByUsername(req.getParameter("username")) == null)) {
            writer.println("Username already taken");
            return;
        }
        User u = new User();
        if (req.getParameter("username") == null || req.getParameter("username").length() < 1) {
            writer.println("Invalid username");
            return;
        } else {
            if (req.getParameter("username") != null) {
                u.setUsername(req.getParameter("username"));
            } else {
                writer.println("Invalid username");
                return;
            }
        }
        if (req.getParameter("password") == null || (req.getParameter("password")).length() < 1) {
            writer.println("Invalid password");
            return;
        } else {
            if (req.getParameter("password") != null) {
                u.setPassword(req.getParameter("password"));
            } else {
                writer.println("Invalid password");
                return;
            }
        }

        if (req.getParameter("firstname") != null) {
            if ((req.getParameter("firstname")).length() > 1) {
                if (validateString(req.getParameter("firstname"))) {
                    u.setFirstname(req.getParameter("firstname"));
                } else {
                    writer.println("Invalid first name");
                    return;
                }
            } else {

                writer.println("Invalid first name");
                return;

            }

        }
        if (req.getParameter("lastname") != null) {
            if ((req.getParameter("lastname")).length() > 1) {
                if (validateString(req.getParameter("lastname"))) {
                    u.setLastname(req.getParameter("lastname"));
                } else {
                    writer.println("Invalid last name");
                    return;
                }
            } else {
                writer.println("Invalid last name");
                return;
            }
        }

        //credit http://emailregex.com/ for the regex used below
        if (req.getParameter("email") == null || (req.getParameter("email")).length() < 1
                || !(req.getParameter("email")).matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
                "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            writer.println("Invalid email");
            return;
        } else {
            if (req.getParameter("email") != null) {
                u.setEmail(req.getParameter("email"));
                // I'm putting this here because i think if they got this far that means all the other input was valid
                // And therefore we can set the user as active
                u.setActive(true);
            } else {
                writer.println("Email Required");
                return;
            }
        }

        UserService.getInstance().addUser(u);
        writer.println("Successfully created user");
        //}
        writer.flush();
    }

    /**
     * This method will deactivate the user
     * TODO: Decide if just to deactivate the user or actually casscade delete everything related to the user
     * TODO: Personally up to me id rather deactive the user this way we cant lose track of any reimbursements
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
                return;
            } else {
                u.setActive(false);
                UserService.getInstance().updateUser(u);
            }
        }
        writer.println("User deleted");
        writer.flush();
    }

    public boolean validateString(String str) {
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
