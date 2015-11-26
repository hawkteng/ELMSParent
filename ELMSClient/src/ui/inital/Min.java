package ui.inital;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import org.dom4j.Element;

import ui.config.GraphicsUtils;
import ui.tools.MyButton;

/*
 * 最小化按钮
 * 
 */
@SuppressWarnings("serial")
public class Min extends MyButton {
	private boolean  mouseContained = false;
	private boolean mouseClicked = false;
	private mainFrame mainframe;
	
	
	public Min(Element e) {
		super(e);
		this.addMouseListener(new MinListener());
		this.setIcon(GraphicsUtils.getIcon("minGrey"));
		this.setVisible(true);
	}

	// public boolean isMouseClicked(){
	// return mouseClicked;
	// }

//	@Override
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
//				RenderingHints.VALUE_RENDER_QUALITY);
//		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//
//		if (!mouseContained) {
//			g2d.drawImage(GraphicsUtils.getImage("minGrey"), 0, 0, null);
//		} else {
//			g2d.drawImage(GraphicsUtils.getImage("minGreen"), 0, 0, null);
//		}
//	}

	public void setMainFrmae(mainFrame frame){
		mainframe=frame;
	}
	
	
	class MinListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			// mins
			  mainframe.setExtendedState(JFrame.ICONIFIED);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			mouseContained = true;
			setIcon(GraphicsUtils.getIcon("minGreen"));
			repaint();

		}

		@Override
		public void mouseExited(MouseEvent e) {
			mouseContained = false;
			setIcon(GraphicsUtils.getIcon("minGrey"));
			repaint();
		}

	}
}