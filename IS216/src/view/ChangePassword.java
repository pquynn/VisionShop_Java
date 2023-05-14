package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField old_password;
	private JPasswordField new_password;
	private JPasswordField confirm_password;
	private JCheckBox showPassword;
	private JLabel old_pw_error;
	private JLabel new_pw_error;
	private JLabel confirm_ps_error;

	private Account account;
	private int id;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChangePassword() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(350, 150, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel changePLabel = new JLabel("Đổi mật khẩu");
		changePLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
		changePLabel.setBounds(318, 64, 240, 55);
		contentPane.add(changePLabel);
		
		old_password = new JPasswordField("Mật khẩu hiện tại");
		old_password.setLocation(310, 150);
		old_password.setForeground(new Color(192,192,192));
		old_password.setFont(new Font("SansSerif", Font.PLAIN, 14));
		old_password.setColumns(10);
		old_password.setSize(252, 32);
		old_password.setBorder(new LineBorder(new Color(211, 211, 211)));
		old_password.setCaretPosition(0);
		old_password.setEchoChar('\u0000');
		contentPane.add(old_password);
		old_password.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(old_password.getText().equals("Mật khẩu hiện tại")) {
					old_password.setText("");
					old_password.requestFocus();
					old_password.setForeground(new Color(105,105,105));
					old_password.setEchoChar('•');
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(old_password.getText().length() == 0) {
					old_password.setForeground(new Color(192,192,192));
					old_password.setEchoChar('\u0000');
					old_password.setText("Mật khẩu hiện tại");
				}
			}
		});
		
		new_password = new JPasswordField("Mật khẩu mới");
		new_password.setLocation(310,203);
		new_password.setForeground(new Color(192,192,192));
		new_password.setFont(new Font("SansSerif", Font.PLAIN, 14));
		new_password.setColumns(10);
		new_password.setSize(252, 32);
		new_password.setBorder(new LineBorder(new Color(211, 211, 211)));
		new_password.setCaretPosition(0);
		new_password.setEchoChar('\u0000');
		contentPane.add(new_password);
		
		new_password.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(new_password.getText().equals("Mật khẩu mới")) {
					new_password.setText("");
					new_password.requestFocus();
					new_password.setForeground(new Color(105,105,105));
					new_password.setEchoChar('•');
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(new_password.getText().length() == 0) {
					new_password.setForeground(new Color(192,192,192));
					new_password.setEchoChar('\u0000');
					new_password.setText("Mật khẩu mới");
				}
			}
		});
		
		confirm_password = new JPasswordField("Nhập lại mật khẩu");
		confirm_password.setLocation(310, 254);
		confirm_password.setForeground(new Color(192,192,192));
		confirm_password.setFont(new Font("SansSerif", Font.PLAIN, 14));
		confirm_password.setColumns(10);
		confirm_password.setSize(252, 32);
		confirm_password.setBorder(new LineBorder(new Color(211, 211, 211)));
		confirm_password.setCaretPosition(0);
		confirm_password.setEchoChar('\u0000');
		contentPane.add(confirm_password);
		confirm_password.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(confirm_password.getText().equals("Nhập lại mật khẩu")) {
					confirm_password.setText("");
					confirm_password.requestFocus();
					confirm_password.setForeground(new Color(105,105,105));
					confirm_password.setEchoChar('•');
				}
			}
			
			public void focusLost(FocusEvent e) {
				if(confirm_password.getText().length() == 0) {
					confirm_password.setForeground(new Color(192,192,192));
					confirm_password.setEchoChar('\u0000');
					confirm_password.setText("Nhập lại mật khẩu");
				}
			}
		});
		
		showPassword = new JCheckBox("Hiển thị mật khẩu");
		showPassword.setForeground(new Color(169, 169, 169));
		showPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		showPassword.setBackground(Color.WHITE);
		showPassword.setBounds(310, 287, 155, 21);
		contentPane.add(showPassword);
		showPassword.addChangeListener(new ChangeListener() { /// sua lai cho nay di, no bi sai roi
			public void stateChanged(ChangeEvent e) {
				if(showPassword.isSelected() 
						|| old_password.getText().equals("Mật khẩu hiện tại")
						|| new_password.getText().equals("Mật khẩu mới")
						|| confirm_password.getText().equals("Nhập lại mật khẩu")) {
					old_password.setEchoChar('\u0000');
					new_password.setEchoChar('\u0000');
					confirm_password.setEchoChar('\u0000');
				}
				else {
					old_password.setEchoChar('•');
					new_password.setEchoChar('•');
					confirm_password.setEchoChar('•');
				}
			}
		});
		
		JButton confirmButton = new JButton("Đồng ý");
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		confirmButton.setBackground(Color.BLACK);
		confirmButton.setBounds(310, 324, 252, 32);
		contentPane.add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessage();
				if(validateChangPW()) {
					updatePassword();
				}
			}
		});
		
		JButton cancelButton = new JButton("Hủy");
		cancelButton.setForeground(new Color(0, 0, 0));
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		cancelButton.setBackground(new Color(255, 255, 255));
		cancelButton.setBounds(310, 364, 252, 32);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Xác nhận Huỷ cập nhật", "Hủy", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION){
				dispose();
				}
			}
		});
		
		old_pw_error = new JLabel();
		old_pw_error.setForeground(Color.RED);
		old_pw_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		old_pw_error.setBounds(310, 180, 285, 21);
		contentPane.add(old_pw_error);
		
		new_pw_error = new JLabel();
		new_pw_error.setForeground(Color.RED);
		new_pw_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		new_pw_error.setBounds(310, 236, 285, 21);
		contentPane.add(new_pw_error);
		
		confirm_ps_error = new JLabel();
		confirm_ps_error.setForeground(Color.RED);
		confirm_ps_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		confirm_ps_error.setBounds(310, 305, 314, 21);
		contentPane.add(confirm_ps_error);
		
	}
	
	
	//save change
	public void updatePassword() {
		String newpw = new_password.getText();
		
		try {
			Connection con = OracleConn.getConnection();
			String sql = "update \"User\" set \"password\" = ? where user_id =?"; 
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, newpw);
			pst.setInt(2, id);
			
			int rowcount = pst.executeUpdate();
			if(rowcount > 0) {
				JOptionPane.showMessageDialog(null, "Cập nhật thành công");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//validate
	public boolean validateChangPW() {
		boolean isValid = true;
		String oldpw = old_password.getText();
		String newpw = new_password.getText();
		String confirmpw = confirm_password.getText();
		
		if(oldpw.equals("Mật khẩu hiện tại")) {
			old_pw_error.setText("Yêu cầu nhập Mật khẩu hiện tại.");
			isValid = false;
		}
		if(newpw.equals("Mật khẩu mới")) {
			new_pw_error.setText("Yêu cầu nhập Mật khẩu mới.");
			isValid = false;
		}
		if(confirmpw.equals("Nhập lại mật khẩu") || !confirmpw.equals(newpw)) {
			confirm_ps_error.setText("Mật khẩu xác nhận không trùng khớp");
			isValid = false;
		}
		
		return isValid;
	}
	
	
	//clear validation message
	public void clearMessage() {
		old_pw_error.setText("");
		new_pw_error.setText("");
		confirm_ps_error.setText("");
	}
	//set user id
	public void setId(int id) {
		this.id = id;
	}
	//set account panel that opens this frame
	public void setAccountPanel(Account account) {
		this.account = account;
	}
	
}




