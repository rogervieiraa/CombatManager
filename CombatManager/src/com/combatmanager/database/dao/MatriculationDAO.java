package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.Date;
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
								+"		data_encerramento 	"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		DEFAULT, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		? 					"
								+"	)";
	private String update = "UPDATE matriculas"
							+ "SET"
							+ "		data_matricula = ?,"
							+ "		dia_vencimento = ?,"
							+ "		data_encerramento = ?"
							+ "WHERE"
							+ "		codigo_matricula = ?";
	private String delete = "DELETE FROM matriculas WHERE codigo_matricula = ?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
	
	Connection io_connection;
	
	public  MatriculationDAO(Connection connection) throws SQLException{

		io_connection = connection;
		
		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		pst_update = connection.prepareStatement(update);
		pst_delete = connection.prepareStatement(delete);
		
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
		pst_select.clearParameters();
		
		Matriculation mat = null;
		pst_select.setInt(1, ((Matriculation)parameter).getCode());
		
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
	public void Update(Object last_parameter, Object new_parameter) throws SQLException {
		pst_update.clearParameters();
		
		Matriculation mat = new Matriculation();
		
		mat = (Matriculation) new_parameter;
		String[] aux = mat.getMatriculation_date().split("-");
		Date dt1 = new Date(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),Integer.parseInt(aux[2]));
		
		pst_update.setDate(1, dt1);
		pst_update.setInt(2, mat.getDue_date());
		if(mat.getClosing_date() != null) {
			System.out.println(mat.getClosing_date());
			aux = mat.getClosing_date().split("-");
			Date dt2 = new Date(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),Integer.parseInt(aux[2]));

			pst_update.setDate(3, dt2);

		}else {
			pst_update.setDate(3, null);
		}
		
		mat = (Matriculation) last_parameter;
		
		pst_update.setInt(4, mat.getCode());
		
		pst_update.execute();
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Matriculation mat = (Matriculation)parameter;
		
		String[] aux = mat.getMatriculation_date().split("-");
		
		
		pst_insert.setInt(1, mat.getCode());
		pst_insert.setInt(2, mat.getStudent_code());
		pst_insert.setDate(3, new Date(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),Integer.parseInt(aux[2])));
		pst_insert.setInt(4, mat.getDue_date());
		aux = mat.getClosing_date().split("-");
		pst_insert.setDate(5, new Date(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),Integer.parseInt(aux[2])));


		pst_insert.execute();
		
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
		pst_delete.clearParameters();
		
		Matriculation mat = new Matriculation();
		
		mat = (Matriculation) parameter;
		pst_delete.setInt(1, mat.getCode());
		
		pst_delete.execute();
		
	}
}
