package test.order;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import util.ResultMessage;
import bl.orderbl.OrderController;
import blservice.orderblservice.Orderblservice;
/** 
 * 
 * @author czq 
 * @version 2015年11月15日 下午2:39:01 
 */
public class OrderControllerTest {
	
	Orderblservice bl ;
	ResultMessage result;
	
	@Before
	public void setUp() throws Exception {
		bl  = new OrderController();
		
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckBarCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrderVO() {
		fail("Not yet implemented");
	}

	@Test
	public void testDel() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSimpleInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFullInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testReceiveInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDocToList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDocLists() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeDocsState() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeOneDocState() {
		fail("Not yet implemented");
	}

}