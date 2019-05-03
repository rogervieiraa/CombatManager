package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.combatmanager.security.Configuration;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class EnrollmentReportWindow extends JPanel implements View {
	
	private final String NAME = "Tela Relatorio";
	private final int ACCESS = 5*11;
	
	@Override
	public int getAccess() {
		return this.ACCESS;
	}
	
	@Override
	public String getName() {
		return this.NAME;
	}
	
	private JTextField textFieldFrom;
	private JTextField textFieldTo;

	/**
	 * Create the panel.
	 */
	public JPanel run(Configuration config) {
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Relat\u00F3rio de Matricula ");
		internalFrame.setClosable(true);
		internalFrame.setFrameIcon(new ImageIcon(EnrollmentReportWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 270, 217);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel lblPeriod = new JLabel("Perido");
		lblPeriod.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPeriod.setBounds(101, 11, 46, 14);
		internalFrame.getContentPane().add(lblPeriod);
		
		JLabel lblfrom = new JLabel("De:");
		lblfrom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblfrom.setBounds(65, 60, 46, 14);
		internalFrame.getContentPane().add(lblfrom);
		
		JLabel lblTo = new JLabel("At\u00E9:");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTo.setBounds(65, 102, 46, 14);
		internalFrame.getContentPane().add(lblTo);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setBounds(117, 58, 86, 20);
		internalFrame.getContentPane().add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(117, 100, 86, 20);
		internalFrame.getContentPane().add(textFieldTo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"HTML"}));
		comboBox.setBounds(10, 152, 101, 20);
		internalFrame.getContentPane().add(comboBox);
		
		JButton btnProcess = new JButton("Processar");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnProcess.setBounds(120, 151, 124, 23);
		internalFrame.getContentPane().add(btnProcess);
		internalFrame.setVisible(true);
		return contentPane;
	}
}
