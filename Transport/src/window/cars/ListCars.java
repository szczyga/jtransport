package window.cars;

import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import transport.Fun;
import window.MyButton;
import window.MyGrid;
import MySQL.MySQL_Cars;
import MySQL.MySQL_PriceList;

import java.awt.GridBagConstraints;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListCars extends JDialog {
	
	private static MySQL_Cars qtm;
	private JPanel panel;
	private JTable table;
	
	public ListCars() {
		// TODO Auto-generated constructor stub
		
//    super("Lista samochodów");
    
    setTitle("Lista samochodów");
    setSize(600, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
	qtm=new MySQL_Cars();
    
//    setBorder(null);
//    setFrameIcon(null);
	setLocationRelativeTo(null);
	
    qtm.getCars();
    getContentPane().setLayout(new BorderLayout(0, 0));
    
    panel = new JPanel();
    getContentPane().add(panel, BorderLayout.NORTH);
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                
    table = new JTable(qtm);
    Fun.resizeColumnWidth(table);
    
// *********Menu siatki*************
    JPopupMenu popupMenu = new JPopupMenu();
	JMenuItem menuAdd = new JMenuItem("Dodaj");
	JMenuItem menuEdit = new JMenuItem("Edytuj");
	JMenuItem menuDel = new JMenuItem("Usuñ");
	
	JMenuItem menuID = new JMenuItem("Show id");
	
	popupMenu.add(menuAdd);
	popupMenu.add(menuEdit);
	popupMenu.add(menuDel);
	popupMenu.add(menuID);
	
	table.setComponentPopupMenu(popupMenu);
//******************************************	
    
    JScrollPane scrollpane = new JScrollPane(table);
    panel.add(scrollpane);
//    getContentPane().add(scrollpane, gbc_scrollpane );
    
    menuAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			FormCars car=new FormCars();
			car.addCar();
			qtm.getCars();
		}
	});
    
    menuEdit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int selectedRow = table.getSelectedRow();
			
			String[] params={
					String.valueOf(table.getValueAt(selectedRow, 0)),
					String.valueOf(table.getValueAt(selectedRow, 1)),
					};			
			
			FormCars car = new FormCars();
			car.editCar(params, new MySQL_Cars().getCarsPriceList(params));
			qtm.getCars();
		}
	});
	
    menuDel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int selectedRow = table.getSelectedRow();
			
			String[] params={String.valueOf(table.getValueAt(selectedRow, 0)),String.valueOf(table.getValueAt(selectedRow, 1))};
			qtm.delCars(params);
			qtm.getCars();
		}
	});
    
    menuID.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int selectedRow = table.getSelectedRow();
			JOptionPane.showMessageDialog(null, qtm.getId(selectedRow));
		}
	});
    
	}
	
	public Vector<String> selectCar(){
		
		JButton select =new JButton("Wybierz");
		panel.add(select);
		
		Vector<String> params=new Vector<String>();
		
		
		
		select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int selectedRow = table.getSelectedRow();
				
				params.add(qtm.getId(selectedRow));
				params.add(String.valueOf(table.getValueAt(selectedRow, 0)));
				params.add(String.valueOf(table.getValueAt(selectedRow, 1)));	
				
				dispose();
			}
		});
		
		setVisible(true);
		return params;
	}

}
