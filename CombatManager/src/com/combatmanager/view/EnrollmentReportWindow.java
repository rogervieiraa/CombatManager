package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.combatmanager.security.Configuration;

import controller.CombatImage;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class EnrollmentReportWindow extends JInternalFrame implements View {
	
	private final String NAME = "Tela Relatorio";
	private final int ACCESS = 7*11;
	
	@Override
	public int getAccess() {
		return this.ACCESS;
	}
	
	@Override
	public String getName() {
		return this.NAME;
	}
	
	private JTextField textFieldFrom;
	private JTextField textFieldTo;

	/**
	 * Create the panel.
	 */
	public JInternalFrame run(Configuration config) {
		
		setLayout(null);
		addInternalFrameListener((InternalFrameListener) new InternalFrameAdapter(){
	        public void internalFrameClosing(InternalFrameEvent e) {
	            resetWindow();
	        }
	    });
		setClosable(true);
		setTitle("Relat\\u00F3rio de Matricula");
		setFrameIcon(CombatImage.combatvinte_20x20);
		setBounds(0, 0, 270, 217);
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
		Dimension jInternalFrameSize = getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		setLocation(width, height);
		
		getContentPane().setLayout(null);
		
		JLabel lblPeriod = new JLabel("Perido");
		lblPeriod.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPeriod.setBounds(101, 11, 46, 14);
		getContentPane().add(lblPeriod);
		
		JLabel lblfrom = new JLabel("De:");
		lblfrom.setFont(new Font("Dialog", Font.BOLD, 12));
		lblfrom.setBounds(65, 60, 46, 14);
		getContentPane().add(lblfrom);
		
		JLabel lblTo = new JLabel("At\u00E9:");
		lblTo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTo.setBounds(65, 102, 46, 14);
		getContentPane().add(lblTo);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setBounds(117, 58, 86, 20);
		getContentPane().add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(117, 100, 86, 20);
		getContentPane().add(textFieldTo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"HTML"}));
		comboBox.setBounds(10, 152, 101, 20);
		getContentPane().add(comboBox);
		
		JButton btnProcess = new JButton("Processar");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnProcess.setBounds(120, 151, 124, 23);
		getContentPane().add(btnProcess);
		setVisible(true);
		return this;
	}

	@Override
	public void resetWindow() {
		// TODO Auto-generated method stub
		
	}
}
