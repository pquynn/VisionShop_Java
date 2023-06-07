package view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Connect.OracleConn;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import components.CustomJTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EditProduct extends JFrame {
	
	public EditProduct() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(440, 180, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel product_heading = new JLabel("Thông tin sản phẩm");
		product_heading.setFont(new Font("SansSerif", Font.BOLD, 22));
		product_heading.setBounds(326, 10, 270, 40);
		contentPane.add(product_heading);
		
		JLabel nameLabel = new JLabel("Tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setForeground(Color.GRAY);
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		nameLabel.setBounds(43, 89, 62, 32);
		contentPane.add(nameLabel);
		
		name = new CustomJTextField("Tên sản phẩm");
		name.setBounds(110, 91, 237, 32);
		name.setTypingStyle();
		contentPane.add(name);
		
		JLabel priceLabel = new JLabel("Giá:");
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLabel.setForeground(Color.GRAY);
		priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		priceLabel.setBounds(34, 136, 71, 32);
		contentPane.add(priceLabel);
		
		price = new CustomJTextField("Giá");
		price.setBounds(110, 138, 237, 32);
		price.setTypingStyle();
		contentPane.add(price);
		
		JLabel quantityLabel = new JLabel("Số lượng:");
		quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		quantityLabel.setForeground(Color.GRAY);
		quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		quantityLabel.setBounds(453, 89, 71, 32);
		contentPane.add(quantityLabel);
		
		quantity = new CustomJTextField("Số lượng");
		quantity.setBounds(532, 91, 237, 32);
		quantity.setTypingStyle();
		contentPane.add(quantity);
		
		JLabel materialLabel = new JLabel("Chất liệu:");
		materialLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		materialLabel.setForeground(Color.GRAY);
		materialLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		materialLabel.setBounds(453, 225, 71, 32);
		contentPane.add(materialLabel);
		
		material = new CustomJTextField("Chất liệu");
		material.setBounds(532, 227, 237, 32);
		material.setTypingStyle();
		contentPane.add(material);
		
		JLabel colorLabel = new JLabel("Màu sắc:");
		colorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		colorLabel.setForeground(Color.GRAY);
		colorLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		colorLabel.setBounds(448, 281, 76, 32);
		contentPane.add(colorLabel);
		
		 color = new CustomJTextField("Màu sắc");
		color.setBounds(532, 282, 237, 32);
		color.setTypingStyle();
		contentPane.add(color);
		
		JLabel shapeLabel = new JLabel("Kiểu dáng:");
		shapeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		shapeLabel.setForeground(Color.GRAY);
		shapeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		shapeLabel.setBounds(444, 328, 80, 32);
		contentPane.add(shapeLabel);
		
		JButton editButton = new JButton("Lưu thay đổi");
		editButton.setForeground(Color.WHITE);
		editButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		editButton.setBackground(Color.BLACK);
		editButton.setBounds(233, 442, 197, 32);
		contentPane.add(editButton);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessage();
				
				if(validateProduct() && !checkDuplicateProductName())
					updateProductDetailById();
			}
		});
		
		JButton cancelButton = new JButton("Hủy");
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setBounds(440, 442, 197, 32);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận Huỷ cập nhật", "Hủy", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
				dispose();
				}
			}
		});
		
		JLabel categoryLabel = new JLabel("Phân loại:");
		categoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		categoryLabel.setForeground(Color.GRAY);
		categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		categoryLabel.setBounds(453, 384, 71, 32);
		contentPane.add(categoryLabel);
		
		category = new JComboBox();
		category.setForeground(new Color(105,105,105));
		category.setFont(new Font("SansSerif", Font.PLAIN, 14));
		category.setBackground(new Color(255, 255, 255));
		category.setBounds(532, 387, 237, 29);
		category.setBorder(null);
		contentPane.add(category);
		setCategoryItem();
		
		shape = new CustomJTextField("Kiểu dáng");
		shape.setBounds(532, 331, 237, 32);
		shape.setTypingStyle();
		contentPane.add(shape);
		
		JLabel descriptionLabel = new JLabel("Mô tả:");
		descriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		descriptionLabel.setForeground(Color.GRAY);
		descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		descriptionLabel.setBounds(451, 136, 71, 32);
		contentPane.add(descriptionLabel);
		
		description = new JTextArea();
		description.setForeground(new Color(105,105,105));
		description.setFont(new Font("SansSerif", Font.PLAIN, 14));
		description.setText("Mô tả ngắn");
		description.setBounds(532, 137, 237, 78);
		description.setBorder(new LineBorder(new Color(211,211,211)));
		contentPane.add(description);
		
		image = new JLabel();
		image.setIcon(new ImageIcon(AddProduct.class.getResource("/assets/no-image-icon-6.png")));
		image.setBackground(new Color(192, 192, 192));
		image.setBounds(90, 185, 290, 225);
		contentPane.add(image);
		image.addMouseListener(new MouseAdapter() { 
			//choose image from computer folder to jframe
			public void mouseClicked(MouseEvent e) {
				
			  file = new JFileChooser();
	          file.setCurrentDirectory(new File(System.getProperty("user.home")));
	          //filter the files
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
	          file.addChoosableFileFilter(filter);
	          int result = file.showSaveDialog(null);
	           //if the user click on save in Jfilechooser
	          if(result == JFileChooser.APPROVE_OPTION){
	              selectedFile = file.getSelectedFile();
	              
	              ImageIcon MyImage = new ImageIcon(selectedFile.getAbsolutePath());
	  	          Image img = MyImage.getImage();
	  	          Image newImg = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
	  	          ImageIcon newImgIcon = new ImageIcon(newImg); 
	              image.setIcon(newImgIcon);
	              isChanged = true;
	          }
			}
		
		});
		
		name_error = new JLabel();
		name_error.setForeground(Color.RED);
		name_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		name_error.setBounds(110, 121, 225, 21);
		contentPane.add(name_error);
		
		price_error = new JLabel();
		price_error.setForeground(Color.RED);
		price_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		price_error.setBounds(110, 168, 225, 21);
		contentPane.add(price_error);
		
		quantity_error = new JLabel();
		quantity_error.setForeground(Color.RED);
		quantity_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		quantity_error.setBounds(532, 121, 225, 21);
		contentPane.add(quantity_error);
		
		description_error = new JLabel();
		description_error.setForeground(Color.RED);
		description_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		description_error.setBounds(532, 214, 225, 21);
		contentPane.add(description_error);
		
		material_error = new JLabel();
		material_error.setForeground(Color.RED);
		material_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		material_error.setBounds(532, 265, 225, 21);
		contentPane.add(material_error);
		
		color_error = new JLabel();
		color_error.setForeground(Color.RED);
		color_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		color_error.setBounds(532, 314, 225, 21);
		contentPane.add(color_error);
		
		shape_error = new JLabel();
		shape_error.setForeground(Color.RED);
		shape_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		shape_error.setBounds(532, 359, 225, 21);
		contentPane.add(shape_error);
		
		image_error = new JLabel();
		image_error.setForeground(Color.RED);
		image_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		image_error.setBounds(110, 411, 225, 21);
		contentPane.add(image_error);
	}
	
	//set user detail by id to frame
	public void setProductDetailById() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select * from Glasses, \"Category\" where glasses_id = ? and Glasses.category_id = \"Category\".category_id";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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
				category.setSelectedItem(rs.getString("category_name"));
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
	
	//update user detail 
	public void updateProductDetailById() {
		String name = this.name.getText();
		String price = this.price.getText();
		String quantity = this.quantity.getText();
		String shape = this.shape.getText();
		String material = this.material.getText();
		String color = this.color.getText();
		String description = this.description.getText();
		int index = this.category.getSelectedIndex();
		String category = this.category.getItemAt(index).toString(); 
		int category_id = findIDByCategoryName(category);
		
		try {
			Connection con = OracleConn.getConnection();
			String sql = 
				"update Glasses set glasses_name=?,price=?,quantity=?,\"description\"=?,color=?,shape=?,material=?,category_id=? where glasses_id=?";
			PreparedStatement prs = con.prepareStatement(sql);
			prs.setString(1, name);
			prs.setInt(2, Integer.parseInt(price));
			prs.setInt(3, Integer.parseInt(quantity));
			prs.setString(4, description);
			prs.setString(5, color);
			prs.setString(6, shape);
			prs.setString(7, material);
			prs.setInt(8, category_id);
			prs.setInt(9, id);
			
			int RowCount = prs.executeUpdate();
			if(RowCount > 0) {
				JOptionPane.showMessageDialog(null, "Cập nhật thành công");
				resetTable();
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(isChanged) {
			try {
				Connection con = OracleConn.getConnection();
				String sql = 
					"update Glasses set image=? where glasses_id=?";
				PreparedStatement prs = con.prepareStatement(sql);
				InputStream in = new FileInputStream(selectedFile);
				prs.setBlob(1, in);
				prs.setInt(2, id);
				
				int rowcount = prs.executeUpdate();
				if(rowcount < 0) {
					JOptionPane.showMessageDialog(null, "Cập nhật hình ảnh không thành công");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//validation
	public boolean validateProduct() {
		tname = this.name.getText();
		 tprice = this.price.getText();
		 tquantity = this.quantity.getText();
		 tcolor = this.color.getText();
		 tshape = this.shape.getText();
		 tmaterial = this.material.getText();
		 tdescription = this.description.getText();
		
		boolean check = true;
		
		if (tname.equals("")) {
			name_error.setText("Yêu cầu nhập Họ tên.");
			check = false;
		}else if (checkDuplicateProductName())
			name_error.setText("Tên sản phẩm đã tồn tại.");
		
		if (tprice.equals("")) {
			price_error.setText("Yêu cầu nhập Giá.");
			check = false;
		}
		else if (!tprice.matches("[0-9]+")){ 
				check = false;
			}
		
		if (tquantity.equals("")) {
			quantity_error.setText("Yêu cầu nhập Số lượng.");
			check = false;
		}
		else if(!tquantity.matches("[0-9]+")) {
		check = false;
		}
		
		if (tcolor.equals("")) {
			color_error.setText("Yêu cầu nhập Màu sắc.");
			check = false;
		}
		
		if (tshape.equals("")) {
			shape_error.setText("Yêu cầu nhập Kiểu dáng.");
			check = false;
		}
		
		if (tmaterial.equals("")) {
			material_error.setText("Yêu cầu nhập Chất liệu.");
			check = false;
		}
		
		if (tdescription.equals("")) {
			description_error.setText("Yêu cầu nhập Mô tả.");
			check = false;
		}
		return check;
	}
	
	// check duplicate product name 
	public boolean checkDuplicateProductName() {
		String name = this.name.getText();
		boolean isExist = false;
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select * from Glasses where glasses_name = ? and glasses_id <> ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, id);
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
	
	// to reset jtable after updating
	public void resetTable() {
		products.clearTable();
		products.setProductsToTable();
	}
	
	public void setUsersPanel(Products products) {
		this.products = products;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	// clear validation messages 
	public void clearMessage() {
		name_error.setText("");
		price_error.setText("");
		quantity_error.setText("");
		description_error.setText("");
		material_error.setText("");
		color_error.setText("");
		shape_error.setText("");
		image_error.setText("");
	}
	
	//set category name to combobox category
    public void setCategoryItem() {
    	try {
    		Connection con = OracleConn.getConnection();
			String sql = "select * from \"Category\" order by category_id";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				category.addItem(rs.getString("category_name")); 
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
		    
    private JPanel contentPane;
	private CustomJTextField name;
	private CustomJTextField price;
	private CustomJTextField quantity;
	private JTextArea description;
	private CustomJTextField shape;
	private CustomJTextField material;
	private CustomJTextField color;
	private JComboBox category;
	private JLabel image;
	private JLabel name_error;
	private JLabel price_error;
	private JLabel quantity_error;
	private JLabel shape_error;
	private JLabel material_error;
	private JLabel color_error;
	private JLabel description_error;
	private JLabel image_error;
	private Products products;
	private int id;
	private JFileChooser file;
	private File selectedFile;
	private boolean isChanged = false;
	private String tname, tprice, tquantity, tdescription, tshape, tcolor, tmaterial, tcategory;
}
