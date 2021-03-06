package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.City;
import com.combatmanager.database.model.Student;
import com.combatmanager.util.DataFixer;

public class StudentDAO extends MasterDAO {
	
	private String selectAll = "select * from alunos order by aluno";
	private String selectById = "select * from alunos where codigo_aluno = ? order by aluno";
	private String select = "select * from alunos where (aluno = ?) order by aluno";
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
								+"		?					"
								+"	)";
	private String update = "UPDATE alunos				"
							+ " SET		  				"
							+ "		aluno = ?,			"
							+ "		data_nascimento = ?,"
							+ "		sexo = ?,			"
							+ "		telefone = ?,		"
							+ "		celular = ?,		"
							+ "		email = ?,			"
							+ "		observacao = ?,		"
							+ "		endereco = ?,		"
							+ "		numero = ?,			"
							+ "		complemento = ?,	"
							+ "		bairro = ?,			"
							+ "		cidade = ?,			"
							+ "		estado = ?,			"
							+ "		pais = ?,			"
							+ "		cep = ?				"
							+ "WHERE					"
							+ "		codigo_aluno = ?	";
	private String delete = "DELETE FROM alunos WHERE codigo_aluno = ?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
	private PreparedStatement pst_selectById;
	DataFixer fixer;
	Connection io_connection;
	
	public  StudentDAO(Connection connection) throws SQLException{
		fixer = new DataFixer();
		io_connection = connection;
		
		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		pst_update = connection.prepareStatement(update);
		pst_delete = connection.prepareStatement(delete);
		pst_selectById = connection.prepareStatement(selectById);
	}
	
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlStudent = new ArrayList<Object>();
		City city = new City ();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			Student student = new Student();
			student.setIndex(Integer.parseInt(rst.getString("codigo_aluno")));
			student.setName(rst.getString("aluno"));
			student.setBirthday(rst.getString("data_nascimento").replace("-", "/"));
			student.setAdress(rst.getString("endereco"));
			student.setCellPhoneNumber(rst.getString("telefone"));
			student.setEmail(rst.getString("email"));
			student.setCep(rst.getString("cep"));
			student.setHomeNumber(rst.getString("numero"));
			student.setLocal(rst.getString("bairro"));
			student.setExtraInformation(rst.getString("complemento"));
			student.setNote(rst.getString("observacao"));
			student.setPhoneNumber(rst.getString("celular"));
			student.setSex((rst.getString("sexo")));
			
			//Creating city object
			city.setName(rst.getString("cidade"));
			city.setCountry(rst.getString("pais"));
			city.setState(rst.getString("estado"));
			//End
			
			student.setCity(city);
			
