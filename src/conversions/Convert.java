package conversions;

import generic.Tuple;

/**
 * Convert deals with all of the conversions for the program.
 * Convert is implemented as a singleton in order to avoid
 * wrong conversion outputs when there are multiple
 * action events going on at once in ConvertPanel.
 * @author Matthew Corless
 *
 */
public class Convert
{
	// member variables
	
	private static Convert instance = null;
	private Tuple<String, String, String, String> result;
	
	public String decimal;
	public String binary;
	public String hexadecimal;
	public String octal;
	
	
	// enums
	
	public enum type
	{
		Decimal,
		Binary,
		Hexadecimal,
		Octal
	}
	
	private Convert()
	{
		// prevent instantiation
	}
	
	public static Convert getInstance()
	{
		if (instance == null)
		{
			instance = new Convert();
		}
		return instance;
	}
	
	public boolean convertMe(type type, String input)
	{
		if (input != null)
		{
			switch (type)
			{
				case Decimal:
					Decimal dec = new Decimal(input);
					if (dec.isValid())
					{
						result = dec.convertDecimal();
						decimal = result._t;
						binary = result._u;
						hexadecimal = result._v;
						octal = result._w;
						return true;
					} 
					break;
				case Binary:
					Binary bin = new Binary(input);
					if (bin.isValid())
					{
						result = bin.convertBinary();
						decimal = result._t;
						binary = result._u;
						hexadecimal = result._v;
						octal = result._w;
						return true;
					} 
					break;
				case Hexadecimal:
					Hexadecimal hex = new Hexadecimal(input);
					if (hex.isValid())
					{
						result = hex.convertHexadecimal();
						decimal = result._t;
						binary = result._u;
						hexadecimal = result._v;
						octal = result._w;
						return true;
					} 
					break;
				case Octal:
					Octal oct = new Octal(input);
					if (oct.isValid())
					{
						result = oct.convertOctal();
						decimal = result._t;
						binary = result._u;
						hexadecimal = result._v;
						octal = result._w;
						return true;
					} 
					break;			
			}
		}
		return false;
	}
}
