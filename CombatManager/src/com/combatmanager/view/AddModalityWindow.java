package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.combatmanager.database.dao.GraduationDAO;
import com.combatmanager.database.dao.ModalityDAO;
import com.combatmanager.database.dao.PlanDAO;
import com.combatmanager.database.model.Graduation;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.database.model.Modality;
import com.combatmanager.database.model.Plan;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

import javax.swing.JButton;

public class AddModalityWindow extends JFrame implements View {
	private JFormattedTextField textFieldStart;
	private JFormattedTextField textFieldEnd;
	private MaskFormatter maskDate1;
	private MaskFormatter maskDate2;
	private JComboBox comboModality;
	private JComboBox comboGraduation;
	private JComboBox comboPlan;
	private JButton btnNewButton;
	private Configuration config;
	private int acess = 5*11;
	private Modality save_modality;
	private MatriculationModality save_matriculationModality;
	
	
	/**
	 * Create the panel.
	 */
	public AddModalityWindow(Configuration config) {
		try {
			maskDate1 = new MaskFormatter("####/##/##");
			maskDate2 = new MaskFormatter("####/##/##");
			
			maskDate1.setValidCharacters("0123456789");
			maskDate2.setValidCharacters("0123456789");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		this.config = config;
		save_matriculationModality = null;
		setLayout(null);
		setBounds(0, 0, 450, 403);
		
		JInternalFrame internalFrame = new JInternalFrame("Adicionar Modalidades");
		internalFrame.setBounds(0, 0, 298, 232);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
		Dimension jInternalFrameSize = getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		setLocation(width, height);
		
		comboModality = new JComboBox();
		comboModality.setBounds(95, 9, 177, 20);
		internalFrame.getContentPane().add(comboModality);
		comboModality.addItem("--- Selecione ---");
				
		comboModality.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboModality.getSelectedIndex() != 0) {
					save_modality = new Modality();
					save_modality.setModality(comboModality.getSelectedItem().toString());
					startComboBoxes();
				}
				else {
					save_modality = null;
					resetWindow();
				}
			}
		});
		
		
		
		JLabel lblModalidade = new JLabel("Modalidade:");
		lblModalidade.setFont(new Font("Dialog", Font.BOLD, 12));
		lblModalidade.setBounds(10, 11, 164, 14);
		internalFrame.getContentPane().add(lblModalidade);
		
		JLabel lblGraduao = new JLabel("Gradua\u00E7\u00E3o:");
		lblGraduao.setFont(new Font("Dialog", Font.BOLD, 12));
		lblGraduao.setBounds(10, 45, 164, 14);
		internalFrame.getContentPane().add(lblGraduao);
		
		JLabel lblPlano = new JLabel("Plano:");
		lblPlano.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPlano.setBounds(10, 76, 164, 14);
		internalFrame.getContentPane().add(lblPlano);
		
		JLabel lblDataIncio = new JLabel("Data In\u00EDcio:");
		lblDataIncio.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDataIncio.setBounds(10, 104, 164, 14);
		internalFrame.getContentPane().add(lblDataIncio);
		
		JLabel lblDataFim = new JLabel("Data Fim:");
		lblDataFim.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDataFim.setBounds(10, 137, 164, 14);
		internalFrame.getContentPane().add(lblDataFim);
		
		textFieldStart = new JFormattedTextField(maskDate1);
		textFieldStart.setBounds(95, 104, 177, 20);
		internalFrame.getContentPane().add(textFieldStart);
		textFieldStart.setColumns(10);
		
		textFieldEnd = new JFormattedTextField(maskDate2);
		textFieldEnd.setColumns(10);
		textFieldEnd.setBounds(95, 135, 177, 20);
		internalFrame.getContentPane().add(textFieldEnd);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(183, 166, 89, 23);
		internalFrame.getContentPane().add(btnNewButton);
		internalFrame.setVisible(true);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(comboPlan.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Favor escolher plano");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}
				
				MatriculationModality matriculationModality = new MatriculationModality();
				
				matriculationModality.setModality(comboModality.getSelectedItem().toString());
				matriculationModality.setPlan(comboPlan.getSelectedItem().toString());
				if(comboGraduation.getSelectedIndex() == 0) {
					matriculationModality.setGraduation(null);
				}
				else {
					matriculationModality.setGraduation(comboGraduation.getSelectedItem().toString());
				}
				
				if(textFieldEnd.equals("")) {
					matriculationModality.setEnd_date(null);
				}
				else {
					matriculationModality.setEnd_date(textFieldEnd.getText());
				}
				
				if(textFieldStart.equals("")) {
					matriculationModality.setEnd_date(null);
				}
				else {
					matriculationModality.setBegin_date(textFieldStart.getText());
				}

				save_matriculationModality = matriculationModality;
				
				dispatchEvent(new WindowEvent(getFrames()[0], WindowEvent.WINDOW_CLOSING));
				
			}
		});
		
		resetWindow();
		internalFrame.getContentPane().add(comboGraduation);
		internalFrame.getContentPane().add(comboPlan);
	}
	
	public MatriculationModality getMatriculationModality() {
		return this.save_matriculationModality;
	}
	
	void resetWindow() {
		comboGraduation = new JComboBox();
		comboGraduation.setBounds(95, 42, 177, 20);
		comboPlan = new JComboBox();
		comboPlan.setBounds(95, 73, 177, 20);

		comboModality.setEnabled(true);
		comboPlan.setEnabled(false);
		comboGraduation.setEnabled(false);
		comboGraduation.addItem("--- Selecione ---");
		comboPlan.addItem("--- Selecione ---");
		comboModality.addItem("--- Selecione ---");
		comboPlan.setEnabled(false);
		textFieldStart.setEnabled(false);
		textFieldEnd.setEnabled(false);
		save_modality = null;
		ModalityDAO modalityDao = null;
		
		try {
			modalityDao = new ModalityDAO(this.config.getConnection());
			List<Object> modality_list = modalityDao.SelectAll();
			for(int i=0;i<modality_list.size();i++) {
				Modality auxiliar_modality = (Modality) modality_list.get(i);
				comboModality.addItem(auxiliar_modality.getModality());
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
				comboGraduation.addItem(graduation.getGraduation());
			}
			
			for(int i=0;i<plan_list.size();i++) {
				Plan plan = plan_list.get(i);
				comboPlan.addItem(plan.getPlan());
			}
			comboModality.setEnabled(false);
			comboGraduation.setEnabled(true);
			comboPlan.setEnabled(true);
			textFieldEnd.setEnabled(true);
			textFieldStart.setEnabled(true);
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
	public JInternalFrame run(Configuration config) {
		this.config = config;
		return null;
	}
	
}
