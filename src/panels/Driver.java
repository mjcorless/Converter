package panels;

import java.awt.EventQueue;

/**
 * I use to utilize CardLayout in order to hold a convertPanel and showPanel in one frame. 
 * However, I took that off because it made showPanel nearly impossible to implement the way
 * I wanted to.
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
					new ConvertPanel();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}

