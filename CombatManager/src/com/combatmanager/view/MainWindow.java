package com.combatmanager.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;

import com.combatmanager.security.Configuration;

import controller.CombatImage;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;

public class MainWindow extends JFrame implements View {
	private Configuration config;
	private JPanel contentPane;

	private final String NAME = "Tela Principal";
	private final int ACCESS = 3 * 5 * 7 * 11;
	private JInternalFrame[] frames= new JInternalFrame[100];
	private HomeWindow hw;
	
	@Override
	public int getAccess() {
		return this.ACCESS;
	}

	@Override
	public String getName() {
		return this.NAME;
	}
	
	public void resetWindow() {
		
	}

	/**
	 * @author Romulo Create the frame.
	 */
	public MainWindow(Configuration config) {
		
		contentPane= new JPanel();
		
		contentPane.setLayout(null);
		
		hw = new HomeWindow(contentPane);
		hw.run(config);
		contentPane.add(hw);
		
		frames[0]= hw;
		
		this.config = config; // TO DO
		setTitle("Combat Manager 1.0");
		setIconImage(CombatImage.combatvinte_20x20.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setLayout(null);

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSystem = new JMenu("Sistema");
		menuBar.add(mnSystem);

		JMenuItem mntmUsers = new JMenuItem( new AbstractAction("Usuarios") {
		
			public void actionPerformed(ActionEvent e)
			{
			
				UsersWindow users = new UsersWindow();
				users.run(config);
				openWindow(users);
	            
		
	
		
			}
				
		});
		mntmUsers.setIcon(CombatImage.aplicacao_16x16);
		
		mnSystem.add(mntmUsers);

		JMenuItem mntmExit = new JMenuItem( new AbstractAction("Sair") {
			
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
				
		});
		mntmExit.setIcon(CombatImage.sair_16x16); //COMO USAR AS IMAGENS
		mnSystem.add(mntmExit);

		JMenu mnRegisted = new JMenu("Cadastros");
		menuBar.add(mnRegisted);
		if(new StudentsWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmStudents = new JMenuItem(new AbstractAction("Alunos"){
				
				public void actionPerformed(ActionEvent e)
				{
					
					StudentsWindow students = new StudentsWindow();		
					students.run(config);
					openWindow(students);

					
				}
					
			});
			mntmStudents.setIcon(CombatImage.aplicacao_16x16);
			mnRegisted.add(mntmStudents);
		}
		
		
		if(new ModalityWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmModalities = new JMenuItem(new AbstractAction("Modalidades"){
				
				public void actionPerformed(ActionEvent e)
				{
					ModalityWindow modality = new ModalityWindow();
					
					modality.run(config);
					openWindow(modality);
					
				}
					
			});
			mntmModalities.setIcon(CombatImage.aplicacao_16x16);
			mnRegisted.add(mntmModalities);
		}
		if(new PlansWindow(hw).getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmPlans = new JMenuItem(new AbstractAction("Planos"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					PlansWindow plans = new PlansWindow(hw);
					
					plans.run(config);
					openWindow(plans);
		            
				}
					
			});
			
			mntmPlans.setIcon(CombatImage.aplicacao_16x16);
			mnRegisted.add(mntmPlans);
		}
		JMenu mnProcesses = new JMenu("Processos");
		menuBar.add(mnProcesses);

		;
		JMenu mnRegister = new JMenu("Matricular");
		mnRegister.setIcon(CombatImage.aplicacao_16x16);
		mnRegister.setHorizontalAlignment(SwingConstants.LEFT);
		mnProcesses.add(mnRegister);
		if(new RegisterStudentWindow(hw).getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmStudent = new JMenuItem(new AbstractAction("Aluno"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					RegisterStudentWindow registerStudent = new RegisterStudentWindow(hw);			
					registerStudent.run(config);
					openWindow(registerStudent);
				}
					
			});
			mnRegister.add(mntmStudent);
		}
		
