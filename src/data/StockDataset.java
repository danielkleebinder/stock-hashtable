package data;

import java.util.Objects;

/**
 * Single stock model class.
 *
 * @author Daniel Kleebinder, Christoph Rippel
 */
public class StockDataset {

	private String date;
	private double open, high, low, close, adjClose, volume;

	public StockDataset(String date,
			double open, double high, double low, double close, double volume,
			double adjClose) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.volume = volume;
		this.adjClose = adjClose;
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

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getVolume() {
		return volume;
	}
}
