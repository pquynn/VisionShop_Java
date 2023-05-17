package view.admin;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import components.CustomJTextField;
import components.CustomScrollPane.CustomScrollPane;
import components.CustomTable.ActionEditer;
import components.CustomTable.ActionPanel;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.PatternSyntaxException;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Connect.OracleConn;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Products extends JPanel {
	private MultiButtonTable products_list;
	private CustomJTextField search_product;
	private JComboBox search_by;
	
	private String name, category;
	private int id, quantity, price;
	private Date createdat, updatedat;
	private DefaultTableModel model;
	private TableEvent event;
	private AddProduct addProduct;
	private EditProduct editProduct;
	
	public Products() {
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
		
		JButton addButton = new JButton("+Thêm");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewAddProduct();
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
		
		JLabel products_heading = new JLabel("Sản phẩm");
		products_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		products_heading.setBounds(27, 10, 305, 40);
		content1_center.add(products_heading);
		
		search_by = new JComboBox();
		search_by.setModel(new DefaultComboBoxModel(new String[] {"ID", "Tên SP", "Phân loại", "Giá", "SL", "Ngày thêm", "Ngày cập nhật"}));
		search_by.setFont(new Font("SansSerif", Font.PLAIN, 14));
		search_by.setBorder(null);
		search_by.setBackground(Color.WHITE);
		search_by.setBounds(27, 48, 89, 25);
		content1_center.add(search_by);
		
		search_product = new CustomJTextField("Tìm kiếm sản phẩm");
		search_product.setBounds(122, 47, 237, 27);
		content1_center.add(search_product);
		search_product.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(search_product.getText().equals("Tìm kiếm sản phẩm")) {
					search_product.setText("");
					search_product.requestFocus();
					search_product.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(search_product.getText().length() == 0) {
					search_product.setDefaultStyle();
					search_product.setText("Tìm kiếm sản phẩm");
				}
			}
		});
		search_product.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String query = search_product.getText().toLowerCase();
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
		products_list = new MultiButtonTable();
		products_list.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"ID", "Tên SP", "Phân loại", "Giá", "SL", "Ngày thêm", "Cập nhật", "Thao tác"}
		));
		
		//action column event
		setActionColumnEvent();
		products_list.setEvent(event);
		products_list.customTable();
		setProductsToTable();
		
		//custom column
		customColumnN(0,5);
		customColumnN(4,20);
		customColumnN(5,10);
		customColumnN(6,20);
		
		//create scrollpane
		CustomScrollPane scrollPane = new CustomScrollPane();
		content2.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(products_list);
		
		//sort table by column
		sort();
	}
		
	//custom column by index
	public void customColumnN(int index, int w) {
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();	
		center.setHorizontalAlignment( JLabel.CENTER );
		products_list.getColumnModel().getColumn(index).setPreferredWidth(w);
		products_list.getColumnModel().getColumn(index).setCellRenderer(center);
		
	}
	
	//set products to jtable
	public void setProductsToTable() {
		try {
			Connection conn = OracleConn.getConnection();
			java.sql.Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery
				("select * from Glasses, \"Category\" where Glasses.category_id=\"Category\".category_id order by glasses_id"); 
			
			while(rs.next()) {
				id = rs.getInt("glasses_id");
				name = rs.getString("glasses_name");
				quantity =rs.getInt("quantity");
				category = rs.getString("category_name");
				price = rs.getInt("price"); 
				createdat = rs.getDate("created_at");
				updatedat = rs.getDate("updated_at");
				
				Object[] objects= {id, name, category, price, quantity, createdat, updatedat, null}; 
				model = (DefaultTableModel)products_list.getModel();
				model.addRow(objects);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// set event to Action column(edit, delete)
	public void setActionColumnEvent() {
		model = (DefaultTableModel) products_list.getModel();
		event = new TableEvent() {
		
		public void onEdit(int row) {
				int id = (int)model.getValueAt(products_list.getSelectedRow(), 0);
				createEditProduct(id);
		}
		
		public void onDelete(int row) {
			int ret = JOptionPane.showConfirmDialog(null, "Xác nhận xóa sản phẩm", "Xóa", JOptionPane.YES_NO_OPTION);
			if (ret == JOptionPane.YES_OPTION){
				if (products_list.isEditing()) {
					products_list.getCellEditor().stopCellEditing();
	                }
				  
				  int id = (int)model.getValueAt(products_list.getSelectedRow(), 0);
				  deleteGlassesById(id);  /////////database bi loi khi xoa glasses r
				  model.removeRow(products_list.getSelectedRow());
			}
		}
	};
	}
	
	//delete user
	public void deleteGlassesById(int id) {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "delete from Glasses where glasses_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			int rowcount = pst.executeUpdate();
			if(rowcount > 0) 
				JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
			else 
				JOptionPane.showMessageDialog(this, "Xóa sản phẩm không thành công");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//clear table
	public void clearTable() {
		model = (DefaultTableModel) products_list.getModel();
		model.setRowCount(0);
	}
	
	//create new panel add product
	public void createNewAddProduct() {
		addProduct = new AddProduct();
		addProduct.setProductPane(this);
		addProduct.setVisible(true);
	}
	
	//create new panel edit product with id
	public void createEditProduct(int id) {
		editProduct = new EditProduct();
		editProduct.setUsersPanel(this);
		editProduct.setId(id);
		editProduct.setProductDetailById();
		editProduct.setVisible(true);
	}
	
	
	//sort table
	public void sort() {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		products_list.setRowSorter(sorter);
	}
	
	
	//search by column in table
	public void searchby(String query, int searchColIndex) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		products_list.setRowSorter(tr);
		
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
