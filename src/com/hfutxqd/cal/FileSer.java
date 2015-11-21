package com.hfutxqd.cal;

//文件服务类，主要功能为保存和读取
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class FileSer {
	private String filename;
	private Context context;

	public FileSer(Context context, String filename) {
		this.filename = filename;
		this.context = context;
	}

	public void output(String content) throws IOException {
		FileOutputStream outStream = context.openFileOutput(filename,
				Context.MODE_PRIVATE);
		outStream.write(content.getBytes());
		outStream.close();
	}

	public String input() throws IOException {
		FileInputStream inStream = context.openFileInput(filename);
		byte[] buffer = new byte[1024];
		inStream.read(buffer);
		inStream.close();
		String res = new String(buffer);
		int count = 0;
		for (int i = 0; i < 1024; i++) {
			if (res.charAt(i) == '1' || res.charAt(i) == '2'
					|| res.charAt(i) == '3' || res.charAt(i) == '4'
					|| res.charAt(i) == '5' || res.charAt(i) == '6'
					|| res.charAt(i) == '7' || res.charAt(i) == '8'
					|| res.charAt(i) == '9' || res.charAt(i) == '0'
					|| res.charAt(i) == ' ') {
				count++;
			} else
				break;
		}
		res = res.substring(0, count);
		return res;
	}
}
