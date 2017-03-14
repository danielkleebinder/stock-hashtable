package data;

import utils.StringUtils;

/**
 * Main stock hashtable class.
 *
 * @author Daniel Kleebinder, Christoph Rippel
 */
public class StockHashtable {

	private final int THRESHOLD = 512;
	private final int CAPACITY = 1000 + THRESHOLD;

	private final SingleStock[] stockHT = new SingleStock[CAPACITY];
	private final String[] nameAbbHT = new String[CAPACITY];

	private int size = 0;

	/**
	 * Creates a new stock hashtable.
	 */
	public StockHashtable() {
	}

	/**
	 * Puts the given stock using the given name and abbreviation.
	 *
	 * @param name Name.
	 * @param abb Abbreviation.
	 * @param st Stock.
	 * @return True if added successfully, otherwise false.
	 */
	public boolean putStock(String name, String abb, SingleStock st) {
		if (isFull()) {
			return false;
		}
		putNameByAbbreviation(name, abb);
		return putStockByName(name, st);
	}

	/**
	 * Puts the given stock using the given name.
	 *
	 * @param name Name.
	 * @param st Stock.
	 * @return True if added successfully, otherwise false.
	 */
	@SuppressWarnings("unused")
	public boolean putStockByName(String name, SingleStock st) {
		if (isFull()) {
			return false;
		}

		int index = keyIndex(name);

		while (stockHT[index] != null) {
			// Check if the entry is already in the hashtable
			if (name.equals(stockHT[index].getName())) {
				return false;
			}

			// Use linear probing
			index = (index + 1) % CAPACITY;
		}

		// Add item
		stockHT[index] = st;
		size++;
		return true;
	}

	/**
	 * Puts the given stock using the given abbreviation.
	 *
	 * @param abb Abbreviation.
	 * @param st Stock.
	 * @return True if added successfully, otherwise false.
	 */
	public boolean putStockByAbbreviation(String abb, SingleStock st) {
		return putStockByName(getNameForAbbreviation(abb), st);
	}

	//
	/**
	 * Save certain stock by using its abbreviation as key. This way stocks are
	 * saved once, although another table for name:abbreviations is required
	 *
	 * @param name Name.
	 * @param abb Abbreviation.
	 * @return True if added successfully, otherwise false.
	 */
	@SuppressWarnings("unused")
	public boolean putNameByAbbreviation(String name, String abb) {
		if (isFull()) {
			return false;
		}

		int index = keyIndex(abb);

		while (nameAbbHT[index] != null) {
			// Check if the entry is already in the hashtable
			if (name.equals(nameAbbHT[index])) {
				return false;
			}

			// Use linear probing
			index = (index + 1) % CAPACITY;
		}

		// Add item
		nameAbbHT[index] = name;
		size++;
		return true;
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
				index = (index + 1) % CAPACITY;
			}
		}

		// Move all items after the deleted one
		for (int j = (index + 1) % CAPACITY; stockHT[j] != null; j = (j + 1) % CAPACITY) {
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
				index = (index + 1) % CAPACITY;
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
		return StringUtils.hashCode(str) % CAPACITY;
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

	/**
	 * Returns if the hashtable is full.
	 *
	 * @return True if full, otherwise false.
	 */
	public boolean isFull() {
		return size >= CAPACITY;
	}

	/**
	 * Returns if the hashtable is empty.
	 *
	 * @return True if empty, otherwise false.
	 */
	public boolean isEmpty() {
		return size <= 0;
	}
}
