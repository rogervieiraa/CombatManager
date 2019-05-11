package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.combatmanager.security.Backup;
import com.combatmanager.security.Configuration;

import controller.CombatImage;

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
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class BackupWindow extends JInternalFrame implements View{
	public BackupWindow() {
	}
	private JTextField textFieldLocal;
	private Configuration config;
	private JPanel contentPane;
	
	private final String NAME = "Tela Backup";
	private final int ACCESS = 11;

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
	public JInternalFrame run(Configuration config){
		setLayout(null);
		
		
		setClosable(true);
		setTitle("Fazer Backup");
		setFrameIcon(CombatImage.combatvinte_20x20);
		setBounds(0, 0, 358, 145);
	
		getContentPane().setLayout(null);
		
		JButton btnBackup = new JButton("Backup");
		btnBackup.setIcon(CombatImage.confirmar_16x16);
		btnBackup.setFont(new Font("Dialog", Font.BOLD, 12));
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Backup backup = new Backup(config);
        		if(textFieldLocal.getText() != "") {
            		if(backup.doBackup(textFieldLocal.getText())) {
            			JOptionPane.showMessageDialog(null, "Backup realizado com sucesso!");
            			resetWindow();
            		}
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Favor selecionar local do arquivo.");
        		}
			}
		});
		
		
		btnBackup.setBounds(10, 71, 326, 23);
		getContentPane().add(btnBackup);
		
        
        textFieldLocal = new JTextField();
        textFieldLocal.setBounds(102, 27, 114, 20);
        getContentPane().add(textFieldLocal);
        textFieldLocal.setColumns(10);
        
        JButton btnSearch = new JButton("Procurar");
        btnSearch.setIcon(CombatImage.pasta_16x16);
        btnSearch.setFont(new Font("Dialog", Font.BOLD, 11));
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				String selectedFile = folderChooser();
				if (selectedFile != null) {
					textFieldLocal.setText(selectedFile);
				} else {
					textFieldLocal.setText("");
				}
			}
        });
        btnSearch.setBounds(228, 24, 108, 26);
        getContentPane().add(btnSearch);
        
        JLabel lblSave = new JLabel("Salvar em:");
        lblSave.setFont(new Font("Dialog", Font.BOLD, 12));
        lblSave.setBounds(12, 29, 94, 16);
        getContentPane().add(lblSave);
		setVisible(true);
		
		return this;

	}
	
	public void resetWindow() {
		textFieldLocal.setText("");
	}
	
	public String folderChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Selecione um diretorio");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getPath().toString();
		}

		return null;
	}
	
}
