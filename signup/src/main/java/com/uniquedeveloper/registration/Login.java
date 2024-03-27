package com.uniquedeveloper.registration;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uemail = request.getParameter("username");
		String upwd	= request.getParameter("passward");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "tejas", "tiger");
			PreparedStatement pst = con.prepareStatement ("select * from users where uemail = ? and upwd = ?");
			pst.setString (1, uemail);
			pst.setString(2, upwd);
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
			 	session.setAttribute("name", rs.getString("uname"));
			 	dispatcher = request.getRequestDispatcher("index.jsp");
			}else {
				request.setAttribute("status","failed");
				dispatcher.forward (request,response);
				
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
	}

	}
}
