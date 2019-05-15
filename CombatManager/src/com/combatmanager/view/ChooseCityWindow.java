package com.combatmanager.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.combatmanager.database.dao.CityDAO;
import com.combatmanager.database.model.City;
import com.combatmanager.database.model.Student;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

public class ChooseCityWindow extends JFrame implements View {
	private final int ACESS = 3*11;
	private final String NAME = "Escolher cidade";
	private City selectedCity;
	private JTextField textFieldName;
	private JTable table;
	private Configuration config;
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cidade", "Estado", "Pais"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

	/**
	 * Create the panel.
	 */
	public ChooseCityWindow(Configuration config) {
		setLayout(null);
		
		this.config = config;
		setBounds(0, 0, 450, 350);
		JInternalFrame internalFrame = new JInternalFrame("Escolher Cidade");
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setClosable(true);
		internalFrame.setBounds(0, 0, 450, 300);
		add(internalFrame);
		
		
		textFieldName = new JTextField();
		textFieldName.setBounds(72, 12, 230, 20);
		internalFrame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblCity = new JLabel("Cidade:\r\n");
		lblCity.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCity.setBounds(12, 14, 55, 16);
		internalFrame.getContentPane().add(lblCity);
		
		JButton btnSearch = new JButton("Procurar");
		btnSearch.setBounds(314, 9, 114, 26);
		
		internalFrame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 416, 173);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setMinWidth(10);
		scrollPane.setViewportView(table);
		
		JButton btnSelect = new JButton("Selecionar");
		btnSelect.setBounds(12, 228, 416, 26);
		
btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CityDAO cityDao = null;
				try {
					if(textFieldName.getText().equals("")) {
						//TODO printa erro
					}
					City aux_city = new City();
					aux_city.setName(textFieldName.getText());
					cityDao = new CityDAO(config.getConnection());
					List<City> listCity = cityDao.SelectByCityName(aux_city);
					for(int i=0;i<listCity.size();i++) {
						City local_city =  listCity.get(i);
						model.addRow(new Object[] {local_city.getName(), local_city.getState(), local_city.getCountry()});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (AccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		btnSelect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(table.getSelectedRow() == -1) {
                	JOptionPane.showMessageDialog(null, "Favor escolher aluno");
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                selectedCity = new City();
                selectedCity.setName((String) model.getValueAt(table.getSelectedRow(), 0));
                selectedCity.setState((String) model.getValueAt(table.getSelectedRow(), 1));
                selectedCity.setCountry((String) model.getValueAt(table.getSelectedRow(), 2));
                
                dispatchEvent(new WindowEvent(getFrames()[0], WindowEvent.WINDOW_CLOSING));
            }
        });
		internalFrame.getContentPane().add(btnSelect);
		internalFrame.setVisible(true);

	}

	@Override
	public int getAccess() {
		return ACESS;
	}

	@Override
	public JInternalFrame run(Configuration config) {
		this.config = config;
		return null;
	}

	public City getCity() {
		return selectedCity;
	}

	@Override
	public void resetWindow() {
		// TODO Auto-generated method stub
		
	}
	
}
