package window.fv;



import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import models.FvRowModel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class FormFv extends JDialog {
	
	private JTextField textNrFv;
	private JTextField textZaklad;
	private JTextField textSellDate;
	
	private FvRowModel fvModel;
	
	public FormFv() {

		setSize(800, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setResizable(false);
			
		setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
		
		fvModel =new FvRowModel();
		
		JTable table=new JTable(fvModel);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		JPanel fv = new JPanel();
		panel.add(fv);
		fv.setLayout(new BoxLayout(fv, BoxLayout.PAGE_AXIS));
		
			JLabel lblNrFv = new JLabel("Nr Faktury");
			fv.add(lblNrFv);
			
			textNrFv = new JTextField();
			textNrFv.setColumns(10);
			fv.add(textNrFv);
			
			JPanel zaklad = new JPanel();
			panel.add(zaklad);
			zaklad.setLayout(new BoxLayout(zaklad, BoxLayout.PAGE_AXIS));
			JLabel lblZakad = new JLabel("Zak³ad obci¹¿any");
			zaklad.add(lblZakad);
			textZaklad = new JTextField();
			textZaklad.setColumns(10);
			zaklad.add(textZaklad);
			
			JPanel sellDate = new JPanel();
			panel.add(sellDate);
			sellDate.setLayout(new BoxLayout(sellDate, BoxLayout.PAGE_AXIS));
			JLabel lblSellDate = new JLabel("Data sprzeda¿y");
			sellDate.add(lblSellDate);
			textSellDate = new JTextField();
			textSellDate.setColumns(10);
			sellDate.add(textSellDate);
		
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
		getContentPane().add(scrollpane);
		
		menuAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FormFvRow row=new FormFvRow();
				fvModel.addRow(row.addRow());
				
			}
		});
		
		menuEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
							
				int selectedRow = table.getSelectedRow();
				
				FormFvRow row=new FormFvRow();
				
				if(selectedRow>=0){
	
				fvModel.editRow(row.editRow(fvModel.getRow(selectedRow)));
				}
			}
		});
		
		menuDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int selectedRow = table.getSelectedRow();
				
				fvModel.delRow(selectedRow);
			}
		});
		
		
		
		setVisible(true);
	}

}
