package Zaklady.Window;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import Zaklady.MySQL.MySQL_ZaklList;
import transport.Fun;

public class ListZaklady extends JDialog {
	
	public static MySQL_ZaklList qtm;
	
	private Vector<String> row; 
	private JTable table;
	
	public ListZaklady() {

		row= new Vector<String>();
		
//********Ustawienia ramki*************
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Lista zak³adów");
		setSize(350, 300);
		setLocationRelativeTo(null);
//*******************************************		
		setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
		
//*********Pobieranie samochodów**************
		qtm = new MySQL_ZaklList();
	    qtm.getZaklList();
	    
	    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
	    
	    JPanel buttons = new JPanel();
	    getContentPane().add(buttons);
	    buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
	    
	    JButton btnAdd = new JButton("Dodaj zak³ad");
	    buttons.add(btnAdd);
	    
	    JButton btnEdit = new JButton("Edytuj zak³ad");
	    buttons.add(btnEdit);
	    
	    JButton btnDel = new JButton("Usu\u0144 zak³ad");
	    buttons.add(btnDel);
//*********************************************
	    
//********Tworzenie tabeli z danych pobranych z bazy********
	    table = new JTable(qtm);
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
		Fun.centerOneColumnText(table,0);
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
		FormZaklady lista= new FormZaklady();
		lista.addZaklad();
		
		qtm.getZaklList();
		Fun.resizeColumnWidth(table);
		Fun.centerOneColumnText(table,0);
	}
	
	private void funEdit(){
		int selectedRow = table.getSelectedRow();
		
		row.clear();
		
		row.addElement(String.valueOf(table.getValueAt(selectedRow, 0)));
		
		row.addElement(qtm.getId(selectedRow));
		
		FormZaklady lista=new FormZaklady();
		lista.editZaklad(row);
		
		qtm.getZaklList();
		Fun.resizeColumnWidth(table);
		Fun.centerOneColumnText(table,0);
	}
	
	private void funDel(){
		
		int opcja =JOptionPane.showConfirmDialog((Component) null, "Czy usun¹æ zak³ad?", "alert", JOptionPane.YES_NO_OPTION);

		if(opcja==0){
		int selectedRow = table.getSelectedRow();
		qtm.delZakladList(qtm.getId(selectedRow));
		
		qtm.getZaklList();
		Fun.resizeColumnWidth(table);
		Fun.centerOneColumnText(table,0);
		}
	}
}
