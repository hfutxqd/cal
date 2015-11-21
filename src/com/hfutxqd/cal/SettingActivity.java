package com.hfutxqd.cal;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingActivity extends Activity{
	private EditText text2;
	private RadioButton button1, button2;
	private String filecontent;
	Button save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);	
		init();
	}
	
	private void init()  //各种初始化
	{
		text2 = (EditText) findViewById(R.id.edittext2);
		button1 = (RadioButton) findViewById(R.id.radio0);
		button2 = (RadioButton) findViewById(R.id.radio1);
		
		button1.setOnClickListener(new RadioButton.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				MainActivity.group = true;
			}
		});
		
		button2.setOnClickListener(new RadioButton.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				MainActivity.group = false;
			}
		});
		
		 if (!(new File("/data/data/com.hfutxqd.cal/files/cal2.ini").exists()))
			{
				FileSer set = new FileSer(getApplicationContext(), "cal2.ini");
				int m = MainActivity.group ? 1 : 0;
				filecontent = Integer.toString(MainActivity.precision) + Integer.toString(m) + MainActivity.max;
				try
				{
					set.output(filecontent);
				} 
				catch (IOException e) 
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		 else
		 {
			 FileSer set = new FileSer(getApplicationContext(), "cal2.ini");
			 try 
			 {
				 filecontent = set.input();
					Scanner in = new Scanner(filecontent);
					int tmp;
					tmp = in.nextInt();
					MainActivity.precision = tmp / 10;
					MainActivity.group  = tmp % 2 == 1 ? true : false;
					MainActivity.max = in.next();
					in.close();
			 } 
			 catch (IOException e) 
			 {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			 }
		 }
		text2.setText(MainActivity.max);
		if (MainActivity.group == true)
		{
			button1.setChecked(true);
			button2.setChecked(false);
		}
		else
		{
			button2.setChecked(true);
			button1.setChecked(false);
		}
	}
	
	public void onSaveClick(View v) throws IOException ////当保存按钮被按下
	{
		String s = "0";
		MainActivity.precision = Integer.parseInt(s);
		int g;
		if (MainActivity.group == true)
			g = 1;
		else
			g = 0;
		MainActivity.max = text2.getText().toString();
		if (Double.parseDouble(MainActivity.max) == 0)
			MainActivity.science = false;
		else
			MainActivity.science = true;
		s = s + String.valueOf(g) + " " + MainActivity.max;
		if (!s.equals(""))
		{
			FileSer set = new FileSer(this, "cal2.ini");
			set.output(s);
			Toast.makeText(getApplicationContext(), "设置已保存", Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(getApplicationContext(), "请正确填写!", Toast.LENGTH_SHORT).show();
	}
}
