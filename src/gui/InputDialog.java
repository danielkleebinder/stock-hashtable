package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class InputDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfAbb;

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
			JButton btnBrowse = new JButton("Browse");
			contentPanel.add(btnBrowse, BorderLayout.SOUTH);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void sendInputDataToHashStocks() {
		/*TODO
		 * Step 1: take name and abbreviation (user input)
		 * Step 2: parse csv data (find file with JFileChooser, try-with-resources!)
		 * Step 3: send file path to parser (has to be platform independent!?)
		 * Step 4: make new SingleStock
		 * Step 5: send it into the StockHashTable
		*/
	}
	

}
