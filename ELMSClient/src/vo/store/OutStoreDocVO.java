  package vo.store;

import java.util.ArrayList;

import util.City;
import util.DocState;
import util.DocType;
import util.MyDate;
import util.TransferWay;
import vo.DocVO;

/**
 * 出库单
 * @author ymc
 *
 */
public class OutStoreDocVO extends DocVO{
	/**
	 * 订单
	 */
	public ArrayList<String> orders;
	/**
	 * 目的地
	 */
	public City loc;
	/**
	 * 对应中转单编号
	 */
	public String transferDoc;
	/**
	 * 装运方式
	 */
	public TransferWay shipWay;
	public OutStoreDocVO() {
	}
	/**
	 * 
	 * @param orderVOs
	 * @param loc
	 * @param time
	 * @param transferDoc
	 * @param shipWay
	 */
	public OutStoreDocVO(String ID,MyDate time, ArrayList<String> orderVOs, City loc, String transferDoc, TransferWay shipWay) {
		//
		super(ID, DocType.outStoreDoc, time, DocState.wait);
		
		
		this.orders = orderVOs;
		this.loc = loc;
		
		this.transferDoc = transferDoc;
		this.shipWay = shipWay;
	}
	
}
