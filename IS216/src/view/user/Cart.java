package view.user;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import components.CustomJTextField;
import components.CustomScrollPane.CustomScrollPane;
import components.ProductPanels.ProductInCart;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class Cart extends JPanel {
	private CustomJTextField name;
	private CustomJTextField email;
	private CustomJTextField address;
	private CustomJTextField phone;

	public Cart() {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 650);
		setLayout(new BorderLayout(0, 0));
		
		JPanel mycart = new JPanel();
		mycart.setBackground(new Color(255, 255, 255));
		add(mycart, BorderLayout.CENTER);
		//mycart.setPreferredSize(new Dimension(400, 100));
		mycart.setLayout(new BorderLayout(0, 0));
		
		JPanel mycart_north = new JPanel();
		mycart_north.setBackground(new Color(255, 255, 255));
		mycart.add(mycart_north, BorderLayout.NORTH);
		mycart_north.setPreferredSize(new Dimension(100, 80));
		mycart_north.setLayout(null);
		
		JLabel cart_heading = new JLabel("Giỏ hàng");
		cart_heading.setVerticalAlignment(SwingConstants.BOTTOM);
		cart_heading.setBounds(30, 25, 168, 45);
		mycart_north.add(cart_heading);
		cart_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		
		JPanel mycart_west = new JPanel();
		mycart_west.setBackground(new Color(255, 255, 255));
		mycart.add(mycart_west, BorderLayout.WEST);
		mycart_west.setPreferredSize(new Dimension(30, 100));
		
		JPanel mycart_south = new JPanel();
		mycart_south.setBackground(new Color(255, 255, 255));
		mycart.add(mycart_south, BorderLayout.SOUTH);
		mycart_south.setPreferredSize(new Dimension(100, 40));
		
	
		CustomScrollPane scrollPane = new CustomScrollPane();
		mycart.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);
		
		JPanel products_list = new JPanel();
		products_list.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(products_list);
		products_list.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		//------------------------------
		JPanel row1 = new JPanel();
		row1.setBackground(new Color(255, 255, 255));
		products_list.add(row1);
		row1.setLayout(new BorderLayout(0, 0));
		
		ProductInCart product1 = new ProductInCart();
		row1.add(product1, BorderLayout.CENTER);
		
		JPanel row2 = new JPanel();
		row2.setBackground(new Color(255, 255, 255));
		products_list.add(row2);
		row2.setLayout(new BorderLayout(0, 0));
		
		ProductInCart product2 = new ProductInCart();
		row2.add(product2, BorderLayout.CENTER);
		
		JPanel row3 = new JPanel();
		row3.setBackground(new Color(255, 255, 255));
		products_list.add(row3);
		row3.setLayout(new BorderLayout(0, 0));
		
		ProductInCart product3 = new ProductInCart();
		row3.add(product3, BorderLayout.CENTER);
		
		JPanel row4 = new JPanel();
		row4.setBackground(new Color(255, 255, 255));
		products_list.add(row4);
		row4.setLayout(new BorderLayout(0, 0));
		
		ProductInCart product4 = new ProductInCart();
		row4.add(product4, BorderLayout.CENTER);
		
		//--------------------------------
		
		JPanel checkout = new JPanel();
		checkout.setBackground(new Color(255, 255, 255));
		add(checkout, BorderLayout.EAST);
		checkout.setLayout(new BorderLayout(0, 0));
		checkout.setPreferredSize(new Dimension(370, 100));
		
		JPanel checkout_north = new JPanel();
		checkout_north.setBackground(new Color(255, 255, 255));
		checkout.add(checkout_north, BorderLayout.NORTH);
		checkout_north.setPreferredSize(new Dimension(100, 80));
		checkout_north.setLayout(null);
		
		JLabel checkout_heading = new JLabel("Thông tin mua hàng");
		checkout_heading.setBounds(44, 30, 271, 40);
		checkout_north.add(checkout_heading);
		checkout_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		
		
		JPanel checkout_center = new JPanel();
		checkout_center.setBackground(new Color(255, 255, 255));
		checkout.add(checkout_center, BorderLayout.CENTER);
		checkout_center.setLayout(null);
		
		JLabel number_products = new JLabel("Tổng sản phẩm");
		number_products.setHorizontalAlignment(SwingConstants.LEFT);
		number_products.setForeground(Color.GRAY);
		number_products.setFont(new Font("SansSerif", Font.PLAIN, 12));
		number_products.setBounds(42, 210, 97, 19);
		checkout_center.add(number_products);
		
		name = new CustomJTextField("Họ tên");
		name.setLocation(42, 30);
		checkout_center.add(name);
		
		email = new CustomJTextField("Email");
		email.setLocation(42, 74);
		checkout_center.add(email);
		
		address = new CustomJTextField("Địa chỉ");
		address.setLocation(42, 118);
		checkout_center.add(address);
		
		phone = new CustomJTextField("Điện thoại");
		phone.setLocation(42, 162);
		checkout_center.add(phone);
		
		JButton checkoutButton = new JButton("Đặt mua hàng");
		checkoutButton.setForeground(Color.WHITE);
		checkoutButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		checkoutButton.setBackground(Color.BLACK);
		checkoutButton.setBounds(42, 304, 252, 32);
		checkout_center.add(checkoutButton);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(211, 211, 211));
		separator.setBackground(new Color(169, 169, 169));
		separator.setBounds(42, 255, 252, 1);
		checkout_center.add(separator);
		
		JLabel total_price = new JLabel("Tổng tiền");
		total_price.setHorizontalAlignment(SwingConstants.LEFT);
		total_price.setForeground(Color.GRAY);
		total_price.setFont(new Font("SansSerif", Font.PLAIN, 12));
		total_price.setBounds(42, 260, 58, 19);
		checkout_center.add(total_price);
		
		JLabel number = new JLabel("4");
		number.setHorizontalAlignment(SwingConstants.LEFT);
		number.setForeground(Color.GRAY);
		number.setFont(new Font("SansSerif", Font.PLAIN, 12));
		number.setBounds(269, 210, 37, 19);
		checkout_center.add(number);
		
		JLabel tota_price2 = new JLabel("100.000đ");
		tota_price2.setHorizontalAlignment(SwingConstants.LEFT);
		tota_price2.setForeground(Color.GRAY);
		tota_price2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tota_price2.setBounds(237, 260, 58, 19);
		checkout_center.add(tota_price2);
		
		JLabel shipping_fee = new JLabel("Phí giao hàng");
		shipping_fee.setHorizontalAlignment(SwingConstants.LEFT);
		shipping_fee.setForeground(Color.GRAY);
		shipping_fee.setFont(new Font("SansSerif", Font.PLAIN, 12));
		shipping_fee.setBounds(42, 230, 97, 19);
		checkout_center.add(shipping_fee);
		
		JLabel shipping_fee2 = new JLabel("10000");
		shipping_fee2.setHorizontalAlignment(SwingConstants.LEFT);
		shipping_fee2.setForeground(Color.GRAY);
		shipping_fee2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		shipping_fee2.setBounds(248, 230, 74, 19);
		checkout_center.add(shipping_fee2);
		
	}
}
