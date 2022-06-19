package tableModels;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.table.AbstractTableModel;

import tools.ToolKit;

public class CustomTable extends AbstractTableModel {
	
	private Class objectClass;
	private List<Field> fields;
	private String[] columns;
	private HashMap<String,?> enities;
	
	public CustomTable(Class clss) {
		this.objectClass = clss;
		this.fields =  ToolKit.getAllFields(this.objectClass);
		this.columns = this.listFields();
		
	}
	
	private String[] listFields() {
		StringBuilder stb = new StringBuilder();
		for (Field f: this.fields) {
			stb.append(f.getName().toString() + "~");
		}
		return stb.toString().split("~");
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
