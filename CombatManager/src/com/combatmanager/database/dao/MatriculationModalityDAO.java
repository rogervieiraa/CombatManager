package com.combatmanager.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.database.model.Modality;
import com.combatmanager.util.DataFixer;

public class MatriculationModalityDAO  extends MasterDAO{
	
	
	private String selectAll = "select * from matriculas_modalidades order by modalidade";
	private String select = "select * from matriculas_modalidades where codigo_matricula = ? AND modalidade = ? order by modalidade";
	private String insert = "INSERT INTO matriculas_modalidades			"
								+"	(						" 
								+"		codigo_matricula, 		"
								+"		modalidade, 				"
								+"		graduacao, 	"
								+"		plano, 	"
								+"		data_inicio, 	"
								+"		data_fim 	"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		? 					"
								+"	)";
	private String selectByMatriculation = "select * from matriculas_modalidades where codigo_matricula = ? order by modalidade";
	private String update = "UPDATE matriculas_modalidades SET data_fim = ? WHERE codigo_matricula = ?";
	private String deleteByModality = "DELETE FROM matriculas_modalidades where modalidade = ?";
	private String deleteByMatriculation = "DELETE FROM matriculas_modalidades where codigo_matricula = ?";
	private String selectByActive = "SELECT * FROM matriculas_modalidades where data_fim is null order by codigo_matricula";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_deleteByModality;
	private PreparedStatement pst_selectByMatriculation;
	private PreparedStatement pst_deleteByMatriculation;
	private PreparedStatement pst_selectByActive;
	
	DataFixer dataFixer;
	Connection io_connection;
	
	public  MatriculationModalityDAO(Connection connection) throws SQLException{
		dataFixer = new DataFixer();
		io_connection = connection;
		
		pst_selectAll = connection.prepareStatement(selectAll);
		pst_selectByMatriculation = connection.prepareStatement(selectByMatriculation);
		pst_select = connection.prepareStatement(select);
		pst_insert = connection.prepareStatement(insert);
		pst_update = connection.prepareStatement(update);
		pst_deleteByModality = connection.prepareStatement(deleteByModality);
		pst_deleteByMatriculation = connection.prepareStatement(deleteByMatriculation);
		pst_selectByActive = connection.prepareStatement(selectByActive);
	}
	
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlMatriculationModality = new ArrayList<Object>();

		ResultSet rst= pst_selectAll.executeQuery();
		
		while(rst.next()) {
			MatriculationModality mm = new MatriculationModality();
			mm.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			mm.setModality(rst.getString("modalidade"));
			mm.setGraduation(rst.getString("graduacao"));
			mm.setPlan(rst.getString("plano"));
			mm.setBegin_date(rst.getString("data_inicio"));
			mm.setEnd_date(rst.getString("data_fim"));
			
			
			arlMatriculationModality.add(mm);
		}
		
		return arlMatriculationModality;
	}
	
	public List<MatriculationModality> SelectAllActive() throws SQLException {
		List<MatriculationModality> arlMatriculationModality = new ArrayList<MatriculationModality>();

		ResultSet rst= pst_selectByActive.executeQuery();
		
		while(rst.next()) {
			MatriculationModality mm = new MatriculationModality();
			mm.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			mm.setModality(rst.getString("modalidade"));
			mm.setGraduation(rst.getString("graduacao"));
			mm.setPlan(rst.getString("plano"));
			mm.setBegin_date(rst.getString("data_inicio"));
			mm.setEnd_date(rst.getString("data_fim"));
			
			
			arlMatriculationModality.add(mm);
		}
		
		return arlMatriculationModality;
	}
	
	@Override
	public Object Select(Object parameter) throws SQLException {
		pst_select.clearParameters();
		
		MatriculationModality mm = null;
		
		Set(pst_select, 1, ((MatriculationModality)parameter).getMatriculation_code());
		Set(pst_select, 2, ((MatriculationModality)parameter).getModality());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			mm = new MatriculationModality();
			mm.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			mm.setModality(rst.getString("modalidade"));
			mm.setGraduation(rst.getString("graduacao"));
			mm.setPlan(rst.getString("plano"));
			mm.setBegin_date(rst.getString("data_inicio"));
			mm.setEnd_date(rst.getString("data_fim"));
			
			
		}
		
		return mm;
	}

	public List<MatriculationModality> SelectGraduationByMatriculation(Matriculation matriculation) throws SQLException {
		List<MatriculationModality> arlMatriculationModality = new ArrayList<MatriculationModality>();
		pst_selectByMatriculation.setInt(1, matriculation.getCode());
		ResultSet rst= pst_selectByMatriculation.executeQuery();
		
		while(rst.next()) {
			MatriculationModality mm = new MatriculationModality();
			mm.setMatriculation_code(Integer.parseInt(rst.getString("codigo_matricula")));
			mm.setModality(rst.getString("modalidade"));
			mm.setGraduation(rst.getString("graduacao"));
			mm.setPlan(rst.getString("plano"));
			mm.setBegin_date(rst.getString("data_inicio"));
			mm.setEnd_date(rst.getString("data_fim"));
			
			arlMatriculationModality.add(mm);
		}
		
		return arlMatriculationModality;
	}
	
	
	
	@Override
	public void Update(Object parameter, Object new_parameter) throws SQLException {
		pst_update.clearParameters();
		
		MatriculationModality mm = (MatriculationModality) parameter;
		
		pst_update.setInt(2, mm.getMatriculation_code());
		pst_update.setDate(1, dataFixer.fixData(mm.getEnd_date().replace("/", "-"), "-"));
		
		pst_update.execute();
	}
	
	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		MatriculationModality mm = (MatriculationModality) parameter;
		
		pst_insert.setInt(1, mm.getMatriculation_code());
		Set(pst_insert, 2, mm.getModality());
		Set(pst_insert, 3, mm.getGraduation());
		Set(pst_insert, 4, mm.getPlan());
		Date dt1 = dataFixer.fixData(mm.getBegin_date(), "-");
		pst_insert.setDate(5, dt1);
		Date dt2 = dataFixer.fixData(mm.getEnd_date(), "-");
		pst_insert.setDate(6, dt2);


		pst_insert.execute();
	}

	public void DeleteByModality(Modality modality) throws SQLException {
		pst_deleteByModality.clearParameters();
					
		Set(pst_deleteByModality, 1, modality.getModality());

		pst_deleteByModality.execute();
	}
	
	public void DeleteByMatriculation(Matriculation matriculatin) throws SQLException {
		pst_deleteByMatriculation.clearParameters();
		
		pst_deleteByMatriculation.setInt(1,matriculatin.getCode());

		pst_deleteByMatriculation.execute();
	}
	
	@Override
	public void Delete(Object parameter) throws SQLException {
		// TODO
	}
}
