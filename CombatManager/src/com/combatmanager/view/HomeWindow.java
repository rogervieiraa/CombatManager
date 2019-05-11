package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.combatmanager.database.dao.AttendanceDAO;
import com.combatmanager.database.dao.MatriculationDAO;
import com.combatmanager.database.dao.MatriculationInvoicesDAO;
import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.dao.StudentDAO;
import com.combatmanager.database.model.Attendance;
import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationInvoices;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.database.model.Student;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;
import com.combatmanager.util.MasterMonthChooser;

import controller.CombatImage;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.sound.midi.Soundbank;
import javax.swing.ImageIcon;

public class HomeWindow extends JInternalFrame implements View {
	private JTable tableLeftUpBox;
	private JTextField textFieldName;
	private JTable tableStudentInfo;
	private JTextField textFieldCenterData;
	private JScrollPane scrollPaneEnrollment;
	private JTable tableEnrollmentInfo;
	private JScrollPane scrollPaneAssiduity;
	private JTable tableAssiduity;
	private MasterMonthChooser data;
	private JTextField textFieldNameSearch;
	
	private JButton btnStudentsInfo;
	private JButton btnEnrollmentInfo;
	private Student saved_student;
	private Configuration config;
	List<Matriculation> student_matriculations;
	private int saved_year;
	private int saved_mounth;
	
	private final String NAME = "Tela Controle Estudantes";
	private final int ACCESS = 3*5*7*11;
	
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
		this.config = config;
		
		setBounds(0, 0, 665, 515);
		setTitle("Controle Aluno");
		setFrameIcon(CombatImage.combatvinte_20x20);		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
		Dimension jInternalFrameSize = getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		setLocation(width, height);
		
		tableLeftUpBox = new JTable();
		tableLeftUpBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tableLeftUpBox.setBackground(UIManager.getColor("Button.background"));
		tableLeftUpBox.setBounds(12, 12, 178, 197);
		add(tableLeftUpBox);
		
		textFieldNameSearch = new JTextField();
		textFieldNameSearch.setBounds(217, 18, 90, 20);

		add(textFieldNameSearch);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldName.setEditable(false);
		textFieldName.setBounds(319, 18, 324, 20);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		JScrollPane scrollPaneStudentInfo = new JScrollPane();
		scrollPaneStudentInfo.setBounds(217, 50, 426, 104);
		add(scrollPaneStudentInfo);
		
		data = new MasterMonthChooser();
		data.setBounds(10, 230,  185, 26);
		add(data);
		
		textFieldCenterData = new JTextField();
		textFieldCenterData.setEditable(false);
		textFieldCenterData.setForeground(Color.WHITE);
		textFieldCenterData.setFont(new Font("Dialog", Font.BOLD, 18));
		textFieldCenterData.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCenterData.setText("D\u00E9bitos Pendentes");
		textFieldCenterData.setBackground(Color.RED);
		textFieldCenterData.setBounds(218, 166, 425, 43);
		add(textFieldCenterData);
		textFieldCenterData.setColumns(10);
		
		btnStudentsInfo = new JButton("Acessar dados do Aluno");
		
		btnStudentsInfo.setBounds(217, 224, 203, 26);
		add(btnStudentsInfo);
		
		btnEnrollmentInfo = new JButton("Acessar dados da Matr\u00EDcula");
		btnEnrollmentInfo.setBounds(440, 224, 203, 26);
		add(btnEnrollmentInfo);
		
		
		
		
		
