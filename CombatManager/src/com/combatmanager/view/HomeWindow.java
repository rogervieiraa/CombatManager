package com.combatmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.combatmanager.security.Configuration;

public class HomeWindow extends JPanel implements View{

	private final String NAME = "Tela Inicial";
	private final int ACCESS = 3*5*7*11;
	
	@Override
	public int getAccess() {
		return this.ACCESS;
	}
	
	@Override
	public String getName() {
		return this.NAME;
	}
		
	/**
	 * Create the panel.
	 */
	public JPanel run(Configuration config) {
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(MainWindow.class.getResource("/img/combat.png")));
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblImg, BorderLayout.CENTER);
		
		return contentPane;
	}

}
