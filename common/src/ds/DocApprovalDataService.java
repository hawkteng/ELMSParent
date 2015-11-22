package ds;

import java.util.ArrayList;

import po.DocPO;
import util.DocState;
import util.DocType;
import util.ResultMessage;
 /** 
 * 单据审批相关接口父接口
 * @author czq 
 * @version 2015年11月22日 下午2:20:52 
 */
public interface DocApprovalDataService extends DataserviceParent{
	/**
	 * 获得单据列表
	 * @return
	 */
	public ArrayList<? extends DocPO> getDocLists(DocType type);
	/**
	 * 批量修改单据状态
	 * @param docsID
	 * @return
	 */
	public ResultMessage changeDocsState(ArrayList<String> docsID , DocType type , DocState state);
	/**
	 * 修改单个单据状态
	 * @param docID
	 * @return
	 */
	public ResultMessage changeOneDocState(String docID,
			DocType type, DocState state);
}
