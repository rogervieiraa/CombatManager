package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.combatmanager.security.Configuration;

import javax.swing.ImageIcon;

public class PayInvoiceWindow extends JPanel implements View {
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private JTable table;

	private final String NAME = "Tela Consultar Fatura";
	private final int ACCESS = 7*11;

	@Override
	public int getAccess() {
		return this.ACCESS;
	}

	@Override
	public String getName() {
		return this.NAME;
	}
	
	/**
	 * Create the panel.
	 */
	public JPanel run(Configuration config) {
		JPanel contentPane= new JPanel(); 
		contentPane.setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Pagamento de Faturas");
		internalFrame.setResizable(true);
		internalFrame.setClosable(true);
		internalFrame.setFrameIcon(new ImageIcon(PayInvoiceWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 600, 575);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel lblDe = new JLabel("De: ");
		lblDe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDe.setBounds(10, 11, 46, 14);
		internalFrame.getContentPane().add(lblDe);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setBounds(35, 9, 86, 20);
		internalFrame.getContentPane().add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		JLabel lblTo = new JLabel("At\u00E9: ");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTo.setBounds(133, 12, 46, 14);
		internalFrame.getContentPane().add(lblTo);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(163, 9, 86, 20);
		internalFrame.getContentPane().add(textFieldTo);
		
		JLabel lblStatus = new JLabel("Situa\u00E7\u00E3o: ");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(259, 12, 68, 14);
		internalFrame.getContentPane().add(lblStatus);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Em aberto", "Pagas", "Canceladas"}));
		comboBox.setBounds(325, 9, 100, 20);
		internalFrame.getContentPane().add(comboBox);
		
		JButton btnSearch = new JButton("Pesquisar");
		btnSearch.setBounds(435, 4, 145, 31);
		internalFrame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 47, 558, 483);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Matr\u00EDcula", "Aluno", "Vencimento", "Valor", "Pagamento", "Cancelamento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		scrollPane.setViewportView(table);
		internalFrame.setVisible(true);
		
		return contentPane;
	}
}