		JMenu mnBilling = new JMenu("Faturamento");
		mnBilling.setIcon(CombatImage.aplicacao_16x16);
		mnProcesses.add(mnBilling);
		if(new GenerateBillWindow(hw).getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmGenerateBill = new JMenuItem(new AbstractAction("Gerar fatura"){
				public void actionPerformed(ActionEvent e)
				{
					
					GenerateBillWindow generate = new GenerateBillWindow(hw);
					
					generate.run(config);
					openWindow(generate);
				
				}
				
			});
			mnBilling.add(mntmGenerateBill);
		}
		
		
		if(new CheckInvoiceWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmCheckBills = new JMenuItem(new AbstractAction("Consultar Faturas"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					CheckInvoiceWindow checkInvoice = new CheckInvoiceWindow();
					
					checkInvoice.run(config);
					
					openWindow(checkInvoice);
				}
					
			});
			mnBilling.add(mntmCheckBills);
		}
		
		if(new PayInvoiceWindow(hw).getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmMakePayment = new JMenuItem(new AbstractAction("Pagamento de Faturas"){
				
				public void actionPerformed(ActionEvent e)
				{
				
					PayInvoiceWindow payInvoice = new PayInvoiceWindow(hw);
					
					payInvoice.run(config);
					
					openWindow(payInvoice);
					
				}
					
			});
			
			mntmMakePayment.setSelected(true);
			mnBilling.add(mntmMakePayment);
		}
		

		JMenu mnReports = new JMenu("Relat\u00F3rios");
		menuBar.add(mnReports);
		if(new EnrollmentReportWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmMtrcula = new JMenuItem(new AbstractAction("Matricula"){
				public void actionPerformed(ActionEvent e)
				{
				
					EnrollmentReportWindow report = new EnrollmentReportWindow();
					
					report.run(config);
					openWindow(report);
				}
				});
			mnReports.add(mntmMtrcula);
		}
		

		JMenu mnBills = new JMenu("Faturas");
		mnReports.add(mnBills);

		JMenu mnUtilities = new JMenu("Utilit\u00E1rios");
		if(new BackupWindow().getAccess()%config.getPermissionValue() == 0) {
			JMenuItem mntmBackup = new JMenuItem(new AbstractAction("Backup"){
				public void actionPerformed(ActionEvent e)
				{
					
					BackupWindow backup = new BackupWindow();
					backup.run(config);
					openWindow(backup);
				}
				});
			mnUtilities.add(mntmBackup);
		}
		
		
		menuBar.add(mnUtilities);
		

		JMenu mnHelp = new JMenu("Ajuda");
		menuBar.add(mnHelp);
		
		
		
		this.addWindowListener((WindowListener) new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				config.printLog();
				System.exit(0);
					
			}
		});

		
		setContentPane(contentPane);	
	}
	
	public void openWindow(JInternalFrame window) {
		int i = 0;
		int c= 0;
		boolean exist= false;
		
		
		while(i < contentPane.getComponents().length) {
			
			
			if(window.getName() == contentPane.getComponent(i).getName()) {
				try {
					window.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				exist= true;
				c= i;
				i = contentPane.getComponents().length;
			
			}else {
				exist= false;
			}
			i++;
			
		}
		
		if(contentPane.getComponent(c).isVisible() == false && exist == true){
			
			
			
			contentPane.getComponent(c).setVisible(true);
			
	
			try {
				window.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			validate();
	        repaint();
	        return;
			
		}
		
		
		if(exist == false) {

			frames[i]= window;
			contentPane.add(frames[i]);
		
			contentPane.setComponentZOrder(frames[i], 0);
			
			int z= 0;
			while(i < 0) {
				contentPane.setComponentZOrder(frames[i], z);
				contentPane.setComponentZOrder(frames[i-1], z+1);
				i--;
				z++;
			}
			
			int j = 0;
			
			while(j < i) {
				
				try {
					frames[j].setSelected(false);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				j++;
			}
			
			try {
				window.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			validate();
	        repaint();
		}
		
        
		return;

	}
	
	

	@Override
	public JInternalFrame run(Configuration config) {
		return null;
	}

}
