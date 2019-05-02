package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class addModalityWindow extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public addModalityWindow() {
		setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Adicionar Modalidades");
		internalFrame.setBounds(0, 0, 298, 232);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(95, 9, 177, 20);
		internalFrame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(95, 42, 177, 20);
		internalFrame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(95, 73, 177, 20);
		internalFrame.getContentPane().add(comboBox_2);
		
		JLabel lblModalidade = new JLabel("Modalidade:");
		lblModalidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModalidade.setBounds(10, 11, 164, 14);
		internalFrame.getContentPane().add(lblModalidade);
		
		JLabel lblGraduao = new JLabel("Gradua\u00E7\u00E3o:");
		lblGraduao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGraduao.setBounds(10, 45, 164, 14);
		internalFrame.getContentPane().add(lblGraduao);
		
		JLabel lblPlano = new JLabel("Plano:");
		lblPlano.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPlano.setBounds(10, 76, 164, 14);
		internalFrame.getContentPane().add(lblPlano);
		
		JLabel lblDataIncio = new JLabel("Data In\u00EDcio:");
		lblDataIncio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataIncio.setBounds(10, 104, 164, 14);
		internalFrame.getContentPane().add(lblDataIncio);
		
		JLabel lblDataFim = new JLabel("Data Fim:");
		lblDataFim.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataFim.setBounds(10, 137, 164, 14);
		internalFrame.getContentPane().add(lblDataFim);
		
		textField = new JTextField();
		textField.setBounds(95, 104, 177, 20);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(95, 135, 177, 20);
		internalFrame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(183, 166, 89, 23);
		internalFrame.getContentPane().add(btnNewButton);
		internalFrame.setVisible(true);

	}
}
