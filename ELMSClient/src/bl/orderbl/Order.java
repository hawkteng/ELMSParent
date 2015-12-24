package bl.orderbl;

import java.awt.Container;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.DocPO;
import po.order.OrderPO;
import po.order.ReceivePO;
import test.java.other.VOPOchange;
import util.City;
import util.DocType;
import util.MyDate;
import util.ResultMessage;
import vo.DocVO;
import vo.order.OrderSimpleInfoVO;
import vo.order.OrderVO;
import vo.order.PreReceiveVO;
import vo.order.ReceiveVO;
import vo.store.InStoreDocVO;
import vo.store.OutStoreDocVO;
import vo.strategy.EstiDateVO;
import vo.transport.ArriveYYDocVO;
import vo.transport.ArriveZZDocVO;
import vo.transport.LoadDocVO;
import vo.transport.SendGoodDocVO;
import vo.transport.TransferDocVO;
import bl.BusinessLogicDataFactory;
import bl.storebl.StoreController;
import bl.strategybl.StrategyController;
import bl.transportbl.TransportController;
import blservice.strategyblservice.StrategyblService;
import ds.orderdataservice.OrderDataService;

/**
 * @author ymc
 * @version 创建时间：2015年10月27日 下午7:48:05
 *
 */
public class Order {
	private OrderDataService orderData;
	private StrategyblService strategybl;
	private TransportController transportController;
	private StoreController storeController;
	
	public Order(OrderDataService orderData) {
		this.orderData = orderData;
		strategybl = new StrategyController();
	}

