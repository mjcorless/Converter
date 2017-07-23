package main.conversions;

public class Hexadecimal implements Numerical
{
	private String hex;

	public Hexadecimal(String input)
	{
		hex = input;
	}

	@Override
	public String getDecimal()
	{
		return Integer.toString(Integer.parseInt(hex, 16));
	}

	@Override
	public String getBinary()
	{
		return Integer.toBinaryString(Integer.parseInt(hex, 16));
	}

	@Override
	public String getHex()
	{
		return hex;
	}

	@Override
	public String getOctal()
	{
		return Integer.toOctalString(Integer.parseInt(hex, 16));
	}

	@Override
	public String showMeToDecimal()
	{
		StringBuilder sb = new StringBuilder("<b>" + hex + "<sub>16</sub> to Decimal:</b>");

		sb.append("<br>Multiply every hex character by the power of the digit location.<br>"
				+ "Remember 'A' = 10, 'B' = 11, 'C' = 12, 'D' = 13, 'E' = 14, 'F' = 16<br><br>");

		sb.append("<table align=\"center\">");
		sb.append("<tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>" + hex + "</td><td> = </td>");
		for (int i = 0; i < hex.length(); i++)
		{
			sb.append("<td>(" + hex.charAt(i) + " * 16<sup>" + (hex.length() - i - 1) + "</sup>)</td>");

			if (i != hex.length() - 1)
			{
				sb.append("<td> + </td>");
			}
		}
		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");

		sb.append("<td>" + hex + "</td><td> = </td>");
		for (int i = 0; i < hex.length(); i++)
		{
			sb.append("<td>(" + Integer.toString(Integer.parseInt(hex.substring(i, i + 1), 16)) + " * "
					+ (int) Math.pow(16, (hex.length() - i - 1)) + "</sup>)</td>");

			if (i != hex.length() - 1)
			{
				sb.append("<td> + </td>");
			}
		}

		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>" + hex + "</td><td> = </td>");
		for (int i = 0; i < hex.length(); i++)
		{
			int hexChar = Integer.parseInt(hex.substring(i, i + 1), 16);
			int indexPower = (int) Math.pow(16, (hex.length() - i - 1));
			sb.append("<td>(" + (hexChar * indexPower) + "</sup>)</td>");

			if (i != hex.length() - 1)
			{
				sb.append("<td> + </td>");
			}
		}
		sb.append("</tr><tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>" + hex + "</td><td> = </td><td><b>" + Integer.toString(Integer.parseInt(hex, 16))
				+ "</b></td></table>");

		return sb.toString();
	}

	@Override
	public String showMeToBinary()
	{
		StringBuilder sb = new StringBuilder("<b>" + hex + "<sub>16</sub> to Binary:</b>");
		sb.append("<br>Convert each individual hexadecimal character to its 4 bit binary representation."
				+ "<br>and then append the binary results.<br><br>");

		sb.append("<table align=\"center\">");
		sb.append("<tr style=\"text-align:center\" valign=\"center\">");
		sb.append("<td>(" + hex + ")<sub>16</sub></td><td> = </td><td> (?)<sub>2</sub></td>");
		sb.append("</tr>");

		for (int i = 0; i < hex.length(); i++)
		{
			// get binary of the hex character
			StringBuilder binarySubString = new StringBuilder(
					Integer.toBinaryString(Integer.parseInt(hex.substring(i, i + 1), 16)));
			// pad binary to 4 characters except for first input
			while (binarySubString.length() < 4 && i != 0)
			{
				binarySubString.insert(0, "0");
			}
			//String binSub = binarySubString.toString();
			sb.append("<tr style=\"text-align:center\" valign=\"center\">");
			sb.append("<td>(" + hex.substring(i, i + 1) + ")<sub>16</sub></td><td> = </td><td>(" + binarySubString
					+ ")<sub>2</sub></td></tr>");
		}

		sb.append("<table align=\"center\">");
		sb.append("<tr style=\"text-align:center\" valign=\"center\">");
		String result = Integer.toBinaryString(Integer.parseInt(hex, 16));
		sb.append("<td>(" + hex + ")<sub>16</sub></td><td> = </td><td>(" + result + ")<sub>2</sub></td></tr></table>");

		return sb.toString();
	}

	@Override
	public String showMeToHexadecimal()
	{
		StringBuilder sb = new StringBuilder("<b>" + hex + "<sub>16</sub></b>");

		return sb.toString();
	}

	@Override
	public String showMeToOctal()
	{
		String binaryStr = getBinary();
		Binary binary = new Binary(binaryStr);

		StringBuilder sb = new StringBuilder("<b>" + hex + "<sub>16</sub> to Octal:</b>");
		sb.append("<br>The easiest way is to first convert from hexadecimal to binary."
				+ "<br>Then convert the binary form to octal<br><br>");

		sb.append(showMeToBinary());
		sb.append("<br>");
		sb.append(binary.showMeToOctal());

		return sb.toString();
	}

	@Override
	public boolean isValid()
	{
		try
		{
			Integer.parseInt(hex, 16);
			return true;
		}
		catch (NumberFormatException hex)
		{
			return false;
		}
	}
}
