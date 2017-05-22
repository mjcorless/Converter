package conversions;

public class Binary
{
	private String Output = "";
	private  int input;
	private  String s;
	
	public Binary(String input)
	{
		s = input;
	}

	private String BinaryToDecimal()
	{
		return Integer.toString(Integer.parseInt(s,2));
	}

	private String BinaryToBinary()
	{
		return s;
	}

	private String BinaryToHex()
	{
		return Integer.toHexString(Integer.parseInt(s,2));
	}

	private String BinaryToASCII()
	{
		input = Integer.parseInt(s,2);
		if (input > 127)
			Output = "Not a valid ASCII character";
		else
			Output = new Character((char)input).toString();
		return Output;
	}

	private String BinaryToOctal()
	{
		return Integer.toOctalString(Integer.parseInt(s, 2));
	}

	public boolean isValid(String input)
	{
		char temp;
		for (int i = 0; i < input.length(); i++)
		{
			temp = input.charAt(i);
			if (temp != '1' && temp != '0')
				return false;
			else
			{
				// do nothing
			}
		}
		return true;
	}
	
	public String[] convertBinary()
	{
		String[] result = new String[4];
		result[0] = BinaryToDecimal();
		result[1] = BinaryToHex();
		result[2] = BinaryToOctal();
		return result;
	}
}
