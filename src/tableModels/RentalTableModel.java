package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Rental;

public class RentalTableModel  extends AbstractTableModel {

	private final String[] columnNames = {"Member", "Rent Date", "Due Date", "Employee"};
	private ArrayList<Rental> rentalMap;
	
	
	public RentalTableModel() {
		super();
		this.rentalMap = new ArrayList<Rental>();
	}
	
	
	public RentalTableModel(ArrayList<Rental> list) {
		super();
		this.rentalMap = new ArrayList<Rental>();
		this.rentalMap.addAll(list);
	}


	@Override
	public int getRowCount() {
		return this.rentalMap.size();
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
		if (rowIndex >= this.rentalMap.size()) return null;
		Rental rental = rentalMap.get(rowIndex);
		Object valueR = null;
		switch (columnIndex) {
		case 0: valueR = rental.getMember().getFirstName();
				break;
		case 1: valueR = rental.getRentalDate();
				break;
		case 2: valueR = rental.getDueDate();
				break;
		case 3: valueR = rental.getEmployee().getFirstName();
				break;
		default : valueR = null;
		}
		return valueR;
	}


	public String[] getColumnNames() {
		return columnNames;
	}
	
	public ArrayList<Rental> getRentals(){
		return rentalMap;
	}
	

	
	
}