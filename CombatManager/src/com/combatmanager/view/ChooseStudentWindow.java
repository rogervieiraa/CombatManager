package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ChooseStudentWindow extends JPanel {
	private JTextField textFieldName;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ChooseStudentWindow() {
		setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Escolher Aluno");
		internalFrame.setClosable(true);
		internalFrame.setBounds(0, 0, 450, 300);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(72, 12, 230, 20);
		internalFrame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblName = new JLabel("Nome:");
		lblName.setFont(new Font("Dialog", Font.BOLD, 12));
		lblName.setBounds(12, 14, 55, 16);
		internalFrame.getContentPane().add(lblName);
		
		JButton btnSearch = new JButton("Procurar");
		btnSearch.setBounds(314, 9, 114, 26);
		internalFrame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 416, 174);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Nome", "Idade"
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
		btnSelect.setBounds(12, 229, 416, 26);
		internalFrame.getContentPane().add(btnSelect);
		internalFrame.setVisible(true);

	}
}
