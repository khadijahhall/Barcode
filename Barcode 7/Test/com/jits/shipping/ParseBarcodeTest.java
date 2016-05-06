package com.jits.shipping;

import static org.junit.Assert.*;

import java.util.List;
import java.util.zip.ZipException;

import org.junit.Before;
import org.junit.Test;


public class ParseBarcodeTest {
	Barcode myBarcode;
	ParseBarcode myParse;
	String bcode;
	String bcode2;
	VerifyBarcode verify;
	
	@Before
	public void test() {
		
		bcode = "11243545698062|GRD|12345|54321|30.0|5|8|10|X!002YZ78W|CHM";
		bcode2 = "11243545698062|GRD|12345|35004|30.0|5|8|10|X!002YZ78W|CHM";
		myBarcode = new Barcode();
		myParse = new ParseBarcode();
	
		verify= new VerifyBarcode();
	
	}

	@Test
	public void testParseId() {
		
		
		long actual =myParse.parsedBcode(bcode).getId();

		long expected = 11243545698062L;
		
		assertEquals(expected, actual);
		}
	
	
	@Test 
	public void testParsefromZip() throws ZipException{
		
		String actual = myParse.parsedBcode(bcode).getFromZip();
		String expected = "12345";
		
		assertEquals(expected, actual);
		}
	@Test 
	public void testParsetoZip() throws ZipException{
		
		String actual = myParse.parsedBcode(bcode).getToZip();
		String expected = "54321";
		
		assertEquals(expected, actual);
		}
	@Test 
	public void testParseweight() {
		
		double actual = myParse.parsedBcode(bcode).getWeight();
		double expected = 30.0;
		double delta= .001;
		
		assertEquals(expected, actual,delta);
		}
	@Test 
	public void testParseheight() {
		
		double actual = myParse.parsedBcode(bcode).getHeight();
		double expected = 5.0;
		double delta= .001;
		
		assertEquals(expected, actual,delta);
		}
	@Test 
	public void testParseWidth() {
		
		double actual = myParse.parsedBcode(bcode).getWidth();
		double expected = 8.0;
		double delta= .001;
		
		assertEquals(expected, actual,delta);
		}
	
	@Test 
	public void testParseDepth() {
		
		double actual = myParse.parsedBcode(bcode).getDepth();
		double expected = 10.0;
		double delta= .001;
		
		assertEquals(expected, actual,delta);
		}
	
	@Test 
	public void testParseOther() {
		
		String actual = myParse.parsedBcode(bcode).getOther();
		String expected = "X!002YZ78W";
		
		assertEquals(expected, actual);
		}
	@Test 
	public void testParseHazards() {
		
		String actual = myParse.parsedBcode(bcode).getHazards();
		String expected = "CHM";
		
		assertEquals(expected, actual);
		}
	@Test
	public void testValidLocation(){
		Barcode rbc = myParse.parsedBcode(bcode2);
		
		rbc.determineRoute();
		
		List<Location> location = rbc.getParcelroute();
		
		String expected = "Whse";
		String actual = rbc.getLocation().createLocation();
		assertEquals(expected, actual);
		
		assertEquals(rbc.getLocation(), location.get(0));
	}
	
	
	}



