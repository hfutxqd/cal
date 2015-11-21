package com.hfutxqd.cal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ScrollView;

public class MyOnTouchListener implements OnTouchListener {

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {

		if (arg1.getAction() == MotionEvent.ACTION_UP) // °´¼üÉÏÆÁ
		{
			switch (((Button) arg0).getId()) {
			case R.id.cancel:
				String s = MainActivity.txt.getText().toString();
				if (s.indexOf("=") == -1 && s.length() > 1 && !s.equals("´íÎó")) {
					if (s.substring(s.length() - 1).equals("."))
						MainActivity.pointable = true;
					s = s.substring(0, s.length() - 1);
					MainActivity.txt.setText(s);
				} else {
					MainActivity.pointable = true;
					MainActivity.txt.setText("0");
				}

				break;
			case R.id.clear:
				MainActivity.pointable = true;
				MainActivity.txt.setText("0");
				break;
			case R.id.left:
			case R.id.right:
			case R.id.add:
			case R.id.minus:
			case R.id.multiply:
			case R.id.divide:
			case R.id.point:
				Display.addpri(((Button) arg0).getText().toString());
				break;
			case R.id.num0:
			case R.id.num1:
			case R.id.num2:
			case R.id.num3:
			case R.id.num4:
			case R.id.num5:
			case R.id.num6:
			case R.id.num7:
			case R.id.num8:
			case R.id.num9:
				String tmp = MainActivity.txt.getText().toString();
				if (tmp.length() > 4 && tmp.charAt(tmp.length() - 4) == 'E')
					MainActivity.txt.setText("0");
				Display.addpri(((Button) arg0).getText().toString());
				break;
			case R.id.equal:
				String s1 = MainActivity.txt.getText().toString();
				if (s1.indexOf("=") != -1) {
					s1 = s1.substring(s1.indexOf("=") + 1, s1.length());
				} else {
					try {
						String r = Compute.compute(MainActivity.txt.getText()
								.toString(), MainActivity.precision,
								MainActivity.group, MainActivity.science,
								MainActivity.max);
						if (r.indexOf(".") != -1)
							MainActivity.pointable = false;
						Display.addpri("=");
						Display.addpri(r);
					} catch (Exception e) {
						MainActivity.txt.setText("´íÎó");
					}
				}
				MainActivity.scroll.fullScroll(ScrollView.FOCUS_DOWN);
				break;

			default:
				break;
			}
		}
		return false;
	}
}
