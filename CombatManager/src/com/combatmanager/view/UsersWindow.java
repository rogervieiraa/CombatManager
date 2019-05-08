package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.combatmanager.security.Configuration;

import controller.CombatImage;

import com.combatmanager.database.dao.StudentDAO;
import com.combatmanager.database.dao.UserDAO;
import com.combatmanager.database.model.City;
import com.combatmanager.database.model.Student;
import com.combatmanager.database.model.User;
import com.combatmanager.error.AccessException;

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
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UsersWindow extends JPanel implements View {
	public UsersWindow() {
	}
	
	private final String NAME = "Tela Usuarios";
	private final int ACCESS = 11;
	private Boolean search = false;
	private String save_user;
	
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
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnSave;
	private JComboBox comboBox;
	private JInternalFrame internalFrame;
	
	/**
	 * Create the panel.
	 */
	public JPanel run(Configuration config) {
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
		internalFrame = new JInternalFrame("Tela Usuarios");	
		internalFrame.setBounds(0, 0, 450, 300);
		contentPane.add(internalFrame);
		internalFrame.setVisible(true);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setFrameIcon(CombatImage.combatvinte_20x20);
		
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
		
		btnSearch = new JButton("Buscar");
		btnSearch.setIcon(CombatImage.localizar_22x22);
		btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSearch.setMaximumSize(new Dimension(100, 40));
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!search) {
					textField.setEnabled(true);
					comboBox.setEnabled(true);
					btnAdd.setEnabled(false);
					search = true;
					return;
				}
				save_user = textField.getText();
				UserDAO userDao = null;
				
				User user = new User();
				user.setUser(textField.getText());
				
				if (comboBox.getSelectedIndex() == 1) {
					user.setProfile("Cadastrar");
				}else if (comboBox.getSelectedIndex() == 2) {
					user.setProfile("Matricular");
				}else if (comboBox.getSelectedIndex() == 3) {
					user.setProfile("Financeiro");
				}else if (comboBox.getSelectedIndex() == 4) {
					user.setProfile("Completo");
				}
				
				
				try {
					
					userDao = new UserDAO(config.getConnection());
					User auxiliar_user = (User) userDao.Select(user);
					if(auxiliar_user == null) {
						JOptionPane.showMessageDialog(null, "Usuario  nao encontrado.");
						resetWindow();
						return;
					}
					System.out.println(auxiliar_user.getUser());
					textField.setText(auxiliar_user.getUser());
					
					if ("Cadastrar".equals(auxiliar_user.getProfile())) {
						comboBox.setSelectedIndex(1);
					}else if ("Matricular".equals(auxiliar_user.getProfile())) {
						comboBox.setSelectedIndex(2);
					}else if ("Financeiro".equals(auxiliar_user.getProfile())) {
						comboBox.setSelectedIndex(3);
					}else if ("Completo".equals(auxiliar_user.getProfile())) {
						comboBox.setSelectedIndex(4);
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
		toolBar.add(btnSearch);
		
		
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(CombatImage.adicionar_22x22);
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdd.setMaximumSize(new Dimension(100, 40));
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startSave();
			}
		});
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		btnRemove = new JButton("Remover");
		btnRemove.setIcon(CombatImage.remover_22x22);
		btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRemove.setMaximumSize(new Dimension(100, 40));
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				UserDAO userDao;
				try {
					userDao = new UserDAO(config.getConnection());
					
					user.setUser(textField.getText());
					
					
					user = (User) userDao.Select(user);
					
					System.out.println(user.toString());
					
					userDao.Delete(user);
					
				
				} catch (SQLException | AccessException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao remover!");
					e1.printStackTrace();
					resetWindow();
				}
				resetWindow();
			}
				
		});
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		btnSave = new JButton("Salvar");
		btnSave.setIcon(CombatImage.salvar_22x22);
		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSave.setMaximumSize(new Dimension(100, 40));
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				config.addToSystemLog(getName()+","+"Incio operacao de salvar");
				UserDAO userDao = null;
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo de usuario");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}else if (pwdPassword.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo de senha");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}else if (pwdConfirmPassword.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo de confirmacao de senha");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}else if (pwdConfirmPassword.getPassword().equals(pwdPassword.getPassword())) {
					JOptionPane.showMessageDialog(null, "As senhas nao coincidem!");
					config.addToSystemLog(getName()+","+"Campo de confirmacao nao coincide com senha");
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
						userDao = new UserDAO(config.getConnection());
						User last_user = new User();
						User new_user = new User();
						
						last_user.setUser(save_user);
						
						last_user = (User) userDao.Select(last_user);
						
						new_user.setUser(textField.getText());
						new_user.setPassword(pwdPassword.getText());				
						
						if (comboBox.getSelectedIndex() == 1) {
							new_user.setProfile("Cadastrar");
						}else if (comboBox.getSelectedIndex() == 2) {
							new_user.setProfile("Matricular");
						}else if (comboBox.getSelectedIndex() == 3) {
							new_user.setProfile("Financeiro");
						}else if (comboBox.getSelectedIndex() == 4) {
							new_user.setProfile("Completo");
						}
												
						
						userDao.Update(last_user, new_user);
						
						config.addToSystemLog(getName()+","+"Salvou com sucesso");
						JOptionPane.showMessageDialog(null, "Operacao de salvar realizada com sucesso.");
						resetWindow();
					} catch (SQLException e1) {
						config.addToSystemLog(getName()+","+"Erro ao salvar");
						e1.printStackTrace();
					} catch (AccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					return;
				}
				
				//caso ja exista
				
				
				try {
					userDao = new UserDAO(config.getConnection());
					User user = new User();

					
					
					user.setUser(textField.getText());
					user.setPassword(pwdPassword.getText());
					if (comboBox.getSelectedIndex() == 1) {
						user.setProfile("Cadastral");
					}else if (comboBox.getSelectedIndex() == 2) {
						user.setProfile("Matricular");
					}else if (comboBox.getSelectedIndex() == 3) {
						user.setProfile("Financeiro");
					}else {
						user.setProfile("Completo");
					}
					
					System.out.println(user.toString());
					
					userDao.Insert(user);
										
					
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
				
				resetWindow();
				
			}
		});
		toolBar.add(btnSave);
		
		textField = new JTextField();
		textField.setBounds(150, 90, 274, 20);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Dialog", Font.BOLD, 12));
		lblUser.setBounds(20, 91, 63, 14);
		internalFrame.getContentPane().add(lblUser);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(150, 129, 274, 20);
		internalFrame.getContentPane().add(pwdPassword);
		
		JLabel lblPassword = new JLabel("Senha: ");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPassword.setBounds(20, 130, 63, 14);
		internalFrame.getContentPane().add(lblPassword);
		
		pwdConfirmPassword = new JPasswordField();
		pwdConfirmPassword.setBounds(150, 172, 274, 20);
		internalFrame.getContentPane().add(pwdConfirmPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirmar Senha:");
		lblConfirmPassword.setFont(new Font("Dialog", Font.BOLD, 12));
		lblConfirmPassword.setBounds(20, 173, 133, 14);
		internalFrame.getContentPane().add(lblConfirmPassword);
		
		JLabel lblProfile = new JLabel("Perfil:");
		lblProfile.setFont(new Font("Dialog", Font.BOLD, 12));
		lblProfile.setBounds(20, 214, 133, 14);
		internalFrame.getContentPane().add(lblProfile);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--selecione--", "Cadastrar", "Matricular", "Financeiro", "Completo"}));
		comboBox.setBounds(153, 213, 271, 20);
		internalFrame.getContentPane().add(comboBox);
		
		resetWindow();
		
		return contentPane;
		
	}
	
	private void resetWindow () {

		textField.setText("");
		pwdPassword.setText("");
		pwdConfirmPassword.setText("");
		comboBox.setSelectedIndex(0);
		textField.setEnabled(false);
		pwdPassword.setEnabled(false);
		pwdConfirmPassword.setEnabled(false);
		comboBox.setEnabled(false);
		btnRemove.setEnabled(false);
		btnSave.setEnabled(false);
		btnSearch.setEnabled(true);
		btnAdd.setEnabled(true);
		search = false;
	}
	
	private void startSave() {
		textField.setEnabled(true);
		pwdPassword.setEnabled(true);
		pwdConfirmPassword.setEnabled(true);
		comboBox.setEnabled(true);
		btnSave.setEnabled(true);
		btnAdd.setEnabled(false);
		btnSearch.setEnabled(false);
	
	}
}
