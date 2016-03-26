package aBin.love.huibing;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.view.animation.*;
import android.view.Gravity;

public class FlyTxtView extends ViewGroup
{
	//设置需要的变量
	private int textColor;
	private int textSize;
	private AnimationSet animationSet;
	private LayoutAnimationController layoutAnimationController;
	private Animation animation;

	public FlyTxtView(Context context)
	{
		super(context, null);
	}

	public FlyTxtView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public FlyTxtView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init();
	}
	

	// init初始化视图
	public void init()
	{
		animationSet = new AnimationSet(true);
		layoutAnimationController = new LayoutAnimationController(animationSet, 0.3f);
		layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
		setAnimation(null);
		setLayoutAnimation(layoutAnimationController);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		for (int index = 0; index < getChildCount(); index++)
		{
			final View child = getChildAt(index);
			// 测量
			child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		final int count = getChildCount();
		int row = 0;// which row lay you view relative to parent
		int lengthX = left; // right position of child relative to parent
		int lengthY = top; // bottom position of child relative to parent
		for (int i = 0; i < count; i++)
		{
			final View child = this.getChildAt(i);
			int width = child.getMeasuredWidth();
			int height = child.getMeasuredHeight();
			lengthX += width ;
			lengthY = row * height  + height + top;
			// 如果文字不能在一行显示, 跳到下一行
			if (lengthX > right)
			{
				lengthX = width ;
				row++;
				lengthY = row * height   + height + top;
			}
			child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
		}
	}

	// 设置文本内容
	public void setTexts(String text)
	{
		removeAllViews();
		char[] chars = text.toCharArray();
		int count = chars.length;
		for (int i = 0; i < count; i++)
		{
			TextView tv = new TextView(getContext());
			tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			tv.setText(chars[i] + "");
			tv.setTextColor(textColor);
			tv.setTextSize(textSize);
			tv.setGravity(Gravity.CENTER);
			addView(tv);
		}
	}
	
	// 必须在设置文本内容之前配置
	// 设置文本颜色
	public void setTextColor(int textColor)
	{
		this.textColor = textColor;
	}

	// 必须在设置文本内容之前配置
	// 设置文本大小
	public void setTextSize(int textSize)
	{
		this.textSize = textSize;
	}

	public void setAnimation(Animation animation)
	{
		if (null != animation)
		{
			animationSet.addAnimation(animation);
		}
		else
		{
			setDefaultAnimation();
		}
	}

	// 开始动画
	public void startAnimation()
	{
		startLayoutAnimation();
	}

	// 默认动画特效
	private void setDefaultAnimation()
	{
		//淡出淡入效果
		animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(700);
		animationSet.addAnimation(animation);
		//移动效果
		animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
		animation.setDuration(500);
		animationSet.addAnimation(animation);
		//旋转效果
		animation = new RotateAnimation(-90, 360,Animation.RELATIVE_TO_SELF,0f, Animation.RELATIVE_TO_SELF,0.5f);
		animation.setDuration(800);
		animationSet.addAnimation(animation);
		//缩放效果
		animation = new ScaleAnimation(2.5f, 1.0f, 2.5f, 1.0f,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(500);
		animationSet.addAnimation(animation);
	}
}
