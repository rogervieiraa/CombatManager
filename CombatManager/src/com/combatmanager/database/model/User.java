package com.combatmanager.database.model;

public class User {
	private String user;
	private String profile;
	private final String[] permissionType = {"Cadastral","Matricular","Financeiro","Completo"};
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		for(int i=0;i<this.permissionType.length;i++) {
			if(profile.equals(permissionType[i])) {
				this.profile = profile;
				return;
			}
		}
		// TO DO ERROR
	}

	public String[] getPermissionType() {
		return permissionType;
	}

	@Override
	public String toString() {
		return "User [user=" + user + ", profile=" + profile + "]";
	}
}
