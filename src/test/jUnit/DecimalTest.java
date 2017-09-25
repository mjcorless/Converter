package test.jUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.conversions.Decimal;
import main.conversions.Numerical;

public class DecimalTest
{

	@Test
	public void getDecimal()
	{
		Numerical decimal1 = new Decimal("0");
		Numerical decimal2 = new Decimal("-5");
		Numerical decimal3 = new Decimal("5");

		String result1 = decimal1.getDecimal();
		String result2 = decimal2.getDecimal();
		String result3 = decimal3.getDecimal();

		assertEquals("0", result1);
		assertEquals("-5", result2);
		assertEquals("5", result3);
	}

	@Test
	public void getBinary()
	{
		Numerical decimal1 = new Decimal("0");
		Numerical decimal2 = new Decimal("-5");
		Numerical decimal3 = new Decimal("5");

		String result1 = decimal1.getBinary();
		String result2 = decimal2.getBinary();
		String result3 = decimal3.getBinary();

		assertEquals("0", result1);
		assertEquals("11111111111111111111111111111011", result2);
		assertEquals("101", result3);
	}

	@Test
	public void getHexadecimal()
	{
		Numerical decimal1 = new Decimal("0");
		Numerical decimal2 = new Decimal("-12");
		Numerical decimal3 = new Decimal("16");

		String result1 = decimal1.getHex();
		String result2 = decimal2.getHex();
		String result3 = decimal3.getHex();

		assertEquals("0", result1);
		assertEquals("fffffff4", result2);
		assertEquals("10", result3);
	}

	@Test
	public void getOctal()
	{
		Numerical decimal1 = new Decimal("0");
		Numerical decimal2 = new Decimal("-12");
		Numerical decimal3 = new Decimal("12");

		String result1 = decimal1.getOctal();
		String result2 = decimal2.getOctal();
		String result3 = decimal3.getOctal();

		assertEquals("0", result1);
		assertEquals("37777777764", result2);
		assertEquals("14", result3);
	}
}
