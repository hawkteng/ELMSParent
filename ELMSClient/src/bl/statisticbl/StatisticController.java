package bl.statisticbl;

import java.util.ArrayList;

import net.RMIManage;
import util.DataServiceType;
import util.ResultMessage;
import vo.statistic.BillVO;
import vo.statistic.CostIncomeVO;
import vo.statistic.StateFormVO;
import blservice.statisticblservice.Statisticblservice;
import ds.statisticdataservice.StatisticDataService;
 /** 
 * 经营状况表、成本收益表、期初建账控制器
 * @author czq 
 * @version 2015年11月15日 上午9:24:59 
 */
public class StatisticController implements Statisticblservice{
	
	private Statistic statistic;
	private StatisticDataService statisticData;
	public StatisticController(){
		statisticData = (StatisticDataService) RMIManage.getDataService(DataServiceType.StatisticDataService);
		statistic = new Statistic(statisticData);
	}
	public ResultMessage bulidStateForm(StateFormVO vo) {
		return statistic.bulidStateForm(vo);
	}

	public ResultMessage bulidCostIncomeForm(CostIncomeVO vo) {
		return statistic.bulidCostIncomeForm(vo);
	}

	public ArrayList<StateFormVO> getStateForm() {
		return statistic.getStateForm();
	}

	public ArrayList<CostIncomeVO> getIncomeForm() {
		return statistic.getIncomeForm();
	}

	public ResultMessage bulidBill(BillVO vo) {
		return statistic.bulidBill(vo);
	}

	public ArrayList<BillVO> getBills() {
		return statistic.getBills();
	}

}
