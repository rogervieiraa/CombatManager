package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;

import com.combatmanager.database.dao.GraduationDAO;
import com.combatmanager.database.dao.ModalityDAO;
import com.combatmanager.database.dao.PlanDAO;
import com.combatmanager.database.model.Graduation;
import com.combatmanager.database.model.Modality;
import com.combatmanager.database.model.Plan;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

import javax.swing.JButton;

public class AddModalityWindow extends JFrame implements View {
	private JTextField textFieldStart;
	private JTextField textFieldEnd;
	private JComboBox comboModalidade;
	private JComboBox comboGraduacao;
	private JComboBox comboPlano;
	private JButton btnNewButton;
	private Configuration config;
	private int acess = 0;
	private Modality save_modality;
	
	/**
	 * Create the panel.
	 */
	public AddModalityWindow() {
		setLayout(null);
		setBounds(0, 0, 450, 403);
		
		JInternalFrame internalFrame = new JInternalFrame("Adicionar Modalidades");
		internalFrame.setBounds(0, 0, 298, 232);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		comboModalidade = new JComboBox();
		comboModalidade.setBounds(95, 9, 177, 20);
		internalFrame.getContentPane().add(comboModalidade);
		comboModalidade.addItem("--- Selecione ---");
				
		comboModalidade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboModalidade.getSelectedIndex() != 0) {
					save_modality = new Modality();
					save_modality.setModality(comboModalidade.getSelectedItem().toString());
					startComboBoxes();
				}
				else {
					save_modality = null;
					resetWindow();
				}
			}
		});
		
		
		
		JLabel lblModalidade = new JLabel("Modalidade:");
		lblModalidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModalidade.setBounds(10, 11, 164, 14);
		internalFrame.getContentPane().add(lblModalidade);
		
		JLabel lblGraduao = new JLabel("Gradua\u00E7\u00E3o:");
		lblGraduao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGraduao.setBounds(10, 45, 164, 14);
		internalFrame.getContentPane().add(lblGraduao);
		
		JLabel lblPlano = new JLabel("Plano:");
		lblPlano.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPlano.setBounds(10, 76, 164, 14);
		internalFrame.getContentPane().add(lblPlano);
		
		JLabel lblDataIncio = new JLabel("Data In\u00EDcio:");
		lblDataIncio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataIncio.setBounds(10, 104, 164, 14);
		internalFrame.getContentPane().add(lblDataIncio);
		
		JLabel lblDataFim = new JLabel("Data Fim:");
		lblDataFim.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataFim.setBounds(10, 137, 164, 14);
		internalFrame.getContentPane().add(lblDataFim);
		
		textFieldStart = new JTextField();
		textFieldStart.setBounds(95, 104, 177, 20);
		internalFrame.getContentPane().add(textFieldStart);
		textFieldStart.setColumns(10);
		
		textFieldEnd = new JTextField();
		textFieldEnd.setColumns(10);
		textFieldEnd.setBounds(95, 135, 177, 20);
		internalFrame.getContentPane().add(textFieldEnd);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(183, 166, 89, 23);
		internalFrame.getContentPane().add(btnNewButton);
		internalFrame.setVisible(true);
		
		resetWindow();
		internalFrame.getContentPane().add(comboGraduacao);
		internalFrame.getContentPane().add(comboPlano);
	}
	
	void resetWindow() {
		comboGraduacao = new JComboBox();
		comboGraduacao.setBounds(95, 42, 177, 20);
		comboPlano = new JComboBox();
		comboPlano.setBounds(95, 73, 177, 20);

		comboModalidade.setEnabled(true);
		comboPlano.setEnabled(false);
		comboGraduacao.setEnabled(false);
		comboGraduacao.addItem("--- Selecione ---");
		comboPlano.addItem("--- Selecione ---");
		comboModalidade.addItem("--- Selecione ---");
		comboPlano.setEnabled(false);
		textFieldStart.setEnabled(false);
		textFieldEnd.setEnabled(false);
		save_modality = null;
		ModalityDAO modalityDao = null;
		
		try {
			modalityDao = new ModalityDAO(this.config.getConnection());
			List<Object> modality_list = modalityDao.SelectAll();
			for(int i=0;i<modality_list.size();i++) {
				Modality auxiliar_modality = (Modality) modality_list.get(i);
				comboModalidade.addItem(auxiliar_modality.getModality());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void startComboBoxes() {
		GraduationDAO graduationDao = null;
		PlanDAO planDao = null;
		
		try {
			graduationDao = new GraduationDAO(this.config.getConnection());
			planDao = new PlanDAO(this.config.getConnection());
			
			List<Graduation> graduation_list = graduationDao.SelectGraduationByModality(save_modality);
			List<Plan> plan_list = planDao.SelectPlanByModality(save_modality);
			
			for(int i=0;i<graduation_list.size();i++) {
				Graduation graduation = graduation_list.get(i);
				comboGraduacao.addItem(graduation.getGraduation());
			}
			
			for(int i=0;i<plan_list.size();i++) {
				Plan plan = plan_list.get(i);
				comboGraduacao.addItem(plan.getPlan());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public int getAccess() {
		return this.acess;
	}

	@Override
	public JPanel run(Configuration config) {
		this.config = config;
		return null;
	}
	
}
