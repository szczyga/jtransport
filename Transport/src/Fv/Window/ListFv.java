package Fv.Window;


import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import transport.Fun;
import Cars.MySQL.MySQL_Cars;
import Cars.Window.FormCars;
import Fv.MySQL.MySQL_Fv;
import Print.Function.Raport;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ListFv extends JInternalFrame {
	
	private static MySQL_Fv qtm;
	private JTable table;

	public ListFv() {
		// TODO Auto-generated constructor stub
    super("Lista faktur");
    setSize(600, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    JPopupMenu popupMenu;
	JMenuItem menuAdd, menuEdit, menuDel;
	
    qtm=new MySQL_Fv();
    
    setBorder(null);
    setFrameIcon(null);
    
    qtm.getFv();
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    
    JPanel buttons = new JPanel();
    getContentPane().add(buttons);
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
    
    JButton btnPrint = new JButton("Drukuj");
    buttons.add(btnPrint);
    
    JPanel button_fun = new JPanel();
    getContentPane().add(button_fun);
    button_fun.setLayout(new BoxLayout(button_fun, BoxLayout.LINE_AXIS));
    
    JButton btnAdd = new JButton("Dodaj fakture");
    button_fun.add(btnAdd);
    
    JButton btnEdit = new JButton("Edytuj fakture");
    button_fun.add(btnEdit);
    
    JButton btnDel = new JButton("Usu\u0144 fakture");
    button_fun.add(btnDel);
  
    JPanel panel = new JPanel();
    getContentPane().add(panel);
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                
    table = new JTable(qtm);
    
    
// *********Menu siatki*************
    popupMenu = new JPopupMenu();
	menuAdd = new JMenuItem("Dodaj");
	menuEdit = new JMenuItem("Edytuj");
	menuDel = new JMenuItem("Usuñ");
	
	popupMenu.add(menuAdd);
	popupMenu.add(menuEdit);
	popupMenu.add(menuDel);
	
	table.setComponentPopupMenu(popupMenu);
//******************************************	
    
    JScrollPane scrollpane = new JScrollPane(table);
    panel.add(scrollpane);
    
    Fun.resizeColumnWidth(table);
//    Definicja listnerów menu
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
    
//    ******************************************
    
//    Definicja listnerów buttonów
    
    btnPrint.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			funPrint();
		}
	});
    
    btnAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
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
		qtm.addFv();
		qtm.getFv();
		Fun.resizeColumnWidth(table);
	}
	
	private void funEdit(){
		
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow>-1){
		
		qtm.editFv(qtm.getId(selectedRow));
		qtm.getFv();
		Fun.resizeColumnWidth(table);
		}
	}
	
	private void funDel(){
		
		int opcja =JOptionPane.showConfirmDialog((Component) null, "Czy usun¹æ fakturê?", "alert", JOptionPane.YES_NO_OPTION);

			if(opcja==0){
			int selectedRow = table.getSelectedRow();
			
			if(selectedRow>-1){
				
			qtm.delFv(qtm.getId(selectedRow));
			qtm.getFv();
			Fun.resizeColumnWidth(table);
			}
		}
	}
	
	private void funPrint(){
		
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow>-1){
		
			Raport print=new Raport();
			print.print(qtm.getId(selectedRow));
		}
	}

}
