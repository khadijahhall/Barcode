package com.jits.shipping;

import static org.junit.Assert.*;

import org.junit.Test;

public class BcTest {

	@Test
	public void test() {
		Barcode myBarcode = new Barcode();
		Barcode myBarcode2 = new Barcode();
		
		myBarcode.setId(456);
		myBarcode2.setId(123);
		
		System.out.println(myBarcode.compareTo(myBarcode2));
return;
	}

}
