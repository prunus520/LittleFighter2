package LF2.custom.panel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CustomJPanel extends JPanel {

	public CustomJPanel() {
		this.setLayout(null);
		this.setSize(new Dimension(1, 1));
		this.setOpaque(false);
	}

	public void setJLocation(Component component, double widthLocation, double heightLocation) {
		component.setLocation((int) (this.getWidth() * widthLocation), (int) (this.getHeight() * heightLocation));
	}

}
