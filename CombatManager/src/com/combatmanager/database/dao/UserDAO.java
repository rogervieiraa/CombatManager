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
	private String select = "select * from usuarios where usuario = ? or perfil = ? order by usuario";
	private String insert = "INSERT INTO usuarios			"
								+"	(						" 
								+"		usuario, 			"
								+"		perfil 				"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		?, 					"
								+"		? 					"
								+"	)";
	private String update = "UPDATE usuarios	"
							+ "SET				"
							+ "		usuario = ?,"
							+ "		perfil = ?	"
							+ "WHERE			"
							+ "		usuario = ?";
	private String delete = "DELETE FROM usuarios WHERE usuario = ?";
	private String is_create_role		=	"create	role		?" +
												"	with		login" +
												"			encrypted password		? " +
												"			in role				admin";
	private String is_alter_role	=	"alter	role		?" +
												"	with		login" +
												"			encrypted password		?";

	private String is_drop_role		=	"drop	role		?";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
	private PreparedStatement pst_createRole;
	private PreparedStatement pst_alterRole;
	private PreparedStatement pst_dropRole;
	
	Connection io_connection;
	
	public  UserDAO(Connection connection) throws SQLException{
		
		io_connection = connection;
		
		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		pst_update = connection.prepareStatement(update);
		pst_delete = connection.prepareStatement(delete);
		pst_createRole = connection.prepareStatement(is_create_role);
		pst_alterRole = connection.prepareStatement(is_alter_role);
		pst_dropRole = connection.prepareStatement(is_drop_role);
		
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
		pst_select.clearParameters();
		
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
	public void Update(Object last_parameter, Object new_parameter) throws SQLException {
		pst_alterRole.clearParameters();
		pst_update.clearParameters();
		
		User user = new User();
		
		user = (User) new_parameter;
		
		Set(pst_update, 1, user.getUser());
		Set(pst_update, 2, user.getProfile());
		
		Set(pst_alterRole, 2, user.getPassword());
		
		user = (User) last_parameter;
		
		Set(pst_update, 3, user.getUser());
		Set(pst_alterRole, 1, user.getUser());
		
		pst_update.execute();
		pst_alterRole.execute();
		
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		pst_createRole.clearParameters();
		
		User user = (User)parameter;
		
		Set(pst_insert, 1, user.getUser());
		Set(pst_insert, 2, user.getProfile());

		pst_insert.execute();
		
		Set(pst_createRole, 1, user.getUser());
		Set(pst_createRole, 2, user.getPassword());
		
		pst_createRole.execute();
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
		pst_delete.clearParameters();
		pst_dropRole.clearParameters();
		
		User user = new User();
		user = (User) parameter;
		
		Set(pst_delete, 1, user.getUser());
		Set(pst_dropRole, 1, user.getUser());
		
		pst_delete.execute();
		pst_dropRole.execute();
		
		
	}
}
