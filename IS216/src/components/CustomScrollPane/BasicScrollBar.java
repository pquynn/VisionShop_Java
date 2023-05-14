package components.CustomScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import components.ZeroSizeButton;

public class BasicScrollBar extends BasicScrollBarUI {
	
	    @Override
	    protected void configureScrollBarColors() {
	        this.thumbColor = new Color(217, 217, 217);
	    }
	    
	    @Override 
	    protected JButton createDecreaseButton(int orientation) {
		    return new ZeroSizeButton();
		  }

		@Override 
		protected JButton createIncreaseButton(int orientation) {
		    return new ZeroSizeButton();
		  }
}