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

	public void setStockDataset(StockDataset[] stockdata) {
		this.stockdata = stockdata;
	}
	
	public StockDataset[] getStockDataset() {
		return stockdata;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("SingleStock{abbreviation=").append(abbreviation).append(", name=").append(name).append("}");
		return result.toString();
	}
}