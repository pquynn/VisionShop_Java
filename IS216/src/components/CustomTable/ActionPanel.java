package components.CustomTable;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


import javax.swing.ImageIcon;

public class ActionPanel extends JPanel {

    private JButton delete;
    private JButton edit;

    public ActionPanel() {
    	setBackground(new Color(255, 255, 255));
        setLayout(new GridBagLayout());
        
        delete = new JButton("");
        ImageIcon deleteIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(ActionPanel.class.getResource("/assets/trash-2.png")));
        Image deleteImage = deleteIcon.getImage().getScaledInstance(16, 17, Image.SCALE_SMOOTH);
        delete.setIcon(new ImageIcon(deleteImage));
        delete.setBounds(69, 10, 20, 20);
        delete.setBorder(null);
        delete.setBackground(Color.white);
        delete.setActionCommand("delete");
        
        edit = new JButton("");
        ImageIcon editIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(ActionPanel.class.getResource("/assets/edit (1).png")));
        Image edImage = editIcon.getImage().getScaledInstance(19, 17, Image.SCALE_SMOOTH);
        edit.setIcon(new ImageIcon(edImage));
        edit.setBounds(34, 10, 20, 20);
        edit.setBorder(null);
        edit.setBackground(Color.white);
        edit.setActionCommand("edit");
        
        add(edit);
        add(delete);
        
    }

   public void addActionListener(TableEvent e, int row) {
	   edit.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
               e.onEdit(row);
           }
       });
       delete.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
               e.onDelete(row);
           }
       });
	   
    }
   
  
}

