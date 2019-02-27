package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	/**
	 * @author Romulo, Roger
	 * @param database name
	 * @param username
	 * @param user's password
	 * @return a connection with a local database
	 */
	public static Connection getConnection(final String dataBaseName, 
											final String username, final 
											String password) 
	{
		
		return getConnection("localhost","5432",dataBaseName,username,password);
	}
	
	/**
	 * @author Romulo, Roger
	 * @param database's ip
	 * @param database's port
	 * @param database's name
	 * @param username
	 * @param user's password
	 * @return
	 */
	public static Connection getConnection(final String dataBaseIp, 
											final String dataBasePort, 
											final String dataBaseName, 
											final String username, 
											final String password) 
	{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://"+dataBaseIp+":"+dataBasePort+"/"+dataBaseName,username,password);
		} catch (Exception e) {
			System.out.println("Catch Error - Connection Factory");
			e.printStackTrace();
		}
		
		return connection;
	}
	
}
