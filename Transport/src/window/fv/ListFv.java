package window.fv;


import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import transport.Fun;
import window.cars.FormCars;
import MySQL.MySQL_Cars;
import MySQL.MySQL_Fv;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import print.Raport;

public class ListFv extends JInternalFrame {
	
	private static MySQL_Fv qtm;
	

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
    

    
    JPanel panel = new JPanel();
    getContentPane().add(panel);
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                
    JTable table = new JTable(qtm);
    Fun.resizeColumnWidth(table);
    
// *********Menu siatki*************
    popupMenu = new JPopupMenu();
	menuAdd = new JMenuItem("Dodaj");
	menuEdit = new JMenuItem("Edytuj");
	menuDel = new JMenuItem("Usu�");
	
	popupMenu.add(menuAdd);
	popupMenu.add(menuEdit);
	popupMenu.add(menuDel);
	
	table.setComponentPopupMenu(popupMenu);
//******************************************	
    
    JScrollPane scrollpane = new JScrollPane(table);
    panel.add(scrollpane);
//    getContentPane().add(scrollpane, gbc_scrollpane );
    
    menuAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			qtm.addFv();
			qtm.getFv();
		}
	});
    
    menuEdit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int selectedRow = table.getSelectedRow();
			
			if(selectedRow>-1){
			
			qtm.editFv(qtm.getId(selectedRow));
			qtm.getFv();
			}
		}
	});
	
    menuDel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = table.getSelectedRow();
			
			if(selectedRow>-1){
				
			qtm.delFv(qtm.getId(selectedRow));
			qtm.getFv();
			}
		}
	});
    
    btnPrint.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int selectedRow = table.getSelectedRow();
			
			if(selectedRow>-1){
			
				Raport print=new Raport();
				print.print(qtm.getId(selectedRow));
			}
			
		}
	});
    
	}

}
