package test;

import database.model.City;

public class TestCity {
	
	public static void main(String[] args) {
		
		City city = new City("Criciuma","SC", "Brasil");
		System.out.println(city.toString());
		
	}
}
