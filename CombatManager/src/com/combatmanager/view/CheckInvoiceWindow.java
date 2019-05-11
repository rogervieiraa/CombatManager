package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.combatmanager.database.dao.MatriculationDAO;
import com.combatmanager.database.dao.MatriculationInvoicesDAO;
import com.combatmanager.database.dao.StudentDAO;
import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationInvoices;
import com.combatmanager.database.model.Student;
import com.combatmanager.security.Configuration;
import com.combatmanager.util.TableRenderer;

import controller.CombatImage;

import javax.swing.ImageIcon;

public class CheckInvoiceWindow extends JInternalFrame implements View {
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private JButton btnSearch;
	private JTable table;
	private Boolean isAll = false;
	private MatriculationInvoices miG;
	private List<MatriculationInvoices> listMi;
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Matricula", "Aluno", "Vencimento", "Valor", "Pagamento", "Cancelamento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		

	private final String NAME = "Tela Consultar Fatura";
	private final int ACCESS = 7*11;

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
	public JInternalFrame run(Configuration config) {
		setLayout(null);
		
		setResizable(true);
		setTitle("Consultar Faturas");
		setClosable(true);
		setFrameIcon(CombatImage.combatvinte_20x20);
		setBounds(0, 0, 600, 575);

		getContentPane().setLayout(null);
		
		JLabel lblDe = new JLabel("De: ");
		lblDe.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDe.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDe);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setBounds(35, 9, 86, 20);
		getContentPane().add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		JLabel lblTo = new JLabel("At\u00E9: ");
		lblTo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTo.setBounds(133, 12, 46, 14);
		getContentPane().add(lblTo);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(163, 9, 86, 20);
		getContentPane().add(textFieldTo);
		
		JLabel lblStatus = new JLabel("Situa\u00E7\u00E3o: ");
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStatus.setBounds(259, 12, 68, 14);
		getContentPane().add(lblStatus);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Em aberto", "Pagas", "Canceladas"}));
		comboBox.setBounds(325, 9, 100, 20);
		getContentPane().add(comboBox);
		
		btnSearch = new JButton("Pesquisar");
		btnSearch.setBounds(435, 4, 145, 31);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				while(model.getRowCount() > 0) {
					model.removeRow(0);
				}
				
				MatriculationInvoices mi = new MatriculationInvoices();
				Matriculation mat = new Matriculation();
				Student student = new Student();
				MatriculationDAO matDao;
				StudentDAO studentDao;
				MatriculationInvoicesDAO miDao;
				
				if (!textFieldFrom.getText().equals("") && (!textFieldTo.getText().equals(""))) {
					if (comboBox.getSelectedIndex() != -1) {
						try {
							miDao = new MatriculationInvoicesDAO(config.getConnection());
							
							listMi = miDao.SelectFilter(textFieldFrom.getText(), textFieldTo.getText(), comboBox.getSelectedItem().toString());
							
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							e1.printStackTrace();
						}
					}
				}else if(comboBox.getSelectedIndex() != -1) {
					try {
						miDao = new MatriculationInvoicesDAO(config.getConnection());
						
						listMi = miDao.SelectAlll(comboBox.getSelectedItem().toString());
						
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						e1.printStackTrace();
					}
				}else {
					isAll = true;
				}
				try {
					matDao = new MatriculationDAO(config.getConnection());
					studentDao = new StudentDAO(config.getConnection());
					
					for(int i=0;i<listMi.size();i++) {
						mat.setCode(listMi.get(i).getMatriculation_code());
						mat = (Matriculation) matDao.Select(mat);
						miG = listMi.get(i);
						student.setIndex(mat.getStudent_code());
						student = (Student) studentDao.SelectById(student);
						
						model.addRow(new Object[] {listMi.get(i).getMatriculation_code(), student.getName(), listMi.get(i).getDue_date(), listMi.get(i).getValue(), listMi.get(i).getPay_date(), listMi.get(i).getCancel_date()});
						
						if (isAll) {
							
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 47, 558, 483);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.setDefaultRenderer(Object.class, new TableRenderer(config));
		scrollPane.setViewportView(table);
		setVisible(true);
		
		return this;
	}
	
	public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column)
	{

	 	if(miG.getPay_date().equals(""))
		{
		       setForeground(Color.GREEN);	
		}
		else if(miG.getCancel_date().equals(""))
		{
		       setForeground(Color.BLACK);		
		}
	 
	 	
		return this;   	
	}

}
