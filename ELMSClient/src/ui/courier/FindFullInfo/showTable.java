package ui.courier.FindFullInfo;

import java.util.ArrayList;

import org.dom4j.Element;

import blservice.orderblservice.Orderblservice;
import ui.table.MyTable;
import ui.table.MyTablePanel;
import util.MyDate;
import vo.order.OrderVO;

@SuppressWarnings("serial")
public class showTable extends MyTablePanel {

	private Orderblservice bl;
	private ArrayList<OrderVO> pre;

	private static final int COLUMN_NUM = 6;

	public showTable(Element config, Orderblservice bl) {
		
		super(config);
		this.bl = bl;
		if(bl==null){
			System.out.println("null");
		}
		
		initialTitleAndColumn(config);
		initTable();
		initScrollerPane();
		this.add(rollpane);

		
	}

	@Override
	protected void initialTitleAndColumn(Element config) {
		columnNames = MyTablePanel.getColumnName(config.attributeValue("column"));

		MyDate date = MyDate.getNowTime();
		String date1 = MyDate.toString(date);
		System.out.println(date1);

		// 获得系统时间
		pre = bl.getOrderVO(date);

		if (pre == null || pre.isEmpty()) {
			return;
		}

		data = new String[pre.size()][COLUMN_NUM];

		if (pre != null) {
			for (int i = 0; i < pre.size(); i++) {
				// 订单号 收件人 收件地址 寄件人 寄件地址 运费 ??????????考虑的形式待议
				data[i][0] = pre.get(i).ID;
				data[i][1] = pre.get(i).sender.getName();
				data[i][2] = pre.get(i).sender.getAddress();
				data[i][3] = pre.get(i).receiver.getName();
				data[i][4] = pre.get(i).receiver.getAddress();
				data[i][5] = String.valueOf(pre.get(i).otherMes.getOrderCost());
			}
		}

	}

	@Override
	protected void initTable() {
		table = new MyTable(columnNames, data) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if ((column >= 0) && (column <= 6)) {
					return false;
				}
				return true;
			}
		};
		int[] columnLen = { 100, 300, 100, 100, 100, 100 };
		setRowAndColumnLen(40, columnLen);

	}

	@Override
	public void updateTableMes() {
	}

}