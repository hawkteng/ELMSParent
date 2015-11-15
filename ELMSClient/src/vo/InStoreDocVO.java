package vo;

import java.util.ArrayList;

import util.Date;
import util.DocState;
import util.DocType;

/**
 * 入库单
 * @author ymc
 *
 */
public class InStoreDocVO extends DocVO {
	

	/**
	 * 订单
	 */
	public ArrayList<OrderVO> orderVOs;
	/**
	 * 目的地
	 */
	public String loc;
	
	/**
	 * 位置信息
	 */
	public ArrayList<String> location;
	
	

	public InStoreDocVO(String iD,  Date date,
			ArrayList<OrderVO> orderVOs, String loc, ArrayList<String> location) {
		
		//初始建单状态
		super(iD,DocType.inStoreDoc, date, DocState.wait);
		this.orderVOs = orderVOs;
		this.loc = loc;
		this.location = location;
	}

	public ArrayList<OrderVO> getOrderVOs() {
		return orderVOs;
	}

	public void setOrderVOs(ArrayList<OrderVO> orderVOs) {
		this.orderVOs = orderVOs;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public ArrayList<String> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<String> location) {
		this.location = location;
	}

	
	
	
	
}