package com.jits.shipping;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReportTest {

	ParseBarcode myParse;
	ArrayList<String> listofbcodes;
	
	@Test
	public void setUp()  {
		myParse= new ParseBarcode();
		
		myParse.parsedBcode("11243545698062|AIR|32082|44122|30.0|5|8|10|X!002YZ78W|CHM|2940 Amherst Street");
		myParse.parsedBcode("21243545698062|AIR|11530|34952|30.0|5|8|10|X!002YZ78W|CHM|1945 Bad Street ");
		myParse.parsedBcode("31243545698062|AIR|21701|46530|30.0|5|8|10|X!002YZ78W|CHM|5947 Good Street ");
		
	    myParse.parsedBcode("41243545698062|GRD|44122|46530|30.0|5|8|10|X!002YZ78W|CHM|8906 Hillcrest Drive");
	
		myParse.parsedBcode("51243545698062|GRD|34952|46530|30.0|5|8|10|X!002YZ78W|CHM|7996 Bellcrest Drive");
		myParse.parsedBcode("61243545698062|GRD|46530|46530|30.0|5|8|10|X!002YZ78W|CHM|9967 Westcrest Drive");
		
		myParse.parsedBcode("71124354569806|RAL|30117|46530|30.0|5|8|10|X!002YZ78W|CHM|2916 Ivy Court");
		myParse.parsedBcode("81243545698062|RAL|15701|46530|30.0|5|8|10|X!002YZ78W|CHM|3016 Green Court");
		myParse.parsedBcode("91243545698062|RAL|21801|46530|30.0|5|8|10|X!002YZ78W|CHM|5678 Blue Court");
		
		
		
		myParse.writemyReport();
	}
	
	

	
}
