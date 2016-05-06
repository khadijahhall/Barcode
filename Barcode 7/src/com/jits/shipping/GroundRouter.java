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

class GroundRouter extends Router{
	
	static{
		 try {
			GroundRouter.populateDCMap();
			GroundRouter.initialisZipMap();
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private static  Map<String, DistributionCenter> myMap; 
	private static Map<String,String>zipMap;
	
	static void populateDCMap() {

		myMap = new HashMap<String, DistributionCenter>();

		DistributionCenter dc1 = new DistributionCenter("Raleigh");
		DistributionCenter dc2 = new DistributionCenter("Kansas City");
		DistributionCenter dc3 = new DistributionCenter("Denver");
		DistributionCenter na = new DistributionCenter("N/A");
		
		dc1.setDestination("DC1");
		dc2.setDestination("DC2");
		dc3.setDestination("DC3");
		na.setDestination("N/A");

		myMap.put("AK", dc3);
		myMap.put("AZ", dc3);
		myMap.put("AR", dc2);
		myMap.put("CA", dc3);
		myMap.put("CO", dc3);
		myMap.put("CT", dc1);
		myMap.put("DE", dc1);
		myMap.put("DC", dc1);
		myMap.put("FL", dc1);
		myMap.put("GA", dc1);
		myMap.put("HI", na);
		myMap.put("ID", dc3);
		myMap.put("IL", dc2);
		myMap.put("IN", dc2);
		myMap.put("IA", dc2);
		myMap.put("KS", dc3);
		myMap.put("KY", dc2);
		myMap.put("LA", dc2);
		myMap.put("ME", dc1);
		myMap.put("MD", dc1);
		myMap.put("MA", dc1);
		myMap.put("MI", dc2);
		myMap.put("MN", dc2);
		myMap.put("MS", dc2);
		myMap.put("MO", dc2);
		myMap.put("MT", dc3);
		myMap.put("NE", dc3);
		myMap.put("NV", dc3);
		myMap.put("NH", dc1);
		myMap.put("NJ", dc1);
		myMap.put("NM", dc3);
		myMap.put("NY", dc1);
		myMap.put("NC", dc1);
		myMap.put("ND", dc3);
		myMap.put("OH", dc1);
		myMap.put("OK", dc3);
		myMap.put("OR", dc3);
		myMap.put("PA", dc1);
		myMap.put("RI", dc1);
		myMap.put("SC", dc1);
		myMap.put("SD", dc3);
		myMap.put("TN", dc2);
		myMap.put("TX", dc3);
		myMap.put("UT", dc3);

	

	}
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
	 public void determineRoute(Barcode b){
			if ((b.getLocation() ==null) && (ShippingMethod.GRD.equals(b.getShipmeth()))){
				
				
				Warehouse myWhse = new Warehouse();
				b.parcelroute.add(myWhse);
				b.setLocation(myWhse);
				
				
				Router ground =new GroundRouter();
				
				Location myroute = ground.findByZip(b.getToZip());
				//System.out.println(myroute == null);
				b.parcelroute.add(myroute);
				
				Location destination = new Destination(b.getAddress(),b.getToZip());
			
				b.parcelroute.add(destination);
		
			}}
	
	public  Location findByZip(String zipCode){
		String state = zipMap.get(zipCode);
		DistributionCenter myDC = myMap.get(state);
		return myDC;
	}
	
//	@Override
//	public Location findByZip(String zipCode) {
//		return routeByZip(zipCode);
//	}
	}