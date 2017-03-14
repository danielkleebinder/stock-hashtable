package data;

/**
 * This model class stores all stock information.
 *
 * @author Christoph Rippel, Daniel Kleebinder
 */
public class SingleStock {

	private String abbreviation, name;

	private String date;
	private double open, high, low, close, adjClose;
	private int volume;

	public SingleStock() {
	}

	public SingleStock(String abbreviation, String name, String date,
			double open, double high, double low, double close, double adjClose,
			int volume) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.adjClose = adjClose;
		this.volume = volume;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getOpen() {
		return open;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getHigh() {
		return high;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getLow() {
		return low;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getClose() {
		return close;
	}

	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}

	public double getAdjClose() {
		return adjClose;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}
}