			arlStudent.add(student);
		}
		
		return arlStudent;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		pst_select.clearParameters();
		
		Student student = (Student)parameter;
		
		String aux ;
		
		Set(pst_select, 1, student.getName());
		
		ResultSet rst = pst_select.executeQuery();
		
		student.setName("");
		student.setEmail("");
		
		if (rst.next()) {
			student = new Student();
			student.setIndex(Integer.parseInt(rst.getString("codigo_aluno")));
			student.setName(rst.getString("aluno"));
			if(rst.getString("data_nascimento") != null) {
				aux = rst.getString("data_nascimento").replace("-", "/");
				student.setBirthday(aux);
			}
			
			student.setSex(rst.getString("sexo"));
			student.setPhoneNumber(rst.getString("telefone"));
			student.setCellPhoneNumber(rst.getString("celular"));
			student.setEmail(rst.getString("email"));
			student.setNote(rst.getString("observacao"));
			student.setAdress(rst.getString("endereco"));
			student.setHomeNumber(rst.getString("numero"));
			student.setExtraInformation(rst.getString("complemento"));
			student.setLocal(rst.getString("bairro"));
			
			//Creating city object
			City city = new City();
			
			city.setName(rst.getString("cidade"));
			city.setCountry(rst.getString("pais"));
			city.setState(rst.getString("estado"));
			//End
			
			student.setCity(city);
			student.setCep(rst.getString("cep"));
			return student;
		}
		
		throw new SQLException("Estutante nao encontrado");
	}
	
	public Object SelectById(Object parameter) throws SQLException {
		pst_selectById.clearParameters();
		
		Student student = (Student)parameter;
		pst_selectById.setInt(1, student.getIndex());
		
		ResultSet rst = pst_selectById.executeQuery();
		
		if (rst.next()) {
			Student return_student = new Student();
			return_student.setIndex(Integer.parseInt(rst.getString("codigo_aluno")));
			return_student.setName(rst.getString("aluno"));
			if(rst.getString("data_nascimento") != null) {
				return_student.setBirthday(rst.getString("data_nascimento").replace("-", "/"));
			}
			return_student.setSex(rst.getString("sexo"));
			return_student.setPhoneNumber(rst.getString("telefone"));
			return_student.setCellPhoneNumber(rst.getString("celular"));
			return_student.setEmail(rst.getString("email"));
			return_student.setNote(rst.getString("observacao"));
			return_student.setAdress(rst.getString("endereco"));
			return_student.setHomeNumber(rst.getString("numero"));
			return_student.setExtraInformation(rst.getString("complemento"));
			return_student.setLocal(rst.getString("bairro"));
			
			//Creating city object
			City city = new City();
			
			city.setName(rst.getString("cidade"));
			city.setCountry(rst.getString("pais"));
			city.setState(rst.getString("estado"));
			//End
			
			return_student.setCity(city);
			return_student.setCep(rst.getString("cep"));
			return return_student;
		}

		return null;
	}

	@Override
	public void Update(Object last_parameter, Object new_parameter) throws SQLException {
		pst_update.clearParameters();
		
		Student student = new Student ();
		student = (Student)new_parameter;
		DataFixer df = new DataFixer();
		
		
		
		Set(pst_update, 1, student.getName());
		if(student.getBirthday().charAt(0) >= '0' && student.getBirthday().charAt(0) <= '0') {
			pst_update.setDate(2, df.fixData(student.getBirthday(), "/"));
		}else {
			pst_update.setDate(2, null);
		}
		
		Set(pst_update, 3, student.getSex());
		Set(pst_update, 4, student.getPhoneNumber());
		Set(pst_update, 5, student.getCellPhoneNumber());
		Set(pst_update, 6, student.getEmail());
		Set(pst_update, 7, student.getNote());
		Set(pst_update, 8, student.getAdress());
		Set(pst_update, 9, student.getHomeNumber());
		Set(pst_update, 10, student.getExtraInformation());
		Set(pst_update, 11, student.getLocal());
		
		City city = new City();
		
		city = student.getCity();
		
		if(city != null && city.getName() != null && (!city.getName().equals(""))) {
			Set(pst_update, 12, city.getName());
			Set(pst_update, 13, city.getState());
			Set(pst_update, 14, city.getCountry());
		}
		else {
			Set(pst_update, 12, null);
			Set(pst_update, 13, null);
			Set(pst_update, 14, null);
		}
		
		Set(pst_update, 15, student.getCep());
		
		student = (Student) last_parameter;
		
		pst_update.setInt(16, student.getIndex());
		
		pst_update.execute();
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Student student = new Student();
		student = (Student)parameter;
		
		String auxs [];
		
		auxs = student.getBirthday().split("/");
		
		Set(pst_insert, 1, student.getName());
		Date dt1 = fixer.fixData(student.getBirthday(), "-");
		pst_insert.setDate(2, dt1);
		Set(pst_insert, 3, student.getSex());
		Set(pst_insert, 4, student.getPhoneNumber());
		Set(pst_insert, 5, student.getCellPhoneNumber());
		Set(pst_insert, 6, student.getEmail());
		Set(pst_insert, 7, student.getNote());
		Set(pst_insert, 8, student.getAdress());
		Set(pst_insert, 9, student.getHomeNumber());
		Set(pst_insert, 10, student.getExtraInformation());
		Set(pst_insert, 11, student.getLocal());
		
		//Creating city object
		City city = new City();
		
		city = student.getCity();
		//End
		if(!city.getName().equals("")) {
			Set(pst_insert, 12, city.getName());
			Set(pst_insert, 13, city.getState());
			Set(pst_insert, 14, city.getCountry());
		}
		else {
			Set(pst_insert, 12, null);
			Set(pst_insert, 13, null);
			Set(pst_insert, 14, null);
		}
		
		Set(pst_insert, 15, student.getCep());

		pst_insert.execute();
		
		
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
		pst_delete.clearParameters();
		
		Student student = new Student();
		student = (Student) parameter;
		
		pst_delete.setInt(1, student.getIndex());
		
		pst_delete.execute();
	}

}
