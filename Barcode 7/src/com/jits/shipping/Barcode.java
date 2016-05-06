package com.jits.shipping;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;

import com.jits.shipping.util.TrackingWriter;

class Barcode implements Comparable<Barcode> {
	private long id;
	private String fromZip;
	private String toZip;
	private double weight;
	private double height;
	private double width;
	private double depth;
	private String other;
	private String hazards;
	private ShippingMethod shipmeth;
	private String address;
	private Location location;
	private int currlocationIndex;
	List <Location> parcelroute = new ArrayList<Location>();
	private String trackingLog;
	
	
	

	public String getTrackingLog() {
		return trackingLog;
	}

	public void setTrackingLog(String trackingLog) {
		if(this.getCurrlocationIndex()==0){
			this.trackingLog = trackingLog;

		}else{
			this.trackingLog += trackingLog;

		}
	}

	public ShippingMethod getShipmeth() {
		return shipmeth;
	}

	public void setShipmeth(String incoming) throws JitsException {
		try {
			this.shipmeth = ShippingMethod.valueOf(incoming);
		} catch (IllegalArgumentException e) {
			throw new JitsException("We only ship by GRD, AIR and RAL)",
					new IllegalArgumentException());

		}
	}

	public String getFromZip() {
		return fromZip;
	}

	public void setFromZip(String fromZip) throws ZipException {
		VerifyBarcode myVerify = new VerifyBarcode();
		if (myVerify.verifyZip(fromZip)) {
			this.fromZip = fromZip;
		} else {
			System.out.println("Incorrect Zipcode: " + fromZip);
		}
		this.fromZip = fromZip;
	}

	public String getToZip() {
		return toZip;
	}

	public void setToZip(String toZip) throws ZipException {
		VerifyBarcode myVerify = new VerifyBarcode();
		if (myVerify.verifyZip(toZip)) {
		//	System.out.println("about to set toZip " + toZip);
			this.toZip = toZip;
		} else {
			System.out.println("Incorrect Zipcode: " + toZip);
		}
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getHazards() {
		return hazards;
	}

	public void setHazards(String hazards) {
		this.hazards = hazards;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int compareTo(Barcode o) {
		int x = this.shipmeth.compareTo(o.getShipmeth());
		if (x == 0) {
			x = Long.compare(this.getId(), o.getId());

		}
		return x;
		
//		int x = -99;
//		if( this.id < o.getId()){
//			x= -1;
//		}else if(this.id==o.getId()){
//			x= 0;
//		}else {
//			x=1;
//		}
//		return x;

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void determineRoute(){
		Router myRouter=null;
		if ((location ==null) && (ShippingMethod.GRD.equals(this.getShipmeth()))){
			myRouter= new GroundRouter();
			
//			Warehouse myWhse = new Warehouse();
		//	parcelroute.add(myWhse);
//			location = myWhse;
//			
//			
//			Router ground =new Router();
//			
//			Location myroute = ground.findByZip(getToZip());
//			System.out.println(myroute == null);
//			parcelroute.add(myroute);
//			
//			Destination destination = new Destination();
//			destination.setAddress(this.getAddress());
//			destination.setToZip(this.getToZip());
//			parcelroute.add(destination);
	
		}else if((location ==null) && (ShippingMethod.AIR.equals(this.getShipmeth()))){
			myRouter= new AirportRouter();
//			Warehouse myWhse = new Warehouse();
//			parcelroute.add(myWhse);
//			location = myWhse;
//			
//			Router air=new Router();
//			
//			Location originAir = air.findByZip(getFromZip());
//			parcelroute.add(originAir);
//			
//			Location destinAir = air.findByZip(getToZip());
//			parcelroute.add(destinAir);
//			
//			Destination destination = new Destination();
//			destination.setAddress(this.getAddress());
//			destination.setToZip(this.getToZip());
		//	parcelroute.add(destination);

		}
		myRouter.determineRoute(this);
	}
	public void moveParcel(){
		
		if (this.currlocationIndex< parcelroute.size()-1) {
			TrackingWriter trackwrite = new TrackingWriter("trackFile.txt", true);
		
			String trackString = location.createTravelstring(getId());
			this.setTrackingLog(trackString);
			trackwrite.write(this.getTrackingLog());
			
			 currlocationIndex++;
			 location = parcelroute.get(currlocationIndex);
		}else{
			System.out.println("Package has already arrived at Destination");
		}
	
}

	public int getCurrlocationIndex() {
		return currlocationIndex;
	}

	public void setCurrlocationIndex(int currlocationIndex) {
		this.currlocationIndex = currlocationIndex;
	}

	public List<Location> getParcelroute() {
		return parcelroute;
	}

	public void setParcelroute(List<Location> parcelroute) {
		this.parcelroute = parcelroute;
	}

	public void setShipmeth(ShippingMethod shipmeth) {
		this.shipmeth = shipmeth;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}}
