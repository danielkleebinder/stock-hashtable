package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.SingleStock;
import data.StockHashtable;
import util.StockCsvParser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class InputDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfAbb;
	
	private File file;
	private JTextField tfFile;
	private String name, abbreviation;
	private SingleStock stock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InputDialog dialog = new InputDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InputDialog() {
		setTitle("Sieht doch ganz passabel aus");
		setBounds(100, 100, 450, 300);
		//Custom
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 15, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pInputs = new JPanel();
			pInputs.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 20, 0), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
			contentPanel.add(pInputs, BorderLayout.CENTER);
			pInputs.setLayout(new BorderLayout(0, 0));
			{
				JPanel pLeft = new JPanel();
				pLeft.setBorder(new EmptyBorder(0, 5, 0, 5));
				pInputs.add(pLeft, BorderLayout.WEST);
				pLeft.setLayout(new GridLayout(2, 0, 0, 5));
				{
					JLabel lbStockName = new JLabel("Stock Name");
					pLeft.add(lbStockName);
				}
				{
					JLabel lbAbb = new JLabel("Abbreviation");
					pLeft.add(lbAbb);
				}
			}
			{
				JPanel pCenter = new JPanel();
				pCenter.setBorder(new EmptyBorder(0, 5, 0, 0));
				pInputs.add(pCenter, BorderLayout.CENTER);
				pCenter.setLayout(new GridLayout(2, 0, 0, 5));
				{
					tfName = new JTextField();
					pCenter.add(tfName);
					tfName.setText("Microsoft Funky Town");
					tfName.setColumns(10);
				}
				{
					tfAbb = new JTextField();
					pCenter.add(tfAbb);
					tfAbb.setText("NSFW");
					tfAbb.setColumns(10);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//ON CONFIRMATION
						/*TODO
						 * Step 1: take name and abbreviation (user input)
						 * Step 2: parse csv data (find file with JFileChooser)
						 * Step 3: send file path to parser (has to be platform independent!?)
						 * Step 4: make new SingleStock
						 * Step 5: send it into the XXXStockHashTableXXX -> GUI instead
						*/
						StockCsvParser scp = null;
						String[] my7tokens;
						
						try {
							scp = new StockCsvParser(file);
							my7tokens = scp.getTokens();
							/*String abbreviation, String name, String date, 
							* double open, double high, double low, double close, int volume,
							* double adjClose
							*/
							name = tfName.getText();
							abbreviation = tfAbb.getText();
							
							stock = new SingleStock(abbreviation, name, my7tokens[0],
									Double.parseDouble(my7tokens[1]), Double.parseDouble(my7tokens[2]), Double.parseDouble(my7tokens[3]), Double.parseDouble(my7tokens[4]), Integer.parseInt(my7tokens[5]), 
									Double.parseDouble(my7tokens[6]));
						} catch (Exception e) {
							JOptionPane.showMessageDialog(InputDialog.this, "File could not be read!", "Parsing error", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			{
				JPanel pFileBrowser = new JPanel();
				contentPanel.add(pFileBrowser, BorderLayout.SOUTH);
				pFileBrowser.setLayout(new BorderLayout(5, 0));
				JButton btBrowse = new JButton("Browse");
				pFileBrowser.add(btBrowse, BorderLayout.EAST);
				{
					tfFile = new JTextField();
					tfFile.setEditable(false);
					pFileBrowser.add(tfFile, BorderLayout.CENTER);
					tfFile.setColumns(10);
				}
				btBrowse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						final JFileChooser chooser = new JFileChooser();
						try {
							if(chooser.showOpenDialog(InputDialog.this) == JFileChooser.APPROVE_OPTION) {
								chooser.setFileFilter(new FileNameExtensionFilter("CSV File","csv"));
							}
							file = chooser.getSelectedFile();
							tfFile.setText(file.getPath());
						}
						catch(Exception e) {
							JOptionPane.showMessageDialog(InputDialog.this, "Try again, I believe you can do it!",
									"Wrong file extension", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getAbb() {
		return abbreviation;
	}
	
	public SingleStock getStock() {
		return stock;
	}
}
