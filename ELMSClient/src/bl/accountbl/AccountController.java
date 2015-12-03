package bl.accountbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.account.AccountVO;
import blservice.accountblservice.Accountblservice;
 /** 
 * 
 * @author czq 
 * @version 2015年11月15日 上午9:15:28 
 */
public class AccountController implements Accountblservice{
	
	Account account;
	
	public AccountController(){
		account=new Account();
	}
	
	public ResultMessage add(AccountVO vo) {
		return account.add(vo);
	}

	public ResultMessage delete(String ID ){
		return account.delete(ID);
	}

	public AccountVO find(String ID) {
		return account.find(ID);
	}

	public ResultMessage modify(AccountVO vo) {
		return account.modify(vo);
	}

	public ArrayList<AccountVO> show() {
		return account.show();
	}

}
