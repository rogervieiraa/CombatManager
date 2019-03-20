package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.MatriculationInvoices;


public class MatriculationInvoicesDAO extends MasterDAO{
	
	
	private String selectAll = "select * from faturas_modalidades order by codigo_matricula";
	private String select = "select * from faturas_modalidades where codigo_matricula = ? order by codigo_matricula";
	private String insert = "INSERT INTO faturas_modalidades			"
								+"	(						" 
								+"		codigo_matricula, 		"
								+"		data_vencimento, 				"
								+"		valor, 	"
								+"		data_pagamento, 	"
								+"		data_cancelamento, 	"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"	)";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	
	Connection io_connection;
	
	public  MatriculationInvoicesDAO(Connection connection) throws SQLException{

		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlMatriculationInvoices = new ArrayList<Object>();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			MatriculationInvoices mi = new MatriculationInvoices();
			mi.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			mi.setDue_date(rst.getString("data_vencimento"));
			mi.setValue(Float.parseFloat(rst.getString("valor")));
			mi.setPay_date(rst.getString("data_pagamento"));
			mi.setCancel_date(rst.getString("data_cancelamento"));		
			
			
			arlMatriculationInvoices.add(mi);
		}
		
		return arlMatriculationInvoices;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		MatriculationInvoices mi = null;
		
		Set(pst_select, 1, ((MatriculationInvoices)parameter).getMatriculation_code());

		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			mi = new MatriculationInvoices();
			mi.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			mi.setDue_date(rst.getString("data_vencimento"));
			mi.setValue(Float.parseFloat(rst.getString("valor")));
			mi.setPay_date(rst.getString("data_pagamento"));
			mi.setCancel_date(rst.getString("data_cancelamento"));	
			
			
		}
		
		return mi;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		//TO DO
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		MatriculationInvoices mi = new MatriculationInvoices();
		
		Set(pst_insert, 1, mi.getMatriculation_code());
		Set(pst_insert, 2, mi.getDue_date());
		Set(pst_insert, 3, mi.getValue());
		Set(pst_insert, 4, mi.getPay_date());
		Set(pst_insert, 5, mi.getCancel_date());

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
