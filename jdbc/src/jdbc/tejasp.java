package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class tejasp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "tejas", "tiger") ;
				System.out.println("Connection Estabilished Successfully");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
