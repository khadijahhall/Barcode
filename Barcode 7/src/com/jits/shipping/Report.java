package com.jits.shipping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class Report {
//prints list from ParseBarcode
	public String writereport(ArrayList <Barcode>listofbcodes){
		Collections.sort(listofbcodes);
	 String HEADER = "JITIS Shipping Package Report";
	String fullreport = HEADER;
	for(Barcode b:listofbcodes){
		fullreport +=("\n"+b.getId()+"|"+b.getShipmeth());
//		if(b.getShipmeth().equals(ShippingMethod.GRD)){
			List<Location>location = b.getParcelroute();
			for(Location location1:location){
				System.out.println(location1.createLocation());
				fullreport += " "+ location1.createLocation();	
//		}}else if(b.getShipmeth().equals(ShippingMethod.AIR)){
//			List<Location>location = b.getParcelroute();
//			for(Location location1:location){
//				System.out.println(location1.createLocation());
//				fullreport += " "+ location1.createLocation();
//		}
			
		
	}
		fullreport+= "\n";
	
	}
	String FOOTER = new Date().toString();
	fullreport += FOOTER;
	return fullreport;
		
	}
}
