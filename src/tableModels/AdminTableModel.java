package tableModels;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import models.Admin;

@SuppressWarnings("serial")
public class AdminTableModel  extends AbstractTableModel {
	
	private final String[] columnNames = {"Name", "Last Name", "JMBG", "Gender", "Wage"};
	private ArrayList<Admin> adminMap;
	
	
	public AdminTableModel() {
		super();
		this.adminMap = new ArrayList<Admin>();
	}
	
	
	public AdminTableModel(ArrayList<Admin> list) {
		super();
		this.adminMap = new ArrayList<Admin>();
		this.adminMap.addAll(list);
	}


	@Override
	public int getRowCount() {
		return this.adminMap.size();
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
		if (rowIndex >= this.adminMap.size()) return null;
		Admin admin = adminMap.get(rowIndex);
		Object valueR = null;
		switch (columnIndex) {
		case 0: valueR = admin.getFirstName();
				break;
		case 1: valueR = admin.getFamilyName();
				break;
		case 2: valueR = admin.getJmbg();
				break;
		case 3: valueR = admin.getGender();
				break;
		case 4: valueR = admin.getWage();
				break;
		default : valueR = null;
		}
		return valueR;
	}


	public String[] getColumnNames() {
		return columnNames;
	}
	
	public ArrayList<Admin> getAdmins(){
		return adminMap;
	}
	

	
	
}
