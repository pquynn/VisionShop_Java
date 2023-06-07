package components.ProductPanels;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import Connect.OracleConn;
import components.CustomJTextField;
import view.user.Cart;
import view.user.ProductDetail;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class ProductInCart extends JPanel {
	private CustomJTextField number_products;
	private JButton incrButton;
	private JButton decrButton;
	
	private int glasses_id, order_id;
	private Cart cart;

	public ProductInCart(int glasses_id, String vname, String vprice, String vquantity, byte[] imagedata, int order_id) {
		this.glasses_id = glasses_id;
		this.order_id = order_id;
		
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		JLabel image = new JLabel();
		add(image, BorderLayout.WEST);
		image.setSize(170, 100);
		if(imagedata != null) {
		ImageIcon MyImage = new ImageIcon(imagedata);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImgIcon = new ImageIcon(newImg); 
		image.setIcon(newImgIcon);
		add(image, BorderLayout.WEST);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(255, 255, 255));
		add(infoPanel, BorderLayout.CENTER);
		infoPanel.setLayout(null);
		
		JLabel name = new JLabel(vname);
		name.setFont(new Font("SansSerif", Font.BOLD, 18));
		name.setBounds(10, 0, 195, 27);
		infoPanel.add(name);
		
		JLabel price = new JLabel(vprice + " đ");
		price.setFont(new Font("SansSerif", Font.BOLD, 14));
		price.setBounds(10, 53, 195, 27);
		infoPanel.add(price);
		
		
		JPanel actionPanel = new JPanel();
		actionPanel.setBackground(new Color(255, 255, 255));
		add(actionPanel, BorderLayout.EAST);
		actionPanel.setPreferredSize(new Dimension(100, 100));
		actionPanel.setLayout(null);
		
		decrButton = new JButton("-");
		decrButton.setForeground(new Color(169, 169, 169));
		decrButton.setBounds(10, 28, 20, 28);
		actionPanel.add(decrButton);
		decrButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
		decrButton.setBorder(null);
		decrButton.setBackground(new Color(255, 255, 255));
		decrButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				decrButton.setForeground(Color.DARK_GRAY);
			}
			
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				decrButton.setForeground(new Color(169, 169, 169));
			}
		});
		decrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(number_products.getText()) - 1;
				if(value == 0) {
					int ret = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa sản phẩm", "Xóa", JOptionPane.YES_NO_OPTION);
					if (ret == JOptionPane.YES_OPTION){
						deleteOrderDetail();
						cart.removeProductInCart();
					}
					else {
						value ++;
						number_products.setText(String.valueOf(value));
					}
				}
				else {
					number_products.setText(String.valueOf(value));
					updateOrderDetail();
				}
			}
		});
		
		incrButton = new JButton("+");
		incrButton.setBounds(59, 30, 20, 27);
		actionPanel.add(incrButton);
		incrButton.setForeground(new Color(169, 169, 169));
		incrButton.setBackground(new Color(255, 255, 255));
		incrButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		incrButton.setBorder(null);
		incrButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				incrButton.setForeground(Color.DARK_GRAY);
			}
			
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				incrButton.setForeground(new Color(169, 169, 169));
			}
		});
		incrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(number_products.getText()) + 1;
				number_products.setText(String.valueOf(value));
				updateOrderDetail();
			}
		});
		
		number_products = new CustomJTextField();
		number_products.setBounds(32, 30, 26, 26);
		actionPanel.add(number_products);
		number_products.setText(vquantity);
		number_products.setHorizontalAlignment(SwingConstants.CENTER);
		number_products.setEditable(false);
	
		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(255, 255, 255));
		add(northPanel, BorderLayout.NORTH);
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(new Color(255, 255, 255));
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(null);
		southPanel.setPreferredSize(new Dimension(100, 10));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(211, 211, 211));
		separator.setBackground(new Color(211, 211, 211));
		separator.setBounds(0, 9, 1500, 1);
		southPanel.add(separator);

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ProductDetail pd  = new ProductDetail(glasses_id, 1);
				pd.setViewOnlyState();
				pd.setVisible(true);
			}
			
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(new Color(192,192,192)));
			}
			
			public void mouseExited(MouseEvent e) {
				setBorder(null);
			}
		});
		}
	}
	//method to set cart panel
	public void setCartPane(Cart cart) {
		this.cart = cart;
	}
	
	//delete order detail---------------
	public void deleteOrderDetail() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql1 = "delete from Order_detail where order_id = ? and glasses_id = ?";
			PreparedStatement pst1 = conn.prepareStatement(sql1);
			pst1.setInt(1, order_id);
			pst1.setInt(2, glasses_id);
			int rowcount1 = pst1.executeUpdate();
			if(rowcount1 > 0)
				cart.setTotalGlassesandPrice();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// update order details-------------- 
	public void updateOrderDetail() {
		try {
			Connection con = OracleConn.getConnection();
			String sql = "update Order_detail set quantity = ? where glasses_id = ? and order_id = ?";
			PreparedStatement prs = con.prepareStatement(sql);
			
			prs.setInt(1, Integer.parseInt(number_products.getText()));
			prs.setInt(2, glasses_id);
			prs.setInt(3, order_id);
			
			int RowCount = prs.executeUpdate();
			if(RowCount > 0) {
				cart.setTotalGlassesandPrice();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
