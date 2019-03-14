package test;

import database.model.City;

public class JunitTestExemple {
	
	public String test() {
		
		City city = new City("Criciuma","SC", "Brasil");

		return city.toString();
		
	}
}
