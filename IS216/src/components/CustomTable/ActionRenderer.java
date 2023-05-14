package components.CustomTable;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ActionRenderer extends DefaultTableCellRenderer {

    private ActionPanel edit_deletePane;

    public ActionRenderer() {
    	edit_deletePane = new ActionPanel();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
        	edit_deletePane.setBackground(table.getSelectionBackground());
        } else {
        	edit_deletePane.setBackground(table.getBackground());
        }
        return edit_deletePane;
    }
}
