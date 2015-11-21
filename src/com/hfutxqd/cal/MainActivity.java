package com.hfutxqd.cal;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.hfutxqd.cal.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
	protected static Button left, right, cancel, clear, add, minus, multiply, divide, point,
			equal, num9, num8, num7, num6, num5, num4, num3, num2, num1, num0;
	protected static ScrollView scroll;
	protected static TextView txt;
	protected static boolean pointable = true;
	protected static String filecontent;
	protected static boolean group = true;
	protected static int precision = 8;
	protected static boolean science = true;
	protected static String max = "10000000000";
	private MyOnTouchListener EVENT = new MyOnTouchListener();
	
	protected void onCreate(Bundle savedInstanceState) { // 构造方法

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
	}

	private void init() // 各类初始化
	{
		if (!(new File("/data/data/com.hfutxqd.cal/files/cal2.ini").exists())) // 读取已经保存的信息，如果没有则新建
		{
			FileSer set = new FileSer(getApplicationContext(), "cal2.ini");
			int m = group ? 1 : 0;
			filecontent = Integer.toString(precision) + Integer.toString(m)
					+ " " + max;
			try {
				set.output(filecontent);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else {
			FileSer set = new FileSer(getApplicationContext(), "cal2.ini");
			try {
				filecontent = set.input();
				Scanner in = new Scanner(filecontent);
				int tmp;
				tmp = in.nextInt();
				precision = tmp / 10;
				group = tmp % 2 == 1 ? true : false;
				max = in.next();
				if (Double.parseDouble(max) == 0) {
					science = false;
				}
				in.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		scroll = (ScrollView) findViewById(R.id.scroll);
		txt = (TextView) findViewById(R.id.display);
		left = (Button) findViewById(R.id.left);
		right = (Button) findViewById(R.id.right);
		cancel = (Button) findViewById(R.id.cancel);
		clear = (Button) findViewById(R.id.clear);
		add = (Button) findViewById(R.id.add);
		minus = (Button) findViewById(R.id.minus);
		multiply = (Button) findViewById(R.id.multiply);
		divide = (Button) findViewById(R.id.divide);
		point = (Button) findViewById(R.id.point);
		equal = (Button) findViewById(R.id.equal);
		num9 = (Button) findViewById(R.id.num9);
		num8 = (Button) findViewById(R.id.num8);
		num7 = (Button) findViewById(R.id.num7);
		num6 = (Button) findViewById(R.id.num6);
		num5 = (Button) findViewById(R.id.num5);
		num4 = (Button) findViewById(R.id.num4);
		num3 = (Button) findViewById(R.id.num3);
		num2 = (Button) findViewById(R.id.num2);
		num1 = (Button) findViewById(R.id.num1);
		num0 = (Button) findViewById(R.id.num0);

		left.setOnTouchListener(EVENT);
		right.setOnTouchListener(EVENT);
		clear.setOnTouchListener(EVENT);
		cancel.setOnTouchListener(EVENT);
		add.setOnTouchListener(EVENT);
		minus.setOnTouchListener(EVENT);
		multiply.setOnTouchListener(EVENT);
		divide.setOnTouchListener(EVENT);
		point.setOnTouchListener(EVENT);
		equal.setOnTouchListener(EVENT);
		num9.setOnTouchListener(EVENT);
		num8.setOnTouchListener(EVENT);
		num7.setOnTouchListener(EVENT);
		num6.setOnTouchListener(EVENT);
		num5.setOnTouchListener(EVENT);
		num4.setOnTouchListener(EVENT);
		num3.setOnTouchListener(EVENT);
		num2.setOnTouchListener(EVENT);
		num1.setOnTouchListener(EVENT);
		num0.setOnTouchListener(EVENT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { // 创建菜单
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) { // 当菜单被点击时
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_setting:
			Intent mainToSetting = new Intent(this, SettingActivity.class);
			startActivity(mainToSetting);
			break;
		case R.id.action_about:
			Intent mainToAbout = new Intent(this, AboutActivity.class);
			startActivity(mainToAbout);
			break;
		}
		return true;
	}
}
