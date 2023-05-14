package components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CustomJTextField extends JTextField{
	
	public CustomJTextField(){
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setColumns(10);
		setSize(252, 32);
		setBorder(new LineBorder(new Color(211, 211, 211)));
		setCaretPosition(0);
		setDefaultStyle();
	}
	
	public CustomJTextField(String text){
		setText(text);
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setColumns(10);
		setSize(252, 32);
		setBorder(new LineBorder(new Color(211, 211, 211)));
		setCaretPosition(0);
		setDefaultStyle();
	}

	public void setDefaultStyle() {
		setForeground(new Color(192,192,192));
	}
	
	public void setTypingStyle() {
		setForeground(new Color(105,105,105));
	}
	
	
}
