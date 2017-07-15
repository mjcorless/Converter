package conversions;

/**
 * Result contains the binary, decimal, hexadecimal, and octal results for each
 * conversion as well as how the conversions are done.
 * 
 * @author Matthew Corless
 */
public class Result
{
	private final String decimal;
	private final String binary;
	private final String hexadecimal;
	private final String octal;
	private final String showDecimal;
	private final String showBinary;
	private final String showHexadecimal;
	private final String showOctal;

	Result(Result result)
	{
		decimal = result.decimal;
		binary = result.binary;
		hexadecimal = result.hexadecimal;
		octal = result.octal;
		showDecimal = result.showDecimal;
		showBinary = result.showBinary;
		showHexadecimal = result.showHexadecimal;
		showOctal = result.showOctal;
	}

	/**
	 * Creates result object with all 8 fields.
	 * 
	 * @param decimal
	 * @param binary
	 * @param hex
	 * @param Octal
	 * @param toDecimal
	 * @param toBinary
	 * @param toHex
	 * @param toOctal
	 */
	Result(String dec, String bin, String hex, String oct, String showDec, String showBin,
			String showHex, String showOct)
	{
		decimal = dec;
		binary = bin;
		hexadecimal = hex;
		octal = oct;
		showDecimal = showDec;
		showBinary = showBin;
		showHexadecimal = showHex;
		showOctal = showOct;
	}

	public String getDecimal()
	{
		return decimal;
	}

	public String getBinary()
	{
		return binary;
	}

	public String getHexadecimal()
	{
		return hexadecimal;
	}

	public String getOctal()
	{
		return octal;
	}

	public String getShowDecimal()
	{
		return showDecimal;
	}

	public String getShowBinary()
	{
		return showBinary;
	}

	public String getShowHexadecimal()
	{
		return showHexadecimal;
	}

	public String getShowOctal()
	{
		return showOctal;
	}
}
