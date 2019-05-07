package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.combatmanager.database.dao.StudentDAO;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.database.model.Student;
import com.combatmanager.error.AccessException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.combatmanager.security.Configuration;

public class ChooseStudentWindow extends JFrame implements View {
	private JTextField textFieldName;
	private JTable table;
	private final String NAME = "Escolher Aluno";
	private final int ACESS = 7*11;
	private Configuration config;
	private Student selectedStudent;
	private List<Object> save_students;
	JButton btnSelect;
	JButton btnSearch;
	
	/**
	 * Create the panel.
	 */
	public ChooseStudentWindow(Configuration config) {
		save_students =null;
		setBounds(0, 0, 450, 403);
		setLayout(null);
		this.config = config;
		JInternalFrame internalFrame = new JInternalFrame("Escolher Aluno");
		internalFrame.setClosable(true);
		internalFrame.setBounds(0, 0, 450, 300);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(72, 12, 230, 20);
		internalFrame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblName = new JLabel("Nome:");
		lblName.setFont(new Font("Dialog", Font.BOLD, 12));
		lblName.setBounds(12, 14, 55, 16);
		internalFrame.getContentPane().add(lblName);
		
		btnSearch = new JButton("Procurar");
		btnSearch.setBounds(314, 9, 114, 26);
		internalFrame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 416, 174);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Codigo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setMinWidth(10);
		scrollPane.setViewportView(table);
		
		btnSelect = new JButton("Selecionar");
		btnSelect.setBounds(12, 229, 416, 26);
		internalFrame.getContentPane().add(btnSelect);
		internalFrame.setVisible(true);
		
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textFieldName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor digitar nome do aluno");
				}
				StudentDAO studentDao;
				try {
					studentDao = new StudentDAO(config.getConnection());
					Student aux_student = new Student();
					aux_student.setName(textFieldName.getText());
					Student st = (Student) studentDao.Select(aux_student);
					if(!st.getName().equals("")) {
						addMMToTable(st);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (AccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnSelect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(table.getSelectedRow() == -1) {
                	JOptionPane.showMessageDialog(null, "Favor escolher aluno");
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                selectedStudent = new Student();
                selectedStudent.setIndex((int) model.getValueAt(table.getSelectedRow(), 1));
                selectedStudent.setName((String) model.getValueAt(table.getSelectedRow(), 0));
                
                dispatchEvent(new WindowEvent(getFrames()[0], WindowEvent.WINDOW_CLOSING));
            }
        });
	}
	
	public Student getStudentId() {
		return selectedStudent;
	}
	
	@Override
	public int getAccess() {
		return ACESS;
	}
	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public JPanel run(Configuration config) {
		this.config = config;
		return null;
	}
	
	public void addMMToTable(Student ss) {
		if(ss == null) {
			return;
		}
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] {ss.getName(),ss.getIndex()});
		table.setModel(model);
	}
}
