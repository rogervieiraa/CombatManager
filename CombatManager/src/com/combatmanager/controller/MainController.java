package com.combatmanager.controller;

import java.awt.EventQueue;

import com.combatmanager.database.model.User;
import com.combatmanager.error.AcessException;
import com.combatmanager.security.Configuration;
import com.combatmanager.security.ValidateAcess;
import com.combatmanager.view.MainWindow;

public class MainController {
	
	//login window
	//TO DO
	
	public static void main(String[] args) {
		try {
			//TO DO get user
			User user = new User("Test","Completo");
			Configuration config = new Configuration(user);
			MainWindow mw = new MainWindow(config);

			if(ValidateAcess.acessWindow(config, mw)) {
				mw.setVisible(true);
			}
			
			
			
			
		} catch (Exception exception) {
			if(exception instanceof AcessException) {
				AcessException acessE = (AcessException) exception; 
				acessE.showAcessWindowDenied();
			}
		}
	}


	
}
