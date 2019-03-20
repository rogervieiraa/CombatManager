package com.combatmanager.view;

import javax.swing.JPanel;

public interface View {
	public abstract int getAccess();
	public abstract String getName();
	public abstract JPanel run();
}