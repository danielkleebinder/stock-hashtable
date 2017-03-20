package data;

import java.util.ArrayList;
import java.util.List;
import util.StringUtils;

/**
 * A hashtable for storing stock data very efficient.
 *
 * @author Daniel Kleebinder, Christoph Rippel
 */
public class StockHashtable {

	// Threshold and capacity
	private int threshold;
	private int capacity;
	private int maxCapacity;

	// Data storage
	private SingleStock[] stockHT;
	private String[] nameAbbHT;

	// Current size
	private int size = 0;

	/**
	 * Creates a new stock hashtable.
	 */
	public StockHashtable() {
		this(1024, 512);
	}

	/**
	 * Creates a new hashtable with the given capacity and threshold.
	 *
	 * @param capacity Capacity.
	 * @param threshold Threshold.
	 */
	public StockHashtable(int capacity, int threshold) {
		this.capacity = capacity;
		this.threshold = threshold;
		maxCapacity = capacity + threshold;

		stockHT = new SingleStock[maxCapacity];
		nameAbbHT = new String[maxCapacity];
	}

	/**
	 * Returns the threshold.
	 *
	 * @return Threshold.
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * Returns the capacity.
	 *
	 * @return Capacity.
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Returns the max capacity (capacity + threshold).
	 *
	 * @return Max capacity.
	 */
	public int getMaxCapacity() {
		return maxCapacity;
	}

	/**
	 * Puts the given stock into the hashtable.
	 *
	 * @param st Stock.
	 * @return True if added successfully, otherwise false.
	 */
	public boolean putStock(SingleStock st) {
		return putStock(st.getName(), st.getAbbreviation(), st);
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

			// Use squared probing
			index = squaredProbing(index);
		}

		// Add item
		stockHT[index] = st;

		// Add abbreviation if one exists
		if (st.getAbbreviation() != null) {
			putNameByAbbreviation(name, st.getAbbreviation());
		}

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

	/**
	 * Save certain stock by using its abbreviation as key. This way stocks are
	 * saved once, although another table for name:abbreviations is required
	 *
	 * @param name Name.
	 * @param abb Abbreviation.
	 * @return True if added successfully, otherwise false.
	 */
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

			// Use squared probing
			index = squaredProbing(index);
		}

		// Add item
		nameAbbHT[index] = name;
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
				// Use squared probing
				index = squaredProbing(index);
			}
		}

		// Move all items after the deleted one
		for (int j = (index + 1) % maxCapacity; stockHT[j] != null; j = (j + 1) % maxCapacity) {
			buffer = stockHT[j];
			stockHT[j] = null;
			putStockByName(name, buffer);
		}

		size--;

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
				// Use squared probing
				index = squaredProbing(index);
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
	 * Returns true if the table contains the stock for the given name.
	 *
	 * @param name Name.
	 * @return True if its in the table, otherwise false.
	 */
	public boolean containsStockByName(String name) {
		return getStockByName(name) != null;
	}

	/**
	 * Returns true if the table contains the stock for the given abbreviation.
	 *
	 * @param abb Abbreviation.
	 * @return True if its in the table, otherwise false.
	 */
	public boolean containsStockByAbbreviation(String abb) {
		return getStockByAbbreviation(abb) != null;
	}

	/**
	 * Returns the stock hashtable as a simple list.
	 *
	 * @return List with all single stock values in it.
	 */
	public List<SingleStock> asList() {
		List<SingleStock> result = new ArrayList<>(size);
		for (int i = 0; i < stockHT.length; i++) {
			if (stockHT[i] == null) {
				continue;
			}
			result.add(stockHT[i]);
		}
		return result;
	}

	/**
	 * Returns the global index of the given string.
	 *
	 * @param str String.
	 * @return Hash table index.
	 */
	private int keyIndex(String str) {
		if (str == null) {
			throw new NullPointerException("Name or abbreviation is null");
		}
		return StringUtils.hashCode(str) % maxCapacity;
	}

	/**
	 * Returns the name for the given abbreviation.
	 *
	 * @param abb Abbreviation.
	 * @return Name.
	 */
	public String getNameForAbbreviation(String abb) {
		String result = nameAbbHT[keyIndex(abb)];
		if (result == null) {
			throw new NullPointerException("No corresponding name for " + abb);
		}
		return result;
	}

	/**
	 * Clears and deletes all entries.
	 */
	public void clear() {
		stockHT = new SingleStock[maxCapacity];
		nameAbbHT = new String[maxCapacity];
		size = 0;
	}

	/**
	 * Returns if the hashtable is full.
	 *
	 * @return True if full, otherwise false.
	 */
	public boolean isFull() {
		return size >= maxCapacity;
	}

	/**
	 * Returns if the hashtable is empty.
	 *
	 * @return True if empty, otherwise false.
	 */
	public boolean isEmpty() {
		return size <= 0;
	}

	/**
	 * Returns the current size.
	 *
	 * @return Size.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Linear probing is a simple and very performant algorithm for inserting
	 * new elements.
	 *
	 * @param index Current index.
	 * @return Probed index.
	 */
	private int linearProbing(int index) {
		return (index + 1) % maxCapacity;
	}

	/**
	 * Squared probing can be more performant than linear probing by using a
	 * squared algorithm.
	 *
	 * @param index Current index.
	 * @return Probed index.
	 */
	private int squaredProbing(int index) {
		return ((index + 1) * (index + 1)) % maxCapacity;
	}
}
