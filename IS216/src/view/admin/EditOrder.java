package view.admin;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Connect.OracleConn;
import Printer.Printer;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class EditOrder extends JFrame {

	public EditOrder(int order_id) {
		this.order_id = order_id;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(440, 180, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel north = new JPanel();
		north.setBackground(new Color(255, 255, 255));
		contentPane.add(north, BorderLayout.NORTH);
		north.setPreferredSize(new Dimension(100, 200));
		north.setLayout(null);
		
		JLabel order_detail = new JLabel("Chi tiết đơn hàng");
		order_detail.setFont(new Font("SansSerif", Font.BOLD, 26));
		order_detail.setBounds(319, 0, 246, 40);
		north.add(order_detail);
		
		JLabel phone = new JLabel("Điện thoại:");
		phone.setHorizontalAlignment(SwingConstants.LEFT);
		phone.setForeground(Color.GRAY);
		phone.setFont(new Font("SansSerif", Font.PLAIN, 16));
		phone.setBounds(96, 76, 80, 32);
		north.add(phone);
		
		JLabel email = new JLabel("Email:");
		email.setHorizontalAlignment(SwingConstants.LEFT);
		email.setForeground(Color.GRAY);
		email.setFont(new Font("SansSerif", Font.PLAIN, 16));
		email.setBounds(96, 104, 71, 32);
		north.add(email);
		
		JLabel customer_name = new JLabel("Họ tên khách hàng: ");
		customer_name.setHorizontalAlignment(SwingConstants.LEFT);
		customer_name.setForeground(Color.GRAY);
		customer_name.setFont(new Font("SansSerif", Font.PLAIN, 16));
		customer_name.setBounds(96, 50, 139, 32);
		north.add(customer_name);
		
		JLabel address = new JLabel("Địa chỉ:");
		address.setHorizontalAlignment(SwingConstants.LEFT);
		address.setForeground(Color.GRAY);
		address.setFont(new Font("SansSerif", Font.PLAIN, 16));
		address.setBounds(96, 133, 80, 32);
		north.add(address);
		
		JLabel order_day = new JLabel("Ngày mua:");
		order_day.setHorizontalAlignment(SwingConstants.LEFT);
		order_day.setForeground(Color.GRAY);
		order_day.setFont(new Font("SansSerif", Font.PLAIN, 16));
		order_day.setBounds(443, 50, 80, 32);
		north.add(order_day);
		
		txtcreatedat = new JLabel("abc");
		txtcreatedat.setHorizontalAlignment(SwingConstants.LEFT);
		txtcreatedat.setForeground(Color.BLACK);
		txtcreatedat.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtcreatedat.setBounds(533, 51, 158, 32);
		north.add(txtcreatedat);
		
		 txt_totalglasses = new JLabel("abc");
		txt_totalglasses.setHorizontalAlignment(SwingConstants.LEFT);
		txt_totalglasses.setForeground(Color.BLACK);
		txt_totalglasses.setFont(new Font("SansSerif", Font.BOLD, 14));
		txt_totalglasses.setBounds(569, 77, 156, 32);
		north.add(txt_totalglasses);
		
		 txtphone = new JLabel("0123456789");
		txtphone.setHorizontalAlignment(SwingConstants.LEFT);
		txtphone.setForeground(Color.BLACK);
		txtphone.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtphone.setBounds(186, 77, 217, 32);
		north.add(txtphone);
		
		 txttotal_money = new JLabel("abc");
		txttotal_money.setHorizontalAlignment(SwingConstants.LEFT);
		txttotal_money.setForeground(Color.BLACK);
		txttotal_money.setFont(new Font("SansSerif", Font.BOLD, 14));
		txttotal_money.setBounds(572, 105, 172, 32);
		north.add(txttotal_money);
		
		txtname = new JLabel("abc");
		txtname.setHorizontalAlignment(SwingConstants.LEFT);
		txtname.setForeground(Color.BLACK);
		txtname.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtname.setBounds(230, 51, 210, 32);
		north.add(txtname);
		
		JLabel lb_totalglasses = new JLabel("Tổng số mắt kính:");
		lb_totalglasses.setHorizontalAlignment(SwingConstants.LEFT);
		lb_totalglasses.setForeground(Color.GRAY);
		lb_totalglasses.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lb_totalglasses.setBounds(443, 76, 139, 32);
		north.add(lb_totalglasses);
		
		 txtaddress = new JLabel("TP Ho Chi Minh");
		txtaddress.setHorizontalAlignment(SwingConstants.LEFT);
		txtaddress.setForeground(Color.BLACK);
		txtaddress.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtaddress.setBounds(161, 134, 272, 32);
		north.add(txtaddress);
		
		JLabel orderstate = new JLabel("Trạng thái đơn hàng:");
		orderstate.setHorizontalAlignment(SwingConstants.LEFT);
		orderstate.setForeground(Color.GRAY);
		orderstate.setFont(new Font("SansSerif", Font.PLAIN, 16));
		orderstate.setBounds(443, 133, 151, 32);
		north.add(orderstate);
		
		 txtemail = new JLabel("abc@gmail.com");
		txtemail.setHorizontalAlignment(SwingConstants.LEFT);
		txtemail.setForeground(Color.BLACK);
		txtemail.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtemail.setBounds(151, 105, 266, 32);
		north.add(txtemail);
		
		JLabel total_money = new JLabel("Trị giá đơn hàng:");
		total_money.setHorizontalAlignment(SwingConstants.LEFT);
		total_money.setForeground(Color.GRAY);
		total_money.setFont(new Font("SansSerif", Font.PLAIN, 16));
		total_money.setBounds(443, 104, 127, 32);
		north.add(total_money);
		
		txtstate = new JLabel("Chờ giao");
		txtstate.setHorizontalAlignment(SwingConstants.LEFT);
		txtstate.setForeground(Color.BLACK);
		txtstate.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtstate.setBounds(593, 133, 194, 32);
		north.add(txtstate);
		
		JPanel south = new JPanel();
		south.setBackground(new Color(255, 255, 255));
		contentPane.add(south, BorderLayout.SOUTH);
		south.setPreferredSize(new Dimension(100, 80));
		south.setLayout(null);
		
		//Product list table
		product_list = new JTable();
		product_list.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"Tên SP", "Số lượng", "Giá", "Thành tiền"}
		));
		product_list.setRowSelectionAllowed(false);
		product_list.setShowVerticalLines(false);
		product_list.setBorder(null);
		product_list.setForeground(new Color(0, 0, 0));
		product_list.setRowHeight(50);
		product_list.setGridColor(new Color(211, 211, 211));
		
		product_list.getTableHeader().setBackground(Color.WHITE);
		product_list.getTableHeader().setForeground(Color.black);
		product_list.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		product_list.getTableHeader().setPreferredSize(new Dimension(100, 30));
		    
		customColumnN(0, 100);
		customColumnN(1, 20);
		customColumnN(2, 20);
		customColumnN(3, 20);
		setOrderDetailToTable();
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();	
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		setBackground(new Color(255, 255, 255));
		product_list.setFont(new Font("SansSerif", Font.PLAIN, 13));
		scrollPane.setViewportView(product_list);
		
		JPanel west = new JPanel();
		west.setBackground(new Color(255, 255, 255));
		contentPane.add(west, BorderLayout.WEST);
		west.setPreferredSize(new Dimension(50, 100));
		
		JPanel east = new JPanel();
		east.setBackground(new Color(255, 255, 255));
		contentPane.add(east, BorderLayout.EAST);
		east.setPreferredSize(new Dimension(50, 100));

		btPrint = new JButton("In hóa đơn");
		btPrint.setBounds(75, 22, 141, 32);
		south.add(btPrint);
		btPrint.setForeground(Color.WHITE);
		btPrint.setFont(new Font("SansSerif", Font.BOLD, 16));
		btPrint.setBackground(Color.BLACK);
		btPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ref = JOptionPane.showConfirmDialog(null, "Bạn muốn in hóa đơn", "In", JOptionPane.YES_NO_OPTION);
				if(ref == JOptionPane.YES_OPTION) {
					Printer printer = new Printer(contentPane);
					printer.print();
				}
			}
		});
		
		btPayment = new JButton("Đã thanh toán");
		btPayment.setForeground(Color.WHITE);
		btPayment.setFont(new Font("SansSerif", Font.BOLD, 16));
		btPayment.setBackground(Color.BLACK);
		btPayment.setBounds(600, 22, 213, 32);
		south.add(btPayment);
		btPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ref = JOptionPane.showConfirmDialog(null, "Xác nhận đơn hàng đã được thanh toán", "Đã thanh toán", JOptionPane.YES_NO_OPTION);
				if(ref == JOptionPane.YES_OPTION)
					setPaymentState();
			}
		});
		
		btDelivery = new JButton("Bắt đầu giao hàng");
		btDelivery.setForeground(Color.WHITE);
		btDelivery.setFont(new Font("SansSerif", Font.BOLD, 16));
		btDelivery.setBackground(Color.BLACK);
		btDelivery.setBounds(363, 22, 213, 32);
		south.add(btDelivery);
		btDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ref = JOptionPane.showConfirmDialog(null, "Xác nhận bắt đầu giao hàng", "Bắt đầu giao hàng", JOptionPane.YES_NO_OPTION);
				if(ref == JOptionPane.YES_OPTION)
					setDeliveryState();
			}
		});
		
		//set order detail by id
		setOrderDetailById();
			
	}
		
	//custom column by index
	public void customColumnN(int index, int w) {
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();	
		center.setHorizontalAlignment( JLabel.CENTER );
		product_list.getColumnModel().getColumn(index).setPreferredWidth(w);
		product_list.getColumnModel().getColumn(index).setCellRenderer(center);
		
	}
	
	public void setOrdersPanel(Orders orders) {
		this.orders = orders;
	}
	
	public void setOrderDetailById() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select * from \"Order\" where order_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order_id);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				txtname.setText(rs.getString("full_name"));
				txtphone.setText(rs.getString("phone"));
				txtemail.setText(rs.getString("email"));
				txtaddress.setText(rs.getString("address"));
				txtcreatedat.setText(String.valueOf(rs.getDate("created_at")));
				txt_totalglasses.setText(String.valueOf(rs.getInt("total_glasses")));
				txttotal_money.setText(String.valueOf(rs.getInt("total_money")));
				txtstate.setText(rs.getString("\"order_state\""));
			}
			hideButton();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		        
				Object[] objects= {glasses_name, quantity, price, into_money};
				model = (DefaultTableModel)product_list.getModel();
				model.addRow(objects);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	//change order state to "đang giao hang"
	public void setDeliveryState() {
		try {
			Connection connection = OracleConn.getConnection();
			String sql = "update \"Order\" set \"order_state\" = 'Đang giao hàng' where order_id = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, order_id);
			
			int rowcount = pst.executeUpdate();
			if(rowcount > 0) {
				txtstate.setText("Đang giao hàng");
				hideButton();
				orders.resetTable();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//change order state to "da thanh toan"
	public void setPaymentState() {
		if(txtstate.getText().equals("Chờ giao hàng")) {
			JOptionPane.showMessageDialog(this, "Không thành công. Đơn hàng chưa được giao.");
		}
		else {
			try {
				Connection connection = OracleConn.getConnection();
				String sql = "update \"Order\" set \"order_state\" = 'Đã thanh toán' where order_id = ?";
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setInt(1, order_id);
				
				int rowcount = pst.executeUpdate();
				if(rowcount > 0) {
					txtstate.setText("Đã thanh toán");
					hideButton();
					orders.resetTable();
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//public disable button
	public void hideButton() {
		if(txtstate.getText().equals("Đang giao hàng")) 
			btDelivery.setVisible(false);
		if(txtstate.getText().equals("Đã thanh toán")) {
			btDelivery.setVisible(false);
			btPayment.setVisible(false);
		}
	}

	private JPanel contentPane;
	private JTable product_list;
	private JLabel txtname;
	private JLabel txtphone;
	private JLabel txtemail;
	private JLabel txtcreatedat;
	private JLabel txttotal_money;
	private JLabel txt_totalglasses;
	private JLabel txtaddress;
	private JLabel txtstate;
	private JButton btPayment;
	private JButton btDelivery;
	private JButton btPrint;
	private DefaultTableModel model;
	private Orders orders;
	private int order_id;
}

				
