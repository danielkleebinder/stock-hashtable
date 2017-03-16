package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import data.StockDataset;

public class StockBuilder{

	private File f;
	private int numColumns = 7;
	//private int numDatasets = 30;
	private StockDataset[] sd;
	
	
	public StockBuilder(File f) throws Exception {
		this.f = f;
		parseData();
	}
	
	//Input: Date,Open,High,Low,Close,Volume,Adj Close
	private void parseData() throws Exception{
		String line;
		String[] lineTokens;
		
		try(BufferedReader br = new BufferedReader(new FileReader(f))) {
			line = br.readLine(); //skip first
			int i=0;
			while((line=br.readLine()) != null) { //for a variable number of lines
				lineTokens = line.split(",");
				sd[i] = new StockDataset(lineTokens[0],
						Double.parseDouble(lineTokens[1]), Double.parseDouble(lineTokens[2]), 
						Double.parseDouble(lineTokens[3]), Double.parseDouble(lineTokens[4]),
						Double.parseDouble(lineTokens[5]), Double.parseDouble(lineTokens[6]));
				++i;
				}
			}			
		}
	}
	
	
	
}