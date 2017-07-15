package conversions;

public class Decimal extends Numerical
{
	private String decimal;

	public Decimal(String input)
	{
		decimal = input;
	}

	@Override
	public String getDecimal()
	{
		return decimal;
	}

	@Override
	public String getBinary()
	{
		return Integer.toBinaryString(Integer.parseInt(decimal));
	}

	@Override
	public String getHex()
	{
		return Integer.toHexString(Integer.parseInt(decimal));
	}

	@Override
	public String getOctal()
	{
		return Integer.toOctalString(Integer.parseInt(decimal));
	}

	@Override
	public String showMeToDecimal()
	{
		return "<b>" + decimal + "</b>";
	}

	@Override
	public String showMeToBinary()
	{
		StringBuilder sb = new StringBuilder("<b>" + decimal + " to Binary:</b>");
		sb.append("<br>------------------------------");
		StringBuilder result = new StringBuilder();
		int temp = Integer.parseInt(decimal);
		int remainder = 0;

		while (temp > 0)
		{
			sb.append("<br>divide by 2");
			sb.append("<br>------------------------------");
			temp = temp / 2;
			remainder = temp % 2;
			result.append(remainder);
			sb.append("<br>Result: " + temp + "\tRemainder: " + remainder);
			sb.append("<br>------------------------------");
		}

		sb.append("<br>Final answer: " + result.reverse().toString());

		return sb.toString();
	}

	@Override
	public String showMeToHexadecimal()
	{
		StringBuilder sb = new StringBuilder("<b>" + decimal + " to Hexadecimal:</b>");
		sb.append("<br>------------------------------");
		StringBuilder result = new StringBuilder();
		int temp = Integer.parseInt(decimal);
		int remainder = 0;

		while (temp > 0)
		{
			sb.append("<br>divide by 16");
			sb.append("<br>------------------------------");
			remainder = temp % 16;
			temp = temp / 16;
			result.append(Integer.toHexString(remainder));
			sb.append("<br>Result: " + temp + "\tRemainder: " + remainder + " (Hex: "
					+ Integer.toHexString(remainder) + ")");
			sb.append("<br>------------------------------");
		}

		sb.append("<br>Final answer: " + result.reverse().toString());

		return sb.toString();
	}

	@Override
	public String showMeToOctal()
	{
		StringBuilder sb = new StringBuilder("<b>" + decimal + " to Octal:</b>");
		sb.append("<br>------------------------------");
		StringBuilder result = new StringBuilder();
		int temp = Integer.parseInt(decimal);
		int remainder = 0;

		while (temp > 0)
		{
			sb.append("<br>divide by 8");
			sb.append("<br>------------------------------");
			remainder = temp % 8;
			temp = temp / 8;
			result.append(Integer.toHexString(remainder));
			sb.append("<br>Result: " + temp + "\tRemainder: " + remainder);
			sb.append("<br>------------------------------");
		}

		sb.append("<br>Final answer: " + result.reverse().toString());

		return sb.toString();
	}

	@Override
	public boolean isValid()
	{
		try
		{
			Integer.parseInt(decimal);
		}
		catch (NumberFormatException dec)
		{
			// dec.printStackTrace();
			return false;
		}
		return true;
	}
}
