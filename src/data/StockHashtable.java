package data;

import utils.StringUtils;

public class StockHashtable {

	private final int THRESHOLD = 512;
	private final int SIZE = 1000 + THRESHOLD;

	private final SingleStock[] stockHT = new SingleStock[SIZE]; //Hashtable: Key = Name, Value = SingleStock
	private final String[] nameAbbHT = new String[SIZE]; //Hashtable: Key = Abbreviation, Value = Name

	
	//Calculate index for String keys, USE AT OWN RISK
	@SuppressWarnings("unused")
	private int calculateKey(String value) {
	int result = 100;
	result = Integer.parseInt(value)*result + 37;
	return result;
	}	
	
	//Save certain stock by using its name as key
	@SuppressWarnings("unused")
	public void putStockByName(String name, SingleStock st) {
		int index = keyIndex(name);
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
		int index = keyIndex(name);
		if (nameAbbHT[index].isEmpty()) {
			nameAbbHT[index] = abb;
		} else {
			//TODO: collision handling
		}
	}

	//Evil?  ¯\(ツ)/¯ 
	public SingleStock getStockByName(String name) {
		return stockHT[keyIndex(name)];
	}

	public SingleStock getStockByAbbreviation(String abb) {
		return getStockByName(nameAbbHT[keyIndex(abb)]);
	}

	/**
	 * Returns the global index of the given string.
	 *
	 * @param str String.
	 * @return Hash table index.
	 */
	private int keyIndex(String str) {
		return StringUtils.hashCode(str) % SIZE;
	}
}
