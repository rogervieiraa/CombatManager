package com.combatmanager.controller;

import java.awt.EventQueue;

import com.combatmanager.configuration.Configuration;
import com.combatmanager.database.model.User;
import com.combatmanager.view.MainWindow;

public class MainController {
	
	//login window
	//TO DO
	
	public static void main(String[] args) {
		try {
			//TO DO get user
			MainWindow frame = new MainWindow(new Configuration(new User("Test","Completo")));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}
