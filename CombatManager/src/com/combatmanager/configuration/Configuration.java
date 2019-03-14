package com.combatmanager.configuration;

import com.combatmanager.database.model.User;

public class Configuration {
	
	private User userLoged;
	
	public Configuration(User userLoged) {
		this.userLoged = userLoged;
	}

	public User getUserLoged() {
		return userLoged;
	}

	public void setUserLoged(User userLoged) {
		this.userLoged = userLoged;
	}
	
	/**
	 * @author Roger
	 * @return the value of the user permission
	 */
	public int getPermissionValue() {
		
		String permission = userLoged.getProfile();
		String[] permissionType = userLoged.getPermissionType();
		for(int i=0;i<permissionType.length;i++) {
			if(permission.equals(permissionType[i])) {
				return i;
			}
		}
		
		
		return -1;
	}
	
}
