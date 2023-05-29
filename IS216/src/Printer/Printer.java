package Printer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Printer {
	private JPanel panel;
	public Printer(JPanel panel) {
		this.panel = panel;
	}
	public void print() {
		PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");
        
        job.setPrintable(new Printable(){
        public int print(Graphics pg,PageFormat pf, int pageNum){
            pf.setOrientation(PageFormat.LANDSCAPE);
            if(pageNum > 0){
                return Printable.NO_SUCH_PAGE;
            }
            Graphics2D g2 = (Graphics2D)pg;
            g2.translate(pf.getImageableX(), pf.getImageableY());
            g2.scale(0.47,0.47);
            panel.print(g2);
     
            return Printable.PAGE_EXISTS;
		   }
		});
        
        boolean ok = job.printDialog();
	    if(ok)
	    {
	    	try{
	    		job.print();
	    		JOptionPane.showMessageDialog(null, "In hóa đơn thành công.");
	    	}
	    	catch (PrinterException ex){
	    		ex.printStackTrace();
	    	}
	    }
	}
}
