package view.user;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import components.CustomScrollPane.CustomScrollPane;
import components.ProductPanels.ProductThumbnail;

import java.awt.BorderLayout;

import java.awt.GridLayout;

import java.awt.Font;
import javax.swing.SwingConstants;
import Connect.OracleConn;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Home extends JPanel {
	private JPanel content_center;
	private ArrayList<Object[]> products_list;
	private CustomScrollPane scrollPane;
	private JComboBox category_list;
	
	private int user_id;

	public Home instanceHome;
	
	public Home(int user_id) {
		this.user_id = user_id;
		instanceHome = this;
		
		setBackground(new Color(255, 255, 255));
		setSize(1000, 650);
		setLayout(new BorderLayout(0, 0));
		
		JPanel content_south = new JPanel();
		content_south.setBackground(new Color(255, 255, 255));
		add(content_south, BorderLayout.SOUTH);
		content_south.setPreferredSize(new Dimension(100, 20));
		
		JPanel content_west = new JPanel();
		content_west.setBackground(new Color(255, 255, 255));
		add(content_west, BorderLayout.WEST);
		content_west.setPreferredSize(new Dimension(34, 100));
		
		JPanel content_east = new JPanel();
		content_east.setBackground(new Color(255, 255, 255));
		add(content_east, BorderLayout.EAST);
		content_east.setPreferredSize(new Dimension(34, 100));
		
		JPanel content_north = new JPanel();
		content_north.setBackground(new Color(255, 255, 255));
		add(content_north, BorderLayout.NORTH);
		content_north.setPreferredSize(new Dimension(100, 50));
		content_north.setLayout(null);
		
		JLabel filter = new JLabel("Lọc theo:");
		filter.setHorizontalAlignment(SwingConstants.RIGHT);
		filter.setForeground(Color.GRAY);
		filter.setFont(new Font("SansSerif", Font.PLAIN, 14));
		filter.setBounds(34, 10, 71, 32);
		content_north.add(filter);
		
		category_list = new JComboBox();
		category_list.setForeground(new Color(169, 169, 169));
		category_list.setFont(new Font("SansSerif", Font.PLAIN, 14));
		category_list.setBorder(null);
		category_list.setBackground(Color.WHITE);
		category_list.setBounds(115, 14, 140, 25);
		content_north.add(category_list);
		setCategoryItem();
		category_list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setProductList(getProductsListByCategory());
			}
		});
		
		scrollPane = new CustomScrollPane();
		scrollPane.setViewportBorder(null);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);
		
		//display product list
		setProductList(getProductsList());
	
	}
	
	public JPanel createEmptyJPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		return panel;
	}
	
	//get product list from db
	public ArrayList<Object[]> getProductsList(){
		ArrayList<Object[]> products_list = new ArrayList<Object[]>();
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select glasses_id, glasses_name, price, image from Glasses order by glasses_id";
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			
			while(rs.next()) {
				Object[] row = new Object[4];
				row[0] = rs.getInt(1);
				row[1] = rs.getString(2);
				row[2] = rs.getInt(3);
				row[3] = rs.getBytes(4);
				products_list.add(row);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return products_list;
	}
	
	// set products list
	public void setProductList(ArrayList<Object[]> products_list) {
		//create panel to hold products_list
		content_center = new JPanel();
		content_center.setBorder(null);
		content_center.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(content_center);
		content_center.setLayout(new GridLayout(0, 4, 20, 20));
		
		if(products_list.isEmpty() == false) {
			for(Object[] i : products_list) {
				int id = (int) i[0];
				String name = String.valueOf(i[1]);
				String price = String.valueOf(i[2]);
				byte[] imagedata = (byte[]) i[3];
				
				ProductThumbnail p = new ProductThumbnail(id, name, price, imagedata);
				content_center.add(p);
				p.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						new ProductDetail(id, user_id).setVisible(true);
					}
				});
			}
			for(int i = 0; i < 12 - products_list.size(); i++)
				content_center.add(createEmptyJPanel());
				
		}
	}
	
	  
    //set category name to combobox category
    public void setCategoryItem() {
    	category_list.addItem("Tất cả");
    	try {
    		Connection con = OracleConn.getConnection();
			String sql = "select * from \"Category\" order by category_id";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				category_list.addItem(rs.getString("category_name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    //get category_id by category_name
    public int findIDByCategoryName(String cn) {
    	int id = 0;
    	try {
    		Connection con = OracleConn.getConnection();
			String sql = "select category_id from \"Category\" where category_name=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, cn);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("category_id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return id;
    }
	
	//get product list from db by glasses_category
	public ArrayList<Object[]> getProductsListByCategory(){
		String category_name = category_list.getSelectedItem().toString();
		if(category_name.equals("Tất cả")) {
			return getProductsList();
		}
		else {
			int category_id = findIDByCategoryName(category_name);
			ArrayList<Object[]> products_list = new ArrayList<Object[]>();
			
			try {
				Connection con = OracleConn.getConnection();
				String sql = "select glasses_id, glasses_name, price, image from Glasses where category_id = ? order by glasses_id";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, category_id);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					Object[] row = new Object[4];
					row[0] = rs.getInt(1);
					row[1] = rs.getString(2);
					row[2] = rs.getInt(3);
					row[3] = rs.getBytes(4);
					products_list.add(row);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return products_list;
		}
	}
}
