package com.combatmanager.view;

import javax.swing.JPanel;

import com.combatmanager.database.dao.MatriculationDAO;
import com.combatmanager.database.dao.MatriculationInvoicesDAO;
import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.dao.PlanDAO;
import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationInvoices;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.database.model.Plan;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;
import com.combatmanager.util.MasterMonthChooser;

import controller.CombatImage;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class GenerateBillWindow extends JInternalFrame implements View {

	private MasterMonthChooser data;
	private final String NAME = "Tela Gerar Fatura";
	private final int ACCESS = 7*11;
	private JButton btnNewButton;
	private Configuration config;
	
	/**
	 * Create the panel.
	 */
	public GenerateBillWindow() {

	}
	@Override
	public int getAccess() {
		return ACCESS;
	}
	
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public JInternalFrame run(Configuration config) {
		this.config = config;

		setLayout(null);
	
		setTitle("Gerar Fatura");
		setClosable(true);
		setFrameIcon(CombatImage.combatvinte_20x20);
		setBounds(0, 0, 336, 132);

		getContentPane().setLayout(null);
		
		data = new MasterMonthChooser();
		data.setBounds(129, 12, 185, 26);
		getContentPane().add(data);
		
		JLabel lblDadosDaFatura = new JLabel("Dados da Fatura:");
		lblDadosDaFatura.setBounds(12, 12, 101, 16);
		getContentPane().add(lblDadosDaFatura);
		
		btnNewButton = new JButton("Gerar Faturas");
		btnNewButton.setIcon(CombatImage.novo_16x16);
		btnNewButton.setBounds(129, 61, 185, 26);
		getContentPane().add(btnNewButton);
		setVisible(true);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				MatriculationModalityDAO matriculationModalityDao;
				MatriculationInvoicesDAO matriculationInvoiceDao;
				PlanDAO planDao;
				MatriculationDAO matriculationDao;
				HomeWindow hw = new HomeWindow();
				float aux_valor = 0;
				
				java.util.Date dat = data.getDate();
				String ddd = Integer.toString(dat.getYear() + 1900) + "-" +
						Integer.toString(dat.getMonth() + 1) + "-";
				System.out.println(ddd);
				try {
					planDao = new PlanDAO(config.getConnection());
					matriculationDao = new MatriculationDAO(config.getConnection());
					matriculationInvoiceDao = new MatriculationInvoicesDAO(config.getConnection());
					matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
					List<MatriculationModality> list_mm = matriculationModalityDao.SelectAllActive();
					if (list_mm.size() == 0) {
						JOptionPane.showMessageDialog(null, "Nenhuma matricula encontrada!");
					}else {
					for(int i=0;i<list_mm.size();i++) {
						
						MatriculationInvoices mi = new MatriculationInvoices();
						MatriculationModality aux_mm = list_mm.get(i);
						MatriculationModality aux_mmm;
						if (i != list_mm.size()-1) {
							aux_mmm = list_mm.get(i+1);
						} else {
							aux_mmm = new MatriculationModality();
							aux_mmm.setMatriculation_code(-1);
						}
						mi.setMatriculation_code(aux_mm.getMatriculation_code());
						Matriculation aux_matriculation = new Matriculation();
						aux_matriculation.setCode(mi.getMatriculation_code());;
						
						Matriculation matriculation = (Matriculation) matriculationDao.Select(aux_matriculation);
						
						Plan aux_plan = new Plan();
						aux_plan.setPlan(aux_mm.getPlan());
						aux_plan.setModality(aux_mm.getModality());
						Plan plan = (Plan) planDao.Select(aux_plan);
						
						aux_valor += plan.getMonth_value();					
						plan.setMonth_value(aux_valor);
						
						mi.setDue_date(ddd + matriculation.getDue_date());
						mi.setPay_date(null);
						
						
						mi.setValue(plan.getMonth_value()); // pegar do plano
						mi.setCancel_date(null);
						System.out.println("Aqui");
						if (aux_mm.getMatriculation_code() != aux_mmm.getMatriculation_code()) {
							matriculationInvoiceDao.Insert(mi);
							JOptionPane.showMessageDialog(null, "Faturas geradas com sucesso!");
							aux_valor = 0;
							
							hw.is_saved = true;
						}else if(aux_mmm == null) {
							matriculationInvoiceDao.Insert(mi);
							JOptionPane.showMessageDialog(null, "Faturas geradas com sucesso!");
							aux_valor = 0;
							
							hw.is_saved = true;
						}
					}
					}
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Faturas ja geradas para esse mes!");
					e.printStackTrace();
				} catch (AccessException e) {
					JOptionPane.showMessageDialog(null, "Faturas ja geradas para esse mes!");
					e.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Faturas ja geradas para esse mes!");
					e.printStackTrace();
				}
				
			}
			
		});
		
		return this;
	}
}
