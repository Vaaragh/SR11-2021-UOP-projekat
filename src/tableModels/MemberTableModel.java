package tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Member;

@SuppressWarnings("serial")
public class MemberTableModel extends AbstractTableModel {
	
	private final String[] columnNames = {"Name", "Last Name", "JMBG", "Membership Type", "Active"};
	private ArrayList<Member> memberMap;
	
	
	public MemberTableModel() {
		super();
		this.memberMap = new ArrayList<Member>();
	}
	
	
	public MemberTableModel(ArrayList<Member> list) {
		super();
		this.memberMap = new ArrayList<Member>();
		this.memberMap.addAll(list);
	}


	@Override
	public int getRowCount() {
		return this.memberMap.size();
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


	//"Name", "Last Name", "JMBG", "Membership Type", "Active"
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex >= this.memberMap.size()) return null;
		Member member = memberMap.get(rowIndex);
		Object valueR = null;
		switch (columnIndex) {
		case 0: valueR = member.getFirstName();
				break;
		case 1: valueR = member.getFamilyName();
				break;
		case 2: valueR = member.getJmbg();
				break;
		case 3: valueR = member.getMembershipType().getName();
				break;
		case 4: valueR = member.isActive();
				break;
		default : valueR = null;
		}
		return valueR;
	}


	public String[] getColumnNames() {
		return columnNames;
	}
	
	public ArrayList<Member> getMembers(){
		return memberMap;
	}
	

	
	
}
