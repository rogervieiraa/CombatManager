package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class registerStudent extends JPanel {
	private JTextField textFieldRegistration;
	private JTextField textFieldF9;
	private JTextField textFieldStudent;
	private JTextField textFieldRegisterDay;
	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public registerStudent() {
		setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Matricular Aluno");
		internalFrame.setFrameIcon(new ImageIcon(registerStudent.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 526, 396);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 415, 39);
		toolBar.setFloatable(false);
		internalFrame.getContentPane().add(toolBar);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(Modality.class.getResource("/img22/localizar.png")));
	
		btnSearch.setMaximumSize(new Dimension(98, 80));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar.add(btnSearch);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(Modality.class.getResource("/img22/adicionar.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		btnAdd.setMaximumSize(new Dimension(98, 80));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setIcon(new ImageIcon(Modality.class.getResource("/img22/remover.png")));
		btnRemove.setMaximumSize(new Dimension(98, 80));
		
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(Modality.class.getResource("/img22/salvar.png")));
		btnSave.setMaximumSize(new Dimension(98, 80));
	
		toolBar.add(btnSave);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula: ");
		lblMatrcula.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMatrcula.setBounds(10, 62, 84, 22);
		internalFrame.getContentPane().add(lblMatrcula);
		
		textFieldRegistration = new JTextField();
		textFieldRegistration.setBounds(143, 61, 84, 19);
		internalFrame.getContentPane().add(textFieldRegistration);
		textFieldRegistration.setColumns(10);
		
		JLabel lblAluno = new JLabel("Aluno: ");
		lblAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAluno.setBounds(10, 94, 84, 22);
		internalFrame.getContentPane().add(lblAluno);
		
		textFieldF9 = new JTextField();
		textFieldF9.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldF9.setBackground(Color.YELLOW);
		textFieldF9.setText("Teclar F9");
		textFieldF9.setForeground(Color.BLACK);
		textFieldF9.setColumns(10);
		textFieldF9.setBounds(143, 97, 84, 19);
		internalFrame.getContentPane().add(textFieldF9);
		
		textFieldStudent = new JTextField();
		textFieldStudent.setEnabled(false);
		textFieldStudent.setColumns(10);
		textFieldStudent.setBounds(237, 97, 263, 19);
		internalFrame.getContentPane().add(textFieldStudent);
		
		textFieldRegisterDay = new JTextField();
		textFieldRegisterDay.setColumns(10);
		textFieldRegisterDay.setBounds(143, 127, 84, 19);
		internalFrame.getContentPane().add(textFieldRegisterDay);
		
		JLabel lblRegisterDay = new JLabel("Data da matr\u00EDcula: ");
		lblRegisterDay.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRegisterDay.setBounds(10, 124, 116, 22);
		internalFrame.getContentPane().add(lblRegisterDay);
		
		JLabel lblDiaDoVencimento = new JLabel("Dia do vencimento da fatura: ");
		lblDiaDoVencimento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiaDoVencimento.setBounds(237, 124, 183, 22);
		internalFrame.getContentPane().add(lblDiaDoVencimento);
		
		textField = new JTextField();
		textField.setBounds(422, 127, 78, 20);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAdicionarModalidade = new JButton("Adicionar Modalidade");
		btnAdicionarModalidade.setBounds(10, 157, 217, 23);
		internalFrame.getContentPane().add(btnAdicionarModalidade);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 490, 160);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Modalidade", "Gradua\u00E7\u00E3o", "Plano", "Data In\u00EDcio", "Data Fim"
			}
		));
		scrollPane.setViewportView(table);
	}
}
