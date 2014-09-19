package window.dictionary;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MySQL.MySQL_PriceList;

import java.util.Vector;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FormPriceList extends JDialog {
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textPriceInw;
	private JTextField textPriceKm;
	private JTextField textPriceIdle;
	
	private JPanel panel;
	private JPanel panel_1;
	
	public FormPriceList() {
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setAlwaysOnTop(true);
		setSize(290, 215);
		setLocationRelativeTo(null);

		
		setModalityType(ModalityType.APPLICATION_MODAL);

		
		panel = new JPanel();
		panel_1 = new JPanel();
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		
		getContentPane().add(panel);
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
				
						JLabel lblName = new JLabel("Nazwa");
						
						GridBagConstraints gbc_lblName = new GridBagConstraints();
						gbc_lblName.anchor = GridBagConstraints.WEST;
						gbc_lblName.insets = new Insets(0, 0, 5, 5);
						gbc_lblName.gridx = 0;
						gbc_lblName.gridy = 0;
						panel.add(lblName, gbc_lblName);
		
				textName = new JTextField();		
				textName.setColumns(15);
				GridBagConstraints gbc_textName = new GridBagConstraints();
				gbc_textName.anchor = GridBagConstraints.NORTHWEST;
				gbc_textName.insets = new Insets(0, 0, 5, 0);
				gbc_textName.gridx = 1;
				gbc_textName.gridy = 0;
				panel.add(textName, gbc_textName);
		JLabel lblPrice = new JLabel("Stawka za godzine");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice.gridwidth = 2;
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 1;
		panel.add(lblPrice, gbc_lblPrice);
		textPrice = new JTextField();		
		textPrice.setColumns(15);
		GridBagConstraints gbc_textPrice = new GridBagConstraints();
		gbc_textPrice.anchor = GridBagConstraints.NORTHWEST;
		gbc_textPrice.insets = new Insets(0, 0, 5, 0);
		gbc_textPrice.gridx = 1;
		gbc_textPrice.gridy = 1;
		panel.add(textPrice, gbc_textPrice);
		JLabel lblPriceInw = new JLabel("Stawka za godzine inw");		
		GridBagConstraints gbc_lblPriceInw = new GridBagConstraints();
		gbc_lblPriceInw.anchor = GridBagConstraints.WEST;
		gbc_lblPriceInw.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriceInw.gridx = 0;
		gbc_lblPriceInw.gridy = 2;
		panel.add(lblPriceInw, gbc_lblPriceInw);
		textPriceInw = new JTextField();		
		textPriceInw.setColumns(15);
		GridBagConstraints gbc_textPriceInw = new GridBagConstraints();
		gbc_textPriceInw.anchor = GridBagConstraints.NORTHWEST;
		gbc_textPriceInw.insets = new Insets(0, 0, 5, 0);
		gbc_textPriceInw.gridx = 1;
		gbc_textPriceInw.gridy = 2;
		panel.add(textPriceInw, gbc_textPriceInw);
		JLabel lblPriceKm = new JLabel("Stawka za km");
		GridBagConstraints gbc_lblPriceKm = new GridBagConstraints();
		gbc_lblPriceKm.anchor = GridBagConstraints.WEST;
		gbc_lblPriceKm.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriceKm.gridx = 0;
		gbc_lblPriceKm.gridy = 3;
		panel.add(lblPriceKm, gbc_lblPriceKm);
		textPriceKm = new JTextField();		
		textPriceKm.setColumns(15);
		GridBagConstraints gbc_textPriceKm = new GridBagConstraints();
		gbc_textPriceKm.anchor = GridBagConstraints.NORTHWEST;
		gbc_textPriceKm.insets = new Insets(0, 0, 5, 0);
		gbc_textPriceKm.gridx = 1;
		gbc_textPriceKm.gridy = 3;
		panel.add(textPriceKm, gbc_textPriceKm);
		JLabel lblPriceIdle = new JLabel("Stawka za post\u00F3j");
		GridBagConstraints gbc_lblPriceIdle = new GridBagConstraints();
		gbc_lblPriceIdle.anchor = GridBagConstraints.WEST;
		gbc_lblPriceIdle.insets = new Insets(0, 0, 0, 5);
		gbc_lblPriceIdle.gridx = 0;
		gbc_lblPriceIdle.gridy = 4;
		panel.add(lblPriceIdle, gbc_lblPriceIdle);
		textPriceIdle = new JTextField();
		textPriceIdle.setColumns(15);
		GridBagConstraints gbc_textPriceIdle = new GridBagConstraints();
		gbc_textPriceIdle.anchor = GridBagConstraints.NORTHWEST;
		gbc_textPriceIdle.gridx = 1;
		gbc_textPriceIdle.gridy = 4;
		panel.add(textPriceIdle, gbc_textPriceIdle);
		
		JButton btnBack = new JButton("Anuluj");
		panel_1.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}
	
	public void addPrice(){
		setTitle("Dodaj cennik");
		
		JButton btnAdd=new JButton("Dodaj");
		
		panel_1.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MySQL_PriceList qtm= new MySQL_PriceList();
				
				String[] buttons={	textName.getText(),
									textPrice.getText(),
									textPriceInw.getText(),
									textPriceKm.getText(),
									textPriceIdle.getText()};
				qtm.setPriceList(buttons);
				dispose();
			}
		});		
		
		setVisible(true);
	}
	
	public void editPrice(Vector<String> row){
		setTitle("Edytuj pozycje");
		
		JButton btnEdit=new JButton("Zmieñ");

		panel_1.add(btnEdit);
			
		textName.setText(row.get(0));
		textPrice.setText(row.get(1));
		textPriceInw.setText(row.get(2));
		textPriceKm.setText(row.get(3));
		textPriceIdle.setText(row.get(4));

		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MySQL_PriceList qtm= new MySQL_PriceList();
				
				row.set(0, textName.getText());
				row.set(1, textPrice.getText());
				row.set(2, textPriceInw.getText());
				row.set(3, textPriceKm.getText());
				row.set(4, textPriceIdle.getText());
				
				
				qtm.editPriceList(row);
				
				
				dispose();
			}
		});
		
		setVisible(true);
	}


}
