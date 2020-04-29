package LF2.custom.button;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class CustomJButton extends JButton implements MouseListener {

	private String title = "";
	private ClickListener clickListener;
	private String state = "exited";
	private int mouseStatus = 0;
	private boolean isFilled = false;
	private int winWidth, winHeight;
	private double widthSize = 0, heightSize = 0;
	private boolean isSize = false;

	public CustomJButton() {
		init();
	}

	public CustomJButton(String title) {
		super(title);
		this.title = title;
		init();
	}

	private void init() {
		this.setSize(new Dimension(1, 1));
		this.setBorderPainted(false);
		this.setContentAreaFilled(isFilled);
		this.setFocusable(false);
		this.setActionCommand(title);

		this.addMouseListener(this);
	}

	public void addClickListener(ClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public int getMouseStatus() {
		return mouseStatus;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setSize(double widthSize, double heightSize) {
		this.widthSize = widthSize;
		this.heightSize = heightSize;
		isSize = true;
	}

	public int getWinWidth() {
		return winWidth;
	}

	public void setWinWidth(int winWidth) {
		this.winWidth = winWidth;
	}

	public int getWinHeight() {
		return winHeight;
	}

	public void setWinHeight(int winHeight) {
		this.winHeight = winHeight;
	}

	public void setWinSize(int winWidth, int winHeight) {
		this.winWidth = winWidth;
		this.winHeight = winHeight;
	}

	public double getWidthSize() {
		return widthSize;
	}

	public double getHeightSize() {
		return heightSize;
	}

	public boolean isSize() {
		return isSize;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		state = "clicked";
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		state = "entered";
		// isFilled = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		state = "exited";
		// isFilled = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseStatus = e.getModifiers();
		clickListener.ClickListener(this.getActionCommand());
		state = "pressed";
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		state = "released";
	}

}
