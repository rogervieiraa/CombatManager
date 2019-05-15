package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.combatmanager.database.dao.MatriculationDAO;
import com.combatmanager.database.dao.MatriculationInvoicesDAO;
import com.combatmanager.database.dao.StudentDAO;
import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationInvoices;
import com.combatmanager.database.model.Student;
import com.combatmanager.security.Configuration;
import com.combatmanager.util.TableRenderer;

import controller.CombatImage;

import javax.swing.ImageIcon;

public class PayInvoiceWindow extends JInternalFrame implements View {
	private JFormattedTextField textFieldFrom;
	private JFormattedTextField textFieldTo;
	private MaskFormatter maskDate1;
	private MaskFormatter maskDate2;
	private JTable table;
	private JButton btnSearch;
	private JPopupMenu popMenu;
	private JMenuItem menuItempay;
	private JMenuItem menuItemcancel;
	private JMenuItem menuItemdesc;
	private List<MatriculationInvoices> listMi;
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Matricula", "Aluno", "Vencimento", "Valor", "Pagamento", "Cancelamento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		
	private final String NAME = "Tela Pagar Fatura";
	private final int ACCESS = 7*11;
	private HomeWindow hw;
		
	/**
	 * Create the panel.
	 */
	public PayInvoiceWindow(HomeWindow hw) {
		this.hw = hw;
	}
	@Override
	public int getAccess() {
		return this.ACCESS;
	}

	@Override
	public String getName() {
		return this.NAME;
	}
	
