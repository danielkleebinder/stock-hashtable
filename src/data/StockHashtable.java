package data;

import utils.StringUtils;

public class StockHashtable {

	private SingleStock[] stockHT = new SingleStock[1000]; //Hashtable: Key = Name, Value = SingleStock
	private String[] nameAbbHT = new String[1000]; //Hashtable: Key = Abbreviation, Value = Name

	//Save certain stock by using its name as key
	@SuppressWarnings("unused")
	public void putStockByName(String name, SingleStock st) {
		int index = StringUtils.hashCode(name);
		if (stockHT[index].equals(null)) {
			stockHT[index] = st;
		} else {
			//TODO: collision handling
		}
	}

	//Save certain stock by using its abbreviation as key, calls putStockByName()
	//This way stocks are saved once, although another table for name:abbreviations is required
	@SuppressWarnings("unused")
	public void putNameByAbbreviation(String name, String abb) {
		int index = StringUtils.hashCode((name));
		if (nameAbbHT[index].isEmpty()) {
			nameAbbHT[index] = abb;
		} else {
			//TODO: collision handling
		}
	}

	//Evil?  ¯\(ツ)/¯ 
	public SingleStock getStockByName(String name) {
		return stockHT[StringUtils.hashCode((name))];
	}

	public SingleStock getStockByAbbreviation(String abb) {
		return getStockByName(nameAbbHT[StringUtils.hashCode((abb))]);
	}
}
