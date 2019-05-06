package com.combatmanager.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChooseCityWindow extends JPanel {

	
	private JTextField textFieldName;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ChooseCityWindow() {
		setLayout(null);
		
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
		internalFrame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 416, 173);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Cidade", "Estado"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setMinWidth(10);
		scrollPane.setViewportView(table);
		
		JButton btnSelect = new JButton("Selecionar");
		btnSelect.setBounds(12, 228, 416, 26);
		internalFrame.getContentPane().add(btnSelect);
		internalFrame.setVisible(true);

	}
}
