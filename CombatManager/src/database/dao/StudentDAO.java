package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.model.Student;

public class StudentDAO extends MasterDAO {
	
	private String selectAll = "select * from alunos order by aluno";
	private String select = "select * from alunos where aluno = ? order by aluno";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	
	public  StudentDAO(Connection connection) throws SQLException{
		pst_selectAll = connection.prepareStatement(selectAll);
		
	}
	
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlStudent = new ArrayList<Object>(); 
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			Student student = new Student();
			student.setIndex(Integer.parseInt(rst.getString("codigo_aluno")));
			student.setBirthday("");
			student.setAdress("Avenida Brasil");
			student.setCellPhoneNumber("(48) 00000-0000");
			student.setCep("88800-000");
			student.setHomeNumber("000");
			student.setIndex(1);
			student.setLocal("Bairo X");
			student.setNote("Good Person");
			student.setPhoneNumber("48 3000-0000");
			student.setSex('M');
			arlStudent.add(student);
		}
		
		return arlStudent;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Update(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Insert(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
