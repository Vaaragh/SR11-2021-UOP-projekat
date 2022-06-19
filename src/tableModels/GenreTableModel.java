package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Genre;

public class GenreTableModel extends AbstractTableModel {

	private final String[] columnNames = {"Name", "ID"};
	private ArrayList<Genre> genreMap;
	
	
	public GenreTableModel() {
		super();
		this.genreMap = new ArrayList<Genre>();
	}
	
	
	public GenreTableModel(ArrayList<Genre> list) {
		super();
		this.genreMap = new ArrayList<Genre>();
		this.genreMap.addAll(list);
	}


	@Override
	public int getRowCount() {
		return this.genreMap.size();
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
		if (rowIndex >= this.genreMap.size()) return null;
		Genre genre = genreMap.get(rowIndex);
		Object valueR = null;
		switch (columnIndex) {
		case 0: valueR = genre.getGenreName();
				break;
		case 1: valueR = genre.getIdentification();
				break;
		default : valueR = null;
		}
		return valueR;
	}


	public String[] getColumnNames() {
		return columnNames;
	}
	
	public ArrayList<Genre> getGenres(){
		return genreMap;
	}
	

	
	
}