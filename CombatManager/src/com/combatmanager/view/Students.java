package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import java.awt.ScrollPane;
import javax.swing.JSlider;
import javax.swing.JTree;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class Students extends JPanel implements View {
	private JTextField textFieldStudent;
	private JTextField textFieldEmail;
	private JTextField textFieldObs;
	private JTextField textFieldPhone;
	private JTextField textFieldCellPhone;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtTeclarF;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_4;

	
	private final String NAME = "Tela Estudantes";
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
		contentPane.setLayout(null);
		JInternalFrame internalFrame = new JInternalFrame("Cadastro de alunos");0
		internalFrame.setBounds(0, 0, 546, 520);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 424, 39);
		internalFrame.getContentPane().add(toolBar);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSearch.setMaximumSize(new Dimension(65, 80));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar.add(btnSearch);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		btnAdd.setMaximumSize(new Dimension(65, 80));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setMaximumSize(new Dimension(65, 80));
		btnRemove.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setMaximumSize(new Dimension(65, 80));
		btnSave.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		toolBar.add(btnSave);
		
		JLabel lblStudent = new JLabel("Aluno:");
		lblStudent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudent.setBounds(10, 63, 46, 14);
		internalFrame.getContentPane().add(lblStudent);
		
		textFieldStudent = new JTextField();
		textFieldStudent.setBounds(143, 61, 377, 20);
		internalFrame.getContentPane().add(textFieldStudent);
		textFieldStudent.setColumns(10);
		
		//MaskFormatter maskFormatter = new MaskFormatter("##/##/##");
	
		
		JFormattedTextField formattedTextFieldDate = new JFormattedTextField();
		//formattedTextFieldDate.setFormatterFactory(tf);
		formattedTextFieldDate.setLocation(143, 92);
		formattedTextFieldDate.setSize(new Dimension(148, 20));

		internalFrame.getContentPane().add(formattedTextFieldDate);
		
		JComboBox comboBoxSex = new JComboBox();
		comboBoxSex.setEnabled(false);
		comboBoxSex.setBounds(372, 92, 148, 20);
		internalFrame.getContentPane().add(comboBoxSex);
		
		JLabel lblBirthDate = new JLabel("Data de nascimento:");
		lblBirthDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBirthDate.setBounds(10, 94, 153, 14);
		internalFrame.getContentPane().add(lblBirthDate);
		
		JLabel lblSex = new JLabel("Sexo:");
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSex.setBounds(301, 94, 61, 14);
		internalFrame.getContentPane().add(lblSex);
		
		JLabel lblPhone = new JLabel("Telefone:");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhone.setBounds(10, 125, 153, 14);
		internalFrame.getContentPane().add(lblPhone);
		
		JLabel lblCellPhone = new JLabel("Celular:");
		lblCellPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCellPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCellPhone.setBounds(301, 125, 61, 14);
		internalFrame.getContentPane().add(lblCellPhone);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(143, 154, 377, 20);
		internalFrame.getContentPane().add(textFieldEmail);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 157, 153, 14);
		internalFrame.getContentPane().add(lblEmail);
		
		JLabel lblObs = new JLabel("Observa\u00E7\u00F5es:");
		lblObs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblObs.setBounds(10, 185, 153, 14);
		internalFrame.getContentPane().add(lblObs);
		
		textFieldObs = new JTextField();
		textFieldObs.setBounds(10, 210, 510, 59);
		internalFrame.getContentPane().add(textFieldObs);
		textFieldObs.setColumns(10);
		
		JTabbedPane tabbedPaneAddress = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneAddress.setBounds(10, 280, 510, 195);
		internalFrame.getContentPane().add(tabbedPaneAddress);
		internalFrame.setVisible(true);

		JPanel addressPanel = new JPanel();
		tabbedPaneAddress.addTab("Endereço", null, addressPanel, null);
		addressPanel.setLayout(null);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 11, 68, 15);
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 12));
		addressPanel.add(lblEndereo);
		
		textField = new JTextField();
		textField.setBounds(109, 9, 145, 20);
		addressPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblComplemento = new JLabel("Complemento: ");
		lblComplemento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblComplemento.setBounds(10, 42, 93, 15);
		addressPanel.add(lblComplemento);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(109, 40, 386, 20);
		addressPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(350, 9, 145, 20);
		addressPanel.add(textField_2);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefone.setBounds(272, 12, 68, 15);
		addressPanel.add(lblTelefone);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(109, 71, 145, 20);
		addressPanel.add(textField_3);
		
		txtTeclarF = new JTextField();
		txtTeclarF.setEnabled(false);
		txtTeclarF.setBackground(Color.YELLOW);
		txtTeclarF.setText("Teclar F9");
		txtTeclarF.setColumns(10);
		txtTeclarF.setBounds(350, 71, 145, 20);
		addressPanel.add(txtTeclarF);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setColumns(10);
		textField_5.setBounds(109, 102, 145, 20);
		addressPanel.add(textField_5);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		textField_4.setBounds(350, 102, 145, 20);
		addressPanel.add(textField_4);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCidade.setBounds(272, 74, 68, 15);
		addressPanel.add(lblCidade);
		
		JLabel lblPas = new JLabel("Pais:");
		lblPas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPas.setBounds(272, 105, 68, 15);
		addressPanel.add(lblPas);
		
		JLabel lblBairro = new JLabel("Bairro: ");
		lblBairro.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBairro.setBounds(10, 74, 93, 15);
		addressPanel.add(lblBairro);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setBounds(10, 105, 93, 15);
		addressPanel.add(lblEstado);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(109, 133, 145, 20);
		addressPanel.add(textField_7);
		
		JLabel lblCep = new JLabel("Cep:");
		lblCep.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCep.setBounds(10, 136, 93, 15);
		addressPanel.add(lblCep);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(143, 123, 148, 20);
		internalFrame.getContentPane().add(textFieldPhone);
		
		textFieldCellPhone = new JTextField();
		textFieldCellPhone.setColumns(10);
		textFieldCellPhone.setBounds(372, 123, 148, 20);
		internalFrame.getContentPane().add(textFieldCellPhone);
		
		return contentPane;
	}
}
