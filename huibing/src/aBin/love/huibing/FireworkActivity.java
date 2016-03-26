package aBin.love.huibing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;

import aBin.love.huibing.fireview.FireworkView;

public class FireworkActivity extends Activity {
	//Added by Dumbbell Yang at 2014-05-03
	static final String FIREWORK_TEXT = "FireworkText";
	static final String FIREWORK_SIZE = "FireworkSize";

	static final String LOG_TAG = FireworkActivity.class.getSimpleName();
	static int SCREEN_W = 480;// 当前窗口的大小
	static int SCREEN_H = 800;

	FireworkView fireworkView;

	// get the current looper (from your Activity UI thread for instance
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		//added by Dumbbell Yang at 2014-05-03
		String fireworkText = "惠冰我喜欢你";
		int fireworkSize = 32;

		fireworkView = new FireworkView(this, fireworkText, fireworkSize);
		setContentView(fireworkView);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (fireworkView.isRunning()) {
			fireworkView.setRunning(false);
		}
	}
}
