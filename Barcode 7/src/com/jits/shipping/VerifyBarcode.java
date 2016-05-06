package com.jits.shipping;

import java.util.zip.ZipException;

class VerifyBarcode {
	//ParseBarcode myParse = new ParseBarcode();
 
	public boolean verifyZip(String zip) throws ZipException {
System.out.println("Zip "+ zip);
		if ((zip != null) && (zip.matches("^[0-9][0-9][0-9][0-9][0-9]$"))) {
			return true;
		} else {
			throw new ZipException("Invalid Zipcode");
		}
	}

}
