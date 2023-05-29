package view.user;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import Connect.OracleConn;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.JobAttributes;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductDetail extends JFrame {

	private JPanel contentPane;
	private JTextField numb_glasses;
	private JLabel name;
	private JLabel price;
	private JLabel quantity;
	private JLabel shape;
	private JLabel material;
	private JLabel color;
	private JLabel category;
	private JLabel image;
	private JLabel description;
	private JLabel numb_glasses_error;
	
	private JButton add_to_cart;
	private JButton decrButton;
	private JButton incrButton;
	
	private int glasses_id, user_id, order_id;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductDetail frame = new ProductDetail(2, 1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ProductDetail(int glasses_id, int user_id) {
		this.glasses_id = glasses_id;
		this.user_id = user_id;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(440, 180, 880, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JLabel("Tên Sản Phẩm");
		name.setFont(new Font("SansSerif", Font.BOLD, 28));
		name.setBounds(459, 43, 397, 40);
		contentPane.add(name);
		
		 price = new JLabel("Giá");
		price.setHorizontalAlignment(SwingConstants.LEFT);
		price.setForeground(new Color(0, 0, 0));
		price.setFont(new Font("SansSerif", Font.PLAIN, 20));
		price.setBounds(487, 86, 156, 32);
		contentPane.add(price);
		
		JLabel shapeLabel = new JLabel("Kiểu dáng:");
		shapeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		shapeLabel.setForeground(new Color(128, 128, 128));
		shapeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		shapeLabel.setBounds(459, 154, 80, 32);
		contentPane.add(shapeLabel);
		
		add_to_cart = new JButton("Thêm vào giỏ hàng");
		add_to_cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numb_glasses_error.setText("");
				findOrderID();
				if(checkDuplicateGlasses() == false) {
					if(validateAddToCart())
						insertOrderDetail();
				}
				else {
					JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại trong hóa đơn");
				}
			}
		});
		add_to_cart.setForeground(Color.WHITE);
		add_to_cart.setFont(new Font("SansSerif", Font.BOLD, 16));
		add_to_cart.setBackground(Color.BLACK);
		add_to_cart.setBounds(459, 359, 240, 40);
		contentPane.add(add_to_cart);
		
		JLabel categoryLabel = new JLabel("Loại kính:");
		categoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
		categoryLabel.setForeground(new Color(128, 128, 128));
		categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		categoryLabel.setBounds(459, 128, 71, 32);
		contentPane.add(categoryLabel);
		
		image = new JLabel();
		image.setIcon(new ImageIcon(ProductDetail.class.getResource("/assets/no-image-icon-6.png")));
		image.setBounds(92, 63, 290, 225);
		contentPane.add(image);
		
		JLabel colorLabel = new JLabel("Màu sắc");
		colorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		colorLabel.setForeground(new Color(128, 128, 128));
		colorLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		colorLabel.setBounds(459, 180, 80, 32);
		contentPane.add(colorLabel);
		
		JLabel materialLabel = new JLabel("Chất liệu:");
		materialLabel.setHorizontalAlignment(SwingConstants.LEFT);
		materialLabel.setForeground(new Color(128, 128, 128));
		materialLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		materialLabel.setBounds(459, 206, 80, 32);
		contentPane.add(materialLabel);
		
		JLabel descriptionLabel = new JLabel("Mô tả ngắn:");
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setForeground(new Color(128, 128, 128));
		descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		descriptionLabel.setBounds(132, 298, 198, 32);
		contentPane.add(descriptionLabel);
		
		category = new JLabel("");
		category.setHorizontalAlignment(SwingConstants.LEFT);
		category.setForeground(Color.BLACK);
		category.setFont(new Font("SansSerif", Font.BOLD, 14));
		category.setBounds(522, 128, 298, 32);
		contentPane.add(category);
		
		shape = new JLabel("");
		shape.setHorizontalAlignment(SwingConstants.LEFT);
		shape.setForeground(Color.BLACK);
		shape.setFont(new Font("SansSerif", Font.BOLD, 14));
		shape.setBounds(532, 154, 288, 32);
		contentPane.add(shape);
		
		color = new JLabel("");
		color.setHorizontalAlignment(SwingConstants.LEFT);
		color.setForeground(Color.BLACK);
		color.setFont(new Font("SansSerif", Font.BOLD, 14));
		color.setBounds(522, 180, 298, 32);
		contentPane.add(color);
		
		material = new JLabel("");
		material.setHorizontalAlignment(SwingConstants.LEFT);
		material.setForeground(Color.BLACK);
		material.setFont(new Font("SansSerif", Font.BOLD, 14));
		material.setBounds(522, 206, 298, 32);
		contentPane.add(material);
		
		//use HTML to set multiple line in jlabel
		 description = new JLabel("a");
		 description.setVerticalAlignment(SwingConstants.TOP);
		//description.setHorizontalAlignment(SwingConstants.LEFT);
		description.setForeground(new Color(0, 0, 0));
		description.setFont(new Font("SansSerif", Font.BOLD, 14));
		description.setBounds(226, 305, 591, 40);
		contentPane.add(description);
		
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(null);
		actionPanel.setPreferredSize(new Dimension(100, 100));
		actionPanel.setBackground(Color.WHITE);
		actionPanel.setBounds(709, 359, 71, 40);
		contentPane.add(actionPanel);
		
		 decrButton = new JButton("-");
		decrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateNumber()) {
					int value = Integer.parseInt(numb_glasses.getText()) - 1;
					numb_glasses.setText(String.valueOf(value));
				}
				
			}
		});
		decrButton.setForeground(new Color(169, 169, 169));
		decrButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
		decrButton.setBorder(null);
		decrButton.setBackground(Color.WHITE);
		decrButton.setBounds(0, 3, 20, 28);
		actionPanel.add(decrButton);
		
		 incrButton = new JButton("+");
		incrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateNumber()) {
					int value = Integer.parseInt(numb_glasses.getText()) + 1;
					numb_glasses.setText(String.valueOf(value));
				}
			}
		});
		incrButton.setForeground(new Color(169, 169, 169));
		incrButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		incrButton.setBorder(null);
		incrButton.setBackground(Color.WHITE);
		incrButton.setBounds(46, 5, 20, 27);
		actionPanel.add(incrButton);
		
		numb_glasses = new JTextField();
		numb_glasses.setForeground(new Color(105, 105, 105));
		numb_glasses.setHorizontalAlignment(SwingConstants.CENTER);
		numb_glasses.setFont(new Font("SansSerif", Font.PLAIN, 16));
		numb_glasses.setText("1");
		numb_glasses.setColumns(10);
		numb_glasses.setBorder(new LineBorder(new Color(211, 211, 211)));
		numb_glasses.setBounds(20, 5, 26, 26);
		actionPanel.add(numb_glasses);
		
		JLabel quantityLabel = new JLabel("Số lượng:");
		quantityLabel.setHorizontalAlignment(SwingConstants.LEFT);
		quantityLabel.setForeground(Color.GRAY);
		quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		quantityLabel.setBounds(459, 235, 80, 32);
		contentPane.add(quantityLabel);
		
		quantity = new JLabel("");
		quantity.setHorizontalAlignment(SwingConstants.LEFT);
		quantity.setForeground(Color.BLACK);
		quantity.setFont(new Font("SansSerif", Font.BOLD, 14));
		quantity.setBounds(522, 235, 239, 32);
		contentPane.add(quantity);
		
		 numb_glasses_error = new JLabel("");
		numb_glasses_error.setHorizontalAlignment(SwingConstants.LEFT);
		numb_glasses_error.setForeground(new Color(255, 0, 0));
		numb_glasses_error.setFont(new Font("SansSerif", Font.PLAIN, 12));
		numb_glasses_error.setBounds(459, 341, 361, 25);
		contentPane.add(numb_glasses_error);
		
		JLabel currency = new JLabel("đ");
		currency.setHorizontalAlignment(SwingConstants.LEFT);
		currency.setForeground(Color.BLACK);
		currency.setFont(new Font("SansSerif", Font.PLAIN, 20));
		currency.setBounds(575, 86, 171, 32);
		contentPane.add(currency);
		
		setProductDetailById(glasses_id);
	}
	
	//set products detail 
	public void setProductDetailById(int glasses_id) {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select * from Glasses, \"Category\" where glasses_id = ? and Glasses.category_id = \"Category\".category_id";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, glasses_id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				name.setText(rs.getString("glasses_name"));
				price.setText(String.valueOf(rs.getInt("price")));
				quantity.setText(String.valueOf(rs.getInt("quantity")));
				shape.setText(rs.getString("shape"));
				color.setText(rs.getString("color"));
				material.setText(rs.getString("material"));
				Clob clob = rs.getClob("\"description\"");
				description.setText(castCLOBtoString(clob));
				category.setText(rs.getString("category_name"));
				
				//image
				byte[] imagedata = rs.getBytes("image");
				ImageIcon MyImage = new ImageIcon(imagedata);
		        Image img = MyImage.getImage();
		        Image newImg = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
		        ImageIcon newImgIcon = new ImageIcon(newImg); 
				image.setIcon(newImgIcon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//clob to string
	public String castCLOBtoString(Clob clob) throws SQLException, IOException {
	    BufferedReader stringReader = new BufferedReader(
	            clob.getCharacterStream());
	    String singleLine = null;
	    StringBuffer strBuff = new StringBuffer();
	    while ((singleLine = stringReader.readLine()) != null) {
	        strBuff.append(singleLine);
	    }
	    return strBuff.toString();
	}
	

	public boolean validateNumber() {
		boolean isValid = true;
		String numb = numb_glasses.getText();
		if(numb.equals("")) {
			numb_glasses_error.setText("Yêu cầu nhập số lượng mắt kính cần mua");
			isValid = false;
		}
		else if(!numb.matches("[0-9]+") || (Integer.parseInt(numb) <= 0)){
			numb_glasses_error.setText("Số lượng mắt kính phải là số lớn hơn 0");
			isValid = false;
		}
		return isValid;
	}
	//check validate number of glasses
	public boolean validateAddToCart() {
		boolean isValid = true;
		isValid = validateNumber();
		
		if(Integer.parseInt(numb_glasses.getText()) > Integer.parseInt(quantity.getText())) {
			numb_glasses_error.setText("Số lượng mắt kính tồn kho không đủ");
			isValid = false;
		}
		
		return isValid;
	}
	
	//find order_id by user_id and order_state --> tim don hang co trang thai chua xac nhan, neu ko thay --> tao don hang ms
	public void findOrderID() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select order_id from \"Order\" where user_id = ? and \"order_state\" = ?";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setString(2, "Chưa xác nhận");
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				order_id = rs.getInt(1);
			}
			else {
				//Find recent order_id in table and set new order_id
				try {
					String sql1 = "select order_id from \"Order\" order by order_id DESC fetch first 1 row only";
					Statement st = conn.createStatement();
					ResultSet rs1 = st.executeQuery(sql1);
					
					if (!rs1.next()) 
						order_id = 1;
					else 
						order_id = rs1.getInt("order_id") + 1;
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				
				//insert new order with that order_id
				try {
					String sql1 = "insert into \"Order\" (order_id, \"order_state\", user_id, total_glasses, total_money) values (?,?,?, 0, 0)";
					//nho lm them may cai trigger cho tong tien = 0 do a
					PreparedStatement st = conn.prepareStatement(sql1);
					st.setInt(1, order_id);
					st.setString(2, "Chưa xác nhận");
					st.setInt(3, user_id);
					
					int rowcount = st.executeUpdate();
					if(rowcount < 0)
						JOptionPane.showMessageDialog(this, "Xảy ra lỗi");
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// insert order details to cart  
	public void insertOrderDetail() {
		findOrderID();
		try {
			Connection con = OracleConn.getConnection();
			String sql = "insert into Order_detail(order_id, glasses_id, glasses_name, quantity, price) values (?,?,?,?,?)";
			PreparedStatement prs = con.prepareStatement(sql);
			
			prs.setInt(1, order_id);
			prs.setInt(2, glasses_id);
			prs.setString(3, name.getText());
			prs.setInt(4, Integer.parseInt(numb_glasses.getText()));
			prs.setInt(5, Integer.parseInt(this.price.getText()));
			
			int RowCount = prs.executeUpdate();
			if(RowCount > 0) {
				JOptionPane.showMessageDialog(this, "Thêm thành công. Hãy kiểm tra giỏ hàng của bạn");
				dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// check duplicate user 
	public boolean checkDuplicateGlasses() {
		boolean isExist = false;
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select * from Order_detail where glasses_id = ? and order_id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, glasses_id);
			pst.setInt(2, order_id);
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
	
	//set view only state
	public void setViewOnlyState() {
		add_to_cart.setVisible(false);
		decrButton.setVisible(false);
		incrButton.setVisible(false);
		numb_glasses.setVisible(false);
	}
}

				
