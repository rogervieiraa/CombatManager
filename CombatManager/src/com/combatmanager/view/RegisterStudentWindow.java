package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import com.combatmanager.database.dao.AttendanceDAO;
import com.combatmanager.database.dao.GraduationDAO;
import com.combatmanager.database.dao.MatriculationDAO;
import com.combatmanager.database.dao.MatriculationInvoicesDAO;
import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.dao.ModalityDAO;
import com.combatmanager.database.dao.StudentDAO;
import com.combatmanager.database.model.Graduation;
import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationInvoices;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.database.model.Modality;
import com.combatmanager.database.model.Student;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

import controller.CombatImage;

public class RegisterStudentWindow extends JInternalFrame implements View{
	private JTextField textFieldRegistration;
	private JTextField textFieldF9;
	private JTextField textFieldStudent;
	private JFormattedTextField textFieldRegisterDay;
	private MaskFormatter maskDate;
	private JFormattedTextField textFieldFinishDay;
	private MaskFormatter maskDay;
	private JTable table;
	private JButton btnSave;
	private JButton btnSearch;
	private JButton btnRemove;
	private JButton btnAdd;
	private JButton btnAdicionarModalidade;
	private Boolean search;
	private JPopupMenu popMenu;
	private JMenuItem menuItemRemove;
	private JMenuItem menuItemAltera;
	private Matriculation save_matriculation;
	private List<MatriculationModality> save_matriculationModality;
	private List<MatriculationModality> newers_mm;
	private final String NAME = "Tela Cadastro Alunos";
	private final int ACCESS = 5*11;
	private Boolean searchOrAdd;
	private Configuration config;
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Modalidade", "Gradua\u00E7\u00E3o", "Plano", "Data In\u00EDcio", "Data Fim"
			}
		){
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
	private HomeWindow hw;
	
	/**
	 * Create the panel.
	 */
	public RegisterStudentWindow(HomeWindow hw) {
		this.hw = hw;
	}
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
	public JInternalFrame run(Configuration config) {
		
		try {
			maskDate = new MaskFormatter("####/##/##");
			
			maskDate.setValidCharacters("0123456789");
			maskDay.setValidCharacters("0123456789");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		addInternalFrameListener((InternalFrameListener) new InternalFrameAdapter(){
	        public void internalFrameClosing(InternalFrameEvent e) {
	            resetWindow();
	        }
	    });
		
		this.config = config;
		searchOrAdd = false;
		newers_mm = new ArrayList<MatriculationModality>();
		setFocusable(true);
		setLayout(null);
		setTitle("Matricular Aluno");
		setFrameIcon(CombatImage.combatvinte_20x20);
		setBounds(0, 0, 526, 396);
		getContentPane().setLayout(null);
		setVisible(true);	
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		setClosable(true);
		Dimension jInternalFrameSize = getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		
		setLocation(width, height);
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 415, 39);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);
		
		btnSearch = new JButton("Buscar");
		btnSearch.setIcon(CombatImage.localizar_22x22);
	
		btnSearch.setMaximumSize(new Dimension(98, 80));
		toolBar.add(btnSearch);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(CombatImage.adicionar_22x22);
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		btnAdd.setMaximumSize(new Dimension(98, 80));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		btnRemove = new JButton("Remover");
		btnRemove.setIcon(CombatImage.remover_22x22);
		btnRemove.setMaximumSize(new Dimension(98, 80));
		
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		btnSave = new JButton("Salvar");
		btnSave.setIcon(CombatImage.salvar_22x22);
		btnSave.setMaximumSize(new Dimension(98, 80));
	
		toolBar.add(btnSave);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula: ");
		lblMatrcula.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMatrcula.setBounds(10, 62, 84, 22);
		getContentPane().add(lblMatrcula);
		
		textFieldRegistration = new JTextField();
		textFieldRegistration.setBounds(143, 61, 84, 19);
		textFieldRegistration.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == arg0.VK_ENTER)
					btnSearch.doClick();
				
			}
		});
		getContentPane().add(textFieldRegistration);
		textFieldRegistration.setColumns(10);
		
		JLabel lblAluno = new JLabel("Aluno: ");
		lblAluno.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAluno.setBounds(10, 94, 84, 22);
		getContentPane().add(lblAluno);
		
		textFieldF9 = new JTextField();
		textFieldF9.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldF9.setBackground(Color.YELLOW);
		textFieldF9.setForeground(Color.BLACK);
		textFieldF9.setColumns(10);
		textFieldF9.setBounds(143, 97, 84, 19);
		getContentPane().add(textFieldF9);
		
		textFieldStudent = new JTextField();
		textFieldStudent.setEnabled(false);
		textFieldStudent.setColumns(10);
		textFieldStudent.setBounds(237, 97, 263, 19);
		getContentPane().add(textFieldStudent);
		
		textFieldRegisterDay = new JFormattedTextField(maskDate);
		textFieldRegisterDay.setColumns(10);
		textFieldRegisterDay.setBounds(143, 127, 84, 19);
		getContentPane().add(textFieldRegisterDay);
		
		JLabel lblRegisterDay = new JLabel("Data da matr\u00EDcula: ");
		lblRegisterDay.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRegisterDay.setBounds(10, 124, 116, 22);
		getContentPane().add(lblRegisterDay);
		
		JLabel lblDiaDoVencimento = new JLabel("Dia do vencimento da fatura: ");
		lblDiaDoVencimento.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDiaDoVencimento.setBounds(237, 124, 183, 22);
		getContentPane().add(lblDiaDoVencimento);
		
		textFieldFinishDay = new JFormattedTextField(maskDay);
		textFieldFinishDay.setBounds(422, 127, 78, 20);
		getContentPane().add(textFieldFinishDay);
		textFieldFinishDay.setColumns(10);
		
		btnAdicionarModalidade = new JButton("Adicionar Modalidade");
		btnAdicionarModalidade.setBounds(10, 157, 217, 23);
		getContentPane().add(btnAdicionarModalidade);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 490, 160);
		getContentPane().add(scrollPane);
		
		popMenu = new JPopupMenu();
		menuItemRemove = new JMenuItem("Remover");
		menuItemAltera = new JMenuItem("Alterar data fim");
		
		menuItemRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = table.getSelectedRow();
				
				if (model.getValueAt(index, 0) != null) {
					Modality mm = new Modality();
					MatriculationModalityDAO mmDao;
					
					mm.setModality(model.getValueAt(index, 0).toString());
					
					try {
						mmDao = new MatriculationModalityDAO(config.getConnection());
						
						mmDao.DeleteByModality(mm);
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro ao gravar!");
						return;
					}
					model.removeRow(index);
				}
				
				
			}
		});
		menuItemAltera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = table.getSelectedRow();
				
				if (model.getValueAt(index, 0) != null) {
					String resp = JOptionPane.showInputDialog("Informe a nova data final:");
					
					model.setValueAt(resp, index, 4);
				}
				
			}
		});
		popMenu.add(menuItemAltera);
		popMenu.add(menuItemRemove);
		
		
		table = new JTable();
		table.setModel(model);
		table.setEnabled(true);
		table.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					if (e.isPopupTrigger()) {
			            popMenu.show(e.getComponent(),
			                       e.getX(), e.getY());
			        }
				}
				
		    }
			public void mouseReleased(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					if (e.isPopupTrigger()) {
			            popMenu.show(e.getComponent(),
			                       e.getX(), e.getY());
			        }
				}
		    }
			
		});
		scrollPane.setViewportView(table);
		
		
		resetWindow();

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!search) {
					searchOrAdd = true;
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
				table.setEnabled(true);				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchOrAdd = true;
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
				textFieldStudent.setEnabled(true);
				textFieldStudent.addKeyListener(new KeyAdapter() {
					
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_F9) {
							ChooseStudentWindow csw = new ChooseStudentWindow(config);
							csw.setVisible(true);
							csw.addWindowListener(new java.awt.event.WindowAdapter() {
			                    @Override
			                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			                      
			                    	if(csw.getStudentId() == null)
			                    		return;			                    	
			                    	
			                       Student aux_student = csw.getStudentId();
			                       if(aux_student != null) {
			                    	   textFieldF9.setText(aux_student.getIndex().toString());
			                    	   textFieldStudent.setText(aux_student.getName()); 
				                       textFieldStudent.setEnabled(false);
			                       }
			                     
			                    }
			                });
						}
					}
				});
				
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
				MatriculationInvoicesDAO matriculationInvoiceDao = null;
				AttendanceDAO attendenceDao = null;
				try {
					matriculationInvoiceDao = new MatriculationInvoicesDAO(config.getConnection());
					attendenceDao = new AttendanceDAO(config.getConnection());
					matriculationDao = new MatriculationDAO(config.getConnection());
					matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
					Matriculation auxiliar_matriculation = save_matriculation;
					System.out.println(auxiliar_matriculation.toString());
					attendenceDao.Delete(auxiliar_matriculation);
					matriculationInvoiceDao.Delete(auxiliar_matriculation);
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
				
				if(textFieldStudent.isEnabled()) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo de aluno utilizando F9");
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
				
				if (textFieldFinishDay.getText().equals("")) {
					
				}else if(Integer.parseInt(textFieldFinishDay.getText()) > 29 || Integer.parseInt(textFieldFinishDay.getText()) < 0) {
					JOptionPane.showMessageDialog(null, "Dia de encerramento incorreto");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em incorreto");
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
												
						for(int i=0;i<model.getRowCount();i++) {
							MatriculationModality aux_mm = new MatriculationModality();
							for (int j = 0; j < newers_mm.size(); j++) {
								if (newers_mm.get(j).getModality().equals(model.getValueAt(i, 0).toString())) {
									MatriculationModality aux_mmm = newers_mm.get(j);
									aux_mmm.setMatriculation_code(auxiliar_matriculation.getCode());
									matriculationModalityDao.Insert(aux_mmm);
									return;
								}
							}
							
							aux_mm.setMatriculation_code(Integer.parseInt(textFieldRegistration.getText()));
							aux_mm.setModality(model.getValueAt(i, 0).toString());
							aux_mm.setGraduation(model.getValueAt(i, 1).toString());
							aux_mm.setPlan(model.getValueAt(i, 2).toString());
							aux_mm.setBegin_date(model.getValueAt(i, 3).toString());
							aux_mm.setEnd_date(model.getValueAt(i, 4) == null ? "" : model.getValueAt(i, 4).toString());
							
							
							matriculationModalityDao.Update(aux_mm, aux_mm);
						}

						
						config.addToSystemLog(getName()+","+"Salvou com sucesso");
						JOptionPane.showMessageDialog(null, "Operacao de salvar realizada com sucesso.");
						resetWindow();
						
						hw.is_saved = true;
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
					
					
					
					studentDao = new StudentDAO(config.getConnection());
					Student auxiliar_student = new Student();
					
					auxiliar_student.setName(textFieldStudent.getText());
					
					auxiliar_student = (Student) studentDao.Select(auxiliar_student);
					if(auxiliar_student != null) {
						List<Matriculation> aux_mmm =  matriculationDao.SelectAllMatriculationByStudent(auxiliar_student);
						if(aux_mmm.size() > 0) {
							JOptionPane.showMessageDialog(null, "Aluno ja cadastrado");
							config.addToSystemLog(getName()+","+"Tentou salvar aluno ja matriculado");
							resetWindow();
							return;
						}
					}
					
					Matriculation auxiliar_matriculation = new Matriculation();
					auxiliar_matriculation.setStudent_code(Integer.parseInt(textFieldF9.getText()));
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
					
					hw.is_saved = true;
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
				        
				    	if(addModalityWindow.getMatriculationModality() == null)
				    		return;			
				    	
				    	MatriculationModality mm = addModalityWindow.getMatriculationModality();
				        
				        if(!mm.equals(null)) {
				        	addMMToTable(mm);
				        	newers_mm.add(mm);
				        }
				        
				    }
				});
			}
		});
		
		
		
		return this;
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
		searchOrAdd = false;
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

	public void setMatriculation(Matriculation matriculation) {
		btnAdd.setEnabled(false);
		btnSearch.setEnabled(false);
		btnRemove.setEnabled(false);
		btnSave.setEnabled(false);
		btnAdicionarModalidade.setEnabled(false);
		textFieldF9.setEnabled(false);
		textFieldRegisterDay.setEnabled(false);
		textFieldRegistration.setEnabled(false);
		textFieldStudent.setEnabled(false);
		textFieldFinishDay.setEnabled(false);
		table.setEnabled(false);
		
		textFieldRegistration.setText(matriculation.getCode().toString());
		textFieldF9.setText(matriculation.getStudent_code().toString());
		textFieldStudent.setText("");
		textFieldRegisterDay.setText(matriculation.getMatriculation_date());
		textFieldFinishDay.setText(matriculation.getDue_date().toString());
		
		MatriculationModalityDAO matriculationModalityDao;
		StudentDAO studentDao;
		
		try {
			Student aux_student = new Student();
			studentDao = new StudentDAO(config.getConnection());
			aux_student.setIndex(matriculation.getStudent_code());
			Student local_student = (Student) studentDao.SelectById(aux_student);
			textFieldStudent.setText(local_student.getName());
			matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
			save_matriculationModality = matriculationModalityDao.SelectGraduationByMatriculation(matriculation);
			for(int i=0;i<save_matriculationModality.size();i++) {
				addMMToTable(save_matriculationModality.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
