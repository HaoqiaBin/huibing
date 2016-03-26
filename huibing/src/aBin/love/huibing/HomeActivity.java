package aBin.love.huibing;

import android.app.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.content.*;
import aBin.love.huibing.fireview.*;

public class HomeActivity extends Activity
{
	private FlyTxtView flyTxtView;
	/** 创建主方法 **/
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setupViews();
	}

	/** 文字飞入特效 **/
	protected void setupViews()
	{
		flyTxtView = (FlyTxtView) findViewById(R.id.flytextview);
		flyTxtView.setTextSize(14);
		flyTxtView.setTextColor(Color.WHITE);
		flyTxtView.setTexts(this.getString(R.string.hello));
		flyTxtView.startAnimation();
	}


	/** 返回键事件 **/
	long touchTime = 0;
	@Override
	public void onBackPressed()
	{
		long currentTime = System.currentTimeMillis();
		if ((currentTime - touchTime) >= 2000)
		{
			Toast.makeText(this, "亲，再按一次退出♥", Toast.LENGTH_LONG).show();
			touchTime = currentTime;	
		}
		else
		{
			finish();
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}

	/** 加载Menu.xml **/
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	/** 选项菜单事件处理 **/
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		CustomDialog();//显示自定义Dialog
		return true;
	}

	/** 自定义Dialog **/
	@Override
	public void CustomDialog()
	{	
		AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
		dialog.setView(getLayoutInflater().inflate(R.layout.about, null));
		Window window = dialog.getWindow();  //获取dialog控件
		window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置  
		window.setWindowAnimations(R.style.Toast); //添加动画
		dialog.show();
	}

	/** 显示Dialog **/
	@Override
	public void showLove(View v)
	{
		AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
		dialog.setView(getLayoutInflater().inflate(R.layout.dialog, null));
		Window window = dialog.getWindow();  //获取dialog控件
		window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置  
		window.setWindowAnimations(R.style.newToast); //添加动画
		dialog.show();
	}
	
	/** 跳转到烟花 **/
	@Override
	public void firework(View v)
	{
		Intent intent = new Intent();
		intent = intent.setClass(HomeActivity.this, FireworkActivity.class);
		startActivity(intent);
	}
}
