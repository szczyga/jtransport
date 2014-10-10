package Cars.Window;

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
import Cars.MySQL.MySQL_Cars;
import PriceList.MySQL.MySQL_PriceList;

import java.awt.GridBagConstraints;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListCars extends JDialog {
	
	private static MySQL_Cars qtm;
	private JPanel panel;
	private JTable table;
	private JPanel buttons;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDel;
	
	public ListCars() {
		// TODO Auto-generated constructor stub
		
//    super("Lista samochodów");
		
	Dimension rozmiar=Toolkit.getDefaultToolkit().getScreenSize();
	
    setTitle("Lista samochodów");
    setSize(rozmiar.width, rozmiar.height-50);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
	qtm=new MySQL_Cars();
    
//    setBorder(null);
//    setFrameIcon(null);
	setLocationRelativeTo(null);
	
    qtm.getCars();
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    
    buttons = new JPanel();
    getContentPane().add(buttons);
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
    
    btnAdd = new JButton("Dodaj auto");
    buttons.add(btnAdd);
    
    btnEdit = new JButton("Edytuj auto");
    buttons.add(btnEdit);
    
    btnDel = new JButton("Usu\u0144 auto");
    buttons.add(btnDel);
    
    panel = new JPanel();
    getContentPane().add(panel);
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                
    table = new JTable(qtm);
    Fun.resizeColumnWidth(table);
    
// *********Menu siatki*************
    JPopupMenu popupMenu = new JPopupMenu();
	JMenuItem menuAdd = new JMenuItem("Dodaj");
	JMenuItem menuEdit = new JMenuItem("Edytuj");
	JMenuItem menuDel = new JMenuItem("Usuñ");
	
	popupMenu.add(menuAdd);
	popupMenu.add(menuEdit);
	popupMenu.add(menuDel);
	
	table.setComponentPopupMenu(popupMenu);
//******************************************	
    
    JScrollPane scrollpane = new JScrollPane(table);
    panel.add(scrollpane);

// Definicja listnerów menu
    
    menuAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			funAdd();
		}
	});
    
    menuEdit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			funEdit();
		}
	});
	
    menuDel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			funDel();
		}
	});
    
//    ********************************************
//    Definicja listnerów buttonów
    
    btnAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			funAdd();
		}
	});
    
    btnEdit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			funEdit();
		}
	});
    
    btnDel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			funDel();
		}
	});
    
	}
	
	private void funAdd(){
		FormCars car=new FormCars();
		car.addCar();
		qtm.getCars();
		Fun.resizeColumnWidth(table);
	}
	
	private void funEdit(){
		
		int selectedRow = table.getSelectedRow();
		
		String[] params={
				String.valueOf(table.getValueAt(selectedRow, 0)),
				String.valueOf(table.getValueAt(selectedRow, 1)),
				};			
		
		FormCars car = new FormCars();
		car.editCar(params, new MySQL_Cars().getCarsPriceList(params));
		qtm.getCars();
		Fun.resizeColumnWidth(table);
	}
	
	private void funDel(){
		
		int opcja =JOptionPane.showConfirmDialog((Component) null, "Czy usun¹æ samochód?", "alert", JOptionPane.YES_NO_OPTION);

		if(opcja==0){
		int selectedRow = table.getSelectedRow();
		
		String[] params={String.valueOf(table.getValueAt(selectedRow, 0)),String.valueOf(table.getValueAt(selectedRow, 1))};
		qtm.delCars(params);
		qtm.getCars();
		Fun.resizeColumnWidth(table);
		}
	}
	
	public Vector<String> selectCar(){
		
		JPanel pSelect =new JPanel();
		getContentPane().add(pSelect);
		
		JButton select =new JButton("Wybierz");
		pSelect.add(select);
		
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
