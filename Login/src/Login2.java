
import java.io.*;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class Login2
 */
@WebServlet("/Login2")
public class Login2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String Username = request.getParameter("Username");
		String Passwordd = request.getParameter("Password");
		RequestDispatcher dispatcher = null;
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "Admin");
			PreparedStatement pst = con.prepareStatement("select * from login where Username=? and Passwordd=?");
			pst.setString(1, Username);
			pst.setString(2, Passwordd);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				dispatcher = request.getRequestDispatcher("Logindone.jsp");
				dispatcher.forward(request, response);

			} else {
				dispatcher = request.getRequestDispatcher("Login2.jsp");
				dispatcher.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
