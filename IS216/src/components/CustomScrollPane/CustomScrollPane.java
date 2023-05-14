package components.CustomScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class CustomScrollPane extends JScrollPane{

	public CustomScrollPane() {
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setBackground(Color.white);
		getVerticalScrollBar().setBackground(Color.white);
		getVerticalScrollBar().setUI(new BasicScrollBar());
		getVerticalScrollBar().setPreferredSize(new Dimension(7, 10));
		
	}
}
