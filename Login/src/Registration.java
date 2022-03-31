

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String Name=request.getParameter("Name");
		String Number=request.getParameter("Number");
		RequestDispatcher dispatcher = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "Admin");
			PreparedStatement pst=con.prepareStatement("insert into Registration(Namee,Numberr)values(?,?)" );
pst.setString(1, Name);
pst.setString(2, Number);
int rows=pst.executeUpdate();
if(rows>0)
{
	request.setAttribute("status", "sucess");
	dispatcher = request.getRequestDispatcher("Registrationdone.jsp");

}
else
{
	request.setAttribute("status", "failed");
	
}
dispatcher.forward(request,response);

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			
		}
		}
		
//		PrintWriter out=response.getWriter();
//		out.print(Name);
//		out.print(Number);
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
