package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import conversions.*;
import old.ShowPanel;
/**
 * After deleting Cardlayout functionality, convertPanel is now implements JFrame instead of JPanel.
 * This frame acts as the converter between decimal, binary, hexadecimal, and octal.
 * @author Matthew Corless
 *
 */
public class ConvertPanel extends JFrame
{
	private static ConvertPanel instance = null;
	private JTextField decField, binField, octField, hexField;
	private JLabel errorLbl;
	private JButton convertBtn;
	private Decimal dec;
	private Binary bin;
	private Hexadecimal hex;
	private Octal oct;
	private String[] result;
	private String input;
	private Font myFont = new Font("Cambria", Font.BOLD, 24);
	private Font smallerFont = new Font("Cambria", Font.BOLD, 16);
	
	GridBagConstraints c = new GridBagConstraints();
	
	
	private ConvertPanel()
	{
		//exists to avoid instantiation
	}
	
	public static ConvertPanel getInstance()
	{
		if (instance == null)
		{
			instance = new ConvertPanel();
		}
		return instance;
	}
				
	
	public void cpInit()
	{
		setLayout(new GridBagLayout());
		
		initLabels();
		initTextFields();
		
		c.weightx = 0.7;
		c.weighty = 0.7;
		c.insets = new Insets (0, 20, 10, 20);
		convertBtn = new JButton("Convert");
		convertBtn.setToolTipText("Convert your current input.");
		convertBtn.setFont(myFont);
		convertBtn.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						convertInput();
					}
			
				});
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		add(convertBtn, c);
		
		
		errorLbl = new JLabel("New label");
		errorLbl.setForeground(Color.RED);
		errorLbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		errorLbl.setVisible(false);
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		add(errorLbl, c);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(300, 140));
		setPreferredSize(new Dimension(400, 250));
		pack();
		setVisible(true);
	}
	
	
	private void initLabels()
	{
		c.weightx = 0.3;
		c.weighty = 0.3;
		// label constants
		c.gridx = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(0, 10, 0, 0);
		
		
		JLabel decLbl = new JLabel("Decimal");
		decLbl.setFont(myFont);
		c.gridy = 0;
		add(decLbl, c);
		
		JLabel binLbl = new JLabel("Binary");
		binLbl.setFont(myFont);
		c.gridy = 1;
		add(binLbl, c);
		
		JLabel hexLbl = new JLabel("Hexadecimal");
		hexLbl.setFont(myFont);
		c.gridy = 2;
		add(hexLbl, c);
		
		JLabel octLbl = new JLabel("Octal");
		octLbl.setFont(myFont);
		c.gridy = 3;
		add(octLbl, c);
	}
	
	private void initTextFields()
	{
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		// constants for all textfields
		c.gridx = 1;
		c.gridwidth = 2;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 20);
		
		// Decimal TextField
		decField = new JTextField("Max: 2147483647");
		decField.setFont(smallerFont);
		decField.setToolTipText("Decimal Input");
		decField.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mousePressed(MouseEvent e)
            {
                clearFields();
            }
        });
		c.gridy = 0;
		add(decField, c);
		
		// Binary TextField
		binField = new JTextField();
		binField.setFont(smallerFont);
		binField.setToolTipText("Binary Input");
		binField.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mousePressed(MouseEvent e)
            {
                clearFields();
            }
        });
		c.gridy = 1;
		add(binField, c);
		
		// Hexadecimal TextField
		hexField = new JTextField();
		hexField.setFont(smallerFont);
		hexField.setToolTipText("Hexadecimal Input");
		hexField.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mousePressed(MouseEvent e)
            {
                clearFields();
            }
        });
		c.gridy = 2;
		add(hexField, c);
		
		// Octal TextField
		octField = new JTextField();
		octField.setFont(smallerFont);
		octField.setToolTipText("octal input");
		octField.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mousePressed(MouseEvent e)
            {
                clearFields();
            }
        });
		c.gridy = 3;
		add(octField, c);
	}

	/**
	 * Changes the text fields to the converted form of the user-input
	 */
	private void convertInput()
	{
		try
		{
			if (!decField.getText().equals(""))
			{
				input = decField.getText();
				dec = new Decimal(decField.getText());
				if (dec.isValid(input))
				{
					result = dec.convertDecimal();
					binField.setText(result[0]);	
					hexField.setText(result[1]);	
					octField.setText(result[2]);
				} else
					invalid("");
			} else if (!binField.getText().equals(""))
			{
				input = binField.getText();
				bin = new Binary(binField.getText());
				if (bin.isValid(input))
				{
					result = bin.convertBinary();
					decField.setText(result[0]);	
					hexField.setText(result[1]);	
					octField.setText(result[2]);
				} else
					invalid("");
			} else if (!hexField.getText().equals(""))
			{
				input = hexField.getText();
				hex = new Hexadecimal(hexField.getText());
				if (hex.isValid(input))
				{
					result = hex.convertHex();
					decField.setText(result[0]);	
					binField.setText(result[1]);	
					octField.setText(result[2]);
				} else
					invalid("");
			} else if(!octField.getText().equals(""))
			{
				input = octField.getText();
				oct = new Octal(octField.getText());
				if (oct.isValid(input))
				{
					result = oct.convertOctal();
					decField.setText(result[0]);	
					binField.setText(result[1]);	
					hexField.setText(result[2]);
				} else
					invalid("");
			} else
			{
				invalid(": all text fields are empty");
			}
		}
		catch (NumberFormatException e)
		{
			invalid("");
		}
	}
	
	/**
	 * Clears the text of every text field
	 */
	private void clearFields()
	{
		decField.setText("");
		binField.setText("");
		hexField.setText("");
		octField.setText("");
		errorLbl.setText("");
	}

	/**
	 * Creates error message for invalid inputs when attempting to convert
	 * @param s string that the user attempted to convert
	 */
	private void invalid(String s)
	{
		errorLbl.setVisible(true);
		errorLbl.setText("Invalid input" + s);
	}
}