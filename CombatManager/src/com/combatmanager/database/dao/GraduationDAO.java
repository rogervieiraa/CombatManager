package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.Graduation;
import com.combatmanager.database.model.Modality;

public class GraduationDAO extends MasterDAO{
		
		private String selectAll = "select * from graduacoes order by graduacao";
		private String select = "select * from graduacoes where graduacao = ? order by graduacao";
		private String selectByModality = "select * from graduacoes where modalidade = ? order by graduacao";
		private String insert = "INSERT INTO graduacoes			"
									+"	(						" 
									+"		modalidade, 		"
									+"		graduacao			"
									+"	)						"  
									+"  VALUES 					"
									+"	(						"
									+"		?, 					"
									+"		?					"
									+"	)";
		private String deleteByModality = "DELETE FROM graduacoes WHERE modalidade = ?";
		private String delete = "DELETE FROM graduacoes WHERE modalidade = ? AND graduacao = ?";
		
		private PreparedStatement pst_selectAll;
		private PreparedStatement pst_select;
		private PreparedStatement pst_selectByModality;
		private PreparedStatement pst_insert;
		private PreparedStatement pst_delete;
		private PreparedStatement pst_deleteByModality;
		
		Connection io_connection;
		
		public GraduationDAO(Connection connection) throws SQLException{

			io_connection = connection;
			pst_selectAll = connection.prepareStatement(selectAll);
			pst_select = connection.prepareStatement(select);
			pst_selectByModality = connection.prepareStatement(selectByModality);
			pst_insert = connection.prepareStatement(insert);
			pst_deleteByModality = connection.prepareStatement(deleteByModality);
			pst_delete = connection.prepareStatement(delete);
			
		}
		
		public List<Object> SelectAll() throws SQLException {
			List<Object> arlGraduation = new ArrayList<Object>();
			ResultSet rst= pst_selectAll.executeQuery();
			
			while(rst.next()) {
				Graduation graduation = new Graduation();
				graduation.setModality(rst.getString("modalidade"));
				graduation.setGraduation(rst.getString("graduacao"));
				
				
				arlGraduation.add(graduation);
			}
			
			return arlGraduation;
		}
		
		@Override
		public Object Select(Object parameter) throws SQLException {
			pst_select.clearParameters();
			
			Graduation graduation = null;
			
			Set(pst_select, 1, ((Graduation)parameter).getGraduation());
			
			ResultSet rst = pst_select.executeQuery();
			
			if (rst.next()) {
				graduation = new Graduation();
				graduation.setGraduation(rst.getString("graduacao"));
				graduation.setModality(rst.getString("modalidade"));
				
				
			}
			
			return graduation;
		}
		
		public List<Graduation> SelectGraduationByModality(Modality modality) throws SQLException {
			List<Graduation> arlGraduation = new ArrayList<Graduation>();

			Set(pst_selectByModality, 1, (modality.getModality()));

			ResultSet rst= pst_selectByModality.executeQuery();

			while(rst.next()) {
				Graduation graduation = new Graduation();
				graduation.setModality(rst.getString("modalidade"));
				graduation.setGraduation(rst.getString("graduacao"));
				
				
				arlGraduation.add(graduation);
			}

			return arlGraduation;
		}
		
		@Override
		public void Update(Object parameter, Object new_parameter) throws SQLException {
			//TO DO
			
		}

		@Override
		public void Insert(Object parameter) throws SQLException {
			pst_insert.clearParameters();
			
			Graduation graduation = (Graduation)parameter;
			
			Set(pst_insert, 1, graduation.getModality());
			Set(pst_insert, 2, graduation.getGraduation());

			pst_insert.execute();
		}

		public void DeleteByModality(Modality modality) throws SQLException {
			pst_deleteByModality.clearParameters();
						
			Set(pst_deleteByModality, 1, modality.getModality());

			pst_deleteByModality.execute();
		}
		
		@Override
		public void Delete(Object parameter) throws SQLException {
			pst_delete.clearParameters();
			
			Graduation graduation = (Graduation)parameter;
			
			Set(pst_delete, 1, graduation.getModality());
			Set(pst_delete, 2, graduation.getGraduation());

			pst_delete.execute();
		}
}
