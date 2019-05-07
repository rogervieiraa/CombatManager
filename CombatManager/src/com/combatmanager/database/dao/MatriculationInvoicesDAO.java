package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.MatriculationInvoices;
import com.combatmanager.util.DataFixer;


public class MatriculationInvoicesDAO extends MasterDAO{
	
	
	private String selectAll = "select * from faturas_matriculas";
	private String select = "select * from faturas_matriculas where (data_vencimento between cast(? as date) and cast(? as date))";
	private String insert = "INSERT INTO faturas_matriculas			"
								+"	(						" 
								+"		codigo_matricula, 		"
								+"		data_vencimento, 				"
								+"		valor, 	"
								+"		data_pagamento, 	"
								+"		data_cancelamento 	"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		? 					"
								+"	)";
	private String update = "UPDATE faturas_matriculas"
							+ "SET"
							+ "		data_vencimento = ?, "
							+ "		valor = ?,			 "
							+ "		data_pagamento = ?,  "
							+ "		data_cancelamento = ?"
							+ "WHERE					 "
							+ "		data_vencimento = ? ";
	private String delete = "DELETE FROM faturas_matriculas WHERE codigo_matricula = ?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
	DataFixer dataFixer;
	
	private DataFixer datafixer;
	Connection io_connection;
	
	public  MatriculationInvoicesDAO(Connection connection) throws SQLException{
		dataFixer = new DataFixer();
		io_connection = connection;
		
		pst_insert = connection.prepareStatement(insert);
		pst_update = connection.prepareStatement(update);
		pst_delete = connection.prepareStatement(delete);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlMatriculationInvoices = new ArrayList<Object>();
		return arlMatriculationInvoices;
	}
	
	public List<MatriculationInvoices> SelectAlll(String opt) throws SQLException {
			
			if (opt.equals("Todas")) {
				selectAll += " order by data_vencimento";
				
				pst_selectAll = io_connection.prepareStatement(selectAll);
			}else if (opt.equals("Em aberto")) {
				selectAll += " where data_pagamento is null order by data_vencimento";
				
				pst_selectAll = io_connection.prepareStatement(selectAll);
			}else if (opt.equals("Pagas")) {
				selectAll += " where data_pagamento is not null order by data_vencimento";
				
				pst_selectAll = io_connection.prepareStatement(selectAll);
			}else if (opt.equals("Canceladas")) {
				selectAll += " where data_cancelamento is not null order by data_vencimento";
				
				pst_selectAll = io_connection.prepareStatement(selectAll);
			}
			
			List<MatriculationInvoices> arlMatriculationInvoices = new ArrayList<MatriculationInvoices>();
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
		MatriculationInvoices mi = new MatriculationInvoices();
		
		return mi;
	}
	
	public List<MatriculationInvoices> SelectFilter(String data1, String data2, String opt) throws SQLException {
		
		if (opt.equals("Todas")) {
			select += " order by data_vencimento";
			
			pst_select = io_connection.prepareStatement(select);
			pst_select.clearParameters();
		}else if (opt.equals("Em aberto")) {
			select += " and data_pagamento is null order by data_vencimento";
			
			pst_select = io_connection.prepareStatement(select);
			pst_select.clearParameters();
		}else if (opt.equals("Pagas")) {
			select += " and data_pagamento is not null order by data_vencimento";
			
			pst_select = io_connection.prepareStatement(select);
			pst_select.clearParameters();
		}else if (opt.equals("Canceladas")) {
			select += " and data_cancelamento is not null order by data_vencimento";
			
			pst_select = io_connection.prepareStatement(select);
			pst_select.clearParameters();
		}
		
		List<MatriculationInvoices> arlMatriculationInvoices = new ArrayList<MatriculationInvoices>();
		
		MatriculationInvoices mi = null;
		
		Set(pst_select, 1, data1);
		Set(pst_select, 2, data2);

		
		ResultSet rst = pst_select.executeQuery();
		
		while(rst.next()) {
			mi = new MatriculationInvoices();
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
	public void Update(Object last_parameter, Object new_parameter) throws SQLException {
		pst_update.clearParameters();
		
		MatriculationInvoices mi = new MatriculationInvoices();
		
		mi = (MatriculationInvoices) new_parameter;
		Date dt1 = datafixer.fixData(mi.getDue_date(), "-");
		Date dt2 = datafixer.fixData(mi.getCancel_date(), "-");
		Date dt3 = datafixer.fixData(mi.getPay_date(), "-");
		
		pst_update.setDate( 1, dt1);
		pst_update.setFloat(2, mi.getValue());
		pst_update.setDate(3, dt3);
		pst_update.setDate(4, dt2);
		
		mi = (MatriculationInvoices) last_parameter;
		
		
		
		pst_update.setDate(5, dt1);
		
		pst_update.execute();
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		MatriculationInvoices mi = (MatriculationInvoices) parameter;
		
		pst_insert.setInt(1, mi.getMatriculation_code());
		Date dt3 = dataFixer.fixData(mi.getDue_date(), "-");
		pst_insert.setDate(2, dt3);
		pst_insert.setDouble(3, mi.getValue());
		Date dt1 = dataFixer.fixData(mi.getPay_date(), "-");
		pst_insert.setDate(4, dt1);
		Date dt2 = dataFixer.fixData(mi.getCancel_date(), "-");
		pst_insert.setDate(5, dt2);

		pst_insert.execute();
		
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
		pst_delete.clearParameters();
		
		MatriculationInvoices mi = new MatriculationInvoices();
		
		mi = (MatriculationInvoices) parameter;
		
		Set(pst_delete, 1, mi.getMatriculation_code());
		
		pst_delete.execute();
		
	}
}
