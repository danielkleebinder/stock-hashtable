package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Single stock model class.
 *
 * @author Daniel Kleebinder, Christoph Rippel
 */
public class SingleStock {

	private String abbreviation, name;
	private List<StockDataset> stockdata = new ArrayList<>(64);

	public SingleStock() {
	}

	public SingleStock(String abbreviation, String name, StockDataset... stockdata) {
		this.abbreviation = abbreviation;
		this.name = name;

		for (StockDataset sds : stockdata) {
			this.stockdata.add(sds);
		}
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

	public void setStockdata(List<StockDataset> stockdata) {
		this.stockdata = stockdata;
	}

	public List<StockDataset> getStockdata() {
		return stockdata;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("SingleStock{abbreviation=").append(abbreviation).append(", name=").append(name).append("}");
		return result.toString();
	}
}
