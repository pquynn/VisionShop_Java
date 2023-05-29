package view.admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import components.CustomJTextField;
import components.CustomScrollPane.CustomScrollPane;
import components.CustomTable.ActionEditer;
import components.CustomTable.ActionPanel;
import components.CustomTable.ActionRenderer;
import components.CustomTable.MultiButtonTable;
import components.CustomTable.TableEvent;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Connect.OracleConn;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProductCategory extends JPanel {
	private CustomJTextField add_category;
	private MultiButtonTable categories_list;

	private JButton edit;
	private JButton delete;
	private JPanel actionPane;
	private JLabel name_error;
	private DefaultTableModel model;
	private TableEvent event;
	private EditProductCategory editcategory;
	
	private int id;
	private String name;
	private Date created, updated;
	private int user_id;
	public ProductCategory instanceCategory;
	
	public ProductCategory(int user_id) {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 600);
		setLayout(new BorderLayout(0, 0));
		
		this.user_id = user_id;
		instanceCategory = this;
		
		JPanel content1 = new JPanel();
		content1.setBackground(new Color(255, 255, 255));
		add(content1, BorderLayout.NORTH);
		content1.setLayout(null);
		content1.setPreferredSize(new Dimension(100, 120));
		
		JLabel category_heading = new JLabel("Loại kính");
		category_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		category_heading.setBounds(27, 10, 305, 40);
		content1.add(category_heading);
		
		add_category = new CustomJTextField("Nhập tên loại sản phẩm");
		add_category.setBounds(27, 54, 270, 23);
		content1.add(add_category);
		add_category.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(add_category.getText().equals("Nhập tên loại sản phẩm")) {
					add_category.setText("");
					add_category.requestFocus();
					add_category.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(add_category.getText().length() == 0) {
					add_category.setDefaultStyle();
					add_category.setText("Nhập tên loại sản phẩm");
				}
			}
		});
		
		add_category.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(checkDuplicate()) {
					name_error.setText("Tên loại kính đã tồn tại");
				}
			}
			
			public void keyPressed(KeyEvent e) {
				name_error.setText("");
			}
		});
		
		
		JButton addButtion = new JButton("+Thêm");
		addButtion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name_error.setText("");
				
				if(!checkDuplicate() && !add_category.getText().equals("Nhập tên loại sản phẩm")) {
					insertCategory();
				}
			}
		});
		addButtion.setForeground(Color.WHITE);
		addButtion.setFont(new Font("SansSerif", Font.BOLD, 14));
		addButtion.setBackground(Color.BLACK);
		addButtion.setBounds(300, 54, 85, 23);
		content1.add(addButtion);
		
		name_error = new JLabel();
		name_error.setForeground(Color.RED);
		name_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		name_error.setBounds(27, 77, 245, 21);
		content1.add(name_error);
		
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
		
		categories_list = new MultiButtonTable();
		categories_list.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"ID", "Tên loại kính", "Ngày tạo", "Ngày cập nhật", "Thao tác"}
		));
		
		//action column event
		setActionColumnEvent();
		categories_list.setEvent(event);
		categories_list.customTable();
		setCategoryToTable();
		
		//custom column
		customColumnN(0,20);
		customColumnN(2,20);
		customColumnN(3,20);
		
		//create scrollpane
		CustomScrollPane scrollPane = new CustomScrollPane();
		content2.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(categories_list);
		
		//sort table by column
		sort();
	}
	
	//custom column by index
	public void customColumnN(int index, int w) {
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();	
		center.setHorizontalAlignment( JLabel.CENTER );
		categories_list.getColumnModel().getColumn(index).setPreferredWidth(w);
		categories_list.getColumnModel().getColumn(index).setCellRenderer(center);
		
	}
	
	//set User to jtable
	public void setCategoryToTable() {
		try {
			Connection conn = OracleConn.getConnection();
			java.sql.Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery
				("select * from \"Category\" order by category_id");
			
			while(rs.next()) {
				id = rs.getInt("category_id");
				name = rs.getString("category_name");
				created = rs.getDate("created_at");
				updated = rs.getDate("updated_at");
				
				Object[] objects= {id, name, created, updated, null};
				model = (DefaultTableModel)categories_list.getModel();
				model.addRow(objects);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// set event to Action column(edit, delete)
	public void setActionColumnEvent() {
		model = (DefaultTableModel) categories_list.getModel();
		event = new TableEvent() {
		
		public void onEdit(int row) {
				int id = (int)model.getValueAt(categories_list.getSelectedRow(), 0);
				createEditCategory(id);
		}
		
		public void onDelete(int row) {
			int ret = JOptionPane.showConfirmDialog(null, "Xác nhận xóa loại kính", "Xóa", JOptionPane.YES_NO_OPTION);
			if (ret == JOptionPane.YES_OPTION){
				if (categories_list.isEditing()) {
					  categories_list.getCellEditor().stopCellEditing();
	                }
				  
				  int id = (int)model.getValueAt(categories_list.getSelectedRow(), 0);
				  deleteCategoryById(id);
				  model.removeRow(categories_list.getSelectedRow());
			}
		}
	};
	}
	
	//clear table
	public void clearTable() {
		model = (DefaultTableModel) categories_list.getModel();
		model.setRowCount(0);
	}
	
	//reset table
	public void resetTable() {
		clearTable();
		setCategoryToTable();
	}
	
	//delete category
	public void deleteCategoryById(int id) {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "delete from \"Category\" where category_id =?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			int rowcount = pst.executeUpdate();
			if(rowcount > 0) 
				JOptionPane.showMessageDialog(this, "Xóa loại kính thành công");
			else 
				JOptionPane.showMessageDialog(this, "Xóa loại kính không thành công");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//insert category
	public void insertCategory() {
		name = add_category.getText();
		
		try {
			Connection con = OracleConn.getConnection();
			String sql = 
				"insert into \"Category\"(category_name) values (?)";
			PreparedStatement prs = con.prepareStatement(sql);
			
			prs.setString(1, name);
			
			int RowCount = prs.executeUpdate();
			if(RowCount > 0) {
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				clearTable();
				setCategoryToTable();
			}
			else {
				JOptionPane.showMessageDialog(null, "Thêm không thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//check duplicate category_name
	public boolean checkDuplicate() {
		name = add_category.getText();
		boolean isExist = false;
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select * from \"Category\" where category_name = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
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
	
	
	//create new panel edituser with id
	public void createEditCategory(int id) {
		editcategory = new EditProductCategory();
		editcategory.setCategoryPane(this);
		editcategory.setId(id);
		editcategory.setCategoryByID();
		editcategory.setVisible(true);
	}
	
	
	//sort table
	public void sort() {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		categories_list.setRowSorter(sorter);
	}
}
