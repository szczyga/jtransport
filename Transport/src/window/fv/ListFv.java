package window.fv;


import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
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

public class ListFv extends JInternalFrame {
	
	private static MySQL_Fv qtm;
	
	public ListFv() {
		// TODO Auto-generated constructor stub
    super("Lista faktur");
    setSize(600, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    qtm=new MySQL_Fv();
    
    setBorder(null);
    setFrameIcon(null);
    
    qtm.getFv();
    getContentPane().setLayout(new BorderLayout(0, 0));
    
    JPanel panel = new JPanel();
    getContentPane().add(panel, BorderLayout.NORTH);
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                
    JTable table = new JTable(qtm);
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
//    getContentPane().add(scrollpane, gbc_scrollpane );
    
    menuAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			FormFv car=new FormFv();
		}
	});
    
    menuEdit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	});
	
    menuDel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	});
    
	}

}
