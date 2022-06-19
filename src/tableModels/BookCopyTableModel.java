package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.BookCopy;

@SuppressWarnings("serial")
public class BookCopyTableModel  extends AbstractTableModel {

	private final String[] columnNames = {"Title", "Binding", "Pages", "Language"};
	private ArrayList<BookCopy> bookCopyMap;
	
	
	public BookCopyTableModel() {
		super();
		this.bookCopyMap = new ArrayList<BookCopy>();
	}
	
	
	public BookCopyTableModel(ArrayList<BookCopy> list) {
		super();
		this.bookCopyMap = new ArrayList<BookCopy>();
		this.bookCopyMap.addAll(list);
	}


	@Override
	public int getRowCount() {
		return this.bookCopyMap.size();
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


	//"Name", "Price"
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex >= this.bookCopyMap.size()) return null;
		BookCopy bookCopy = bookCopyMap.get(rowIndex);
		Object valueR = null;
		switch (columnIndex) {
		case 0: valueR = bookCopy.getTitle();
				break;
		case 1: valueR = bookCopy.getBinding();
				break;
		case 2: valueR = bookCopy.getNumberOfPages();
				break;
		case 3: valueR = bookCopy.getPrintLanguage();
				break;
		default : valueR = null;
		}
		return valueR;
	}


	public String[] getColumnNames() {
		return columnNames;
	}
	
	public ArrayList<BookCopy> getBookCopys(){
		return bookCopyMap;
	}
	

	
	
}