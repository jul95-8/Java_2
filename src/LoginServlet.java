import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


@WebServlet("/loginServlet")

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if ((req.getCookies().length > 1) && (login.equals(""))) {

            login = req.getCookies()[1].getValue().toString();
            req.setAttribute("login", login);
            req.getRequestDispatcher("welcomepage.jsp").forward(req, resp);
        } else {
            req.setAttribute("login", login);

            String path = this.getServletContext().getRealPath("/") + "/users.txt";

            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String temp = scanner.nextLine();
                String[] s = temp.split(" ");
                if (s[0].equals(login)) {
                    if (s[1].equals(password)) {

                        if(req.getCookies().length > 1){
                            req.getCookies()[1].setValue(login);
                            req.getCookies()[2].setValue(password);
                        }
                        createCookie(req, resp);
                        req.setAttribute("login", login);
                    }
                    req.getRequestDispatcher("welcomepage.jsp").forward(req, resp);
                } else {
                    resp.getWriter().println("<script type='text/javascript'>");
                    resp.getWriter().println("alert('" + "Incorrect password!" + "');");
                    resp.getWriter().println("</script>");
                }
            }
        }
        req.setAttribute("warning", "  ");
        req.getRequestDispatcher("newuser.jsp").forward(req, resp);
    }

    private void createCookie(HttpServletRequest req, HttpServletResponse resp){
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Cookie loginCookie = new Cookie("login", login);
        Cookie passwordCookie = new Cookie("password", password);
        loginCookie.setMaxAge(60 * 60 * 24);
        passwordCookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(loginCookie);
        resp.addCookie(passwordCookie);
    }

//          через HttpSession:

//            HttpSession session = req.getSession();
//     if ((session.getAttribute("login") != null) && (login.equals(""))) {
//            login = (String) session.getAttribute("login");
//            req.setAttribute("login", login);
//            req.getRequestDispatcher("welcomepage.jsp").forward(req, resp);
//        } else {
//            req.setAttribute("login", login);
//
//            String path = this.getServletContext().getRealPath("/") + "/users.txt";
//
//            Scanner scanner = new Scanner(new File(path));
//            while (scanner.hasNext()) {
//                String temp = scanner.nextLine();
//                String[] s = temp.split(" ");
//                if (s[0].equals(login)) {
//                    if (s[1].equals(password)) {
////                    System.out.println("Password correct!");
//                        session.setAttribute("login", login);
//                        session.setAttribute("password", password);
//                        req.getRequestDispatcher("welcomepage.jsp").forward(req, resp);
//                    } else {
//                        resp.getWriter().println("<script type='text/javascript'>");
//                        resp.getWriter().println("alert('" + "Incorrect password!" + "');");
//                        resp.getWriter().println("</script>");
//                    }
//                }
//            }
//            req.getRequestDispatcher("newuser.jsp").forward(req, resp);
//        }
}
