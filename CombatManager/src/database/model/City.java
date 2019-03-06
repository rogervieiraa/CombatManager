package database.model;

public class City {
	private String name;
	private String state;
	private String country;
	
	public City(String name, String state, String country) {
		this.name = name;
		this.state = state;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", state=" + state + ", country=" + country + "]";
	}
	
	
	
}