		tableStudentInfo = new JTable();
		tableStudentInfo.setColumnSelectionAllowed(true);
		tableStudentInfo.setCellSelectionEnabled(true);
		scrollPaneStudentInfo.setViewportView(tableStudentInfo);
		tableStudentInfo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Modalidade", "Gradua\u00E7\u00E3o", "Plano", "Data In\u00EDcio", "Data Fim"
			}
		) {
			boolean[] columnEditables = new boolean[] {
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableStudentInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tableStudentInfo.setBackground(new Color(255, 255, 255));
		
		scrollPaneAssiduity = new JScrollPane();
		scrollPaneAssiduity.setBounds(12, 262, 178, 208);
		add(scrollPaneAssiduity);
		
		tableAssiduity = new JTable();
		tableAssiduity.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Assiduidade"
			}
		));
		scrollPaneAssiduity.setViewportView(tableAssiduity);
		
		scrollPaneEnrollment = new JScrollPane();
		scrollPaneEnrollment.setBounds(217, 262, 426, 208);
		add(scrollPaneEnrollment);
		
		tableEnrollmentInfo = new JTable();
		tableEnrollmentInfo.setEnabled(false);
		tableEnrollmentInfo.setModel(new DefaultTableModel(
			new Object[][] {
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
		
		textFieldNameSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = -1;
				try {
					index = Integer.parseInt(textFieldNameSearch.getText());
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Apenas numeros inteiros permitidos.");
					return;
				}
				
				
				Student aux_student = new Student();
				aux_student.setIndex(index);
				
				StudentDAO studentDao;
				AttendanceDAO attendanceDao;
				
				try {
					resetWindow();
					
					studentDao = new StudentDAO(config.getConnection());
					
					
					saved_student = (Student) studentDao.SelectById(aux_student);
					if(saved_student == null) {
						throw new SQLException("Nenhum estudante encontrado");
					}
					textFieldName.setText(saved_student.getName());
					
					addTables();

					btnStudentsInfo.setEnabled(true);
					
					if (student_matriculations.size() > 0) {
						btnEnrollmentInfo.setEnabled(true);
					}
					
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					resetWindow();
				} catch (AccessException e) {
					e.printStackTrace();
					resetWindow();
				}
				
			}
		});
		
		btnStudentsInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentsWindow sw = new StudentsWindow();
				
				setContentPane(sw.run(config));
				Student st = new Student();
				
				sw.setStudent(saved_student);
			}
		});
		
		btnEnrollmentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterStudentWindow rsw = new RegisterStudentWindow();
				
				setContentPane(rsw.run(config));
				rsw.setMatriculation(student_matriculations.get(0));
			}
		});
		

		setVisible(true);
		
		resetWindow();

		return this;
	}
	
	private void resetWindow() {
		saved_student = null;
		student_matriculations = null;
		textFieldCenterData.setText("Aguardando Consulta");
		textFieldCenterData.setBackground(new Color(230,232,250));
		btnEnrollmentInfo.setEnabled(false);
		btnStudentsInfo.setEnabled(false);
		
		textFieldName.setText("");
		textFieldNameSearch.setText("");
		textFieldNameSearch.setEnabled(true);
		
		/* reset table */
		DefaultTableModel model1 = (DefaultTableModel) tableAssiduity.getModel();
		while(model1.getRowCount() > 0) {
			model1.removeRow(0);
		}
		tableAssiduity.setModel(model1);
		
		DefaultTableModel model2 = (DefaultTableModel) tableEnrollmentInfo.getModel();
		while(model2.getRowCount() > 0) {
			model2.removeRow(0);
		}
		tableEnrollmentInfo.setModel(model2);
		
		DefaultTableModel model3 = (DefaultTableModel) tableStudentInfo.getModel();
		while(model3.getRowCount() > 0) {
			model3.removeRow(0);
		}
		tableStudentInfo.setModel(model3);
		
		tableAssiduity.setEnabled(false);
		tableEnrollmentInfo.setEnabled(false);
		tableStudentInfo.setEnabled(false);
	}
	
	public void startThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					
					while (!Thread.currentThread().isInterrupted()) {

						if(saved_mounth != data.getDate().getMonth() && 
								saved_year != data.getDate().getMonth()) {
							genereteAttendance();
						}
						
						if(saved_student == null) {
							System.out.println("parou thread");
							break;
						}
						
						Thread.sleep(2000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void addTables() {
		
		MatriculationDAO matriculationDao = null;
		MatriculationModalityDAO matriculationModalityDao = null;
		MatriculationInvoicesDAO matriculationInvoicesDao = null;
		
		try {
			matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
			matriculationDao = new MatriculationDAO(config.getConnection());
			matriculationInvoicesDao = new MatriculationInvoicesDAO(config.getConnection());
			
			student_matriculations = matriculationDao.SelectAllMatriculationByStudent(saved_student);
			AttendanceDAO attendanceDao;
			if(student_matriculations != null) {
				System.out.println(student_matriculations);
				for(int i=0;i<student_matriculations.size();i++) {
					Matriculation aux_matriculation = student_matriculations.get(i);
					
					
					
					/* matriculation modality */
					List<MatriculationModality> save_matriculationModality = matriculationModalityDao.SelectGraduationByMatriculation(aux_matriculation);
					for(int j=0;j<save_matriculationModality.size();j++) {
						addMMToTable(save_matriculationModality.get(j));
					}
					
					/* invoices */
					List<MatriculationInvoices> save_matriculationInvoice = matriculationInvoicesDao.SelectAllByMatriculation(aux_matriculation);
					boolean hasDebit = false;
					for(int j=0;j<save_matriculationInvoice.size();j++) {
						MatriculationInvoices mi = save_matriculationInvoice.get(j);
						if(comperDate(mi.getPay_date(),mi.getDue_date(),mi.getCancel_date())) {
							hasDebit = true;
						}
						addInvoiceToTable(mi);
					}
					
					changeMainFrame(hasDebit);
					
					Attendance aux_att = new Attendance();
					attendanceDao = new AttendanceDAO(config.getConnection());
					aux_att.setMatriculation_code(aux_matriculation.getCode());
					attendanceDao.Insert(aux_att);
					
					/* assuidade */
					genereteAttendance();
				}
			}
			else {
				System.out.println("null??");
			}
			
			startThread();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void changeMainFrame(boolean hasDebit) {
		if(hasDebit) {
			textFieldCenterData.setForeground(Color.RED);
			textFieldCenterData.setText("Possui debitos!");
		}else {
			textFieldCenterData.setForeground(Color.GREEN);
			textFieldCenterData.setText("Sem restricoes");
		}
		
	}

	private boolean comperDate(String pay_date, String due_date, String cancel_date) {
		if(cancel_date != null && !cancel_date.equals("")) {
			
			return false;
		}
		if(pay_date != null && !pay_date.equals("")) {
			
			return false;
		}
		
		LocalDate l = LocalDate.now();
		int y = l.getYear();
		int m = l.getMonthValue();
		int d = l.getDayOfMonth();
		int y2 = Integer.parseInt(due_date.substring(0, 4));
		int m2 = Integer.parseInt(due_date.substring(5, 7));
		int d2 = Integer.parseInt(due_date.substring(8, 9));

		if(y > y2) {
			return true;
		}
		if(y == y2 && m > m2) {
			return true;
		}
		if(y == y2 && m == m2 && d > d2) {
			return true;
		}
		
		
		return false;
	}

	public void addMMToTable(MatriculationModality mm) {
		if(mm == null) {
			return;
		}

		DefaultTableModel model11 = (DefaultTableModel) tableStudentInfo.getModel();
		model11.addRow(new Object[] {mm.getModality(),mm.getGraduation(),mm.getPlan(),mm.getBegin_date(),mm.getEnd_date()});
		tableStudentInfo.setModel(model11);
	}
	
	public void addAttendanceToTable(Attendance mm) {
		if(mm == null) {
			return;
		}

		DefaultTableModel model12 = (DefaultTableModel) tableAssiduity.getModel();
		model12.addRow(new Object[] {mm.getEntry_date()});
		tableAssiduity.setModel(model12);

	}
	
	public void genereteAttendance() {
		DefaultTableModel model12 = (DefaultTableModel) tableAssiduity.getModel();
		if(student_matriculations.size() == 0) {
			return;
		}
		Matriculation m = student_matriculations.get(0);
		
		Date dd = data.getDate();
		saved_year = dd.getYear();
		saved_mounth = dd.getMonth();
		dd.setYear(dd.getYear() + 1900);
		dd.setMonth(dd.getMonth() + 1);
		AttendanceDAO attendanceDao = null;
		
		try {
			attendanceDao = new AttendanceDAO(config.getConnection());
			String data1 = Integer.toString(dd.getYear()) + "-" +
					Integer.toString(dd.getMonth()) + "-01";
			System.out.println(data1);
			String data2;
			if(dd.getMonth() == 12) {
				data2 = Integer.toString(dd.getYear() + 1) + "-" +
					Integer.toString(dd.getMonth() - 11) + "-01";
			}
			else {
				data2 = Integer.toString(dd.getYear()) + "-" +
						Integer.toString(dd.getMonth() + 1) + "-01";
			}

			List<Attendance> list_att = attendanceDao.SelectAllByDate(data1, data2);
			
			while(model12.getRowCount() > 0) {
				model12.removeRow(0);
			}
			for(int j=0;j<list_att.size();j++) {
				addAttendanceToTable(list_att.get(j));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addInvoiceToTable(MatriculationInvoices mm) {
		if(mm == null) {
			return;
		}
		DefaultTableModel model12 = (DefaultTableModel) tableEnrollmentInfo.getModel();
		model12.addRow(new Object[] {mm.getDue_date(),mm.getValue(),mm.getPay_date(),mm.getCancel_date()});
		tableEnrollmentInfo.setModel(model12);

	}
	
}
