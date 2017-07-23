package main.conversions;

/**
 * Result contains the binary, decimal, hexadecimal, and octal results for each conversion
 * as well as how the conversions are done.
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

	/**
	 * Creates result object with every conversion for the user's input.
	 * 
	 * @param numerical
	 */
	public Result(Numerical numerical)
	{
		decimal = numerical.getDecimal();
		binary = numerical.getBinary();
		hexadecimal = numerical.getHex();
		octal = numerical.getOctal();
		showDecimal = numerical.showMeToDecimal();
		showBinary = numerical.showMeToBinary();
		showHexadecimal = numerical.showMeToHexadecimal();
		showOctal = numerical.showMeToOctal();
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
