package com.jits.shipping;

class Warehouse extends Location {
	String whse = "Whse";

	@Override
	public String createLocation() {
				return whse;
	}
	public String createTravelstring(long Id){
		 String b= super.createTravelstring(Id);
		 String a= whse;
		 return a +b ;
		 
	 }

}
