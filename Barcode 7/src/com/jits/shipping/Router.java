package com.jits.shipping;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

 abstract class Router {
	 abstract Location findByZip(String zipCode);
	 
	 public abstract void determineRoute(Barcode b);
	
	 private static Map<String,String>zipMap;
		static void initialisZipMap() throws BiffException, IOException{
			zipMap = new HashMap<String, String>();
			WorkbookSettings wb = new WorkbookSettings();
			wb.setSuppressWarnings(true);
			Workbook workbook = Workbook.getWorkbook(new File("zip.xls"),wb);
			Sheet sheet = workbook.getSheet(0);
			
			int rows = sheet.getRows();

			for(int i = 0; i < rows; i++){
				Cell[] myRows = sheet.getRow(i);
				String zipCode= myRows[0].getContents();
				String state = myRows[1].getContents();
				zipMap.put(zipCode,state);
		
			}
}
		
 }
