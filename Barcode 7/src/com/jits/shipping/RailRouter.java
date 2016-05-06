package com.jits.shipping;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

class RailRouter extends Router {
	 
	 private static Map<String, Coordinate> zipcodeMap = 
			    new HashMap<String, Coordinate>(29500);
			  
			  // Airports
		private static Collection<Railway> railList = new ArrayList<Railway>();
			  
			  /**
			   * Finds the closest Airport to the given zipcode.
			   */
			  @Override
			  public Location findByZip(String zipCode) {
			    Railway result = null;
			    
			    // get Coordinate for zipcode
			    Coordinate zipCoord = zipcodeMap.get(zipCode);
			    
			    // iterate over airports, find distance between zipcode and each one
			    // hold onto airport with smallest distance
			    double shortestDistance = 10000000000.0;
			    for (Railway railway : railList) {
			      Coordinate railCoord = zipcodeMap.get(railway.getZipcode());
			      double distance = zipCoord.distanceTo(railCoord);
			      if (distance < shortestDistance) {
			        shortestDistance = distance;
			        result = railway;
			      }
			    }
			    // return airport with smallest distance
			    return result;
			  }
			//  
			  static {
			    loadZipcodeData();
			    loadRailways();
			  }

			  private static void loadZipcodeData() {
			    // disable warnings
			    WorkbookSettings settings = new WorkbookSettings();
			    settings.setSuppressWarnings(true);
			    
			    Workbook workbook = null;
			    try {
			      // load spreadsheet
			      workbook = Workbook.getWorkbook(new File("zipLatLng.xls"), settings);
			      Sheet sheet = workbook.getSheet(0);
			      
			      // read all rows (zero-indexed), store zipcode/Coordinate data in map
			      int numRows = sheet.getRows();
			      for (int i = 1; i < numRows; i++) {
			        Cell[] rowCells = sheet.getRow(i);
			        String zipcode = rowCells[1].getContents();
			        String lat = rowCells[4].getContents();
			        String lng = rowCells[5].getContents();
			        zipcodeMap.put(zipcode, 
			          new Coordinate(Double.parseDouble(lat), Double.parseDouble(lng)));
			      }
			    }
			    catch (Exception e) {
			      e.printStackTrace();
			    }
			    finally {
			      if (workbook != null) {
			        workbook.close();
			      }
			    }
			  }
	
			private static void  loadRailways(){
		railList.add(new Railway("NW01", "Portland", "OR", "97212"));
		railList.add(new Railway("SW02", "Phoenix", "AZ", "85003"));
		railList.add(new Railway("NC03", "Rapid City", "SD", "57701"));
		railList.add(new Railway("SC04", "San Antonio", "TX", "78202"));
		railList.add(new Railway("NE05", "Cleveland", "OH", "44102"));
		railList.add(new Railway("SE06", "Jacksonville", "FL", "32202"));
		
			}
		

	@Override
	public void determineRoute(Barcode b) {
		if((b.getLocation() ==null) && (ShippingMethod.RAL.equals(b.getShipmeth()))){
			
			Warehouse myWhse = new Warehouse();
			b.parcelroute.add(myWhse);
			b.setLocation(myWhse);
			
			Router rail=new RailRouter();
			
			Location myroute = rail.findByZip(b.getToZip());
			//System.out.println(myroute == null);
			b.parcelroute.add(myroute);
			
			
			Location destination = new Destination(b.getAddress(),b.getToZip());

			b.parcelroute.add(destination);

	}

}}
