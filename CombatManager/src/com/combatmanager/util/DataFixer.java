package com.combatmanager.util;

import java.sql.Date;
import java.util.Calendar;

public class DataFixer {

	
	public Date fixData(String s, String divisor) {
		if(s == null || s.equals("")) {
			return null;
		}
		String[] aux = s.split(divisor);
		if(aux.length != 3) {
			System.out.println("Invalido");
			return null;
		}
		int year = Integer.parseInt(aux[0]) - 1900;
		int month = Integer.parseInt(aux[1]) - 1;
		int day = Integer.parseInt(aux[2]);
		
		if(month == 0) {
			year--;
			month = 12;
		}
		
		Date ans = new Date(year,month,day);

		return ans;
	}
	
}
