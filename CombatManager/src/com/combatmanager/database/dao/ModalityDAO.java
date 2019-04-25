package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.Modality;

public class ModalityDAO extends MasterDAO{
	private String selectAll = "select * from modalidades order by modalidade";
	private String select = "select * from modalidades where modalidade = ? order by modalidade";
	private String insert = "INSERT INTO modalidades			"
								+"	(						" 
								+"		modalidade	 		"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		? 					"
								+"	)";
	private String delete = "DELETE FROM modalidades where modalidade = ?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_delete;
	
	Connection io_connection;
	
	public  ModalityDAO(Connection connection) throws SQLException{

		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		pst_delete = connection.prepareStatement(delete);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlModality = new ArrayList<Object>();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			Modality modality = new Modality();
			modality.setModality(rst.getString("modalidade"));
			
			
			arlModality.add(modality);
		}
		
		return arlModality;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		pst_select.clearParameters();
		
		Modality modality = null;
		
		Set(pst_select, 1, ((Modality)parameter).getModality());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			modality = new Modality();
			modality.setModality(rst.getString("modalidade"));
			
			
		}
		
		return modality;
	}

	@Override
	public void Update(Object parameter, Object new_parameter) throws SQLException {
		//TO DO
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Modality modality = (Modality)parameter;
		
		Set(pst_insert, 1, modality.getModality());
		
		pst_insert.execute();
		
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
		pst_delete.clearParameters();
		
		Modality modality = (Modality)parameter;
		
		Set(pst_delete, 1, modality.getModality());

		pst_delete.execute();
		
	}
}
