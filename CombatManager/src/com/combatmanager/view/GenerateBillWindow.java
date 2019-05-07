package com.combatmanager.view;

import javax.swing.JPanel;

import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;
import com.combatmanager.util.MasterMonthChooser;

import controller.CombatImage;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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

public class GenerateBillWindow extends JPanel implements View {

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
	public JPanel run(Configuration config) {
		this.config = config;
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		JInternalFrame internalFrame = new JInternalFrame("Gerar Faturas");
		internalFrame.setClosable(true);
		internalFrame.setFrameIcon(CombatImage.combatvinte_20x20);
		internalFrame.setBounds(0, 0, 336, 132);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		data = new MasterMonthChooser();
		data.setBounds(129, 12, 185, 26);
		internalFrame.getContentPane().add(data);
		
		JLabel lblDadosDaFatura = new JLabel("Dados da Fatura:");
		lblDadosDaFatura.setBounds(12, 12, 101, 16);
		internalFrame.getContentPane().add(lblDadosDaFatura);
		
		btnNewButton = new JButton("Gerar Faturas");
		btnNewButton.setIcon(CombatImage.novo_16x16);
		btnNewButton.setBounds(129, 61, 185, 26);
		internalFrame.getContentPane().add(btnNewButton);
		internalFrame.setVisible(true);
		contentPane.add(internalFrame);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				MatriculationModalityDAO matriculationModalityDao;
				
				try {
					matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
					List<MatriculationModality> list_mm = matriculationModalityDao.SelectAllActive();
					for(int i=0;i<list_mm.size();i++) {
						System.out.println(list_mm.get(i).toString());
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		return contentPane;
	}
}
