package PriceList.Window;

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

import Cars.MySQL.MySQL_Cars;
import PriceList.MySQL.MySQL_PriceList;

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
	private JTable table;
	
	public ListPriceList() {

		row= new Vector<String>();
		
//********Ustawienia ramki*************
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Lista cenników");
		setSize(800, 350);
		setLocationRelativeTo(null);
//*******************************************		
		setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
		
//*********Pobieranie samochodów**************
		qtm = new MySQL_PriceList();
	    qtm.getPriceLists();
	    
	    
	    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
	    
	    JPanel buttons = new JPanel();
	    getContentPane().add(buttons);
	    buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
	    
	    JButton btnAdd = new JButton("Dodaj cennik");
	    buttons.add(btnAdd);
	    
	    JButton btnEdit = new JButton("Edytuj cennik");
	    buttons.add(btnEdit);
	    
	    JButton btnDel = new JButton("Usu\u0144 cennik");
	    buttons.add(btnDel);
//*********************************************
	    
//********Tworzenie tabeli z danych pobranych z bazy********
	    table = new JTable(qtm);
	    
	    Fun.setRightColumns(table, 1, 5);
	    
	    JScrollPane scrollPane = new JScrollPane(table); 
		getContentPane().add(scrollPane);
		
// Tworzenie menu na tablicy wyœwietlanej z zapytania
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuAdd = new JMenuItem("Dodaj");
		JMenuItem menuEdit = new JMenuItem("Edytuj");
		JMenuItem menuDel = new JMenuItem("Usuñ");
		
		popupMenu.add(menuAdd);
		popupMenu.add(menuEdit);
		popupMenu.add(menuDel);
		
		table.setComponentPopupMenu(popupMenu);
//****************************************************		

		
		Fun.resizeColumnWidth(table);
//Dodanie listnerów do elementów menu
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
		
//**************************************************************		
		
//		Dodanie listnerów buttonów
		
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

	
//	Funkcje dodawania, edycji, usuwania
	
	private void funAdd(){
		FormPriceList lista= new FormPriceList();
		lista.addPrice();
		
		qtm.getPriceLists();
		Fun.resizeColumnWidth(table);
		Fun.setRightColumns(table, 1, 5);
	}
	
	private void funEdit(){
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow!=-1){
		row.clear();
		
		for (int i=0; i<table.getColumnCount(); i++)
		{
		row.addElement(String.valueOf(table.getValueAt(selectedRow, i)));
		}
		
		row.addElement(qtm.getId(selectedRow));
		
		FormPriceList lista=new FormPriceList();
		lista.editPrice(row);
		
		qtm.getPriceLists();
		Fun.resizeColumnWidth(table);
		Fun.setRightColumns(table, 1, 5);
		}
	}
	
	private void funDel(){
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow!=-1){
		int opcja =JOptionPane.showConfirmDialog((Component) null, "Czy usun¹æ cennik?", "alert", JOptionPane.YES_NO_OPTION);

			if(opcja==0){			
			
			qtm.delPriceList(qtm.getId(selectedRow));
			
			qtm.getPriceLists();
			Fun.resizeColumnWidth(table);
			Fun.setRightColumns(table, 1, 5);
			}
		}
	}
}
