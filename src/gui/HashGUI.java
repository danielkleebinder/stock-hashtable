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
import javax.swing.JSeparator;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HashGUI extends JFrame {

	private StockHashtable sht; //Source for table data
	InputDialog id = new InputDialog(); //source for stockhashtable
	DefaultTableModel dtm; //used for displaying stock

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
		JMenuItem mntmLoad = new JMenuItem("Load");
		JSeparator separator = new JSeparator();

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener((ActionEvent evt) -> {
			System.exit(0);
		});

		mnFile.add(mntmSave);
		mnFile.add(mntmLoad);
		mnFile.add(separator);
		mnFile.add(mntmExit);

		JMenu navigation = new JMenu("Navigate");

		JMenuItem selectStock = new JMenuItem("Select Stock");
		JMenuItem mntmImport = new JMenuItem("Import New Stock");
		mntmImport.addActionListener((ActionEvent evt) -> {
			InputDialog indi = new InputDialog();
			indi.setModal(true);
			indi.setVisible(true);
			sht.putStock(indi.getStock());
		});

		navigation.add(selectStock);
		navigation.add(mntmImport);

		menuBar.add(mnFile);
		menuBar.add(navigation);

		centerContentPane.add(stockTitle, BorderLayout.NORTH);
		centerContentPane.add(tabbedPane, BorderLayout.CENTER);

		getContentPane().add(menuBar, BorderLayout.NORTH);
		getContentPane().add(centerContentPane, BorderLayout.CENTER);
	}
}