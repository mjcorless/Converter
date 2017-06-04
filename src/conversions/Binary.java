package conversions;

import generic.Tuple;

public class Binary
{
	private String binary;

	public Binary(String input)
	{
		binary = input;
	}

	private String BinaryToDecimal()
	{
		return Integer.toString(Integer.parseInt(binary,2));
	}

	private String BinaryToBinary()
	{
		return binary;
	}

	private String BinaryToHex()
	{
		return Integer.toHexString(Integer.parseInt(binary,2));
	}

	private String BinaryToOctal()
	{
		return Integer.toOctalString(Integer.parseInt(binary, 2));
	}

	public boolean isValid()
	{
		char temp;
		for (int i = 0; i < binary.length(); i++)
		{
			temp = binary.charAt(i);
			if (temp != '1' && temp != '0')
			{
				return false;
			} 
		}
		return true;
	}	
	
	public Tuple<String, String, String, String> convertBinary()
	{	
		return new Tuple<String, String, String, String>
						(BinaryToDecimal(), binary, BinaryToHex(), BinaryToOctal());
	}
}
