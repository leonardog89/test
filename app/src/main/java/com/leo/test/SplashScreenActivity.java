package com.leo.test;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.leo.test.Presentation.Category.Implementation.CategoriesActivity;

public class SplashScreenActivity extends Activity {
	ImageView imageSplash;
	int width, height;
	private double pulgadas;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		double x = Math.pow(dm.widthPixels/dm.xdpi,2);
		double y = Math.pow(dm.heightPixels/dm.ydpi,2);
		pulgadas = Math.sqrt(x+y);
		pulgadas=  (double)Math.round(pulgadas * 10) / 10;
		Log.d("debug", "Screen inches : " + pulgadas);

        getWindow().setFormat(PixelFormat.RGBA_8888);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashscreen);
		Display display = getWindowManager().getDefaultDisplay(); 
        width = display.getWidth();
        height = display.getHeight();
		imageSplash = (ImageView)findViewById(R.id.imageSplash);
		imageSplash.setVisibility(View.VISIBLE);
		AnimationSet as = new AnimationSet(true);
        Animation aa = new ScaleAnimation((float)0.6
        		, (float)0.5, (float)0.6, (float)0.5
				, width / 2
				, height / 2);
		aa.setDuration(1000);
		aa.setStartOffset(1000);
		as.addAnimation(aa);
		aa = new AlphaAnimation(0, 1);
		aa.setDuration(1000);
		aa.setStartOffset(1000);
		as.addAnimation(aa);
		as.setFillEnabled(true);
		as.setFillAfter(true);
		as.setInterpolator(new DecelerateInterpolator());
		as.setStartTime(1000);
		imageSplash.startAnimation(as);
		imageSplash.setVisibility(View.VISIBLE);

		Runnable runnable = new Runnable() {
			public void run() {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent goToCAtegories = new Intent(SplashScreenActivity.this, CategoriesActivity.class);
				startActivity(goToCAtegories);
				SplashScreenActivity.this.finish();
			}
		};
		new Thread(runnable).start();
	}

}
