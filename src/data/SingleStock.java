package data;

import java.util.Date;

public class SingleStock {
	private String abbreviation, name;
	
	private String date; //dateformattargh
	private double open, high, low, close, adjClose;
	private int volume;
	
	public SingleStock(String abbreviation, String name,
			String date, double open, double high, double low, double close, int volume, double adjClose)
	{
		this.abbreviation = abbreviation;
		this.name = name;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.adjClose = adjClose;
		this.volume = volume;
	}
}
