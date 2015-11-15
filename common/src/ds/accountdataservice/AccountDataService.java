package ds.accountdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.AccountPO;
import util.ResultMessage;
/**
 * 账户数据层接口
 * @author czq
 * @date 2015-10-22
 */
public interface AccountDataService extends Remote{
	/**
	 * 增加账户
	 * @param po 只包括账号、密码、种类，其他信息自动初始化为零值即可
	 * @return
	 */
	public ResultMessage add(AccountPO po) throws RemoteException;
	/**
	 * 初始化账户数据（即清空所有账户，管理员除外）
	 * @return
	 */
	public ResultMessage initial() throws RemoteException;
	/**
	 * 管理员查找账户，返回账户信息
	 * @param ID
	 * @return
	 */
	public AccountPO find(String ID) throws RemoteException;
	/**
	 * 删除账户
	 * @param id 账户ID
	 * @return
	 */
	public ResultMessage delete(String ID) throws RemoteException;
	/**
	 * 修改个人账户信息   用户使用
	 * @param po
	 * @return
	 */
	public ResultMessage modify(AccountPO po) throws RemoteException;
	/**
	 * 查询账号密码是否匹配（用户登陆界面）
	 * @param ID
	 * @param password
	 * @return
	 */
	public ResultMessage check(String ID , String password) throws RemoteException;
	/**
	 * 查看个人账户信息（用于用户修改个人信息界面）
	 * @param ID
	 * @return
	 */
	public AccountPO getMes(String ID) throws RemoteException;
	
	

}