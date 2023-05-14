package view.user;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JTextField;

public class ProductDetail extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductDetail frame = new ProductDetail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ProductDetail() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(440, 180, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel name = new JLabel("Tên Sản Phẩm");
		name.setFont(new Font("SansSerif", Font.BOLD, 24));
		name.setBounds(459, 50, 290, 40);
		contentPane.add(name);
		
		JLabel priceLabel = new JLabel("Giá");
		priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		priceLabel.setForeground(new Color(0, 0, 0));
		priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		priceLabel.setBounds(459, 100, 50, 32);
		contentPane.add(priceLabel);
		
		JLabel shapeLabel = new JLabel("Kiểu dáng:");
		shapeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		shapeLabel.setForeground(new Color(128, 128, 128));
		shapeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		shapeLabel.setBounds(459, 183, 80, 32);
		contentPane.add(shapeLabel);
		
		JButton add_to_cart = new JButton("Thêm vào giỏ hàng");
		add_to_cart.setForeground(Color.WHITE);
		add_to_cart.setFont(new Font("SansSerif", Font.BOLD, 16));
		add_to_cart.setBackground(Color.BLACK);
		add_to_cart.setBounds(459, 302, 207, 40);
		contentPane.add(add_to_cart);
		
		JLabel categoryLabel = new JLabel("Loại kính:");
		categoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
		categoryLabel.setForeground(new Color(128, 128, 128));
		categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		categoryLabel.setBounds(459, 157, 71, 32);
		contentPane.add(categoryLabel);
		
		JPanel image = new JPanel();
		image.setBounds(138, 62, 264, 280);
		contentPane.add(image);
		
		JLabel brandLabel = new JLabel("Thương hiệu:");
		brandLabel.setHorizontalAlignment(SwingConstants.LEFT);
		brandLabel.setForeground(new Color(128, 128, 128));
		brandLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		brandLabel.setBounds(459, 131, 94, 32);
		contentPane.add(brandLabel);
		
		JLabel colorLabel = new JLabel("Màu sắc");
		colorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		colorLabel.setForeground(new Color(128, 128, 128));
		colorLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		colorLabel.setBounds(459, 209, 80, 32);
		contentPane.add(colorLabel);
		
		JLabel materialLabel = new JLabel("Chất liệu:");
		materialLabel.setHorizontalAlignment(SwingConstants.LEFT);
		materialLabel.setForeground(new Color(128, 128, 128));
		materialLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		materialLabel.setBounds(459, 235, 80, 32);
		contentPane.add(materialLabel);
		
		JLabel descriptionLabel = new JLabel("Mô tả:");
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setForeground(new Color(0, 0, 0));
		descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		descriptionLabel.setBounds(122, 383, 602, 32);
		contentPane.add(descriptionLabel);
		
		JLabel brand = new JLabel("abc");
		brand.setHorizontalAlignment(SwingConstants.LEFT);
		brand.setForeground(new Color(0, 0, 0));
		brand.setFont(new Font("SansSerif", Font.BOLD, 14));
		brand.setBounds(547, 131, 119, 32);
		contentPane.add(brand);
		
		JLabel category = new JLabel("abc");
		category.setHorizontalAlignment(SwingConstants.LEFT);
		category.setForeground(Color.BLACK);
		category.setFont(new Font("SansSerif", Font.BOLD, 14));
		category.setBounds(522, 157, 119, 32);
		contentPane.add(category);
		
		JLabel shape = new JLabel("đa giác");
		shape.setHorizontalAlignment(SwingConstants.LEFT);
		shape.setForeground(Color.BLACK);
		shape.setFont(new Font("SansSerif", Font.BOLD, 14));
		shape.setBounds(532, 183, 119, 32);
		contentPane.add(shape);
		
		JLabel color = new JLabel("abc");
		color.setHorizontalAlignment(SwingConstants.LEFT);
		color.setForeground(Color.BLACK);
		color.setFont(new Font("SansSerif", Font.BOLD, 14));
		color.setBounds(522, 209, 119, 32);
		contentPane.add(color);
		
		JLabel material = new JLabel("abc");
		material.setHorizontalAlignment(SwingConstants.LEFT);
		material.setForeground(Color.BLACK);
		material.setFont(new Font("SansSerif", Font.BOLD, 14));
		material.setBounds(522, 235, 119, 32);
		contentPane.add(material);
		
		//use HTML to set multiple line in jlabel
		JLabel description = new JLabel("<html>aaaaaaaaaaaaaa bla aaaaaaaaaaaaaa<br>Second line aaaaaaaaaaaaaa bla aaaaaaaaaaaaaa</html>");
		description.setHorizontalAlignment(SwingConstants.LEFT);
		description.setForeground(new Color(128, 128, 128));
		description.setFont(new Font("SansSerif", Font.PLAIN, 14));
		description.setBounds(167, 383, 557, 74);
		contentPane.add(description);
		
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(null);
		actionPanel.setPreferredSize(new Dimension(100, 100));
		actionPanel.setBackground(Color.WHITE);
		actionPanel.setBounds(678, 302, 71, 40);
		contentPane.add(actionPanel);
		
		JButton decrButton = new JButton("-");
		decrButton.setForeground(new Color(169, 169, 169));
		decrButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
		decrButton.setBorder(null);
		decrButton.setBackground(Color.WHITE);
		decrButton.setBounds(0, 3, 20, 28);
		actionPanel.add(decrButton);
		
		JButton incrButton = new JButton("+");
		incrButton.setForeground(new Color(169, 169, 169));
		incrButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		incrButton.setBorder(null);
		incrButton.setBackground(Color.WHITE);
		incrButton.setBounds(46, 5, 20, 27);
		actionPanel.add(incrButton);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBorder(new LineBorder(new Color(211, 211, 211)));
		textField.setBounds(20, 5, 26, 26);
		actionPanel.add(textField);
		
		JLabel quantityLabel = new JLabel("Số lượng:");
		quantityLabel.setHorizontalAlignment(SwingConstants.LEFT);
		quantityLabel.setForeground(Color.GRAY);
		quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		quantityLabel.setBounds(459, 264, 80, 32);
		contentPane.add(quantityLabel);
		
		JLabel quantity = new JLabel("abc");
		quantity.setHorizontalAlignment(SwingConstants.LEFT);
		quantity.setForeground(Color.BLACK);
		quantity.setFont(new Font("SansSerif", Font.BOLD, 14));
		quantity.setBounds(522, 264, 119, 32);
		contentPane.add(quantity);
		
		

		
	}
}

				
