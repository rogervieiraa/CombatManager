package com.combatmanager.security;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.swing.JOptionPane;


public class Backup {
	Configuration config;
	
	public Backup(Configuration config) {
		this.config = config;
	}
	
	
	public Boolean doBackup(String fileUri) {
		config.addToSystemLog("Backup,"+"Iniciou backup.");
		Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
	    try{  
            ProcessBuilder pb;  
            Process p;
            pb = new ProcessBuilder("/usr/bin/pg_dump", "-h", "localhost", "-p", 
            		"5432","-U", "admin", "-F", "c", "-b", "-v" ,"-f", 
            		fileUri + "/"+ Long.toString(dataDeHoje.getTime()) + ".backup");
            pb.environment().put("PGPASSWORD", "admin");  
            pb.redirectErrorStream(true);  
            p = pb.start();
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String ll;
            while ((ll = br.readLine()) != null) { 
            	config.addToSystemLog("Backup,"+ll);
            }
            
            config.addToSystemLog("Backup,"+"Finalizou backup com sucesso.");
            return true;
        }catch(Exception ex){  
            JOptionPane.showMessageDialog(null, "ERRO BACKUP favor contactar o suporte"); 
            config.addToSystemLog("Erro ao realizar backup");
            ex.printStackTrace();
            return false;
        }
	    
	}
	
}
