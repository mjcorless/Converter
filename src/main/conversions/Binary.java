package main.conversions;

public class Binary implements Numerical
{
	private String binary;

	public Binary(String input)
	{
		binary = input;
	}

	@Override
	public String getDecimal()
	{
		return Integer.toString(Integer.parseInt(binary, 2));
	}

	@Override
	public String getBinary()
	{
		return binary;
	}

	@Override
	public String getHex()
	{
		return Integer.toHexString(Integer.parseInt(binary, 2));
	}

	@Override
	public String getOctal()
	{
		return Integer.toOctalString(Integer.parseInt(binary, 2));
	}

	@Override
	public String showMeToDecimal()
	{
		StringBuilder sb = new StringBuilder("<b>" + binary + "<sub>2</sub> to Decimal:</b>");
		sb.append("<br><br>");

		// sb.append("Binary Digit Value: &nbsp;&nbsp;&nbsp;&nbspDecimal Digit
		// Value: &nbsp;");
		sb.append(
				"<table align=\"center\" style=\"width:50%\"><tr><th align=\"left\">Power of 2</th><th align=\"left\">Binary</th><th align=\"left\">Decimal</th></tr>");

		StringBuilder resultSb = new StringBuilder("");

		for (int i = binary.length() - 1; i >= 0; i--)
		{
			sb.append("<tr>");
			sb.append("<td align=\"center\">2<sup>" + (binary.length() - i - 1) + "</sup></td>");

			sb.append("<td align=\"center\">" + binary.charAt(i) + "</td>");

			// add to the result output if the character is = 1 
			if (binary.charAt(i) == '1')
			{
				resultSb.append((int) Math.pow(2, binary.length() - i - 1) + " + ");
				sb.append("<td align=\"center\"><b>" + (int) Math.pow(2, binary.length() - i - 1) + "</b><td></tr>");
			}
			else // only add to display and not result if the char = 0
			{
				sb.append("<td align=\"center\">" + (int) Math.pow(2, binary.length() - i - 1) + "<td></tr>");
			}
		}

		sb.append("<table>");

		if (resultSb.length() > 3)
		{
			resultSb.setLength(resultSb.length() - 2);
			resultSb.append(
					"=&nbsp;<FONT Color=RED><b>" + Integer.toString(Integer.parseInt(binary, 2)) + "</b></FONT>");
		}

		sb.append("<br><br><b>Add up each power of 2 where the binary bit equals 1: <br></b>" + resultSb.toString());
		return sb.toString();
	}

	@Override
	public String showMeToBinary()
	{
		return "<b>" + binary + "<sub>2</sub></b>";
	}

	@Override
	public String showMeToHexadecimal()
	{
		StringBuilder sb = new StringBuilder("<b>" + binary + "<sub>2</sub> to Hexadecimal:</b><br>");
		sb.append("First, seperate the hexadecimal into groups of 4, placing the leftovers in the left-most group."
				+ "<br>" + "Each group of 4 binary characters represents 1 hexadecimal character.");
		StringBuilder intermediateSb = new StringBuilder("<tr>"); // on its own line
		StringBuilder resultSb = new StringBuilder("<tr>"); // on its own line
		int length = binary.length();

		// Get number of groups of 4
		int groupsOf4 = length / 4; // always rounds down
		if (length % 4 != 0)
		{
			groupsOf4++;
		}

		sb.append("<table align=\"center\" style=\"width:50%\">");

		sb.append("<tr>");
		// get appropriate substring
		for (int i = 0; i < groupsOf4; i++)
		{
			boolean lastIteration = i == (groupsOf4 - 1);
			// if i = 0 then start at 0, other wise start at length - (i*4)
			String subString;
			if (i == 0 && binary.length() % 4 != 0) // if the first group of 4 is less than 4 characters
			{
				// dont divide by zero if input is less than 4 characters
				int leftover = (groupsOf4 == 1) ? length % (4) : length % (4 * (groupsOf4 - 1));
				// the remaining string (less than 4 characters)
				subString = binary.substring(0, leftover);
			}
			else // dealing with a group of 4 characters
			{
				int startSubString = (i == 0) ? 0 : length - ((groupsOf4 - i) * 4);
				subString = binary.substring(startSubString, startSubString + 4);
			}

			sb.append("<td style=\"text-align:center\" valign=\"center\">");
			intermediateSb.append("<td style=\"text-align:center\" valign=\"center\">");
			resultSb.append("<td style=\"text-align:center\" valign=\"center\">");

			int subLength = subString.length();
			for (int j = 0; j < subLength; j++)
			{
				sb.append(subString.charAt(j) + "<sup>" + (int) Math.pow(2, subLength - j - 1) + "</sup>");
				String s = (j == subLength - 1) ? "&nbsp;" : "+";
				intermediateSb.append(
						((int) Math.pow(2, subLength - j - 1)) * Integer.parseInt(subString.substring(j, j + 1)) + s);
			}

			if (!lastIteration)
			{
				sb.append("</td> <td></td>");
				intermediateSb.append("</td> <td>&emsp; + &emsp;</td>");
				resultSb.append(Integer.toHexString(Integer.parseInt(subString, 2)) + "</td> <td>&emsp; + &emsp;</td>");
			}
			else
			{
				sb.append("</td><td>&emsp;</td>");
				intermediateSb.append("<td>&emsp;</td>");
				sb.append("</td><td>&emsp;</td>");
				intermediateSb.append("<td>&emsp;&emsp;</td>");
				resultSb.append(Integer.toHexString(Integer.parseInt(subString, 2)) + "</td>");
				resultSb.append("<td> = </td><td><FONT Color=RED>" + Integer.toHexString(Integer.parseInt(binary, 2))
						+ "</font></td>");
			}
		}

		sb.append("</tr>");

		sb.append(intermediateSb.toString() + "</tr>" + resultSb.toString() + "</tr>");

		sb.append("</table>");

		return sb.toString();
	}

