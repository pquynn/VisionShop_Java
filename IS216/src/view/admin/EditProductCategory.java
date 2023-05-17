package view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Connect.OracleConn;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import components.CustomJTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditProductCategory extends JFrame {

	private JPanel contentPane;
	private ProductCategory productCategory;
	private int id;
	private CustomJTextField txt_id;
	private CustomJTextField txt_name;
	
	private JLabel name_error;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProductCategory frame = new EditProductCategory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public EditProductCategory() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(560, 250, 566, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel product_category_heading = new JLabel("Thông tin loại sản phẩm");
		product_category_heading.setFont(new Font("SansSerif", Font.BOLD, 22));
		product_category_heading.setBounds(146, 36, 270, 40);
		contentPane.add(product_category_heading);
		
		JLabel id = new JLabel("ID:");
		id.setHorizontalAlignment(SwingConstants.RIGHT);
		id.setForeground(Color.GRAY);
		id.setFont(new Font("SansSerif", Font.PLAIN, 14));
		id.setBounds(129, 113, 38, 32);
		contentPane.add(id);
		
		txt_id = new CustomJTextField("Họ tên");
		txt_id.setEditable(false);
		txt_id.setText("id");
		txt_id.setBounds(196, 114, 173, 32);
		contentPane.add(txt_id);
		
		JLabel name = new JLabel("Tên:");
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		name.setForeground(Color.GRAY);
		name.setFont(new Font("SansSerif", Font.PLAIN, 14));
		name.setBounds(116, 157, 51, 32);
		contentPane.add(name);
		
		txt_name = new CustomJTextField("Email");
		txt_name.setText("Tên loại sản phẩm");
		txt_name.setBounds(196, 158, 173, 32);
		txt_name.setTypingStyle();
		contentPane.add(txt_name);
		txt_name.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(checkDuplicate()) {
					name_error.setText("Tên loại kính đã tồn tại");
				}
			}
			public void keyPressed(KeyEvent e) {
				name_error.setText("");
			}
		});
		
		
		JButton editButton = new JButton("Lưu thay đổi");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCategoryById();
			}
		});
		editButton.setForeground(Color.WHITE);
		editButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		editButton.setBackground(Color.BLACK);
		editButton.setBounds(112, 233, 153, 32);
		contentPane.add(editButton);
		
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
		cancelButton.setBounds(275, 233, 153, 32);
		contentPane.add(cancelButton);
		
		name_error = new JLabel();
		name_error.setForeground(Color.RED);
		name_error.setFont(new Font("SansSerif", Font.PLAIN, 10));
		name_error.setBounds(196, 189, 230, 21);
		contentPane.add(name_error);

	}
	
	//set category detail by id to frame
		public void setCategoryByID() {
			txt_id.setText(String.valueOf(id));
			try {
				Connection conn = OracleConn.getConnection();
				String sql = "select * from \"Category\" where category_id = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, String.valueOf(id));
				ResultSet rs = pst.executeQuery();
				
				while(rs.next())
					txt_name.setText(rs.getString("category_name"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//update user detail 
		public void updateCategoryById() {
			String name = txt_name.getText();
			
			try {
				Connection con = OracleConn.getConnection();
				String sql = 
					"update \"Category\" set category_name = ? where category_id = ?";
				PreparedStatement prs = con.prepareStatement(sql);
				
				prs.setString(1, name);
				prs.setInt(2, id);
				
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
		}
		
		//check duplicate category_name
		public boolean checkDuplicate() {
			String name = txt_name.getText();
			boolean isExist = false;
			try {
				Connection con = OracleConn.getConnection();
				String sql = "select * from \"Category\" where category_name = ? and category_id <> ?";
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
			productCategory.clearTable();
			productCategory.setCategoryToTable();
		}
		
		
		public void setCategoryPane(ProductCategory pc) {
			productCategory = pc;
		}
		
		public void setId(int id) {
			this.id = id;
		}
}
