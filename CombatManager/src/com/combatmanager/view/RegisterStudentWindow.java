package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.combatmanager.database.dao.AttendanceDAO;
import com.combatmanager.database.dao.GraduationDAO;
import com.combatmanager.database.dao.MatriculationDAO;
import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.dao.ModalityDAO;
import com.combatmanager.database.dao.StudentDAO;
import com.combatmanager.database.model.Graduation;
import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.database.model.Modality;
import com.combatmanager.database.model.Student;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

public class RegisterStudentWindow extends JPanel implements View{
	private JTextField textFieldRegistration;
	private JTextField textFieldF9;
	private JTextField textFieldStudent;
	private JTextField textFieldRegisterDay;
	private JTextField textFieldFinishDay;
	private JTable table;
	private JButton btnSave;
	private JButton btnSearch;
	private JButton btnRemove;
	private JButton btnAdd;
	private JButton btnAdicionarModalidade;
	private Boolean search;
	private Matriculation save_matriculation;
	private List<MatriculationModality> save_matriculationModality;
	private List<MatriculationModality> newers_mm;
	private final String NAME = "Tela Cadastro Alunos";
	private final int ACCESS = 5*11;
	private Boolean waiting;

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
	public JPanel run(Configuration config) {
		newers_mm = new ArrayList<MatriculationModality>();
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
		JInternalFrame internalFrame = new JInternalFrame("Matricular Aluno");
		internalFrame.setFrameIcon(new ImageIcon(RegisterStudentWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 526, 396);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);
		waiting = false;
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
		Dimension jInternalFrameSize = internalFrame.getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		
		internalFrame.setLocation(width, height);
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 415, 39);
		toolBar.setFloatable(false);
		internalFrame.getContentPane().add(toolBar);
		
		btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/localizar.png")));
	
		btnSearch.setMaximumSize(new Dimension(98, 80));
		toolBar.add(btnSearch);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/adicionar.png")));
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		btnAdd.setMaximumSize(new Dimension(98, 80));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		btnRemove = new JButton("Remover");
		btnRemove.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/remover.png")));
		btnRemove.setMaximumSize(new Dimension(98, 80));
		
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/salvar.png")));
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
		
		textFieldFinishDay = new JTextField();
		textFieldFinishDay.setBounds(422, 127, 78, 20);
		internalFrame.getContentPane().add(textFieldFinishDay);
		textFieldFinishDay.setColumns(10);
		
