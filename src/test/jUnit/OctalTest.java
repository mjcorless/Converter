package test.jUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.conversions.Numerical;
import main.conversions.Octal;

public class OctalTest
{
	@Test
	public void getDecimal()
	{
		Numerical decimal1 = new Octal("0");
		Numerical decimal2 = new Octal("10");
		Numerical decimal3 = new Octal("75345");

		String result1 = decimal1.getDecimal();
		String result2 = decimal2.getDecimal();
		String result3 = decimal3.getDecimal();

		assertEquals("0", result1);
		assertEquals("8", result2);
		assertEquals("31461", result3);
	}

	@Test
	public void getBinary()
	{
		Numerical decimal1 = new Octal("0");
		Numerical decimal2 = new Octal("10");
		Numerical decimal3 = new Octal("75345");

		String result1 = decimal1.getBinary();
		String result2 = decimal2.getBinary();
		String result3 = decimal3.getBinary();

		assertEquals("0", result1);
		assertEquals("1000", result2);
		assertEquals("111101011100101", result3);
	}

	@Test
	public void getHexadecimal()
	{
		Numerical decimal1 = new Octal("0");
		Numerical decimal2 = new Octal("10");
		Numerical decimal3 = new Octal("75345");

		String result1 = decimal1.getHex();
		String result2 = decimal2.getHex();
		String result3 = decimal3.getHex();

		assertEquals("0", result1);
		assertEquals("8", result2);
		assertEquals("7ae5", result3);
	}

	@Test
	public void getOctal()
	{
		Numerical decimal1 = new Octal("0");
		Numerical decimal2 = new Octal("10");
		Numerical decimal3 = new Octal("75345");

		String result1 = decimal1.getOctal();
		String result2 = decimal2.getOctal();
		String result3 = decimal3.getOctal();

		assertEquals("0", result1);
		assertEquals("10", result2);
		assertEquals("75345", result3);
	}
}
