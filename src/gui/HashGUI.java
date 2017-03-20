package gui;

import data.SingleStock;
import data.StockDataset;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import data.StockHashtable;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;
import org.xml.sax.SAXException;
import util.HashtableIO;

public class HashGUI extends JFrame {

	private StockHashtable sht; //Source for table data
	private SingleStock selectedStock;
	DefaultTableModel dtm; //used for displaying stock

	private String documentName;

	private JLabel stockTitle;
	private ChartPanel chartPanel;

	private File saveFile;
	private File loadFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			Logger.getLogger(HashGUI.class.getName()).log(Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HashGUI frame = new HashGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HashGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 650);

		documentName = createTitleByName(null);
		setTitle(documentName);

		JPanel centerContentPane = new JPanel(new BorderLayout());

		stockTitle = new JLabel("< Nothing Selected >");
		stockTitle.setPreferredSize(new Dimension(300, 50));
		stockTitle.setFont(stockTitle.getFont().deriveFont(Font.BOLD, 18.0f));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		sht = new StockHashtable();
		this.setLocationRelativeTo(null);

		//Table custom code
		JTable table = new JTable();
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		tabbedPane.addTab("Table", null, scrollPane, null);
		table.setFillsViewportHeight(true);

		dtm = new DefaultTableModel(40, 7);
		table.setModel(dtm);
		Object[] columnIdentifiers = {"Date", "Open", "High", "Low", "Close", "Volume", "adjClose"};
		dtm.setColumnIdentifiers(columnIdentifiers);

		chartPanel = new ChartPanel(null);
		tabbedPane.addTab("Graph", null, chartPanel, null);

