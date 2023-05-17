package view.user;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import components.CustomJTextField;
import components.CustomScrollPane.CustomScrollPane;
import components.ProductPanels.ProductThumbnail;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JPanel {

	private CustomJTextField search_bar;
	
	public Home() {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 650);
		setLayout(new BorderLayout(0, 0));
		
		
		JPanel content_south = new JPanel();
		content_south.setBackground(new Color(255, 255, 255));
		add(content_south, BorderLayout.SOUTH);
		content_south.setPreferredSize(new Dimension(100, 20));
		
		JPanel content_west = new JPanel();
		content_west.setBackground(new Color(255, 255, 255));
		add(content_west, BorderLayout.WEST);
		content_west.setPreferredSize(new Dimension(34, 100));
		
		JPanel content_east = new JPanel();
		content_east.setBackground(new Color(255, 255, 255));
		add(content_east, BorderLayout.EAST);
		content_east.setPreferredSize(new Dimension(34, 100));
		
		JPanel content_north = new JPanel();
		content_north.setBackground(new Color(255, 255, 255));
		add(content_north, BorderLayout.NORTH);
		content_north.setPreferredSize(new Dimension(100, 80));
		content_north.setLayout(null);
		
		search_bar = new CustomJTextField("Tìm kiếm sản phẩm");
		search_bar.setBounds(128, 23, 237, 27);
		content_north.add(search_bar);
		search_bar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		search_bar.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(search_bar.getText().equals("Tìm kiếm sản phẩm")) {
					search_bar.setText("");
					search_bar.requestFocus();
					search_bar.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(search_bar.getText().length() == 0) {
					search_bar.setDefaultStyle();
					search_bar.setText("Tìm kiếm sản phẩm");
				}
			}
		});
		
		JComboBox search_by = new JComboBox();
		search_by.setBounds(33, 24, 89, 25);
		search_by.setFont(new Font("SansSerif", Font.PLAIN, 14));
		search_by.setBackground(new Color(255, 255, 255));
		search_by.setBorder(null);

		content_north.add(search_by);
		
		JComboBox sort_by = new JComboBox();
		sort_by.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sort_by.setBorder(null);
		sort_by.setBackground(Color.WHITE);
		sort_by.setBounds(486, 24, 134, 25);
		content_north.add(sort_by);
		
		JLabel sorbyLabel = new JLabel("Sắp xếp theo:");
		sorbyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sorbyLabel.setForeground(Color.GRAY);
		sorbyLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sorbyLabel.setBounds(392, 23, 96, 27);
		content_north.add(sorbyLabel);
		
		CustomScrollPane scrollPane = new CustomScrollPane();
		scrollPane.setViewportBorder(null);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);
		
		
		JPanel content_center = new JPanel();
		content_center.setBorder(null);
		content_center.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(content_center);
		content_center.setLayout(new GridLayout(0, 4, 20, 12));
		
		
		//---------------------------------------------------
		
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(createEmptyJPanel());
		content_center.add(createEmptyJPanel());
		content_center.add(createEmptyJPanel());
		content_center.add(createEmptyJPanel());
		content_center.add(createEmptyJPanel());
		content_center.add(createEmptyJPanel());
		content_center.add(createEmptyJPanel());
		content_center.add(createEmptyJPanel());
		content_center.add(createEmptyJPanel());
		
		/*content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		content_center.add(new ProductThumbnail());
		*/
		/*JPanel product_row_1 = new JPanel();
		product_row_1.setBackground(new Color(255, 255, 255));
		content_center.add(product_row_1);
		product_row_1.setLayout(new GridLayout(1, 4, 20, 20));
		
		ProductThumbnail productThumbnail = new ProductThumbnail();
		product_row_1.add(productThumbnail);
		product_row_1.add(new ProductThumbnail());
		product_row_1.add(new ProductThumbnail());
		product_row_1.add(new ProductThumbnail());
		
		
		JPanel product_row_2 = new JPanel();
		product_row_2.setBackground(new Color(255, 255, 255));
		content_center.add(product_row_2);
		product_row_2.setLayout(new GridLayout(1, 4, 20, 20));
		
		product_row_2.add(new ProductThumbnail());
		product_row_2.add(new ProductThumbnail());
		product_row_2.add(new ProductThumbnail());
		product_row_2.add(new ProductThumbnail());
		
		JPanel product_row_3 = new JPanel();
		product_row_3.setBackground(new Color(255, 255, 255));
		content_center.add(product_row_3);
		product_row_3.setLayout(new GridLayout(1, 4, 20, 20));
		
		product_row_3.add(new ProductThumbnail());
		product_row_3.add(new ProductThumbnail());
		product_row_3.add(new ProductThumbnail());
		product_row_3.add(new ProductThumbnail());
		*/
		
	}
	
	public JPanel createEmptyJPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		return panel;
	}
}
