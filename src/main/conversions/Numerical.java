package main.conversions;

public interface Numerical
{
	public String getDecimal();

	public String getBinary();

	public String getHex();

	public String getOctal();

	public String showMeToDecimal();

	public String showMeToBinary();

	public String showMeToHexadecimal();

	public String showMeToOctal();

	public boolean isValid();
}