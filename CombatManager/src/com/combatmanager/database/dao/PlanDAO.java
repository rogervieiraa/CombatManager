package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.City;
import com.combatmanager.database.model.Plan;

public class PlanDAO extends MasterDAO{
	
	
	private String selectAll = "select * from planos order by plano";
	private String select = "select * from planos where plano = ? order by plano";
	private String insert = "INSERT INTO planos			"
								+"	(						" 
								+"		modalidade, 		"
								+"		plano, 				"
								+"		valor_mensal, 	"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"	)";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	
	Connection io_connection;
	
	public  PlanDAO(Connection connection) throws SQLException{

		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlPlan = new ArrayList<Object>();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			Plan plan = new Plan();
			plan.setPlan(rst.getString("plano"));
			plan.setModality(rst.getString("modalidade"));
			plan.setMonth_value(Float.parseFloat(rst.getString("valor_mensal")));
			
			
			arlPlan.add(plan);
		}
		
		return arlPlan;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		Plan plan = null;
		
		Set(pst_select, 1, ((Plan)parameter).getPlan());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			plan = new Plan();
			plan.setPlan(rst.getString("plano"));
			plan.setModality(rst.getString("modalidade"));
			plan.setMonth_value(Float.parseFloat(rst.getString("valor_mensal")));
			
			
		}
		
		return plan;
	}

	@Override
	public void Update(Object parameter, Object new_parameter) throws SQLException {
		//TO DO
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Plan plan = (Plan)parameter;
		
		Set(pst_insert, 1, plan.getModality());
		Set(pst_insert, 2, plan.getPlan());
		Set(pst_insert, 3, plan.getMonth_value());

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
