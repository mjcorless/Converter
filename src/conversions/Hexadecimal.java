package conversions;

public class Hexadecimal
{
	private String Output = "";
	String s;
	
	public Hexadecimal(String input)
	{
		s = input;
	}
	
	private String HexToDecimal()
	{
		return Integer.toString(Integer.parseInt(s, 16));
	}

	private String HexToBinary()
	{
		return Integer.toBinaryString(Integer.parseInt(s, 16));
	}

	private String HexToHex()
	{
		return s;
	}

	private String HexToASCII()
	{
		StringBuilder output = new StringBuilder();
	    for (int i = 0; i < s.length(); i+=2) {
	        String str = s.substring(i, i+2);
	        output.append((char)Integer.parseInt(str, 16));
	    }
	    Output = output.toString();
	    return Output;
	}

	private String HexToOctal()
	{
		return Integer.toOctalString(Integer.parseInt(s, 16));
	}

	public boolean isValid(String input)
	{
		try 
		{
		    Integer.parseInt(input, 16);
		    return true;
		}
		catch (NumberFormatException hex) 
		{
			System.out.println(hex);
			return false;
		}
	}
	
	public String[] convertHex()
	{
		String[] result = new String[4];
		result[0] = HexToDecimal();
		result[1] = HexToBinary();
		result[2] = HexToOctal();
		return result;
	}
}
