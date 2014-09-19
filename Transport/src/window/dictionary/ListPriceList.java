package window.dictionary;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import MySQL.MySQL_Cars;
import MySQL.MySQL_PriceList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import transport.Fun;

public class ListPriceList extends JDialog {
	
	public static MySQL_PriceList qtm;
	
	private Vector<String> row; 
	
	public ListPriceList() {

		row= new Vector<String>();
		
//********Ustawienia ramki*************
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Lista cenników");
		setSize(550, 250);
		setLocationRelativeTo(null);
//*******************************************		
		setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
		
//*********Pobieranie samochodów**************
		qtm = new MySQL_PriceList();
	    qtm.getPriceLists();
//*********************************************
	    
//********Tworzenie tabeli z danych pobranych z bazy********
	    JTable table = new JTable(qtm);
	    JScrollPane scrollPane = new JScrollPane(table);
	    	    
	    Fun.resizeColumnWidth(table);	
	    
		getContentPane().add(scrollPane);
		
// Tworzenie menu na tablicy wyœwietlanej z zapytania
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuAdd = new JMenuItem("Dodaj");
		JMenuItem menuEdit = new JMenuItem("Edytuj");
		JMenuItem menuDel = new JMenuItem("Usuñ");
		JMenuItem menuNr = new JMenuItem("Numer");
		
		popupMenu.add(menuAdd);
		popupMenu.add(menuEdit);
		popupMenu.add(menuDel);
		popupMenu.add(menuNr);
		
		table.setComponentPopupMenu(popupMenu);
//****************************************************		

//Dodanie listnerów do elementów menu
		menuAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub		 
				FormPriceList lista= new FormPriceList();
				lista.addPrice();
				
				qtm.getPriceLists();
				Fun.resizeColumnWidth(table);
			}
		});
		
		menuEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedRow = table.getSelectedRow();
				
				row.clear();
				
				for (int i=0; i<table.getColumnCount(); i++)
				{
				row.addElement(String.valueOf(table.getValueAt(selectedRow, i)));
//				 row.add(String.valueOf(table.getValueAt(selectedRow, i)));			Stara konfiguracja
				}
				
				row.addElement(qtm.getId(selectedRow));
				
				FormPriceList lista=new FormPriceList();
				lista.editPrice(row);
				
				qtm.getPriceLists();
				Fun.resizeColumnWidth(table);
			}
		});
		
		menuDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedRow = table.getSelectedRow();
//				qtm.delPriceList(String.valueOf(table.getValueAt(selectedRow, 0))); Stara konfiguracja
				qtm.delPriceList(qtm.getId(selectedRow));
				
				qtm.getPriceLists();
				Fun.resizeColumnWidth(table);
			}
		});
		
		menuNr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int selectedRow = table.getSelectedRow();
				JOptionPane.showMessageDialog(null, qtm.getId(selectedRow));
			}
		});
//**************************************************************		
	}
}