	public ResultMessage add(OrderVO vo) {
		OrderPO po = (OrderPO) VOPOchange.VOtoPO(vo);
		try {
			return orderData.add(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

	public ResultMessage checkBarCode(String orderBarCode) {
		OrderPO po = null;
		try {
			po = orderData.getSingleOrderPO(orderBarCode);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (po == null)
			return ResultMessage.NOT_EXIST;
		else
			return ResultMessage.SUCCESS;
	}

	public ArrayList<OrderVO> getOrderVO(MyDate date) {

		ArrayList<OrderPO> pos = new ArrayList<OrderPO>();

		try {
			pos = orderData.getDayOrderPO(date);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(pos==null){
			return null;
		}
		ArrayList<OrderVO> vos = new ArrayList<OrderVO>(pos.size());

		for (OrderPO po : pos) {
			vos.add((OrderVO) VOPOchange.POtoVO(po));
		}

		return vos;
	}

	public ResultMessage del(String orderBarCode) {

		try {
			return orderData.del(orderBarCode);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

	public ArrayList<OrderSimpleInfoVO> getSimpleInfo(String orderBarCode) {
		
		ArrayList<String> poStrings = new ArrayList<String>();
		ArrayList<OrderSimpleInfoVO> orderSimpleInfoVOs = null;
		
		transportController = (TransportController) BusinessLogicDataFactory.getFactory().getTransportblservice();
		storeController = new StoreController();
		
		try {
			poStrings = orderData.getSingleOrderDocs(orderBarCode);
			if(poStrings == null ){
				return null;
			}
			orderSimpleInfoVOs = new ArrayList<OrderSimpleInfoVO>(poStrings.size());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String time = "";
		String place = "";
		DocType type=null;
		for (String poString : poStrings) {
			if(poStrings == null || poString.length() < 3){
				break;
			}
			String tmp = poString.substring(0, 3);
			switch (tmp) {
			//装车单
			case "ZCD":
				LoadDocVO vol = (LoadDocVO) transportController.getByID(poString , DocType.loadDoc);
				place = vol.arriveCity.toString();
				time = MyDate.toString(vol.date);
				type=DocType.loadDoc;
				break;
			//接受单
			case "JSD":
				ArriveYYDocVO voy = (ArriveYYDocVO) transportController.getByID(poString , DocType.arriveYYDoc);
				place = voy.sendCity.toString();
				time = MyDate.toString(voy.date);
				type=DocType.arriveYYDoc;
				break;
			//到达单
			case "DDD":
				ArriveZZDocVO voz = (ArriveZZDocVO) transportController.getByID(poString , DocType.arriveZZDoc);
				place = voz.sendCity.toString();
				time = MyDate.toString(voz.date);
				type=DocType.arriveZZDoc;
				break;
			//中转单
			case "ZZD":
				TransferDocVO vot = (TransferDocVO) transportController.getByID(poString , DocType.transferDoc);
				place = vot.sendCity.toString();
				time =MyDate.toString(vot.date);
				type=DocType.transferDoc;
				break;
			//派送单 
			case "PSD":
				SendGoodDocVO vop = (SendGoodDocVO) transportController.getByID(poString , DocType.sendGoodDoc);
				//传入派送员姓名
				place = vop.sendMan.toString();
				time = MyDate.toString(vop.date);
				type=DocType.sendGoodDoc;
				break;
			//入库单
			case "RKD":
				InStoreDocVO vor = (InStoreDocVO) storeController.getByID(poString , DocType.inStoreDoc);
				place = vor.loc.toString();
				time = MyDate.toString(vor.date);
				type=DocType.loadDoc;
				break;
			//出库单
			case "CKD":
				OutStoreDocVO voc = (OutStoreDocVO) storeController.getByID(poString , DocType.outStoreDoc);
				place = voc.loc.toString();
				time = MyDate.toString(voc.date);
				type=DocType.outStoreDoc;
				break;
			default:
				break;
			}
			orderSimpleInfoVOs.add(new OrderSimpleInfoVO(orderBarCode, place, time,type));
			
		
		
			
		}

		return orderSimpleInfoVOs;
	}

	public OrderVO getFullInfo(String orderBarCode) {
		OrderPO po = null;
		try {
			po = orderData.getSingleOrderPO(orderBarCode);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			OrderVO vo = (OrderVO) VOPOchange.POtoVO(po);
			return vo;
		}
		catch(NullPointerException e){
			return null;
		}
	}

	public ResultMessage receiveInfo(ReceiveVO vo) {
		String orderBarCode = vo.orderBarCode;
		ReceivePO po = (ReceivePO) VOPOchange.VOtoPO(vo);
		try {
			return orderData.receiveInfo(po, orderBarCode);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

	public ResultMessage addDocToList(DocVO vo,ArrayList<String> orderBarCodes) {
		DocPO po = (DocPO) VOPOchange.VOtoPO(vo);
		try {
			return orderData.addDocToList(po, orderBarCodes);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

	public ArrayList<PreReceiveVO> getPreReceive(ArrayList<SendGoodDocVO> daySendDocs) {
		ArrayList<String> barCodes = null;
		ArrayList<PreReceiveVO> pres = null;
		
		if(daySendDocs!= null){
			barCodes = new ArrayList<>(daySendDocs.size());
			pres = new ArrayList<>(daySendDocs.size());
		}
		
		if(barCodes!=null){
			OrderVO tmp = null;
			for(int i = 0;i<barCodes.size();i++){
				tmp = getFullInfo(barCodes.get(i));
//				PreReceiveVO vo = new PreReceiveVO(tmp.ID, tmp.receiver.getAddress(), tmp.receiver.getName());
			}
		}
		return pres;
	}

	public double getEstiDate(City one,City two) {
		EstiDateVO vo = strategybl.getEstiDateVO();
		double[] map = new double[6];
		map[0] = vo.dayInBG;
		map[1] = vo.dayInBN;
		map[2] = vo.dayInBS;
		map[3] = vo.dayInNG;
		map[4] = vo.dayInNS;
		map[5] = vo.dayInSG;
		if(twoPlace(City.BEIJING, City.GUANGZHOU, one, two))
			return map[0];
		else if(twoPlace(City.BEIJING, City.NANJING, one, two))
			return map[1];
		else if(twoPlace(City.BEIJING, City.SHANGHAI, one, two))
			return map[2];
		else if(twoPlace(City.NANJING, City.GUANGZHOU, one, two))
			return map[3];
		else if(twoPlace(City.NANJING, City.SHANGHAI, one, two))
			return map[4];
		else if(twoPlace(City.SHANGHAI, City.GUANGZHOU, one, two))
			return map[5];
					 
		return 0;
	}
	private boolean twoPlace(City target1,City target2,City one , City two) {
		if(target1==one&&target2==two)
			return true;
		
		if(target1==two&&target2==one)
			return true;
		
		return false;
	}
	public ResultMessage setEstiDate(double day,City one,City two) {
		EstiDateVO vo =strategybl.getEstiDateVO();
		if(twoPlace(City.BEIJING, City.GUANGZHOU, one, two))
			vo.dayInBG =( vo.dayInBG+day)/2;
		else if(twoPlace(City.BEIJING, City.NANJING, one, two))
			vo.dayInBN =( vo.dayInBN+day)/2;
		else if(twoPlace(City.BEIJING, City.SHANGHAI, one, two))
			vo.dayInBS =( vo.dayInBS+day)/2;
		else if(twoPlace(City.NANJING, City.GUANGZHOU, one, two))
			vo.dayInNG =( vo.dayInNG+day)/2;
		else if(twoPlace(City.NANJING, City.SHANGHAI, one, two))
			vo.dayInNS =( vo.dayInNS+day)/2;
		else if(twoPlace(City.SHANGHAI, City.GUANGZHOU, one, two))
			vo.dayInSG =( vo.dayInSG+day)/2;
		return strategybl.setEstiDateVO(vo);
	}

}
