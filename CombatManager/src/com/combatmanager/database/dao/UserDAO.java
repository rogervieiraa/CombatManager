package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.User;

public class UserDAO extends MasterDAO{
	
	
	private String selectAll = "select * from usuarios order by usuario";
	private String select = "select * from usuarios where usuario = ? and perfil = ? order by usuario";
	private String insert = "INSERT INTO usuarios			"
								+"	(						" 
								+"		usuario, 		"
								+"		perfil, 				"
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
	
	public  UserDAO(Connection connection) throws SQLException{

		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlUser = new ArrayList<Object>();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			User user = new User();
			user.setUser(rst.getString("usuario"));
			user.setProfile(rst.getString("perfil"));
			
			
			arlUser.add(user);
		}
		
		return arlUser;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		User user = null;
		
		Set(pst_select, 1, ((User)parameter).getUser());
		Set(pst_select, 2, ((User)parameter).getProfile());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			user = new User();
			user.setUser(rst.getString("usuario"));
			user.setProfile(rst.getString("perfil"));
			
			
		}
		
		return user;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		//TO DO
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		User user = (User)parameter;
		
		Set(pst_insert, 1, user.getUser());
		Set(pst_insert, 2, user.getProfile());

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
