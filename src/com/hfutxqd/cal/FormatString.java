package com.hfutxqd.cal;

public class FormatString {
	public static int [] left = new int[1000];
	public static int [] right = new int[1000];
	public static String [] str = new String[1000];
	
	public static String Format(String s) 
	{
		int  count = 0;
		for (int i = 0; i < s.length(); i++)
		{
			if (s.indexOf(i) == (int)'(')
			{
				left[count] = i;
			}
		}
		if (count == 0)
			return s;
		count = 0;
		for (int i = s.length() - 1; i >= 0; i--)
		{
			if (s.indexOf(i) == (int)')')
			{
				right[count] = i;
				String res = s.substring(left[count], right[count] + 1);
				res = res.replaceAll("(", "");
				res = res.replaceAll(")", "");
				res = Compute.compute(res, 30, false, false, "100000000000");
				return Format(res);
			}
		}
		return s;
	}
}
