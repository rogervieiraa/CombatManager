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
	private String select = "select * from alunos where aluno = ? and email = ? order by aluno";
	private String insert = "INSERT INTO alunos			"
								+"	(						" 
								+"		codigo_aluno, 		"
								+"		aluno, 				"
								+"		data_nascimento, 	"
								+"		sexo, 				"
								+"		telefone, 			"
								+"		celular,			" 
								+"      email, 				"
								+"		observacao, 		"
								+"		endereco, 			"
								+"		numero, 			"
								+"		complemento, 		"
								+"		bairro, 			"
								+"		cidade, 			" 
								+"      estado, 			"
								+"		pais, 				"
								+"		cep					"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		DEFAULT, 			"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					" 
								+"      ?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					" 
								+"      ?, 					"
								+"		?, 					"
								+"		?"
								+"	)";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	
	public  StudentDAO(Connection connection) throws SQLException{

		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		
	}
	
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlStudent = new ArrayList<Object>(); 
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			Student student = new Student();
			student.setIndex(Integer.parseInt(rst.getString("codigo_aluno")));
			student.setName(rst.getString("aluno"));
			student.setBirthday(rst.getString("data_nascimento"));
			student.setAdress(rst.getString("endereco"));
			student.setCellPhoneNumber(rst.getString("telefone"));
			student.setEmail(rst.getString("email"));
			student.setCep(rst.getString("cep"));
			student.setHomeNumber(rst.getString("numero"));
			student.setLocal(rst.getString("bairro"));
			student.setExtraInformation(rst.getString("complemento"));
			student.setNote(rst.getString("observacao"));
			student.setPhoneNumber(rst.getString("celular"));
			student.setSex((rst.getString("sexo").charAt(0)));
			arlStudent.add(student);
		}
		
		return arlStudent;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		Student student = null;
		
		Set(pst_select, 1, ((Student)parameter).getName());
		Set(pst_select, 1, ((Student)parameter).getEmail());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			student = new Student();
			student.setName(rst.getString("aluno"));
			student.setBirthday(rst.getString("data_nascimento"));
			student.setSex(rst.getString("sexo").charAt(0));
			student.setPhoneNumber(rst.getString("telefone"));
			student.setCellPhoneNumber(rst.getString("celular"));
			student.setEmail(rst.getString("email"));
			student.setNote(rst.getString("observacao"));
			student.setAdress(rst.getString("endereco"));
			student.setHomeNumber(rst.getString("numero"));
			student.setExtraInformation(rst.getString("complemento"));
			student.setLocal(rst.getString("bairro"));
			student.setCity(rst.getString("cidade"));
			student.setCep(rst.getString("cep"));
			student.setCountry(rst.getString("pais"));
			
		}
		
		return student;
	}

	@Override
	public Object Update(Object parameter) throws SQLException {
		//TO DO
		return null;
	}

	@Override
	public Object Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Student student = (Student)parameter;
		
		Set(pst_insert, 1, student.getName());
		Set(pst_insert, 2, student.getBirthday());
		Set(pst_insert, 3, student.getSex());
		Set(pst_insert, 4, student.getPhoneNumber());
		Set(pst_insert, 5, student.getCellPhoneNumber());
		Set(pst_insert, 6, student.getEmail());
		Set(pst_insert, 7, student.getNote());
		Set(pst_insert, 8, student.getAdress());
		Set(pst_insert, 9, student.getHomeNumber());
		Set(pst_insert, 10, student.getExtraInformation());
		Set(pst_insert, 11, student.getLocal());
		Set(pst_insert, 12, student.getCity());
		//Set(pst_insert, 1, student.get);
		
		return null;
	}

	@Override
	public Object Delete(Object parameter) throws SQLException {
		// TODO
		return null;
	}

}
