package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

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
			line = br.readLine();
			tokens = line.split(line, ','); //this line is the cause of a SHITLOAD OF ERRORS
		}
	}
	
	public String[] getTokens() {
		return tokens;
	}
}