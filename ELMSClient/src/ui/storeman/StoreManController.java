package ui.storeman;

import org.dom4j.Element;

import config.StaticMessage;
import ui.generalmanager.ApprovalDocsPanel;
import ui.generalmanager.ConstSetPanel;
import ui.generalmanager.GeneralManagerMain;
import ui.generalmanager.StatisticPanel;
import ui.tools.MyPanel;
import ui.tools.MySideBarButton;
import ui.util.ButtonState;
import ui.util.CompomentType;
import ui.util.MySideBarListener;
import ui.util.PanelController;
 /** 
 * 
 * @author czq 
 * @version 2015年11月26日 下午3:40:14 
 */
public class StoreManController extends PanelController{

	private MyPanel SMmainpanel;

	private MyPanel TransportPanel;
	private MyPanel ArriveZZPanel;
	private MyPanel StorePanel;
	
	private MySideBarButton transportButton;
	private MySideBarButton arriveZZButton;
	private MySideBarButton StoreButton;
	
	
	private final String SMmainpanelStr = StaticMessage.MAIN_WINDOW;
	private final String TransportPanelStr = "TransportPanel";
	private final String ArriveZZPanelStr = "ArriveZZPanel";
	private final String StorePanelStr = "StorePanel";
	
	public StoreManController(MyPanel initialPanel, Element e) {
		super(initialPanel , e);
		initButtons(e.element(CompomentType.BUTTONS.name()));
		initPanel(e);
		addButtons();
		addPanels();
		addListeners();
		addToMap();
		setAllButtonVisable(false);
		changePanel.setVisible(true);
	}

	@Override
	protected void initPanel(Element e) {
		SMmainpanel = new StoreMain(e.element(SMmainpanelStr) , this);
		TransportPanel = new TransportPanel(e.element(TransportPanelStr));
		ArriveZZPanel = new ArriveZZPanel(e.element(ArriveZZPanelStr));
		StorePanel = new StoreShowPanel(e.element(StorePanelStr));
	}

	@Override
	protected void initButtons(Element e) {
		transportButton = new MySideBarButton(e.element("transport"));
		arriveZZButton = new MySideBarButton(e.element("arriveZZ"));
		StoreButton = new MySideBarButton(e.element("store"));
	}

	@Override
	protected void addButtons() {
		mainPanel.add(transportButton);
		mainPanel.add(arriveZZButton);
		mainPanel.add(StoreButton);
		
	}

	@Override
	protected void addPanels() {
		changePanel.add(SMmainpanel,SMmainpanelStr);
		changePanel.add(TransportPanel,TransportPanelStr);
		changePanel.add(ArriveZZPanel, ArriveZZPanelStr);
		changePanel.add(StorePanel, StorePanelStr);
		
	}

	@Override
	protected void addListeners() {
		transportButton.addMouseListener(new MySideBarListener(transportButton, this, TransportPanelStr));
		arriveZZButton.addMouseListener(new MySideBarListener(arriveZZButton, this, ArriveZZPanelStr));
		StoreButton.addMouseListener(new MySideBarListener(StoreButton, this, StorePanelStr));
	}

	@Override
	public void setAllButtonUnClicked() {
		transportButton.setMyIcon(ButtonState.NORMAL);
		arriveZZButton.setMyIcon(ButtonState.NORMAL);
		StoreButton.setMyIcon(ButtonState.NORMAL);

		
	}

	@Override
	public void setAllButtonVisable(boolean state) {
		transportButton.setVisible(state);	
		arriveZZButton.setVisible(state);
		StoreButton.setVisible(state);
	}

	@Override
	protected void addToMap() {

		buttonMap.put(TransportPanelStr, transportButton);
		buttonMap.put(ArriveZZPanelStr, arriveZZButton);
		buttonMap.put(StorePanelStr, StoreButton);
	}


	

}
