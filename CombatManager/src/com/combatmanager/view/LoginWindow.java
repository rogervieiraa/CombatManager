package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.combatmanager.controller.MainController;
import com.combatmanager.database.dao.UserDAO;
import com.combatmanager.database.model.User;
import com.combatmanager.security.Configuration;

import controller.CombatImage;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	private User local_user;
	/**
	 * Create the panel.
	 */
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(CombatImage.combatvinte_20x20.getImage());
	
		setResizable(false);
		setTitle("Combat Manager 1.0");
		getContentPane().setLayout(null);
		setBounds(0, 0, 450, 403);
		local_user = null;
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(167, 218, 165, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(111, 220, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSenha.setBounds(111, 249, 102, 14);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 249, 165, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(CombatImage.combat);
		lblNewLabel_1.setBounds(0, 11, 444, 180);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDAO userDao = null;
				
				
				
				
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Favor preencher campo de usuario.");
					return;
				}
				if(passwordField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Favor preencher campo de senha.");
					return;
				}
				
				User default_user = new User("admin", "Completo", "admin");
				User admin_user = new User();
				Configuration standart_config;
				
				try {
					standart_config = new Configuration(default_user);
					userDao = new UserDAO(standart_config.getConnection());
					
					admin_user.setUser(textField.getText());
					admin_user = (User) userDao.Select(admin_user);
					admin_user.setPassword(passwordField.getText());
					
					standart_config = new Configuration(admin_user);
					userDao = new UserDAO(standart_config.getConnection());
					admin_user = (User) userDao.Select(admin_user);
					admin_user.setPassword(passwordField.getText());
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"Usuario ou senha invalido");
					return;
				}
			
				setVisible(false);
				MainController mc = new MainController();
				mc.run(admin_user);
			}
		});
		btnNewButton.setBounds(111, 309, 221, 23);
		contentPane.add(btnNewButton);
		contentPane.setBounds(0, 0, 450, 403);
		contentPane.setVisible(true);
		setContentPane(contentPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	public User userLoged() {
		return local_user;
	}
}
