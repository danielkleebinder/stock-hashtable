package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.SingleStock;
import data.StockHashtable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HashGUI extends JFrame {

	private JPanel contentPane;

	private StockHashtable sht; //Source for table data
	InputDialog id = new InputDialog(); //source for stockhashtable
	DefaultTableModel dtm; //used for displaying stock
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		//Table
		JTable table = new JTable();
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		tabbedPane.addTab("Table", null, scrollPane, null);
		table.setFillsViewportHeight(true);
		//String abbreviation, String name, String date,
		//double open, double high, double low, double close, int volume,
		//double adjClose
		dtm = new DefaultTableModel(30, 7);
		table.setModel(dtm);
		Object[] columnIdentifiers = {"Date", "Open", "High", "Low", "Close", "Volume", "adjClose"};
		dtm.setColumnIdentifiers(columnIdentifiers);		
		
		
		JPanel pGraph = new JPanel();
		tabbedPane.addTab("Graph", null, pGraph, null);
		
		JPanel pMenu = new JPanel();
		pMenu.setBorder(null);
		contentPane.add(pMenu, BorderLayout.NORTH);
		pMenu.setLayout(new GridLayout(0, 1, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		pMenu.add(menuBar);
		
		JMenu mnFile = new JMenu(" File ");
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.setMnemonic('f');
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenuItem mntmImport = new JMenuItem("Import New Stock");
		mntmImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputDialog indi = new InputDialog();
				indi.setModal(true);
				indi.setVisible(true);
				//System.out.println(indi.getName());
				sht.putStockByName(indi.getName(), indi.getStock());
			}
		});
		menuBar.add(mntmImport);
		
		JMenu mnExport = new JMenu(" Export ");
		mnExport.setMnemonic('e');
		mnExport.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnExport);
		
		JMenuItem mntmKappa = new JMenuItem("Kappa");
		mnExport.add(mntmKappa);
		
		
		
		//Custom code
		sht = new StockHashtable();
		this.setLocationRelativeTo(null);
	}
	
		
}
