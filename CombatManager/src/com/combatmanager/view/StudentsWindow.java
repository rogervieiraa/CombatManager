package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

import com.combatmanager.database.dao.GraduationDAO;
import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.dao.ModalityDAO;
import com.combatmanager.database.dao.StudentDAO;
import com.combatmanager.database.model.City;
import com.combatmanager.database.model.Graduation;
import com.combatmanager.database.model.Modality;
import com.combatmanager.database.model.Student;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

import controller.CombatImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Component;
import java.awt.Container;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import javax.swing.JSlider;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class StudentsWindow extends JPanel implements View {
	
	private JTextField textFieldStudent;
	private JTextField textFieldEmail;
	private JTextField textFieldObs;
	private JTextField textFieldPhone;
	private JTextField textFieldCellPhone;
	private JTextField textFieldAddress;
	private JTextField textFieldComplement;
	private JTextField textFieldHomeNumber;
	private JTextField textFieldLocal;
	private JTextField txtTeclarF;
	private JTextField textFieldState;
	private JTextField textFieldCep;
	private JTextField textFieldCountry;
	private JFormattedTextField formattedTextFieldDate;
	private JButton btnRemove;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnSearch;
	private JButton btnOk;
	private JComboBox comboBoxSex;
	
	private String savedcity;
	private final String NAME = "Tela Estudantes";
	private final int ACCESS = 3*11;
	private Boolean search = false;
	private Boolean add = false;
	private Configuration config;
	private String save_student = "";

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
		
		this.config = config;
		
		
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
		JInternalFrame internalFrame = new JInternalFrame("Cadastro de alunos");
		internalFrame.setBounds(0, 0, 546, 520);
		
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		contentPane.add(internalFrame, BorderLayout.CENTER);
		internalFrame.setFrameIcon(CombatImage.combatvinte_20x20);
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
	
		Dimension jInternalFrameSize = internalFrame.getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		internalFrame.setLocation(width, height);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 424, 39);
		toolBar.setFloatable(false);
		internalFrame.getContentPane().add(toolBar);
		
		btnSearch = new JButton("Buscar");
		btnSearch.setIcon(CombatImage.localizar_22x22);
		btnSearch.setMaximumSize(new Dimension(100, 80));
		toolBar.add(btnSearch);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(CombatImage.adicionar_22x22);
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		btnAdd.setMaximumSize(new Dimension(100, 80));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		btnRemove = new JButton("Remover");
		btnRemove.setIcon(CombatImage.remover_22x22);
		btnRemove.setMaximumSize(new Dimension(100, 80));
				
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		btnSave = new JButton("Salvar");
		btnSave.setIcon(CombatImage.salvar_22x22);
		btnSave.setMaximumSize(new Dimension(100, 80));
			
		toolBar.add(btnSave);
		
		JLabel lblStudent = new JLabel("Aluno:");
		lblStudent.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStudent.setBounds(10, 63, 46, 14);
		internalFrame.getContentPane().add(lblStudent);
		
		textFieldStudent = new JTextField();
		textFieldStudent.setBounds(143, 61, 377, 20);
		internalFrame.getContentPane().add(textFieldStudent);
		textFieldStudent.setColumns(10);
		
		//MaskFormatter maskFormatter = new MaskFormatter("##/##/##");
	
		
		formattedTextFieldDate = new JFormattedTextField();
		//formattedTextFieldDate.setFormatterFactory(tf);
		formattedTextFieldDate.setLocation(143, 92);
		formattedTextFieldDate.setSize(new Dimension(148, 20));

		internalFrame.getContentPane().add(formattedTextFieldDate);
		
		comboBoxSex = new JComboBox();
		comboBoxSex.setEnabled(false);
		comboBoxSex.setBounds(372, 92, 148, 20);
		comboBoxSex.addItem("M");
		comboBoxSex.addItem("F");
		internalFrame.getContentPane().add(comboBoxSex);
		
		JLabel lblBirthDate = new JLabel("Data de nascimento:");
		lblBirthDate.setFont(new Font("Dialog", Font.BOLD, 12));
		lblBirthDate.setBounds(10, 94, 153, 14);
		internalFrame.getContentPane().add(lblBirthDate);
		
		JLabel lblSex = new JLabel("Sexo:");
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSex.setBounds(301, 94, 61, 14);
		internalFrame.getContentPane().add(lblSex);
		
		JLabel lblPhone = new JLabel("Telefone:");
		lblPhone.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPhone.setBounds(10, 125, 153, 14);
		internalFrame.getContentPane().add(lblPhone);
		
		JLabel lblCellPhone = new JLabel("Celular:");
		lblCellPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCellPhone.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCellPhone.setBounds(301, 125, 61, 14);
		internalFrame.getContentPane().add(lblCellPhone);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(143, 154, 377, 20);
		internalFrame.getContentPane().add(textFieldEmail);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmail.setBounds(10, 157, 153, 14);
		internalFrame.getContentPane().add(lblEmail);
		
		JLabel lblObs = new JLabel("Observa\u00E7\u00F5es:");
		lblObs.setFont(new Font("Dialog", Font.BOLD, 12));
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
		tabbedPaneAddress.addTab("Endereco", null, addressPanel, null);
		addressPanel.setLayout(null);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 11, 68, 15);
		lblEndereo.setFont(new Font("Dialog", Font.BOLD, 12));
		addressPanel.add(lblEndereo);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setBounds(109, 9, 145, 20);
		addressPanel.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		JLabel lblComplemento = new JLabel("Complemento: ");
		lblComplemento.setFont(new Font("Dialog", Font.BOLD, 12));
		lblComplemento.setBounds(10, 42, 93, 15);
		addressPanel.add(lblComplemento);
		
		textFieldComplement = new JTextField();
		textFieldComplement.setColumns(10);
		textFieldComplement.setBounds(109, 40, 386, 20);
		addressPanel.add(textFieldComplement);
		
		textFieldHomeNumber = new JTextField();
		textFieldHomeNumber.setColumns(10);
		textFieldHomeNumber.setBounds(350, 9, 145, 20);
		addressPanel.add(textFieldHomeNumber);
		
		JLabel lblTelefone = new JLabel("Numero:");
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTelefone.setBounds(272, 12, 68, 15);
		addressPanel.add(lblTelefone);
		
		textFieldLocal = new JTextField();
		textFieldLocal.setColumns(10);
		textFieldLocal.setBounds(109, 71, 145, 20);
		addressPanel.add(textFieldLocal);
		
		txtTeclarF = new JTextField();
		txtTeclarF.setEnabled(false);
		txtTeclarF.setBackground(Color.YELLOW);
		txtTeclarF.setText("Teclar F9");
		txtTeclarF.setColumns(10);
		txtTeclarF.setBounds(350, 71, 145, 20);
		addressPanel.add(txtTeclarF);
		
		textFieldState = new JTextField();
		textFieldState.setEnabled(false);
		textFieldState.setColumns(10);
		textFieldState.setBounds(109, 102, 145, 20);
		addressPanel.add(textFieldState);
		
		textFieldCountry = new JTextField();
		textFieldCountry.setEnabled(false);
		textFieldCountry.setColumns(10);
		textFieldCountry.setBounds(350, 102, 145, 20);
		addressPanel.add(textFieldCountry);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCidade.setBounds(272, 74, 68, 15);
		addressPanel.add(lblCidade);
		
		JLabel lblPas = new JLabel("Pais:");
		lblPas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPas.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPas.setBounds(272, 105, 68, 15);
		addressPanel.add(lblPas);
		
		JLabel lblBairro = new JLabel("Bairro: ");
		lblBairro.setFont(new Font("Dialog", Font.BOLD, 12));
		lblBairro.setBounds(10, 74, 93, 15);
		addressPanel.add(lblBairro);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEstado.setBounds(10, 105, 93, 15);
		addressPanel.add(lblEstado);
		
		textFieldCep = new JTextField();
		textFieldCep.setColumns(10);
		textFieldCep.setBounds(109, 133, 145, 20);
		addressPanel.add(textFieldCep);
		
		JLabel lblCep = new JLabel("Cep:");
		lblCep.setFont(new Font("Dialog", Font.BOLD, 12));
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
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!search) {
					textFieldStudent.setEnabled(true);
					btnAdd.setEnabled(false);
					search = true;
					return;
				}
				save_student = textFieldStudent.getText();
				StudentDAO studentDao = null;
				
				Student student = new Student();
				student.setName(textFieldStudent.getText());
				student.setEmail(textFieldEmail.getText());
				try {
					
					studentDao = new StudentDAO(config.getConnection());
					Student auxiliar_student = (Student) studentDao.Select(student);
					System.out.println(auxiliar_student.toString());
					City city = new City();
					if(auxiliar_student.getName().equals("")) {
						JOptionPane.showMessageDialog(null, "Aluno nao encontrado.");
						resetWindow();
						return;
					}
					
					city = auxiliar_student.getCity();	
					textFieldAddress.setText(auxiliar_student.getAdress());
					textFieldLocal.setText(auxiliar_student.getLocal());
					textFieldState.setText(city.getState());
					textFieldCellPhone.setText(auxiliar_student.getCellPhoneNumber());
					textFieldComplement.setText(auxiliar_student.getExtraInformation());
					textFieldEmail.setText(auxiliar_student.getEmail());
					textFieldObs.setText(auxiliar_student.getNote());
					textFieldPhone.setText(auxiliar_student.getPhoneNumber());
					textFieldAddress.setText(auxiliar_student.getAdress());
					textFieldCep.setText(auxiliar_student.getCep());
					textFieldStudent.setText(auxiliar_student.getName());
					textFieldCountry.setText(city.getCountry());
					formattedTextFieldDate.setText(auxiliar_student.getBirthday());	
					textFieldHomeNumber.setText(auxiliar_student.getHomeNumber());
					savedcity = city.getName();
					txtTeclarF.setEnabled(true);
					txtTeclarF.addKeyListener(new KeyAdapter() {
						
						@Override
						public void keyPressed(KeyEvent e) {
							if (e.getKeyCode() == KeyEvent.VK_F9) {
								ChooseCityWindow ccw = new ChooseCityWindow(config);
								ccw.setVisible(true);
								ccw.addWindowListener(new java.awt.event.WindowAdapter() {
				                    @Override
				                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				                      
				                       City aux_city = ccw.getCity();
				                       if(aux_city.getName() != null) {
				                    	   System.out.println(aux_city.toString());
				                    	   txtTeclarF.setText(aux_city.getName());
				                    	   txtTeclarF.setEnabled(false);
				                    	   textFieldState.setText(aux_city.getState());
				                    	   textFieldCountry.setText(aux_city.getCountry());
				                       }
				                     
				                    }
				                });
							}
						}
					});
					if ("M".equals(auxiliar_student.getSex())) {
						comboBoxSex.setSelectedIndex(0);
					}else {
						comboBoxSex.setSelectedIndex(1);
					}	
					
					startSave();
					btnRemove.setEnabled(true);
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Aluno nao encontrado.");
					resetWindow();
				} catch (AccessException e1) {
					e1.showAcessWindowDenied();
					resetWindow();
				}
							
			}
		
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startSave();
				add = true;
				txtTeclarF.setEnabled(true);
				txtTeclarF.addKeyListener(new KeyAdapter() {
					
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_F9) {
							ChooseCityWindow ccw = new ChooseCityWindow(config);
							ccw.setVisible(true);
							ccw.addWindowListener(new java.awt.event.WindowAdapter() {
			                    @Override
			                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			                      
			                       City aux_city = ccw.getCity();
			                       if(aux_city != null) {
			                    	   System.out.println(aux_city.toString());
			                    	   txtTeclarF.setText(aux_city.getName());
			                    	   txtTeclarF.setEnabled(false);
			                    	   textFieldState.setText(aux_city.getState());
			                    	   textFieldCountry.setText(aux_city.getCountry());
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
			public void actionPerformed(ActionEvent arg0) {
				
				Student studentr = new Student();
				StudentDAO studentDao;
				try {
					studentDao = new StudentDAO(config.getConnection());
					
					studentr.setName(textFieldStudent.getText());
					studentr.setEmail(textFieldEmail.getText());
					
					
					studentr = (Student) studentDao.Select(studentr);
					
					System.out.println(studentr.toString());
					
					studentDao.Delete(studentr);
					
				
				} catch (SQLException | AccessException e) {
					JOptionPane.showMessageDialog(null, "Erro ao remover!");
					e.printStackTrace();
					resetWindow();
				}
				resetWindow();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				config.addToSystemLog(getName()+","+"Incio operacao de salvar");
				StudentDAO studentDao = null;
				char sex = ' ';
				if(textFieldStudent.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo de nome");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}else if (comboBoxSex.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo de sexo");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}
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
						studentDao = new StudentDAO(config.getConnection());
						Student last_student = new Student();
						Student new_student = new Student();
						City city = new City();
						
						last_student.setName(save_student);
						
						last_student = (Student) studentDao.Select(last_student);
						
						new_student.setName(textFieldStudent.getText());
						new_student.setAdress(textFieldAddress.getText());
						new_student.setBirthday(formattedTextFieldDate.getText());
						new_student.setCellPhoneNumber(textFieldCellPhone.getText());
						new_student.setCep(textFieldCep.getText());
						
						if(txtTeclarF.isEnabled()) {
							city.setName(savedcity);
							city.setCountry(textFieldCountry.getText());
							city.setState(textFieldState.getText());
						}
						else {
							city.setName(txtTeclarF.getText());
							city.setCountry(textFieldCountry.getText());
							city.setState(textFieldState.getText());
						}
						
						new_student.setCity(city);
						new_student.setEmail(textFieldEmail.getText());
						new_student.setExtraInformation(textFieldComplement.getText());
						new_student.setHomeNumber(textFieldHomeNumber.getText());
						new_student.setLocal(textFieldLocal.getText());
						new_student.setNote(textFieldObs.getText());
						new_student.setPhoneNumber(textFieldPhone.getText());
												
						new_student.setSex(comboBoxSex.getSelectedItem().toString());
												
						
						studentDao.Update(last_student, new_student);
						
						config.addToSystemLog(getName()+","+"Salvou com sucesso");
						JOptionPane.showMessageDialog(null, "Operacao de salvar realizada com sucesso.");
						resetWindow();
					} catch (SQLException e1) {
						config.addToSystemLog(getName()+","+"Erro ao salvar");
						e1.printStackTrace();
					} catch (AccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return;
				}
				
				//caso ja exista
				
				
				try {
					studentDao = new StudentDAO(config.getConnection());
					Student student = new Student();
					City city = new City();
					
					
					student.setName(textFieldStudent.getText());
					student.setAdress(textFieldAddress.getText());
					student.setBirthday(formattedTextFieldDate.getText().toString());
					student.setCellPhoneNumber(textFieldCellPhone.getText());
					student.setCep(textFieldCep.getText());
					
					
					if(!txtTeclarF.isEnabled()) {
						city.setName(txtTeclarF.getText());
						city.setCountry(textFieldCountry.getText());
						city.setState(textFieldState.getText());
					}
					
					student.setCity(city);
					student.setEmail(textFieldEmail.getText());
					student.setExtraInformation(textFieldComplement.getText());
					student.setHomeNumber(textFieldHomeNumber.getText());
					student.setLocal(textFieldLocal.getText());
					student.setNote(textFieldObs.getText());
					student.setPhoneNumber(textFieldPhone.getText());
					
					
					student.setSex(comboBoxSex.getSelectedItem().toString());								
					
					studentDao.Insert(student);
										
					
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
				
				add = false;
				
				resetWindow();
				
			}
		});

		
		resetWindow();
		
		return contentPane;
	}
	
	private void resetWindow() {
		txtTeclarF.setText("Teclar F9");
		textFieldEmail.setText("");
		textFieldAddress.setText("");
		textFieldLocal.setText("");
		textFieldState.setText("");
		textFieldCountry.setText("");
		textFieldCellPhone.setText("");
		textFieldComplement.setText("");
		textFieldEmail.setText("");
		textFieldObs.setText("");
		textFieldPhone.setText("");
		textFieldHomeNumber.setText("");
		textFieldCep.setText("");
		textFieldStudent.setText("");
		formattedTextFieldDate.setText("");
		comboBoxSex.setSelectedIndex(-1);
		btnRemove.setEnabled(false);
		btnSave.setEnabled(false);
		btnAdd.setEnabled(true);
		btnSearch.setEnabled(true);
		search = false;
		textFieldAddress.setEnabled(false);
		textFieldLocal.setEnabled(false);
		textFieldState.setEnabled(false);
		textFieldCountry.setEnabled(false);
		textFieldCellPhone.setEnabled(false);
		textFieldComplement.setEnabled(false);
		textFieldEmail.setEnabled(false);
		textFieldObs.setEnabled(false);
		textFieldPhone.setEnabled(false);
		textFieldHomeNumber.setEnabled(false);
		textFieldCep.setEnabled(false);
		textFieldStudent.setEnabled(false);
		textFieldStudent.setEnabled(false);
		formattedTextFieldDate.setEnabled(false);
		comboBoxSex.setEnabled(false);
	}
	
	private void startSave (){
		btnRemove.setEnabled(false);
		btnSave.setEnabled(true);
		btnAdd.setEnabled(false);
		btnSearch.setEnabled(false);
		textFieldAddress.setEnabled(true);
		textFieldLocal.setEnabled(true);
		textFieldCellPhone.setEnabled(true);
		textFieldComplement.setEnabled(true);
		textFieldEmail.setEnabled(true);
		textFieldObs.setEnabled(true);
		textFieldPhone.setEnabled(true);
		textFieldHomeNumber.setEnabled(true);
		textFieldCep.setEnabled(true);
		textFieldStudent.setEnabled(true);
		textFieldLocal.setEnabled(true);
		formattedTextFieldDate.setEnabled(true);
		comboBoxSex.setEnabled(true);
		
	}

}


