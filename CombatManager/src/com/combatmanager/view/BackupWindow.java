package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.combatmanager.security.Configuration;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;

import java.awt.Container;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BackupWindow extends JPanel implements View{
	public BackupWindow() {
	}
	private JTextField textFieldLocal;
	private Configuration config;
	private JPanel contentPane;
	
	private final String NAME = "Tela Backup";
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
	 * Create the panel.
	 */
	public JPanel run(Configuration config){
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Fazer Backup");
		internalFrame.setClosable(true);
		internalFrame.setFrameIcon(new ImageIcon(BackupWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 358, 145);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JButton btnBackup = new JButton("Backup");
		btnBackup.setIcon(new ImageIcon(BackupWindow.class.getResource("/img16/confirmar.png")));
		btnBackup.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		btnBackup.setBounds(10, 71, 326, 23);
		internalFrame.getContentPane().add(btnBackup);
		
        
        textFieldLocal = new JTextField();
        textFieldLocal.setBounds(102, 27, 114, 20);
        internalFrame.getContentPane().add(textFieldLocal);
        textFieldLocal.setColumns(10);
        
        JButton btnSearch = new JButton("Procurar");
        btnSearch.setIcon(new ImageIcon(BackupWindow.class.getResource("/img16/pasta.png")));
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        btnSearch.setBounds(228, 24, 108, 26);
        internalFrame.getContentPane().add(btnSearch);
        
        JLabel lblSave = new JLabel("Salvar em:");
        lblSave.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSave.setBounds(12, 29, 94, 16);
        internalFrame.getContentPane().add(lblSave);
		internalFrame.setVisible(true);
		
		return contentPane;

	}
	
}
