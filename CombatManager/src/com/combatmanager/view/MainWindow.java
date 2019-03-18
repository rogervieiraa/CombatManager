package com.combatmanager.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.combatmanager.configuration.Configuration;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class MainWindow extends JFrame {
	private Configuration config;
	private JPanel contentPane;
	
	/**
	 * @author Romulo
	 * Create the frame.
	 */
	public MainWindow(Configuration config) {
		this.config = config; //TO DO
		setTitle("Combat Manager 1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/combat.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmUsuarios = new JMenuItem("Usu\u00E1rios");
		mntmUsuarios.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		mnSistema.add(mntmUsuarios);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mnSistema.add(mntmSair);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenuItem mntmAlunos = new JMenuItem("Alunos");
		mntmAlunos.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		mnCadastros.add(mntmAlunos);
		
		JMenuItem mntmModalidades = new JMenuItem("Modalidades");
		mntmModalidades.setIcon(new ImageIcon(MainWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		mnCadastros.add(mntmModalidades);
		
		JMenuItem mntmPlanos = new JMenuItem("Planos");
		mntmPlanos.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		mnCadastros.add(mntmPlanos);
		
		JMenu mnProcessos = new JMenu("Processos");
		menuBar.add(mnProcessos);
		
		JMenu mnMatricular = new JMenu("Matricular");
		mnMatricular.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		mnMatricular.setHorizontalAlignment(SwingConstants.LEFT);
		mnProcessos.add(mnMatricular);
		
		JMenuItem mntmAluno = new JMenuItem("Aluno");
		mnMatricular.add(mntmAluno);
		
		JMenu mnFaturamento = new JMenu("Faturamento");
		mnFaturamento.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		mnProcessos.add(mnFaturamento);
		
		JMenuItem mntmGerarFaturas = new JMenuItem("Gerar Faturas");
		mnFaturamento.add(mntmGerarFaturas);
		
		JMenuItem mntmConsultarFaturas = new JMenuItem("Consultar Faturas");
		mnFaturamento.add(mntmConsultarFaturas);
		
		JMenuItem mntmRealizarPagamento = new JMenuItem("Realizar Pagamento");
		mntmRealizarPagamento.setSelected(true);
		mnFaturamento.add(mntmRealizarPagamento);
		
		JMenu mnRelatorios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatorios);
		
		JMenuItem mntmMtrcula = new JMenuItem("Matr\u00EDcula");
		mnRelatorios.add(mntmMtrcula);
		
		JMenu mnFaturas = new JMenu("Faturas");
		mnRelatorios.add(mnFaturas);
		
		JMenu mnUtilitarios = new JMenu("Utilit\u00E1rios");
		menuBar.add(mnUtilitarios);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(MainWindow.class.getResource("/img/combat.png")));
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblImg, BorderLayout.CENTER);
	}
	
}
