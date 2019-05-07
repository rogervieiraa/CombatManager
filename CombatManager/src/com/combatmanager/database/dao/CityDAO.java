package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.City;

public class CityDAO extends MasterDAO {
	
	
	
	private String selectAll = "select * from cidades order by cidade";
	private String select = "select * from cidades where cidade = ? and estado = ? order by cidade";
	private String insert = "INSERT INTO cidades			"
								+"	(						" 
								+"		cidade, 		"
								+"		estado, 				"
								+"		pais, 	"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"	)";
	private String selectByCityName = "select * from cidades where cidade = ?";
	
	private PreparedStatement pst_selectByCityName;
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	
	Connection io_connection;
	
	public  CityDAO(Connection connection) throws SQLException{

		io_connection = connection;
		
		pst_selectAll = connection.prepareStatement(selectAll);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		pst_selectByCityName = connection.prepareStatement(selectByCityName);
		
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlCity = new ArrayList<Object>();
		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			City city = new City();
			city.setName(rst.getString("cidade"));
			city.setState(rst.getString("estado"));
			city.setCountry(rst.getString("pais"));
			
			
			arlCity.add(city);
		}
		
		return arlCity;
	}
	
	public List<City> SelectByCityName(City aux_city) throws SQLException {
		List<City> arlCity = new ArrayList<City>();
		Set(pst_selectByCityName, 1, aux_city.getName());
		ResultSet rst= pst_selectByCityName.executeQuery();
		
		while(rst.next()) {
			City city = new City();
			city.setName(rst.getString("cidade"));
			city.setState(rst.getString("estado"));
			city.setCountry(rst.getString("pais"));
			
			
			arlCity.add(city);
		}
		
		return arlCity;
	}
	
	
	@Override
	public Object Select(Object parameter) throws SQLException {
		
		pst_select.clearParameters();
		
		City city = null;
		
		Set(pst_select, 1, ((City)parameter).getName());
		Set(pst_select, 2, ((City)parameter).getState());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			city = new City();
			city.setName(rst.getString("cidade"));
			city.setState(rst.getString("estado"));
			city.setCountry(rst.getString("pais"));
			
			
		}
		
		return city;
	}

	@Override
	public void Update(Object parameter, Object new_parameter) throws SQLException {
		//TO DO
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		City city = (City)parameter;
		
		Set(pst_insert, 1, city.getName());
		Set(pst_insert, 2, city.getState());
		Set(pst_insert, 3, city.getCountry());

		pst_insert.execute();
		
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
		// TODO
	}
}
