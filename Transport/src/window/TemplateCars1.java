package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable.PrintMode;

import MySQL.MySQL_Cars;
import javax.swing.BoxLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class TemplateCars1 extends JFrame {
	
	JLabel lName, lNrRej, lPrice, lPriceInw, lPrice50, lPrice100, lPrice200, lPriceKm;
	
	JTextField tName, tNrRej, tPrice, tPriceInw, tPrice50, tPrice100, tPrice200, tPriceKm;	
	JPanel panel;
	private JLabel lblCennik;
	private JLabel label;
	
	public TemplateCars1() {
		// TODO Auto-generated constructor stub
	super("Dodaj");
	
    lName=new JLabel("Nazwa");
    lNrRej=new JLabel("Numer rejestracyjny");

	
	tName=new JTextField();
	tName.setColumns(20);
	tNrRej=new JTextField();

	
	
	panel=new JPanel();
	panel.setLayout(new GridBagLayout());
	
	panel.add(lName, new MyGrid(0, 0));
	panel.add(tName, new MyGrid(1, 0));
	panel.add(lNrRej, new MyGrid(0, 1));
	panel.add(tNrRej, new MyGrid(1, 1));
	
	setSize(400, 400);
	getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
	getContentPane().add(panel);
			
			lblCennik = new JLabel("Cennik");
			GridBagConstraints gbc_lblCennik = new GridBagConstraints();
			gbc_lblCennik.insets = new Insets(0, 0, 5, 5);
			gbc_lblCennik.gridx = 0;
			gbc_lblCennik.gridy = 2;
			panel.add(lblCennik, gbc_lblCennik);
				
				label = new JLabel("Cennik");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 3;
				panel.add(label, gbc_label);
				
					
					
					JButton button=new JButton("Dodaj");
					GridBagConstraints gbc_button = new GridBagConstraints();
					gbc_button.insets = new Insets(0, 0, 0, 5);
					gbc_button.gridx = 0;
					gbc_button.gridy = 5;
					panel.add(button, gbc_button);

	
    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	
        	String update ="INSERT INTO `transport`.`cars` (`cars_id`, `name`, `price`, `price_inw`, `price_50`, `price_100`, `price_200`, `price_km`, `nr_rej`) "
        			+ "VALUES "
        			+ "(NULL, '"+tName.getText()+"', "
        					+ "'"+tPrice.getText()+"', "
        							+ "'"+tPriceInw.getText()+"', "
        									+ "'"+tPrice50.getText()+"', "
        									+ "'"+tPrice100.getText()+"', "
        									+ "'"+tPrice200.getText()+"', "
        									+ "'"+tPriceKm.getText()+"', "
        									+ "'"+tNrRej.getText()+"');";
        	
        	MySQL_Cars cars=new MySQL_Cars();
        	JOptionPane.showMessageDialog(null,
        			update
        			);
        }
     });
	}
	
	
}
