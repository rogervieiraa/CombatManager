package com.combatmanager.security;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.combatmanager.database.ConnectionFactory;
import com.combatmanager.database.model.User;
import com.combatmanager.error.AccessException;

public class Configuration {
	
	private User userLoged;
	/*
	 * We will use the prime numbers to identify who have access
	 * 
	 * If a window have access = 35
	 * Every user that (35 % userPermission == 0) can access the window
	 * 
	 * */
	private final int PRIME_NUMBER[] = {3,5,7,11,13,17,19,23,27};
	private final String DB_IP = null;
	private final String DB_PORT = null;
	private final String DB_NAME = "master";
	private final String DB_USER_NAME = "admin";
	private final String DB_PASSWORD = "admin";	
	private final boolean DEBUG = true;
	private HashMap<Integer, String> sysLog;
	private Integer logKey;
	
	public Configuration(User userLoged) throws Exception {
		sysLog = new HashMap<Integer, String>();
		logKey = 0;
		if(userLoged == null) {
			throw new Exception("ERRO 000: Usuario nulo!");
		}
		this.userLoged = userLoged;
	}
	
	public boolean hasUserLoged() {
		return (this.userLoged != null);
	}
	
	public User getUserLoged() {
		return userLoged;
	}
	
	public boolean getDebug() {
		return this.DEBUG;
	}
	
	/**
	 * @author Roger
	 * @return the a prime value of the user permission
	 */
	public int getPermissionValue() {
		
		String permission = userLoged.getProfile();
		String[] permissionType = User.getPermissionType();
		for(int i=0;i<permissionType.length;i++) {
			if(permission.equals(permissionType[i])) {
				return PRIME_NUMBER[i];
			}
		}
		
		return 0;
	}
	
	public Connection getConnection() throws AccessException {
		if(this.getPermissionValue() != 0) {
			if(this.DB_IP != null) {
				return ConnectionFactory.getConnection(this.DB_IP, this.DB_PORT, this.DB_NAME, this.DB_USER_NAME, this.DB_PASSWORD);
			}
			else {
				return ConnectionFactory.getConnection(this.DB_NAME, this.DB_USER_NAME, this.DB_PASSWORD);
			}
		}
		
		throw new AccessException("Configuracao" , "Usuario indefinido, permissao 0.");
	}
	
	public void addToSystemLog(String log) {
		
		sysLog.put(logKey, log);
		logKey++;
	}
	
	public void saveLog() {
		// TO DO
	}
	
}
