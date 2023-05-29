package components.CustomTable;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

import javax.security.auth.kerberos.DelegationPermission;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

//AbstractCellEditor
public class ActionEditer extends DefaultCellEditor implements TableCellEditor {

    //private ActionPanel edit_deletePane;
	private TableEvent event;
    public ActionEditer(TableEvent event) {
    	super(new JCheckBox());
    	this.event = event;
    	
    }
    
   

    public boolean isCellEditable(EventObject e) {
        return true;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	ActionPanel edit_deletePane = new ActionPanel();
    	edit_deletePane.addActionListener(event, row);
       if (isSelected) {
    	   edit_deletePane.setBackground(table.getSelectionBackground());
        } else {
        	edit_deletePane.setBackground(table.getBackground());
        }
        
        return edit_deletePane;
    }
}
