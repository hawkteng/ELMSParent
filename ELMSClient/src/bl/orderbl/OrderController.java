package bl.orderbl;

import java.rmi.Remote;
import java.util.ArrayList;

import net.RMIManage;
import util.City;
import util.DataServiceType;
import util.DocState;
import util.DocType;
import util.MyDate;
import util.ResultMessage;
import vo.DocVO;
import vo.order.OrderSimpleInfoVO;
import vo.order.OrderVO;
import vo.order.PreReceiveVO;
import vo.order.ReceiveVO;
import bl.BusinessController;
import bl.transportbl.TransportController;
import blservice.orderblservice.Orderblservice;
import ds.orderdataservice.OrderDataService;
import exception.ExceptionHandler;
 /** 
 * 订单controller类
 * @author czq 
 * @version 2015年11月15日 上午9:22:18 
 */
public class OrderController extends BusinessController implements Orderblservice{

	private TransportController transportController;
	private OrderDataService orderData;
	private Order order ;
	public OrderController() {
		orderData  = (OrderDataService) RMIManage.getDataService(DataServiceType.OrderDataService);
		order = new Order(orderData);
		myType = DataServiceType.OrderDataService;
		transportController = new TransportController();
	}
	@Override
	public ResultMessage add(OrderVO vo) {
		try {
			return order.add(vo);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.add(vo);
				} catch (Exception e1) {}
			}
		}
		return ResultMessage.FAIL;
	}

	@Override
	public ResultMessage checkBarCode(String orderBarCode) {
		try {
			return order.checkBarCode(orderBarCode);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.checkBarCode(orderBarCode);
				} catch (Exception e1) {}
			}
		}return ResultMessage.FAIL;
	}

	@Override
	public ArrayList<OrderVO> getOrderVO(MyDate date) {
		try {
			return order.getOrderVO(date);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.getOrderVO(date);
				} catch (Exception e1) {}
			}
		}return null;
	}

	@Override
	public ResultMessage del(String orderBarCode) {
		try {
			return order.del(orderBarCode);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.del(orderBarCode);
				} catch (Exception e1) {}
			}
		}return ResultMessage.FAIL;
	}

	@Override
	public ArrayList<OrderSimpleInfoVO> getSimpleInfo(String orderBarCode) {
		try {
			return order.getSimpleInfo(orderBarCode);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.getSimpleInfo(orderBarCode);
				} catch (Exception e1) {}
			}
		}return null;
	}

	@Override
	public OrderVO getFullInfo(String orderBarCode) {
		try {
			return order.getFullInfo(orderBarCode);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.getFullInfo(orderBarCode);
				} catch (Exception e1) {}
			}
		}return null;
	}

	@Override
	public ResultMessage receiveInfo(ReceiveVO vo) {
		try {
			return order.receiveInfo(vo);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.receiveInfo(vo);
				} catch (Exception e1) {}
			}
		}return ResultMessage.FAIL;
	}

	@Override
	public ResultMessage addDocToList(DocVO vo,ArrayList<String> orderBarCodes) {
		try {
			return order.addDocToList(vo,orderBarCodes);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.addDocToList(vo,orderBarCodes);
				} catch (Exception e1) {}
			}
		}return ResultMessage.FAIL;
	}

	@Override
	public ArrayList<DocVO> getDocLists(DocType type) {
		
		try {
			return order.getDocLists();
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.getDocLists();
				} catch (Exception e1) {}
			}
		}return null;
	}

	@Override
	public ResultMessage changeDocsState(ArrayList<String> docsID, DocType type, DocState state) {
		try {
			return order.changeDocsState(docsID , type , state);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.changeDocsState(docsID , type , state);
				} catch (Exception e1) {}
			}
		}return ResultMessage.FAIL;
	}
	@Override
	public ResultMessage changeOneDocState(String docID, DocType type, DocState state) {
		try {
			return order.changeOneDocState(docID , type , state);
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.changeOneDocState(docID , type , state);
				} catch (Exception e1) {}
			}
		}return ResultMessage.FAIL;
	}
	@Override
	public ArrayList<PreReceiveVO> getPreReceive() {
		try {
			return order.getPreReceive(transportController.getDaySendDocs(MyDate.getNowTime()));
		} catch (Exception e) {
			if(ExceptionHandler.myExceptionHandler(myType, this)){
				try {
					return order.getPreReceive(transportController.getDaySendDocs(MyDate.getNowTime()));
				} catch (Exception e1) {}
			}
		}return null;
	}
	@Override
	public DocVO getByID(String ID, DocType type) {
		return getFullInfo(ID);
	}
	@Override
	public double getEstiDate(City one,City two) {
		return order.getEstiDate(one,two);
	}
	@Override
	public ResultMessage setEstiDate(double day,City one,City two) {
		
		return order.setEstiDate(day,one,two);
	}

	@Override
	public int getDayDocCount(DocType type) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void reinitDataService(Remote dataService) {
		orderData = (OrderDataService) dataService;
		order = new Order(orderData);
	}
	
	
}
