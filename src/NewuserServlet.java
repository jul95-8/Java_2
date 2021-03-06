import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@WebServlet("/newuserServlet")

public class NewuserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confpass = req.getParameter("confpass");

        String path = this.getServletContext().getRealPath("/") + "/users.txt";

        req.setAttribute("login", login);

        if (password.equals(confpass) && !login.equals("")) {
            update(path, login + " " + password);
            req.setAttribute("warning", "  ");
            req.getRequestDispatcher("welcomepage.jsp").forward(req, resp);
        } else {
            req.setAttribute("warning", "Please ensure that your password and your confirm password are the same! ");
            req.getRequestDispatcher("newuser.jsp").forward(req, resp);
        }

    }

    private static void update(String path, String text) throws FileNotFoundException {
        try {
            StringBuilder sb = new StringBuilder();
            String oldFile = read(path);
            sb.append(oldFile);
            sb.append(text);
            write(path, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write(String path, String text) throws IOException {
        File temp = new File(path);
        try {
            if (!temp.exists()) {
                temp.createNewFile();
            }
            PrintWriter writer = new PrintWriter(temp.getAbsoluteFile());

            writer.print(text);
            writer.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String read(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        String temp = "";
        while (scanner.hasNext()) {
            temp += scanner.nextLine();
            temp += "\n";
        }
        return temp;
    }
}

