package Exporter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class exporter {
	private TreeMap<String, Object[]> map;
	public exporter(TreeMap<String, Object[]> map) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet ws = wb.createSheet();
		
		this.map = map; 
		Set<String> id = map.keySet();
		
		XSSFRow fRow;
		int rowId = 0;
		
		for(String key : id) {
			fRow = ws.createRow(rowId++);
			
			Object[] value = map.get(key);
			int cellId = 0;
			
			for(Object object : value) {
				XSSFCell cell = fRow.createCell(cellId++);
				if(object != null) 
					cell.setCellValue(object.toString());
				else 
					cell.setCellValue("");
			}
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(new File(getExcelPath()));
			wb.write(fos);
			fos.close();
			JOptionPane.showMessageDialog(null, "Xuất file Excel thành công.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//get excel path 
	public String getExcelPath() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		String path = "";
		try {
			File file = fileChooser.getSelectedFile();
			path = file.getAbsolutePath();
			path += ".xlsx";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

}

