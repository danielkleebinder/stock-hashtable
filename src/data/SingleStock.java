package data;

import java.util.Objects;

/**
 * Single stock model class.
 *
 * @author Daniel Kleebinder, Christoph Rippel
 */
public class SingleStock {

	private String abbreviation, name;

	private String[] date;
	private double[] open, high, low, close, adjClose, volume;

	public SingleStock() {
	}

	public SingleStock(String abbreviation, String name, String[] date,
			double[] open, double[] high, double[] low, double[] close, double[] volume,
			double[] adjClose) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.volume = volume;
		this.adjClose = adjClose;
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

	public void setDate(String[] date) {
		this.date = date;
	}

	public String[] getDate() {
		return date;
	}

	public void setOpen(double[] open) {
		this.open = open;
	}

	public double[] getOpen() {
		return open;
	}

	public void setHigh(double[] high) {
		this.high = high;
	}

	public double[] getHigh() {
		return high;
	}

	public void setLow(double[] low) {
		this.low = low;
	}

	public double[] getLow() {
		return low;
	}

	public void setClose(double[] close) {
		this.close = close;
	}

	public double[] getClose() {
		return close;
	}

	public void setAdjClose(double[] adjClose) {
		this.adjClose = adjClose;
	}

	public double[] getAdjClose() {
		return adjClose;
	}

	public void setVolume(double[] volume) {
		this.volume = volume;
	}

	public double[] getVolume() {
		return volume;
	}

	/*
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + Objects.hashCode(this.abbreviation);
		hash = 37 * hash + Objects.hashCode(this.name);
		hash = 37 * hash + Objects.hashCode(this.date);
		hash = 37 * hash + (int) (Double.doubleToLongBits(this.open) ^ (Double.doubleToLongBits(this.open) >>> 32));
		hash = 37 * hash + (int) (Double.doubleToLongBits(this.high) ^ (Double.doubleToLongBits(this.high) >>> 32));
		hash = 37 * hash + (int) (Double.doubleToLongBits(this.low) ^ (Double.doubleToLongBits(this.low) >>> 32));
		hash = 37 * hash + (int) (Double.doubleToLongBits(this.close) ^ (Double.doubleToLongBits(this.close) >>> 32));
		hash = 37 * hash + (int) (Double.doubleToLongBits(this.adjClose) ^ (Double.doubleToLongBits(this.adjClose) >>> 32));
		hash = 37 * hash + this.volume;
		return hash;
	}*/
	
	/*
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SingleStock other = (SingleStock) obj;
		if (!Objects.equals(this.abbreviation, other.abbreviation)) {
			return false;
		}
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
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
		return this.volume == other.volume;
	}
	 */
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("SingleStock{abbreviation=").append(abbreviation).append(", name=").append(name).append("}");
		return result.toString();
	}
}
