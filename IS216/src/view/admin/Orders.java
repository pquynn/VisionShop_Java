package view.admin;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import components.CustomJTextField;
import components.CustomScrollPane.CustomScrollPane;
import components.CustomTable.MultiButtonTable;
import components.CustomTable.TableEvent;
import view.user.OrderDetail;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Connect.OracleConn;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.PatternSyntaxException;
import javax.swing.DefaultComboBoxModel;

public class Orders extends JPanel {

	private MultiButtonTable orders_list;
	private CustomJTextField search_order;
	private JComboBox search_by;
	private DefaultTableModel model;
	private TableEvent event;
	
	private OrderDetail orderDetail;
	
	public Orders() {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 600);
		setLayout(new BorderLayout(0, 0));
		
		JPanel content1 = new JPanel();
		content1.setBackground(new Color(255, 255, 255));
		add(content1, BorderLayout.NORTH);
		content1.setLayout(new BorderLayout());
		content1.setPreferredSize(new Dimension(100, 120));
		
		JPanel content1_east = new JPanel();
		content1_east.setBackground(new Color(255, 255, 255));
		content1.add(content1_east, BorderLayout.EAST);
		content1_east.setLayout(null);
		content1_east.setPreferredSize(new Dimension(200, 100));
		
		JPanel content1_center = new JPanel();
		content1_center.setBackground(new Color(255, 255, 255));
		content1.add(content1_center, BorderLayout.CENTER);
		content1_center.setLayout(null);
		
		JLabel orders_heading = new JLabel("Đơn hàng ");
		orders_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		orders_heading.setBounds(27, 10, 305, 40);
		content1_center.add(orders_heading);
		
		search_by = new JComboBox();
		search_by.setModel(new DefaultComboBoxModel(new String[] {"ID", "Mã KH", "Tên KH", "Điện thoại", "Tổng tiền", "Trạng thái", "Ngày mua", "Cập nhật"}));
		search_by.setFont(new Font("SansSerif", Font.PLAIN, 14));
		search_by.setBorder(null);
		search_by.setBackground(Color.WHITE);
		search_by.setBounds(27, 48, 89, 25);
		content1_center.add(search_by);
		
