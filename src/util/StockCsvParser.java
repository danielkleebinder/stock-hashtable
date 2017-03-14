package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class StockCsvParser{

	private File f;
	
	public StockCsvParser(File f) {
		this.f = f;
	}
	
	//Input: Date,Open,High,Low,Close,Volume,Adj Close
	public String[] parseData() throws Exception{
		String line;
		String[] tokens;
		
		try(BufferedReader br = new BufferedReader(new FileReader(f))) {
			line = br.readLine();
			tokens = line.split(",");
		}
		return tokens;
	}	
}