package test;

import data.SingleStock;
import data.StockDataset;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.StockReader;

/**
 * Test class for the stock parser.
 *
 * @author Daniel Kleebinder
 */
public class StockParserTest {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			SingleStock ss = new StockReader().read(StockParserTest.class.getResourceAsStream("/test/table.csv"));
			for (StockDataset sds : ss.getStockdata()) {
				System.out.println(sds);
			}
		} catch (IOException ex) {
			Logger.getLogger(StockParserTest.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ParseException ex) {
			Logger.getLogger(StockParserTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
