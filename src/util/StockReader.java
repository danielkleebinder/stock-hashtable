package util;

import data.SingleStock;
import data.StockDataset;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * The stock reader class is able to read stock CSV files.
 *
 * @author Daniel Kleebinder
 */
public class StockReader {

	// Parsing constants for stock CSV files
	private static final String DELIMITER = ",";
	private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	/**
	 * Reads the given CSV stock file.
	 *
	 * @param input Input stream.
	 * @return Single stock.
	 * @throws IOException Can occur while reading the file.
	 * @throws ParseException Can occur while parsing the CSV file.
	 */
	public SingleStock read(InputStream input) throws IOException, ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
			reader.readLine();

			SingleStock stock = new SingleStock();
			String line;
			String[] lineTokens;

			while ((line = reader.readLine()) != null) {
				lineTokens = line.split(DELIMITER);

				StockDataset dataset = new StockDataset();
				dataset.setDate(FORMATTER.parse(lineTokens[0]));
				dataset.setOpen(Double.parseDouble(lineTokens[1]));
				dataset.setHigh(Double.parseDouble(lineTokens[2]));
				dataset.setLow(Double.parseDouble(lineTokens[3]));
				dataset.setClose(Double.parseDouble(lineTokens[4]));
				dataset.setVolume(Double.parseDouble(lineTokens[5]));
				dataset.setAdjClose(Double.parseDouble(lineTokens[6]));

				stock.getStockdata().add(dataset);
			}

			return stock;
		}
	}
}
