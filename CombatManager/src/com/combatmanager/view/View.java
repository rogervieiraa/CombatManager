package com.combatmanager.view;

import javax.swing.JPanel;

import com.combatmanager.security.Configuration;

public interface View {
	public abstract int getAccess();
	public abstract String getName();
	public abstract JPanel run(Configuration config);
}