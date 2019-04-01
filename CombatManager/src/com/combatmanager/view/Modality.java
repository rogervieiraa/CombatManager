package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;

public class Modality extends JPanel implements View{
	public Modality() {
	}
	private JTextField textFieldModality;
	private JTextField textFieldGraduation;
	private JTable table;

	private final String NAME = "Tela Modalidades";
	private final int ACCESS = 0;

	@Override
	public int getAccess() {
		return this.ACCESS;
	}

	@Override
	public String getName() {
		return this.NAME;
	}
	

	/**
	 * @author Romulo Create the frame.
	 */
	
	
	/**
	 * Create the panel.
	 */
	public JPanel run() {
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
		JInternalFrame internalFrame = new JInternalFrame("Modalidades e Gradua\u00E7\u00F5es");
		internalFrame.setFrameIcon(new ImageIcon(Modality.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 450, 344);
	
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
	
		Dimension jInternalFrameSize = internalFrame.getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		internalFrame.setLocation(width, height);
		
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 415, 39);
		toolBar.setFloatable(false);
		internalFrame.getContentPane().add(toolBar);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(Modality.class.getResource("/img22/localizar.png")));
	
		btnSearch.setMaximumSize(new Dimension(98, 80));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar.add(btnSearch);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(Modality.class.getResource("/img22/adicionar.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		btnAdd.setMaximumSize(new Dimension(98, 80));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setIcon(new ImageIcon(Modality.class.getResource("/img22/remover.png")));
		btnRemove.setMaximumSize(new Dimension(98, 80));
		
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(Modality.class.getResource("/img22/salvar.png")));
		btnSave.setMaximumSize(new Dimension(98, 80));
	
		toolBar.add(btnSave);
		
		JLabel lblModality = new JLabel("Modalidade: ");
		lblModality.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModality.setBounds(10, 63, 78, 14);
		internalFrame.getContentPane().add(lblModality);
		
		textFieldModality = new JTextField();
		textFieldModality.setBounds(98, 61, 326, 20);
		internalFrame.getContentPane().add(textFieldModality);
		textFieldModality.setColumns(10);
		
		JLabel lblGraduation = new JLabel("Gradua\u00E7\u00E3o:  ");
		lblGraduation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGraduation.setBounds(10, 100, 78, 14);
		internalFrame.getContentPane().add(lblGraduation);
		
		textFieldGraduation = new JTextField();
		textFieldGraduation.setColumns(10);
		textFieldGraduation.setBounds(98, 98, 266, 20);
		internalFrame.getContentPane().add(textFieldGraduation);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(370, 92, 53, 29);
		internalFrame.getContentPane().add(btnOk);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		JLabel lblWarning = new JLabel("Duplo Clique na linha da gradua\u00E7\u00E3o para remov\u00EA-la");
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWarning.setBounds(10, 289, 414, 14);
		internalFrame.getContentPane().add(lblWarning);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 133, 414, 148);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Faixa Preta"},
				{"Faixa Azul"},
				{"Feixa Amarela"},
			},
			new String[] {
				"Graduacao"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("New label");
		scrollPane.setColumnHeaderView(lblNewLabel);
		return contentPane;
	}
}
