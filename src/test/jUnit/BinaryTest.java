package test.jUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.conversions.Binary;
import main.conversions.Numerical;

public class BinaryTest
{
	@Test
	public void getDecimal()
	{
		Numerical decimal1 = new Binary("0");
		Numerical decimal2 = new Binary("11111111111111111011");
		Numerical decimal3 = new Binary("1011001");

		String result1 = decimal1.getDecimal();
		String result2 = decimal2.getDecimal();
		String result3 = decimal3.getDecimal();

		assertEquals("0", result1);
		assertEquals("1048571", result2);
		assertEquals("89", result3);
	}

	@Test
	public void getBinary()
	{
		Numerical decimal1 = new Binary("0");
		Numerical decimal2 = new Binary("11111111111111111011");
		Numerical decimal3 = new Binary("1011001");

		String result1 = decimal1.getBinary();
		String result2 = decimal2.getBinary();
		String result3 = decimal3.getBinary();

		assertEquals("0", result1);
		assertEquals("11111111111111111011", result2);
		assertEquals("1011001", result3);
	}

	@Test
	public void getHexadecimal()
	{
		Numerical decimal1 = new Binary("0");
		Numerical decimal2 = new Binary("11111111111111111011");
		Numerical decimal3 = new Binary("1011001");

		String result1 = decimal1.getHex();
		String result2 = decimal2.getHex();
		String result3 = decimal3.getHex();

		assertEquals("0", result1);
		assertEquals("ffffb", result2);
		assertEquals("59", result3);
	}

	@Test
	public void getOctal()
	{
		Numerical decimal1 = new Binary("0");
		Numerical decimal2 = new Binary("11111111111111111011");
		Numerical decimal3 = new Binary("1011001");

		String result1 = decimal1.getOctal();
		String result2 = decimal2.getOctal();
		String result3 = decimal3.getOctal();

		assertEquals("0", result1);
		assertEquals("3777773", result2);
		assertEquals("131", result3);
	}
}
