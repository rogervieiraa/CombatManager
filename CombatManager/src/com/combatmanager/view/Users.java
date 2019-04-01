package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class Users extends JPanel implements View {
	public Users() {
	}
	
	private final String NAME = "Tela Usuarios";
	private final int ACCESS = 0;
	
	@Override
	public int getAccess() {
		return this.ACCESS;
	}
	
	@Override
	public String getName() {
		return this.NAME;
	}
	
	private JTextField textField;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirmPassword;
	private JInternalFrame internalFrame;
	
	/**
	 * Create the panel.
	 */
	public JPanel run() {
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
	
		internalFrame = new JInternalFrame("Tela Usuarios");
		internalFrame.setClosable(true);
	
		internalFrame.setBounds(0, 0, 450, 300);
		contentPane.add(internalFrame);
		internalFrame.setVisible(true);
		internalFrame.getContentPane().setLayout(null);
		JToolBar toolBar = new JToolBar();
		toolBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		toolBar.setBounds(10, 11, 414, 50);
		internalFrame.getContentPane().add(toolBar);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSearch.setMaximumSize(new Dimension(65, 40));
		toolBar.add(btnSearch);
		btnSearch.setPreferredSize(new Dimension(65, 40));
		btnSearch.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdd.setPreferredSize(new Dimension(65, 40));
		btnAdd.setMaximumSize(new Dimension(65, 40));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRemove.setPreferredSize(new Dimension(65, 40));
		btnRemove.setMaximumSize(new Dimension(65, 40));
		btnRemove.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSave.setPreferredSize(new Dimension(65, 40));
		btnSave.setMaximumSize(new Dimension(65, 40));
		btnSave.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		toolBar.add(btnSave);
		
		textField = new JTextField();
		textField.setBounds(150, 90, 274, 20);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUser.setBounds(20, 91, 63, 14);
		internalFrame.getContentPane().add(lblUser);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(150, 129, 274, 20);
		internalFrame.getContentPane().add(pwdPassword);
		
		JLabel lblPassword = new JLabel("Senha: ");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(20, 130, 63, 14);
		internalFrame.getContentPane().add(lblPassword);
		
		pwdConfirmPassword = new JPasswordField();
		pwdConfirmPassword.setBounds(150, 172, 274, 20);
		internalFrame.getContentPane().add(pwdConfirmPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirmar Senha:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmPassword.setBounds(20, 173, 133, 14);
		internalFrame.getContentPane().add(lblConfirmPassword);
		
		JLabel lblProfile = new JLabel("Perfil:");
		lblProfile.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfile.setBounds(20, 214, 133, 14);
		internalFrame.getContentPane().add(lblProfile);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--selecione--", "Cadastrar", "Matricular", "Financeiro", "Completo"}));
		comboBox.setBounds(153, 213, 271, 20);
		internalFrame.getContentPane().add(comboBox);
		
		return contentPane;
		
	}
}
