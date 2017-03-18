package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import data.StockDataset;

/**
 * Builds SingleStock out of StockDataset.
 *
 * @author Christoph Rippel, Daniel Kleebinder (Hah!)
 */
public class StockBuilder {

	private File f;
	//private int numColumns = 7;
	//private int numDatasets = 30;
	private StockDataset[] sd;

	public StockBuilder(File f) throws Exception {
		this.f = f;
		parseData();
	}

	//Input: Date,Open,High,Low,Close,Volume,Adj Close
	private void parseData() throws Exception {
		String line;
		String[] lineTokens;

		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			line = br.readLine(); //skip first
			int i = 0;
			while ((line = br.readLine()) != null) { //for a variable number of lines
				lineTokens = line.split(",");
				System.out.println(lineTokens[0] + " " + lineTokens[1] + " " + lineTokens[2]);
				sd[0] = new StockDataset(lineTokens[0],
						Double.parseDouble(lineTokens[1]), Double.parseDouble(lineTokens[2]),
						Double.parseDouble(lineTokens[3]), Double.parseDouble(lineTokens[4]),
						Double.parseDouble(lineTokens[5]), Double.parseDouble(lineTokens[6]));
				++i;
			}
		}
	}

	public StockDataset[] getFilledStockDataset() {
		return sd;
	}
}
