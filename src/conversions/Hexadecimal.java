package conversions;

import generic.Tuple;

public class Hexadecimal
{
	private String hex;
	
	public Hexadecimal(String input)
	{
		hex = input;
	}
	
	private String HexToDecimal()
	{
		return Integer.toString(Integer.parseInt(hex, 16));
	}

	private String HexToBinary()
	{
		return Integer.toBinaryString(Integer.parseInt(hex, 16));
	}

	private String HexToHex()
	{
		return hex;
	}

	private String HexToOctal()
	{
		return Integer.toOctalString(Integer.parseInt(hex, 16));
	}

	public boolean isValid()
	{
		try 
		{
		    Integer.parseInt(hex, 16);
		    return true;
		}
		catch (NumberFormatException hex) 
		{
			return false;
		}
	}
	
	public Tuple<String, String, String, String> convertHexadecimal()
	{
		return new Tuple<String, String, String, String>
						(HexToDecimal(), HexToBinary(), hex, HexToOctal());
	}
	
}
