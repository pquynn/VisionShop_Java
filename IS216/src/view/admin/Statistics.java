package view.admin;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class Statistics extends JPanel {


	public Statistics() {
		setBackground(new Color(255, 255, 255));
		setSize(1000, 600);
		setLayout(new BorderLayout(0, 0));
		
		JPanel content1 = new JPanel();
		content1.setBackground(new Color(255, 255, 255));
		add(content1, BorderLayout.NORTH);
		content1.setLayout(null);
		content1.setPreferredSize(new Dimension(100, 150));
		
		JLabel statistics_heading = new JLabel("Thống kê");
		statistics_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		statistics_heading.setBounds(27, 10, 305, 40);
		content1.add(statistics_heading);
		
		JPanel content2 = new JPanel();
		content2.setBackground(new Color(255, 255, 255));
		add(content2, BorderLayout.CENTER);
		content2.setLayout(new BorderLayout(0, 0));
		
		JPanel content2_west = new JPanel();
		content2_west.setLayout(null);
		content2_west.setBackground(Color.WHITE);
		content2.add(content2_west, BorderLayout.WEST);
		content2_west.setPreferredSize(new Dimension(25, 100));
		
		JPanel content2_south = new JPanel();
		content2_south.setLayout(null);
		content2_south.setBackground(Color.WHITE);
		content2.add(content2_south, BorderLayout.SOUTH);
		content2_south.setPreferredSize(new Dimension(80, 20));
		
		JPanel content2_east = new JPanel();
		content2_east.setLayout(null);
		content2_east.setBackground(Color.WHITE);
		content2.add(content2_east, BorderLayout.EAST);
		content2_east.setPreferredSize(new Dimension(25, 100));
		
		
		
		
	}
}
