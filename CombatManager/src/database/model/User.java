package database.model;

public class User {
	private String user;
	private String profile;
	
	
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
		this.profile = profile;
	}
	
	
	@Override
	public String toString() {
		return "User [user=" + user + ", profile=" + profile + "]";
	}
}
