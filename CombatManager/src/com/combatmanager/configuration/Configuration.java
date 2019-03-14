package com.combatmanager.configuration;

import com.combatmanager.database.model.User;

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
	
	public Configuration(User userLoged) {
		this.userLoged = userLoged;
	}
	
	public boolean hasUserLoged() {
		return (this.userLoged != null);
	}
	
	public User getUserLoged() {
		return userLoged;
	}

	public void setUserLoged(User userLoged) {
		this.userLoged = userLoged;
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
		
		
		return -1;
	}
	
}
