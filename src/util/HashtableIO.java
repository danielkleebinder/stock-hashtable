package util;

import data.SingleStock;
import data.StockDataset;
import data.StockHashtable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The hashtable input and output class is a utility class for reading and
 * writing the current state of a stock hashtable as XML.
 *
 * @author Daniel Kleebinder
 */
public class HashtableIO {

	// Date formatter
	private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	// Element and attribute names
	private static final String ROOT = "table";
	private static final String CAPACITY = "capacity";
	private static final String THRESHOLD = "threshold";
	private static final String STOCK = "stock";
	private static final String DATASET = "dataset";
	private static final String NAME = "name";
	private static final String ABBREVIATION = "abbreviation";
	private static final String DATE = "date";
	private static final String OPEN = "open";
	private static final String HIGH = "high";
	private static final String LOW = "low";
	private static final String CLOSE = "close";
	private static final String VOLUME = "volume";
	private static final String ADJCLOSE = "adjclose";

	/**
	 * Exports the given hashtable as XML file.
	 *
	 * @param sht Stock hashtable.
	 * @param output Output stream.
	 *
	 * @throws ParserConfigurationException Can occur while parsing XML.
	 * @throws TransformerException Can occur while writing XML.
	 */
	public static void exportTable(StockHashtable sht, OutputStream output) throws ParserConfigurationException, TransformerException {
		List<SingleStock> data = sht.asList();

		// Create document builder factory
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		// Root Element
		Document document = documentBuilder.newDocument();
		Element root = document.createElement(ROOT);
		root.setAttribute(CAPACITY, Integer.toString(sht.getCapacity()));
		root.setAttribute(THRESHOLD, Integer.toString(sht.getThreshold()));
		document.appendChild(root);

		// Store table
		for (SingleStock stock : data) {
			Element stockElement = document.createElement(STOCK);
			stockElement.setAttribute(NAME, stock.getName());
			stockElement.setAttribute(ABBREVIATION, stock.getAbbreviation());
			root.appendChild(stockElement);

			for (StockDataset dataset : stock.getStockdata()) {
				Element datasetElement = document.createElement(DATASET);
				datasetElement.setAttribute(DATE, FORMATTER.format(dataset.getDate()));
				datasetElement.setAttribute(OPEN, Double.toString(dataset.getOpen()));
				datasetElement.setAttribute(HIGH, Double.toString(dataset.getHigh()));
				datasetElement.setAttribute(LOW, Double.toString(dataset.getLow()));
				datasetElement.setAttribute(CLOSE, Double.toString(dataset.getClose()));
				datasetElement.setAttribute(VOLUME, Double.toString(dataset.getVolume()));
				datasetElement.setAttribute(ADJCLOSE, Double.toString(dataset.getAdjClose()));

				stockElement.appendChild(datasetElement);
			}
		}

		// Write XML
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(output);

		transformer.transform(source, result);
	}

	/**
	 * Imports a stock hashtable from the given input stream.
	 *
	 * @param input Input stream.
	 * @return Stock hashtable.
	 *
	 * @throws IOException Can occur while reading XML.
	 * @throws SAXException Can occur while parsing XML.
	 * @throws ParserConfigurationException Can occur while parsing XML.
	 * @throws ParseException Can occur while parsing XML.
	 */
	public static StockHashtable importTable(InputStream input) throws IOException, SAXException, ParserConfigurationException, ParseException {
		// Create document builder factory
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(input);

		// Normalize root element
		document.getDocumentElement().normalize();

		// Fetch root element
		Element root = document.getDocumentElement();

		// Create hashtable with specific capacity and threashold
		StockHashtable result = new StockHashtable(
				Integer.parseInt(root.getAttribute(CAPACITY)),
				Integer.parseInt(root.getAttribute(THRESHOLD))
		);

		// Read all hashtable information
		NodeList stockNodeList = document.getElementsByTagName(STOCK);
		for (int i = 0; i < stockNodeList.getLength(); i++) {
			Node stockNode = stockNodeList.item(i);
			if (stockNode.getNodeType() == Node.ELEMENT_NODE) {
				SingleStock stock = new SingleStock();

				Element stockElement = (Element) stockNode;
				stock.setName(stockElement.getAttribute(NAME));
				stock.setAbbreviation(stockElement.getAttribute(ABBREVIATION));

				NodeList datasetNodeList = stockElement.getElementsByTagName(DATASET);
				for (int j = 0; j < datasetNodeList.getLength(); j++) {
					Node datasetNode = datasetNodeList.item(j);
					if (datasetNode.getNodeType() == Node.ELEMENT_NODE) {
						StockDataset dataset = new StockDataset();

						Element datasetElement = (Element) datasetNode;
						dataset.setDate(FORMATTER.parse(datasetElement.getAttribute(DATE)));
						dataset.setOpen(Double.parseDouble(datasetElement.getAttribute(OPEN)));
						dataset.setHigh(Double.parseDouble(datasetElement.getAttribute(HIGH)));
						dataset.setLow(Double.parseDouble(datasetElement.getAttribute(LOW)));
						dataset.setClose(Double.parseDouble(datasetElement.getAttribute(CLOSE)));
						dataset.setVolume(Double.parseDouble(datasetElement.getAttribute(VOLUME)));
						dataset.setAdjClose(Double.parseDouble(datasetElement.getAttribute(ADJCLOSE)));

						stock.getStockdata().add(dataset);
					}
				}

				result.putStock(stock);
			}
		}

		return result;
	}
}
