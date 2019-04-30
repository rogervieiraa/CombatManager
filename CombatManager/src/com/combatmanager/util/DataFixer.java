package com.combatmanager.util;

import java.sql.Date;

public class DataFixer {

	
	public Date fixData(String s, String divisor) {
		String[] aux = s.split(divisor);
		if(aux.length != 3) {
			System.out.println("Invalido");
			return null;
		}
		int year = Integer.parseInt(aux[0]);
		int month = Integer.parseInt(aux[1]);
		int day = Integer.parseInt(aux[2]);
		System.out.println("String inicial: " + s);
		System.out.println("Convertida para inteiro:");
		System.out.println("Year " + year);
		System.out.println("month " + month);
		System.out.println("day " + day);
		Date ans = new Date(year,month,day);
		System.out.println("Convertida para Date:");
		System.out.println("Year " + ans.getYear());
		System.out.println("month " + ans.getMonth());
		System.out.println("day " + ans.getDay());
		return ans;
	}
	
}
