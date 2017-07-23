package main.conversions;

public class Octal implements Numerical
{
	private String octal;

	public Octal(String input)
	{
		octal = input;
	}

	@Override
	public String getDecimal()
	{
		return Integer.toString(Integer.parseInt(octal, 8));
	}

	@Override
	public String getBinary()
	{
		return Integer.toBinaryString(Integer.parseInt(octal, 8));
	}

	@Override
	public String getHex()
	{
		return Integer.toHexString(Integer.parseInt(octal, 8));
	}

	@Override
	public String getOctal()
	{
		return octal;
	}

	@Override
	public String showMeToDecimal()
	{
		StringBuilder sb = new StringBuilder("<b>" + octal + "<sub>8</sub> to Decimal</b>");

		sb.append("<br>Multiply every octal character by the power of the digit location.<br>");

		sb.append("<table align=\"center\">");
		sb.append("<tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td>");
		for (int i = 0; i < octal.length(); i++)
		{
			sb.append("<td>(" + octal.charAt(i) + " * 8<sup>" + (octal.length() - i - 1) + "</sup>)</td>");

			if (i != octal.length() - 1)
			{
				sb.append("<td> + </td>");
			}
		}
		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");

		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td>");
		for (int i = 0; i < octal.length(); i++)
		{
			sb.append("<td>(" + Integer.toString(Integer.parseInt(octal.substring(i, i + 1), 8)) + " * "
					+ (int) Math.pow(8, (octal.length() - i - 1)) + "</sup>)</td>");

			if (i != octal.length() - 1)
			{
				sb.append("<td> + </td>");
			}
		}

		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td>");
		for (int i = 0; i < octal.length(); i++)
		{
			int hexChar = Integer.parseInt(octal.substring(i, i + 1), 8);
			int indexPower = (int) Math.pow(8, (octal.length() - i - 1));
			sb.append("<td>(" + (hexChar * indexPower) + "</sup>)</td>");

			if (i != octal.length() - 1)
			{
				sb.append("<td> + </td>");
			}
		}
		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td><td><b>"
				+ Integer.toString(Integer.parseInt(octal, 8)) + "</b></td></table>");

		return sb.toString();
	}

	@Override
	public String showMeToBinary()
	{
		StringBuilder sb = new StringBuilder("<b>" + octal + "<sub>8</sub> to Binary</b>");
		sb.append("<br>Convert each individual octal character to its 3 bit binary representation."
				+ "<br>and then append the binary results.<br><br>");

		sb.append("<table align=\"center\">");
		sb.append("<tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td><td> (?)<sub>2</sub></td>");
		sb.append("</tr>");

		for (int i = 0; i < octal.length(); i++)
		{
			// get binary of the octal character
			StringBuilder binarySubString = new StringBuilder(
					Integer.toBinaryString(Integer.parseInt(octal.substring(i, i + 1), 8)));
			// pad binary to 3 characters except for first input
			while (binarySubString.length() < 3 && i != 0)
			{
				binarySubString.insert(0, "0");
			}

			sb.append("<tr style=\"text-align:center\" valign=\"center\">");
			sb.append("<td>(" + octal.substring(i, i + 1) + ")<sub>8</sub></td><td> = </td><td>(" + binarySubString
					+ ")<sub>2</sub></td></tr>");
		}

		sb.append("<table align=\"center\">");
		sb.append("<tr style=\"text-align:center\" valign=\"center\">");
		String result = Integer.toBinaryString(Integer.parseInt(octal, 8));
		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td><td>(" + result + ")<sub>2</sub></td></tr></table>");

		return sb.toString();
	}

	@Override
	public String showMeToHexadecimal()
	{
		StringBuilder sb = new StringBuilder("<b>" + octal + "<sub>8</sub> Hexadecimal</b>");

		sb.append("<br>Multiply every octal character by the power of the digit location.<br>");

		sb.append("<table align=\"center\">");
		sb.append("<tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td>");
		for (int i = 0; i < octal.length(); i++)
		{
			sb.append("<td>(" + octal.charAt(i) + " * 8<sup>" + (octal.length() - i - 1) + "</sup>)</td>");

			if (i != octal.length() - 1)
			{
				sb.append("<td> + </td>");
			}
		}
		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");

		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td>");
		for (int i = 0; i < octal.length(); i++)
		{
			sb.append("<td>(" + Integer.toHexString(Integer.parseInt(octal.substring(i, i + 1), 8)) + " * "
					+ (int) Math.pow(8, (octal.length() - i - 1)) + ")</td>");

			if (i != octal.length() - 1)
			{
				sb.append("<td> + </td>");
			}
		}

		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td>");
		int[] decimalArray = new int[octal.length()];
		for (int i = 0; i < octal.length(); i++)
		{
			int octChar = Integer.parseInt(octal.substring(i, i + 1), 8);
			int indexPower = (int) Math.pow(8, (octal.length() - i - 1));
			sb.append("<td>(" + (octChar * indexPower) + ")</td>");

			if (i != octal.length() - 1)
			{
				sb.append("<td> + </td>");
			}

			decimalArray[i] = (octChar * indexPower);
		}

		// convert each parenthesis from decimal to hexadecimal
		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td>");
		for (int i = 0; i < octal.length(); i++)
		{
			int octChar = Integer.parseInt(octal.substring(i, i + 1), 8);
			int indexPower = (int) Math.pow(8, (octal.length() - i - 1));
			int decimal = octChar * indexPower;
			sb.append("<td>(" + Integer.toHexString(decimal) + ")<sub>16</sub></td>");

			if (i != octal.length() - 1)
			{
				sb.append("<td> + </td>");
			}
		}

		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>(" + octal + ")<sub>8</sub></td><td> = </td><td><b>"
				+ Integer.toHexString(Integer.parseInt(octal, 8)) + "</b><sub>16</sub></td></table>");

		return sb.toString();
	}

	@Override
	public String showMeToOctal()
	{
		StringBuilder sb = new StringBuilder("<b>" + octal + "<sub>8</sub></b>");

		return sb.toString();
	}

	@Override
	public boolean isValid()
	{
		int temp = Integer.parseInt(octal);
		if (temp >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
