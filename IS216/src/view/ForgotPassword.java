package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField oldPassword;
	private JTextField newPassword;
	private JTextField confirmPassword;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(350, 150, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel forgotPLabel = new JLabel("Lấy lại mật khẩu");
		forgotPLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
		forgotPLabel.setBounds(310, 63, 303, 55);
		contentPane.add(forgotPLabel);
		
		oldPassword = new JTextField();
		oldPassword.setText("Mật khẩu hiện tại");
		oldPassword.setForeground(new Color(211, 211, 211));
		oldPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		oldPassword.setColumns(10);
		oldPassword.setBounds(310, 142, 252, 32);
		contentPane.add(oldPassword);
		
		JCheckBox showPassword = new JCheckBox("Hiển thị mật khẩu");
		showPassword.setForeground(Color.LIGHT_GRAY);
		showPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		showPassword.setBackground(Color.WHITE);
		showPassword.setBounds(310, 264, 155, 21);
		contentPane.add(showPassword);
		
		JButton confirmButton = new JButton("Đồng ý");
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		confirmButton.setBackground(Color.BLACK);
		confirmButton.setBounds(310, 306, 252, 32);
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
		cancelButton.setBounds(310, 347, 252, 32);
		contentPane.add(cancelButton);
		
		newPassword = new JTextField();
		newPassword.setText("Mật khẩu mới");
		newPassword.setForeground(new Color(211, 211, 211));
		newPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		newPassword.setColumns(10);
		newPassword.setBounds(310, 184, 252, 32);
		contentPane.add(newPassword);
		
		confirmPassword = new JTextField();
		confirmPassword.setText("Nhập lại mật khẩu");
		confirmPassword.setForeground(new Color(211, 211, 211));
		confirmPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirmPassword.setColumns(10);
		confirmPassword.setBounds(310, 226, 252, 32);
		contentPane.add(confirmPassword);
	}

}
