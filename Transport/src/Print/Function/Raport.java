package Print.Function;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import Print.MySQL.MySQL_Print;

public class Raport {
	
	JasperPrint mainPrint;
	
	public Raport() {
		// TODO Auto-generated constructor stub
		mainPrint=new JasperPrint();
		
	}
	
	public void print(String[] FvId){
		
		MySQL_Print sqlPrint=new MySQL_Print();
		
		Map parameters = new HashMap();
		
		parameters=sqlPrint.returnParams(FvId[0]);
		
		ArrayList<DataBeanFv> dataBeanList=sqlPrint.returnFvRows(FvId[0]);
		
		 try {
			InputStream inputStream = new FileInputStream ("reports/faktura.jrxml");

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
	
	        JasperDesign jasperDesign;
			try {
				jasperDesign = JRXmlLoader.load(inputStream);
				
		        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		        
		        mainPrint= JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
    
				
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		if(FvId.length>1){
			
			for(int i=1; i<FvId.length; i++){
				
				Map parameters2 = new HashMap();
				
				parameters2=sqlPrint.returnParams(FvId[i]);
				
				ArrayList<DataBeanFv> dataBeanList2=sqlPrint.returnFvRows(FvId[i]);
				
				 try {
					InputStream inputStream = new FileInputStream ("reports/faktura.jrxml");

					JRBeanCollectionDataSource beanColDataSource2 = new JRBeanCollectionDataSource(dataBeanList2);
			
			        JasperDesign jasperDesign;
			        
					try {
						jasperDesign = JRXmlLoader.load(inputStream);
						
				        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
				        
				        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters2, beanColDataSource2);
				        
				        List pages=jasperPrint.getPages();
				        
				        
				        
				        for(int j=0;j<pages.size();j++){
				        	JRPrintPage object = (JRPrintPage) pages.get(j);
				        	
				        	mainPrint.addPage(object);
				        }
				        
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
	 

		 JasperViewer.viewReport(mainPrint, false);
	}

}
