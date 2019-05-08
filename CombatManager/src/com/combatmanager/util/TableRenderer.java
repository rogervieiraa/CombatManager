package com.combatmanager.util;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.combatmanager.database.dao.MatriculationDAO;
import com.combatmanager.database.dao.MatriculationInvoicesDAO;
import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationInvoices;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.security.Configuration;

public class TableRenderer extends DefaultTableCellRenderer{
	
	
    private static final long   serialVersionUID    = 1L;
    private Configuration config;
    
    public TableRenderer(Configuration config) {
    	this.config = config;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    Color c = Color.WHITE;
	    
	    Matriculation mat = new Matriculation();
	    MatriculationModality mm = new MatriculationModality() ;
	    List<MatriculationModality> listMat = new ArrayList<MatriculationModality>();
	    MatriculationModalityDAO matDao;
	    
	    mat.setCode(Integer.parseInt(table.getValueAt(row, 0).toString()));
	    
	    try {
			matDao = new MatriculationModalityDAO(config.getConnection());
			
			listMat =  matDao.SelectGraduationByMatriculation(mat);
			mm = listMat.get(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
	    
	    if ((table.getValueAt(row, 4) != null) && (table.getValueAt(row, 5) == null)){
	        c = Color.GREEN;	    	
	    }else if ((table.getValueAt(row, 4) == null) && (table.getValueAt(row, 5) != null)) {
	    	c = Color.YELLOW;
	    }else if ((listMat.size() == 1) && (mm.getEnd_date() != null)) {
	    	c = Color.RED;
	    }
	    label.setBackground(c);
    	return label;
	}
}
