package view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddProduct extends JFrame {

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
	
	private Products product;
	private String tname, tprice, tquantity, tdescription, tshape, tcolor, tmaterial, tcategory;
	private JFileChooser file;
	private File selectedFile;
	private boolean isExits = false;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AddProduct() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(440, 180, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel add_product_heading = new JLabel("Nhập thông tin sản phẩm");
		add_product_heading.setFont(new Font("SansSerif", Font.BOLD, 22));
		add_product_heading.setBounds(303, 10, 270, 40);
		contentPane.add(add_product_heading);
		
		JLabel nameLabel = new JLabel("Tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setForeground(Color.GRAY);
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		nameLabel.setBounds(43, 89, 62, 32);
		contentPane.add(nameLabel);
		
		name = new CustomJTextField("Tên sản phẩm");
		name.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(checkDuplicateProductName())
					name_error.setText("Sản phẩm đã tồn tại");
			}
			public void keyPressed(KeyEvent e) {
				name_error.setText("");
			}
		});
		name.setBounds(110, 91, 237, 32);
		contentPane.add(name);
		name.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(name.getText().equals("Tên sản phẩm")) {
					name.setText("");
					name.requestFocus();
					name.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(name.getText().length() == 0) {
					name.setDefaultStyle();
					name.setText("Tên sản phẩm");
				}
			}
		});
		
		JLabel priceLabel = new JLabel("Giá:");
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLabel.setForeground(Color.GRAY);
		priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		priceLabel.setBounds(34, 136, 71, 32);
		contentPane.add(priceLabel);
		
		price = new CustomJTextField("Giá");
		price.setText("Giá");
		price.setBounds(110, 138, 237, 32);
		contentPane.add(price);
		price.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(price.getText().equals("Giá")) {
					price.setText("");
					price.requestFocus();
					price.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(price.getText().length() == 0) {
					price.setDefaultStyle();
					price.setText("Giá");
				}
			}
		});
		price.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(!price.getText().matches("[0-9]+") && !price.getText().equals(""))
					price_error.setText("Không hợp lệ. Giá tiền phải là số");
			}
			public void keyPressed(KeyEvent e) {
				price_error.setText("");
			}
		});
		
		JLabel quantityLabel = new JLabel("Số lượng:");
		quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		quantityLabel.setForeground(Color.GRAY);
		quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		quantityLabel.setBounds(453, 89, 71, 32);
		contentPane.add(quantityLabel);
		
		quantity = new CustomJTextField("Số lượng");
		quantity.setBounds(532, 91, 237, 32);
		contentPane.add(quantity);
		quantity.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(quantity.getText().equals("Số lượng")) {
					quantity.setText("");
					quantity.requestFocus();
					quantity.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(quantity.getText().length() == 0) {
					quantity.setDefaultStyle();
					quantity.setText("Số lượng");
				}
			}
		});
		quantity.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(!quantity.getText().matches("[0-9]+")  && !quantity.getText().equals(""))
					quantity_error.setText("Không hợp lệ. Số lượng phải là số");
			}
			public void keyPressed(KeyEvent e) {
				quantity_error.setText("");
			}
		});
		
		JLabel materialLabel = new JLabel("Chất liệu:");
		materialLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		materialLabel.setForeground(Color.GRAY);
		materialLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		materialLabel.setBounds(453, 234, 71, 32);
		contentPane.add(materialLabel);
		
		material = new CustomJTextField("Chất liệu");
		material.setBounds(532, 235, 237, 32);
		contentPane.add(material);
		material.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(material.getText().equals("Chất liệu")) {
					material.setText("");
					material.requestFocus();
					material.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(material.getText().length() == 0) {
					material.setDefaultStyle();
					material.setText("Chất liệu");
				}
			}
		});
		
		JLabel colorLabel = new JLabel("Màu sắc:");
		colorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		colorLabel.setForeground(Color.GRAY);
		colorLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		colorLabel.setBounds(448, 281, 76, 32);
		contentPane.add(colorLabel);
		
		color = new CustomJTextField("Màu sắc");
		color.setBounds(532, 282, 237, 32);
		contentPane.add(color);
		color.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(color.getText().equals("Màu sắc")) {
					color.setText("");
					color.requestFocus();
					color.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(color.getText().length() == 0) {
					color.setDefaultStyle();
					color.setText("Màu sắc");
				}
			}
		});
		
		JLabel shapeLabel = new JLabel("Kiểu dáng:");
		shapeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		shapeLabel.setForeground(Color.GRAY);
		shapeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		shapeLabel.setBounds(444, 328, 80, 32);
		contentPane.add(shapeLabel);
		
		JButton addButton = new JButton("Thêm mới");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessage();
				
				if(validateAddProduct() && !checkDuplicateProductName()) {
					insertProduct();
				}
				
			}
		});
		addButton.setForeground(Color.WHITE);
		addButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		addButton.setBackground(Color.BLACK);
		addButton.setBounds(222, 442, 197, 32);
		contentPane.add(addButton);
		
		JButton cancelButton = new JButton("Hủy");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận Huỷ thêm mới", "Hủy", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
				dispose();
				}
			}
		});
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setBounds(429, 442, 197, 32);
		contentPane.add(cancelButton);
		
		JLabel categoryLabel = new JLabel("Phân loại:");
		categoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		categoryLabel.setForeground(Color.GRAY);
		categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		categoryLabel.setBounds(453, 384, 71, 32);
		contentPane.add(categoryLabel);
		
		category = new JComboBox();
		category.setForeground(new Color(169, 169, 169));
		category.setFont(new Font("SansSerif", Font.PLAIN, 14));
		category.setBackground(new Color(255, 255, 255));
		category.setBounds(532, 387, 237, 29);
		contentPane.add(category);
		//link jcombobox with database
		setCategoryItem();
		
		shape = new CustomJTextField("Kiểu dáng");
		shape.setBounds(532, 329, 237, 32);
		contentPane.add(shape);
		shape.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(shape.getText().equals("Kiểu dáng")) {
					shape.setText("");
					shape.requestFocus();
					shape.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(shape.getText().length() == 0) {
					shape.setDefaultStyle();
					shape.setText("Kiểu dáng");
				}
			}
		});
		
		JLabel descriptionLabel = new JLabel("Mô tả:");
		descriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		descriptionLabel.setForeground(Color.GRAY);
		descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		descriptionLabel.setBounds(451, 136, 71, 32);
		contentPane.add(descriptionLabel);
		
		description = new JTextArea("Mô tả ngắn");
		description.setForeground(new Color(211, 211, 211));
		description.setFont(new Font("SansSerif", Font.PLAIN, 14));
		description.setBounds(532, 137, 237, 78);
		description.setBorder(new LineBorder(new Color(211,211,211)));
		contentPane.add(description);
		description.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(description.getText().equals("Mô tả ngắn")) {
					description.setText("");
					description.requestFocus();
					description.setForeground(new Color(105,105,105));
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(description.getText().length() == 0) {
					description.setForeground(new Color(192,192,192));
					description.setText("Mô tả ngắn");
				}
			}
		});
		
		//insert image from computer
		image = new JLabel();
		image.setIcon(new ImageIcon(AddProduct.class.getResource("/assets/no-image-icon-6.png")));
		
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
	              isExits = true;
	          }
			}
		});
		image.setBackground(new Color(192, 192, 192));
		image.setBounds(110, 185, 252, 225);
		contentPane.add(image);
		
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
	
	
	// insert value into user table
	public void insertProduct() {
		 tname = this.name.getText();
		 tprice = this.price.getText();
		 tquantity = this.quantity.getText();
		 tcolor = this.color.getText();
		 tshape = this.shape.getText();
		 tmaterial = this.material.getText();
		 tdescription = this.description.getText();
		 
		int index = this.category.getSelectedIndex();
		tcategory = this.category.getItemAt(index).toString(); 
		int category_id = findIDByCategoryName(tcategory);
		try {
			Connection con = OracleConn.getConnection();
			String sql = 
				"insert into Glasses(glasses_name, price, quantity, color, shape, material, \"description\", category_id, file_image) values (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement prs = con.prepareStatement(sql);
			InputStream in = new FileInputStream(selectedFile);
			prs.setString(1, tname);
			prs.setInt(2, Integer.parseInt(tprice));
			prs.setInt(3, Integer.parseInt(tquantity));
			prs.setString(4, tcolor);
			prs.setString(5, tshape);
			prs.setString(6, tmaterial);
			prs.setString(7, tdescription);
			prs.setInt(8, category_id);
			prs.setBlob(9, in);
			
			
			int RowCount = prs.executeUpdate();
			if(RowCount > 0) {
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				resetTable();
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Thêm không thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//validation
	public boolean validateAddProduct() {
		tname = this.name.getText();
		 tprice = this.price.getText();
		 tquantity = this.quantity.getText();
		 tcolor = this.color.getText();
		 tshape = this.shape.getText();
		 tmaterial = this.material.getText();
		 tdescription = this.description.getText();
		
		boolean check = true;
		
		if (tname.equals("Tên sản phẩm")) {
			name_error.setText("Yêu cầu nhập Họ tên.");
			check = false;
		}
		
		if (tprice.equals("Giá")) {
			price_error.setText("Yêu cầu nhập Giá.");
			check = false;
		}
		else if (!tprice.matches("[0-9]+")){ 
				check = false;
			}
		
		if (tquantity.equals("Số lượng")) {
			quantity_error.setText("Yêu cầu nhập Số lượng.");
			check = false;
		}
		else if(!tquantity.matches("[0-9]+")) {
		check = false;
		}
		
		if (tcolor.equals("Màu sắc")) {
			color_error.setText("Yêu cầu nhập Màu sắc.");
			check = false;
		}
		
		if (tshape.equals("Kiểu dáng")) {
			shape_error.setText("Yêu cầu nhập Kiểu dáng.");
			check = false;
		}
		
		if (tmaterial.equals("Chất liệu")) {
			material_error.setText("Yêu cầu nhập Chất liệu.");
			check = false;
		}
		
		if (tdescription.equals("Mô tả ngắn")) {
			description_error.setText("Yêu cầu nhập Mô tả.");
			check = false;
		}
		
		if (isExits == false) {
			image_error.setText("Yêu cầu chọn ảnh cho sản phẩm.");
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
			String sql = "select * from Glasses where glasses_name = ?";
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
	
	// to reset jtable after inserting
	public void resetTable() {
		product.clearTable();
		product.setProductsToTable();
	}
	
	//set Product panel 
	public void setProductPane(Products product) {
		this.product = product;
	}
	
	// Methode to resize imageIcon with the same size of a Jlabel
    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
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
}
