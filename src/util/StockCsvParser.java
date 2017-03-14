package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StockCsvParser{

	private File f;
	private String[] tokens;
	
	public StockCsvParser(File f) throws Exception {
		this.f = f;
		parseData();
	}
	
	//Input: Date,Open,High,Low,Close,Volume,Adj Close
	private void parseData() throws Exception{
		String line;
		
		try(BufferedReader br = new BufferedReader(new FileReader(f))) {
			line = br.readLine(); //skip first
			while(line != null) { //while(true) would work
				line = br.readLine();
				//System.out.println(line);
				if(line == null) break; //otherwise .split throws in last iteration
				tokens = line.split(",");
			}
		}
	}
	
	public String[] getTokens() {
		return tokens;
	}
}