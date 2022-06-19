package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Book;

public class BookTableModel extends AbstractTableModel {

	private final String[] columnNames = {"Name", "Author", "Genre", "Language"};
	private ArrayList<Book> bookMap;
	
	
	public BookTableModel() {
		super();
		this.bookMap = new ArrayList<Book>();
	}
	
	
	public BookTableModel(ArrayList<Book> list) {
		super();
		this.bookMap = new ArrayList<Book>();
		this.bookMap.addAll(list);
	}


	@Override
	public int getRowCount() {
		return this.bookMap.size();
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
		if (rowIndex >= this.bookMap.size()) return null;
		Book book = bookMap.get(rowIndex);
		Object valueR = null;
		switch (columnIndex) {
		case 0: valueR = book.getOriginalTitle();
				break;
		case 1: valueR = book.getAuthor();
				break;
		case 2: valueR = book.getGenre().getGenreName();
				break;
		case 3: valueR = book.getOriginalLanguage();
				break;
		default : valueR = null;
		}
		return valueR;
	}


	public String[] getColumnNames() {
		return columnNames;
	}
	
	public ArrayList<Book> getBooks(){
		return bookMap;
	}
	

	
	
}