package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
		contentPane.setBackground(Color.DARK_GRAY);
		internalFrame = new JInternalFrame("Tela Usuarios");
		internalFrame.setFrameIcon(new ImageIcon(Modality.class.getResource("/img/combat.png")));		
		internalFrame.setBounds(0, 0, 450, 300);
		contentPane.add(internalFrame);
		internalFrame.setVisible(true);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setFrameIcon(new ImageIcon(Modality.class.getResource("/img/combatvinte.png")));
		
		BasicInternalFrameUI ui = (BasicInternalFrameUI)internalFrame.getUI();

		Component north = ui.getNorthPane();
		MouseMotionListener[] actions =
		(MouseMotionListener[])north.getListeners(MouseMotionListener.class);

		for (int i = 0; i < actions.length; i++)
		north.removeMouseMotionListener( actions[i] );
		
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
	
		Dimension jInternalFrameSize = internalFrame.getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		internalFrame.setLocation(width, height);
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		toolBar.setBounds(10, 11, 414, 50);
		toolBar.setFloatable(false);
		internalFrame.getContentPane().add(toolBar);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(Modality.class.getResource("/img22/localizar.png")));
		btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSearch.setMaximumSize(new Dimension(100, 40));
		toolBar.add(btnSearch);
		
		
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(Modality.class.getResource("/img22/adicionar.png")));
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdd.setMaximumSize(new Dimension(100, 40));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setIcon(new ImageIcon(Modality.class.getResource("/img22/remover.png")));
		btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRemove.setMaximumSize(new Dimension(100, 40));
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(Modality.class.getResource("/img22/salvar.png")));
		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSave.setMaximumSize(new Dimension(100, 40));
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
