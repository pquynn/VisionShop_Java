package view.admin;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import Connect.OracleConn;

import org.jfree.chart.plot.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;


public class Statistics extends JPanel {

	private JPanel piechart_pane;
	private JPanel linechart_pane;
	private JLabel top1;
	private JLabel top2;
	private JLabel top3;
	private JLabel top4;
	private JLabel top5;
	private JLabel revenue_thismonth;
	private JLabel customers;
	private JLabel glasses;
	private JLabel comparision;
	private JLabel waiting_order;
	
	public Statistics instanceStatistics;
	
	public Statistics() {
		setBackground(new Color(255, 255, 255));
		setSize(965, 600);
		setLayout(new BorderLayout(0, 0));
		
		instanceStatistics = this;
		
		JPanel content1 = new JPanel();
		content1.setBackground(new Color(255, 255, 255));
		add(content1, BorderLayout.NORTH);
		content1.setLayout(null);
		content1.setPreferredSize(new Dimension(100, 200));
		
		JLabel statistics_heading = new JLabel("Thống kê");
		statistics_heading.setFont(new Font("SansSerif", Font.BOLD, 24));
		statistics_heading.setBounds(27, 10, 305, 40);
		content1.add(statistics_heading);
		
		JPanel revenue_pane = new JPanel();
		revenue_pane.setBorder(new LineBorder(new Color(211, 211, 211)));
		revenue_pane.setBackground(new Color(255, 255, 255));
		revenue_pane.setBounds(30, 60, 200, 98);
		content1.add(revenue_pane);
		revenue_pane.setLayout(null);
		
		JLabel lb_revenue = new JLabel("Doanh thu tháng hiện tại:");
		lb_revenue.setHorizontalAlignment(SwingConstants.CENTER);
		lb_revenue.setFont(new Font("SansSerif", Font.BOLD, 14));
		lb_revenue.setForeground(new Color(169, 169, 169));
		lb_revenue.setBounds(0, 10, 200, 23);
		revenue_pane.add(lb_revenue);
		
		 revenue_thismonth = new JLabel("6 000 000");
		revenue_thismonth.setHorizontalAlignment(SwingConstants.CENTER);
		revenue_thismonth.setForeground(new Color(0, 0, 0));
		revenue_thismonth.setFont(new Font("SansSerif", Font.BOLD, 32));
		revenue_thismonth.setBounds(0, 38, 190, 39);
		revenue_pane.add(revenue_thismonth);
		
		comparision = new JLabel("Tăng 50000");
		comparision.setHorizontalAlignment(SwingConstants.RIGHT);
		comparision.setForeground(new Color(46, 139, 87));
		comparision.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comparision.setBounds(0, 75, 193, 23);
		revenue_pane.add(comparision);
		
		JLabel dvi = new JLabel("đ");
		dvi.setBounds(165, 38, 35, 39);
		revenue_pane.add(dvi);
		dvi.setHorizontalAlignment(SwingConstants.CENTER);
		dvi.setForeground(Color.BLACK);
		dvi.setFont(new Font("SansSerif", Font.BOLD, 32));
		
		JPanel customer_pane = new JPanel();
		customer_pane.setLayout(null);
		customer_pane.setBorder(new LineBorder(new Color(211, 211, 211)));
		customer_pane.setBackground(Color.WHITE);
		customer_pane.setBounds(460, 60, 200, 98);
		content1.add(customer_pane);
		
		JLabel lb_customers = new JLabel("Số khách hàng đã đăng ký:");
		lb_customers.setHorizontalAlignment(SwingConstants.CENTER);
		lb_customers.setForeground(new Color(169, 169, 169));
		lb_customers.setFont(new Font("SansSerif", Font.BOLD, 14));
		lb_customers.setBounds(0, 10, 200, 23);
		customer_pane.add(lb_customers);
		
		 customers = new JLabel("600");
		customers.setHorizontalAlignment(SwingConstants.CENTER);
		customers.setForeground(new Color(0, 0, 0));
		customers.setFont(new Font("SansSerif", Font.BOLD, 32));
		customers.setBounds(0, 38, 200, 39);
		customer_pane.add(customers);
		
		JPanel glasses_pane = new JPanel();
		glasses_pane.setLayout(null);
		glasses_pane.setBorder(new LineBorder(new Color(211, 211, 211)));
		glasses_pane.setBackground(Color.WHITE);
		glasses_pane.setBounds(675, 60, 200, 98);
		content1.add(glasses_pane);
		
		JLabel lb_glasses = new JLabel("Tổng số mắt kính hiện có:");
		lb_glasses.setHorizontalAlignment(SwingConstants.CENTER);
		lb_glasses.setForeground(new Color(169, 169, 169));
		lb_glasses.setFont(new Font("SansSerif", Font.BOLD, 14));
		lb_glasses.setBounds(0, 10, 200, 23);
		glasses_pane.add(lb_glasses);
		
		 glasses = new JLabel("150");
		glasses.setHorizontalAlignment(SwingConstants.CENTER);
		glasses.setForeground(new Color(0, 0, 0));
		glasses.setFont(new Font("SansSerif", Font.BOLD, 32));
		glasses.setBounds(0, 38, 200, 39);
		glasses_pane.add(glasses);
		
		JPanel order_pane = new JPanel();
		order_pane.setLayout(null);
		order_pane.setBorder(new LineBorder(new Color(211, 211, 211)));
		order_pane.setBackground(Color.WHITE);
		order_pane.setBounds(245, 60, 200, 98);
		content1.add(order_pane);
		
		JLabel lb_order = new JLabel("Đơn hàng chưa hoàn thành:");
		lb_order.setHorizontalAlignment(SwingConstants.CENTER);
		lb_order.setForeground(new Color(169, 169, 169));
		lb_order.setFont(new Font("SansSerif", Font.BOLD, 14));
		lb_order.setBounds(0, 10, 200, 23);
		order_pane.add(lb_order);
		
		waiting_order = new JLabel("600");
		waiting_order.setHorizontalAlignment(SwingConstants.CENTER);
		waiting_order.setForeground(Color.BLACK);
		waiting_order.setFont(new Font("SansSerif", Font.BOLD, 32));
		waiting_order.setBounds(0, 38, 200, 39);
		order_pane.add(waiting_order);
		
		JPanel content2 = new JPanel();
		content2.setBackground(new Color(255, 255, 255));
		add(content2, BorderLayout.CENTER);
		content2.setLayout(null);
		
		piechart_pane = new JPanel();
		piechart_pane.setBackground(new Color(255, 255, 255));
		piechart_pane.setBounds(242, 54, 291, 246);
		content2.add(piechart_pane);
		
		 linechart_pane = new JPanel();
		 linechart_pane.setBackground(new Color(255, 255, 255));
		linechart_pane.setBounds(530, 41, 373, 281);
		content2.add(linechart_pane);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(105, 105, 105)));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(40, 25, 190, 275);
		content2.add(panel_1_1);
		
		JLabel lb_top5glasses = new JLabel("Top 5 mắt kính bán chạy");
		lb_top5glasses.setHorizontalAlignment(SwingConstants.CENTER);
		lb_top5glasses.setForeground(new Color(0, 0, 0));
		lb_top5glasses.setFont(new Font("SansSerif", Font.BOLD, 14));
		lb_top5glasses.setBounds(0, 10, 190, 23);
		panel_1_1.add(lb_top5glasses);
		
		 top1 = new JLabel("Kính mát hiệu abc");
		top1.setForeground(new Color(0, 0, 0));
		top1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		top1.setBounds(29, 67, 161, 39);
		panel_1_1.add(top1);
		
		JLabel lb_top5glasses1 = new JLabel("tháng hiện tại:");
		lb_top5glasses1.setHorizontalAlignment(SwingConstants.CENTER);
		lb_top5glasses1.setForeground(new Color(0, 0, 0));
		lb_top5glasses1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lb_top5glasses1.setBounds(0, 34, 190, 23);
		panel_1_1.add(lb_top5glasses1);
		
		JLabel lb1 = new JLabel("1. ");
		lb1.setForeground(new Color(0, 0, 0));
		lb1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lb1.setBounds(10, 67, 27, 39);
		panel_1_1.add(lb1);
		
		JLabel lb2 = new JLabel("2. ");
		lb2.setForeground(new Color(0, 0, 0));
		lb2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lb2.setBounds(10, 103, 27, 39);
		panel_1_1.add(lb2);
		
		JLabel lb3 = new JLabel("3. ");
		lb3.setForeground(new Color(0, 0, 0));
		lb3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lb3.setBounds(10, 139, 27, 39);
		panel_1_1.add(lb3);
		
		JLabel lb4 = new JLabel("4. ");
		lb4.setForeground(new Color(0, 0, 0));
		lb4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lb4.setBounds(10, 176, 27, 39);
		panel_1_1.add(lb4);
		
		JLabel lb5 = new JLabel("5. ");
		lb5.setForeground(new Color(0, 0, 0));
		lb5.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lb5.setBounds(10, 214, 27, 39);
		panel_1_1.add(lb5);
		
		 top2 = new JLabel("Kính mát hiệu abc");
		top2.setForeground(new Color(0, 0, 0));
		top2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		top2.setBounds(29, 103, 161, 39);
		panel_1_1.add(top2);
		
		 top3 = new JLabel("Kính mát hiệu abc");
		top3.setForeground(new Color(0, 0, 0));
		top3.setFont(new Font("SansSerif", Font.PLAIN, 14));
		top3.setBounds(29, 139, 161, 39);
		panel_1_1.add(top3);
		
		 top4 = new JLabel("Kính mát hiệu abc");
		top4.setForeground(new Color(0, 0, 0));
		top4.setFont(new Font("SansSerif", Font.PLAIN, 14));
		top4.setBounds(29, 176, 161, 39);
		panel_1_1.add(top4);
		
		 top5 = new JLabel("Kính mát hiệu abc");
		top5.setForeground(new Color(0, 0, 0));
		top5.setFont(new Font("SansSerif", Font.PLAIN, 14));
		top5.setBounds(29, 214, 161, 39);
		panel_1_1.add(top5);
		
		JLabel lb_glasses_bycategory = new JLabel("Số mắt kính bán theo từng loại:");
		lb_glasses_bycategory.setHorizontalAlignment(SwingConstants.CENTER);
		lb_glasses_bycategory.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lb_glasses_bycategory.setBounds(240, 10, 274, 38);
		content2.add(lb_glasses_bycategory);
		
		JLabel lb_year_revenue = new JLabel("Doanh thu theo năm hiện tại:");
		lb_year_revenue.setHorizontalAlignment(SwingConstants.CENTER);
		lb_year_revenue.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lb_year_revenue.setBounds(525, 10, 360, 38);
		content2.add(lb_year_revenue);
		
		displayDetail();
	}
	
	//set this month revenue
	public void setMonthRevenue() {
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select sum(total_money) from \"Order\" where extract(year from updated_at) = extract(year from sysdate)"
					+ " and extract(month from updated_at) = extract(month from sysdate) and \"order_state\" = 'Đã thanh toán'";
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			
			if(rs.next()) {
				revenue_thismonth.setText(String.valueOf(rs.getInt(1)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// set compare this month revenue to last month revenue
	public void compareRevenue() {
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select sum(total_money) from \"Order\" where \"order_state\" = 'Đã thanh toán' and"
					+ "((extract(year from updated_at) = extract(year from sysdate) and extract(month from updated_at) = extract(month from sysdate) - 1)"
					+ "or (extract(year from updated_at) = extract(year from sysdate) - 1 and extract(month from updated_at)=12 and extract(month from sysdate)=1))";
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			
			if(rs.next()) {
				int change_revenue = Integer.parseInt(revenue_thismonth.getText()) - rs.getInt(1);
				if(change_revenue > 0)
					this.comparision.setText("Tăng " + String.valueOf(Math.abs(change_revenue)) + " đ so với tháng trước");
				else if(change_revenue < 0)
					this.comparision.setText("Giảm " + String.valueOf(Math.abs(change_revenue)) + " đ so với tháng trước");
				else
				this.comparision.setText("Bằng tháng trước");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//set the number of orders have order state is 'cho giao', 'dang giao'
	public void setNumbOfOrders() {
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select count(*) from \"Order\" where \"order_state\" in ('Chờ giao hàng', 'Đang giao hàng')";
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			
			if(rs.next()) {
				waiting_order.setText(String.valueOf(rs.getInt(1)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//set the number of customers
	public void setNumbOfCustomers() {
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select count(*) from \"User\" where role_id = 3";
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			
			if(rs.next()) {
				customers.setText(String.valueOf(rs.getInt(1)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//set the number of glasses
	public void setNumbOfGlasses() {
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select count(*) from Glasses";
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			
			if(rs.next()) {
				glasses.setText(String.valueOf(rs.getInt(1)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//set the top 5 glasses
	public void setTop5Glassees() {
	
		String [] glasses_name = new String[5];
		
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select glasses_name from Glasses where glasses_id in"
					+ "(select glasses_id from Order_detail where order_id in(select order_id from \"Order\" where \"order_state\" = 'Đã thanh toán')"
					+ " group by glasses_id order by sum(quantity) desc fetch first 5 rows only)"; 
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			int i = 0;
			while(rs.next() && i<5) {
				glasses_name[i] = rs.getString(1);
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		top1.setText(glasses_name[0]);
		top2.setText(glasses_name[1]);
		top3.setText(glasses_name[2]);
		top4.setText(glasses_name[3]);
		top5.setText(glasses_name[4]);
	}
	
	//get glasses category that has the number of glasses were sold
	public ArrayList<Object[]> getGlassesCategory(){
		ArrayList<Object[]> dataset = new ArrayList<Object[]>();
		
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select category_name, \"quanty\" from \"Category\" left join "
					+ "    (select category_id, sum(Order_detail.quantity) as \"quanty\" "
					+ "		from Glasses, Order_detail "
					+ "		where Glasses.glasses_id = Order_detail.glasses_id"
					+ "		and order_id in (select order_id from \"Order\" where \"order_state\" = 'Đã thanh toán')"
					+ "		group by category_id) \"A\""
					+ "on \"Category\".category_id = \"A\".category_id";
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			
			while(rs.next()) {
				Object[] row = new Object[2];
				row[0] = rs.getString(1);
				row[1] = rs.getInt(2);
				dataset.add(row);
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataset;
	}
	
	// method to show piechart
	public void showPieChart(){
	        
	        //create dataset
	      DefaultPieDataset barDataset = new DefaultPieDataset( );
	      ArrayList<Object[]> dataset = getGlassesCategory();
	    
	        for(Object[] i : dataset) {
	        	barDataset.setValue((Comparable) i[0],(Number) i[1]);
	        	
	        }
	      //create chart
		   JFreeChart piechart = ChartFactory.createPieChart("",barDataset, false,true,false);//explain
		   
		   PiePlot piePlot =(PiePlot) piechart.getPlot();
		   //changing pie chart blocks colors
		   
		   for(Object[] i : dataset) {
			   double hue = Math.random();
			   int rgb = Color.HSBtoRGB((float)hue,0.5f,0.5f);
			   Color color = new Color(rgb);
			   piePlot.setSectionPaint((Comparable) i[0], color);
		   }
		   piePlot.setBackgroundPaint(Color.white);
		   
		    //create chartPanel to display chart(graph)
		    ChartPanel barChartPanel = new ChartPanel(piechart);
		    barChartPanel.setDomainZoomable(true);
		    barChartPanel.setPreferredSize(new Dimension(270, 220));
		   
		    piechart_pane.removeAll();
		    piechart_pane.add(barChartPanel, BorderLayout.CENTER);
		    piechart_pane.validate();
	   }

	
	//get revenue by year
	public int[] getRevenueByYear(){
		int[] revenue = new int[12];
		try {
			Connection con = OracleConn.getConnection();
			String sql = "select sum(total_money), extract(month from updated_at) from \"Order\" "
					+ "where extract(year from updated_at) = extract(year from sysdate) "
					+ "and \"order_state\" = 'Đã thanh toán'"
					+ "group by extract(month from updated_at) order by extract(month from updated_at)"; 
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			int i = 0; 
			while(rs.next() && i<12) {
				while(i + 1 != rs.getInt(2)) {
					revenue[i] = 0;
					i++;
				}
				revenue[i] = rs.getInt(1);
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return revenue;
	}
	
	//method to show linechart
	public void showLineChart(){
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int revenue[] = getRevenueByYear();
        
        for(int i = 0; i<12; i++) {
        	dataset.setValue((Number) revenue[i], "", i+1);
        }
    
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("","Tháng","Doanh thu (đồng)", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
        //create plot object
         CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
       // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        
        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204,0,51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        
         //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        lineChartPanel.setPreferredSize(new Dimension(360, 270));
        linechart_pane.removeAll();
        linechart_pane.add(lineChartPanel, BorderLayout.CENTER);
        linechart_pane.validate();
    }
	
	//display page
	public void displayDetail() {
		setMonthRevenue();
		compareRevenue();
		setNumbOfOrders();
		setNumbOfCustomers();
		setNumbOfGlasses();
		setTop5Glassees();
		showPieChart();
		showLineChart();
		
	}
}
