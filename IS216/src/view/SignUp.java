package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Connect.OracleConn;
import components.CustomJTextField;
import view.user.Account;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUp() {
		ImageIcon frameIcon = new ImageIcon(Account.class.getResource("/assets/sunglasses.png"));
		setIconImage(frameIcon.getImage());
		setTitle("Cửa hàng mắt kính Vision");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(440, 180, 880, 550);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(128, 128, 128));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel signupLabel = new JLabel("Đăng ký");
		signupLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
		signupLabel.setBounds(362, 10, 185, 55);
		contentPane.add(signupLabel);
		
		name = new CustomJTextField("Họ tên"); 
		name.setLocation(326, 75);
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
		
		email = new CustomJTextField("Email");
		email.setLocation(326, 130);
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
		
		password = new JPasswordField("Mật khẩu");
		password.setLocation(326, 186);
		password.setForeground(new Color(192,192,192));
		password.setFont(new Font("SansSerif", Font.PLAIN, 14));
		password.setColumns(10);
		password.setSize(252, 32);
		password.setBorder(new LineBorder(new Color(211, 211, 211)));
		password.setCaretPosition(0);
		password.setEchoChar('\u0000');
		contentPane.add(password);
		password.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(password.getText().equals("Mật khẩu")) {
					password.setText("");
					password.requestFocus();
					password.setForeground(new Color(105,105,105));
					password.setEchoChar('•');
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(password.getText().length() == 0) {
					password.setForeground(new Color(192,192,192));
					password.setEchoChar('\u0000');
					password.setText("Mật khẩu");
				}
			}
		});
		
		address = new CustomJTextField("Địa chỉ");
		address.setLocation(326, 266);
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
		
		phone = new CustomJTextField("Điện thoại");
		phone.setLocation(326, 322);
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
		
		
		emailLabel = new JLabel("*Email:");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setForeground(new Color(128, 128, 128));
		emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		emailLabel.setBounds(199, 130, 122, 32);
		contentPane.add(emailLabel);
		
		passwordLabel = new JLabel("*Mật khẩu:");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setForeground(new Color(128, 128, 128));
		passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		passwordLabel.setBounds(199, 184, 122, 32);
		contentPane.add(passwordLabel);
		
		JCheckBox showPassword = new JCheckBox("Hiển thị mật khẩu");
		showPassword.setForeground(new Color(169, 169, 169));
		showPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		showPassword.setBackground(Color.WHITE);
		showPassword.setBounds(326, 220, 155, 21);
		contentPane.add(showPassword);
		showPassword.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(showPassword.isSelected() || password.getText().equals("Mật khẩu")) {
					password.setEchoChar('\u0000');
				}
				else {
					password.setEchoChar('•');
				}
			}
		});
		
		addressLabel = new JLabel("Địa chỉ: ");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setForeground(new Color(128, 128, 128));
		addressLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		addressLabel.setBounds(199, 264, 122, 32);
		contentPane.add(addressLabel);
		
		phoneLabel = new JLabel("Điện thoại:");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setForeground(new Color(128, 128, 128));
		phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		phoneLabel.setBounds(199, 322, 122, 32);
		contentPane.add(phoneLabel);
		
		genderLabel = new JLabel("Giới tính:");
		genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		genderLabel.setForeground(new Color(128, 128, 128));
		genderLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		genderLabel.setBounds(199, 370, 122, 32);
		contentPane.add(genderLabel);
		
		
		JLabel nameLabel = new JLabel("*Họ tên:");
		nameLabel.setForeground(new Color(128, 128, 128));
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nameLabel.setBounds(199, 75, 122, 32);
		contentPane.add(nameLabel);
		
		cancelButton = new JButton("Hủy");
		cancelButton.setForeground(new Color(0, 0, 0));
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		cancelButton.setBackground(new Color(255, 255, 255));
		cancelButton.setBounds(326, 454, 252, 32);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		
		male = new JRadioButton("Nam");
		male.setForeground(new Color(128, 128, 128));
		male.setFont(new Font("SansSerif", Font.PLAIN, 14));
		male.setBackground(Color.WHITE);
		male.setBounds(326, 377, 62, 21);
		male.setActionCommand("Nam");
		contentPane.add(male);
		
		female = new JRadioButton("Nữ");
		female.setForeground(new Color(128, 128, 128));
		female.setFont(new Font("SansSerif", Font.PLAIN, 14));
		female.setBackground(Color.WHITE);
		female.setBounds(411, 377, 62, 21);
		female.setActionCommand("Nữ");
		contentPane.add(female);
		
		other = new JRadioButton("Khác");
		other.setForeground(new Color(128, 128, 128));
		other.setFont(new Font("SansSerif", Font.PLAIN, 14));
		other.setBackground(Color.WHITE);
		other.setBounds(485, 377, 62, 21);
		other.setActionCommand("Khác");
		contentPane.add(other);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(male);
		genderGroup.add(female);
		genderGroup.add(other);
		
		name_error = new JLabel();
		name_error.setForeground(new Color(255, 0, 0));
		name_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		name_error.setBounds(326, 106, 109, 21);
		contentPane.add(name_error);
		
		email_error = new JLabel();
		email_error.setForeground(Color.RED);
		email_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		email_error.setBounds(326, 162, 109, 21);
		contentPane.add(email_error);
		
		password_error = new JLabel();
		password_error.setForeground(Color.RED);
		password_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		password_error.setBounds(326, 238, 155, 21);
		contentPane.add(password_error);
		
		phone_error = new JLabel();
		phone_error.setForeground(Color.RED);
		phone_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		phone_error.setBounds(326, 355, 177, 21);
		contentPane.add(phone_error);

		signupButton = new JButton("Đăng ký");
		signupButton.setForeground(Color.WHITE);
		signupButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		signupButton.setBackground(Color.BLACK);
		signupButton.setBounds(326, 415, 252, 32);
		contentPane.add(signupButton);
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessage();
				
				if(validateSignup() == true) {
					if(checkDuplicateUser() == false) {
						insertSignUpDetail();
					}
				}
			}
		});
	}
	
	// insert value into user table
	public void insertSignUpDetail() {
		String full_name = name.getText();
		String address = this.address.getText();
		String email = this.email.getText();
		String password = this.password.getText();
		String phone = this.phone.getText();
		String gender = null;
		
		if (address.equals("Địa chỉ")) {
			this.address.setText("");
		}
		if (phone.equals("Điện thoại")) {
			this.phone.setText("");
		}
		if(genderGroup.getSelection() != null)
			 gender = genderGroup.getSelection().getActionCommand();
		
		try {
			Connection con = OracleConn.getConnection();
			String sql = 
				"insert into \"User\"(role_id, full_name, address, email, \"password\", gender, phone) values (\'3\',?,?,?,?,?,?)";
			PreparedStatement prs = con.prepareStatement(sql);
			
			prs.setString(1, full_name);
			prs.setString(2, address);
			prs.setString(3, email);
			prs.setString(4, password);
			prs.setString(5, gender);
			prs.setString(6, phone);
			
			int RowCount = prs.executeUpdate();
			if(RowCount > 0) {
				JOptionPane.showMessageDialog(null, "Đăng ký thành công");
				new Login().setVisible(true);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Đăng ký không thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//validation
	public boolean validateSignup() {
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
		else {
			if (!email.matches("^.+@.+\\..+$")) {
				email_error.setText("Email không hợp lệ.");
				check = false;
			}
		}
		//password
		if (password.equals("Mật khẩu")) {
			password_error.setText("Yêu cầu nhập Mật khẩu.");
			check = false;
		}
		//phone
		if((phone.length() != 10 || !phone.matches("[0-9]+"))&& !phone.equals("Điện thoại")) {
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
	
	private JPanel contentPane;
	private CustomJTextField name;
	private CustomJTextField email;
	private JPasswordField password;
	private CustomJTextField address;
	private CustomJTextField phone;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JLabel addressLabel;
	private JLabel phoneLabel;
	private JLabel genderLabel;
	private JRadioButton male;
	private JRadioButton female;
	private JRadioButton other;
	private ButtonGroup genderGroup;
	private JButton signupButton;
	private JButton cancelButton;
	private JLabel name_error;
	private JLabel email_error;
	private JLabel password_error;
	private JLabel phone_error;
}
