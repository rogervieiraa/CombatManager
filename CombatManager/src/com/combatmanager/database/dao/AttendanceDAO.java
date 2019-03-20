package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.Attendance;
import com.combatmanager.database.model.MatriculationInvoices;

public class AttendanceDAO extends MasterDAO{
	
	
	private String selectAll = "select * from assiduidade order by codigo_matricula";
	private String select = "select * from assiduidade where codigo_matricula = ? order by codigo_matricula";
	private String insert = "INSERT INTO assiduidade			"
								+"	(						" 
								+"		codigo_matricula, 		"
								+"		data_entrada, 				"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		?, 					"
								+"		?, 					"
								+"	)";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	
	Connection io_connection;
	
	public  AttendanceDAO(Connection connection) throws SQLException{

		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlAttendance = new ArrayList<Object>();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			Attendance att = new Attendance();
			att.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			att.setEntry_date(rst.getString("data_entrada"));			
			
			arlAttendance.add(att);
		}
		
		return arlAttendance;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		Attendance att = null;
		
		Set(pst_select, 1, ((Attendance)parameter).getMatriculation_code());

		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			att = new Attendance();
			att.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			att.setEntry_date(rst.getString("data_entrada"));	
			
			
		}
		
		return att;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		//TO DO
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Attendance att = new Attendance();
		
		Set(pst_insert, 1, att.getMatriculation_code());
		Set(pst_insert, 2, att.getEntry_date());

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
