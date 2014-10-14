package Fv.Window;


import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import transport.Fun;
import Cars.MySQL.MySQL_Cars;
import Cars.Window.FormCars;
import Fv.createFvNumbers;
import Fv.dateSelect;
import Fv.MySQL.MySQL_Fv;
import Print.Function.Raport;
import Print.Function.RaportList;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.MatteBorder;

public class ListFv extends JInternalFrame {
	
	private static MySQL_Fv qtm;
	private JTable table;
	
	private JDatePickerImpl dateStart;
	private SpringLayout springLayoutStart;
	
	private JDatePickerImpl dateEnd;
	private SpringLayout springLayoutEnd;

	public ListFv() {
		// TODO Auto-generated constructor stub
    super("Lista faktur");
    setSize(600, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    JPopupMenu popupMenu;
	JMenuItem menuAdd, menuEdit, menuDel;
	
    qtm=new MySQL_Fv();
    
    setFrameIcon(null);
     
    qtm.getFv(null,null);
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    
    JPanel buttons = new JPanel();
    getContentPane().add(buttons);
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
    
    JButton btnPrint = new JButton("Drukuj");
    buttons.add(btnPrint);
    
    JButton btnPrintList = new JButton("Drukuj zestawienie");
    buttons.add(btnPrintList);
    
    JPanel zakres = new JPanel();
    zakres.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
    getContentPane().add(zakres);
    zakres.setLayout(new BoxLayout(zakres, BoxLayout.LINE_AXIS));

//    ******************Data start********************
    
    UtilDateModel modelStart = new UtilDateModel();
    JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStart);
	
	JPanel dStart = new JPanel();
	zakres.add(dStart);
	dStart.setLayout(new BoxLayout(dStart, BoxLayout.PAGE_AXIS));
	
	JLabel lblDStart = new JLabel("Data pocz\u0105tkowa");
	dStart.add(lblDStart);
	dateStart = new JDatePickerImpl(datePanelStart);
	dStart.add(dateStart);
	springLayoutStart = (SpringLayout) dateStart.getLayout();
	springLayoutStart.putConstraint(SpringLayout.WEST, dateStart.getJFormattedTextField(), 0, SpringLayout.WEST, dateStart);
	dateStart.setPreferredSize(new Dimension(202, 34));
	
//	*******************Data End************************
	
	JPanel dEnd = new JPanel();
	zakres.add(dEnd);
	dEnd.setLayout(new BoxLayout(dEnd, BoxLayout.PAGE_AXIS));
	
	JLabel lblDEnd = new JLabel("Data ko\u0144cowa");
	dEnd.add(lblDEnd);
	
	UtilDateModel modelEnd = new UtilDateModel();
	
	JDatePanelImpl datePanelEnd = new JDatePanelImpl(modelEnd);
	
	dateEnd = new JDatePickerImpl(datePanelEnd);
	dEnd.add(dateEnd);
	springLayoutEnd = (SpringLayout) dateEnd.getLayout();
	springLayoutEnd.putConstraint(SpringLayout.WEST, dateEnd.getJFormattedTextField(), 0, SpringLayout.WEST, dateEnd);
	dateEnd.setPreferredSize(new Dimension(202, 34));
	
	JPanel dButton = new JPanel();
	zakres.add(dButton);
	dButton.setLayout(new BoxLayout(dButton, BoxLayout.PAGE_AXIS));
	
	JButton btnZakres = new JButton("Ustaw zakres");
	dButton.add(btnZakres);
    
    JPanel button_fun = new JPanel();
    getContentPane().add(button_fun);
    button_fun.setLayout(new BoxLayout(button_fun, BoxLayout.LINE_AXIS));
    
    JButton btnAdd = new JButton("Dodaj fakture");
    button_fun.add(btnAdd);
    
    JButton btnEdit = new JButton("Edytuj fakture");
    button_fun.add(btnEdit);
    
    JButton btnDel = new JButton("Usu\u0144 fakture");
    button_fun.add(btnDel);
    
    JPanel pNumSet = new JPanel();
    getContentPane().add(pNumSet);
    pNumSet.setLayout(new BoxLayout(pNumSet, BoxLayout.LINE_AXIS));
    
    JButton btnNumSet = new JButton("Ustawienie numer\u00F3w faktur");
    pNumSet.add(btnNumSet);
  
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
    
    btnZakres.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setZakres();
		}
	});
    
    btnNumSet.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setNum();
			qtm.getFv((Date) dateStart.getModel().getValue(),(Date) dateEnd.getModel().getValue());
			Fun.resizeColumnWidth(table);
		}
	});
    
    btnPrintList.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			funPrintList();
		}
	});
    
	}
	
	private void setZakres(){
		
		qtm.getFv((Date) dateStart.getModel().getValue(),(Date) dateEnd.getModel().getValue());
		Fun.resizeColumnWidth(table);
	}
	
	private void setNum(){
		
		if((Date) dateStart.getModel().getValue()!=null&&(Date) dateEnd.getModel().getValue()!=null){
			try {
				new createFvNumbers((Date) dateStart.getModel().getValue(), (Date) dateEnd.getModel().getValue());
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		JOptionPane.showMessageDialog(null, "Nie wybrano zakresu dat.");
	}
	
	private void funAdd(){
		qtm.addFv();
		
		qtm.getFv((Date) dateStart.getModel().getValue(),(Date) dateEnd.getModel().getValue());
		Fun.resizeColumnWidth(table);
	}
	
	private void funEdit(){
		
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow>-1){
		
		qtm.editFv(qtm.getId(selectedRow));
		qtm.getFv((Date) dateStart.getModel().getValue(),(Date) dateEnd.getModel().getValue());
		Fun.resizeColumnWidth(table);
		}
	}
	
	private void funDel(){
		
		int opcja =JOptionPane.showConfirmDialog((Component) null, "Czy usun¹æ fakturê?", "alert", JOptionPane.YES_NO_OPTION);

			if(opcja==0){
			int selectedRow = table.getSelectedRow();
			
			if(selectedRow>-1){
				
			qtm.delFv(qtm.getId(selectedRow));
			qtm.getFv((Date) dateStart.getModel().getValue(),(Date) dateEnd.getModel().getValue());
			Fun.resizeColumnWidth(table);
			}
		}
	}
	
	private void funPrint(){
		
		int selectedRows[]=table.getSelectedRows();

		
		if(selectedRows.length>0){
			
			String [] Ids=qtm.getIds(selectedRows);
					
			Object options[] ={"Tak","Nie"};
			int answer = JOptionPane.showOptionDialog(null, "Zmieniæ datê faktur?", "Zmiana daty", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
			if(answer==0){
				dateSelect data=new dateSelect();

				String dat=data.getDateBase();
				
				if(dat!=null){
					qtm.setDate(Ids, dat);
					qtm.getFv((Date) dateStart.getModel().getValue(),(Date) dateEnd.getModel().getValue());
					Fun.resizeColumnWidth(table);
				}
			}
			
			Raport print=new Raport();
			print.print(Ids);
		}
	}
	
	private void funPrintList(){
		
		int selectedRows[]=table.getSelectedRows();
		
		if(selectedRows.length>0){
			
			dateSelect data=new dateSelect();
			
			String dat=data.getDate();
			
			if(dat!=null){
				System.out.println(dat);
				
				RaportList print=new RaportList();

				print.print(qtm.getIds(selectedRows),dat);	
			}
			else
			JOptionPane.showMessageDialog(null, "Data nie zosta³a wybrana");
			
		}
		
	}

}
