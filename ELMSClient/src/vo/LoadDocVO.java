package vo;

import util.Date;
import util.DocState;
import util.DocType;



/**
 * 装车单VO类
 * @author JerryZhang
 *
 */
public class LoadDocVO extends DocVO{
	/**
	 * 本营业厅编号
	 */
	public int YYID;
	/**
	 * 汽运编号
	 */
	public int LoadDocID;
	/**
	 * 到达地
	 */
	public String arriveCity;
	/**
	 * 车辆代号
	 */
	public int CarID;
	/**
	 * 监运员姓名
	 */
	public String Supervisor;
	/**
	 * 押送员代号
	 */
	public String Escort;
	


	public LoadDocVO(String iD, Date date,
			int yYID, int loadDocID, String arriveCity, int carID,
			String supervisor, String escort) {
		super(iD, DocType.loadDoc, date, DocState.wait);
		YYID = yYID;
		LoadDocID = loadDocID;
		this.arriveCity = arriveCity;
		CarID = carID;
		Supervisor = supervisor;
		Escort = escort;
	}

	
}