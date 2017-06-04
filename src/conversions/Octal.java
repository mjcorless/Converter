package conversions;

import generic.Tuple;

public class Octal
{
	private String octal;
	
	public Octal(String input)
	{
		octal = input; 
	}
	
	private String OctalToDecimal()
	{
		return Integer.toString(Integer.parseInt(octal,8));
	}

	private String OctalToBinary()
	{
		return Integer.toBinaryString(Integer.parseInt(octal,8));
	}

	private String OctalToHex()
	{
		return Integer.toHexString(Integer.parseInt(octal,8));
	}

	private String OctalToOctal()
	{
		return octal;
	}
	
	public boolean isValid()
	{
		int temp = Integer.parseInt(octal);
		if (temp >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Tuple<String, String, String, String> convertOctal()
	{
		return new Tuple<String, String, String, String>
						(OctalToDecimal(), OctalToBinary(), OctalToHex(), octal);
	}
}
