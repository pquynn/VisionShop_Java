package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import components.CustomJTextField;
import view.user.Account;
import view.user.HomePage;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import Connect.OracleConn;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {

	private HomePage homePage;
	private JPanel contentPane;
	private CustomJTextField email;
	private JPasswordField password;
	
	private JButton loginButton;
	private JCheckBox showPassword;
	private JLabel email_error;
	private JLabel password_error;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Login() {
		ImageIcon frameIcon = new ImageIcon(Account.class.getResource("/assets/sunglasses.png"));
		setIconImage(frameIcon.getImage());
		setTitle("Cửa hàng mắt kính Vision");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(440, 180, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginLabel = new JLabel("Đăng nhập");
		loginLabel.setForeground(new Color(0, 0, 0));
		loginLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
		loginLabel.setBounds(333, 84, 185, 55);
		contentPane.add(loginLabel);
		
		email = new CustomJTextField("Email");
		email.setLocation(302, 159);
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
			}
		});
		
		password = new JPasswordField("Mật khẩu");
		password.setLocation(302, 211);
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
		
		
		showPassword = new JCheckBox("Hiển thị mật khẩu");
		showPassword.setForeground(new Color(169, 169, 169));
		showPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		showPassword.setBackground(Color.WHITE);
		showPassword.setBounds(302, 246, 155, 21);
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
		
		loginButton = new JButton("Đăng nhập");
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		loginButton.setBackground(Color.BLACK);
		loginButton.setBounds(302, 283, 252, 32);
		contentPane.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email_error.setText("");
				password_error.setText("");
				if(validateLogin()) {
					verify();
				}
			}
		});
		
		JLabel signup = new JLabel("Đăng ký ngay");
		signup.setForeground(new Color(169, 169, 169));
		signup.setHorizontalAlignment(SwingConstants.RIGHT);
		signup.setFont(new Font("SansSerif", Font.PLAIN, 14));
		signup.setBounds(432, 324, 122, 32);
		contentPane.add(signup);
		signup.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new SignUp().setVisible(true);;
				dispose();
			}
			
			public void mouseEntered(MouseEvent e) {
				signup.setForeground(new Color(105,105,105));
			}
			
			public void mouseExited(MouseEvent e) {
				signup.setForeground(new Color(169, 169, 169));
			}
			
		});
		
		JLabel forgetPassword = new JLabel("Quên mật khẩu?");
		forgetPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		forgetPassword.setForeground(new Color(169, 169, 169));
		forgetPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		forgetPassword.setBounds(432, 342, 122, 32);
		contentPane.add(forgetPassword);
		
		email_error = new JLabel();
		email_error.setForeground(Color.RED);
		email_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		email_error.setBounds(302, 190, 109, 21);
		contentPane.add(email_error);
		
		password_error = new JLabel();
		password_error.setForeground(Color.RED);
		password_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		password_error.setBounds(302, 261, 166, 21);
		contentPane.add(password_error);
		
		JLabel lblChoMngBn = new JLabel("Chào mừng trở lại với Vision,");
		lblChoMngBn.setForeground(Color.BLACK);
		lblChoMngBn.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblChoMngBn.setBounds(260, 46, 381, 55);
		contentPane.add(lblChoMngBn);
		forgetPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new ForgotPassword().setVisible(true);
			}
			
			public void mouseEntered(MouseEvent e) {
				forgetPassword.setForeground(new Color(105,105,105));
			}
			
			public void mouseExited(MouseEvent e) {
				forgetPassword.setForeground(new Color(169, 169, 169));
			}
			
		});

	}
	
	//verify account
	public void verify() {
		String email = this.email.getText();
		String password = this.password.getText();
		
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select user_id from \"User\" where email = ? and \"password\" = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, email);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				int user_id = rs.getInt("user_id");
				homePage = new HomePage(user_id);
				homePage.setVisible(true);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Email hoặc mật khẩu không hợp lệ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//validate login
	public boolean validateLogin() {
		boolean isValid = true;
		String email = this.email.getText();
		String password = this.password.getText();
		
		if(email.equals("Email")) {
			email_error.setText("Yêu cầu nhập Email.");
			isValid = false;
		}
		if(password.equals("Mật khẩu")) {
			password_error.setText("Yêu cầu nhập Mật khẩu.");
			isValid = false;
		}	
		
		return isValid;
	}
}
