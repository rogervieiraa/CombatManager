package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import com.combatmanager.security.Configuration;

public class PlansWindow extends JPanel implements View{
	private JTextField textFieldPlans;
	private JTextField textFieldPrice;
	
	private final String NAME = "Tela Planos";
	private final int ACCESS = 0;

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
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/localizar.png")));
	
		btnSearch.setMaximumSize(new Dimension(98, 80));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar.add(btnSearch);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/adicionar.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		btnAdd.setMaximumSize(new Dimension(98, 80));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/remover.png")));
		btnRemove.setMaximumSize(new Dimension(98, 80));
		
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/salvar.png")));
		btnSave.setMaximumSize(new Dimension(98, 80));
	
		toolBar.add(btnSave);
		
		JComboBox comboBox = new JComboBox();
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
		
		return contentPane;
	}
}
