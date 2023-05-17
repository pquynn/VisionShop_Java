package view.user;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import components.CustomJTextField;
import view.ChangePassword;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Connect.OracleConn;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Account extends JPanel {
	private CustomJTextField name;
	private CustomJTextField email;
	private CustomJTextField address;
	private CustomJTextField phone;
	private JRadioButton male;
	private JRadioButton female;
	private JRadioButton other;
	private ButtonGroup gender;
	private JLabel name_display;
	private JLabel email_display;
	private JLabel avatar;
	
	private JLabel name_error;
	private JLabel email_error;
	private JLabel phone_error;
	
	private int user_id;
	private JFileChooser file;
	private File selectedFile;
	private boolean isChanged = false;
	
	private ChangePassword changePassword;
	
	public Account(int user_id) {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 650);
		setLayout(new BorderLayout(0, 0));
		this.user_id = user_id;
		
		JPanel content_heading = new JPanel();
		content_heading.setBackground(new Color(255, 255, 255));
		add(content_heading, BorderLayout.NORTH);
		content_heading.setPreferredSize(new Dimension(100, 80));
		content_heading.setLayout(null);
		
		JLabel info_heading = new JLabel("Thông tin tài khoản");
		info_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		info_heading.setBounds(25, 30, 305, 40);
		content_heading.add(info_heading);
		
		JPanel avatar_setting = new JPanel();
		avatar_setting.setBackground(new Color(255, 255, 255));
		add(avatar_setting, BorderLayout.WEST);
		avatar_setting.setLayout(null);
		avatar_setting.setPreferredSize(new Dimension(400,100));
		
		avatar = new JLabel("");
		avatar.addMouseListener(new MouseAdapter() {
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
		  	          Image newImg = img.getScaledInstance(avatar.getWidth(), avatar.getHeight(), Image.SCALE_SMOOTH);
		  	          ImageIcon newImgIcon = new ImageIcon(newImg); 
		              avatar.setIcon(newImgIcon);
		              isChanged = true;
		      
		          }
			}
		});
		avatar.setIcon(new ImageIcon(Account.class.getResource("/assets/avatar_large_icon.png")));
		avatar.setHorizontalAlignment(SwingConstants.CENTER);
		avatar.setBounds(172, 86, 150, 150);
		avatar_setting.add(avatar);
		
		/*JButton changeAvaButton_1 = new JButton("Thay đổi avatar");
		changeAvaButton_1.setForeground(Color.BLACK);
		changeAvaButton_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		changeAvaButton_1.setBackground(Color.WHITE);
		changeAvaButton_1.setBounds(172, 44, 152, 32);
		avatar_setting.add(changeAvaButton_1);
		*/
		
		name_display = new JLabel("name");
		name_display.setHorizontalAlignment(SwingConstants.CENTER);
		name_display.setForeground(new Color(105, 105, 105));
		name_display.setFont(new Font("SansSerif", Font.PLAIN, 12));
		name_display.setBounds(182, 232, 140, 32);
		avatar_setting.add(name_display);
		
		email_display = new JLabel("abc@gmail.com");
		email_display.setHorizontalAlignment(SwingConstants.CENTER);
		email_display.setForeground(SystemColor.controlDkShadow);
		email_display.setFont(new Font("SansSerif", Font.PLAIN, 12));
		email_display.setBounds(182, 259, 140, 25);
		avatar_setting.add(email_display);
		
		JPanel info_setting = new JPanel();
		info_setting.setBackground(new Color(255, 255, 255));
		add(info_setting, BorderLayout.CENTER);
		info_setting.setLayout(null);
		
		JLabel nameLabel = new JLabel("Họ tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setForeground(Color.GRAY);
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nameLabel.setBounds(0, 32, 96, 32);
		info_setting.add(nameLabel);
		
		name = new CustomJTextField("Họ tên");
		name.setBounds(106, 36, 252, 32);
		name.setTypingStyle();
		info_setting.add(name);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setForeground(Color.GRAY);
		emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		emailLabel.setBounds(0, 83, 96, 32);
		info_setting.add(emailLabel);
		
		email = new CustomJTextField("Email");
		email.setBounds(106, 85, 252, 32);
		email.setTypingStyle();
		info_setting.add(email);
		
		JLabel addressLabel = new JLabel("Địa chỉ: ");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setForeground(Color.GRAY);
		addressLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		addressLabel.setBounds(0, 130, 96, 32);
		info_setting.add(addressLabel);
		
		address = new CustomJTextField("Địa chỉ");
		address.setBounds(106, 132, 252, 32);
		address.setTypingStyle();
		info_setting.add(address);
		
		JLabel phoneLabel = new JLabel("Điện thoại:");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setForeground(Color.GRAY);
		phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		phoneLabel.setBounds(0, 183, 96, 32);
		info_setting.add(phoneLabel);
		
		phone = new CustomJTextField("Điện thoại");
		phone.setBounds(106, 185, 252, 32);
		phone.setTypingStyle();
		info_setting.add(phone);
		phone.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(phone.getText().equals("Điện thoại")) {
					phone.setText("");
					phone.requestFocus();
					phone.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(phone.getText().length() == 0) {
					phone.setDefaultStyle();
					phone.setText("Điện thoại");
				}
			}
		});
		
		JLabel genderLabel = new JLabel("Giới tính:");
		genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		genderLabel.setForeground(Color.GRAY);
		genderLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		genderLabel.setBounds(0, 234, 96, 32);
		info_setting.add(genderLabel);
		
		JButton saveButton = new JButton("Lưu thay đổi");
		saveButton.setForeground(Color.WHITE);
		saveButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		saveButton.setBackground(Color.BLACK);
		saveButton.setBounds(106, 294, 252, 32);
		info_setting.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessage();
				
				if(validateUser() && !checkDuplicateUser())
					updateUserDetailById();
			}
		});
		
		JButton changePButton = new JButton("Đổi mật khẩu");
		changePButton.setForeground(Color.BLACK);
		changePButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		changePButton.setBackground(Color.WHITE);
		changePButton.setBounds(106, 336, 252, 32);
		info_setting.add(changePButton);
		changePButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewChangePassword();
			}
		});
		

		male = new JRadioButton("Nam");
		male.setForeground(Color.GRAY);
		male.setFont(new Font("SansSerif", Font.PLAIN, 14));
		male.setBackground(Color.WHITE);
		male.setBounds(106, 241, 62, 21);
		male.setActionCommand("Nam");
		info_setting.add(male);
		
		 female = new JRadioButton("Nữ");
		female.setForeground(Color.GRAY);
		female.setFont(new Font("SansSerif", Font.PLAIN, 14));
		female.setBackground(Color.WHITE);
		female.setBounds(204, 241, 62, 21);
		female.setActionCommand("Nữ");
		info_setting.add(female);
		
		 other = new JRadioButton("Khác");
		other.setForeground(Color.GRAY);
		other.setFont(new Font("SansSerif", Font.PLAIN, 14));
		other.setBackground(Color.WHITE);
		other.setBounds(282, 241, 62, 21);
		other.setActionCommand("Khác");
		info_setting.add(other);
		
		gender = new ButtonGroup();
		gender.add(female);
		gender.add(male);
		gender.add(other);
		
		 name_error = new JLabel();
		name_error.setForeground(Color.RED);
		name_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		name_error.setBounds(106, 65, 109, 21);
		info_setting.add(name_error);
		
		 email_error = new JLabel();
		email_error.setForeground(Color.RED);
		email_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		email_error.setBounds(106, 115, 109, 21);
		info_setting.add(email_error);
		
		 phone_error = new JLabel();
		phone_error.setForeground(Color.RED);
		phone_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		phone_error.setBounds(106, 214, 177, 21);
		info_setting.add(phone_error);
		
		setUserDetail();
	}
	
	public void setUserDetail() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select * from \"User\" where user_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				name.setText(rs.getString("full_name"));
				email.setText(rs.getString("email"));
				address.setText(rs.getString("address"));
				phone.setText(rs.getString("phone"));
				name_display.setText(rs.getString("full_name"));
				email_display.setText(rs.getString("email"));
				
				//gender
				String genderString = rs.getString("gender");
				if(male.getText().equals(genderString))
					male.setSelected(true);
				else if(female.getText().equals(genderString))
						female.setSelected(true);
				else if(other.getText().equals(genderString))
						other.setSelected(true);
				
				//image
				byte[] imagedata = rs.getBytes("image"); //cho no thanh hinh tron lun
				if(imagedata != null) {
				ImageIcon MyImage = new ImageIcon(imagedata);
		        Image img = MyImage.getImage();
		        Image newImg = img.getScaledInstance(avatar.getWidth(), avatar.getHeight(), Image.SCALE_SMOOTH);
		        ImageIcon newImgIcon = new ImageIcon(newImg); 
				avatar.setIcon(newImgIcon);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//update user detail 
		public void updateUserDetailById() {
			String full_name = name.getText();
			String address = this.address.getText(); //xet email khi thay doi da ton tai cha
			String email = this.email.getText(); //xet email co hop le ko
			String phone = this.phone.getText(); //xet so dt khi thay doi co hop le ko
			String gender = null;
			
			if(this.gender.getSelection() != null)
				 gender = this.gender.getSelection().getActionCommand();
			
			try {
				Connection con = OracleConn.getConnection();
				String sql = 
					"update \"User\" set full_name=?,address=?,email=?,phone=?,gender=? where user_id=?";
				PreparedStatement prs = con.prepareStatement(sql);
				
				prs.setString(1, full_name);
				prs.setString(2, address);
				prs.setString(3, email);
				prs.setString(4, phone);
				prs.setString(5, gender);
				prs.setInt(6, user_id);
				
				int RowCount = prs.executeUpdate();
				if(RowCount > 0) {
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					setUserDetail();
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
						"update \"User\" set image=? where user_id=?";
					PreparedStatement prs = con.prepareStatement(sql);
					InputStream in = new FileInputStream(selectedFile);
					prs.setBlob(1, in);
					prs.setInt(2, user_id);
					int rowcount = prs.executeUpdate();
					if(rowcount < 0) {
						JOptionPane.showMessageDialog(null, "Cập nhật hình ảnh không thành công");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public void createNewChangePassword() {
			changePassword = new ChangePassword(user_id);
			changePassword.setAccountPanel(this);
			changePassword.setVisible(true);
		}
		
		//validation
				public boolean validateUser() {
					String full_name = name.getText();
					String email = this.email.getText();
					String phone = this.phone.getText();
					
					boolean check = true;
					//full name
					if (full_name.equals("")) {
						name_error.setText("Yêu cầu nhập Họ tên.");
						check = false;
					}
					//email
					if (email.equals("")) {
						email_error.setText("Yêu cầu nhập Email.");
						check = false;
					}
					else if (!email.matches("^.+@.+\\..+$")) {
							email_error.setText("Email không hợp lệ.");
							check = false;
						}
					else if (checkDuplicateUser()) {
						email_error.setText("Email này đã tồn tại");
					}
					
					//phone
					if((phone.length() != 10 || !phone.matches("[0-9]+"))&& !phone.equals("Điện thoại")) {
					check = false;
					}
					return check;
				}
				
				// check duplicate user 
				public boolean checkDuplicateUser() {
					String email = this.email.getText();
					boolean isExist = false;
					try {
						Connection con = OracleConn.getConnection();
						String sql = "select * from \"User\" where email = ? and user_id <> ?";
						PreparedStatement pst = con.prepareStatement(sql);
						pst.setString(1, email);
						pst.setInt(2, user_id);
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
					email_error.setText("");
					phone_error.setText("");
				}
				
}
