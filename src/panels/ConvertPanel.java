package panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import conversions.*;
/**
 * After deleting Cardlayout functionality, convertPanel is now implements JFrame instead of JPanel.
 * This frame acts as the converter between decimal, binary, hexadecimal, and octal.
 * @author Matthew Corless
 *
 */
public class ConvertPanel extends JFrame
{
	private JTextField decField, binField, octField, hexField;
	private JLabel errorLbl;
	private JButton convertBtn;
	private Decimal dec;
	private Binary bin;
	private Hexadecimal hex;
	private Octal oct;
	private String[] result;
	private String input;
	private JButton showBtn;
	private int type;
	private Font myFont = new Font("Cambria", Font.BOLD, 24);
	private Font smallerFont = new Font("Cambria", Font.BOLD, 16);
	
	/**
	 * Create the panel.
	 */
	public ConvertPanel() 
	{
		setLayout(null);
		
		JLabel decLbl = new JLabel("Decimal");
		decLbl.setBounds(20, 20, 150, 30);
		decLbl.setHorizontalAlignment(SwingConstants.LEFT);
		decLbl.setFont(myFont);
		add(decLbl);
		
		JLabel binLbl = new JLabel("Binary");
		binLbl.setBounds(20, 60, 150, 30);
		binLbl.setHorizontalAlignment(SwingConstants.LEFT);
		binLbl.setFont(myFont);
		add(binLbl);
		
		JLabel hexLbl = new JLabel("Hexadecimal");
		hexLbl.setBounds(20, 100, 150, 30);
		hexLbl.setHorizontalAlignment(SwingConstants.LEFT);
		hexLbl.setFont(myFont);
		add(hexLbl);
		
		JLabel octLbl = new JLabel("Octal");
		octLbl.setBounds(20, 140, 150, 30);
		octLbl.setHorizontalAlignment(SwingConstants.LEFT);
		octLbl.setFont(myFont);
		add(octLbl);
		
		JLabel maxLbl = new JLabel("Max: 2147483647");
		maxLbl.setFont(new Font("Cambria", Font.BOLD, 12));
		maxLbl.setBounds(170, 5, 100, 10);
		add(maxLbl);
		
		decField = new JTextField();
		decField.setFont(smallerFont);
		decField.setToolTipText("Decimal input");
		decField.setBounds(170, 20, 250, 30);
		decField.setActionCommand("clear");
		decField.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mousePressed(MouseEvent e)
            {
                clearFields();
            }
        });
		add(decField);
		decField.setColumns(10);
		
		binField = new JTextField();
		binField.setFont(smallerFont);
		binField.setToolTipText("binary input");
		binField.setBounds(170, 60, 250, 30);
		add(binField);
		binField.setColumns(10);
		binField.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mousePressed(MouseEvent e)
            {
                clearFields();
            }
        });
		
		hexField = new JTextField();
		hexField.setFont(smallerFont);
		hexField.setToolTipText("hexadecimal input");
		hexField.setBounds(170, 100, 250, 30);
		add(hexField);
		hexField.setColumns(10);
		hexField.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mousePressed(MouseEvent e)
            {
                clearFields();
            }
        });
		
		octField = new JTextField();
		octField.setFont(smallerFont);
		octField.setToolTipText("octal input");
		octField.setBackground(new Color(255, 255, 255));
		octField.setForeground(new Color(0, 0, 0));
		octField.setBounds(170, 140, 250, 30);
		add(octField);
		octField.setColumns(10);
		octField.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mousePressed(MouseEvent e)
            {
                clearFields();
            }
        });
		
		convertBtn = new JButton("Convert");
		convertBtn.setToolTipText("Convert your current input.");
		convertBtn.setFont(myFont);
		convertBtn.setBounds(145, 220, 150, 50);
		convertBtn.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						convertInput();
					}
			
				});
		add(convertBtn);
		
		
		errorLbl = new JLabel("New label");
		errorLbl.setForeground(Color.RED);
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorLbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		errorLbl.setBounds(90, 185, 250, 20);
		errorLbl.setVisible(false);
		add(errorLbl);
		
		showBtn = new JButton("Show me How!");
		showBtn.setFont(smallerFont);
		add(showBtn);
		showBtn.setBounds(145, 290, 150, 30);
		showBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new ShowPanel();
			}
	
		});
		showBtn.setVisible(false);
		
		setSize(450, 355);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
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
				type = 1;
				dec = new Decimal(decField.getText());
				if (dec.isValid(input))
				{
					result = dec.convertDecimal();
					binField.setText(result[0]);	
					hexField.setText(result[1]);	
					octField.setText(result[2]);
					showBtn.setVisible(true);
				} else
					invalid("");
			} else if (!binField.getText().equals(""))
			{
				input = binField.getText();
				type = 2;
				bin = new Binary(binField.getText());
				if (bin.isValid(input))
				{
					result = bin.convertBinary();
					decField.setText(result[0]);	
					hexField.setText(result[1]);	
					octField.setText(result[2]);
					showBtn.setVisible(true);
				} else
					invalid("");
			} else if (!hexField.getText().equals(""))
			{
				input = hexField.getText();
				type = 3;
				hex = new Hexadecimal(hexField.getText());
				if (hex.isValid(input))
				{
					result = hex.convertHex();
					decField.setText(result[0]);	
					binField.setText(result[1]);	
					octField.setText(result[2]);
					showBtn.setVisible(true);
				} else
					invalid("");
			} else if(!octField.getText().equals(""))
			{
				input = octField.getText();
				type = 4;
				oct = new Octal(octField.getText());
				if (oct.isValid(input))
				{
					result = oct.convertOctal();
					decField.setText(result[0]);	
					binField.setText(result[1]);	
					hexField.setText(result[2]);
					showBtn.setVisible(true);
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
		showBtn.setVisible(false);
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