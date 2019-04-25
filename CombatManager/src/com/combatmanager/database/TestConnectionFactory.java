package com.combatmanager.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.combatmanager.database.dao.StudentDAO;

public class TestConnectionFactory {
	
	public static void main(String[] args) {
		Connection conn = ConnectionFactory.getConnection("master","jackson","12345");
		
		try {
			conn.setAutoCommit(false);
			StudentDAO dao = new StudentDAO(conn);
			List<Object> lst = dao.SelectAll();
			System.out.println(lst.size());
		} catch (SQLException e) {
			System.out.println("Catch Error - Test Connection Factory");
			e.printStackTrace();
		}
		
	}
	
}
