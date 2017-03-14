package data;

public class StockHashtable {
	
	private SingleStock[] stockHT = new SingleStock[1000]; //Hashtable: Key = Name, Value = SingleStock
	private String[] nameAbbHT = new String[1000]; //Hashtable: Key = Abbreviation, Value = Name
	
	//Calculate index for String keys 
	private int calculateKey(String value) {
		int result = 100;
		result = Integer.parseInt(value)*result + 37;
		return result;
	}
	
	//Save certain stock by using its name as key
	@SuppressWarnings("unused")
	public void putStockByName (String name, SingleStock st) 
	{
		int index = calculateKey(name);
		if(stockHT[index].equals(null))
			stockHT[index] = st;
		else {
		   //TODO: collision handling
		} 
	} 
	
	//Save certain stock by using its abbreviation as key, calls putStockByName()
	//This way stocks are saved once, although another table for name:abbreviations is required
	@SuppressWarnings("unused")
	public void putNameByAbbreviation (String name, String abb) {
		int index = calculateKey(name);
		if(nameAbbHT[index].isEmpty()) {
			nameAbbHT[index] = abb;
		}
		else {
			//TODO: collision handling
		}
	}
	
	//Evil?  ¯\(ツ)/¯ 
	public SingleStock getStockByName(String name) {
		return stockHT[calculateKey(name)];
	}
	
	public SingleStock getStockByAbbreviation(String abb) {
		return getStockByName(nameAbbHT[calculateKey(abb)]);
	}
}
