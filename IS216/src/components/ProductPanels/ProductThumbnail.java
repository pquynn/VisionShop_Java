package components.ProductPanels;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.user.ProductDetail;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductThumbnail extends JPanel {
	private JLabel image;
	private JLabel price;
	private JLabel name;
	private int glasses_id;
/*public ProductThumbnail() {
		
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		image = new JLabel();
		image.setBackground(new Color(255, 255, 255));
		/*if(imagedata != null) {
		ImageIcon MyImage = new ImageIcon(imagedata);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImgIcon = new ImageIcon(newImg); 
		image.setIcon(newImgIcon);
		add(image, BorderLayout.CENTER);
		
		JPanel product_info = new JPanel();
		product_info.setBackground(new Color(255, 255, 255));
		add(product_info, BorderLayout.SOUTH);
		product_info.setLayout(null);
		product_info.setPreferredSize(new Dimension(100, 50));

		name = new JLabel();
		name.setText("Tên mắt kính");
		name.setBounds(10, 7, 187, 19);
		name.setForeground(new Color(0, 0, 0));
		name.setFont(new Font("SansSerif", Font.PLAIN, 14));
		product_info.add(name);
		
		price = new JLabel();
		price.setText("Giá");
		price.setBounds(15, 25, 36, 16);
		price.setFont(new Font("SansSerif", Font.PLAIN, 14));
		price.setEnabled(true);
		product_info.add(price);
		image.setLayout(new BorderLayout(0, 0));
		
		
		JPanel west = new JPanel();
		west.setBackground(new Color(255, 255, 255));
		add(west, BorderLayout.WEST);
		west.setPreferredSize(new Dimension(5, 100));
		
		JPanel east = new JPanel();
		east.setBackground(new Color(255, 255, 255));
		add(east, BorderLayout.EAST);
		east.setPreferredSize(new Dimension(5, 100));
		
		JPanel north = new JPanel();
		north.setBackground(new Color(255, 255, 255));
		add(north, BorderLayout.NORTH);
		north.setPreferredSize(new Dimension(100, 5));
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new ProductDetail().setVisible(true);
			}
			
			
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(new Color(192,192,192)));
			}
			
			public void mouseExited(MouseEvent e) {
				setBorder(null);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
	*/
	
	public ProductThumbnail(int vglasses_id, String vname , String vprice , byte[] imagedata) {
		
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		glasses_id = vglasses_id;
		
		image = new JLabel();
		image.setSize(180, 100);
		if(imagedata != null) {
		ImageIcon MyImage = new ImageIcon(imagedata);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImgIcon = new ImageIcon(newImg); 
		image.setIcon(newImgIcon);
		add(image, BorderLayout.CENTER);
		
		JPanel product_info = new JPanel();
		product_info.setBackground(new Color(255, 255, 255));
		add(product_info, BorderLayout.SOUTH);
		product_info.setLayout(null);
		product_info.setPreferredSize(new Dimension(100, 50));

		name = new JLabel();
		name.setText(vname);
		name.setBounds(15, 7, 300, 19);
		name.setForeground(new Color(0, 0, 0));
		name.setFont(new Font("SansSerif", Font.BOLD, 14));
		product_info.add(name);
		
		price = new JLabel();
		price.setText(vprice + " đ");
		price.setBounds(15, 25, 100, 16);
		price.setFont(new Font("SansSerif", Font.PLAIN, 14));
		price.setEnabled(true);
		product_info.add(price);
		image.setLayout(new BorderLayout(0, 0));
		
		
		JPanel west = new JPanel();
		west.setBackground(new Color(255, 255, 255));
		add(west, BorderLayout.WEST);
		west.setPreferredSize(new Dimension(5, 100));
		
		JPanel east = new JPanel();
		east.setBackground(new Color(255, 255, 255));
		add(east, BorderLayout.EAST);
		east.setPreferredSize(new Dimension(5, 100));
		
		JPanel north = new JPanel();
		north.setBackground(new Color(255, 255, 255));
		add(north, BorderLayout.NORTH);
		north.setPreferredSize(new Dimension(100, 5));
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
			
			
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(new Color(192,192,192)));
			}
			
			public void mouseExited(MouseEvent e) {
				setBorder(null);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
	
	}
}
