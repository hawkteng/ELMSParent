package ui.saleman.SendGoodDoc;

import javax.swing.JPanel;

import org.dom4j.Element;

import ui.saleman.LoadDoc.LoadDocOrders;
import ui.table.MyTablePanel;
import ui.tools.AddDocPanel;
import ui.tools.MyComboBox;
import ui.tools.MyDatePicker;
import ui.tools.MyLabel;
import ui.tools.MyPanel;
import ui.tools.MyPictureLabel;
import ui.tools.MyTextField;
import ui.util.CancelListener;
import ui.util.ConfirmListener;

/**
 * 
 * @author czq
 *
 */
@SuppressWarnings("serial")
public class SendGoodDocAddPanel extends AddDocPanel{
	
	private MyLabel id;
	private MyDatePicker date;
	private MyLabel sendMan;
	private MyLabel orderBarCode;
	private MyLabel sendCity;
	
	private MyTextField idT;
	private MyTextField sendManT;
	private MyTextField orderBarCodeT;
	private MyComboBox sendCityB;
	
	
	public SendGoodDocAddPanel(Element config, JPanel changePanel, String checkDocPanelStr, MyTablePanel messageTable) {
		super(config , changePanel , checkDocPanelStr,  messageTable);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWhitePanels(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initButtons(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initTextFields(Element e) {
		idT = new MyTextField(e.element("id"));
		sendManT = new MyTextField(e.element("sendMan"));
		orderBarCodeT = new MyTextField(e.element("orderBarCode"));
		
	}

	@Override
	protected void initLabels(Element e) {
		id = new MyPictureLabel(e.element("id"));
		sendMan = new MyPictureLabel(e.element("sendMan"));
		orderBarCode = new MyPictureLabel(e.element("orderBarCode"));
		sendCity = new MyPictureLabel(e.element("sendCity"));
		
	}

	@Override
	protected void initOtherCompoment(Element e) {
		date = new MyDatePicker(e.element("datepicker"));
		sendCityB = new MyComboBox(e.element("sendCity"));
	}

	@Override
	protected void addCompoment() {
		add(sendMan);add(sendManT);add(sendCity);add(sendCityB);add(date);add(id);add(idT);
		add(orderBarCode);add(orderBarCodeT);
		
	}

	@Override
	protected void addListener() {
		confirm.addMouseListener(new ConfirmListener(confirm) {
			
			@Override
			protected void updateMes() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void saveToSQL() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void reInitial() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected boolean checkDataValid() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		cancel.addMouseListener(new CancelListener(cancel) {
			
			@Override
			public void resetMes() {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
