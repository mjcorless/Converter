package conversions;

public class Octal
{
	private String s;
	
	public Octal(String input)
	{
		s = input; 
	}
	
	private String OctalToDecimal()
	{
		return Integer.toString(Integer.parseInt(s,8));
	}

	private String OctalToBinary()
	{
		return Integer.toBinaryString(Integer.parseInt(s,8));
	}

	private String OctalToHex()
	{
		return Integer.toHexString(Integer.parseInt(s,8));
	}
	
	//
	//String OctalToASCII()
	//{
	//	String imLazy = OctalToDecimal();
		//Decimal temp = new Decimal(imLazy);
		//return temp.DecimalToASCII();
	//}

	private String OctalToOctal()
	{
		return s;
	}
	
	public boolean isValid(String text)
	{
		int temp = Integer.parseInt(text);
		if (temp >= 0)
			return true;
		else
			return false;
	}
	
	public String[] convertOctal()
	{
		String[] result = new String[4];
		result[0] = OctalToDecimal();
		result[1] = OctalToBinary();
		result[2] = OctalToHex();
		return result;
	}
}
