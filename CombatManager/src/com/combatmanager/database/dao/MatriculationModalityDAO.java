package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.database.model.Modality;

public class MatriculationModalityDAO  extends MasterDAO{
	
	
	private String selectAll = "select * from matriculas_modalidades order by modalidade";
	private String select = "select * from matriculas_modalidades where codigo_matricula = ? order by modalidade";
	private String insert = "INSERT INTO matriculas_modalidades			"
								+"	(						" 
								+"		codigo_matricula, 		"
								+"		modalidade, 				"
								+"		graduacao, 	"
								+"		plano, 	"
								+"		data_inicio, 	"
								+"		data_fim, 	"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"	)";
	private String updadeGraduation = "";
	private String deleteByModality = "DELETE FROM matriculas_modalidades where modalidade = ?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_updadeGraduatio;
	private PreparedStatement pst_deleteByModality;
	
	Connection io_connection;
	
	public  MatriculationModalityDAO(Connection connection) throws SQLException{

		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		pst_updadeGraduatio = connection.prepareStatement(updadeGraduation);
		pst_deleteByModality = connection.prepareStatement(deleteByModality);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlMatriculationModality = new ArrayList<Object>();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			MatriculationModality mm = new MatriculationModality();
			mm.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			mm.setModality(rst.getString("modalidade"));
			mm.setGraduation(rst.getString("graducao"));
			mm.setPlan(rst.getString("plano"));
			mm.setBegin_date(rst.getString("data_inicio"));
			mm.setEnd_date(rst.getString("data_fim"));
			
			
			arlMatriculationModality.add(mm);
		}
		
		return arlMatriculationModality;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		MatriculationModality mm = null;
		
		Set(pst_select, 1, ((MatriculationModality)parameter).getMatriculation_code());

		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			mm = new MatriculationModality();
			mm.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			mm.setModality(rst.getString("modalidade"));
			mm.setGraduation(rst.getString("graducao"));
			mm.setPlan(rst.getString("plano"));
			mm.setBegin_date(rst.getString("data_inicio"));
			mm.setEnd_date(rst.getString("data_fim"));
			
			
		}
		
		return mm;
	}

	@Override
	public void Update(Object parameter, Object new_parameter) throws SQLException {
		//TO DO
		
	}

	public void UpdateGraduation(Object parameter, Object new_parameter) throws SQLException {
		//TO DO
		
	}
	
	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		MatriculationModality mm = new MatriculationModality();
		
		Set(pst_insert, 1, mm.getMatriculation_code());
		Set(pst_insert, 2, mm.getModality());
		Set(pst_insert, 3, mm.getGraduation());
		Set(pst_insert, 4, mm.getPlan());
		Set(pst_insert, 5, mm.getBegin_date());
		Set(pst_insert, 6, mm.getEnd_date());

		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
	}

	public void DeleteByModality(Modality modality) throws SQLException {
		pst_deleteByModality.clearParameters();
					
		Set(pst_deleteByModality, 1, modality.getModality());

		pst_deleteByModality.execute();
	}
	
	@Override
	public void Delete(Object parameter) throws SQLException {
		// TODO
	}
}
