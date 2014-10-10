package transport;


import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import sun.swing.table.DefaultTableCellHeaderRenderer;

public class Fun {

	public static void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 50; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width, width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	     
	    }
	}
	
	public static void centerOneColumnText(JTable table, int colNr){
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(colNr).setCellRenderer( centerRenderer );
		
		DefaultTableCellHeaderRenderer headerRenderer=new DefaultTableCellHeaderRenderer();
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(colNr).setHeaderRenderer(headerRenderer);	
	}
	
	public static void setRightOneColumnText(JTable table, int colNr){
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
		table.getColumnModel().getColumn(colNr).setCellRenderer( centerRenderer );
	}
	
	public static void setRightColumns(JTable table, int colStart, int colAmount){
		
		for(int i=colStart; i<colAmount; i++){
		setRightOneColumnText(table, i);	
		}
		
	}
	
	public static String translacja(double l) {
		
		
		long liczba=(long)l;
		int num=(int) Math.round(((l-liczba)*100));
		
		
	      String[] jedno�ci = { "", "jeden ", "dwa ", "trzy ", "cztery ",
	              "pi�� ", "sze�� ", "siedem ", "osiem ", "dziewi�� ", };

	      String[] nastki = { "", "jedena�cie ", "dwana�cie ", "trzyna�cie ",
	              "czterna�cie ", "pi�tna�cie ", "szesna�cie ", "siedemna�cie ",
	              "osiemna�cie ", "dziewi�tna�cie ", };

	      String[] dziesi�tki = { "", "dziesi�� ", "dwadzie�cia ",
	              "trzydzie�ci ", "czterdzie�ci ", "pi��dziesi�t ",
	              "sze��dziesi�t ", "siedemdziesi�t ", "osiemdziesi�t ",
	              "dziewi��dziesi�t ", };

	      String[] setki = { "", "sto ", "dwie�cie ", "trzysta ", "czterysta ",
	              "pi��set ", "sze��set ", "siedemset ", "osiemset ",
	              "dziewi��set ", };

	      String[][] grupy = { { "", "", "" },
	              { "tysi�c ", "tysi�ce ", "tysi�cy " },
	              { "milion ", "miliony ", "milion�w " },
	              { "miliard ", "miliardy ", "miliard�w " },
	              { "bilion ", "biliony ", "bilion�w " },
	              { "biliard ", "biliardy ", "biliard�w " },
	              { "trylion ", "tryliony ", "tryliard�w " }, };

	      // INICJACJA ZMIENNYCH
	      long j = 0/* jedno�ci */, n = 0/* nastki */, d = 0/* dziesi�tki */, s = 0/* setki */, g = 0/* grupy */, k = 0/* ko�c�wwki */, p =0 /*poprawka*/;
	      String s�ownie = "";
	      String znak = "";

	      // OPERACJA DOTYCZ�CA ZNAKU

	      if (liczba < 0) {
	          znak = "minus ";
	          liczba = -liczba; // bezwgl�dna warto�� poniewa�, je�li b�dziemy
	                              // operowa� na liczbie z minusem tablica b�dzie
	                              // przyjmowa�a warto�ci ujemne i zwr�ci nam b��d
	      }
	      if (liczba == 0) {
	          znak = "zero";
	      }

	      // P�TLA G��WNA
	      while (liczba != 0) {
	          s = liczba % 1000 / 100;
	          d = liczba % 100 / 10;
	          j = liczba % 10;

	          if (d == 1 & j > 0) // if zajmuj�cy si� nastkami
	          {
	              n = j;
	              d = 0;
	              j = 0;
	          } else {
	              n = 0;
	          }

	          // <---- KO�C�WKI

	          if (j == 1 & s + d + n == 0) {
	              k = 0;

	              if (s + d == 0) // je�li nie b�dzie dziesi�tek ani setek, wtedy
	                              // otrzymamy sam� grup�
	              { // przyk�adowo 1000 - wy�wietli nam si� "tysi�c", je�li
	                  // zakomentujemy tego if'a to otrzymamy "jeden tysi�c"
	                  j = 0;
	              }
	          } else if (j == 2) {
	              k = 1;
	          } else if (j == 3) {
	              k = 1;
	          } else if (j == 4) {
	              k = 1;
	          } else {
	              k = 2;
	          }

	          // KONIEC KO�C�WEK -->
	          
	          // DROBNA POPRAWKA - �EBY NIE ZWRACA�O PUSTYCH LICZB(WARTO�CI)
	          p=0;
	          if(d==0 & s==0 & j==0)
	          {
	              p=g;
	              g=0;
	              k=0;
	          }
	          
	          s�ownie = setki[(int) s] + dziesi�tki[(int) d] + nastki[(int) n]
	                  + jedno�ci[(int) j] + grupy[(int) g][(int) k] + s�ownie;

	          // POZBYWAMY SI� TYCH LICZBY KT�RE JU� PRZEROBILI�MY czyli
	          // przyk�adowo z 132132 zostaje nam 132 do obr�bki
	          liczba = liczba / 1000;
	          // ORAZ ZWI�KSZAMY G KT�RE ODPOWIEDZIALNE JEST ZA NUMER POLA W
	          // TABLICY WIELOWYMIAROWEJ
	          g = g + 1 + p;
	      }
	      // KONIEC P�TLI G��WNEJ

	      // DODANIE ZNAKU I ZWR�CENIE METODY
	      s�ownie = znak + s�ownie;
	      return s�ownie+" "+String.valueOf(num)+"/100 z�";

	  }
}
