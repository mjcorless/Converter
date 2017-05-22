package conversions;

public class Decimal
{
	private String Output = "";
	private  int input;
	String s;
	
	public Decimal(String input)
	{
		s = input;
	}
	
	private String DecimalToDecimal()
	{
		return s;
	}

	private String DecimalToBinary()
	{
		return Integer.toBinaryString(Integer.parseInt(s));
	}

	private String DecimalToHex()
	{
		return Integer.toHexString(Integer.parseInt(s));
	}

	private String DecimalToASCII()
	{
		input = Integer.parseInt(s);
		if(input < 128)
		{
			char temp = (char) input;
			Output = Character.toString(temp);
		}
		else
			Output = "Cannot convert above 127";
		return Output;
	}

	private String DecimalToOctal()
	{
		return Integer.toOctalString(Integer.parseInt(s));
	}

	public boolean isValid(String text)
	{
		try
		{
			input = Integer.parseInt(text);
		}
		catch(NumberFormatException dec)
		{
			return false;
		}
		return true;
	}
	
	public String[] convertDecimal()
	{
		String[] result = new String[4];
		result[0] = DecimalToBinary();
		result[1] = DecimalToHex();
		result[2] = DecimalToOctal();
		return result;
	}
	
}
