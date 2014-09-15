package transport;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import window.MyButton;
import window.MyGrid;
import window.cars.ListCars;
import window.dictionary.ListPriceList;
import window.fv.ListFv;
import MySQL.MySQL_Cars;

import javax.swing.JMenuBar;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JMenuItem;
import javax.swing.JMenu;


public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
//****************Ustawienie wygl¹du windws**********************************
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	*********************************************************

		
		
//***************Utworzenie g³ównej ramki********************
		JFrame frame = new JFrame("G³ówne");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JDesktopPane desktop=new JDesktopPane();
	    desktop.setBackground(new Color(204, 204, 204));
	    
	    frame.setContentPane(desktop);
	    
		//*********Utworzenie listwy menu na górze*******************	    
			    JMenuBar menuBar = new JMenuBar();
			    	    
			    JMenu mnMenu = new JMenu("Menu");
			    JMenu mnDictionary = new JMenu("S³owniki");
			    menuBar.add(mnMenu);
			    menuBar.add(mnDictionary);
			    
			    JMenuItem mnitmCennik = new JMenuItem("Cenniki");	    
			    JMenuItem mnitmCarList = new JMenuItem("Lista samochodów");
			    JMenuItem mnitmFv = new JMenuItem("Lista faktur");
			    
			    mnMenu.add(mnitmCarList);
			    mnMenu.add(mnitmFv);
			    mnDictionary.add(mnitmCennik);
			    
			    frame.setJMenuBar(menuBar);
			    
		//***************************************************************
 
	    
	    frame.setSize(700, 700);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
//**********************************************************	   
	   	    
//*****************Listner pozycji menu górnego cenników****************
	    mnitmCennik.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ListPriceList lista=new ListPriceList();
				lista.setVisible(true);
			}
		});
//********************************************************	
	    
//***********Listner dla pozycji menu listy samochodów**************
	    mnitmCarList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ListCars lista=new ListCars();

//				desktop.add(lista);				
		        
		        lista.setVisible(true);
			}
		});
//***************************************************************
	    
//*************Listner dla pozycji faktur************************
	    mnitmFv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ListFv lista=new ListFv();
				
				desktop.add(lista);				
				
				try {
					lista.setMaximum(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		        try {
		            lista.setSelected(true);
		        } catch (java.beans.PropertyVetoException er) {}
		        
		        lista.setVisible(true);
			}
		});
	    
	    
	}

}
