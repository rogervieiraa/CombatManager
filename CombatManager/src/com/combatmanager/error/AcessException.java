package com.combatmanager.error;

import javax.swing.JOptionPane;

public class AcessException extends Exception {
	
	
	public void showAcessWindowDenied() {
		JOptionPane.showMessageDialog(null, "Voce nao tem acesso a essa tela!");
	}
	
	public void showButtonActivatedDenied() {
		JOptionPane.showMessageDialog(null, "Voce nao tem permissao para utilizar esse botao!");
	}
	
}
