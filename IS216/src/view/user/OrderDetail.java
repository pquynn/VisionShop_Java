package view.user;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import view.user.MyOrder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderDetail extends JFrame {

	private JPanel contentPane;
	private JTable product_list;
	private JLabel txtname;
	private JLabel txtphone;
	private JLabel txtemail;
	private JLabel txtcreatedat;
	private JLabel txttotal_money;
	private JLabel txtshippingfee;
	private JLabel txtaddress;
	private JLabel order_id;
	private JButton btCancel;
	
	private MyOrder myOrder;
	private int id;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetail frame = new OrderDetail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public OrderDetail() {
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
		txtcreatedat.setBounds(533, 51, 119, 32);
		north.add(txtcreatedat);
		
		 txtshippingfee = new JLabel("abc");
		txtshippingfee.setHorizontalAlignment(SwingConstants.LEFT);
		txtshippingfee.setForeground(Color.BLACK);
		txtshippingfee.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtshippingfee.setBounds(556, 77, 119, 32);
		north.add(txtshippingfee);
		
		 txtphone = new JLabel("0123456789");
		txtphone.setHorizontalAlignment(SwingConstants.LEFT);
		txtphone.setForeground(Color.BLACK);
		txtphone.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtphone.setBounds(186, 77, 119, 32);
		north.add(txtphone);
		
		 txttotal_money = new JLabel("abc");
		txttotal_money.setHorizontalAlignment(SwingConstants.LEFT);
		txttotal_money.setForeground(Color.BLACK);
		txttotal_money.setFont(new Font("SansSerif", Font.BOLD, 14));
		txttotal_money.setBounds(572, 105, 119, 32);
		north.add(txttotal_money);
		
		txtname = new JLabel("abc");
		txtname.setHorizontalAlignment(SwingConstants.LEFT);
		txtname.setForeground(Color.BLACK);
		txtname.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtname.setBounds(245, 51, 119, 32);
		north.add(txtname);
		
		 order_id = new JLabel("Phí giao hàng:");
		order_id.setHorizontalAlignment(SwingConstants.LEFT);
		order_id.setForeground(Color.GRAY);
		order_id.setFont(new Font("SansSerif", Font.PLAIN, 16));
		order_id.setBounds(443, 76, 119, 32);
		north.add(order_id);
		
		 txtaddress = new JLabel("TP Ho Chi Minh");
		txtaddress.setHorizontalAlignment(SwingConstants.LEFT);
		txtaddress.setForeground(Color.BLACK);
		txtaddress.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtaddress.setBounds(161, 134, 119, 32);
		north.add(txtaddress);
		
		JLabel shipping = new JLabel("Trạng thái đơn hàng:");
		shipping.setHorizontalAlignment(SwingConstants.LEFT);
		shipping.setForeground(Color.GRAY);
		shipping.setFont(new Font("SansSerif", Font.PLAIN, 16));
		shipping.setBounds(443, 133, 151, 32);
		north.add(shipping);
		
		 txtemail = new JLabel("abc@gmail.com");
		txtemail.setHorizontalAlignment(SwingConstants.LEFT);
		txtemail.setForeground(Color.BLACK);
		txtemail.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtemail.setBounds(151, 105, 119, 32);
		north.add(txtemail);
		
		JLabel total_money = new JLabel("Trị giá đơn hàng:");
		total_money.setHorizontalAlignment(SwingConstants.LEFT);
		total_money.setForeground(Color.GRAY);
		total_money.setFont(new Font("SansSerif", Font.PLAIN, 16));
		total_money.setBounds(443, 104, 127, 32);
		north.add(total_money);
		
		JLabel state = new JLabel("Chờ giao");
		state.setHorizontalAlignment(SwingConstants.LEFT);
		state.setForeground(Color.BLACK);
		state.setFont(new Font("SansSerif", Font.BOLD, 14));
		state.setBounds(593, 133, 119, 32);
		north.add(state);
		
		
		
		
		JPanel south = new JPanel();
		south.setBackground(new Color(255, 255, 255));
		contentPane.add(south, BorderLayout.SOUTH);
		south.setPreferredSize(new Dimension(100, 80));
		south.setLayout(null);
		
		

		product_list = new JTable();
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
		    
		
		//String space = "                                                             "; // chua xong, moi len y tuong ve khoang cach 
		product_list.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"#", "T\u00EAn m\u1EAFt k\u00EDnh", "Ph\u00E2n lo\u1EA1i", "H\u00ECnh \u1EA3nh", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u00E1", "Th\u00E0nh ti\u1EC1n"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//orders_list.getColumnModel().getColumn(0).setResizable(false);
		//orders_list.getColumnModel().getColumn(0).setPreferredWidth(30);
		
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
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();	
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		btCancel = new JButton("Hủy đơn");
		btCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận Huỷ cập nhật", "Hủy", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
					//kiem tra co cho huy dơn ko, chuyen trang thai huy don
					//reset lai cac table co lien quan den don (order, myorder)
					dispose();
				}
			}
		});
		btCancel.setForeground(Color.WHITE);
		btCancel.setFont(new Font("SansSerif", Font.BOLD, 16));
		btCancel.setBackground(Color.BLACK);
		btCancel.setBounds(600, 22, 213, 32);
		south.add(btCancel);
		
			
	}
		
	public void setId(int id) {
		this.id = id;
	}
		
	public void setMyOrdersPanel(MyOrder myOrder) {
		this.myOrder = myOrder;
	}
	
	public void resetTable() {
		//myOrder.clearTable();
		//myOrder.setOrdersToTable();
	}
	
	public void updateOrder() {
		
	}
	
	public void showOrderInfo() {
		
	}
	public void setOrderDetailToTable() {
		
	}
}

				
