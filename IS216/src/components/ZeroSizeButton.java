package components;
import java.awt.Dimension;
import javax.swing.JButton;

public class ZeroSizeButton extends JButton{
	@Override public Dimension getPreferredSize() {
	    return new Dimension();
	  }
}
