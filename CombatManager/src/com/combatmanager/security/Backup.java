package com.combatmanager.security;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import com.sun.jmx.snmp.Timestamp;

public class Backup {
	Configuration config;
	
	public Backup(Configuration config) {
		this.config = config;
	}
	
	
	public void doBackup(String fileUri) {
		config.addToSystemLog("Backup,"+"Iniciou backup.");
		Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
	    try{  
            ProcessBuilder pb;  
            Process p;  
            pb = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\9.2\\bin\\pg_dump.exe ", "-i", "-h", "localhost", "-p", "5432","-U", "admin", "-F", "c", "-b", "-v" ,"-f", fileUri, Long.toString(dataDeHoje.getDateTime()));
            pb.environment().put("PGPASSWORD", "admin");  
            pb.redirectErrorStream(true);  
            p = pb.start();
        }catch(Exception ex){  
            JOptionPane.showMessageDialog(null, "ERRO BACKUP favor contactar o suporte"); 
            config.addToSystemLog("Erro ao realizar backup");
            ex.printStackTrace();
        }
	    
		
		
		config.addToSystemLog("Backup,"+"Finalizou backup com sucesso.");
	}
	
}
