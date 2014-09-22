package Print.Function;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import Print.MySQL.MySQL_Print;

public class Raport {
	
	public void print(String FvId){
		
		 try {
			InputStream inputStream = new FileInputStream ("reports/faktura.jrxml");
			
			Map parameters = new HashMap();
			
			MySQL_Print sqlPrint=new MySQL_Print();
			
			parameters=sqlPrint.returnParams(FvId);
			ArrayList<DataBeanFv> dataBeanList=sqlPrint.returnFvRows(FvId);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
			
			
	        JasperDesign jasperDesign;
			try {
				jasperDesign = JRXmlLoader.load(inputStream);
				
		        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		        
		        JasperViewer.viewReport(jasperPrint, false);	
		        
				
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
