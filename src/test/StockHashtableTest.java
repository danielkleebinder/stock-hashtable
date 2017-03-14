package test;

import data.StockHashtable;

/**
 * Test class for stock hashtable.
 *
 * @author Daniel Kleebinder
 */
public class StockHashtableTest {
	public static void main(String[] args) {
		StockHashtable sht = new StockHashtable();
		
		SingleStock ss1 = new SingleStock();
		ss1.setName("Microsoft 1");
		ss1.setAbbreviation("MFT1");
		
		SingleStock ss2 = new SingleStock();
		ss2.setName("Microsoft 2");
		ss2.setAbbreviation("MFT2");
		
		SingleStock ss3 = new SingleStock();
		ss3.setName("Microsoft 3");
		ss3.setAbbreviation("MFT3");
	}
}