		JMenuBar menuBar = new JMenuBar();

		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');

		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
		save.addActionListener((ActionEvent e) -> {
			if (saveFile == null) {
				saveFile = saveAsDialog();
			}
			if (saveFile == null) {
				return;
			}
			if (!saveHashtable(saveFile)) {
				JOptionPane.showConfirmDialog(null, "Cannot save file!", "Save", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		});

		JMenuItem saveAs = new JMenuItem("Save As...");
		saveAs.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK | Event.SHIFT_MASK));
		saveAs.addActionListener((ActionEvent e) -> {
			saveFile = saveAsDialog();
			if (saveFile == null) {
				return;
			}
			documentName = createTitleByName(saveFile.getName());
			if (!saveHashtable(saveFile)) {
				JOptionPane.showConfirmDialog(null, "Cannot save file!", "Save", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
			setTitle(documentName);
		});

		JMenuItem load = new JMenuItem("Load...");
		load.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		load.addActionListener((ActionEvent e) -> {
			loadFile = loadDialog();
			if (loadFile == null) {
				return;
			}
			saveFile = new File(loadFile.getPath());
			documentName = createTitleByName(saveFile.getName());
			if (!loadHashtable(loadFile)) {
				JOptionPane.showConfirmDialog(null, "Cannot load file!", "Load", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			setTitle(documentName);
		});

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		mnFile.add(save);
		mnFile.add(saveAs);
		mnFile.add(load);
		mnFile.add(new JPopupMenu.Separator());
		mnFile.add(mntmExit);

		JMenu edit = new JMenu("Edit");

		JMenuItem editStockArchive = new JMenuItem("Edit Stock Archive...");
		editStockArchive.setAccelerator(KeyStroke.getKeyStroke('E', Event.CTRL_MASK));
		editStockArchive.addActionListener((ActionEvent e) -> {
			SelectionDialog selectionDialog = new SelectionDialog(sht);
			selectionDialog.setVisible(true);

			SingleStock selected = selectionDialog.getSelectedStock();
			if (selected != null) {
				selectedStock = selected;
				updateGUI();
			}
		});

		edit.add(editStockArchive);

		menuBar.add(mnFile);
		menuBar.add(edit);

		centerContentPane.add(stockTitle, BorderLayout.NORTH);
		centerContentPane.add(tabbedPane, BorderLayout.CENTER);

		getContentPane().add(menuBar, BorderLayout.NORTH);
		getContentPane().add(centerContentPane, BorderLayout.CENTER);
	}

	/**
	 * Updates the GUI.
	 */
	private void updateGUI() {
		// Set stock title
		stockTitle.setText(selectedStock.toString());

		// Set chart data
		double lowest = Double.MAX_VALUE;
		double highest = Double.MIN_VALUE;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = selectedStock.getStockdata().size() - 1; i >= 0; i--) {
			StockDataset sds = selectedStock.getStockdata().get(i);
			String formatted = new SimpleDateFormat("dd.MM").format(sds.getDate());

			dataset.addValue((Number) sds.getOpen(), "Open", formatted);
			dataset.addValue((Number) sds.getClose(), "Close", formatted);
			dataset.addValue((Number) sds.getLow(), "Low", formatted);
			dataset.addValue((Number) sds.getHigh(), "High", formatted);

			lowest = (lowest > sds.getLow()) ? sds.getLow() : lowest;
			highest = (highest < sds.getHigh()) ? sds.getHigh() : highest;
		}

		JFreeChart lineChart = ChartFactory.createLineChart(selectedStock.toString(), "Days", "€ EUR", dataset);
		lineChart.setAntiAlias(true);
		lineChart.getCategoryPlot().getRangeAxis().setRange(lowest * 0.99, highest * 1.01);
		((NumberAxis) lineChart.getCategoryPlot().getRangeAxis()).setAutoRangeIncludesZero(true);
		((NumberAxis) lineChart.getCategoryPlot().getRangeAxis()).setAutoRangeStickyZero(true);
		chartPanel.setChart(lineChart);

		// Set table data
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);

		StockDataset sds;
		for (int i = 0; i < selectedStock.getStockdata().size(); i++) {
			sds = selectedStock.getStockdata().get(i);
			dtm.setValueAt(sdf.format(sds.getDate()), i, 0);
			dtm.setValueAt(nf.format(sds.getOpen()), i, 1);
			dtm.setValueAt(nf.format(sds.getHigh()), i, 2);
			dtm.setValueAt(nf.format(sds.getLow()), i, 3);
			dtm.setValueAt(nf.format(sds.getClose()), i, 4);
			dtm.setValueAt(nf.format(sds.getVolume()), i, 5);
			dtm.setValueAt(nf.format(sds.getAdjClose()), i, 6);
		}
	}

	/**
	 * Shows the save as dialog and returns the file name.
	 *
	 * @return File name.
	 */
	private File saveAsDialog() {
		JFileChooser jfc = new JFileChooser(saveFile);
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.addChoosableFileFilter(new FileNameExtensionFilter("Stock Hashtable", "sht"));
		if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile();
		}
		return null;
	}

	/**
	 * Shows the load dialog and returns the file name.
	 *
	 * @return File name.
	 */
	private File loadDialog() {
		JFileChooser jfc = new JFileChooser(loadFile);
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.addChoosableFileFilter(new FileNameExtensionFilter("Stock Hashtable", "sht"));
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile();
		}
		return null;
	}

	/**
	 * Saves the current state of the hashtable.
	 *
	 * @param file File.
	 * @return True if successfully saved, otherwise false.
	 */
	private boolean saveHashtable(File file) {
		try {
			HashtableIO.exportTable(sht, new FileOutputStream(file));
		} catch (FileNotFoundException | ParserConfigurationException | TransformerException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Loads a hashtable.
	 *
	 * @param file File.
	 * @return True if successfully loaded, otherwise false.
	 */
	private boolean loadHashtable(File file) {
		try {
			sht = HashtableIO.importTable(new FileInputStream(file));
		} catch (IOException | SAXException | ParseException | ParserConfigurationException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Creates the window title by the given file name.
	 *
	 * @param fileName File name.
	 * @return Window title.
	 */
	private String createTitleByName(String fileName) {
		StringBuilder result = new StringBuilder();
		if (fileName != null && !fileName.isEmpty()) {
			result.append(fileName);
			result.append(" - ");
		}
		result.append("Stock HashTable GUI 1.0.0");
		return result.toString();
	}
}
