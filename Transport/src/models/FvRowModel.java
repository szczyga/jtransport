package models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class FvRowModel extends AbstractTableModel {
	
	Vector<String[]> cache;
	String[] hedders;
	Vector<String> carId;
	int colCount;

	public FvRowModel() {
		// TODO Auto-generated constructor stub
		cache =new Vector<String[]>();
		carId=new Vector<String>();
		
		colCount=11;
		
		String[] hedd={
				"Marka",
				"Nr rej",
				"Data",
				"Co robi≥",
				"Il. godzin",
				"Il. godzin 50",
				"Il. godzin 100",
				"Il. godzin 200",
				"Il. km",
				"Godz. post.",
				"Uwagi"
		};
		
		hedders=hedd;
		
		
		
		String[] data={
			"Jelcz",
			"Era 1234",
			"11-11-2014",
			"Jeüdzi≥ tu i tam",
			"8",
			"8",
			"8",
			"8",
			"24",
			"4",
			"Jakieú tam uwagi"
		};
		
		cache.add(data);		
		carId.add("1");
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colCount;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return cache.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return cache.get(row)[col];
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return hedders[column];
	}
	
	
	public void addRow(Vector<String> row){
		
		carId.add(row.get(0));
		
		String[] rowSt={
				row.get(1),
				row.get(2),
				row.get(3),
				row.get(4),
				row.get(5),
				row.get(6),
				row.get(7),
				row.get(8),
				row.get(9),
				row.get(10),
				row.get(11)
				};
		
		cache.add(rowSt);
		fireTableChanged(null);
	}
}
