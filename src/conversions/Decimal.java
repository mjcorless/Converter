package conversions;

import generic.Tuple;

public class Decimal
{
	private String decimal;
	
	public Decimal(String input)
	{
		decimal = input;
	}
	
	private String DecimalToDecimal()
	{
		return decimal;
	}

	private String DecimalToBinary()
	{
		return Integer.toBinaryString(Integer.parseInt(decimal));
	}

	private String DecimalToHex()
	{
		return Integer.toHexString(Integer.parseInt(decimal));
	}

	private String DecimalToOctal()
	{
		return Integer.toOctalString(Integer.parseInt(decimal));
	}

	public boolean isValid()
	{
		try
		{
			Integer.parseInt(decimal);
		}
		catch(NumberFormatException dec)
		{
			return false;
		}
		return true;
	}
	
	public Tuple<String, String, String, String> convertDecimal()
	{	
		return new Tuple<String, String, String, String>
						(decimal, DecimalToBinary(), DecimalToHex(), DecimalToOctal());
	}
}
