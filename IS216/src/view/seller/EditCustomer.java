package view.seller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Connect.OracleConn;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import components.CustomJTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class EditCustomer extends JFrame {

	public EditCustomer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(440, 180, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel user_heading = new JLabel("Thông tin khách hàng");
		user_heading.setFont(new Font("SansSerif", Font.BOLD, 22));
		user_heading.setBounds(323, 33, 290, 40);
		contentPane.add(user_heading);
		
		JLabel nameLabel = new JLabel("Họ tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setForeground(Color.GRAY);
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		nameLabel.setBounds(94, 120, 62, 32);
		contentPane.add(nameLabel);
		
		name = new CustomJTextField("Họ tên");
		name.setBounds(161, 122, 228, 32);
		name.setTypingStyle();
		contentPane.add(name);
		name.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				name_error.setText("");
				if(name.getText().length() == 0) {
					name_error.setText("Yêu cầu nhập Họ tên.");
				}
			}
		});
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setForeground(Color.GRAY);
		emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		emailLabel.setBounds(85, 167, 71, 32);
		contentPane.add(emailLabel);
		
		email = new CustomJTextField("Email");
		email.setBounds(161, 167, 228, 32);
		email.setTypingStyle();
		contentPane.add(email);
		email.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				email_error.setText("");
				if(email.getText().length() == 0) {
					email_error.setText("Yêu cầu nhập Email.");
				}
			}
		});
		email.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				email_error.setText("");
			}
			public void keyReleased(KeyEvent e) {
				if(checkDuplicateUser()) {
					email_error.setText("Email này đã tồn tại");
				}
				String vemail = email.getText();
				
				if (!vemail.matches("^.+@.+\\..+$")) {
					email_error.setText("Email không hợp lệ.");
				}
			}
		});
		
		JLabel addressLabel = new JLabel("Địa chỉ: ");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setForeground(Color.GRAY);
		addressLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		addressLabel.setBounds(448, 120, 71, 32);
		contentPane.add(addressLabel);
		
		address = new CustomJTextField("Địa chỉ");
		address.setBounds(529, 122, 228, 32);
		address.setTypingStyle();
		contentPane.add(address);
		
		JLabel phoneLabel = new JLabel("Điện thoại:");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setForeground(Color.GRAY);
		phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		phoneLabel.setBounds(443, 167, 76, 32);
		contentPane.add(phoneLabel);
		
		phone = new CustomJTextField("Điện thoại");
		phone.setBounds(529, 167, 228, 32);
		phone.setTypingStyle();
		contentPane.add(phone);
		phone.addKeyListener(new KeyAdapter() {
		 	public void keyReleased(KeyEvent e) {
		 		String vphone = phone.getText();
				if((vphone.length() != 10 || !vphone.matches("[0-9]+") || vphone.charAt(0) != '0')) {
					phone_error.setText("Điện thoại không hợp lệ");
				}
		 	}
		 	public void keyPressed(KeyEvent e) {
		 		phone_error.setText("");
		 	}
		 });
		
		JLabel genderLabel = new JLabel("Giới tính:");
		genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		genderLabel.setForeground(Color.GRAY);
		genderLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		genderLabel.setBounds(441, 216, 80, 32);
		contentPane.add(genderLabel);
		
		male = new JRadioButton("Nam");
		male.setForeground(Color.GRAY);
		male.setFont(new Font("SansSerif", Font.PLAIN, 14));
		male.setBackground(Color.WHITE);
		male.setBounds(526, 221, 62, 21);
		male.setActionCommand("Nam");
		contentPane.add(male);
		
		 female = new JRadioButton("Nữ");
		female.setForeground(Color.GRAY);
		female.setFont(new Font("SansSerif", Font.PLAIN, 14));
		female.setBackground(Color.WHITE);
		female.setBounds(602, 221, 62, 21);
		female.setActionCommand("Nữ");
		contentPane.add(female);
		
		other = new JRadioButton("Khác");
		other.setForeground(Color.GRAY);
		other.setFont(new Font("SansSerif", Font.PLAIN, 14));
		other.setBackground(Color.WHITE);
		other.setBounds(672, 221, 62, 21);
		other.setActionCommand("Khác");
		contentPane.add(other);
		
		gender = new ButtonGroup();
		gender.add(female);
		gender.add(male);
		gender.add(other);
		
		JButton editButton = new JButton("Lưu thay đổi");
		editButton.setForeground(Color.WHITE);
		editButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		editButton.setBackground(Color.BLACK);
		editButton.setBounds(231, 328, 197, 32);
		contentPane.add(editButton);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessage();
				if(validateUser() && !checkDuplicateUser())
					updateUserDetailById();
			}
		});
		
		JButton cancelButton = new JButton("Hủy");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận Hủy cập nhật", "Hủy", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
				dispose();
				}
			}
		});
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setBounds(439, 328, 197, 32);
		contentPane.add(cancelButton);
		
		name_error = new JLabel();
		name_error.setForeground(Color.RED);
		name_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		name_error.setBounds(166, 151, 109, 21);
		contentPane.add(name_error);
		
		email_error = new JLabel();
		email_error.setForeground(Color.RED);
		email_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		email_error.setBounds(161, 200, 109, 21);
		contentPane.add(email_error);
		
		phone_error = new JLabel();
		phone_error.setForeground(Color.RED);
		phone_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		phone_error.setBounds(530, 200, 177, 21);
		contentPane.add(phone_error);
	}
	
	//set user detail by id to frame
	public void setUserDetailByID() {
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select * from \"User\" where user_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, String.valueOf(id));
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				name.setText(rs.getString("full_name"));
				email.setText(rs.getString("email"));
				address.setText(rs.getString("address"));
				phone.setText(rs.getString("phone"));
				String genderString = rs.getString("gender");
				
				if(male.getText().equals(genderString))
					male.setSelected(true);
				else if(female.getText().equals(genderString))
						female.setSelected(true);
				else if(other.getText().equals(genderString))
						other.setSelected(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//update user detail 
	public void updateUserDetailById() {
		String full_name = name.getText();
		String address = this.address.getText(); 
		String email = this.email.getText(); 
		String phone = this.phone.getText(); 
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
			prs.setInt(6, id);
			
			int RowCount = prs.executeUpdate();
			if(RowCount > 0) {
				JOptionPane.showMessageDialog(null, "Cập nhật thành công");
				customers.resetTable();
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if((phone.length() != 10 || !phone.matches("[0-9]+") || phone.charAt(0) != '0')&& !phone.equals("Điện thoại")) {
			phone_error.setText("Điện thoại không hợp lệ");
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
	
	// clear validation messages 
	public void clearMessage() {
		name_error.setText("");
		email_error.setText("");
		phone_error.setText("");
	}
		
	public void setUsersPanel(Customers customers) {
		this.customers = customers;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	private JPanel contentPane;
	private CustomJTextField name;
	private CustomJTextField email;
	private CustomJTextField address;
	private CustomJTextField phone;
	private JRadioButton male;
	private JRadioButton female;
	private JRadioButton other;
	private ButtonGroup gender;
	private JLabel name_error;
	private JLabel email_error;
	private JLabel phone_error;
	private Customers customers;
	private int id;
}
