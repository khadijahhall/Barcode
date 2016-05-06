package com.jits.shipping;

class DistributionCenter extends Location{
	private String city;
	private String destinationcode;

	public DistributionCenter(String city) {
		this.city= city;
	}

	public String getDestination() {
		return destinationcode;
	}

	public void setDestination(String destination) {
		this.destinationcode = destination;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "DistributionCenter [city=" + city + ", destination="
				+ destinationcode + "]";
	}

	@Override
	public String createLocation() {
		
		return "DistributionCenter [city=" + city + ", destination="
				+ destinationcode + "]";
	}
	public String createTravelstring(long Id){
		 String b= super.createTravelstring(Id);
		 String a= destinationcode;
		 return a +b ;
		 
	 }

}
