package view.user;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Connect.OracleConn;
import components.CustomJTextField;
import components.CustomScrollPane.CustomScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.PatternSyntaxException;

import javax.swing.SwingConstants;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MyOrder extends JPanel{
	private JTable orders_list;
	private CustomJTextField search_order;
	private JComboBox table_col;
	private DefaultTableModel model;
	private int user_id = -1;
	public MyOrder instanceMyOrder;
	
	public MyOrder(int user_id) {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 650);
		setLayout(new BorderLayout(0, 0));
		
		this.user_id = user_id;
		instanceMyOrder = this;
		
		JPanel content1 = new JPanel();
		content1.setBackground(new Color(255, 255, 255));
		add(content1, BorderLayout.NORTH);
		content1.setLayout(null);
		content1.setPreferredSize(new Dimension(100, 140));
		
		JLabel myorder_heading = new JLabel("Đơn hàng của tôi");
		myorder_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		myorder_heading.setBounds(28, 36, 305, 40);
		content1.add(myorder_heading);
		
		search_order = new CustomJTextField();
		search_order.setText("Tìm kiếm đơn hàng");
		search_order.setHorizontalAlignment(SwingConstants.LEFT);
		search_order.setForeground(Color.LIGHT_GRAY);
		search_order.setFont(new Font("SansSerif", Font.PLAIN, 14));
		search_order.setColumns(10);
		search_order.setCaretPosition(0);
		search_order.setBorder(new LineBorder(new Color(211, 211, 211)));
		search_order.setBounds(27, 82, 237, 27);
		content1.add(search_order);
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
				searchby(query, table_col.getSelectedIndex());
			}
		});
		
		
		table_col = new JComboBox();
		table_col.setModel(new DefaultComboBoxModel(new String[] {"ID", "Mã KH", "Tên KH", "Điện Thoại", "Tổng SP", "Tổng tiền", "Trạng thái", "Ngày mua", "Ngày cập nhật"}));
		table_col.setFont(new Font("SansSerif", Font.PLAIN, 14));
		table_col.setBorder(null);
		table_col.setBackground(Color.WHITE);
		table_col.setBounds(274, 83, 122, 25);
		content1.add(table_col);
		
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
		
		CustomScrollPane scrollPane = new CustomScrollPane();
		content2.add(scrollPane, BorderLayout.CENTER);
		
		//----------------------------------
		//Table
		orders_list = new JTable();
		orders_list.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"ID", "Mã KH", "Tên KH", "Điện thoại", "Tổng SP", "Tổng tiền", "Trạng thái", "Ngày mua", "Cập nhật"}
		));
		orders_list.setRowSelectionAllowed(false);
		orders_list.setShowVerticalLines(false);
		orders_list.setBorder(null);
		orders_list.setForeground(new Color(0, 0, 0));
		orders_list.setRowHeight(50);
		orders_list.setGridColor(new Color(211, 211, 211));
		
		orders_list.getTableHeader().setBackground(Color.WHITE);
		orders_list.getTableHeader().setForeground(Color.black);
		orders_list.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		orders_list.getTableHeader().setPreferredSize(new Dimension(100, 30));
		setOrdersToTable();
		orders_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = orders_list.getSelectedRow();
				int order_id = (int) model.getValueAt(index, 0);
				new OrderDetail(order_id).setVisible(true);
			}
		});
		customColumnN(0, 10);
		customColumnN(1, 10);
		customColumnN(3, 20);
		customColumnN(4, 10);
		customColumnN(5, 10);
		customColumnN(6, 25);
		customColumnN(7, 15);
		customColumnN(8, 15);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();	
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		scrollPane.setViewportView(orders_list);
	}
			
	//custom column by index
	public void customColumnN(int index, int w) {
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();	
		center.setHorizontalAlignment( JLabel.CENTER );
		orders_list.getColumnModel().getColumn(index).setPreferredWidth(w);
		orders_list.getColumnModel().getColumn(index).setCellRenderer(center);
	}
		
	//set orders to table
	public void setOrdersToTable() {
		 int order_id;
		 String user_name, phone, order_state;
		 int total_money, total_glasses;
		 Date createdat, updatedat;
		
		
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select * from \"Order\" where user_id = ? and \"order_state\" <> 'Chưa xác nhận' order by order_id";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			ResultSet rs = pst.executeQuery();
				
			while(rs.next()) {
				user_id = rs.getInt("user_id");
				order_id = rs.getInt("order_id");
				user_name = rs.getString("full_name");
				phone =rs.getString("phone");
				order_state = rs.getString("order_state");
				total_money = rs.getInt("total_money");
				total_glasses = rs.getInt("total_glasses");
				createdat = rs.getDate("created_at");
				updatedat = rs.getDate("updated_at");
				
				Object[] objects= {order_id, user_id, user_name, phone, total_glasses, total_money, order_state, createdat, updatedat};
				model = (DefaultTableModel)orders_list.getModel();
				model.addRow(objects);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//reset table
	public void resetTable() {
		model = (DefaultTableModel) orders_list.getModel();
		model.setRowCount(0);
		setOrdersToTable();
		//sort table by column
		sort();
	}
	
	//sort by
	public void sort() {
		model = (DefaultTableModel) orders_list.getModel();
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
}
