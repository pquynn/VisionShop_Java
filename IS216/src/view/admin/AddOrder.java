package view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Connect.OracleConn;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import components.CustomJTextField;
import view.user.OrderDetail;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddOrder extends JFrame {

	private JPanel pane;
	private JLabel quantity_error;
	private JLabel name_error;
	private JLabel phone_error;
	private CustomJTextField name;
	private CustomJTextField email;
	private CustomJTextField address;
	private CustomJTextField phone;
	private CustomJTextField price;
	private CustomJTextField quantity;
	private JComboBox users_list;
	private JComboBox products_list;
	private JLabel number_products;
	private JLabel total_price;
	private JLabel txt_tota_price;
	private JLabel txt_number;
	
	private JTable orderdetails_list;
	private DefaultTableModel model;
	private Orders orders;
	private OrderDetail orderDetail;
	private int order_id = -1;
	private int user_id = -1;
	private int glasses_id = -1;
	private String glasses_name = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOrder frame = new AddOrder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AddOrder() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận thoát", "Thoát", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
				//delete order
					deleteOrder();
					
				dispose();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(435, 100, 880, 622);
		pane = new JPanel();
		pane.setBackground(new Color(255, 255, 255));
		pane.setBorder(null);

		setContentPane(pane);
		pane.setLayout(null);
		
		//INSERT NEW ORDER
		insertEmptyOrder();
		
		JLabel order_heading = new JLabel("Nhập thông tin khách hàng");
		order_heading.setFont(new Font("SansSerif", Font.BOLD, 22));
		order_heading.setBounds(122, 10, 290, 40);
		pane.add(order_heading);
		
		JLabel nameLabel = new JLabel("Họ tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setForeground(Color.GRAY);
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		nameLabel.setBounds(87, 150, 62, 32);
		pane.add(nameLabel);
		
		name = new CustomJTextField("Họ tên");
		name.setText("");
		name.setBounds(154, 150, 228, 32);
		name.setTypingStyle();
		pane.add(name);
		
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setForeground(Color.GRAY);
		emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		emailLabel.setBounds(77, 100, 71, 32);
		pane.add(emailLabel);
		
		 email = new CustomJTextField("Email");
		 email.setText("");
		 email.setEditable(false);
		email.setBounds(153, 102, 228, 32);
		email.setTypingStyle();
		pane.add(email);
		
		JLabel addressLabel = new JLabel("Địa chỉ: ");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setForeground(Color.GRAY);
		addressLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		addressLabel.setBounds(72, 192, 71, 32);
		pane.add(addressLabel);
		
		address = new CustomJTextField("Địa chỉ");
		address.setText("");
		address.setBounds(153, 194, 228, 32);
		address.setTypingStyle();
		pane.add(address);
		
		
		JLabel phoneLabel = new JLabel("Điện thoại:");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setForeground(Color.GRAY);
		phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		phoneLabel.setBounds(67, 239, 76, 32);
		pane.add(phoneLabel);
		
		 phone = new CustomJTextField("Điện thoại");
		 phone.setText("");
		 phone.setTypingStyle();
		 phone.addKeyListener(new KeyAdapter() {
		 	public void keyReleased(KeyEvent e) {
		 		if((phone.getText().length() != 10 || !phone.getText().matches("[0-9]+"))&& !phone.getText().equals("Điện thoại")) {
		 			phone_error.setText("Điện thoại không hợp lệ");
		 			}
		 	}
		 	public void keyPressed(KeyEvent e) {
		 		phone_error.setText("");
		 			
		 	}
		 });
		 
		phone.setBounds(153, 239, 228, 32);
		pane.add(phone);
		
		
		JButton btAddOrder = new JButton("Thanh toán");
		btAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ref = JOptionPane.showConfirmDialog(null, "Xác nhận thanh toán đơn hàng", "Thanh toán", JOptionPane.YES_NO_OPTION);
				if(ref == JOptionPane.YES_OPTION) {
					clearMessage();
					if(validateSaveOrder() == true) {
						updateOrder();
					}
				}
			}
		});
		btAddOrder.setForeground(Color.WHITE);
		btAddOrder.setFont(new Font("SansSerif", Font.BOLD, 16));
		btAddOrder.setBackground(Color.BLACK);
		btAddOrder.setBounds(595, 535, 221, 32);
		pane.add(btAddOrder);
		
		JLabel id_name = new JLabel("Mã KH | Tên KH");
		id_name.setHorizontalAlignment(SwingConstants.RIGHT);
		id_name.setForeground(Color.GRAY);
		id_name.setFont(new Font("SansSerif", Font.PLAIN, 14));
		id_name.setBounds(39, 58, 109, 32);
		pane.add(id_name);
		
		users_list = new JComboBox();
		users_list.setForeground(new Color(105,105,105));
		users_list.setFont(new Font("SansSerif", Font.PLAIN, 14));
		users_list.setBackground(new Color(255, 255, 255));
		users_list.setBounds(153, 59, 228, 29);
		users_list.setBorder(null);
		pane.add(users_list);
		setUserList();
		setUserDetailByID();
		users_list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setUserDetailByID();
				
			}
		});
		
		quantity_error = new JLabel();
		quantity_error.setForeground(Color.RED);
		quantity_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		quantity_error.setBounds(534, 176, 158, 21);
		pane.add(quantity_error);
		
		phone_error = new JLabel();
		phone_error.setForeground(Color.RED);
		phone_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		phone_error.setBounds(153, 267, 177, 21);
		pane.add(phone_error);
		
		JLabel order_heading_1 = new JLabel("Nhập chi tiết hóa đơn");
		order_heading_1.setFont(new Font("SansSerif", Font.BOLD, 22));
		order_heading_1.setBounds(533, 10, 244, 40);
		pane.add(order_heading_1);
		
		number_products = new JLabel("Tổng sản phẩm");
		number_products.setHorizontalAlignment(SwingConstants.LEFT);
		number_products.setForeground(Color.GRAY);
		number_products.setFont(new Font("SansSerif", Font.PLAIN, 13));
		number_products.setBounds(595, 496, 97, 19);
		pane.add(number_products);
		
		total_price = new JLabel("Tổng tiền");
		total_price.setHorizontalAlignment(SwingConstants.LEFT);
		total_price.setForeground(Color.GRAY);
		total_price.setFont(new Font("SansSerif", Font.BOLD, 13));
		total_price.setBounds(595, 514, 71, 19);
		pane.add(total_price);
		
		txt_tota_price = new JLabel("0");
		txt_tota_price.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_tota_price.setForeground(Color.GRAY);
		txt_tota_price.setFont(new Font("SansSerif", Font.BOLD, 13));
		txt_tota_price.setBounds(747, 514, 58, 19);
		pane.add(txt_tota_price);
		
		txt_number = new JLabel("0");
		txt_number.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_number.setForeground(Color.GRAY);
		txt_number.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txt_number.setBounds(768, 496, 37, 19);
		pane.add(txt_number);
		
		JLabel prodcut_listLabel = new JLabel("Mã MK | Tên MK:");
		prodcut_listLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		prodcut_listLabel.setForeground(Color.GRAY);
		prodcut_listLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		prodcut_listLabel.setBounds(404, 58, 124, 32);
		pane.add(prodcut_listLabel);
		

		JLabel priceLabel = new JLabel("Đơn giá:");
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLabel.setForeground(Color.GRAY);
		priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		priceLabel.setBounds(467, 100, 62, 32);
		pane.add(priceLabel);
		
		price = new CustomJTextField();
		price.setEditable(false);
		price.setBounds(534, 102, 228, 32);
		price.setTypingStyle();
		pane.add(price);
		

		quantity = new CustomJTextField();
		quantity.setBounds(533, 150, 228, 32);
		quantity.setTypingStyle();
		pane.add(quantity);
		
		products_list = new JComboBox();
		products_list.setForeground(new Color(105,105,105));
		products_list.setFont(new Font("SansSerif", Font.PLAIN, 14));
		products_list.setBorder(null);
		products_list.setBackground(Color.WHITE);
		products_list.setBounds(533, 59, 228, 29);
		pane.add(products_list);
		setProductList();
		setglassesDetailByID();
		products_list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setglassesDetailByID();
			}
		});
		JLabel quantityLabel = new JLabel("Số lượng");
		quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		quantityLabel.setForeground(Color.GRAY);
		quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		quantityLabel.setBounds(466, 147, 62, 32);
		pane.add(quantityLabel);
		
		
		JButton btAddDetail = new JButton("Thêm");
		btAddDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessage();
				if(!checkDuplicateGlasses()) {
					if(validateAddOderDetail())
						insertOrderDetail();
				}
				else {
					JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại trong hóa đơn");
				}
			}
		});
		btAddDetail.setForeground(new Color(0, 0, 0));
		btAddDetail.setFont(new Font("SansSerif", Font.BOLD, 16));
		btAddDetail.setBackground(new Color(255, 255, 255));
		btAddDetail.setBounds(512, 193, 85, 32);
		pane.add(btAddDetail);
		
		JButton btDelete = new JButton("Xóa");
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận Xóa sản phẩm", "Xóa", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
					deleteOrderDetail();
					model.removeRow(orderdetails_list.getSelectedRow());
				}
			}
		});
		btDelete.setForeground(new Color(0, 0, 0));
		btDelete.setFont(new Font("SansSerif", Font.BOLD, 16));
		btDelete.setBackground(new Color(255, 255, 255));
		btDelete.setBounds(692, 193, 85, 32);
		pane.add(btDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 290, 766, 198);
		pane.add(scrollPane);
		
		//Product list table
		orderdetails_list = new JTable();
		orderdetails_list.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"Tên SP", "Giá", "Số lượng", "Thành tiền"}
		));
		orderdetails_list.setRowSelectionAllowed(false);
		orderdetails_list.setShowVerticalLines(false);
		orderdetails_list.setBorder(null);
		orderdetails_list.setForeground(new Color(0, 0, 0));
		orderdetails_list.setRowHeight(50);
		orderdetails_list.setGridColor(new Color(211, 211, 211));
		
		orderdetails_list.getTableHeader().setBackground(Color.WHITE);
		orderdetails_list.getTableHeader().setForeground(Color.black);
		orderdetails_list.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		orderdetails_list.getTableHeader().setPreferredSize(new Dimension(100, 30));

		orderdetails_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayODToJTextField();
			}
		});
		
		customColumnN(0, 100);
		customColumnN(1, 20);
		customColumnN(2, 20);
		customColumnN(3, 20);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();	
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		scrollPane.setViewportView(orderdetails_list);
		
		name_error = new JLabel();
		name_error.setForeground(Color.RED);
		name_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		name_error.setBounds(153, 176, 177, 21);
		pane.add(name_error);
		
		JButton btEditDetail = new JButton("Sửa");
		btEditDetail.setForeground(Color.BLACK);
		btEditDetail.setFont(new Font("SansSerif", Font.BOLD, 16));
		btEditDetail.setBackground(Color.WHITE);
		btEditDetail.setBounds(602, 194, 85, 32);
		pane.add(btEditDetail);
		setOrderDetailToTable();
		btEditDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận Sửa sản phẩm", "Sửa thông tin", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
					updateOrderDetail();
				}
			}
		});
	}
	
	//insert new order with order state is 'chua xac nhan'
	public void insertEmptyOrder() {
		//Find recent order_id in table and set new order_id
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select order_id from \"Order\" order by order_id DESC fetch first 1 row only";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if (!rs.next()) 
				order_id = 1;
			else 
				order_id = rs.getInt("order_id") + 1;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//insert new order with that order_id
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "insert into \"Order\" (order_id, \"order_state\", total_glasses, total_money) values (?,?, 0, 0)";
			//nho lm them may cai trigger cho tong tien = 0 do a
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, order_id);
			st.setString(2, "Chưa xác nhận");
			
			int rowcount = st.executeUpdate();
			if(rowcount < 0)
				JOptionPane.showMessageDialog(this, "Xảy ra lỗi");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//set category name to combobox category
    public void setUserList() {
    	try {
    		Connection con = OracleConn.getConnection();
			String sql = "select user_id, full_name from \"User\" order by user_id";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				String item = String.valueOf(rs.getInt("user_id")) + " | " + rs.getString("full_name");
				users_list.addItem(item); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
  //user---------------------------
	//set user detail by id
	public void setUserDetailByID() {
		//get the user_id from selected item
		if(users_list.getItemCount() > 0) {
			String selected_user = users_list.getSelectedItem().toString();
			int iend = selected_user.indexOf(" "); 

			String subString = "-1";
			if (iend != -1) 
			    subString= selected_user.substring(0 , iend); 
			user_id = Integer.parseInt(subString);
			
			//set the user detail to textfields
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
		
	}
	//set category name to combobox category
    public void setProductList() {
    	try {
    		Connection con = OracleConn.getConnection();
			String sql = "select glasses_id, glasses_name from Glasses order by glasses_id";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				String item = String.valueOf(rs.getInt("glasses_id")) + " | " + rs.getString("glasses_name");
				products_list.addItem(item); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	//set glasses detail by id
	public void setglassesDetailByID() {
		//get the user_id from selected item
		if(products_list.getItemCount()> 0) {
			String selected_glasses = products_list.getSelectedItem().toString();
			int iend = selected_glasses.indexOf(" "); 

			String subString = "-1";
			if (iend != -1) 
			    subString= selected_glasses.substring(0 , iend); 
			glasses_id = Integer.parseInt(subString);
			
			//set the glasses detail to textfields
			try {
				Connection conn = OracleConn.getConnection();
				String sql = "select * from Glasses where glasses_id = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, glasses_id);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					price.setText(String.valueOf(rs.getInt("price")));
					quantity.setText("");
					glasses_name = rs.getString("glasses_name");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// insert order details-------------- 
	public void insertOrderDetail() {
		int quantity = Integer.parseInt(this.quantity.getText());
		int price = Integer.parseInt(this.price.getText());
		try {
			Connection con = OracleConn.getConnection();
			String sql = "insert into Order_detail(order_id, glasses_id, glasses_name, quantity, price) values (?,?,?,?,?)";
			PreparedStatement prs = con.prepareStatement(sql);
			
			prs.setInt(1, order_id);
			prs.setInt(2, glasses_id);
			prs.setString(3, glasses_name);
			prs.setInt(4, quantity);
			prs.setInt(5, price);
			
			int RowCount = prs.executeUpdate();
			if(RowCount > 0) {
				resetTable();
				setTotalGlassesandPrice();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetTable() {
		model = (DefaultTableModel) orderdetails_list.getModel();
		model.setRowCount(0);
		setOrderDetailToTable();
	}

	public boolean validateAddOderDetail() {
		String quantity = this.quantity.getText();
		String phone = this.phone.getText();
		boolean isValid = true;
		if(quantity.equals(""))
		{
			isValid = false;
			quantity_error.setText("Yêu cầu nhập số lượng");
		}
		else if((!quantity.matches("[0-9]+"))) {
			isValid = false;
			quantity_error.setText("Yêu cầu nhập số lượng");
		}
		else if(Integer.parseInt(quantity) <= 0) {
			isValid = false;
			quantity_error.setText("Số lượng sản phẩm phải > 0");
		}
		
		return isValid;
	}
	
	//----------------------------------
	//set order detail from table to jtextfield
	public void displayODToJTextField() {
		int index = orderdetails_list.getSelectedRow();
		if(index < model.getRowCount() && index >= 0) {
			String glasses_name = model.getValueAt(index, 0).toString();
			try {
				Connection conn = OracleConn.getConnection();
				String sql1 = "select glasses_id from Glasses where glasses_name = ?";
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setString(1, glasses_name);
				ResultSet rs = pst1.executeQuery();
				if(rs.next()) {
					glasses_id = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String string = String.valueOf(glasses_id) + " | " + glasses_name;
			products_list.setSelectedItem(string);
			price.setText(model.getValueAt(index, 1).toString());
			quantity.setText(model.getValueAt(index, 2).toString());
		}
	}
	
	//delete order detail---------------
	public void deleteOrderDetail() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql1 = "delete from Order_detail where order_id = ? and glasses_id = ?";
			PreparedStatement pst1 = conn.prepareStatement(sql1);
			pst1.setInt(1, order_id);
			pst1.setInt(2, glasses_id);
			int rowcount1 = pst1.executeUpdate();
			setTotalGlassesandPrice();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// update order details-------------- 
		public void updateOrderDetail() {
			try {
				Connection con = OracleConn.getConnection();
				String sql = "update Order_detail set quantity = ? where glasses_id = ? and order_id = ?";
				PreparedStatement prs = con.prepareStatement(sql);
				
				prs.setInt(1, Integer.parseInt(quantity.getText()));
				prs.setInt(2, glasses_id);
				prs.setInt(3, order_id);
				
				int RowCount = prs.executeUpdate();
				if(RowCount > 0) {
					//resetTable();
					int index = orderdetails_list.getSelectedRow();
					if(index < model.getRowCount() && index >= 0) {
						int update_intomoney = Integer.parseInt(price.getText()) * Integer.parseInt(quantity.getText());
						model.setValueAt(quantity.getText(), index, 2);
						model.setValueAt(String.valueOf(update_intomoney), index, 3);
					}
					setTotalGlassesandPrice();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	// check duplicate user 
	public boolean checkDuplicateGlasses() {
		boolean isExist = false;
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select * from Order_detail where glasses_id = ? and order_id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, glasses_id);
			pst.setInt(2, order_id);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				isExist = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return isExist;
	}
	
	// clear validation messages 
	public void clearMessage() {
		quantity_error.setText("");
		phone_error.setText("");
	}
	
	//custom column by index
	public void customColumnN(int index, int w) {
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();	
		center.setHorizontalAlignment( JLabel.CENTER );
		orderdetails_list.getColumnModel().getColumn(index).setPreferredWidth(w);
		orderdetails_list.getColumnModel().getColumn(index).setCellRenderer(center);
	}
		
	public void setOrdersPanel(Orders orders) {
		this.orders = orders;
	}
	
	//update order - save order---------------------------------
	public void updateOrder() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql="update \"Order\" set user_id=?, full_name=?, address=?, phone=?, total_glasses=?, total_money=?, \"order_state\"=?, email=? where order_id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, user_id);
			st.setString(2, name.getText());
			st.setString(3, address.getText());
			st.setString(4, phone.getText());
			st.setInt(5, Integer.parseInt(txt_number.getText()));
			st.setInt(6, Integer.parseInt(txt_tota_price.getText()));
			st.setString(7, "Đã thanh toán");
			st.setString(8, email.getText());
			st.setInt(9, order_id);
			
			int rowcount = st.executeUpdate();
			if(rowcount > 0) {
				JOptionPane.showMessageDialog(this, "Xác nhận đơn hàng đã thanh toán");
				dispose();
				orders.resetTable();
			}
			else 
				JOptionPane.showMessageDialog(this, "Thao tác bị lỗi");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//validation
	public boolean validateSaveOrder() {
		String full_name = name.getText();
		String phone = this.phone.getText();
		boolean check = true;
		//kiem tra hang ton kho
		check = validateOrderDetails();
		
		//kiem tra dinh dang
		if(full_name.equals(""))
			name_error.setText("Yêu cầu nhập tên khách hàng");
		
		if((phone.length() != 10 || !phone.matches("[0-9]+"))&& !phone.equals("")) {
			check = false;
			phone_error.setText("Số điện thoại sai định dạng");
		}		
		
		return check;
	}
	
	//check Validate order detail---------------------
	public boolean validateOrderDetails() {
		boolean isValid = true;
		model = (DefaultTableModel)orderdetails_list.getModel();
		if(model.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong giỏ hàng. Tiếp thục mua sắm.");
			isValid = false;
		}
		else {
			Connection conn = OracleConn.getConnection();
			for(int i = 0; i < model.getRowCount(); i++) {
				try {
					String sql="select * from Glasses where glasses_name = ? and quantity >= ?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, model.getValueAt(i, 0).toString());
					pst.setInt(2, Integer.parseInt(model.getValueAt(i, 2).toString()));
					ResultSet rs = pst.executeQuery();
					if(!rs.next()) {
						isValid = false;
						JOptionPane.showMessageDialog(this, model.getValueAt(i, 0).toString() + " không đủ hàng hoặc không tồn tại. Vui lòng chọn sản phẩm khác.");
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return isValid;
	}
	//set order detail to table
	public void setOrderDetailToTable() {
		String glasses_name;
		int quantity, price, into_money, glasses_id;
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select * from Order_detail where order_id = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order_id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				quantity = rs.getInt("quantity");
				price = rs.getInt("price");
				into_money = rs.getInt("into_money");
				glasses_name = rs.getString("glasses_name");
		        
				Object[] objects= {glasses_name, price, quantity, into_money};
				model = (DefaultTableModel)orderdetails_list.getModel();
				model.addRow(objects);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//delete order 
	public void deleteOrder() {
		try {
			Connection conn = OracleConn.getConnection();
			
			String sql2 = "delete from \"Order\" where order_id = ?"; 
			PreparedStatement pst2 = conn.prepareStatement(sql2);
			pst2.setInt(1, order_id);
			int rowcount2 = pst2.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	//set total glasses and total price when update order detail
	public void setTotalGlassesandPrice() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql1 = "select total_glasses, total_money from \"Order\" where order_id = ?";
			PreparedStatement pst1 = conn.prepareStatement(sql1);
			pst1.setInt(1, order_id);
			ResultSet rs = pst1.executeQuery();
			
			if(rs.next()) {
				txt_number.setText(String.valueOf(rs.getInt("total_glasses")));
				txt_tota_price.setText(String.valueOf(rs.getInt("total_money")));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
