package view.admin;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import components.CustomJTextField;
import components.CustomScrollPane.CustomScrollPane;
import components.CustomTable.MultiButtonTable;
import components.CustomTable.TableEvent;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.PatternSyntaxException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableCellRenderer;

import Connect.OracleConn;

import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Users extends JPanel {

	private MultiButtonTable users_list;
	private CustomJTextField search_user;
	private JComboBox search_by;
	private Object[][] data;
	
	private String name, email, role, phone;
	private int id;
	private Date createdat, updatedat;
	private DefaultTableModel model;
	private TableEvent event;
	private AddUser adduser;
	private EditUser edituser;
	private int user_id;
	

	public Users() {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 600);
		setLayout(new BorderLayout(0, 0));
		
		//--content1
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
		
		JButton addButton = new JButton("+Thêm");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewAddUser();
			}
		});
		addButton.setBounds(80, 48, 85, 23);
		content1_east.add(addButton);
		addButton.setForeground(Color.WHITE);
		addButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		addButton.setBackground(Color.BLACK);
		
		JPanel content1_center = new JPanel();
		content1_center.setBackground(new Color(255, 255, 255));
		content1.add(content1_center, BorderLayout.CENTER);
		content1_center.setLayout(null);
		
		JLabel users_heading = new JLabel("Người dùng");
		users_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		users_heading.setBounds(27, 10, 305, 40);
		content1_center.add(users_heading);
		
		search_by = new JComboBox();
		search_by.setModel(new DefaultComboBoxModel(new String[] {"ID", "Họ tên", "Email", "Vai trò", "Tham gia", "Cập nhật"}));
		search_by.setFont(new Font("SansSerif", Font.PLAIN, 14));
		search_by.setBorder(null);
		search_by.setBackground(Color.WHITE);
		search_by.setBounds(27, 48, 89, 25);
		content1_center.add(search_by);
		
		search_user = new CustomJTextField("Tìm kiếm người dùng");
		search_user.setBounds(122, 47, 237, 27);
		content1_center.add(search_user);
		search_user.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(search_user.getText().equals("Tìm kiếm người dùng")) {
					search_user.setText("");
					search_user.requestFocus();
					search_user.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(search_user.getText().length() == 0) {
					search_user.setDefaultStyle();
					search_user.setText("Tìm kiếm người dùng");
				}
			}
		});
		search_user.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String query = search_user.getText().toLowerCase();
				searchby(query, search_by.getSelectedIndex());
			}
		});
		
		
		//--content2
		JPanel content2 = new JPanel();
		content2.setBackground(new Color(255, 255, 255));
		add(content2, BorderLayout.CENTER);
		content2.setLayout(new BorderLayout(0, 0));
		
		JPanel content2_west = new JPanel();
		content2_west.setLayout(null);
		content2_west.setBackground(Color.WHITE);
		content2.add(content2_west, BorderLayout.WEST);
		content2_west.setPreferredSize(new Dimension(30, 100));
		
		JPanel content2_south = new JPanel();
		content2_south.setLayout(null);
		content2_south.setBackground(Color.WHITE);
		content2.add(content2_south, BorderLayout.SOUTH);
		content2_south.setPreferredSize(new Dimension(80, 25));
		
		JPanel content2_east = new JPanel();
		content2_east.setLayout(null);
		content2_east.setBackground(Color.WHITE);
		content2.add(content2_east, BorderLayout.EAST);
		content2_east.setPreferredSize(new Dimension(30, 100));
		
		//----------------------------------
		//Table
		
		users_list = new MultiButtonTable();
		users_list.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"ID", "Họ tên", "Email", "Vai trò", "Tham gia", "Cập nhật", "Thao tác"}
		));
		
		//action column event
		setActionColumnEvent();
		users_list.setEvent(event);
		users_list.customTable();
		setUsersToTable();
		
		//custom column
		customColumnN(0,10);
		customColumnN(4,20);
		customColumnN(5,20);
		
		//create scrollpane
		CustomScrollPane scrollPane = new CustomScrollPane();
		content2.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(users_list);
		
		//sort table by column
		sort();
	}
	
	//custom column by index
	public void customColumnN(int index, int w) {
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();	
		center.setHorizontalAlignment( JLabel.CENTER );
		users_list.getColumnModel().getColumn(index).setPreferredWidth(w);
		users_list.getColumnModel().getColumn(index).setCellRenderer(center);
		
	}
	
	//set User to jtable
	public void setUsersToTable() {
		try {
			Connection conn = OracleConn.getConnection();
			java.sql.Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery
				("select user_id, full_name, email, role_name, created_at, updated_at from \"User\", \"Role\"where \"User\".role_id = \"Role\".role_id order by user_id");
			
			while(rs.next()) {
				id = rs.getInt("user_id");
				name = rs.getString("full_name");
				email =rs.getString("email");
				role = rs.getString("role_name");
				createdat = rs.getDate("created_at");
				updatedat = rs.getDate("updated_at");
				
				Object[] objects= {id, name, email, role, createdat, updatedat, null};
				model = (DefaultTableModel)users_list.getModel();
				model.addRow(objects);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// set event to Action column(edit, delete)
	public void setActionColumnEvent() {
		model = (DefaultTableModel) users_list.getModel();
		event = new TableEvent() {
		
		public void onEdit(int row) {
				int id = (int)model.getValueAt(users_list.getSelectedRow(), 0);
				createEditUser(id);
				
		}
		
		public void onDelete(int row) {
			int ret = JOptionPane.showConfirmDialog(null, "Xác nhận xóa người dùng", "Xóa", JOptionPane.YES_NO_OPTION);
			if (ret == JOptionPane.YES_OPTION){
				if (users_list.isEditing()) {
					  users_list.getCellEditor().stopCellEditing();
	                }
				  
				  int id = (int)model.getValueAt(users_list.getSelectedRow(), 0);
				  deleteUserById(id);
				  model.removeRow(users_list.getSelectedRow());
			}
		}
	};
	}
	
	//delete user
	public void deleteUserById(int id) {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "delete from \"User\" where user_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			int rowcount = pst.executeUpdate();
			if(rowcount > 0) 
				JOptionPane.showMessageDialog(this, "Xóa người dùng thành công");
			else 
				JOptionPane.showMessageDialog(this, "Xóa người dùng không thành công");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//clear table
	public void clearTable() {
		model = (DefaultTableModel) users_list.getModel();
		model.setRowCount(0);
	}
	
	//create new panel adduser
	public void createNewAddUser() {
		adduser = new AddUser();
		adduser.setUsersPanel(this);
		adduser.setVisible(true);
	}
	
	//create new panel edituser with id
	public void createEditUser(int id) {
		edituser = new EditUser();
		edituser.setUsersPanel(this);
		edituser.setId(id);
		edituser.setUserDetailByID();
		edituser.setVisible(true);
	}
	
	
	//sort table
	public void sort() {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		users_list.setRowSorter(sorter);
	}
	
	
	//search by column in table
	public void searchby(String query, int searchColIndex) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		users_list.setRowSorter(tr);
		
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
	
	public void setId(int id) {
		user_id = id;
	}
	
}