package com.jits.shipping;

import java.util.Date;

abstract class Location {
	
	 abstract String createLocation();
	 
	public String createTravelstring(long Id){
		 Date date = new Date();
		 String scanId= Long.toString(Id);
		return "|"+scanId +"|"+ date;
		 
	 }

}
