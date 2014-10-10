package transport;

import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Cars.Window.ListCars;
import Fv.Window.ListFv;
import PriceList.Window.ListPriceList;
import javax.swing.JMenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import Zaklady.Window.ListZaklady;


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
	    
	    frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
	    
	    frame.setContentPane(desktop);
	    
		//*********Utworzenie listwy menu na górze*******************	    
			    JMenuBar menuBar = new JMenuBar();
			    	    
			    JMenu mnMenu = new JMenu("Menu");
			    JMenu mnDictionary = new JMenu("S³owniki");
			    menuBar.add(mnMenu);
			    menuBar.add(mnDictionary);
			    
			    JMenuItem mnitmCennik = new JMenuItem("Cenniki");
			    JMenuItem mnitmZaklad = new JMenuItem("Zak³ady");
			    JMenuItem mnitmCarList = new JMenuItem("Lista samochodów");
			    JMenuItem mnitmFv = new JMenuItem("Lista faktur");
			    
			    mnMenu.add(mnitmCarList);
			    mnMenu.add(mnitmFv);
			    mnDictionary.add(mnitmCennik);
			    mnDictionary.add(mnitmZaklad);
			    
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
	  
	    mnitmZaklad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ListZaklady lista=new ListZaklady();
				lista.setVisible(true);
			}
		});
//***********Listner dla pozycji menu listy samochodów**************
	    mnitmCarList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ListCars lista;
				
					lista = new ListCars();
			        
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
