package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Librarian;

@SuppressWarnings("serial")
public class LibrarianTableModel extends AbstractTableModel {

	private final String[] columnNames = {"Name", "Last Name", "JMBG", "Gender", "Wage"};
	private ArrayList<Librarian> librarianMap;
	
	
	public LibrarianTableModel() {
		super();
		this.librarianMap = new ArrayList<Librarian>();
	}
	
	
	public LibrarianTableModel(ArrayList<Librarian> list) {
		super();
		this.librarianMap = new ArrayList<Librarian>();
		this.librarianMap.addAll(list);
	}


	@Override
	public int getRowCount() {
		return this.librarianMap.size();
	}


	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}


	//"Name", "Last Name", "JMBG", "Gender", "Wage"
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex >= this.librarianMap.size()) return null;
		Librarian librarian = librarianMap.get(rowIndex);
		Object valueR = null;
		switch (columnIndex) {
		case 0: valueR = librarian.getFirstName();
				break;
		case 1: valueR = librarian.getFamilyName();
				break;
		case 2: valueR = librarian.getJmbg();
				break;
		case 3: valueR = librarian.getGender();
				break;
		case 4: valueR = librarian.getWage();
				break;
		default : valueR = null;
		}
		return valueR;
	}


	public String[] getColumnNames() {
		return columnNames;
	}
	
	public ArrayList<Librarian> getLibrarians(){
		return librarianMap;
	}
	

	
	
}
