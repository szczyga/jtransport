package Fv.Window;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;

import Cars.Window.ListCars;
import sun.util.calendar.CalendarUtils;
import sun.util.resources.cldr.CalendarData;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FormFvRow extends JDialog {
	private JTextField textCar;
	private JTextField textDate;
	private JTextField textWhatDo;
	private JTextField textHour;
	private JTextField textHour50;
	private JTextField textHour100;
	private JTextField textHour200;
	private JTextField textKm;
	private JTextField textIdle;
	private JTextField textComment;
	private JDatePickerImpl datePicker;
	
	Vector<String> carParams;
	
	int isEmpty;
	Vector<String> row;
	
	
	public FormFvRow() {
		isEmpty=1;
		
//		Inicjalizacja zmiennych w klasie

		carParams=new Vector<String>();
		row=new Vector<String>();

//		Ustawienie Layoutu, wielkoœci okna, w³aœciwoœci i po³o¿enie okna
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		setSize(450, 300);
		setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
//		************************************************************

//		Main panel
		JPanel main = new JPanel();
		getContentPane().add(main);
		
		
		
//		Car panel
		JPanel car = new JPanel();
		main.add(car);
		car.setPreferredSize(new Dimension(200, 35));
		car.setLayout(new BoxLayout(car, BoxLayout.PAGE_AXIS));
		
		JLabel lblCar = new JLabel("Samoch\u00F3d");
		car.add(lblCar);
		
		textCar = new JTextField();
		textCar.setEnabled(false);
		car.add(textCar);
			
		textCar.setColumns(10);
//	***********************************************
		
//		Date panel
		JPanel date = new JPanel();
		main.add(date);
		date.setPreferredSize(new Dimension(100, 38));
		date.setLayout(new BoxLayout(date, BoxLayout.PAGE_AXIS));
		
		JLabel lblDate = new JLabel("Data");
		date.add(lblDate);
		
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.setPreferredSize(new Dimension(202, 34));

		date.add(datePicker);
//		*******************************************
		
//		Hours panels
		
		JPanel hours = new JPanel();
		hours.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(hours);
		
		JPanel hour = new JPanel();
		hour.setPreferredSize(new Dimension(50, 35));
		hours.add(hour);
		hour.setLayout(new BoxLayout(hour, BoxLayout.PAGE_AXIS));
		
		JLabel lblhour = new JLabel("Il. godzin");
		hour.add(lblhour);
		
		textHour = new JTextField();
		textHour.setText("0");
		hour.add(textHour);
		textHour.setColumns(10);
		
		JPanel hour50 = new JPanel();
		hours.add(hour50);
		hour50.setLayout(new BoxLayout(hour50, BoxLayout.PAGE_AXIS));
		
		JLabel lblHour50 = new JLabel("Il. godzin 50");
		hour50.add(lblHour50);
		
		textHour50 = new JTextField();
		textHour50.setText("0");
		textHour50.setColumns(10);
		hour50.add(textHour50);
		
		JPanel hour100 = new JPanel();
		hour100.setPreferredSize(new Dimension(70, 35));
		hours.add(hour100);
		hour100.setLayout(new BoxLayout(hour100, BoxLayout.PAGE_AXIS));
		
		JLabel lblHour100 = new JLabel("Il. godzin 100");
		hour100.add(lblHour100);
		
		textHour100 = new JTextField();
		textHour100.setText("0");
		textHour100.setColumns(10);
		hour100.add(textHour100);
		
		JPanel hour200 = new JPanel();
		hour200.setPreferredSize(new Dimension(70, 35));
		hours.add(hour200);
		hour200.setLayout(new BoxLayout(hour200, BoxLayout.PAGE_AXIS));
		
		JLabel lblHour200 = new JLabel("Il. godzin 200");
		hour200.add(lblHour200);
		
		textHour200 = new JTextField();
		textHour200.setText("0");
		textHour200.setColumns(10);
		hour200.add(textHour200);
		
//********************************************	
		
//		Params panel
		
		JPanel params = new JPanel();
		getContentPane().add(params);
		
		JPanel km = new JPanel();
		km.setPreferredSize(new Dimension(40, 35));
		params.add(km);
		km.setLayout(new BoxLayout(km, BoxLayout.PAGE_AXIS));
		
		JLabel lblKm = new JLabel("Il. km");
		km.add(lblKm);
		
		textKm = new JTextField();
		textKm.setText("0");
		textKm.setColumns(10);
		km.add(textKm);
		
		JPanel idle = new JPanel();
		idle.setPreferredSize(new Dimension(60, 35));
		params.add(idle);
		idle.setLayout(new BoxLayout(idle, BoxLayout.PAGE_AXIS));
		
		JLabel lblIdle = new JLabel("Godz. post.");
		idle.add(lblIdle);
		
		textIdle = new JTextField();
		textIdle.setText("0");
		textIdle.setColumns(10);
		idle.add(textIdle);
		
		JPanel text = new JPanel();
		getContentPane().add(text);
		
		JPanel whatDone = new JPanel();
		whatDone.setPreferredSize(new Dimension(200, 35));
		text.add(whatDone);
		whatDone.setLayout(new BoxLayout(whatDone, BoxLayout.PAGE_AXIS));
		
		JLabel lblWhatDo = new JLabel("Co robi\u0142");
		whatDone.add(lblWhatDo);
		
		textWhatDo = new JTextField();
		whatDone.add(textWhatDo);
		textWhatDo.setColumns(10);
		
		JPanel comment = new JPanel();
		comment.setPreferredSize(new Dimension(200, 35));
		text.add(comment);
		comment.setLayout(new BoxLayout(comment, BoxLayout.PAGE_AXIS));
		
		JLabel lblComment = new JLabel("Uwagi");
		comment.add(lblComment);
		
		textComment = new JTextField();
		textComment.setColumns(10);
		comment.add(textComment);
		
		textCar.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				ListCars list=new ListCars();
				
				Vector<String> tmp=new Vector<String>();
				tmp=list.selectCar();
				
				if(tmp.size()>0)
				{
				carParams =tmp;
				textCar.setText(carParams.get(1)+" "+carParams.get(2));
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}
	
	public Vector<String> addRow(){
		
		setTitle("Dodaj pozycje faktyry");
		row=new Vector<String>();
		
		JButton btnAdd = new JButton("Dodaj");
		getContentPane().add(btnAdd);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				isSet();
				
					if(isEmpty==0){
					row=rowSet();
	
					dispose();
					}
				};
		});
		
		setVisible(true);
		
		return row;
	}
	
	public Vector<String> editRow(Vector<String> rowEdit){
		
		setTitle("Modyfikuj pozycje faktury");
		
		row=new Vector<String>();
		
		carParams.add(rowEdit.get(0));
		carParams.add(rowEdit.get(1));
		carParams.add(rowEdit.get(2));
		
		textCar.setText(carParams.get(1)+" "+carParams.get(2));
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		
		try {
			c.setTime(df.parse(rowEdit.get(3)));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		datePicker.getModel().setYear(c.get(Calendar.YEAR));
		datePicker.getModel().setMonth(c.get(Calendar.MONTH));
		datePicker.getModel().setDay(c.get(Calendar.DAY_OF_MONTH));
		datePicker.getModel().setSelected(true);
				
		textWhatDo.setText(rowEdit.get(4));
		textHour.setText(rowEdit.get(5));
		textHour50.setText(rowEdit.get(6));
		textHour100.setText(rowEdit.get(7));
		textHour200.setText(rowEdit.get(8));
		textKm.setText(rowEdit.get(9));
		textIdle.setText(rowEdit.get(10));
		textComment.setText(rowEdit.get(11));	
		
		
		JButton btnEdit = new JButton("Zmieñ");
		getContentPane().add(btnEdit);
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				isSet();
				
				if(isEmpty==0){
				row=rowSet();
				row.add(rowEdit.get(12));
				dispose();
				}

			}
		});
		
		setVisible(true);
		
		return row;
	}
	
	private void isSet(){

		if(carParams.size()==0){
		isEmpty=1;
		JOptionPane.showMessageDialog(null, "Samochód nie zoasta³ wybrany");
		}else{
		if(!datePicker.getModel().isSelected()){
			isEmpty=1;
			JOptionPane.showMessageDialog(null, "Data nie wybrana");
			}else{
			isEmpty=0;	
			}
		}
	}
	
	private Vector<String> rowSet(){
		
		Vector<String> row=new Vector<String>();
		
		Date selectedDate = (Date) datePicker.getModel().getValue();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		row.add(carParams.get(0));//1
		row.add(carParams.get(1));//2
		row.add(carParams.get(2));//3
		row.add(df.format(selectedDate));//4
		row.add(textWhatDo.getText());//5
		row.add(textHour.getText());//6
		row.add(textHour50.getText());//7
		row.add(textHour100.getText());//8
		row.add(textHour200.getText());//9
		row.add(textKm.getText());//10
		row.add(textIdle.getText());//11
		row.add(textComment.getText());//12
		
		return row;
	}
}
