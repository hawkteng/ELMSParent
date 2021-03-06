package ui.user;

import java.awt.event.MouseEvent;

import org.dom4j.Element;

import bl.BusinessLogicDataFactory;
import blservice.accountblservice.Accountblservice;
import config.StaticMessage;
import ui.tools.MyPanel;
import ui.tools.MySideBarButton;
import ui.util.ButtonState;
import ui.util.CompomentType;
import ui.util.MySideBarListener;
import ui.util.PanelController;
 /** 
 * 管理员界面控制器
 * @author czq 
 * @version 2015年11月26日 下午3:07:45 
 */
public class AdminstratorController extends PanelController{
	
	private MySideBarButton addAccountButton;
	private MySideBarButton modifyAccountButton;
	
	private MyPanel AdminMainPanel;
	private MyPanel addAccountPanel;
	private ModifyAccountPanel modifyAccountPanel;
	
	private final static String adminPanelStr = StaticMessage.MAIN_WINDOW;
	private final static String addAccountPanelStr = "AddAccountPanel";
	private final static String modifyAccountPanelStr = "ModifyAccountPanel";
	
	private Accountblservice bl ;
	
	public AdminstratorController(MyPanel initialPanel, Element e) {
		super(initialPanel , e);
		initialBL();
		initButtons(e.element(CompomentType.BUTTONS.name()));
		initPanel(e);
		addButtons();
		addPanels();
		addListeners();
		addToMap();
		this.setAllButtonVisable(false);
		changePanel.setVisible(true);
	}
	
	@Override
	protected void initialBL() {
		bl = BusinessLogicDataFactory.getFactory().getAccountBusinessLogic();
	}
	
	@Override
	protected void initPanel(Element e) {
		AdminMainPanel = new UserManageMain(e.element(adminPanelStr), this);
		addAccountPanel = new AddAccountPanel(e.element(addAccountPanelStr) , bl);
		modifyAccountPanel = new ModifyAccountPanel(e.element(modifyAccountPanelStr) , bl);
		
	}

	@Override
	protected void initButtons(Element e) {
		addAccountButton = new MySideBarButton(e.element("AddAccount"));
		modifyAccountButton = new MySideBarButton(e.element("ModifyAccount"));
	}

	@Override
	protected void addButtons() {
		mainPanel.add(addAccountButton);
		mainPanel.add(modifyAccountButton);
		
	}

	@Override
	protected void addPanels() {
		changePanel.add(AdminMainPanel,adminPanelStr);
		changePanel.add(addAccountPanel, addAccountPanelStr);
		changePanel.add(modifyAccountPanel, modifyAccountPanelStr);
		
	}

	@Override
	protected void addListeners() {
		addAccountButton.addMouseListener(new MySideBarListener(addAccountButton, this, addAccountPanelStr));
		modifyAccountButton.addMouseListener(new MyModifyButtonListener(modifyAccountButton, this, modifyAccountPanelStr));
	}

	@Override
	public void setAllButtonUnClicked() {
		addAccountButton.setMyIcon(ButtonState.NORMAL);
		modifyAccountButton.setMyIcon(ButtonState.NORMAL);
		
	}

	@Override
	public void setAllButtonVisable(boolean state) {
		addAccountButton.setVisible(state);
		modifyAccountButton.setVisible(state);
	}

	@Override
	protected void addToMap() {
		buttonMap.put(addAccountPanelStr, addAccountButton);
		buttonMap.put(modifyAccountPanelStr, modifyAccountButton);
		
	}
	
	class MyModifyButtonListener extends MySideBarListener{

		public MyModifyButtonListener(MySideBarButton button,
				PanelController controller, String itsPanel) {
			super(button, controller, itsPanel);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			modifyAccountPanel.updateTableData();
			
		}
		
		
		
		
	}
	

	

}
