package com.hfutxqd.cal;

import android.widget.ScrollView;

//显示类，负责显示到屏幕上去
public class Display {
	public static void addpri(String s) { // 将字符加到屏幕中字符的后面
		MainActivity.scroll.fullScroll(ScrollView.FOCUS_DOWN);
		String a = MainActivity.txt.getText().toString();
		if (a.endsWith("+") || a.endsWith("-") || a.endsWith("×")
				|| a.endsWith("÷"))
		{
			MainActivity.pointable = false;
			MainActivity.txt.setText(a + s);
		}
		if (a.indexOf("错误") != -1) {
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
				MainActivity.txt.setText(s); // 如果屏幕上显示的是0，就直接把所按的键显示在屏幕上
		} else {
			String b = a.substring(a.length() - 1);
			if (s.equals(".")) {
				if (MainActivity.pointable == true) {
					MainActivity.txt.setText(a + s);
					MainActivity.pointable = false;
				} else
					;
			} else if ((b.equals("+") || b.equals("-") || b.equals("×") || b
					.equals("÷"))
					&& (s.equals("+") || s.equals("-") || s.equals("×") || s
							.equals("÷"))) {
			} else {
				if (b.equals("+") || b.equals("-") || b.equals("×")
						|| b.equals("÷"))
					MainActivity.pointable = true;
				MainActivity.txt.setText(a + s);
			}
		}
	}
}
