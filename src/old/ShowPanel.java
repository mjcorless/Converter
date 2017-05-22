package old;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ShowPanel extends JFrame implements ActionListener
{
	private int type; // 1=decimal, 2=binary, 3=hex, 4=octal
	private String input; // input
	private JButton toDecBtn, toBinBtn, toHexBtn, toOctBtn;
	private JLabel user = new JLabel("", SwingConstants.CENTER);
	private JPanel output, output2, output3;
	private JLabel placeholder;
	private Font myFont = new Font("Cambria", Font.BOLD, 24);
	private JButton backBtn;
	
	ShowPanel()
	{
		setLayout(null);
		setSize(594, 621);
		
		toDecBtn = new JButton("To Decimal");
		toDecBtn.setActionCommand("decimal");
		toDecBtn.addActionListener(this);
		
		toBinBtn = new JButton("To Binary");
		toBinBtn.setActionCommand("binary");
		toBinBtn.addActionListener(this);
		
		toHexBtn = new JButton("To Hex");
		toHexBtn.setActionCommand("hex");
		toHexBtn.addActionListener(this);
		
		toOctBtn = new JButton("To Octal");
		toOctBtn.setActionCommand("octal");
		toOctBtn.addActionListener(this);
		
		placeholder = new JLabel("<html><font color = red><b>" + "This is not yet implemented");
		add(placeholder);
		placeholder.setBounds(200, 50, 200, 20);
		
		
		output = new JPanel();
		output2 = new JPanel();
		output3 = new JPanel();
		add(output);
		add(output2);
		add(output3);
		
		add(toDecBtn);
		toDecBtn.setBounds(23, 5, 120, 30);
		add(toBinBtn);
		toBinBtn.setBounds(166, 5, 120, 30);
		add(toHexBtn);
		toHexBtn.setBounds(309, 5, 120, 30);
		add(toOctBtn);
		toOctBtn.setBounds(452, 5, 120, 30);
		add(user);
		user.setBounds(200, 30, 200, 20);
		
		
		
		setSize(600, 450);
		setResizable(false);
		setVisible(true);
	}
	
	public void UpdatePanel()
	{
		setEnabledTrue();
		setVisibleTrue();
		remove(output);
		remove(output2);
		remove(output3);
		
		switch (this.type)
		{
			case 1:	user.setText("Input:  " + input + "   (decimal)");
					toDecBtn.setVisible(false);
					break;
			case 2: user.setText("Input:  " + input + "   (binary)");
					toBinBtn.setVisible(false);
					break;
			case 3:	user.setText("Input:  " + input + "   (hexadecimal)");
					toHexBtn.setVisible(false);
					break;
			case 4: user.setText("Input:  " + input + "   (octal)");
					toOctBtn.setVisible(false);
					break;
		}
		this.add(user);
		user.setBounds(200, 30, 200, 20);
	}
	
	public void setType(int t)
	{
		this.type = t;
	}
	
	public void setInput(String s)
	{
		this.input = s;
	}


//--------------------------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e)
	{
		String action = e.getActionCommand();
		setEnabledTrue();
		remove(output);
		remove(output2);
		remove(output3);
		remove(placeholder);
		
		if(action.equals("decimal"))
		{
			toDecBtn.setEnabled(false);
			toDecimal();
		}
		else if(action.equals("binary"))
		{
			toBinBtn.setEnabled(false);
			toBinary();
		}
		else if(action.equals("hex"))
		{
			toHexBtn.setEnabled(false);
			toHex();
		}
		else
		{
			toOctBtn.setEnabled(false);
			toOctal();
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	

	private void setEnabledTrue()
	{
		toDecBtn.setEnabled(true);
		toBinBtn.setEnabled(true);
		toHexBtn.setEnabled(true);
		toOctBtn.setEnabled(true);
	}
	
	private void setVisibleTrue()
	{
		toDecBtn.setVisible(true);
		toBinBtn.setVisible(true);
		toHexBtn.setVisible(true);
		toOctBtn.setVisible(true);
	}
	
	//--------------------------------------To Decimal--------------------------------
	private void toDecimal()
	{
		// TODO Auto-generated method stub
		
	}
	
	
	
	//--------------------------------------To Binary--------------------------------
	private void toBinary()
	{
		switch (type)
		{
			case 1:	showDecimalToBinary();
					break;
			case 2: 
					break;
			case 3:	
					break;
			case 4: 
					break;
		}
	}
	
	private void showDecimalToBinary()
	{
		output = new JPanel();
		output.setLayout(new BoxLayout(output, BoxLayout.Y_AXIS));
		output2 = new JPanel();
		output2.setLayout(new BoxLayout(output2, BoxLayout.Y_AXIS));
		output3 = new JPanel();
		
		JLabel temp1 = new JLabel("Divison Method");
		output.add(temp1);
		JLabel temp2 = new JLabel("Remainder");
		output2.add(temp2);
		
		int count = 0;
		for (int i = Integer.parseInt(input); i > 0; i = (int) Math.round(i/2))
		{
			count++;
		}
		
		int i = Integer.parseInt(input);
		for (int j = count - 1; j >= 0; j--)
		{
			if(j == count - 1)
			{
				temp2 = new JLabel("<html>" + "1" + "&nbsp" + "<font color = red><font size=+1><sub> ^");
			}
			else
			{
				if (i % 2 == 1)
				{
					temp2 = new JLabel("<html>" + "1" + "&nbsp &nbsp" + "<font color = red> |");
				} else
				{
					temp2 = new JLabel("<html>" + "0" + "&nbsp &nbsp" + "<font color = red> |");
				}
			}
			output2.add(temp2);
			temp1 = new JLabel("<html>" + "2 " + "<u><b>)" + Integer.toString(i) + " </u></b>");
			output.add(temp1);
			i = (int) Math.round(i/2);
		}
			
		add(output2);
		output2.setBounds(300, 50, 64, 600);
		add(output);
		output.setBounds(200,50,200,600);
	}
	
	//--------------------------------------To Hex----------------------------------
	private void toHex()
	{
		// TODO Auto-generated method stub
		
	}
	
	//--------------------------------------To Octal--------------------------------
	private void toOctal()
	{
		// TODO Auto-generated method stub
		
	}
	
}
