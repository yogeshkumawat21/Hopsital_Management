package hospital2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String member = request.getParameter("member");
        String hid = request.getParameter("hid");
        String url = "jdbc:mysql://localhost:3306/hospital";
        String user = "root";
        String dbPassword = "1234";

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            Connection con = DriverManager.getConnection(url, user, dbPassword);
            // Prepare SQL query
            String query = "SELECT * FROM data WHERE name = ? AND password = ? AND field = ?  AND hid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, member);
            ps.setString(4, hid);
            // Execute the query
            ResultSet rs = ps.executeQuery();
            // Check if the user is present in the database
            if (rs.next()) {
            	  request.getSession().setAttribute("loggedIn", true);
            	    // Forward to home page
            	    response.sendRedirect("dashboard.html"); // Assuming your home page is named index.html request dispatch
            } else {
                // User is not valid
                out.println("<h1>You are not a valid user</h1>");
               
            }
            rs.close();
            ps.close();
            con.close();
            out.close();
        } catch (Exception ex) {
            out.println(ex.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
