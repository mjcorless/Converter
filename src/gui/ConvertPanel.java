package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import conversions.*;
import old.ShowPanel;
/**
 * After deleting Cardlayout functionality, convertPanel is now implements JFrame instead of JPanel.
 * This frame acts as the converter between decimal, binary, hexadecimal, and octal.
 * @author Matthew Corless
 *
 */
public class ConvertPanel extends JPanel implements KeyListener
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
	private JFrame frame;
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
		frame = new JFrame("Converter");
		setLayout(new GridBagLayout());
		
		initLabels();
		initTextFields();	
		
		c.weightx = 0.3;
		c.weighty = 0.3;
		c.insets = new Insets(0, 10, 0, 0);
		errorLbl = new JLabel("New label");
		errorLbl.setForeground(Color.RED);
		errorLbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		errorLbl.setVisible(false);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		add(errorLbl, c);
		
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets (0, 20, 10, 20);
		convertBtn = new JButton("Convert");
		convertBtn.setToolTipText("Convert your current input.");
		convertBtn.setFont(myFont);
		convertBtn.setBackground(new Color(211, 234, 255));
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
		
		/*
		addComponentListener(new ComponentAdapter() 
		{  
		        public void componentResized(ComponentEvent event) 
		        {
		            //UpdateSize();
		        	
		        	Rectangle r = frame.getBounds();
		        	int h = r.height;
		        	int w = r.width;
		        	System.out.println("h: " + h + " w: " + w);
		        }
		});
		*/
		
		
		
		setBackground(new Color(233, 229, 255));
		frame.add(this);
		setTrayIcon();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(300, 140));
		frame.setPreferredSize(new Dimension(450, 250));
		frame.pack();
		convertBtn.requestFocus();
		frame.setVisible(true);
	}
	
	/**
	 * Set The tray Icon for the program. Different settings and operating systems
	 * can require different image sizes so a list is used.
	 */
	private void setTrayIcon() 
	{	
		List<Image> icons = new ArrayList<Image>();
		
		java.net.URL imgUrl = ConvertPanel.class.getResource("/resources/images/calculator16.png");
		icons.add(new ImageIcon(imgUrl).getImage());
		imgUrl = ConvertPanel.class.getResource("/resources/images/calculator32.png");
		icons.add(new ImageIcon(imgUrl).getImage());
		imgUrl = ConvertPanel.class.getResource("/resources/images/calculator64.png");
		icons.add(new ImageIcon(imgUrl).getImage());
		frame.setIconImages(icons);
	}

	private void initLabels()
	{
		c.weightx = 0.5;
		c.weighty = 0.5;
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
		// constants for all textfields
		c.weightx = 1.5;
		c.weighty = 1.5;
		c.gridx = 1;
		c.gridwidth = 2;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 20);
		
		// Decimal TextField
		decField = new JTextField("Max: 2147483647");
		decField.setFont(myFont);
		decField.setForeground(Color.LIGHT_GRAY);
		decField.setToolTipText("Decimal Input");
		decField.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mousePressed(MouseEvent e)
            {
                clearFields();
            }
        });
		decField.addKeyListener(this);
		
		c.gridy = 0;
		add(decField, c);
		
		// Binary TextField
		binField = new JTextField();
		binField.setFont(myFont);
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
		binField.addKeyListener(this);
		add(binField, c);
		
		// Hexadecimal TextField
		hexField = new JTextField();
		hexField.setFont(myFont);
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
		hexField.addKeyListener(this);
		add(hexField, c);
		
		// Octal TextField
		octField = new JTextField();
		octField.setFont(myFont);
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
		octField.addKeyListener(this);
		add(octField, c);
	}

	/**
	 * Changes the text fields to the converted form of the user-input
	 */
	private void convertInput()
	{
		try
		{
			if (decField == frame.getFocusOwner())
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
			} else if (binField == frame.getFocusOwner())
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
			} else if (hexField == frame.getFocusOwner())
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
			} else if (octField == frame.getFocusOwner())
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
			invalid(": max decimal value is 2147483647.");
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
	
	
	/**
	 * Called when a key is pressed while a textfield is focused.
	 * Resets DecField to get rid of max int string.
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		String s = decField.getText();
		String sthreechars = s.substring(0, Math.min(s.length(), 3));
		decField.setForeground(Color.BLACK);
		
		if (sthreechars.equals("Max"))
		{
			decField.setText("");
		}
	}
	
	/**
	 * Converts the input when the user releases the pressed key.
	 * @param e
	 */
	@Override
	public void keyReleased(KeyEvent e) 
	{
		convertInput();
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// do nothing
	}
}