	/**
	 * Create the panel.
	 */
	public JInternalFrame run(Configuration config) {
		
		try {
			maskDate1 = new MaskFormatter("##/##/####");
			maskDate2 = new MaskFormatter("##/##/####");
			
			maskDate1.setValidCharacters("0123456789");
			maskDate2.setValidCharacters("0123456789");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		setLayout(null);
		
		
		setTitle("Pagamento de Faturas");
		setClosable(true);
		setFrameIcon(CombatImage.combatvinte_20x20);
		setBounds(0, 0, 600, 575);
	
		getContentPane().setLayout(null);
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
		Dimension jInternalFrameSize = getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		setLocation(width, height);
		
		JLabel lblDe = new JLabel("De: ");
		lblDe.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDe.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDe);
		
		textFieldFrom = new JFormattedTextField(maskDate1);
		textFieldFrom.setBounds(35, 9, 86, 20);
		getContentPane().add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		JLabel lblTo = new JLabel("At\u00E9: ");
		lblTo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTo.setBounds(133, 12, 46, 14);
		getContentPane().add(lblTo);
		
		textFieldTo = new JFormattedTextField(maskDate2);
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(163, 9, 86, 20);
		textFieldTo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == arg0.VK_ENTER)
					btnSearch.doClick();
				
			}
		});
		getContentPane().add(textFieldTo);
		
		JLabel lblStatus = new JLabel("Situa\u00E7\u00E3o: ");
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStatus.setBounds(259, 12, 68, 14);
		getContentPane().add(lblStatus);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Em aberto", "Pagas", "Canceladas"}));
		comboBox.setBounds(325, 9, 100, 20);
		comboBox.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == arg0.VK_ENTER)
					btnSearch.doClick();
				
			}
		});
		getContentPane().add(comboBox);
		
		btnSearch = new JButton("Pesquisar");
		btnSearch.setBounds(435, 4, 145, 31);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				while(model.getRowCount() > 0) {
					model.removeRow(0);
				}
				
				MatriculationInvoices mi = new MatriculationInvoices();
				Matriculation mat = new Matriculation();
				Student student = new Student();
				MatriculationDAO matDao;
				StudentDAO studentDao;
				MatriculationInvoicesDAO miDao;
				
				if ((textFieldFrom.isEditValid()) && (textFieldTo.isEditValid())) {
					if (comboBox.getSelectedIndex() != -1) {
						try {
							miDao = new MatriculationInvoicesDAO(config.getConnection());
							
							listMi = miDao.SelectFilter(textFieldFrom.getText(), textFieldTo.getText(), comboBox.getSelectedItem().toString());
							
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Erro ao pesquisar!");
						}
					}
				}else if(comboBox.getSelectedIndex() != -1) {
					try {
						miDao = new MatriculationInvoicesDAO(config.getConnection());
						
						listMi = miDao.SelectAlll(comboBox.getSelectedItem().toString());
						
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Erro as pesquisar!");
					}
				}
				try {
					matDao = new MatriculationDAO(config.getConnection());
					studentDao = new StudentDAO(config.getConnection());
					
					for(int i=0;i<listMi.size();i++) {
						mat.setCode(listMi.get(i).getMatriculation_code());
						mat = (Matriculation) matDao.Select(mat);
						
						student.setIndex(mat.getStudent_code());
						student = (Student) studentDao.SelectById(student);
						
						model.addRow(new Object[] {listMi.get(i).getMatriculation_code(), student.getName(), listMi.get(i).getDue_date(), listMi.get(i).getValue(), listMi.get(i).getPay_date(), listMi.get(i).getCancel_date()});
					}
				} catch (Exception e2) {
					JOptionPane.showConfirmDialog(null, "Erro ao pesquisar!");
				}
				
			}
		});
		getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 47, 558, 483);
		getContentPane().add(scrollPane);
		
		popMenu = new JPopupMenu();
		menuItempay = new JMenuItem("Pagar Fatura");
		menuItemcancel = new JMenuItem("Cancelar Fatura");
		menuItemdesc = new JMenuItem("Desconto/Acrecimo");
		
		menuItempay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if((model.getValueAt(index, 4) == null) && (model.getValueAt(index, 5) == null)) {
					MatriculationInvoices mi = new MatriculationInvoices();
					MatriculationInvoicesDAO miDao;
					
					mi.setMatriculation_code(Integer.parseInt(model.getValueAt(index, 0).toString()));
					mi.setDue_date(model.getValueAt(index, 2).toString());
					mi.setValue(Float.parseFloat(model.getValueAt(index, 3).toString()));
					mi.setPay_date(getDataAtual().toString());
					mi.setCancel_date(null);
					
					try {
						miDao = new MatriculationInvoicesDAO(config.getConnection());
						miDao.Update(mi, mi);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
					btnSearch.doClick();
					
					hw.is_saved = true;
				}else {
					if (model.getValueAt(index, 5) != null) {
						JOptionPane.showMessageDialog(null, "Fatura cancelada!");
					}else {
						JOptionPane.showMessageDialog(null, "A fatura ja foi paga!");
					}
					
					return;
				}
				
			}
		});
		menuItemcancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if((model.getValueAt(index, 5) == null) && (model.getValueAt(index, 4) == null)) {
					MatriculationInvoices mi = new MatriculationInvoices();
					MatriculationInvoicesDAO miDao;

					
					mi.setMatriculation_code(Integer.parseInt(model.getValueAt(index, 0).toString()));
					mi.setDue_date(model.getValueAt(index, 2).toString());
					mi.setValue(Float.parseFloat(model.getValueAt(index, 3).toString()));
					mi.setPay_date(null);
					mi.setCancel_date(getDataAtual().toString());
					
					System.out.println(mi.toString());
					
					try {
						miDao = new MatriculationInvoicesDAO(config.getConnection());
						miDao.Update(mi, mi);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
					btnSearch.doClick();
					
					hw.is_saved = true;
				}else {
					if (model.getValueAt(index, 5) != null) {
						JOptionPane.showMessageDialog(null, "Fatura ja cancelada!");
					}else {
						JOptionPane.showMessageDialog(null, "A fatura ja foi paga!");
					}
					return;
				}
				
			}
		});
		menuItemdesc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = table.getSelectedRow();
				Float nValor = Float.parseFloat(JOptionPane.showInputDialog("Informe o novo valor:"));
				MatriculationInvoices mi = new MatriculationInvoices();
				MatriculationInvoicesDAO miDao;
				
				mi.setMatriculation_code(Integer.parseInt(model.getValueAt(index, 0).toString()));
				mi.setDue_date(model.getValueAt(index, 2).toString());
				mi.setValue(nValor);
				
				try {
					miDao = new MatriculationInvoicesDAO(config.getConnection());
					
					miDao.Update(mi, mi);
				} catch (Exception e) {
					e.printStackTrace();
				}
				btnSearch.doClick();
				
				hw.is_saved = true;
			}
		});
		
		popMenu.add(menuItempay);
		popMenu.add(menuItemcancel);
		popMenu.add(menuItemdesc);
		
		
		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.setDefaultRenderer(Object.class, new TableRenderer(config));
		table.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					if (e.isPopupTrigger()) {
			            popMenu.show(e.getComponent(),
			                       e.getX(), e.getY());
			        }
				}
				
		    }
			public void mouseReleased(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					if (e.isPopupTrigger()) {
			            popMenu.show(e.getComponent(),
			                       e.getX(), e.getY());
			        }
				}
		    }
			
		});
		scrollPane.setViewportView(table);
		setVisible(true);
		
		return this;
	}
	private  LocalDate getDataAtual(){
		LocalDate ld = LocalDate.now();
		
		return ld;
	}
}
