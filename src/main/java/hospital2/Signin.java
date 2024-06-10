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
@WebServlet("/signup")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Signin() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","1234");
			//for data insertion 
			PreparedStatement ps=con.prepareStatement("insert into data(name,mobile,password,email,field) values(?,?,?,?,?)");
			ps.setString(1, request.getParameter("name"));
			ps.setString(2, request.getParameter("mobile"));
			ps.setString(3, request.getParameter("password"));
			ps.setString(4, request.getParameter("email"));
			ps.setString(5, request.getParameter("member"));
			
	
			int status=ps.executeUpdate();
			con.close();
			 response.sendRedirect("login.html");
		}
		catch(Exception ex) {
			out.println(ex);
		}
			}

	
	

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
