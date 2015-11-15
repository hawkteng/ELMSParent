package blservice.financeblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import ds.financedataservice.FinanceDataService;
import ds.financedataservice.TempFinanceDataImpl;
import po.CostPO;
import util.ResultMessage;
import vo.CostVO;
/**
 * 成本信息管理接口
 * @author ymc
 *
 */
public class CostService_Stub implements CostService {
	FinanceDataService financeDS;
	
	public ArrayList<CostVO> showCosts() {
		financeDS=new TempFinanceDataImpl();
		ArrayList<CostVO> vos = null;
		try {
			ArrayList<CostPO> pos=financeDS.show();
			vos = new ArrayList<CostVO>();
			if(pos!=null)
				for(CostPO po:pos){
					vos.add(getValue(po));
				}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vos;
	}

	public ResultMessage add(CostVO vo) {
		
		return ResultMessage.SUCCESS;
	}

	public ResultMessage modify(CostVO vo) {
		return ResultMessage.SUCCESS;
	}

	public ResultMessage del(CostVO vo) {
		return ResultMessage.SUCCESS;
	}
	private  CostVO getValue(CostPO po){
		CostVO vo = new CostVO(po.getMoney(), po.getType());
		
		return vo;
		
	} 

}