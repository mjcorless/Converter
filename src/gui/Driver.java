package gui;

import java.awt.EventQueue;

/**
 * Runs the program.
 * @author Matthew Corless
 *
 */
public class Driver
{	
	/**
	 * Launches the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ConvertPanel tmp = ConvertPanel.getInstance();
					tmp.cpInit();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}

