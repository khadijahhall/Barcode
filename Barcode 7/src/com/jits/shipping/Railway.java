package com.jits.shipping;

class Railway extends Location {
	
	String code;
	String city;
	String state;
	String zipcode;
	
	
	Railway (String code, String  city, String  state, String zipcode){
		this.code = code;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	String createLocation() {
		
		return"|"+"RY: "+ this.getCode() +this.getCity()+ this.getState()+ "|"; 
	}
	public String createTravelstring(long Id){
		String b= super.createTravelstring(Id);
		String a = this.code;
		return "|"+ a +  b;
	}
}
