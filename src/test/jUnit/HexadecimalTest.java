package test.jUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.conversions.Hexadecimal;
import main.conversions.Numerical;

public class HexadecimalTest
{
	@Test
	public void getDecimal()
	{
		Numerical decimal1 = new Hexadecimal("0");
		Numerical decimal2 = new Hexadecimal("126fae");
		Numerical decimal3 = new Hexadecimal("f");

		String result1 = decimal1.getDecimal();
		String result2 = decimal2.getDecimal();
		String result3 = decimal3.getDecimal();

		assertEquals("0", result1);
		assertEquals("1208238", result2);
		assertEquals("15", result3);
	}

	@Test
	public void getBinary()
	{
		Numerical decimal1 = new Hexadecimal("0");
		Numerical decimal2 = new Hexadecimal("126fae");
		Numerical decimal3 = new Hexadecimal("f");

		String result1 = decimal1.getBinary();
		String result2 = decimal2.getBinary();
		String result3 = decimal3.getBinary();

		assertEquals("0", result1);
		assertEquals("100100110111110101110", result2);
		assertEquals("1111", result3);
	}

	@Test
	public void getHexadecimal()
	{
		Numerical decimal1 = new Hexadecimal("0");
		Numerical decimal2 = new Hexadecimal("126fae");
		Numerical decimal3 = new Hexadecimal("f");

		String result1 = decimal1.getHex();
		String result2 = decimal2.getHex();
		String result3 = decimal3.getHex();

		assertEquals("0", result1);
		assertEquals("126fae", result2);
		assertEquals("f", result3);
	}

	@Test
	public void getOctal()
	{
		Numerical decimal1 = new Hexadecimal("0");
		Numerical decimal2 = new Hexadecimal("126fae");
		Numerical decimal3 = new Hexadecimal("f");

		String result1 = decimal1.getOctal();
		String result2 = decimal2.getOctal();
		String result3 = decimal3.getOctal();

		assertEquals("0", result1);
		assertEquals("4467656", result2);
		assertEquals("17", result3);
	}
}
