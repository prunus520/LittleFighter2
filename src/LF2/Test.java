package LF2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Test extends JFrame {

	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	int WinWidth = (int) (screenSize.width * 0.527);
	int WinHeight = (int) (screenSize.height * 0.679);

	public Test(Component comp) {
		this.setSize(WinWidth, WinHeight);
		this.add(comp, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(this);
		this.setVisible(true);
	}

}
