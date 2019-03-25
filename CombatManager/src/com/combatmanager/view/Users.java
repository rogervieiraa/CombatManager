package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JToolBar;
import java.awt.Component;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import javax.swing.DebugGraphics;

public class Users extends JPanel implements View {
	private JTextField textUser;
	private JLabel lblUsurio;
	private JTextField textPassword;
	private JTextField textConfirmPassword;

	private final String NAME = "Tela Principal";
	private final int ACCESS = 0;

	@Override
	public int getAccess() {
		return this.ACCESS;
	}

	@Override
	public String getName() {
		return this.NAME;
	}
	

	/**
	 * @author Romulo Create the frame.
	 */
	
	/**
	 * Create the panel.
	 */
	public JPanel run() {
		JPanel contentPane= new JPanel();
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		textUser = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, textUser, -10, SpringLayout.EAST, contentPane);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		JComboBox profile = new JComboBox();
		springLayout.putConstraint(SpringLayout.EAST, profile, -10, SpringLayout.EAST, contentPane);
		profile.addItem("--Selecione--");
		profile.addItem("Cadastrar");
		profile.addItem("Matricular");
		profile.addItem("Financeiro");
		profile.addItem("Completo");
		contentPane.add(profile);
		
		lblUsurio = new JLabel("Usu\u00E1rio: ");
		springLayout.putConstraint(SpringLayout.WEST, textUser, 68, SpringLayout.EAST, lblUsurio);
		springLayout.putConstraint(SpringLayout.NORTH, lblUsurio, 99, SpringLayout.NORTH, contentPane);
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsurio.setRequestFocusEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, textUser, -3, SpringLayout.NORTH, lblUsurio);
		springLayout.putConstraint(SpringLayout.WEST, lblUsurio, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblUsurio);
		
		textPassword = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textPassword, 18, SpringLayout.SOUTH, textUser);
		springLayout.putConstraint(SpringLayout.EAST, textPassword, -10, SpringLayout.EAST, contentPane);
		textPassword.setColumns(10);
		contentPane.add(textPassword);
		
		textConfirmPassword = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textConfirmPassword, 16, SpringLayout.SOUTH, textPassword);
		springLayout.putConstraint(SpringLayout.EAST, textConfirmPassword, -10, SpringLayout.EAST, contentPane);
		textConfirmPassword.setColumns(10);
		contentPane.add(textConfirmPassword);
		
		JLabel lblSenha = new JLabel("Senha: ");
		springLayout.putConstraint(SpringLayout.WEST, lblSenha, 10, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, textPassword, 77, SpringLayout.EAST, lblSenha);
		springLayout.putConstraint(SpringLayout.NORTH, lblSenha, 18, SpringLayout.SOUTH, lblUsurio);
		lblSenha.setRequestFocusEnabled(false);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar senha: ");
		springLayout.putConstraint(SpringLayout.WEST, lblConfirmarSenha, 10, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, textConfirmPassword, 6, SpringLayout.EAST, lblConfirmarSenha);
		springLayout.putConstraint(SpringLayout.NORTH, lblConfirmarSenha, 19, SpringLayout.SOUTH, lblSenha);
		lblConfirmarSenha.setRequestFocusEnabled(false);
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblConfirmarSenha);
		
		JLabel lblProfile = new JLabel("Perfil: ");
		springLayout.putConstraint(SpringLayout.WEST, lblProfile, 10, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.NORTH, profile, 0, SpringLayout.NORTH, lblProfile);
		springLayout.putConstraint(SpringLayout.WEST, profile, 86, SpringLayout.EAST, lblProfile);
		springLayout.putConstraint(SpringLayout.NORTH, lblProfile, 21, SpringLayout.SOUTH, lblConfirmarSenha);
		lblProfile.setRequestFocusEnabled(false);
		lblProfile.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblProfile);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		toolBar.setPreferredSize(new Dimension(17, 7));
		springLayout.putConstraint(SpringLayout.NORTH, toolBar, 10, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, toolBar, 0, SpringLayout.WEST, lblUsurio);
		springLayout.putConstraint(SpringLayout.SOUTH, toolBar, 68, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, toolBar, 0, SpringLayout.EAST, textUser);
		contentPane.add(toolBar);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearch.setMaximumSize(new Dimension(100, 40));
		btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSearch.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSearch.setForeground(Color.BLACK);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar.add(btnSearch);
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAdd.setMaximumSize(new Dimension(100, 40));
		btnAdd.setSize(new Dimension(50, 60));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnRemove.setMaximumSize(new Dimension(100, 40));
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSave.setMaximumSize(new Dimension(100, 40));
		toolBar.add(btnSave);
		return contentPane;
	}
}
