package com.combatmanager.view;

import javax.swing.JPanel;

import com.combatmanager.util.MasterMonthChooser;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GenerateBillWindow extends JPanel {

	MasterMonthChooser data;
	/**
	 * Create the panel.
	 */
	public GenerateBillWindow() {
		setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Gerar Faturas");
		internalFrame.setClosable(true);
		internalFrame.setFrameIcon(new ImageIcon(GenerateBillWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 336, 132);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		data = new MasterMonthChooser();
		data.setBounds(129, 12, 185, 26);
		internalFrame.getContentPane().add(data);
		
		JLabel lblDadosDaFatura = new JLabel("Dados da Fatura:");
		lblDadosDaFatura.setBounds(12, 12, 101, 16);
		internalFrame.getContentPane().add(lblDadosDaFatura);
		
		JButton btnNewButton = new JButton("Gerar Faturas");
		btnNewButton.setIcon(new ImageIcon(GenerateBillWindow.class.getResource("/img16/novo.png")));
		btnNewButton.setBounds(129, 61, 185, 26);
		internalFrame.getContentPane().add(btnNewButton);
		internalFrame.setVisible(true);

	}
}
