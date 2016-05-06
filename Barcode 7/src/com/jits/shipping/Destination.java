package com.jits.shipping;

import java.util.Date;

class Destination extends Location{
private String address;
private String toZip;

	public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getToZip() {
	return toZip;
}
public void setToZip(String toZip) {
	this.toZip = toZip;
}
	Destination(String address, String zipCode){
		this.setAddress(address);
		this.setToZip(zipCode);
	}
	@Override
	public String createLocation() {
		
		return "Address " + address+ " Zipcode "+ toZip;
	}
	
	public String createTravelstring(long Id){
		 String b= super.createTravelstring(Id);
		 String concatvar= address+toZip;
		 String newconcat= concatvar.replaceAll(" ", "").trim();
		 
		 
		 int sum = 0;
		 char[] concatArray = newconcat.toCharArray();
		 for (int i = 0; i < concatArray.length; i++) {
			sum += concatArray[i];
		}
		 
		 return sum +b ;
		 
	 }
}
