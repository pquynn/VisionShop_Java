package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connect.OracleConn;
import Email.JavaMail;
import components.CustomJTextField;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private CustomJTextField email;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ForgotPassword() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(560, 250, 566, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel forgotPLabel = new JLabel("Quên mật khẩu");
		forgotPLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		forgotPLabel.setBounds(175, 49, 303, 55);
		contentPane.add(forgotPLabel);
		
		email = new CustomJTextField("Nhập email đã đăng ký");
		email.setBounds(151, 131, 266, 32);
		contentPane.add(email);
		email.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(email.getText().equals("Nhập email đã đăng ký")) {
					email.setText("");
					email.requestFocus();
					email.setTypingStyle();
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(email.getText().length() == 0) {
					email.setDefaultStyle();
					email.setText("Nhập email đã đăng ký");
				}
			}
		});
		
		JButton confirmButton = new JButton("Lấy lại mật khẩu");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw = getPasswordByEmail(email.getText());
				if(pw == null)
					JOptionPane.showMessageDialog(null, "Email này chưa được đăng ký.");
				else {
					String to = email.getText();
				    String subject = "[Vision] Lấy lại mật khẩu";
				    String msg ="Vui lòng nhập mật khẩu " + pw + " để đăng nhập vào Vision. \n" + "Trân trọng!";
					JavaMail mail = new JavaMail(to, subject, msg);
					mail.sendEmail();
					
				    JOptionPane.showMessageDialog(null, "Mật khẩu đã được gửi qua email");
				    dispose();
				}
			}
		});
		
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		confirmButton.setBackground(Color.BLACK);
		confirmButton.setBounds(151, 190, 266, 32);
		contentPane.add(confirmButton);
		
		JButton cancelButton = new JButton("Hủy");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận Huỷ thao tác", "Hủy", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
				dispose();
				}
			}
		});
		cancelButton.setForeground(new Color(0, 0, 0));
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		cancelButton.setBackground(new Color(255, 255, 255));
		cancelButton.setBounds(151, 232, 266, 32);
		contentPane.add(cancelButton);
	}
	
	public String getPasswordByEmail(String email) {
		String pw = null;
		try {
			Connection conn = OracleConn.getConnection();
			String sql = "select \"password\" from \"User\" where email = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				pw = rs.getString("\"password\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pw;
	}
}
