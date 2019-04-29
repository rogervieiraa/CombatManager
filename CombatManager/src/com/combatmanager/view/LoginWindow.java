package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public LoginWindow() {
		setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Combat Manager");
		internalFrame.setResizable(true);
		internalFrame.setClosable(true);
		internalFrame.getContentPane().setBackground(Color.DARK_GRAY);
		internalFrame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(167, 218, 165, 20);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(111, 220, 46, 14);
		internalFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setBounds(111, 249, 102, 14);
		internalFrame.getContentPane().add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 249, 165, 20);
		internalFrame.getContentPane().add(passwordField);
		
		
		internalFrame.getContentPane().add(table);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(LoginWindow.class.getResource("/img/combat.png")));
		lblNewLabel_1.setBounds(0, 11, 440, 180);
		internalFrame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(111, 309, 221, 23);
		internalFrame.getContentPane().add(btnNewButton);
		internalFrame.setFrameIcon(new ImageIcon(LoginWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 450, 403);
		add(internalFrame);
		internalFrame.setVisible(true);

	}
}
