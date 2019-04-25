package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.combatmanager.database.dao.GraduationDAO;
import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.dao.ModalityDAO;
import com.combatmanager.database.model.Graduation;
import com.combatmanager.database.model.Modality;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;

public class ModalityWindow extends JPanel implements View{
	public ModalityWindow() {
	}
	private Modality save_modality;
	private List<Graduation> save_graduation;
	private List<Integer> deleted_graduation_id;
	private JTextField textFieldModality;
	private JTextField textFieldGraduation;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnRemove;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnSearch;
	private JButton btnOk;

	private final String NAME = "Tela Modalidades";
	private final int ACCESS = 0;
	private Boolean search;
	
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
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
		JInternalFrame internalFrame = new JInternalFrame("Modalidades e Gradua\u00E7\u00F5es");
		internalFrame.setFrameIcon(new ImageIcon(ModalityWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 450, 344);
	
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
	
		Dimension jInternalFrameSize = internalFrame.getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		internalFrame.setLocation(width, height);
		
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);
		
		
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
		
		JLabel lblModality = new JLabel("Modalidade: ");
		lblModality.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModality.setBounds(10, 63, 78, 14);
		internalFrame.getContentPane().add(lblModality);
		
		textFieldModality = new JTextField();
		textFieldModality.setBounds(98, 61, 326, 20);
		internalFrame.getContentPane().add(textFieldModality);
		textFieldModality.setColumns(10);
		
		
		JLabel lblGraduation = new JLabel("Gradua\u00E7\u00E3o:  ");
		lblGraduation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGraduation.setBounds(10, 100, 78, 14);
		internalFrame.getContentPane().add(lblGraduation);
		
		textFieldGraduation = new JTextField();
		textFieldGraduation.setColumns(10);
		textFieldGraduation.setBounds(98, 98, 266, 20);
		
