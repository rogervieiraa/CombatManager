package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;

public class ChooseWindow extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChooseWindow() {
		setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Localizar Arquivo");
		internalFrame.setResizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setClosable(true);
		internalFrame.setFrameIcon(new ImageIcon(ChooseWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 482, 361);
		add(internalFrame);
		internalFrame.setVisible(true);
		
		JFileChooser file = new JFileChooser(); 
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i= file.showSaveDialog(null);
        internalFrame.getContentPane().add(file);

	}

}
