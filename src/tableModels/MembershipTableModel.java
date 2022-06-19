package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Membership;

@SuppressWarnings("serial")
public class MembershipTableModel  extends AbstractTableModel {

	private final String[] columnNames = {"Name", "Price"};
	private ArrayList<Membership> membershipMap;
	
	
	public MembershipTableModel() {
		super();
		this.membershipMap = new ArrayList<Membership>();
	}
	
	
	public MembershipTableModel(ArrayList<Membership> list) {
		super();
		this.membershipMap = new ArrayList<Membership>();
		this.membershipMap.addAll(list);
	}


	@Override
	public int getRowCount() {
		return this.membershipMap.size();
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
		if (rowIndex >= this.membershipMap.size()) return null;
		Membership membership = membershipMap.get(rowIndex);
		Object valueR = null;
		switch (columnIndex) {
		case 0: valueR = membership.getName();
				break;
		case 1: valueR = membership.getPrice();
				break;
		default : valueR = null;
		}
		return valueR;
	}


	public String[] getColumnNames() {
		return columnNames;
	}
	
	public ArrayList<Membership> getMemberships(){
		return membershipMap;
	}
	

	
	
}
