package data.DTManagedata;

import java.rmi.RemoteException;

import po.CarPO;
import po.DriverPO;
import util.Date;
import util.ResultMessage;
import dataSuper.DataServiceHelper;
import dataSuper.DataSuperClass;
import ds.DTManagedataservice.DTManagedateservice;
 /** 
 * 车辆司机信息管理
 * @author czq 
 * @version 2015年11月5日 下午8:45:11 
 */
public class DTManageDataImpl extends DataSuperClass implements DTManagedateservice{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 车辆信息表
	 */
	private final String carTable = "car";
	/**
	 * 司机信息表 
	 */
	private final String driverTable = "driver";
	
	
	public DTManageDataImpl() throws RemoteException {}

	public DriverPO getDriverMes(String ID) throws RemoteException {
		findMes = findFromSQL(driverTable, ID);
		if(findMes == null){
			return null;
		}else{
			return new DriverPO(findMes.get(0), findMes.get(1), Date.getDate(findMes.get(2)), findMes.get(3), findMes.get(4), helper.changeFromInt(findMes.get(5)), Integer.parseInt(findMes.get(6)));
		}
		
	}

	public CarPO getCarMes(String ID) throws RemoteException {
		findMes = findFromSQL(carTable, ID);
		if(findMes == null){
			return null;
		}else{
			return new CarPO(findMes.get(0), findMes.get(1), Integer.parseInt(findMes.get(2)));
		}
		
	}

	public ResultMessage addDriverPO(DriverPO po) throws RemoteException {
		return addToSQL(driverTable, po.getID() , po.getName() , Date.toString(po.getBirthDay()) , po.getIDcard() , po.getPhoneNum() , po.getIsman()?"1":"0" , String.valueOf(po.getLicenseYear()));
	}

	public ResultMessage addCarPO(CarPO po) throws RemoteException {
		return addToSQL(carTable, po.getID() , po.getPlateNum() , String.valueOf(po.getUseYear()));
	}

	public ResultMessage updateDriverPo(DriverPO po) throws RemoteException {
		return modifyFromSQL(driverTable,  po.getID() , po.getName() , Date.toString(po.getBirthDay()) , po.getIDcard() , po.getPhoneNum() , po.getIsman()?"1":"0" , String.valueOf(po.getLicenseYear()));
	}

	public ResultMessage updateCarPo(CarPO po) throws RemoteException {
		return modifyFromSQL(carTable, carTable, po.getID() , po.getPlateNum() , String.valueOf(po.getUseYear()));
	}

}
