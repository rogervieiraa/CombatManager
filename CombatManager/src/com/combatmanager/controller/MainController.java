package com.combatmanager.controller;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.combatmanager.database.model.User;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;
import com.combatmanager.security.ValidateAcess;
import com.combatmanager.view.MainWindow;

public class MainController {
	
	//login window
	//TO DO
	
	public static void main(String[] args) {
		
		
		
		try {
			//TO DO get user
			User user = new User("Test","Completo","Senha");
			Configuration config = new Configuration(user);
			MainWindow mw = new MainWindow(config);

			if(ValidateAcess.canAccess(config, mw)) {
				mw.setVisible(true);
			}		
			
		} catch (Exception exception) {
			if(exception instanceof AccessException) {
				AccessException acessE = (AccessException) exception; 
				acessE.showAcessWindowDenied();
			}
			else {
				exception.printStackTrace();
				exception.getMessage();
			}
		}
	}


	
}
