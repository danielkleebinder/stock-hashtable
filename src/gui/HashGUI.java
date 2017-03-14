package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.SingleStock;
import data.StockHashtable;

public class HashGUI extends JFrame {

	private JPanel contentPane;

	private StockHashtable sht;
	InputDialog id = new InputDialog();
	
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Custom code
		this.setLocationRelativeTo(null);
		id.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		id.setLocationRelativeTo(this);
		id.setAlwaysOnTop(true);

		sht = new StockHashtable();
		
	}
	
	//"map" this to a button
	public void addStock(String name, SingleStock stock) {
		//TODO: run method only if stock is new!
		//sht.putStockByName(id.getName(), id.getStock());
	}
	
}
