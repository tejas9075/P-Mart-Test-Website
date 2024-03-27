package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String uname = request.getParameter("name");
	    String upwd = request.getParameter("pass");
	    String uemail = request.getParameter("email");
	    String umobile = request.getParameter("contact");
	    RequestDispatcher dispatcher = null;
	    Connection con = null;
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "tejas", "tiger");
	        PreparedStatement pst = con.prepareStatement("insert into users(uname, upwd, uemail, umobile)values (?, ?, ?, ?)");
	        pst.setString(1, uname);
	        pst.setString(2, upwd); // corrected column index
	        pst.setString(3, uemail); // corrected column index
	        pst.setString(4, umobile);
	        int rowCount = pst.executeUpdate();
	        dispatcher = request.getRequestDispatcher("registration.jsp");
	        if (rowCount > 0) {
	            request.setAttribute("status", "success");
	        } else {
	            request.setAttribute("status", "failed");
	        }
	        dispatcher.forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

		
		}
	

