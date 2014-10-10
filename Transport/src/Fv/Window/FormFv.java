package Fv.Window;



import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import Fv.models.FvRowModel;
import Zaklady.MySQL.MySQL_ZaklList;

import com.sun.org.apache.bcel.internal.generic.FNEG;


public class FormFv extends JDialog {
	
	private JTextField textNrFv;
	private JTextField textSellDate;
	JComboBox cZaklad, cRodzFv;
	
	private FvRowModel fvModel;
	JTable table;
	JPanel fv_buttons;
	
	private JDatePickerImpl datePicker;
	private SpringLayout springLayout;
	
	int accept;
	
	MySQL_ZaklList zakMod;
	
	public FormFv() {

		setSize(900, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setResizable(false);
			
		setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
		
		fvModel =new FvRowModel();
		zakMod=new MySQL_ZaklList();
		
		table=new JTable(fvModel);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		JPanel pola = new JPanel();
		getContentPane().add(pola);
		pola.setLayout(new BoxLayout(pola, BoxLayout.LINE_AXIS));
		
		JPanel fv = new JPanel();
		pola.add(fv);
		fv.setLayout(new BoxLayout(fv, BoxLayout.PAGE_AXIS));
		
			JLabel lblNrFv = new JLabel("Nr Faktury");
			fv.add(lblNrFv);
			
			textNrFv = new JTextField();
			textNrFv.setColumns(10);
			fv.add(textNrFv);
			
			JPanel zaklad = new JPanel();
			pola.add(zaklad);
			zaklad.setLayout(new BoxLayout(zaklad, BoxLayout.PAGE_AXIS));
			JLabel lblZakad = new JLabel("Zak³ad obci¹¿any");
			zaklad.add(lblZakad);
			
			cZaklad = new JComboBox<String>(zakMod.getZaklListCombo());
			zaklad.add(cZaklad);
			
			JPanel sellDate = new JPanel();
			pola.add(sellDate);
			sellDate.setLayout(new BoxLayout(sellDate, BoxLayout.PAGE_AXIS));
			JLabel lblSellDate = new JLabel("Data sprzeda¿y");
			sellDate.add(lblSellDate);
			
			UtilDateModel model = new UtilDateModel();
			JDatePanelImpl datePanel = new JDatePanelImpl(model);
			datePicker = new JDatePickerImpl(datePanel);
			springLayout = (SpringLayout) datePicker.getLayout();
			springLayout.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 0, SpringLayout.WEST, datePicker);
			datePicker.setPreferredSize(new Dimension(202, 34));

			sellDate.add(datePicker);
			
//			textSellDate = new JTextField();
//			textSellDate.setColumns(10);
//			sellDate.add(textSellDate);
			
			JPanel rodzFv = new JPanel();
			pola.add(rodzFv);
			rodzFv.setLayout(new BoxLayout(rodzFv, BoxLayout.PAGE_AXIS));
			
			JLabel lblRodzFv = new JLabel("Rodzaj obci\u0105\u017Cenia");
			rodzFv.add(lblRodzFv);
			
			cRodzFv = new JComboBox();
			cRodzFv.setModel(new DefaultComboBoxModel(new String[] {"Kosztowe", "Inwestycyjne"}));
			rodzFv.add(cRodzFv);
		
		// *********Menu siatki*************
	    JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuAdd = new JMenuItem("Dodaj");
		JMenuItem menuEdit = new JMenuItem("Edytuj");
		JMenuItem menuDel = new JMenuItem("Usuñ");
		
		popupMenu.add(menuAdd);
		popupMenu.add(menuEdit);
		popupMenu.add(menuDel);
		
		JPanel row_buttons = new JPanel();
		getContentPane().add(row_buttons);
		row_buttons.setLayout(new BoxLayout(row_buttons, BoxLayout.LINE_AXIS));
		
		JButton btnAdd = new JButton("Dodaj pozycje");
		row_buttons.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addRow();
			}
		});
		
		JButton btnEdit = new JButton("Edytuj pozycje");
		row_buttons.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editRow();
			}
		});
		
		JButton btnDel = new JButton("Usu\u0144 pozycje");
		row_buttons.add(btnDel);
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delRow();
			}
		});
		
		table.setComponentPopupMenu(popupMenu);
		//******************************************

		JScrollPane scrollpane = new JScrollPane(table);
		getContentPane().add(scrollpane);
		
		fv_buttons = new JPanel();
		getContentPane().add(fv_buttons);
		fv_buttons.setLayout(new BoxLayout(fv_buttons, BoxLayout.LINE_AXIS));
		
		

		
		menuAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addRow();
			}
		});
		
		menuEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editRow();
			}
		});
		
		menuDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delRow();
			}
		});
		
	}
	
	private void addRow(){
		FormFvRow row=new FormFvRow();
		fvModel.addRow(row.addRow());
	}
	
	private void editRow(){
		
		int selectedRow = table.getSelectedRow();
		
		FormFvRow row=new FormFvRow();
		
		if(selectedRow>=0){
		
		fvModel.editRow(row.editRow(fvModel.getRow(selectedRow)));
		}
	}
	
	private void delRow(){		
		int selectedRow = table.getSelectedRow();
		
		fvModel.delRow(selectedRow);
	}
	
	public FvRowModel getRowSet(){
		
		return fvModel;
	}
	
	public Map<String, String> getFvHedder(){
		
		Map<String, String> fvHeadder=new HashMap<String, String>();
		
		fvHeadder.put("fvNr", textNrFv.getText());
		fvHeadder.put("zaklad", zakMod.getZakId(cZaklad.getSelectedIndex()));
		fvHeadder.put("rodzFv", (String)cRodzFv.getSelectedItem());
		
		Date selectedDate = (Date) datePicker.getModel().getValue();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		fvHeadder.put("data",String.valueOf(df.format(selectedDate)));
		
		return fvHeadder;
	}
	
	public int addFv(){
		
		accept=0;
		
		setTitle("Dodawanie faktury");
		
		JButton btnCancel = new JButton("Anuluj");
		fv_buttons.add(btnCancel);
		
		JButton btnAccept = new JButton("Zatwierd\u017A");
		fv_buttons.add(btnAccept);
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		btnAccept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(fvModel.getRowCount()>0){
					if(datePicker.getModel().isSelected()){
						accept=1;
						dispose();
					}
					else{
					accept=0;
					JOptionPane.showMessageDialog(null, "Data nie wybrana");
					}
				}
				else{
				accept=0;
				JOptionPane.showMessageDialog(null, "Faktura nie ma ¿adnych pozycji");
				}
			}
		});
		
		setVisible(true);
		
		return accept;
	}

	public int editFv(Map<String, String> headder, FvRowModel rows){
		
		accept=0;
		
		setTitle("Edycja faktury");
		
		for(int i=0; i<rows.getRowCount(); i++){
			fvModel.addRow(rows.getRow(i));
		}
				
		textNrFv.setText(headder.get("fvNr"));
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		
		try {
			c.setTime(df.parse(headder.get("data")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		datePicker.getModel().setYear(c.get(Calendar.YEAR));
		datePicker.getModel().setMonth(c.get(Calendar.MONTH));
		datePicker.getModel().setDay(c.get(Calendar.DAY_OF_MONTH));
		datePicker.getModel().setSelected(true);
		

		cRodzFv.setSelectedIndex(Integer.parseInt(headder.get("rodzFv")));
		cZaklad.setSelectedIndex(zakMod.getZakIndex(headder.get("zaklad")));
		
		JButton btnCancel = new JButton("Anuluj");
		fv_buttons.add(btnCancel);
		
		JButton btnEdit = new JButton("Zatwierd\u017A");
		fv_buttons.add(btnEdit);
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(fvModel.getRowCount()>0){
					if(datePicker.getModel().isSelected()){
						accept=1;
						dispose();
					}
					else{
					accept=0;
					JOptionPane.showMessageDialog(null, "Data nie wybrana");
					}
				}
				else{
				accept=0;
				JOptionPane.showMessageDialog(null, "Faktura nie ma ¿adnych pozycji");
				}
			}
		});		
		
		setVisible(true);
		
		return accept;
	}
}
