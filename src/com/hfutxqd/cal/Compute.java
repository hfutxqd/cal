package com.hfutxqd.cal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

//�����࣬������ʽ�ַ�������ȷ�ȣ��ͷ�����Ϣ�����ؼ�����
public class Compute {
	public static String compute(String s, int precision, boolean group,
			boolean science, String max) {
		String min = "-" + max;
		s = s.replaceAll(",", "");
		if (s.startsWith("��") || s.startsWith("��") || s.startsWith(")")) {
			System.out.println("�ַ�����ͷ�Ƿ���");
			return "����";
		} else if (s.startsWith("+") || s.startsWith("-")) {
			String a = "0";
			s = a.concat(s);
		}
		if (s.endsWith("+") || s.endsWith("-") || s.endsWith("��")
				|| s.endsWith("��")) {
			s = s.substring(0, s.length() - 1);
		}
		s = s.replaceAll("��", "*");
		s = s.replaceAll("��", "/");
		//s = s.replaceAll(")(", ")*(");
		System.out.println(s);
		String r = Calculator.toPolishNotation(s);
		BigDecimal res = new BigDecimal(r);
		if (science
				&& (res.compareTo(new BigDecimal(max)) > 0 || res
						.compareTo(new BigDecimal(min)) < 0)) {
			DecimalFormat df = new DecimalFormat("0.#######E000");
			return df.format(res).toString();
		} else {
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(precision);
			nf.setGroupingUsed(group);
			return nf.format(res).toString();
		}
	}
}
