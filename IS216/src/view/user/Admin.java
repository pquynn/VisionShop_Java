package view.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Connect.OracleConn;
import view.admin.Orders;
import view.admin.ProductCategory;
import view.admin.Products;
import view.admin.Statistics;
import view.admin.Users;
import view.seller.Customers;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin extends JPanel {
	private JPanel subBar;
	
	private JButton statistic;
	private JButton glasses_categories;
	private JButton glasses;
	private JButton orders;
	private JButton users;
	private JButton customers;
	
	private Statistics statisticsPanel;
	private Products productsPanel;
	private ProductCategory categoryPanel;
	private Users usersPanel;
	private Orders ordersPanel;
	private Customers customersPanel;
	
	private int user_id;
	public Admin instanceAdmin;
	
	public Admin(int user_id) {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 650);
		setLayout(new BorderLayout(0, 0));
		
		this.user_id = user_id;
		instanceAdmin = this;
		
		//----------statistics
		statisticsPanel = new Statistics().instanceStatistics;
		add(statisticsPanel, BorderLayout.CENTER);
		
		//----------products, category, user, order panel
		productsPanel = new Products().instanceProducts;
		categoryPanel = new ProductCategory(user_id).instanceCategory;
		usersPanel = new Users(user_id).instanceUsers;
		ordersPanel = new Orders().instanceOrders;
		customersPanel = new Customers().instanceCustomer;
		
		//----------subnavbar
		subBar = new JPanel();
		subBar.setBackground(new Color(255, 255, 255));
		add(subBar, BorderLayout.NORTH);
		subBar.setPreferredSize(new Dimension(100, 40));
		subBar.setLayout(new GridLayout(1, 0, 5, 5));
		
		JPanel grid_head = new JPanel();
		subBar.add(grid_head);
		grid_head.setBackground(Color.WHITE);
		
		//--statistic 
		statistic = new JButton("Thống kê");
		statistic.setForeground(Color.DARK_GRAY);
		statistic.setBounds(0, 0, 93, 40);
		subBar.add(statistic);
		statistic.setFont(new Font("SansSerif", Font.PLAIN, 16));
		statistic.setHorizontalAlignment(SwingConstants.CENTER);
		statistic.setBackground(Color.white);
		statistic.setBorder(null);
		statistic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				statistic.setForeground(Color.DARK_GRAY);
				glasses_categories.setForeground(new Color(169, 169, 169));
				glasses.setForeground(new Color(169, 169, 169));
				users.setForeground(new Color(169, 169, 169));
				orders.setForeground(new Color(169, 169, 169));
				
				statisticsPanel.setVisible(true);
				productsPanel.setVisible(false);
				categoryPanel.setVisible(false);
				usersPanel.setVisible(false);
				ordersPanel.setVisible(false);
				
				customers.setForeground(new Color(169, 169, 169));
				customersPanel.setVisible(false);
				
				statisticsPanel.displayDetail();
			}
		});
		
		statistic.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(statistic.getForeground() != Color.DARK_GRAY) {
				statistic.setForeground(new Color(105, 105,105));
				}
			}
			public void mouseExited(MouseEvent e) {
				if(statistic.getForeground() != Color.DARK_GRAY) {
					statistic.setForeground(new Color(169, 169, 169));
				}
			}
		});
		

		//--orders 
		orders = new JButton(" Đơn hàng");
		orders.setBounds(0, 0, 93, 40);
		subBar.add(orders);
		orders.setHorizontalAlignment(SwingConstants.CENTER);
		orders.setForeground(new Color(169, 169, 169));
		orders.setFont(new Font("SansSerif", Font.PLAIN, 16));
		orders.setBackground(Color.white);
		orders.setBorder(null);
		orders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				orders.setForeground(Color.DARK_GRAY);
				glasses.setForeground(new Color(169, 169, 169));
				statistic.setForeground(new Color(169, 169, 169));
				glasses_categories.setForeground(new Color(169, 169, 169));
				users.setForeground(new Color(169, 169, 169));
				
				add(ordersPanel, BorderLayout.CENTER);
				ordersPanel.setVisible(true);
				statisticsPanel.setVisible(false);
				productsPanel.setVisible(false);
				categoryPanel.setVisible(false);
				usersPanel.setVisible(false);
				
				customers.setForeground(new Color(169, 169, 169));
				customersPanel.setVisible(false);
				
				ordersPanel.resetTable();
			}
		});
		
		orders.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(orders.getForeground() != Color.DARK_GRAY) {
					orders.setForeground(new Color(105, 105,105));
				}
			}
			public void mouseExited(MouseEvent e) {
				if(orders.getForeground() != Color.DARK_GRAY) {
					orders.setForeground(new Color(169, 169, 169));
				}
			}
		});
			
		
	
		//--glasses_categories 
		glasses_categories = new JButton("Loại kính");
		glasses_categories.setBounds(0, 0, 93, 40);
		subBar.add(glasses_categories);
		glasses_categories.setHorizontalAlignment(SwingConstants.CENTER);
		glasses_categories.setForeground(new Color(169, 169, 169));
		glasses_categories.setFont(new Font("SansSerif", Font.PLAIN, 16));
		glasses_categories.setBackground(Color.white);
		glasses_categories.setBorder(null);
		glasses_categories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				glasses_categories.setForeground(Color.DARK_GRAY);
				glasses.setForeground(new Color(169, 169, 169));
				statistic.setForeground(new Color(169, 169, 169));
				users.setForeground(new Color(169, 169, 169));
				orders.setForeground(new Color(169, 169, 169));
				
				add(categoryPanel, BorderLayout.CENTER);
				categoryPanel.setVisible(true);
				statisticsPanel.setVisible(false);
				productsPanel.setVisible(false);
				usersPanel.setVisible(false);
				ordersPanel.setVisible(false);
				
				customers.setForeground(new Color(169, 169, 169));
				customersPanel.setVisible(false);
				
				categoryPanel.resetTable();
			}
		});
		
		glasses_categories.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(glasses_categories.getForeground() != Color.DARK_GRAY) {
					glasses_categories.setForeground(new Color(105, 105,105));
				}
			}
			public void mouseExited(MouseEvent e) {
				if(glasses_categories.getForeground() != Color.DARK_GRAY) {
					glasses_categories.setForeground(new Color(169, 169, 169));
				}
			}
		});
		

		//--glasses 
		glasses = new JButton("Kính mắt");
		glasses.setBounds(0, 0, 93, 40);
		subBar.add(glasses);
		glasses.setHorizontalAlignment(SwingConstants.CENTER);
		glasses.setForeground(new Color(169, 169, 169));
		glasses.setFont(new Font("SansSerif", Font.PLAIN, 16));
		glasses.setBackground(Color.white);
		glasses.setBorder(null);
		glasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				glasses.setForeground(Color.DARK_GRAY);
				glasses_categories.setForeground(new Color(169, 169, 169));
				statistic.setForeground(new Color(169, 169, 169));
				users.setForeground(new Color(169, 169, 169));
				orders.setForeground(new Color(169, 169, 169));
				
				add(productsPanel, BorderLayout.CENTER);
				productsPanel.setVisible(true);
				statisticsPanel.setVisible(false);
				categoryPanel.setVisible(false);
				usersPanel.setVisible(false);
				ordersPanel.setVisible(false);
				
				customers.setForeground(new Color(169, 169, 169));
				customersPanel.setVisible(false);
				
				productsPanel.resetTable();
			}
		});
		
		glasses.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(glasses.getForeground() != Color.DARK_GRAY) {
					glasses.setForeground(new Color(105, 105,105));
				}
			}
			public void mouseExited(MouseEvent e) {
				if(glasses.getForeground() != Color.DARK_GRAY) {
					glasses.setForeground(new Color(169, 169, 169));
				}
			}
		});
		
		//--users 
		users = new JButton("Người dùng");
		users.setBounds(0, 0, 93, 40);
		subBar.add(users);
		users.setHorizontalAlignment(SwingConstants.CENTER);
		users.setForeground(new Color(169, 169, 169));
		users.setBackground(Color.white);
		users.setFont(new Font("SansSerif", Font.PLAIN, 16));
		users.setBorder(null);
		users.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				users.setForeground(Color.DARK_GRAY);
				glasses.setForeground(new Color(169, 169, 169));
				statistic.setForeground(new Color(169, 169, 169));
				glasses_categories.setForeground(new Color(169, 169, 169));
				orders.setForeground(new Color(169, 169, 169));
				
				add(usersPanel, BorderLayout.CENTER);
				usersPanel.setVisible(true);
				statisticsPanel.setVisible(false);
				productsPanel.setVisible(false);
				categoryPanel.setVisible(false);
				ordersPanel.setVisible(false);
				
				customers.setForeground(new Color(169, 169, 169));
				customersPanel.setVisible(false);
				
				usersPanel.resetTable();
			}
		});
		
		users.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(users.getForeground() != Color.DARK_GRAY) {
					users.setForeground(new Color(105, 105,105));
				}
			}
			public void mouseExited(MouseEvent e) {
				if(users.getForeground() != Color.DARK_GRAY) {
					users.setForeground(new Color(169, 169, 169));
				}
			}
		});
		
		//--customer
		customers = new JButton("Khách hàng");
		customers.setBounds(0, 0, 93, 40);
		subBar.add(customers);
		customers.setHorizontalAlignment(SwingConstants.CENTER);
		customers.setForeground(new Color(169, 169, 169));
		customers.setBackground(Color.white);
		customers.setFont(new Font("SansSerif", Font.PLAIN, 16));
		customers.setBorder(null);
		customers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				customers.setForeground(Color.DARK_GRAY);
				glasses.setForeground(new Color(169, 169, 169));
				statistic.setForeground(new Color(169, 169, 169));
				glasses_categories.setForeground(new Color(169, 169, 169));
				orders.setForeground(new Color(169, 169, 169));
				users.setForeground(new Color(169, 169, 169));
				
				add(customersPanel, BorderLayout.CENTER);
				customersPanel.setVisible(true);
				statisticsPanel.setVisible(false);
				productsPanel.setVisible(false);
				categoryPanel.setVisible(false);
				ordersPanel.setVisible(false);
				usersPanel.setVisible(false);
				
				customersPanel.resetTable();
			}
		});
		
		customers.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(customers.getForeground() != Color.DARK_GRAY) {
					customers.setForeground(new Color(105, 105,105));
				}
			}
			public void mouseExited(MouseEvent e) {
				if(customers.getForeground() != Color.DARK_GRAY) {
					customers.setForeground(new Color(169, 169, 169));
				}
			}
		});
		
		
		JPanel grid_tail = new JPanel();
		subBar.add(grid_tail);
		grid_tail.setBackground(Color.WHITE);
		
		
		
		if(isSeller()) {
			//hid statistic
			subBar.remove(statistic);
			remove(statisticsPanel);
			add(categoryPanel, BorderLayout.CENTER);
			//hide user
			subBar.remove(users);
			remove(usersPanel);
			
		}
		else {
			subBar.remove(customers);
			remove(customersPanel);
		}
	}
	
	
	public boolean isSeller() {
		boolean check = false;
		try {
			Connection connection = OracleConn.getConnection();
			String sql = "select * from \"User\" where user_id = ? and role_id = 2";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, user_id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				check = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
}
