package com.combatmanager.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.combatmanager.database.dao.CityDAO;
import com.combatmanager.database.model.City;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

public class ChooseCityWindow extends JFrame {

	
	private JTextField textFieldName;
	private JTable table;
	private Configuration conf;
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Cidade", "Estado", "Pais"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

	/**
	 * Create the panel.
	 */
	public  ChooseCityWindow(Configuration config) {
		setLayout(null);
		
		conf = config;
		
		JInternalFrame internalFrame = new JInternalFrame("Escolher Cidade");
		internalFrame.setClosable(true);
		internalFrame.setBounds(0, 0, 450, 300);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(72, 12, 230, 20);
		internalFrame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblCity = new JLabel("Cidade:\r\n");
		lblCity.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCity.setBounds(12, 14, 55, 16);
		internalFrame.getContentPane().add(lblCity);
		
		JButton btnSearch = new JButton("Procurar");
		btnSearch.setBounds(314, 9, 114, 26);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				while(model.getRowCount() > 0) {
					model.removeRow(0);
				}
				
				
				
				
			}
		});
		internalFrame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 416, 173);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setMinWidth(10);
		scrollPane.setViewportView(table);
		
		JButton btnSelect = new JButton("Selecionar");
		btnSelect.setBounds(12, 228, 416, 26);
		btnSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentsWindow sw = new StudentsWindow();
				
			}
		});
		internalFrame.getContentPane().add(btnSelect);
		internalFrame.setVisible(true);

	}
	
	private void fillCity () throws SQLException, AccessException {
		CityDAO cityDao = new CityDAO(conf.getConnection());
		List<Object> listCity = new ArrayList<Object>();
		City city = new City();
		
		listCity = cityDao.SelectAll();
		
		for(int i=0;i<listCity.size();i++) {
			city = (City) listCity.get(i);
			model.addRow(new Object[] {city,getName(), city.getState(), city.getCountry()});
		}
		
	}
	
}
