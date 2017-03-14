package data;

import utils.StringUtils;

/**
 * Main stock hashtable class.
 *
 * @author Daniel Kleebinder, Christoph Rippel
 */
public class StockHashtable {

	private final int THRESHOLD = 512;
	private final int SIZE = 1000 + THRESHOLD;

	private final SingleStock[] stockHT = new SingleStock[SIZE];
	private final String[] nameAbbHT = new String[SIZE];

	//Save certain stock by using its name as key
	@SuppressWarnings("unused")
	public void putStockByName(String name, SingleStock st) {
		int index = keyIndex(name);
		if (stockHT[index] == null) {
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
		if (nameAbbHT[index] == null) {
			nameAbbHT[index] = abb;
		} else {
			//TODO: collision handling
		}
	}

	/**
	 * Removes the single stock entry with the given name.
	 *
	 * @param name Name.
	 * @return Removed item.
	 */
	public SingleStock removeStockByName(String name) {
		int index = keyIndex(name);

		SingleStock result = null;
		SingleStock buffer;

		while (stockHT[index] != null) {
			result = stockHT[index];
			if (name.equals(result.getName())) {
				break;
			} else {
				// Use linear probing
				index = (index + 1) % SIZE;
			}
		}

		// Move all items after the deleted one
		for (int j = (index + 1) % SIZE; stockHT[j] != null; j = (j + 1) % SIZE) {
			buffer = stockHT[j];
			stockHT[j] = null;
			putStockByName(name, buffer);
		}

		return result;
	}

	/**
	 * Removes the single stock entry with the given abbreviation.
	 *
	 * @param abb Abbreviation.
	 * @return Removed item.
	 */
	public SingleStock removeStockByAbbreviation(String abb) {
		return removeStockByName(getNameForAbbreviation(abb));
	}

	/**
	 * Returns the single stock for the given name.
	 *
	 * @param name Name.
	 * @return Stock.
	 */
	public SingleStock getStockByName(String name) {
		int index = keyIndex(name);

		while (stockHT[index] != null) {
			if (name.equals(stockHT[index].getName())) {
				return stockHT[index];
			} else {
				// Use linear probing
				index = (index + 1) % SIZE;
			}
		}
		return null;
	}

	/**
	 * Returns the single stock for the given abbreviation.
	 *
	 * @param abb Abbreviation.
	 * @return Stock.
	 */
	public SingleStock getStockByAbbreviation(String abb) {
		return getStockByName(getNameForAbbreviation(abb));
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

	/**
	 * Returns the name for the given abbreviation.
	 *
	 * @param abb Abbreviation.
	 * @return Name.
	 */
	public String getNameForAbbreviation(String abb) {
		return nameAbbHT[keyIndex(abb)];
	}
}
