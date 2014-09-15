package window.fv;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;

import window.cars.ListCars;
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
	
	
	public FormFvRow() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		setSize(450, 300);
		carParams=new Vector<String>();
		
		setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		JPanel main = new JPanel();
		getContentPane().add(main);
		
		JPanel car = new JPanel();
		main.add(car);
		car.setPreferredSize(new Dimension(200, 35));
		car.setLayout(new BoxLayout(car, BoxLayout.PAGE_AXIS));
		
		JLabel lblCar = new JLabel("Samoch\u00F3d");
		car.add(lblCar);
		
		textCar = new JTextField();
		textCar.setEnabled(false);
		car.add(textCar);
		
		textCar.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				ListCars list=new ListCars();
				
				carParams =list.selectCar();
				textCar.setText(carParams.get(0)+" "+carParams.get(1));
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
		
		textCar.setColumns(10);
		
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
	
	}
	
	public Vector<String> addRow(){
		
		setTitle("Dodaj pozycje faktyry");
		
		Vector<String> row=new Vector<String>();
		
		JButton btnAdd = new JButton("Dodaj");
		getContentPane().add(btnAdd);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
				
				dispose();
			}
		});
		
		setVisible(true);
		
		return row;
	}
}
