package test;

import data.SingleStock;
import data.StockHashtable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import util.HashtableIO;
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
			StockHashtable hashtable = new StockHashtable();

			SingleStock dax = new StockReader().read(StockParserTest.class.getResourceAsStream("/test/dax.csv"));
			SingleStock msft = new StockReader().read(StockParserTest.class.getResourceAsStream("/test/msft.csv"));
			SingleStock estx = new StockReader().read(StockParserTest.class.getResourceAsStream("/test/estx.csv"));
			SingleStock dji = new StockReader().read(StockParserTest.class.getResourceAsStream("/test/dji.csv"));

			dax.setName("GDAXI");
			dax.setAbbreviation("DAX");

			msft.setName("Microsoft");
			msft.setAbbreviation("MSFT");

			estx.setName("Euro Stock Exchange");
			estx.setAbbreviation("ESTX");

			dji.setName("Dow Jones");
			dji.setAbbreviation("DJI");

			hashtable.putStock(dax);
			hashtable.putStock(msft);
			hashtable.putStock(estx);
			hashtable.putStock(dji);

			HashtableIO.exportTable(hashtable, new FileOutputStream("./hastable.sht"));
			hashtable = HashtableIO.importTable(new FileInputStream("./hastable.sht"));

			System.out.println(hashtable.getSize());
			System.out.println(hashtable.getStockByName("Microsoft"));
			System.out.println(hashtable.getStockByName("Euro Stock Exchange"));
			System.out.println(hashtable.getStockByAbbreviation("DAX"));
			System.out.println(hashtable.getStockByAbbreviation("DJI"));
		} catch (IOException | ParseException | ParserConfigurationException | TransformerException | SAXException ex) {
			Logger.getLogger(StockParserTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