		search_order = new CustomJTextField("Tìm kiếm đơn hàng");
		search_order.setBounds(122, 47, 237, 27);
		content1_center.add(search_order);
		search_order.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(search_order.getText().equals("Tìm kiếm đơn hàng")) {
					search_order.setText("");
					search_order.requestFocus();
					search_order.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(search_order.getText().length() == 0) {
					search_order.setDefaultStyle();
					search_order.setText("Tìm kiếm đơn hàng");
				}
			}
		});
		
		search_order.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String query = search_order.getText().toLowerCase();
				searchby(query, search_by.getSelectedIndex());
			}
		});
		
		JPanel content2 = new JPanel();
		content2.setBackground(new Color(255, 255, 255));
		add(content2, BorderLayout.CENTER);
		content2.setLayout(new BorderLayout(0, 0));
		
		JPanel content2_west = new JPanel();
		content2_west.setLayout(null);
		content2_west.setBackground(Color.WHITE);
		content2.add(content2_west, BorderLayout.WEST);
		content2_west.setPreferredSize(new Dimension(25, 100));
		
		JPanel content2_south = new JPanel();
		content2_south.setLayout(null);
		content2_south.setBackground(Color.WHITE);
		content2.add(content2_south, BorderLayout.SOUTH);
		content2_south.setPreferredSize(new Dimension(80, 20));
		
		JPanel content2_east = new JPanel();
		content2_east.setLayout(null);
		content2_east.setBackground(Color.WHITE);
		content2.add(content2_east, BorderLayout.EAST);
		content2_east.setPreferredSize(new Dimension(25, 100));
		
		
		//----------------------------------
		//Table
		orders_list = new MultiButtonTable();
		orders_list.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"ID", "Mã KH", "Tên KH", "Điện thoại", "Tổng tiền", "Trạng thái", "Ngày mua", "Cập nhật", "Thao t\u00E1c"}
		));
		
		
		//action column event
		setActionColumnEvent();
		orders_list.setEvent(event);
		orders_list.customTable();
		setOrdersToTable();
		
		//custom column
		customColumnN(0,10);
		customColumnN(1,10);
		customColumnN(3,20);
		customColumnN(4,15);
		customColumnN(5,20);
		customColumnN(6,20);
		customColumnN(7,20);
		
		//create scrollpane
		CustomScrollPane scrollPane = new CustomScrollPane();
		content2.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(orders_list);
		
		//sort table by column
		sort();
		
	}
	
	//custom column by index
		public void customColumnN(int index, int w) {
			DefaultTableCellRenderer center = new DefaultTableCellRenderer();	
			center.setHorizontalAlignment( JLabel.CENTER );
			orders_list.getColumnModel().getColumn(index).setPreferredWidth(w);
			orders_list.getColumnModel().getColumn(index).setCellRenderer(center);
			
		}
		
	
	public void setOrdersToTable() {
	int order_id, user_id;
	String user_name, phone, order_state;
	int total_money;
	Date createdat, updatedat;
	
		try {
			Connection conn = OracleConn.getConnection();
			java.sql.Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery
				("select * from \"User\", \"Order\"where \"User\".user_id = \"Order\".user_id order by order_id");
			
			while(rs.next()) {
				user_id = rs.getInt("user_id");
				order_id = rs.getInt("order_id");
				user_name = rs.getString("full_name");
				phone =rs.getString("phone");
				order_state = rs.getString("order_state");
				total_money = rs.getInt("total_money");
				createdat = rs.getDate("created_at");
				updatedat = rs.getDate("updated_at");
				
				Object[] objects= {order_id, user_id, user_name, phone, total_money, order_state, createdat, updatedat, null};
				model = (DefaultTableModel)orders_list.getModel();
				model.addRow(objects);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//create new order detail panel--------------------------------------
	public void createNewOrderDetail() {
		//orderDetail = new OrderDetail();
		
		
		//create new order detail panel--------------------------------------
	}
	
	//clear table
	public void clearTable() {
		model = (DefaultTableModel) orders_list.getModel();
		model.setRowCount(0);
	}
	//sort by
	public void sort() {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		orders_list.setRowSorter(sorter);
	}
	//search by column in table
		public void searchby(String query, int searchColIndex) {
			TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
			orders_list.setRowSorter(tr);
			
			if (query.length() == 0) {
	            tr.setRowFilter(null);
	        } else {
	            try {
	                tr.setRowFilter(RowFilter.regexFilter("^(?i)" + query, searchColIndex));
	            } catch (PatternSyntaxException pse) {
	                System.out.println("Bad regex pattern");
	            }
	        }
			
		}
		
		// set event to Action column(edit, delete)
		public void setActionColumnEvent() {
			model = (DefaultTableModel) orders_list.getModel();
			event = new TableEvent() {
			
			public void onEdit(int row) {
					int id = (int)model.getValueAt(orders_list.getSelectedRow(), 0);
					//createEditUser(id);
					
			}
			
			public void onDelete(int row) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận xóa đơn hàng", "Xóa", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
					if (orders_list.isEditing()) {
						orders_list.getCellEditor().stopCellEditing();
		                }
					  
					  int id = (int)model.getValueAt(orders_list.getSelectedRow(), 0);
					  String state = (String)model.getValueAt(orders_list.getSelectedRow(), 5);
					  
					  if(checkValidateOrder(state)) {
						  deleteOrderById(id);
						  model.removeRow(orders_list.getSelectedRow());
					  }
				}
			}
		};
		}
		
		//check validate delete order
		public boolean checkValidateOrder(String state) {
			boolean isValid = true;
			
			//neu trang thai don hang dang la cho giao, dang giao thì ko cho xoa
			if(state.equals("Chờ giao")) {
				JOptionPane.showMessageDialog(this, "Không thể xóa đơn hàng khi chờ giao");
				isValid = false;
			}
			if(state.equals("Đang giao")) {
				JOptionPane.showMessageDialog(this, "Không thể xóa đơn hàng khi đang giao");
				isValid = false;
			}
			
			return isValid;
		}
		
		//delete user
		public void deleteOrderById(int id) {
			try {
				Connection conn = OracleConn.getConnection();
				String sql = "delete from \"Order\" where order_id = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				
				int rowcount = pst.executeUpdate();
				if(rowcount > 0) 
					JOptionPane.showMessageDialog(this, "Xóa đơn hàng thành công");
				else 
					JOptionPane.showMessageDialog(this, "Xóa đơn hàng không thành công");
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
