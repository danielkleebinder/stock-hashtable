package data;

/**
 * Single stock model class.
 *
 * @author Daniel Kleebinder, Christoph Rippel
 */

public class SingleStock {

	private String abbreviation, name;
	private StockDataset[] stockdata;

	public SingleStock() {
	}

	public SingleStock(String abbreviation, String name, StockDataset[] stockdata) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.stockdata = stockdata;
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

	public void setStockdataset(StockDataset[] stockdata) {
		this.stockdata = stockdata;
	}
	
	public StockDataset[] getStockdataset() {
		return stockdata;
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
