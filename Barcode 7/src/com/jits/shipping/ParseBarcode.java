package com.jits.shipping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.ZipException;

class ParseBarcode {

	 ArrayList<Barcode> listofbcodes = new ArrayList<Barcode>();

	// public ArrayList<Barcode> getListofbcodes() {
	// return listofbcodes;
	// }

	// public void setListofbcodes(ArrayList<Barcode> listofbcodes) {
	// this.listofbcodes = listofbcodes;
	// }
	//

	public Barcode parsedBcode(String bcode) {
		Barcode myBarcode = new Barcode();		

		String[] ccode = bcode.split("\\|");

		long id2 = Long.parseLong(ccode[0]);
		myBarcode.setId(id2);

		try {

			myBarcode.setShipmeth(ccode[1]);

		} catch (JitsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			myBarcode.setFromZip(ccode[2]);
			myBarcode.setToZip(ccode[3]);
			
		} catch (ZipException e) {
			e.printStackTrace();
		}

		double weight2 = Double.parseDouble(ccode[4]);
		myBarcode.setWeight(Math.ceil(weight2));

		double height2 = Double.parseDouble(ccode[5]);
		myBarcode.setHeight(Math.ceil(height2));

		double width2 = Double.parseDouble(ccode[6]);
		myBarcode.setWidth(Math.ceil(width2));

		double depth2 = Double.parseDouble(ccode[7]);
		myBarcode.setDepth(Math.ceil(depth2));

		myBarcode.setOther(ccode[8]);

		myBarcode.setHazards(ccode[9]);

		myBarcode.setAddress(ccode[10]);
		
		myBarcode.determineRoute();
		
		this.listofbcodes.add(myBarcode);

		return myBarcode;

	}

	public void sortList(ArrayList<Barcode> listofbcodes) {

		Collections.sort(listofbcodes);

	}

	void writemyReport() {
		Report myReport = new Report();
		
		System.out.println(myReport.writereport(listofbcodes));
	}

}
