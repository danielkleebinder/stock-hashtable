package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import data.StockHashtable;
import java.awt.Font;
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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import util.HashtableIO;

public class HashGUI extends JFrame {

	private StockHashtable sht; //Source for table data
	InputDialog id = new InputDialog(); //source for stockhashtable
	DefaultTableModel dtm; //used for displaying stock

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
		setBounds(100, 100, 450, 453);

		JPanel centerContentPane = new JPanel(new BorderLayout());

		JLabel stockTitle = new JLabel("< Nothing Selected >");
		stockTitle.setFont(stockTitle.getFont().deriveFont(Font.BOLD, 14.0f));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		//Stock Data stored here____________________________________________________________________________
		sht = new StockHashtable();
		this.setLocationRelativeTo(null);

		//Table custom code
		JTable table = new JTable();
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		tabbedPane.addTab("Table", null, scrollPane, null);
		table.setFillsViewportHeight(true);

		dtm = new DefaultTableModel(30, 7);
		table.setModel(dtm);
		Object[] columnIdentifiers = {"Date", "Open", "High", "Low", "Close", "Volume", "adjClose"};
		dtm.setColumnIdentifiers(columnIdentifiers);
		//_________________________________________________________________________________________________

//		JFreeChart lineChart = ChartFactory.createLineChart(stock.getName() + "(" + stock.getAbbreviation() + ")", "Days", "€ EUR", dataset);
//		ChartPanel chartPanel = new ChartPanel(lineChart);
//		tabbedPane.addTab("Graph", null, chartPanel, null);
		JMenuBar menuBar = new JMenuBar();

		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener((ActionEvent e) -> {
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

		JMenuItem mntmSaveAs = new JMenuItem("Save As ...");
		mntmSaveAs.addActionListener((ActionEvent e) -> {
			saveFile = saveAsDialog();
			if (saveFile == null) {
				return;
			}
			if (!saveHashtable(saveFile)) {
				JOptionPane.showConfirmDialog(null, "Cannot save file!", "Save", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		});

		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener((ActionEvent e) -> {
			loadFile = loadDialog();
			if (loadFile == null) {
				return;
			}
			if (!loadHashtable(loadFile)) {
				JOptionPane.showConfirmDialog(null, "Cannot load file!", "Load", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		});

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		mnFile.add(mntmSave);
		mnFile.add(mntmSaveAs);
		mnFile.add(mntmLoad);
		mnFile.add(new JPopupMenu.Separator());
		mnFile.add(mntmExit);

		JMenu edit = new JMenu("Edit");

		JMenuItem selectStock = new JMenuItem("Select Stock");
		JMenuItem mntmImport = new JMenuItem("Import New Stock");
		mntmImport.addActionListener((ActionEvent e) -> {
			InputDialog indi = new InputDialog();
			indi.setModal(true);
			indi.setVisible(true);
			sht.putStock(indi.getStock());
		});
		JMenuItem deleteStock = new JMenuItem("Delete Stock");

		edit.add(selectStock);
		edit.add(mntmImport);
		edit.add(deleteStock);

		menuBar.add(mnFile);
		menuBar.add(edit);

		centerContentPane.add(stockTitle, BorderLayout.NORTH);
		centerContentPane.add(tabbedPane, BorderLayout.CENTER);

		getContentPane().add(menuBar, BorderLayout.NORTH);
		getContentPane().add(centerContentPane, BorderLayout.CENTER);
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
}
