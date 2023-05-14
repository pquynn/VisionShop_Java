package components.ProductPanels;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import components.CustomJTextField;
import view.user.ProductDetail;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class ProductInCart extends JPanel {
	private CustomJTextField number_products;
	private JButton incrButton;
	private JButton decrButton;
	private JButton removeButton;
	
	
	public ProductInCart() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new ProductDetail().setVisible(true);
			}
			
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(new Color(192,192,192)));
			}
			
			public void mouseExited(MouseEvent e) {
				setBorder(null);
			}
		});
		setBackground(new Color(224, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(null);
		add(imagePanel, BorderLayout.WEST);
		imagePanel.setLayout(new BorderLayout(0, 0));
		imagePanel.setPreferredSize(new Dimension(150, 100));
		
		JPanel checkbox_panel = new JPanel();
		checkbox_panel.setBackground(new Color(255, 255, 255));
		imagePanel.add(checkbox_panel, BorderLayout.WEST);
		checkbox_panel.setPreferredSize(new Dimension(30, 100));
		checkbox_panel.setLayout(null);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setFont(new Font("SansSerif", Font.PLAIN, 10));
		checkBox.setBackground(new Color(255, 255, 255));
		checkBox.setBounds(4, 33, 27, 27);
		checkbox_panel.add(checkBox);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(255, 255, 255));
		add(infoPanel, BorderLayout.CENTER);
		infoPanel.setLayout(null);
		
		JLabel name = new JLabel("Tên sản phẩm");
		name.setFont(new Font("SansSerif", Font.BOLD, 14));
		name.setBounds(10, 0, 195, 27);
		infoPanel.add(name);
		
		JLabel price = new JLabel("50000đ");
		price.setFont(new Font("SansSerif", Font.BOLD, 12));
		price.setBounds(10, 53, 195, 27);
		infoPanel.add(price);
		
		
		JPanel actionPanel = new JPanel();
		actionPanel.setBackground(new Color(255, 255, 255));
		add(actionPanel, BorderLayout.EAST);
		actionPanel.setPreferredSize(new Dimension(100, 100));
		actionPanel.setLayout(null);
		
		
		decrButton = new JButton("-");
		decrButton.setForeground(new Color(169, 169, 169));
		decrButton.setBounds(10, 36, 20, 28);
		actionPanel.add(decrButton);
		decrButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
		decrButton.setBorder(null);
		decrButton.setBackground(new Color(255, 255, 255));
		decrButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				decrButton.setForeground(Color.DARK_GRAY);
			}
			
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				decrButton.setForeground(new Color(169, 169, 169));
			}
		});
		
		incrButton = new JButton("+");
		incrButton.setBounds(59, 38, 20, 27);
		actionPanel.add(incrButton);
		incrButton.setForeground(new Color(169, 169, 169));
		incrButton.setBackground(new Color(255, 255, 255));
		incrButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		incrButton.setBorder(null);
		incrButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				incrButton.setForeground(Color.DARK_GRAY);
			}
			
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				incrButton.setForeground(new Color(169, 169, 169));
			}
		});
		
		number_products = new CustomJTextField();
		number_products.setBounds(32, 38, 26, 26);
		actionPanel.add(number_products);
		
		
		removeButton = new JButton("x");
		removeButton.setForeground(new Color(169, 169, 169));
		removeButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
		removeButton.setBackground(new Color(255, 255, 255));
		removeButton.setBounds(81, 0, 19, 21);
		actionPanel.add(removeButton);
		removeButton.setBorder(null);
		removeButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				removeButton.setForeground(Color.DARK_GRAY);
			}
			
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				removeButton.setForeground(new Color(169, 169, 169));
			}
		});
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove product in cart
				
				//hien confirm dialog
			}
		});
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(255, 255, 255));
		add(northPanel, BorderLayout.NORTH);
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(new Color(255, 255, 255));
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(null);
		southPanel.setPreferredSize(new Dimension(100, 10));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(211, 211, 211));
		separator.setBackground(new Color(211, 211, 211));
		separator.setBounds(0, 9, 1500, 1);
		southPanel.add(separator);

	}
}
