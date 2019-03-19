package com.combatmanager.error;

import javax.swing.JOptionPane;

public class AcessException extends Exception {
	private String origin;
	private String description;
	public AcessException(String origin, String description) {
		this.origin = origin;
		this.description = description;
	}
	
	public void showAcessWindowDenied() {
		JOptionPane.showMessageDialog(null, this.origin + " ->" + this.description);
	}
	
}
