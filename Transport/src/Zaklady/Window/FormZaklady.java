package Zaklady.Window;


import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import Zaklady.MySQL.MySQL_ZaklList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FormZaklady extends JDialog {
	private JTextField textName;
	
	private JPanel panel;
	private JPanel panel_1;
	
	public FormZaklady() {
		
		
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
						gbc_lblName.insets = new Insets(0, 0, 5, 0);
						gbc_lblName.gridx = 1;
						gbc_lblName.gridy = 0;
						panel.add(lblName, gbc_lblName);
		
				textName = new JTextField();		
				textName.setColumns(15);
				GridBagConstraints gbc_textName = new GridBagConstraints();
				gbc_textName.anchor = GridBagConstraints.NORTHWEST;
				gbc_textName.gridx = 1;
				gbc_textName.gridy = 1;
				panel.add(textName, gbc_textName);
		
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
	
	public void addZaklad(){
		setTitle("Dodaj zak³ad");
		
		JButton btnAdd=new JButton("Dodaj");
		
		panel_1.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MySQL_ZaklList qtm= new MySQL_ZaklList();
				
				String[] buttons={	textName.getText()};
				qtm.setZakladList(buttons);
				dispose();
			}
		});		
		
		setVisible(true);
	}
	
	public void editZaklad(Vector<String> row){
		setTitle("Edytuj zak³ad");
		
		JButton btnEdit=new JButton("Zmieñ");

		panel_1.add(btnEdit);
			
		textName.setText(row.get(0));

		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MySQL_ZaklList qtm= new MySQL_ZaklList();
				
				row.set(0, textName.getText());
					
				qtm.editZakladList(row);
				
				dispose();
			}
		});
		
		setVisible(true);
	}


}
