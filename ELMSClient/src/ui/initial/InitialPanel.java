package ui.initial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.AXIS;

import org.dom4j.Element;

import ui.config.GraphicsUtils;
import ui.courier.CourierController;
import ui.financeman.FinanceController;
import ui.generalmanager.GeneralManagerController;
import ui.saleman.SaleManController;
import ui.storeman.StoreManController;
import ui.storemanager.StoreManagerController;
import ui.tools.MyFrame;
import ui.tools.MyLabel;
import ui.tools.MyPanel;
import ui.tools.MyPictureButton;
import ui.user.AdminstratorController;
import ui.util.ButtonState;
import ui.util.CompomentType;
import ui.util.PanelController;
import ui.util.TipsDialog;
import util.AccountType;
import vo.account.AccountVO;

/**
 * 主面板界面，不动的界面
 * 
 * @author xingcheng
 *
 */
@SuppressWarnings("serial")
public class InitialPanel extends MyPanel {

	// private ShowCareer career;
	private MyPictureButton exit;
	private MyPictureButton min;
	private MyPictureButton home;
	private MyPictureButton rectangle;
	private MyLabel career;
	private TimeLabel time;
	private static String[] choices = {"退出系统" , "注销" , "取消"};
	/**
	 * 控制器
	 */
	private PanelController controller;
	private MyFrame parent;
	private AccountVO vo;

	private final static Image bg = GraphicsUtils.getImage("bg//bg");

	public InitialPanel(Element e, MyFrame frame, AccountVO vo) {
		super(e);
		this.parent = frame;
		this.vo = vo;
		TipsDialog.setFrame(frame);

		this.initButtons(e.element(CompomentType.BUTTONS.name()));
		this.initLabels(e.element(CompomentType.LABELS.name()));
		this.initTextFields(e.element(CompomentType.TEXTFIELDS.name()));
		this.initOtherCompoment(e);
		this.addCompoment();
		this.addListener();
		// 界面跳转方法
		this.addOtherPanel(e);

		this.repaint();

		this.setVisible(true);
		// set
		
//		time.start
		new Thread(time).start();
	}

	/**
	 * 根据账户类型跳转至不同的界面
	 * 
	 * @param vo
	 */
	private void addOtherPanel(Element e) {



		AccountType type = vo.type;

		switch (type) {
		case Adminstrator:
			controller = new AdminstratorController(this,
					e.element("Adminstrator"));
			break;
		case courier:
			controller = new CourierController(this,
					e.element("CourierManager"));
			break;
		case financeman:
			controller = new FinanceController(this, e.element("Financeman"));
			break;
		case manager:
			controller = new GeneralManagerController(this,
					e.element("GeneralManager"));
			break;
		case saleman:
			controller = new SaleManController(this, e.element("Salesman"));
			break;
		case storeman:
			controller = new StoreManController(this, e.element("StoreMan"));
			break;
		case storemanager:
			controller = new StoreManagerController(this,
					e.element("StoreManager"));
			break;
		default:
			break;


		}
	}

	@Override
	public void paintComponent(Graphics g) {

		g.drawImage(bg, 0, 0, null);

	};

	@Override
	protected void initButtons(Element e) {
		//解决边框透明度的问题
		setBackground(new Color(0f, 0f, 0f,0f));
		min = new MyPictureButton(e.element("min"));
		home = new MyPictureButton(e.element("home"));
		exit = new MyPictureButton(e.element("exit"));
		exit.setBackground(new Color(0f, 0f, 0f,0f));
		
		rectangle = new MyPictureButton(e.element("drop"));
	}

	@Override
	protected void initTextFields(Element e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initLabels(Element e) {
		career = new MyLabel(e.element("career"), vo.type.getName() + "  "
				+ vo.name);
		time = new TimeLabel();
		
		
	}

	@Override
	protected void initOtherCompoment(Element e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addCompoment() {
		this.add(exit);
		this.add(min);
		this.add(home);

		this.add(rectangle);
		this.add(career);
		this.add(time);
	}

	@Override
	protected void addListener() {
		exit.addMouseListener(new ExitListener());
		min.addMouseListener(new MinListener());
		home.addMouseListener(new HomeListener());
		rectangle.addMouseListener(new RectangleListener());
		
		
		
		
		
		
	}

	class ExitListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			exit.setMyIcon(ButtonState.MOUSE_CLICKED);
			
			int result = JOptionPane.showOptionDialog(parent, "确认退出？","系统提示",
					JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE , null , choices , null);
			if(result == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else if(result == JOptionPane.NO_OPTION){
				parent.dispose();
				new AXIS();
			}else{
				return;
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			exit.setMyIcon(ButtonState.MOUSE_ENTERED);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			exit.setMyIcon(ButtonState.NORMAL);
		}
	}

	class MinListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			min.setMyIcon(ButtonState.MOUSE_CLICKED);
			// 最小化到任务栏
			parent.setExtendedState(JFrame.ICONIFIED);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			min.setMyIcon(ButtonState.MOUSE_ENTERED);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			min.setMyIcon(ButtonState.NORMAL);
		}
	}

	class HomeListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			home.setMyIcon(ButtonState.MOUSE_CLICKED);
			// 切换到主页
			controller.jumpBackToMainWindow();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			home.setMyIcon(ButtonState.MOUSE_ENTERED);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			home.setMyIcon(ButtonState.NORMAL);
		}
	}

	class RectangleListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			rectangle.setMyIcon(ButtonState.MOUSE_CLICKED);
			// 下拉窗口 选择“设置账户信息”“登出账户”
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			rectangle.setMyIcon(ButtonState.MOUSE_ENTERED);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			rectangle.setMyIcon(ButtonState.NORMAL);
		}
	}

	@Override
	protected void initWhitePanels(Element e) {

	}
}