		btnAdicionarModalidade = new JButton("Adicionar Modalidade");
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
		
		
		resetWindow();

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!search) {
					textFieldRegistration.setEnabled(true);
					
					btnAdd.setEnabled(false);
					search = true;
					config.addToSystemLog(getName()+","+"Iniciou a operacao de busca");
					return;
				}
				MatriculationDAO matriculationDao = null;
				MatriculationModalityDAO matriculationModalityDao = null;
				StudentDAO studentDao = null;
				//save
				save_matriculation = new Matriculation();
				save_matriculation.setCode(Integer.parseInt(textFieldRegistration.getText()));
				try {
					
					matriculationDao = new MatriculationDAO(config.getConnection());
					studentDao = new StudentDAO(config.getConnection());
					Matriculation auxiliar_matriculation = (Matriculation) matriculationDao.Select(save_matriculation);
					if(auxiliar_matriculation == null) {
						JOptionPane.showMessageDialog(null, "Matricula nao encontrada.");
						config.addToSystemLog(getName()+","+"Matricula nao encontrada.");
						resetWindow();
						return;
					}
					save_matriculation = auxiliar_matriculation;
					textFieldRegisterDay.setText(save_matriculation.getMatriculation_date());
					textFieldFinishDay.setText(Integer.toString(save_matriculation.getDue_date()));
					Student auxiliar_student = new Student();
					auxiliar_student.setIndex(save_matriculation.getStudent_code());
					Student student = (Student) studentDao.SelectById(auxiliar_student);
					textFieldStudent.setText(student.getName());
					textFieldRegistration.setText(Integer.toString(save_matriculation.getCode()));
					
					matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
					save_matriculationModality = matriculationModalityDao.SelectGraduationByMatriculation(save_matriculation);
					for(int i=0;i<save_matriculationModality.size();i++) {
						addMMToTable(save_matriculationModality.get(i));
					}
					
					config.addToSystemLog(getName()+","+"Busca realizada com sucesso"+","+auxiliar_matriculation.toString());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "erro em buscar.");
					config.addToSystemLog(getName()+","+"Erro em buscar");
					e1.printStackTrace();
					resetWindow();
				} catch (AccessException e1) {
					e1.showAcessWindowDenied();
					config.addToSystemLog(getName()+","+"Erro em buscar");
					resetWindow();
				}
				btnAdicionarModalidade.setEnabled(true);
				btnRemove.setEnabled(true);
				btnSave.setEnabled(true);
				btnSearch.setEnabled(false);
				textFieldF9.setEnabled(false);
				textFieldFinishDay.setEnabled(true);
				textFieldRegisterDay.setEnabled(true);
				textFieldStudent.setEnabled(false);
				textFieldRegistration.setEnabled(false);
				btnAdicionarModalidade.setEnabled(true);
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.addToSystemLog(getName()+","+"Iniciou adicao de nova modalidade");
				textFieldFinishDay.setEnabled(true);
				textFieldRegisterDay.setEnabled(true);
				textFieldStudent.setEnabled(false);
				textFieldF9.setEnabled(false);
				btnSave.setEnabled(true);
				btnSearch.setEnabled(false);
				btnAdd.setEnabled(false);
				btnRemove.setEnabled(false);
				btnAdicionarModalidade.setEnabled(true);
				
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				config.addToSystemLog(getName()+","+"Iniciou a opreacao de remocao de uma matricula");
				if (JOptionPane.showConfirmDialog(null, "Deletando essa matricula voce estara deletando todo o historico de pagamentos, concorda com isso?")
						== 0) {
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Operacao cancelada");
					config.addToSystemLog(getName()+","+"Cancelou a remocao da matricula");
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "Deletando essa matricula voce estara deletando todas as suas assuidades, concorda com isso?")
						== 0) {
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Operacao cancelada");
					config.addToSystemLog(getName()+","+"Cancelou a remocao da matricula");
					return;
				}
				
				MatriculationDAO matriculationDao = null;
				MatriculationModalityDAO matriculationModalityDao = null;
				AttendanceDAO attendenceDao = null;
				try {
					
					matriculationDao = new MatriculationDAO(config.getConnection());
					matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
					Matriculation auxiliar_matriculation = save_matriculation;
					System.out.println(auxiliar_matriculation.toString());
					matriculationModalityDao.DeleteByMatriculation(auxiliar_matriculation);
					matriculationDao.Delete(auxiliar_matriculation);
					config.addToSystemLog(getName()+","+"Deletou com sucesso"+","+auxiliar_matriculation.toString());
					
					
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Operacao cancelada, mediante a erro.");
					config.addToSystemLog(getName()+","+"Operacao cancelada, mediante a erro");
					e1.printStackTrace();
				} catch (AccessException e1) {
					System.out.println("BTN SEARCH REMOVE");
					config.addToSystemLog(getName()+","+"Erro em deletar");
					e1.printStackTrace();
				}
				resetWindow();
			}
		});
			
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				config.addToSystemLog(getName()+","+"Incio operacao de salvar");
				textFieldStudent.setText("roger");
				MatriculationModalityDAO matriculationModalityDao = null;
				MatriculationDAO matriculationDao = null;
				StudentDAO studentDao = null;
				if(textFieldStudent.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo de aluno");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}
				if(textFieldRegisterDay.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo data registro");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}
				
				if(textFieldFinishDay.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo de vencimento");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}
				//ALUNO INCORRETO
				// 0=yes, 1=no, 2=cancel
				int save_option = JOptionPane.showConfirmDialog(null, "Deseja salvar as alteracoes?");
				if (save_option == 1) {
					JOptionPane.showMessageDialog(null, "As alteracoes NAO foram salvas.");
					config.addToSystemLog(getName()+","+"Negou em salvar e descartou as alteracoes");
					resetWindow();
					return;
				}
				else if(save_option == 2) {
					JOptionPane.showMessageDialog(null, "Operacao de salvar cancelada.");
					config.addToSystemLog(getName()+","+"Cancelou a operacao de salvar");
					return;
				}
				if(search) {
					
					try {
						matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
						matriculationDao = new MatriculationDAO(config.getConnection());
						
						Matriculation auxiliar_matriculation = new Matriculation();
						auxiliar_matriculation.setCode(save_matriculation.getCode());
						auxiliar_matriculation.setStudent_code(save_matriculation.getStudent_code());
						auxiliar_matriculation.setClosing_date(save_matriculation.getClosing_date());
						auxiliar_matriculation.setMatriculation_date(textFieldRegisterDay.getText());
						auxiliar_matriculation.setDue_date(Integer.parseInt(textFieldFinishDay.getText()));
						matriculationDao.Update(save_matriculation, auxiliar_matriculation);
												
						for(int i=0;i<newers_mm.size();i++) {
							MatriculationModality aux_mm = newers_mm.get(i);
							aux_mm.setMatriculation_code(Integer.parseInt(textFieldRegistration.getText()));
							matriculationModalityDao.Insert(aux_mm);
						}

						
						config.addToSystemLog(getName()+","+"Salvou com sucesso");
						JOptionPane.showMessageDialog(null, "Operacao de salvar realizada com sucesso.");
						resetWindow();
					} catch (SQLException e1) {
						config.addToSystemLog(getName()+","+"Erro ao salvar");
						e1.printStackTrace();
					} catch (AccessException e1) {
						config.addToSystemLog(getName()+","+"Erro ao salvar");
						e1.printStackTrace();
					}
					
					return;
				}
				
				try {
					matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
					matriculationDao = new MatriculationDAO(config.getConnection());
					
					Matriculation auxiliar_matriculation = new Matriculation();
					
					studentDao = new StudentDAO(config.getConnection());
					Student auxiliar_student = new Student();
					
					auxiliar_student.setName(textFieldStudent.getText());
					
					auxiliar_student = (Student) studentDao.Select(auxiliar_student);
					auxiliar_matriculation.setStudent_code(auxiliar_student.getIndex());
					auxiliar_matriculation.setMatriculation_date(textFieldRegisterDay.getText());
					auxiliar_matriculation.setDue_date(Integer.parseInt(textFieldFinishDay.getText()));
					
					matriculationDao.Insert(auxiliar_matriculation);
					auxiliar_matriculation.setCode(matriculationDao.GetCode(auxiliar_matriculation));
					for(int i=0;i<newers_mm.size();i++) {
						MatriculationModality aux_mm = newers_mm.get(i);
						aux_mm.setMatriculation_code(auxiliar_matriculation.getCode());
						System.out.println(aux_mm.toString());
						matriculationModalityDao.Insert(aux_mm);
					}
					
					JOptionPane.showMessageDialog(null, "Operacao de salvar realizada com sucesso.");
					config.addToSystemLog(getName()+","+"Salvou/inserio com sucesso"+","+auxiliar_matriculation.toString());
					
				} catch (SQLException e1) {
					
					config.addToSystemLog(getName()+","+"Erro ao salvar");
					e1.printStackTrace();
				} catch (AccessException e1) {
					config.addToSystemLog(getName()+","+"Erro ao salvar");
					e1.printStackTrace();
				}
				
				resetWindow();
			}

		});
		
		btnAdicionarModalidade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddModalityWindow addModalityWindow = new AddModalityWindow(config);
				addModalityWindow.setVisible(true);
				addModalityWindow.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				        MatriculationModality mm = addModalityWindow.getMatriculationModality();
				        
				        if(!mm.equals(null)) {
				        	addMMToTable(mm);
				        	newers_mm.add(mm);
				        }
				        
				    }
				});
				
			}
		});
		
		return contentPane;
	}
	
	public void resetTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
		table.setModel(model);
	}
	
	public void addMMToTable(MatriculationModality mm) {
		if(mm == null) {
			return;
		}
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] {mm.getModality(),mm.getGraduation(),mm.getPlan(),mm.getBegin_date(),mm.getEnd_date()});
		table.setModel(model);
	}
	
	public void resetWindow() {
		newers_mm = new ArrayList<MatriculationModality>();
		resetTable();
		waiting = false;
		search = false;
		btnAdd.setEnabled(true);
		btnSearch.setEnabled(true);
		btnRemove.setEnabled(false);
		btnSave.setEnabled(false);
		btnAdicionarModalidade.setEnabled(false);
		textFieldF9.setText("Teclar F9");
		textFieldF9.setEnabled(false);
		textFieldRegisterDay.setText("");
		textFieldRegistration.setText("");
		textFieldStudent.setText("");
		textFieldFinishDay.setText("");
		textFieldRegisterDay.setEnabled(false);
		textFieldRegistration.setEnabled(false);
		textFieldStudent.setEnabled(false);
		textFieldFinishDay.setEnabled(false);
		table.setEnabled(false);
	}
	
}
