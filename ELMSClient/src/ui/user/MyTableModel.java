package ui.user;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MyTableModel extends DefaultTableModel {

	/**
	 * 标题
	 */
	String[] headers;
	/**
	 * 表格值
	 */
	Object[][] values;

	boolean[][] isCellEditable;

	public MyTableModel(Object[] headers, Object[][] data) {
		super(data, headers);

		isCellEditable = new boolean[data.length][data[0].length];
		for (int i = 0; i < isCellEditable.length; i++) {
			for (int j = 0; j < isCellEditable[0].length; j++) {
				isCellEditable[i][j] = false;
			}

		}

	}

	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/**
	 * 设置某一列可否编辑
	 * 
	 * @param flag
	 * @param column
	 */
	public void setCellInColumEdit(boolean flag, int column) {
		for (int i = 0; i < isCellEditable.length; i++) {
			isCellEditable[i][column] = flag;
		}

	}

	public void setCellInRowEdit(boolean flag, int row) {
		for (int i = 0; i < isCellEditable[0].length; i++) {
			isCellEditable[row][i] = flag;
		}

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return isCellEditable[rowIndex][columnIndex];
	}

}