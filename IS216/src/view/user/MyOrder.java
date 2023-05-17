package view.user;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import components.CustomJTextField;
import components.CustomScrollPane.CustomScrollPane;
import components.CustomTable.MultiButtonTable;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

public class MyOrder extends JPanel{
	private JTable orders_list;
	private CustomJTextField search_order;
	private int user_id;
	
	public MyOrder(int user_id) {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 650);
		setLayout(new BorderLayout(0, 0));
		this.user_id = user_id;
		
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
		search_order.setBounds(127, 82, 237, 27);
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
		
		JLabel sorbyLabel = new JLabel("Sắp xếp theo:");
		sorbyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sorbyLabel.setForeground(Color.GRAY);
		sorbyLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sorbyLabel.setBounds(391, 82, 96, 27);
		content1.add(sorbyLabel);
		
		JComboBox sort_by = new JComboBox();
		sort_by.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sort_by.setBorder(null);
		sort_by.setBackground(Color.WHITE);
		sort_by.setBounds(485, 83, 134, 25);
		content1.add(sort_by);
		
		JComboBox search_by = new JComboBox();
		search_by.setFont(new Font("SansSerif", Font.PLAIN, 14));
		search_by.setBorder(null);
		search_by.setBackground(Color.WHITE);
		search_by.setBounds(28, 84, 89, 25);
		content1.add(search_by);
		
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
		
		
		
		String[] header = {	"ID", "Mã khách hàng", "Ngày mua", "Điện thoại", "Tổng tiền", "Trạng thái", "Thao t\u00E1c"};
	
	
	}
	
	public void setId(int id) {
		user_id = id;
	}
}
