package database;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectionFactory {
	
	public static void main(String[] args) {
		Connection conn = ConnectionFactory.getConnection("master","admin","admin");
		
		try {
			conn.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println("Catch Error - Test Connection Factory");
			e.printStackTrace();
		}
		
	}
	
}
