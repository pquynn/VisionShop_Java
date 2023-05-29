package components.CustomTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TabExpander;

import view.admin.EditProduct;
import view.admin.EditProductCategory;
import view.admin.EditUser;
import view.user.OrderDetail;

public class MultiButtonTable extends JTable{
	private TableEvent event;
	
	public MultiButtonTable() {
		}
    
    public void setEvent(TableEvent ev) {
    	event = ev;
    }
    
    public void customTable() {
    	// cell renderer, cell editer
        ActionRenderer renderer = new ActionRenderer();
        getColumnModel().getColumn(getColumnCount()-1).setCellRenderer(renderer);
        getColumnModel().getColumn(getColumnCount()-1).setCellEditor(new ActionEditer(event));
        setRowHeight(renderer.getTableCellRendererComponent(this, null, true, true, 0, 0).getPreferredSize().height);

        //custom table
        getColumnModel().getColumn(getColumnCount()-1).setPreferredWidth(10);
		setRowSelectionAllowed(false);
		setShowVerticalLines(false);
		setBorder(null);
		setForeground(new Color(0, 0, 0));
		setRowHeight(40);
		setGridColor(new Color(211, 211, 211));
		
		//custom table header
		getTableHeader().setBackground(Color.WHITE);
		getTableHeader().setForeground(Color.black);
		getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		getTableHeader().setPreferredSize(new Dimension(100, 30));
		
		
		for(int i = 0; i < getColumnModel().getColumnCount(); i++){
			getColumnModel().getColumn(i).setResizable(false);
		}
		
		//set uneditable cell in table
		for (int c = 0; c < getColumnCount() - 1; c++){
		    Class<?> col_class = getColumnClass(c);
		    setDefaultEditor(col_class, null);        
		}
		
		setBackground(new Color(255, 255, 255));
		setFont(new Font("SansSerif", Font.PLAIN, 13));
	
    }
    
    public void customTableWithOutActionCol() {
        //custom table
		setRowSelectionAllowed(false);
		setShowVerticalLines(false);
		setBorder(null);
		setForeground(new Color(0, 0, 0));
		setRowHeight(40);
		setGridColor(new Color(211, 211, 211));
		
		//custom table header
		getTableHeader().setBackground(Color.WHITE);
		getTableHeader().setForeground(Color.black);
		getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		getTableHeader().setPreferredSize(new Dimension(100, 30));
		
		
		for(int i = 0; i < getColumnModel().getColumnCount(); i++){
			getColumnModel().getColumn(i).setResizable(false);
		}
		
		//set uneditable cell in table
		for (int c = 0; c < getColumnCount(); c++){
		    Class<?> col_class = getColumnClass(c);
		    setDefaultEditor(col_class, null);        
		}
		setBackground(new Color(255, 255, 255));
		setFont(new Font("SansSerif", Font.PLAIN, 13));
    }
}

