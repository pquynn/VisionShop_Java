package view.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import Connect.OracleConn;
import view.Login;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HomePage extends JFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage(22);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomePage(int user_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1040, 640);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//----------user_id
		this.user_id = user_id;
		
		//----------homePanel
		homePanel = new Home(this.user_id).instanceHome;
		contentPane.add(homePanel, BorderLayout.CENTER);
		
		//-----------
		cartPanel = new Cart(this.user_id).instanceCart;
		myorderPanel = new MyOrder(this.user_id).instanceMyOrder;
		accountPanel = new Account(this.user_id).instanceAccount;
		adminPanel = new Admin(this.user_id).instanceAdmin;

		//-----------navbar
		navbar = new JPanel();
		contentPane.add(navbar, BorderLayout.WEST);
		navbar.setPreferredSize(new Dimension(120, 100));
		navbar.setLayout(new BorderLayout(0, 0));
		
		navbar_header = new JPanel();
		navbar_header.setBounds(0, 160, 1, 1);
		navbar.add(navbar_header, BorderLayout.CENTER);
		navbar_header.setBackground(new Color(26,26,26));
		navbar_header.setLayout(null);
		
		JLabel logo = new JLabel(" C-O-");
		logo.setBounds(0, 10, 120, 32);
		navbar_header.add(logo);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setForeground(new Color(255, 255, 255));
		logo.setFont(new Font("SansSerif", Font.BOLD, 24));
		
		JLabel shop_name = new JLabel(" VISION");
		shop_name.setBounds(0, 27, 120, 48);
		navbar_header.add(shop_name);
		shop_name.setHorizontalAlignment(SwingConstants.CENTER);
		shop_name.setForeground(Color.WHITE);
		shop_name.setFont(new Font("SansSerif", Font.BOLD, 24));
		
		navbar_footer = new JPanel();
		navbar_footer.setBounds(-50, 5, 100, 150);
		navbar.add(navbar_footer, BorderLayout.SOUTH);
		navbar_footer.setBackground(new Color(26,26,26));
		navbar_footer.setPreferredSize(new Dimension(100, 150));
		navbar_footer.setLayout(null);
		
		
		//--logout
		logout = new JButton("     Đăng xuất");
		logout.setHorizontalAlignment(SwingConstants.LEFT);
		logout.setBackground(new Color(26,26,26));
		logout.setBounds(0, 98, 121, 32);
		navbar_footer.add(logout);
		logout.setForeground(Color.LIGHT_GRAY);
		logout.setFont(new Font("SansSerif", Font.BOLD, 15));
		logout.setBorder(null);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận đăng xuất", "Đăng xuất", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		
		logout.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(logout.getBackground().equals(new Color(150,148,148)) == false) {
					logout.setBackground(new Color(85,85,85));
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(logout.getBackground().equals(new Color(150,148,148)) == false) {
					logout.setBackground(new Color(26,26,26));
				}
			}
		});
		
		//--account
		account = new JButton("     Tài khoản");
		account.setHorizontalAlignment(SwingConstants.LEFT);
		account.setBackground(new Color(26,26,26));
		account.setBounds(0, 64, 121, 32);
		navbar_footer.add(account);
		account.setForeground(Color.LIGHT_GRAY);
		account.setFont(new Font("SansSerif", Font.BOLD, 15));
		account.setBorder(null);
		account.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.setBackground(new Color(150,148,148));
				cart.setBackground(new Color(26,26,26));
				myorder.setBackground(new Color(26,26,26));
				admin.setBackground(new Color(26,26,26));
				home.setBackground(new Color(26,26,26));
				
				account.setForeground(Color.white);
				cart.setForeground(Color.LIGHT_GRAY);
				myorder.setForeground(Color.LIGHT_GRAY);
				admin.setForeground(Color.LIGHT_GRAY);
				home.setForeground(Color.LIGHT_GRAY);
				
				contentPane.add(accountPanel, BorderLayout.CENTER);
				homePanel.setVisible(false);
				cartPanel.setVisible(false);
				accountPanel.setVisible(true);
				myorderPanel.setVisible(false);
				adminPanel.setVisible(false);
				
				accountPanel.setUserDetail();
			}
		});
		
		account.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(account.getBackground().equals(new Color(150,148,148)) == false) {
					account.setBackground(new Color(85,85,85));
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(account.getBackground().equals(new Color(150,148,148)) == false) {
					account.setBackground(new Color(26,26,26));
				}
			}
		});
		
		//home
		home = new JButton("     Trang chủ");
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBackground(new Color(150,148,148));
		home.setBounds(0, 86, 121, 32);
		navbar_header.add(home);
		home.setForeground(Color.white);
		home.setFont(new Font("SansSerif", Font.BOLD, 15));
		home.setBorder(null);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setBackground(new Color(150,148,148));
				cart.setBackground(new Color(26,26,26));
				myorder.setBackground(new Color(26,26,26));
				admin.setBackground(new Color(26,26,26));
				account.setBackground(new Color(26,26,26));
				
				home.setForeground(Color.white);
				cart.setForeground(Color.LIGHT_GRAY);
				myorder.setForeground(Color.LIGHT_GRAY);
				admin.setForeground(Color.LIGHT_GRAY);
				account.setForeground(Color.LIGHT_GRAY);
				
				homePanel.setVisible(true);
				cartPanel.setVisible(false);
				accountPanel.setVisible(false);
				myorderPanel.setVisible(false);
				adminPanel.setVisible(false);
				
				homePanel.setProductList(homePanel.getProductsList());
			}
		});

		home.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(home.getBackground().equals(new Color(150,148,148)) == false) {
					home.setBackground(new Color(85,85,85));
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(home.getBackground().equals(new Color(150,148,148)) == false) {
					home.setBackground(new Color(26,26,26));
				}
			}
		});
		
		//--cart
		cart = new JButton("     Giỏ hàng");
		cart.setHorizontalAlignment(SwingConstants.LEFT);
		cart.setBackground(new Color(26,26,26));
		cart.setBounds(0, 120, 120, 32);
		navbar_header.add(cart);
		cart.setForeground(Color.LIGHT_GRAY);
		cart.setFont(new Font("SansSerif", Font.BOLD, 15));
		cart.setBorder(null);
		cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cart.setBackground(new Color(150,148,148));
				home.setBackground(new Color(26,26,26));
				myorder.setBackground(new Color(26,26,26));
				admin.setBackground(new Color(26,26,26));
				account.setBackground(new Color(26,26,26));
				
				cart.setForeground(Color.white);
				home.setForeground(Color.LIGHT_GRAY);
				myorder.setForeground(Color.LIGHT_GRAY);
				admin.setForeground(Color.LIGHT_GRAY);
				account.setForeground(Color.LIGHT_GRAY);
				
				contentPane.add(cartPanel, BorderLayout.CENTER);
				homePanel.setVisible(false);
				cartPanel.setVisible(true);
				accountPanel.setVisible(false);
				myorderPanel.setVisible(false);
				adminPanel.setVisible(false);
				
				cartPanel.setOrderDetailToCart();
				cartPanel.setUserDetail();
			}
		});
		
		cart.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(cart.getBackground().equals(new Color(150,148,148)) == false) {
					cart.setBackground(new Color(85,85,85));
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(cart.getBackground().equals(new Color(150,148,148)) == false) {
					cart.setBackground(new Color(26,26,26));
				}
			}
		});
		
		//--myorder
		myorder = new JButton("     Đơn hàng");
		myorder.setHorizontalAlignment(SwingConstants.LEFT);
		myorder.setBackground(new Color(26,26,26));
		myorder.setForeground(Color.LIGHT_GRAY);
		myorder.setFont(new Font("SansSerif", Font.BOLD, 15));
		myorder.setBounds(0, 154, 120, 32);
		navbar_header.add(myorder);
		myorder.setBorder(null);
		myorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myorder.setBackground(new Color(150,148,148));
				cart.setBackground(new Color(26,26,26));
				home.setBackground(new Color(26,26,26));
				admin.setBackground(new Color(26,26,26));
				account.setBackground(new Color(26,26,26));
				
				myorder.setForeground(Color.white);
				cart.setForeground(Color.LIGHT_GRAY);
				home.setForeground(Color.LIGHT_GRAY);
				admin.setForeground(Color.LIGHT_GRAY);
				account.setForeground(Color.LIGHT_GRAY);
				
				contentPane.add(myorderPanel, BorderLayout.CENTER);
				homePanel.setVisible(false);
				cartPanel.setVisible(false);
				accountPanel.setVisible(false);
				myorderPanel.setVisible(true);
				adminPanel.setVisible(false);
				
				myorderPanel.resetTable();
			}
		});
		myorder.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(myorder.getBackground().equals(new Color(150,148,148)) == false) {
					myorder.setBackground(new Color(85,85,85));
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(myorder.getBackground().equals(new Color(150,148,148)) == false) {
					myorder.setBackground(new Color(26,26,26));
				}
			}
		});
		
		//--admin
		admin = new JButton("     Quản trị");
		admin.setHorizontalAlignment(SwingConstants.LEFT);
		admin.setBackground(new Color(26,26,26));
		admin.setForeground(Color.LIGHT_GRAY);
		admin.setFont(new Font("SansSerif", Font.BOLD, 15));
		admin.setBounds(0, 188, 121, 32);
		navbar_header.add(admin);
		admin.setBorder(null);
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin.setBackground(new Color(150,148,148));
				cart.setBackground(new Color(26,26,26));
				myorder.setBackground(new Color(26,26,26));
				home.setBackground(new Color(26,26,26));
				account.setBackground(new Color(26,26,26));
				
				admin.setForeground(Color.white);
				cart.setForeground(Color.LIGHT_GRAY);
				myorder.setForeground(Color.LIGHT_GRAY);
				home.setForeground(Color.LIGHT_GRAY);
				account.setForeground(Color.LIGHT_GRAY);
				
				contentPane.add(adminPanel, BorderLayout.CENTER);
				homePanel.setVisible(false);
				cartPanel.setVisible(false);
				accountPanel.setVisible(false);
				myorderPanel.setVisible(false);
				adminPanel.setVisible(true);
			}
		});
		
		admin.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(admin.getBackground().equals(new Color(150,148,148)) == false) {
					admin.setBackground(new Color(85,85,85));
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(admin.getBackground().equals(new Color(150,148,148)) == false) {
					admin.setBackground(new Color(26,26,26));
				}
			}
		});
		
		setUIByRole();
	}
	
	// set login screen
	public void setLogin(Login lg) {
		login = lg;
	}
	
	//ham phan quyen user
	public void setUIByRole() {
		if(isCustomer()) {
			admin.setVisible(false);
		}
	}
	
	public boolean isCustomer() {
		boolean check = false;
		try {
			Connection connection = OracleConn.getConnection();
			String sql = "select * from \"User\" where user_id = ? and role_id = 3";
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

	private Login login;
	private JPanel contentPane;
	private JPanel navbar;
	private JPanel navbar_footer;
	private JPanel navbar_header;
	private JButton home;
	private JButton cart;
	private JButton myorder;
	private JButton admin;
	private JButton account;
	private JButton logout;
	private Home homePanel;
	private Cart cartPanel;
	private MyOrder myorderPanel;
	private Admin adminPanel;
	private Account accountPanel;
	private int user_id;
}
