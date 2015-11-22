package po;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 库存信息
 * @author ymc
 *
 */
public class StoreMessagePO implements Serializable{
	
	public StoreMessagePO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 库存数量
	 */
	private int number;
	/**
	 * 所在城市
	 */
	public String location;
	/**
	 * 入库单集合
	 */
	
	private ArrayList<InStoreDocPO> inStoreDocs;
	/**
	 * 出库单集合
	 */
	private ArrayList<OutStoreDocPO> OutStoreDocs;
	/**
	 * 
	 * @param inStoreDocs
	 * @param outStoreDocs
	 */
	
	public int getNumber() {
		return number;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public StoreMessagePO(int number, String location, ArrayList<InStoreDocPO> inStoreDocs,
			ArrayList<OutStoreDocPO> outStoreDocs) {
		super();
		this.number = number;
		this.location = location;
		this.inStoreDocs = inStoreDocs;
		OutStoreDocs = outStoreDocs;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public ArrayList<InStoreDocPO> getInStoreDocs() {
		return inStoreDocs;
	}
	public void setInStoreDocs(ArrayList<InStoreDocPO> inStoreDocs) {
		this.inStoreDocs = inStoreDocs;
	}
	public ArrayList<OutStoreDocPO> getOutStoreDocs() {
		return OutStoreDocs;
	}
	public void setOutStoreDocs(ArrayList<OutStoreDocPO> outStoreDocs) {
		OutStoreDocs = outStoreDocs;
	}
	
}
