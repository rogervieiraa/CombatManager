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
		// TODO
		return null;
	}

	@Override
	public Object Update(Object parameter) throws SQLException {
		// TODO
		return null;
	}

	@Override
	public Object Insert(Object parameter) throws SQLException {
		// TODO
		return null;
	}

	@Override
	public Object Delete(Object parameter) throws SQLException {
		// TODO
		return null;
	}

}
