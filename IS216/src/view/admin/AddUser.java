package view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Connect.OracleConn;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import components.CustomJTextField;
import view.Login;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JRadioButton male;
	private JRadioButton female;
	private JRadioButton other;
	private JLabel name_error;
	private JLabel email_error;
	private JLabel password_error;
	private JLabel phone_error;
	private CustomJTextField name;
	private CustomJTextField email;
	private CustomJTextField password;
	private CustomJTextField address;
	private CustomJTextField phone;
	private JComboBox role;
	private ButtonGroup gender;
	private Users users;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AddUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(440, 180, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel add_user_heading = new JLabel("Nhập thông tin người dùng");
		add_user_heading.setFont(new Font("SansSerif", Font.BOLD, 22));
		add_user_heading.setBounds(280, 33, 290, 40);
		contentPane.add(add_user_heading);
		
		JLabel nameLabel = new JLabel("Họ tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setForeground(Color.GRAY);
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		nameLabel.setBounds(78, 120, 62, 32);
		contentPane.add(nameLabel);
		
		name = new CustomJTextField("Họ tên");
		name.setBounds(145, 122, 228, 32);
		contentPane.add(name);
		name.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(name.getText().equals("Họ tên")) {
					name.setText("");
					name.requestFocus();
					name.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(name.getText().length() == 0) {
					name.setDefaultStyle();
					name.setText("Họ tên");
				}
			}
		});
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setForeground(Color.GRAY);
		emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		emailLabel.setBounds(69, 167, 71, 32);
		contentPane.add(emailLabel);
		
		 email = new CustomJTextField("Email");
		email.setBounds(145, 170, 228, 32);
		contentPane.add(email);
		email.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(email.getText().equals("Email")) {
					email.setText("");
					email.requestFocus();
					email.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(email.getText().length() == 0) {
					email.setDefaultStyle();
					email.setText("Email");
				}
				if(checkDuplicateUser()) {
					email_error.setText("Email này đã tồn tại");
				}
			}
		});
		
		JLabel passwordLabel = new JLabel("Mật khẩu:");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setForeground(Color.GRAY);
		passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		passwordLabel.setBounds(69, 216, 71, 32);
		contentPane.add(passwordLabel);
		
		password = new CustomJTextField("Mật khẩu");
		password.setBounds(145, 218, 228, 32);
		contentPane.add(password);
		password.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(password.getText().equals("Mật khẩu")) {
					password.setText("");
					password.requestFocus();
					password.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(password.getText().length() == 0) {
					password.setDefaultStyle();
					password.setText("Mật khẩu");
				}
			}
		});
		
		JLabel addressLabel = new JLabel("Địa chỉ: ");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setForeground(Color.GRAY);
		addressLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		addressLabel.setBounds(449, 120, 71, 32);
		contentPane.add(addressLabel);
		
		address = new CustomJTextField("Địa chỉ");
		address.setBounds(530, 122, 228, 32);
		contentPane.add(address);
		address.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(address.getText().equals("Địa chỉ")) {
					address.setText("");
					address.requestFocus();
					address.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(address.getText().length() == 0) {
					address.setDefaultStyle();
					address.setText("Địa chỉ");
				}
			}
		});
		
		JLabel phoneLabel = new JLabel("Điện thoại:");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setForeground(Color.GRAY);
		phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		phoneLabel.setBounds(444, 167, 76, 32);
		contentPane.add(phoneLabel);
		
		 phone = new CustomJTextField("Điện thoại");
		 phone.addKeyListener(new KeyAdapter() {
		 	public void keyReleased(KeyEvent e) {
		 		if((phone.getText().length() != 10 || !phone.getText().matches("[0-9]+"))&& !phone.getText().equals("Điện thoại")) {
		 			phone_error.setText("Điện thoại không hợp lệ");
		 			}
		 	}
		 	public void keyPressed(KeyEvent e) {
		 		phone_error.setText("");
		 			
		 	}
		 });
		phone.setBounds(530, 167, 228, 32);
		contentPane.add(phone);
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
		genderLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		genderLabel.setBounds(440, 216, 80, 32);
		contentPane.add(genderLabel);
		
		male = new JRadioButton("Nam");
		male.setForeground(Color.GRAY);
		male.setFont(new Font("SansSerif", Font.PLAIN, 14));
		male.setBackground(Color.WHITE);
		male.setBounds(526, 222, 62, 21);
		male.setActionCommand("Nam");
		contentPane.add(male);
		
		female = new JRadioButton("Nữ");
		female.setForeground(Color.GRAY);
		female.setFont(new Font("SansSerif", Font.PLAIN, 14));
		female.setBackground(Color.WHITE);
		female.setBounds(616, 222, 62, 21);
		female.setActionCommand("Nữ");
		contentPane.add(female);
		
		other = new JRadioButton("Khác");
		other.setForeground(Color.GRAY);
		other.setFont(new Font("SansSerif", Font.PLAIN, 14));
		other.setBackground(Color.WHITE);
		other.setBounds(696, 222, 62, 21);
		other.setActionCommand("Khác");
		contentPane.add(other);
		
		gender = new ButtonGroup();
		gender.add(female);
		gender.add(male);
		gender.add(other);
		
		JButton addButton = new JButton("Thêm mới");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessage();
				
				if(validateAddUser() == true) {
					if(checkDuplicateUser() == false) {
						insertUser();
					}
				}
			}
		});
		addButton.setForeground(Color.WHITE);
		addButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		addButton.setBackground(Color.BLACK);
		addButton.setBounds(225, 369, 197, 32);
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
		cancelButton.setBounds(432, 369, 197, 32);
		contentPane.add(cancelButton);
		
		JLabel roleLabel = new JLabel("Vai trò:");
		roleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		roleLabel.setForeground(Color.GRAY);
		roleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		roleLabel.setBounds(69, 267, 71, 32);
		contentPane.add(roleLabel);
		
		role = new JComboBox();
		role.setModel(new DefaultComboBoxModel(new String[] {"Quản lý", "Nhân viên bán hàng", "Khách hàng"}));
		role.setForeground(new Color(169, 169, 169));
		role.setFont(new Font("SansSerif", Font.PLAIN, 14));
		role.setBackground(new Color(255, 255, 255));
		role.setBounds(145, 268, 228, 29);
		role.setBorder(null);
		contentPane.add(role);
		
		name_error = new JLabel();
		name_error.setForeground(Color.RED);
		name_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		name_error.setBounds(145, 151, 109, 21);
		contentPane.add(name_error);
		
		email_error = new JLabel();
		email_error.setForeground(Color.RED);
		email_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		email_error.setBounds(145, 200, 109, 21);
		contentPane.add(email_error);
		
		password_error = new JLabel();
		password_error.setForeground(Color.RED);
		password_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		password_error.setBounds(145, 247, 155, 21);
		contentPane.add(password_error);
		
		phone_error = new JLabel();
		phone_error.setForeground(Color.RED);
		phone_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		phone_error.setBounds(530, 200, 177, 21);
		contentPane.add(phone_error);

	}
	
	

	
	// insert value into user table
	public void insertUser() {
		String full_name = name.getText();
		String address = this.address.getText();
		String email = this.email.getText();
		String password = this.password.getText();
		String phone = this.phone.getText();
		String gender = null;
		String role_id = String.valueOf(role.getSelectedIndex() + 1);
		
		if (address.equals("Địa chỉ")) {
			address = null;
		}
		if (phone.equals("Điện thoại")) {
			phone = null;
		}
		if(this.gender.getSelection() != null)
			 gender = this.gender.getSelection().getActionCommand();
		
		try {
			Connection con = OracleConn.getConnection();
			String sql = 
				"insert into \"User\"(full_name, address, email, \"password\", gender, phone, role_id) values (?,?,?,?,?,?,?)";
			PreparedStatement prs = con.prepareStatement(sql);
			
			prs.setString(1, full_name);
			prs.setString(2, address);
			prs.setString(3, email);
			prs.setString(4, password);
			prs.setString(5, gender);
			prs.setString(6, phone);
			prs.setString(7, role_id);
			
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
	public boolean validateAddUser() {
		String full_name = name.getText();
		String password = this.password.getText();
		String email = this.email.getText();
		String phone = this.phone.getText();
		
		boolean check = true;
		//full name
		if (full_name.equals("Họ tên")) {
			name_error.setText("Yêu cầu nhập Họ tên.");
			check = false;
		}
		//email
		if (email.equals("Email")) {
			email_error.setText("Yêu cầu nhập Email.");
			check = false;
		}
		else if (!email.matches("^.+@.+\\..+$")) {
				email_error.setText("Email không hợp lệ.");
				check = false;
			}
		
		//password
		if (password.equals("Mật khẩu")) {
			password_error.setText("Yêu cầu nhập Mật khẩu.");
			check = false;
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
			String sql = "select * from \"User\" where email = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
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
		password_error.setText("");
		phone_error.setText("");
	}
	
	// to reset jtable after inserting
	public void resetTable() {
		users.clearTable();
		users.setUsersToTable();
	}
	
	public void setUsersPanel(Users users) {
		this.users = users;
	}
	
	
}
