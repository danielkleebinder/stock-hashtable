package test;

import data.SingleStock;
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
		ss1.setName("Microsoft");
		ss1.setAbbreviation("MFT");

		SingleStock ss2 = new SingleStock();
		ss2.setName("Samsung");
		ss2.setAbbreviation("SNSG");

		SingleStock ss3 = new SingleStock();
		ss3.setName("Apple");
		ss3.setAbbreviation("APL");

		SingleStock ss4 = new SingleStock();
		ss4.setName("Facebook");
		ss4.setAbbreviation("FB");

		System.out.println("Empty: " + sht.isEmpty());

		sht.putStockByName(ss1.getName(), ss1);
		sht.putStockByName(ss2.getName(), ss2);
		sht.putStockByName(ss3.getName(), ss3);
		sht.putStockByName(ss4.getName(), ss4);

		System.out.println("Size: " + sht.getSize());

		System.out.println(sht.getStockByName("Apple"));
		System.out.println(sht.getStockByName("Microsoft"));
		System.out.println(sht.getStockByName("Samsung"));
		System.out.println(sht.getStockByName("Facebook"));

		System.out.println(sht.removeStockByAbbreviation("FB"));
		System.out.println(sht.removeStockByName("Samsung"));

		System.out.println("Size: " + sht.getSize());

		System.out.println(sht.getStockByAbbreviation("SNSG"));
		System.out.println(sht.getStockByAbbreviation("APL"));
		System.out.println(sht.getStockByAbbreviation("MFT"));
		System.out.println(sht.getStockByName("Microsoft"));

		System.out.println("Size: " + sht.getSize());
	}
}
