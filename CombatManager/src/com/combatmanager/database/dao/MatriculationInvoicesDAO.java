package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

<<<<<<< HEAD
import javax.swing.JOptionPane;

=======
import com.combatmanager.database.model.Matriculation;
>>>>>>> 70c96f60f00535d4d0eaca651a00aa2e3d310e43
import com.combatmanager.database.model.MatriculationInvoices;
import com.combatmanager.util.DataFixer;


public class MatriculationInvoicesDAO extends MasterDAO{
	
	
	private String selectAll = "select * from faturas_matriculas";
	private String selectAllByMatriculation = "select * from faturas_matriculas where codigo_matricula = ?";
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
							+ "		valor = ?,			  "
							+ "		data_pagamento = ?,   "
							+ "		data_cancelamento = ? "
							+ "WHERE					 "
							+ "		data_vencimento = ? ";
	private String delete = "DELETE FROM faturas_matriculas WHERE codigo_matricula = ?";
	
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
<<<<<<< HEAD
	private Calendar cal;
=======
	private PreparedStatement pst_selectAllByMatriculation;
	DataFixer dataFixer;
>>>>>>> 70c96f60f00535d4d0eaca651a00aa2e3d310e43
	
	DataFixer dataFixer;
	Connection io_connection;
	
	public  MatriculationInvoicesDAO(Connection connection) throws SQLException{
		dataFixer = new DataFixer();
		io_connection = connection;
		
		pst_insert = connection.prepareStatement(insert);
		pst_update = connection.prepareStatement(update);
		pst_delete = connection.prepareStatement(delete);
		pst_selectAllByMatriculation = connection.prepareStatement(selectAllByMatriculation);
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlMatriculationInvoices = new ArrayList<Object>();
		return arlMatriculationInvoices;
	}
	
	
	public List<MatriculationInvoices> SelectAllByMatriculation(Matriculation m) throws SQLException {
		pst_selectAllByMatriculation.clearParameters();
		pst_selectAllByMatriculation.setInt(1, m.getCode());;
		List<MatriculationInvoices> arlMatriculationInvoices = new ArrayList<MatriculationInvoices>();
		
		ResultSet rst= pst_selectAllByMatriculation.executeQuery();
		
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
		//Date dt1 = datafixer.fixData(mi.getDue_date(), "-");
		//Date dt2 = datafixer.fixData(mi.getCancel_date(), "-");
		//Date dt3 = datafixer.fixData(mi.getPay_date(), "-");
		
		//pst_update.setDate( 1, dt1);
		try {
			pst_update.setFloat(1, mi.getValue());
			cal = setDate(mi.getPay_date());
			System.out.println("teste");
			System.out.println(cal.getTime().toString());
			pst_update.setDate(2, (java.sql.Date) cal.getTime());
			cal = setDate(mi.getCancel_date());
			pst_update.setDate(3, (java.sql.Date) cal.getTime());
			cal = setDate(mi.getDue_date());
			pst_update.setDate(4, (java.sql.Date) cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
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
		
		Matriculation mi = new Matriculation();
		
		mi = (Matriculation) parameter;
		pst_delete.setInt(1, mi.getCode());
		
		pst_delete.execute();
		
	}
	
	private Calendar setDate(String data) {
		String aux [];
		int year, month, day;
		System.out.println(data);
		aux = data.split("-");
		year = Integer.parseInt(aux[0]);
		System.out.println(year);
		month = Integer.parseInt(aux[1]);
		System.out.println(month);
		day = Integer.parseInt(aux[2]);
		System.out.println(day);
		
		cal = new GregorianCalendar();
		
		cal.set(year, month, day);
		
		return cal;
	}
}
