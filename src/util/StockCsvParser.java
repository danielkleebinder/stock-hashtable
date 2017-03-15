package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StockCsvParser{

	private File f;
	private String[][] tokens;
	private Double[][] finalTokens;
	private int numColumns = 7;
	private int numLines;
	
	public StockCsvParser(File f) throws Exception {
		this.f = f;
		parseData();
	}
	
	//Input: Date,Open,High,Low,Close,Volume,Adj Close
	private void parseData() throws Exception{
		String line;
		String[] lineTokens;
		
		try(BufferedReader br = new BufferedReader(new FileReader(f))) {
			line = br.readLine(); //skip first
			
			for(int i=0; ; i++) { //for a variable number of lines
				line = br.readLine();
				lineTokens = line.split(",");
				if(line == null) break; //otherwise .split throws in last iteration
				for(int j=0; j<numColumns; j++) { //for every column
					tokens[j][i] = lineTokens[j]; //[j][i] is right
					//Date,Open,High,Low,Close,Volume,Adj Close
					//Date,Open,High,Low,Close,Volume,Adj Close
					//...
					//becomes
					//Date,Date,Date,Dat,Date ,Date  ,Date
					//Open,Open,Open,Ope,Open ,Open  ,Open
					//...
					
				}
			}			
		}
		
		for(int i=1; i<=6; i++)
		{
			for(int j=0; j<=numColumns;j++) {
			//parse lines 1-6 to double
				finalTokens[i-1][j] = Double.parseDouble(tokens[i][j]);
			}
		}
	}
	
	
	
	public String[] getDates() {
		return tokens[0];
	}
	//then
	public Double[][] getRest() {
		return finalTokens[][];
	}
}