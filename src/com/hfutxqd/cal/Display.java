package com.hfutxqd.cal;

import android.widget.ScrollView;

//��ʾ�࣬������ʾ����Ļ��ȥ
public class Display {
	public static void addpri(String s) { // ���ַ��ӵ���Ļ���ַ��ĺ���
		MainActivity.scroll.fullScroll(ScrollView.FOCUS_DOWN);
		String a = MainActivity.txt.getText().toString();
		if (a.endsWith("+") || a.endsWith("-") || a.endsWith("��")
				|| a.endsWith("��"))
		{
			MainActivity.pointable = false;
			MainActivity.txt.setText(a + s);
		}
		if (a.indexOf("����") != -1) {
			MainActivity.txt.setText("0");
			a = MainActivity.txt.getText().toString();
		}

		if (a.indexOf("=") != -1) {
			if (a.indexOf("=") == a.length() - 1) {
				a = a + "\n";
				MainActivity.txt.setText(a);
			} else {
				a = a.substring(a.indexOf("=") + 2, a.length());
				MainActivity.txt.setText(a);
			}
		}

		if (a.equals("0")) {
			if (s.equals(".") && MainActivity.pointable == true) {
				MainActivity.pointable = false;
				MainActivity.txt.setText("0.");
			} else if (s.equals("=")) {
				MainActivity.txt.setText(MainActivity.txt.getText().toString()
						+ s);
			} else
				MainActivity.txt.setText(s); // �����Ļ����ʾ����0����ֱ�Ӱ������ļ���ʾ����Ļ��
		} else {
			String b = a.substring(a.length() - 1);
			if (s.equals(".")) {
				if (MainActivity.pointable == true) {
					MainActivity.txt.setText(a + s);
					MainActivity.pointable = false;
				} else
					;
			} else if ((b.equals("+") || b.equals("-") || b.equals("��") || b
					.equals("��"))
					&& (s.equals("+") || s.equals("-") || s.equals("��") || s
							.equals("��"))) {
			} else {
				if (b.equals("+") || b.equals("-") || b.equals("��")
						|| b.equals("��"))
					MainActivity.pointable = true;
				MainActivity.txt.setText(a + s);
			}
		}
	}
}
