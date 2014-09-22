package Cars.Window;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;











import Cars.MySQL.MySQL_Cars;
import PriceList.MySQL.MySQL_PriceList;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCars extends JDialog {
	
	private JTextField tNazwa;
	private JTextField tNrRej;
	private MySQL_Cars qtm;
	private JPanel panel;
	private String carsId;
	JComboBox<String> comboBox;
	
	public FormCars() {

		panel = new JPanel();
		
		setSize(345, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
		
		qtm = new MySQL_Cars();	
					
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panel.columnWidths = new int[]{0, 20, 0};
		panel.setLayout(gbl_panel);
		JLabel lbNazwa = new JLabel("Nazwa");
		
		
		GridBagConstraints gbc_lbNazwa = new GridBagConstraints();
		gbc_lbNazwa.anchor = GridBagConstraints.WEST;
		gbc_lbNazwa.insets = new Insets(0, 0, 5,5);
		gbc_lbNazwa.gridx = 0;
		gbc_lbNazwa.gridy = 0;	
		panel.add(lbNazwa, gbc_lbNazwa);
		
		tNazwa = new JTextField();
		tNazwa.setColumns(20);
		GridBagConstraints gbc_tNazwa = new GridBagConstraints();
		gbc_tNazwa.fill = GridBagConstraints.HORIZONTAL;
		gbc_tNazwa.insets = new Insets(0, 0, 5, 5);
		gbc_tNazwa.gridx = 1;
		gbc_tNazwa.gridy = 0;
		panel.add(tNazwa, gbc_tNazwa);
		
		JLabel lbNrRej = new JLabel("Numer Rejestracyjny");
		GridBagConstraints gbc_lbNrRej = new GridBagConstraints();
		gbc_lbNrRej.anchor = GridBagConstraints.WEST;
		gbc_lbNrRej.insets = new Insets(0, 0, 5, 5);
		gbc_lbNrRej.gridx = 0;
		gbc_lbNrRej.gridy = 1;
		panel.add(lbNrRej, gbc_lbNrRej);
		
		tNrRej = new JTextField();
		GridBagConstraints gbc_tNrRej = new GridBagConstraints();
		gbc_tNrRej.fill = GridBagConstraints.HORIZONTAL;
		gbc_tNrRej.insets = new Insets(0, 0, 5, 5);
		gbc_tNrRej.gridx = 1;
		gbc_tNrRej.gridy = 1;
		panel.add(tNrRej, gbc_tNrRej);

		JLabel lbPriceList = new JLabel("Cennik");
		GridBagConstraints gbc_lbPriceList = new GridBagConstraints();
		gbc_lbPriceList.anchor = GridBagConstraints.WEST;
		gbc_lbPriceList.insets = new Insets(0, 0, 5, 5);
		gbc_lbPriceList.gridx = 0;
		gbc_lbPriceList.gridy = 2;
		panel.add(lbPriceList, gbc_lbPriceList);

		comboBox = new JComboBox<String>(new MySQL_PriceList().getPriceList());
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		
		getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	public void addCar(){
		setTitle("Dodaj samochód");
		
		JButton btnAdd = new JButton("Dodaj");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 5;
		panel.add(btnAdd, gbc_btnAdd);
	
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String params[]={tNazwa.getText(),tNrRej.getText(),new MySQL_PriceList().getPriceListId(String.valueOf(comboBox.getSelectedItem()))};
				qtm.setCars(params);
				dispose();
			}
		});

		setVisible(true);
	}

	public void editCar(String[] params, String pricelist_id){
		setTitle("Edytuj samochód");
		
		carsId=qtm.getCarsId(params);
		
		JButton btnAdd = new JButton("Zmieñ");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 5;
		panel.add(btnAdd, gbc_btnAdd);
		
		tNazwa.setText(params[0]);
		tNrRej.setText(params[1]);
		
		String tmp="";
		
		tmp=new MySQL_PriceList().getPriceListName(pricelist_id);
		comboBox.setSelectedItem(tmp);
		
	    btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String params[]={
						carsId,
						tNazwa.getText(),
						tNrRej.getText(),
						new MySQL_PriceList().getPriceListId(String.valueOf(comboBox.getSelectedItem()))
						};
				qtm.editCars(params);
				dispose();
			}
		});
		
		setVisible(true);
	}
}
