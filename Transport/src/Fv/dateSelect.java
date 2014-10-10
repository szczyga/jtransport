package Fv;

import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JButton;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class dateSelect extends JDialog {
	
	private JDatePickerImpl dateStart;
	private SpringLayout springLayoutStart;
	
	JPanel dStart;
	String strDate;
	
	public dateSelect() {
	
		setSize(200, 105);
		setModalityType(ModalityType.APPLICATION_MODAL);//blokowanie prze³¹czania w dó³
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
	    UtilDateModel modelStart = new UtilDateModel();
	    JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStart);
		
		dStart = new JPanel();
		dStart.setLayout(new BoxLayout(dStart, BoxLayout.PAGE_AXIS));
		
		JLabel lblDStart = new JLabel("Wybierz datê");
		dStart.add(lblDStart);
		dateStart = new JDatePickerImpl(datePanelStart);
		dStart.add(dateStart);
		springLayoutStart = (SpringLayout) dateStart.getLayout();
		springLayoutStart.putConstraint(SpringLayout.WEST, dateStart.getJFormattedTextField(), 0, SpringLayout.WEST, dateStart);
		dateStart.setPreferredSize(new Dimension(202, 34));
		
		getContentPane().add(dStart);
		

	}
	
	public String getDate(){
		
		strDate=null;
		
		JButton btnSelect = new JButton("Wybierz");
		dStart.add(btnSelect);
		
	    btnSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				
				if(dateStart.getModel().getValue()!=null)
				strDate=df.format((Date) dateStart.getModel().getValue());
				
				dispose();
			}
		});	
		
	    setVisible(true);
		return strDate;
	}

}
