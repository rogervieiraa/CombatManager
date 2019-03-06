package test;

import model.City;
import model.Student;

public class TestStudent {
	public static void main(String[] args) {
		
		Student student = new Student();
		
		student.setBirthday(21,8,1999);
		System.out.println(student.getBirthday());
		student.setBirthday("21/8/1999");
		System.out.println(student.getBirthday());
		student.setAdress("Avenida Brasil");
		student.setCellPhoneNumber("(48) 00000-0000");
		student.setCep("88800-000");
		student.setCity(new City("São Paulo", "São Paulo", "Brasil"));
		student.setHomeNumber("000");
		student.setIndex(1);
		student.setLocal("Bairo X");
		student.setNote("Good Person");
		student.setPhoneNumber("48 3000-0000");
		student.setSex('M');
		
		System.out.println(student.toString());
		
	}
}
