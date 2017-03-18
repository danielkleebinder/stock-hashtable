package data;

import java.util.Date;
import java.util.Objects;

/**
 * Model of a single line in the input file.
 *
 * @author Daniel Kleebinder, Christoph Rippel
 */
public class StockDataset {

	private Date date;
	private double open, high, low, close, adjClose, volume;

	public StockDataset() {
	}

	public StockDataset(Date date,
			double open, double high, double low, double close, double volume,
			double adjClose) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.adjClose = adjClose;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
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

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Objects.hashCode(this.date);
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.open) ^ (Double.doubleToLongBits(this.open) >>> 32));
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.high) ^ (Double.doubleToLongBits(this.high) >>> 32));
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.low) ^ (Double.doubleToLongBits(this.low) >>> 32));
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.close) ^ (Double.doubleToLongBits(this.close) >>> 32));
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.adjClose) ^ (Double.doubleToLongBits(this.adjClose) >>> 32));
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.volume) ^ (Double.doubleToLongBits(this.volume) >>> 32));
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final StockDataset other = (StockDataset) obj;
		if (!Objects.equals(this.date, other.date)) {
			return false;
		}
		if (Double.doubleToLongBits(this.open) != Double.doubleToLongBits(other.open)) {
			return false;
		}
		if (Double.doubleToLongBits(this.high) != Double.doubleToLongBits(other.high)) {
			return false;
		}
		if (Double.doubleToLongBits(this.low) != Double.doubleToLongBits(other.low)) {
			return false;
		}
		if (Double.doubleToLongBits(this.close) != Double.doubleToLongBits(other.close)) {
			return false;
		}
		if (Double.doubleToLongBits(this.adjClose) != Double.doubleToLongBits(other.adjClose)) {
			return false;
		}
		return Double.doubleToLongBits(this.volume) == Double.doubleToLongBits(other.volume);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(256);
		result.append("StockDataset{");
		result.append("date=").append(date);
		result.append(",open=").append(open);
		result.append(",high=").append(high);
		result.append(",low=").append(low);
		result.append(",close=").append(close);
		result.append(",adjClose=").append(adjClose);
		result.append(",volume=").append(volume);
		result.append("}");
		return result.toString();
	}
}
