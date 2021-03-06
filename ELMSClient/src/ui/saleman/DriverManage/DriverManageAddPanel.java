package ui.saleman.DriverManage;

import java.awt.Color;

import javax.swing.JPanel;

import org.dom4j.Element;

import blservice.DTManageblservice.DTManageblservice;
import ui.config.DataType;
import ui.config.SimpleDataFormat;
import ui.config.UserfulMethod;
import ui.table.MyTablePanel;
import ui.tools.AddDocPanel;
import ui.tools.MyComboBox;
import ui.tools.MyDatePicker;
import ui.tools.MyLabel;
import ui.tools.MyPictureLabel;
import ui.tools.MyTextField;
import ui.tools.MyWhitePanel;
import ui.util.CancelListener;
import ui.util.ConfirmListener;
import ui.util.TipsDialog;
import util.MyDate;
import util.ResultMessage;
import vo.DTManage.DriverVO;

/**
 * 司机增加界面
 * 
 * @author czq
 * @version 2015年12月8日 下午8:17:45
 */
@SuppressWarnings("serial")
public class DriverManageAddPanel extends AddDocPanel {

	private MyWhitePanel whitePanel;

	private MyPictureLabel addCarman;

	private MyLabel id;
	private MyLabel name;
	private MyLabel instid;
	private MyLabel bornDay;
	private MyLabel idCard;
	private MyLabel phone;
	private MyLabel sex;
	private MyLabel licenseYear;

	private MyTextField idT;
	private MyTextField nameT;
	private MyTextField instidT;
	private MyTextField idCardT;
	private MyTextField phoneT;
	private MyComboBox sexB;
	private MyTextField licenseYearT;
	private MyDatePicker borns;
	DTManageblservice bl;

	public DriverManageAddPanel(Element config, JPanel changePanel, String checkDocPanelStr,
			MyTablePanel messageTable) {
		super(config, changePanel, checkDocPanelStr, messageTable);
	}

	@Override
	protected void initWhitePanels(Element e) {
		whitePanel=new MyWhitePanel(e.element("whitePanel"));

	}

	@Override
	protected void initButtons(Element e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initTextFields(Element e) {
		
		idT = new MyTextField(e.element("id"));
		nameT = new MyTextField(e.element("name"));
		phoneT = new MyTextField(e.element("phone"));

		instidT = new MyTextField(e.element("instid"));
		licenseYearT = new MyTextField(e.element("license"));
		idCardT = new MyTextField(e.element("idCard"));

	}

	@Override
	protected void initLabels(Element e) {
		addCarman=new MyPictureLabel(e.element("addCarman"));
		
		id = new MyLabel(e.element("id"));
		name = new MyLabel(e.element("name"));
		bornDay = new MyLabel(e.element("bornday"));
		phone = new MyLabel(e.element("phone"));

		instid = new MyLabel(e.element("instid"));
		sex = new MyLabel(e.element("sex"));
		licenseYear = new MyLabel(e.element("license"));
		idCard = new MyLabel(e.element("idCard"));
	}

	@Override
	protected void initOtherCompoment(Element e) {
		borns = new MyDatePicker(e.element("borns"));
		sexB = new MyComboBox(e.element("sex"));
	}

	@Override
	protected void addCompoment() {
		whitePanel.add(addCarman);
		whitePanel.add(bornDay);
		whitePanel.add(borns);
		whitePanel.add(id);
		whitePanel.add(idT);
		whitePanel.add(name);
		whitePanel.add(nameT);
		whitePanel.add(phone);
		whitePanel.add(phoneT);
		whitePanel.add(sex);
		whitePanel.add(sexB);
		whitePanel.add(idCard);
		whitePanel.add(idCardT);
		whitePanel.add(instid);
		whitePanel.add(instidT);
		whitePanel.add(licenseYear);
		whitePanel.add(licenseYearT);
		
		confirm.setLocation(280,575);
		cancel.setLocation(450,575);
		
		
		this.add(whitePanel);
	}

	@Override
	protected void addListener() {
		confirm.addMouseListener(new ConfirmListener(confirm) {
			String id;
			String name;
			String phone;
			String instid;
			MyDate birthday;
			String licenseYear;
			boolean isman;
			String idCard;
			DriverVO vo;

			@Override
			protected void updateMes() {
				if (isAddOrModify) {
					messageTable.addOneData(vo, 1);
				} else {
					messageTable.addOneData(vo, 2);
				}

			}

			@Override
			protected boolean saveToSQL() {
				if (isAddOrModify) {
					result = bl.addDriver(vo = new DriverVO(id, name, instid, birthday, idCard, phone, isman,
							Integer.parseInt(licenseYear)));
					if (result == ResultMessage.SUCCESS) {
						new TipsDialog("成功增加司机信息", Color.GREEN);
						return true;
					} else {
						new TipsDialog("增加司机信息失败，可能由于ID重复或数据库错误", Color.RED);
						System.err.println(result);
						return false;
					}
				} else {
					result = bl.modifyDriver(vo = new DriverVO(id, name, instid, birthday, idCard, phone, isman,
							Integer.parseInt(licenseYear)));
					if (result == ResultMessage.SUCCESS) {
						new TipsDialog("成功修改司机信息", Color.GREEN);
						return true;
					} else {
						new TipsDialog("修改司机信息失败，可能由于ID重复或数据库错误", Color.RED);
						System.err.println(result);
						return false;
					}

				}

			}

			@Override
			protected void reInitial() {
				myInit();
			}

			@Override
			protected boolean checkDataValid() {
				id = idT.getText();
				name = nameT.getText();
				phone = phoneT.getText();
				instid = instidT.getText();
				birthday = borns.getMyDate();
				idCard = idCardT.getText();
				licenseYear = licenseYearT.getText();
				isman = sexB.getSelectedIndex() == 0 ? true : false;

				SimpleDataFormat[] datas = { new SimpleDataFormat(id, DataType.ID, "ID"),
						new SimpleDataFormat(phone, DataType.phone, "手机号码"),
						new SimpleDataFormat(instid, DataType.ID, "机构ID"),
						new SimpleDataFormat(idCard, DataType.idCard, "身份证号"),
						new SimpleDataFormat(licenseYear, DataType.PositiveNum, "驾驶证期限") };
				return UserfulMethod.dealWithData(datas);
			}
		});
		cancel.addMouseListener(new CancelListener(cancel) {

			@Override
			public void resetMes() {
				myInit();

			}
		});
	}

	private void myInit() {
		idT.setText("");
		nameT.setText("");
		phoneT.setText("");
		instidT.setText("");
		idCardT.setText("");
		licenseYearT.setText("");
	}

	@Override
	public void setAddOrModify(boolean isAdd, String id) {
		isAddOrModify = isAdd;
		idT.setEditable(isAdd);

		if (isAdd) {

		} else {
			idT.setText(id);
		}

	}

}
