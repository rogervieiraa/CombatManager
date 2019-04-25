package com.combatmanager.security;

public class Backup {
	Configuration config;
	
	public Backup(Configuration config) {
		this.config = config;
	}
	
	
	public void doBackup(String fileUri) {
		config.addToSystemLog("Backup,"+"Iniciou backup.");
		
		config.addToSystemLog("Backup,"+"Finalizou backup.");
	}
	
}
