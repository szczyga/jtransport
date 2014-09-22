package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Cars.Window.FormCars;


public class MyButton extends JButton {

	
	public MyButton(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		
		if (name=="Dodaj"){
	    this.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	FormCars template=new FormCars();
	        	template.setVisible(true);
	        }
	     });
		}else if(name=="Usuñ"){
		 this.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	JOptionPane.showMessageDialog(null, "Usuñ");
		        }
		     });
		}else if(name=="Edytuj"){
		 this.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	JOptionPane.showMessageDialog(null, "Edytuj");
		        }
		     });
		}
	}
}
