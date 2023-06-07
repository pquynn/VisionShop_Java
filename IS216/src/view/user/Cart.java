package view.user;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import components.CustomJTextField;
import components.CustomScrollPane.CustomScrollPane;
import components.ProductPanels.ProductInCart;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import Connect.OracleConn;
import Email.JavaMail;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cart extends JPanel {
	public Cart(int user_id) {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 650);
		setLayout(new BorderLayout(0, 0));
		
		this.user_id = user_id;
		instanceCart = this;
		
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
		
		scrollPane = new CustomScrollPane();
		mycart.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);

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
		name.setTypingStyle();
		name.setLocation(42, 30);
		checkout_center.add(name);
		
		email = new CustomJTextField("Email");
		email.setLocation(42, 74);
		checkout_center.add(email);
		email.setTypingStyle();
		
		address = new CustomJTextField("Địa chỉ");
		address.setLocation(42, 118);
		address.setTypingStyle();
		checkout_center.add(address);
		
		phone = new CustomJTextField("Điện thoại");
		phone.setTypingStyle();
		phone.setLocation(42, 162);
		checkout_center.add(phone);
		
		JButton checkoutButton = new JButton("Đặt mua hàng");
		checkoutButton.setForeground(Color.WHITE);
		checkoutButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		checkoutButton.setBackground(Color.BLACK);
		checkoutButton.setBounds(42, 285, 252, 32);
		checkout_center.add(checkoutButton);
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ref = JOptionPane.showConfirmDialog(null, "Xác nhận đặt mua hàng", "Đặt hàng", JOptionPane.YES_NO_OPTION);
				if(ref == JOptionPane.YES_OPTION) {
					clearmessage();
					if(validateCheckOut()) {
						updateOrder();
					}
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(211, 211, 211));
		separator.setBackground(new Color(169, 169, 169));
		separator.setBounds(42, 237, 252, 1);
		checkout_center.add(separator);
		
		JLabel total_price = new JLabel("Tổng tiền");
		total_price.setHorizontalAlignment(SwingConstants.LEFT);
		total_price.setForeground(Color.GRAY);
		total_price.setFont(new Font("SansSerif", Font.PLAIN, 12));
		total_price.setBounds(42, 248, 58, 19);
		checkout_center.add(total_price);
		
		quanty = new JLabel("0");
		quanty.setHorizontalAlignment(SwingConstants.RIGHT);
		quanty.setForeground(Color.GRAY);
		quanty.setFont(new Font("SansSerif", Font.PLAIN, 12));
		quanty.setBounds(247, 210, 47, 19);
		checkout_center.add(quanty);
		
		 total_money = new JLabel("0");
		total_money.setHorizontalAlignment(SwingConstants.RIGHT);
		total_money.setForeground(Color.GRAY);
		total_money.setFont(new Font("SansSerif", Font.PLAIN, 12));
		total_money.setBounds(187, 248, 97, 19);
		checkout_center.add(total_money);
		
		name_error = new JLabel();
		name_error.setForeground(Color.RED);
		name_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		name_error.setBounds(42, 58, 252, 19);
		checkout_center.add(name_error);
		
		 email_error = new JLabel();
		email_error.setForeground(Color.RED);
		email_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		email_error.setBounds(42, 102, 252, 19);
		checkout_center.add(email_error);
		
		 address_error = new JLabel();
		address_error.setForeground(Color.RED);
		address_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		address_error.setBounds(42, 148, 252, 19);
		checkout_center.add(address_error);
		
		 phone_error = new JLabel();
		phone_error.setForeground(Color.RED);
		phone_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		phone_error.setBounds(42, 191, 252, 19);
		checkout_center.add(phone_error);
		
		JLabel currency = new JLabel("đ");
		currency.setHorizontalAlignment(SwingConstants.RIGHT);
		currency.setForeground(Color.GRAY);
		currency.setFont(new Font("SansSerif", Font.PLAIN, 12));
		currency.setBounds(277, 248, 17, 19);
		checkout_center.add(currency);
	
		//------------------------------
		setOrderDetailToCart();
		
		//set user detail to checkout pane
		setUserDetail();
	}
	
	// create empty panel
	public JPanel createEmptyJPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		return panel;
	}
	
	//set user information to text field
	public void setUserDetail() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select * from \"User\" where user_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				name.setText(rs.getString("full_name"));
				email.setText(rs.getString("email"));
				address.setText(rs.getString("address"));
				phone.setText(rs.getString("phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// set total glasses and total price label
	public void setTotalGlassesandPrice() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql1 = "select total_glasses, total_money from \"Order\" where order_id = ?";
			PreparedStatement pst1 = conn.prepareStatement(sql1);
			pst1.setInt(1, order_id);  ///phai lay order_id co trang thai la gio hang ra
			ResultSet rs = pst1.executeQuery();
			
			if(rs.next()) {
				quanty.setText(String.valueOf(rs.getInt("total_glasses")));
				total_money.setText(String.valueOf(rs.getInt("total_money")));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//find order_id by user_id and order_state = 'chua xac nhan'
	public void findOrderID() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select order_id from \"Order\" where user_id = ? and \"order_state\" = ?";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setString(2, "Chưa xác nhận");
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				order_id = rs.getInt(1);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//get product list from db
		public ArrayList<Object[]> getProductsList(){
			findOrderID();
			ArrayList<Object[]> products_list = new ArrayList<Object[]>();
			
			try {
				Connection con = OracleConn.getConnection();
				String sql = "select Order_detail.glasses_id as \"id\", Order_detail.glasses_name as \"name\", "
						+ "Order_detail.price \"gprice\", Order_detail.quantity \"quanty\" , image "
					+ "from Order_detail, Glasses where order_id = ? and Order_detail.glasses_id = Glasses.glasses_id order by detail_id"; 
				
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, order_id);
				
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					Object[] row = new Object[5];
					row[0] = rs.getInt("id");
					row[1] = rs.getString("name");
					row[3] = rs.getInt("quanty");
					row[2] = rs.getInt("gprice");
					row[4] = rs.getBytes("image");
					products_list.add(row);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return products_list;
	}
		
		
	// set order_detail list in cart
	public void setOrderDetailToCart() {
		emptyCart();
		
		products_list = getProductsList();
		if(products_list.isEmpty() == false) {
			for(Object[] i : products_list) {
				int id = (int) i[0];
				String name = String.valueOf(i[1]);
				String price = String.valueOf(i[2]);
				String quantity = String.valueOf(i[3]);
				byte[] imagedata = (byte[]) i[4];
				
				ProductInCart p = new ProductInCart(id, name, price, quantity, imagedata, order_id);
				list_pane.add(p);
				p.setCartPane(this);
			}
			setTotalGlassesandPrice();
			for(int i = 0; i < 4 - products_list.size(); i++)
				list_pane.add(createEmptyJPanel());
		}
	}
	
	//empty cart after checkout successfully
	public void emptyCart() {
		list_pane = new JPanel();
		list_pane.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(list_pane);
		list_pane.setLayout(new GridLayout(0, 1, 0, 10));
	}
	
	//remove specific component in jpanel
	public void removeProductInCart() {
		emptyCart();
		setOrderDetailToCart();
	}
	
	//validation
	public boolean validateCheckOut() {
		String full_name = name.getText();
		String address = this.address.getText();
		String email = this.email.getText();
		String phone = this.phone.getText();
		
		boolean check = true;
		//KIEM TRA SP HET HANG CHUA, CON TON TAI KO?, CO VUOT QUA SL TON KHO KO
		check = validateOrderDetails();
				
		//---KIEM TRA DINH DANG THONG TIN
		//full name
		if (full_name.equals("")) {
			name_error.setText("Yêu cầu nhập Họ tên.");
			check = false;
		}
		//email
		if (email.equals("")) {
			email_error.setText("Yêu cầu nhập Email.");
			check = false;
		}
		else {
			if (!email.matches("^.+@.+\\..+$")) {
				email_error.setText("Email không hợp lệ.");
				check = false;
			}
		}
		//address
		if (address.equals("")) {
			address_error.setText("Yêu cầu nhập Địa chỉ.");
			check = false;
		}
		
		//phone
		if (phone.equals("")) {
			phone_error.setText("Yêu cầu nhập Điện thoại.");
			check = false;
		}
		else if((phone.length() != 10 || !phone.matches("[0-9]+"))&& !phone.equals("Điện thoại")) {
			phone_error.setText("Điện thoại không hợp lệ");
			check = false;
		}
		
		return check;
	}
	
	//check Validate order detail---------------------
	public boolean validateOrderDetails() {
		boolean isValid = true;
		ArrayList<Object[]> orderdetail_list = getProductsList(); //glasses_id, glasses_name, price, quantity, image
		if(orderdetail_list.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong giỏ hàng. Tiếp thục mua sắm.");
			isValid = false;
		}
		else {
			Connection conn = OracleConn.getConnection();
			for(Object[] i : orderdetail_list) {
				try {
					String sql="select * from Glasses where glasses_name = ? and quantity >= ?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, String.valueOf(i[1]));
					pst.setInt(2, Integer.parseInt(String.valueOf(i[3])));
					ResultSet rs = pst.executeQuery();
					if(!rs.next()) {
						isValid = false;
						JOptionPane.showMessageDialog(this, String.valueOf(i[1]) + " không đủ hàng hoặc không tồn tại. Vui lòng chọn sản phẩm khác.");
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return isValid;
	}
	
	//clear error message
	public void clearmessage() {
		name_error.setText("");
		phone_error.setText("");
		address_error.setText("");
		email_error.setText("");
	}
	
	//update order - save order
	public void updateOrder() { 
		try {
			Connection conn = OracleConn.getConnection();
			String sql="update \"Order\" set user_id=?, full_name=?, address=?, phone=?, total_glasses=?, total_money=?, \"order_state\"=?, email=? where order_id = ?";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, user_id);
			st.setString(2, name.getText());
			st.setString(3, address.getText());
			st.setString(4, phone.getText());
			st.setInt(5, Integer.parseInt(quanty.getText()));
			st.setInt(6, Integer.parseInt(total_money.getText()));
			st.setString(7, "Chờ giao hàng");
			st.setString(8, email.getText());
			st.setInt(9, order_id);
			
			int rowcount = st.executeUpdate();
			if(rowcount > 0) {
				JOptionPane.showMessageDialog(this, "Đặt hàng thành công.");
				
				//gui email
				String to = email.getText();
				String subject = "[Vision] THÔNG TIN HÓA ĐƠN";
				//lay thong tin Hoa don
				String msg_order = "Tên khách hàng: "+name.getText()
						+"\nĐịa chỉ: "+address.getText()
						+"\nĐiện thoại: "+phone.getText()
						+"\nTổng số mắt kính đã mua: "+quanty.getText()
						+"\nTổng trị giá đơn hàng: "+total_money.getText();
				
				//lay thong tin CTHD
				products_list = getProductsList();
				String msg_detail = "\n\n---CHI TIẾT ĐƠN HÀNG---";
				if(products_list.isEmpty() == false) {
					for(Object[] i : products_list) 
						msg_detail += "\n Tên mắt kính: " + String.valueOf(i[1]) + " - SL: " + String.valueOf(i[3]) + " - Giá: " + String.valueOf(i[2]);
				}
				
				JavaMail mail = new JavaMail(to, subject, msg_order + msg_detail);
				mail.sendEmail();
				JOptionPane.showMessageDialog(this, "Thông tin đơn hàng đã được gửi qua email.");
				
				// lam trong gio hang
				order_id = 0;
				emptyCart();
				quanty.setText("0");
				total_money.setText("0");
			}
			else 
				JOptionPane.showMessageDialog(this, "Đặt hàng không thành công");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CustomJTextField name;
	private CustomJTextField email;
	private CustomJTextField address;
	private CustomJTextField phone;
	private JLabel quanty;
	private JLabel total_money;
	private JLabel name_error;
	private JLabel address_error;
	private JLabel phone_error;
	private JLabel email_error;
	private CustomScrollPane scrollPane;
	private JPanel list_pane;
	public Cart instanceCart;
	private int user_id, order_id;
	private ArrayList<Object[]> products_list;
}
