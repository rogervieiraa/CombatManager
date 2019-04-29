package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.combatmanager.security.Configuration;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class StudentControl extends JPanel implements View {
	private JTable tableLeftUpBox;
	private JTable tableColor;
	private JTextField textFieldName;
	private JTable tableStudentInfo;
	private JTextField txtChooser;
	private JScrollPane scrollPaneEnrollment;
	private JTable tableEnrollmentInfo;
	private JScrollPane scrollPaneAssiduity;
	private JTable tableAssiduity;

	private final String NAME = "Tela Controle Estudantes";
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
	 * Create the panel.
	 */
	public JPanel run(Configuration config) {
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Controle de Alunos");
		internalFrame.setFrameIcon(new ImageIcon(StudentControl.class.getResource("/img/combatvinte.png")));
		internalFrame.getContentPane().setEnabled(false);
		internalFrame.setBounds(0, 0, 665, 515);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		tableLeftUpBox = new JTable();
		tableLeftUpBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tableLeftUpBox.setBackground(UIManager.getColor("Button.background"));
		tableLeftUpBox.setBounds(12, 12, 178, 197);
		internalFrame.getContentPane().add(tableLeftUpBox);
		
		tableColor = new JTable();
		tableColor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tableColor.setBounds(217, 18, 90, 20);
		internalFrame.getContentPane().add(tableColor);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldName.setEditable(false);
		textFieldName.setBounds(319, 18, 324, 20);
		internalFrame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JScrollPane scrollPaneStudentInfo = new JScrollPane();
		scrollPaneStudentInfo.setBounds(217, 50, 426, 104);
		internalFrame.getContentPane().add(scrollPaneStudentInfo);
		
		tableStudentInfo = new JTable();
		tableStudentInfo.setColumnSelectionAllowed(true);
		tableStudentInfo.setCellSelectionEnabled(true);
		scrollPaneStudentInfo.setViewportView(tableStudentInfo);
		tableStudentInfo.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Modalidade", "Gradua\u00E7\u00E3o", "Plano", "Data In\u00EDcio", "Data Fim"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableStudentInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tableStudentInfo.setBackground(new Color(255, 255, 255));
		
		txtChooser = new JTextField();
		txtChooser.setEditable(false);
		txtChooser.setForeground(Color.WHITE);
		txtChooser.setFont(new Font("Dialog", Font.BOLD, 18));
		txtChooser.setHorizontalAlignment(SwingConstants.CENTER);
		txtChooser.setText("D\u00E9bitos Pendentes");
		txtChooser.setBackground(Color.RED);
		txtChooser.setBounds(218, 166, 425, 43);
		internalFrame.getContentPane().add(txtChooser);
		txtChooser.setColumns(10);
		
		JButton btnStudentsInfo = new JButton("Acessar dados do Aluno");
		btnStudentsInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStudentsInfo.setBounds(217, 224, 203, 26);
		internalFrame.getContentPane().add(btnStudentsInfo);
		
		JButton btnEnrollmentInfo = new JButton("Acessar dados da Matr\u00EDcula");
		btnEnrollmentInfo.setBounds(440, 224, 203, 26);
		internalFrame.getContentPane().add(btnEnrollmentInfo);
		
		scrollPaneEnrollment = new JScrollPane();
		scrollPaneEnrollment.setBounds(217, 262, 426, 208);
		internalFrame.getContentPane().add(scrollPaneEnrollment);
		
		tableEnrollmentInfo = new JTable();
		tableEnrollmentInfo.setEnabled(false);
		tableEnrollmentInfo.setBackground(Color.RED);
		tableEnrollmentInfo.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Vencimento", "Valor", "Pagamento", "Cancelamento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneEnrollment.setViewportView(tableEnrollmentInfo);
		
		scrollPaneAssiduity = new JScrollPane();
		scrollPaneAssiduity.setBounds(12, 262, 178, 208);
		internalFrame.getContentPane().add(scrollPaneAssiduity);
		
		tableAssiduity = new JTable();
		tableAssiduity.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Assiduidade"
			}
		));
		scrollPaneAssiduity.setViewportView(tableAssiduity);
		internalFrame.setVisible(true);
		return contentPane;
	}
	
}
