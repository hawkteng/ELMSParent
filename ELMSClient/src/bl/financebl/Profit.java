package bl.financebl;

import blservice.financeblservice.ProfitService;
import ds.financedataservice.FinanceDataService;
import vo.finance.ProfitVO;

/** 
 * @author ymc 
 * @version 创建时间：2015年10月27日 下午7:47:28 
 *
 */
public class Profit {
	FinanceDataService financebl;
	public Profit(FinanceDataService financeDataService) {
		this.financebl = financeDataService;
	}
	public ProfitVO getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