		internalFrame.getContentPane().add(textFieldGraduation);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(370, 92, 53, 29);
		internalFrame.getContentPane().add(btnOk);
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		JLabel lblWarning = new JLabel("Duplo Clique na linha da gradua\u00E7\u00E3o para remov\u00EA-la");
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWarning.setBounds(10, 289, 414, 14);
		internalFrame.getContentPane().add(lblWarning);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 133, 414, 148);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel();
		model.addColumn("Graduacao");
		table.setModel(model);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("New label");
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		resetWindow();
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!search) {
					textFieldModality.setEnabled(true);
					btnAdd.setEnabled(false);
					search = true;
					return;
				}
				ModalityDAO modalityDao = null;
				GraduationDAO graduationDao = null;
				btnRemove.setEnabled(true);
				btnSave.setEnabled(true);
				btnSearch.setEnabled(false);
				textFieldGraduation.setEnabled(true);
				textFieldModality.setEnabled(true);
				btnOk.setEnabled(true);
				
				save_modality = new Modality();
				save_modality.setModality(textFieldModality.getText());
				try {
					
					modalityDao = new ModalityDAO(config.getConnection());
					Modality auxiliar_modality = (Modality) modalityDao.Select(save_modality);
					if (save_modality.getModality().equals(auxiliar_modality.getModality())) {
						textFieldModality.setText(save_modality.getModality());
						textFieldModality.setEnabled(false);
					}
					graduationDao = new GraduationDAO(config.getConnection());
					save_graduation = graduationDao.SelectGraduationByModality(save_modality);
					for(int i=0;i<save_graduation.size();i++) {
						model.addRow(new Object[] {save_graduation.get(i).getGraduation()});
					}
					
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Modalidade nao encontrada.");
					resetWindow();
				} catch (AccessException e1) {
					System.out.println("BTN SEARCH ERROR");
					e1.printStackTrace();
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldGraduation.setEnabled(true);
				textFieldModality.setEnabled(true);
				btnSave.setEnabled(true);
				btnOk.setEnabled(true);
				btnSearch.setEnabled(false);
				btnAdd.setEnabled(false);
				btnRemove.setEnabled(false);
				
			}
		});
		
		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String local_graduation = textFieldGraduation.getText();
				
				if(textFieldGraduation.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Graduacao vazia.");
				}
				textFieldGraduation.setText("");

				model.addRow(new Object[] {local_graduation});
					
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Deletando essa modalidade voce estara deletando todas as suas respequitivas graduacoes, concorda com isso?")
						== 0) {
					
				}
				else if (JOptionPane.showConfirmDialog(null, "Deletando essa modalidade voce estara deletando todos os seus respequitivos planos, concorda com isso?")
						== 0) {
					
				}
				else if (JOptionPane.showConfirmDialog(null, "Deletando essa modalidade voce estara deletando todas as suas respequitivas matriculas, concorda com isso?")
						== 0) {
				}
				else {
					JOptionPane.showMessageDialog(null, "Operacao cancelada");
					return;
				}
				
				ModalityDAO modalityDao = null;
				GraduationDAO graduationDao = null;
				MatriculationModalityDAO matriculationModalityDAO = null;
				try {
					
					modalityDao = new ModalityDAO(config.getConnection());
					graduationDao = new GraduationDAO(config.getConnection());
					matriculationModalityDAO = new MatriculationModalityDAO(config.getConnection());
					Modality auxiliar_modality = save_modality;
					
					
					matriculationModalityDAO.DeleteByModality(auxiliar_modality);
					graduationDao.DeleteByModality(auxiliar_modality);
					modalityDao.Delete(auxiliar_modality);
					
					
					
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Operacao cancelada, mediante a erro.");
				} catch (AccessException e1) {
					System.out.println("BTN SEARCH REMOVE");
					e1.printStackTrace();
				}
				resetWindow();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ModalityDAO modalityDao = null;
				GraduationDAO graduationDao = null;
				MatriculationModalityDAO matriculationModalityDao = null;
				//UNTESTED
				if(search) {
					try {
						graduationDao = new GraduationDAO(config.getConnection());
						matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
						//insert
						for(int i=0;i<model.getRowCount();i++) {
							String model_value_graduation = (String) model.getValueAt(i, 0);
							Boolean found = false;
							for(int j=0;j<save_graduation.size();j++) {
								if(save_graduation.get(j).getGraduation().equals(model_value_graduation)) {
									save_graduation.remove(j);
									found = true;
									break;
								}
							}
							if(!found) {
								Graduation local_graduation = new Graduation();
								local_graduation.setModality(save_modality.getModality());
								local_graduation.setGraduation(model_value_graduation);
								graduationDao.Insert(new Graduation());
							}
						}
						//delete
						for(int i=0;i<save_graduation.size();i++) {
							matriculationModalityDao.UpdateGraduation(save_graduation.get(i),null);
							graduationDao.Delete(save_graduation);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (AccessException e1) {
						e1.printStackTrace();
					}
					
					return;
				}
				
				try {
					modalityDao = new ModalityDAO(config.getConnection());
					
					Modality local_modality = new Modality();
					local_modality.setModality(textFieldModality.getText());
					System.out.println(local_modality.toString());
					modalityDao.Insert(local_modality);
					
					graduationDao = new GraduationDAO(config.getConnection());
					for(int i=0;i<model.getRowCount();i++) {
						Graduation local_gradual = new Graduation();
						
						local_gradual.setModality(local_modality.getModality());
						local_gradual.setGraduation((String) model.getValueAt(i, 0));
						System.out.println(local_gradual.toString());
						graduationDao.Insert(local_gradual);
						
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (AccessException e1) {
					e1.printStackTrace();
				}
				
				resetWindow();
			}

		});
		
		return contentPane;
	}
	
	private void resetWindow() {
		textFieldGraduation.setText("");
		textFieldModality.setText("");
		btnOk.setEnabled(false);
		btnRemove.setEnabled(false);
		btnSave.setEnabled(false);
		btnAdd.setEnabled(true);
		btnSearch.setEnabled(true);
		textFieldModality.setEnabled(false);
		textFieldGraduation.setEnabled(false);
		search = false;
		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
		
	}
	
}
