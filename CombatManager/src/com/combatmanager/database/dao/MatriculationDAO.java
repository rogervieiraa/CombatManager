package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.City;
import com.combatmanager.database.model.Matriculation;

public class MatriculationDAO extends MasterDAO{
		
	
	private String selectAll = "select * from matriculas order by data_matricula";
	private String select = "select * from matriculas where codigo_matricula = ? order by data_matricula";
	private String insert = "INSERT INTO matriculas			"
								+"	(						" 
								+"		codigo_matricula, 		"
								+"		codigo_aluno, 				"
								+"		data_matricula, 	"
								+"		dia_vencimento, 	"
								+"		data_encerramento, 	"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		DEFAULT, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"	)";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	
	Connection io_connection;
	
	public  MatriculationDAO(Connection connection) throws SQLException{

		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlMatriculation = new ArrayList<Object>();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			Matriculation mat = new Matriculation();
			mat.setCode(Integer.parseInt(rst.getString("codigo_matricula")));
			mat.setStudent_code(Integer.parseInt(rst.getString("codigo_aluno")));
			mat.setMatriculation_date(rst.getString("data_matricula"));
			mat.setDue_date(Integer.parseInt(rst.getString("dia_vencimento")));
			mat.setClosing_date(rst.getString("data_encerramento"));
			
			
			arlMatriculation.add(mat);
		}
		
		return arlMatriculation;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		Matriculation mat = null;
		
		Set(pst_select, 1, ((Matriculation)parameter).getCode());

		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			mat = new Matriculation();
			mat.setCode(Integer.parseInt(rst.getString("codigo_matricula")));
			mat.setStudent_code(Integer.parseInt(rst.getString("codigo_aluno")));
			mat.setMatriculation_date(rst.getString("data_matricula"));
			mat.setDue_date(Integer.parseInt(rst.getString("dia_vencimento")));
			mat.setClosing_date(rst.getString("data_encerramento"));
			
			
		}
		
		return mat;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		//TO DO
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Matriculation mat = (Matriculation)parameter;
		
		Set(pst_insert, 1, mat.getCode());
		Set(pst_insert, 2, mat.getStudent_code());
		Set(pst_insert, 3, mat.getMatriculation_date());
		Set(pst_insert, 4, mat.getDue_date());
		Set(pst_insert, 5, mat.getClosing_date());

		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
		// TODO
	}
}