	@Override
	public String showMeToOctal()
	{
		StringBuilder sb = new StringBuilder("<b>" + binary + "<sub>2</sub> to Octal:</b><br>");
		sb.append("First, seperate the binary string into groups of 3, placing the leftovers in the left-most group."
				+ "<br>" + "Each group of 3 binary characters represents 1 octal character.");
		StringBuilder intermediateSb = new StringBuilder("<tr>"); // on its own line
		StringBuilder resultSb = new StringBuilder("<tr>"); // on its own line
		int length = binary.length();

		// Get number of groups of 3
		int groupsOf3 = length / 3; // always rounds down
		if (length % 3 != 0)
		{
			groupsOf3++;
		}

		sb.append("<table align=\"center\" style=\"width:50%\">");

		sb.append("<tr>");
		// get appropriate substring
		for (int i = 0; i < groupsOf3; i++)
		{
			boolean lastIteration = i == (groupsOf3 - 1);
			// if i = 0 then start at 0, other wise start at length - (i*4)
			String subString;
			if (i == 0 && binary.length() % 3 != 0) // if the first group of 4 is less than 4 characters
			{
				// dont divide by zero if input is less than 4 characters
				int leftover = (groupsOf3 == 1) ? length % (3) : length % (3 * (groupsOf3 - 1));
				// the remaining string (less than 4 characters)
				subString = binary.substring(0, leftover);
			}
			else // dealing with a group of 4 characters
			{
				int startSubString = (i == 0) ? 0 : length - ((groupsOf3 - i) * 3);
				subString = binary.substring(startSubString, startSubString + 3);
			}

			sb.append("<td style=\"text-align:center\" valign=\"center\">");
			intermediateSb.append("<td style=\"text-align:center\" valign=\"center\">");
			resultSb.append("<td style=\"text-align:center\" valign=\"center\">");

			int subLength = subString.length();
			for (int j = 0; j < subLength; j++)
			{
				sb.append(subString.charAt(j) + "<sup>" + (int) Math.pow(2, subLength - j - 1) + "</sup>");
				String s = (j == subLength - 1) ? "&nbsp;" : "+";
				intermediateSb.append(
						((int) Math.pow(2, subLength - j - 1)) * Integer.parseInt(subString.substring(j, j + 1)) + s);
			}

			if (!lastIteration)
			{
				sb.append("</td> <td></td>");
				intermediateSb.append("</td> <td>&emsp; + &emsp;</td>");
				resultSb.append(
						Integer.toOctalString(Integer.parseInt(subString, 2)) + "</td> <td>&emsp; + &emsp;</td>");
			}
			else
			{
				sb.append("</td><td>&emsp;</td>");
				intermediateSb.append("<td>&emsp;</td>");
				sb.append("</td><td>&emsp;</td>");
				intermediateSb.append("<td>&emsp;&emsp;</td>");
				resultSb.append(Integer.toOctalString(Integer.parseInt(subString, 2)) + "</td>");
				resultSb.append("<td> = </td><td><FONT Color=RED>" + Integer.toOctalString(Integer.parseInt(binary, 2))
						+ "</font></td>");
			}
		}

		sb.append("</tr>");

		sb.append(intermediateSb.toString() + "</tr>" + resultSb.toString() + "</tr>");

		sb.append("</table>");

		return sb.toString();
	}

	@Override
	public boolean isValid()
	{
		char temp;
		for (int i = 0; i < binary.length(); i++)
		{
			temp = binary.charAt(i);
			if (temp != '1' && temp != '0')
			{
				return false;
			}
		}
		return true;
	}
}
