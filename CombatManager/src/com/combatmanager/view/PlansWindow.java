package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import com.combatmanager.database.dao.GraduationDAO;
import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.dao.ModalityDAO;
import com.combatmanager.database.dao.PlanDAO;
import com.combatmanager.database.model.Graduation;
import com.combatmanager.database.model.Modality;
import com.combatmanager.database.model.Plan;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

public class PlansWindow extends JPanel implements View{
	private JTextField textFieldPlans;
	private JTextField textFieldPrice;
	
	JButton btnSearch;
	JButton btnAdd;
	JButton btnRemove;
	JButton btnSave;
	JComboBox comboBox;
	
	private final String NAME = "Tela Planos";
	private final int ACCESS = 0;
	private Boolean search;
	private Configuration config;
	private Plan save_plan;
	
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
		JInternalFrame internalFrame = new JInternalFrame("Planos");
		internalFrame.setFrameIcon(new ImageIcon(PlansWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 450, 200);
		
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
		toolBar.setFloatable(false);
		toolBar.setBounds(10, 11, 415, 39);
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
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--Selecione--"}));
		comboBox.setBounds(105, 63, 319, 20);
		internalFrame.getContentPane().add(comboBox);
		
		JLabel lblModality = new JLabel("Modalidade:");
		lblModality.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModality.setBounds(10, 61, 92, 20);
		internalFrame.getContentPane().add(lblModality);
		
		textFieldPlans= new JTextField();
		textFieldPlans.setBounds(105, 95, 320, 20);
		internalFrame.getContentPane().add(textFieldPlans);
		textFieldPlans.setColumns(10);
		
		JLabel lblPlano = new JLabel("Plano:");
		lblPlano.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPlano.setBounds(10, 93, 92, 20);
		internalFrame.getContentPane().add(lblPlano);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldPrice.setText("0,00");
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(105, 127, 140, 20);
		internalFrame.getContentPane().add(textFieldPrice);
		
		JLabel lblPrice = new JLabel("Valor:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setBounds(10, 125, 92, 20);
		internalFrame.getContentPane().add(lblPrice);
		
		resetWindow();
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!search) {
					textFieldPlans.setEnabled(true);
					comboBox.setEnabled(true);
					btnAdd.setEnabled(false);
					search = true;
					return;
				}
				
				PlanDAO planDao = null;
				save_plan = new Plan();
				save_plan.setModality((String)comboBox.getSelectedItem());
				save_plan.setPlan(textFieldPlans.getText());
				try {
					
					planDao = new PlanDAO(config.getConnection());
					Plan auxiliar_plan = (Plan) planDao.Select(save_plan);
					if(auxiliar_plan == null) {
						JOptionPane.showMessageDialog(null, "Plano nao encontrada.");
						resetWindow();
						return;
					}

					textFieldPrice.setText(Float.toString(auxiliar_plan.getMonth_value()));
					
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Modalidade nao encontrada.");
					resetWindow();
				} catch (AccessException e1) {
					e1.showAcessWindowDenied();
					resetWindow();
				}
				
				textFieldPrice.setEnabled(true);
				comboBox.setEnabled(false);
				textFieldPlans.setEnabled(false);
				btnRemove.setEnabled(true);
				btnSave.setEnabled(true);
				btnSearch.setEnabled(false);
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldPlans.setEnabled(true);
				textFieldPrice.setEnabled(true);
				btnAdd.setEnabled(false);
				btnSave.setEnabled(true);
				btnSearch.setEnabled(false);
				btnRemove.setEnabled(false);
				comboBox.setEnabled(true);
				
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Deletando essa modalidade voce estara deletando todas as suas respequitivas matriculas, concorda com isso?")
						== 0) {
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Operacao cancelada");
					return;
				}
				
				PlanDAO planDao = null;

				try {
					
					planDao = new PlanDAO(config.getConnection());
					Plan auxiliar_plan = save_plan;
					auxiliar_plan.setMonth_value(Float.parseFloat(textFieldPrice.getText()));
					System.out.println(auxiliar_plan.toString());
					planDao.Delete(auxiliar_plan);					
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Operacao cancelada, mediante a erro.");
					e1.printStackTrace();
				} catch (AccessException e1) {
					System.out.println("BTN SEARCH REMOVE");
					
				}
				resetWindow();
				
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				/*
				if(search) {
					return;
				}*/
				
				PlanDAO planDao = null;
				try {
					planDao = new PlanDAO(config.getConnection());
					Plan local_plan = new Plan();
					
					local_plan.setPlan(textFieldPlans.getText());
					local_plan.setMonth_value(Float.parseFloat(textFieldPrice.getText()));
					local_plan.setModality((String)comboBox.getSelectedItem());
					System.out.println(local_plan.toString());
					
					planDao.Insert(local_plan);
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
		textFieldPlans.setText("");
		textFieldPrice.setText("");
		btnRemove.setEnabled(false);
		btnSave.setEnabled(false);
		btnAdd.setEnabled(true);
		btnSearch.setEnabled(true);
		textFieldPlans.setEnabled(false);
		textFieldPrice.setEnabled(false);
		search = false;
		comboBox.setEnabled(false);
		
		ModalityDAO modalityDao = null;
		try {
			modalityDao = new ModalityDAO(config.getConnection());
			List<Object> all_modality = modalityDao.SelectAll();
			
			for(int i=0;i<all_modality.size();i++) {
				Modality local_modality = (Modality) all_modality.get(i);
				comboBox.addItem(local_modality.getModality());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AccessException e) {
			e.printStackTrace();
		}
		
	}
}
