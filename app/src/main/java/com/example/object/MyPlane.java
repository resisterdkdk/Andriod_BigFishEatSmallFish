package com.example.object;

import java.util.ArrayList;
import java.util.List;
import com.example.factory.GameObjectFactory;
import com.example.interfaces.IMyPlane;
import com.example.mybeatplane.R;
import com.example.view.MainView;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
/*玩家飞机的类*/
public class MyPlane extends GameObject implements IMyPlane{
	private float middle_x;			 // 飞机的中心坐标
	private float middle_y;
	protected float object_width_2; 	// 对象的宽度
	protected float object_height_2; 	// 对象的高度
	protected float object_width_3; 	// 对象的宽度
	protected float object_height_3; 	// 对象的高度
    protected float object_width_4; 	// 对象的宽度
    protected float object_height_4; 	// 对象的高度
    protected float object_width_5; 	// 对象的宽度
    protected float object_height_5; 	// 对象的高度
    protected float object_width_6; 	// 对象的宽度
    protected float object_height_6; 	// 对象的高度
    protected float object_width_7; 	// 对象的宽度
    protected float object_height_7; 	// 对象的高度
	private long startTime;	 	 	 // 开始的时间
	private long endTime;	 	 	 // 结束的时间
	private boolean isChangeBullet;  // 标记更换了子弹
	private Bitmap myfish1;			 // 飞机飞行时的图片
	private Bitmap myfish12;		 // 飞机爆炸时的图片
	private Bitmap myfish2;			 // 飞机飞行时的图片
	private Bitmap myfish22;		 // 飞机爆炸时的图片
	private Bitmap myfish3;			 // 飞机飞行时的图片
	private Bitmap myfish32;		 // 飞机爆炸时的图片
	private Bitmap myfish4;			 // 飞机飞行时的图片
	private Bitmap myfish42;		 // 飞机爆炸时的图片
	private Bitmap myfish5;			 // 飞机飞行时的图片
	private Bitmap myfish52;		 // 飞机爆炸时的图片
	private Bitmap myfish6;			 // 飞机飞行时的图片
	private Bitmap myfish62;		 // 飞机爆炸时的图片
	private Bitmap myfish7;			 // 飞机飞行时的图片
	private Bitmap myfish72;		 // 飞机爆炸时的图片



	private Bitmap myMidfish;			 // 飞机飞行时的图片
	private Bitmap myMidfish2;		 // 飞机爆炸时的图片
	private Bitmap myBigfish;			 // 飞机飞行时的图片
	private Bitmap myBigfish2;		 // 飞机爆炸时的图片
	private MainView mainView;
	private GameObjectFactory factory;
	public MyPlane(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		initBitmap();
		this.speed = 100;
		this.dir = 1;
		this.power = 2;
		factory = new GameObjectFactory();
	}
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	// 设置屏幕宽度和高度
	@Override
	public void setScreenWH(float screen_width, float screen_height) {
		super.setScreenWH(screen_width, screen_height);
		object_x = screen_width/2 - object_width/2;;
		object_y = screen_height/2 - object_height/2;
		middle_x = object_x + object_width/2;
		middle_y = object_y + object_height/2;
	}
	// 初始化图片资源的
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		myfish1 =  BitmapFactory.decodeResource(resources, R.drawable.myfish1_1);
		myfish12 = BitmapFactory.decodeResource(resources, R.drawable.myfish1_2);
		myfish2 =  BitmapFactory.decodeResource(resources, R.drawable.myfish2_1);
		myfish22 = BitmapFactory.decodeResource(resources, R.drawable.myfish2_2);
		myfish3 =  BitmapFactory.decodeResource(resources, R.drawable.myfish3_1);
		myfish32 = BitmapFactory.decodeResource(resources, R.drawable.myfish3_2);
		myfish4 =  BitmapFactory.decodeResource(resources, R.drawable.myfish4_1);
		myfish42 = BitmapFactory.decodeResource(resources, R.drawable.myfish4_2);
		myfish5 =  BitmapFactory.decodeResource(resources, R.drawable.myfish5_1);
		myfish52 = BitmapFactory.decodeResource(resources, R.drawable.myfish5_2);
		myfish6 =  BitmapFactory.decodeResource(resources, R.drawable.myfish6_1);
		myfish62 = BitmapFactory.decodeResource(resources, R.drawable.myfish6_2);
		myfish7 =  BitmapFactory.decodeResource(resources, R.drawable.myfish7_1);
		myfish72 = BitmapFactory.decodeResource(resources, R.drawable.myfish7_2);

	}
	// 对象的绘图方法
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		if(this.power/2==1) {
			object_width = myfish1.getWidth(); // 获得每一帧位图的宽
			object_height = myfish1.getHeight();    // 获得每一帧位图的高
		}
		if(this.power/2==2) {
			object_height = myfish2.getHeight();
			object_width = myfish2.getWidth();
		}
		if(this.power/2==3) {
			object_height = myfish3.getHeight();
			object_width = myfish3.getWidth();
		}
		if(this.power/2==4) {
			object_height = myfish4.getHeight();
			object_width = myfish4.getWidth();
		}
		if(this.power/2==5) {
			object_height = myfish5.getHeight();
			object_width = myfish5.getWidth();
		}
		if(this.power/2==6) {
			object_height = myfish6.getHeight();
			object_width = myfish6.getWidth();
		}
		if(this.power/2==7) {
			object_height = myfish7.getHeight();
			object_width = myfish7.getWidth();
		}
		if(isAlive){
			int x = (int) (currentFrame * object_width); // 获得当前帧相对于位图的X坐标
			if(dir == 1)
			{
				canvas.save();
				if(this.getPower()/2==1)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish1, object_x, object_y, paint);
				}

				if(this.getPower()/2==2)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish2, object_x, object_y, paint);
				}

				if(this.getPower()/2==3)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish3, object_x, object_y, paint);
				}
				if(this.getPower()/2==4)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish4, object_x, object_y, paint);
				}
				if(this.getPower()/2==5)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish5, object_x, object_y, paint);
				}
				if(this.getPower()/2==6)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish6, object_x, object_y, paint);
				}
				if(this.getPower()/2==7)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish7, object_x, object_y, paint);
				}
				canvas.restore();
			}
			else
			{
				canvas.save();

				if(this.getPower()/2==1)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish12, object_x, object_y, paint);
				}

				if(this.getPower()/2==2)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish22, object_x, object_y, paint);
				}

				if(this.getPower()/2==3)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish32, object_x, object_y, paint);
				}
				if(this.getPower()/2==4)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish42, object_x, object_y, paint);
				}
				if(this.getPower()/2==5)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish52, object_x, object_y, paint);
				}
				if(this.getPower()/2==6)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish62, object_x, object_y, paint);
				}
				if(this.getPower()/2==7)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish72, object_x, object_y, paint);
				}
				canvas.restore();
			}

		}
	}
	// 释放资源的方法
	@Override
	public void release() {
		// TODO Auto-generated method stub
		if(!myfish1.isRecycled()){
			myfish1.recycle();
		}
		if(!myfish12.isRecycled()){
			myfish12.recycle();
		}
		if(!myfish2.isRecycled()){
			myfish2.recycle();
		}
		if(!myfish22.isRecycled()){
			myfish22.recycle();
		}
		if(!myfish3.isRecycled()){
			myfish3.recycle();
		}
		if(!myfish32.isRecycled()){
			myfish32.recycle();
		}
		if(!myfish4.isRecycled()){
			myfish4.recycle();
		}
		if(!myfish42.isRecycled()){
			myfish42.recycle();
		}
		if(!myfish5.isRecycled()){
			myfish5.recycle();
		}
		if(!myfish52.isRecycled()){
			myfish52.recycle();
		}
		if(!myfish6.isRecycled()){
			myfish6.recycle();
		}
		if(!myfish62.isRecycled()){
			myfish62.recycle();
		}
		if(!myfish7.isRecycled()){
			myfish7.recycle();
		}
		if(!myfish72.isRecycled()){
			myfish72.recycle();
		}
	}

	//getter和setter方法
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	@Override
	public float getMiddle_x() {
		return middle_x;
	}
	@Override
	public void setMiddle_x(float middle_x) {
		this.middle_x = middle_x;
		this.object_x = middle_x - object_width/2;
	}
	@Override
	public float getMiddle_y() {
		return middle_y;
	}
	@Override
	public void setMiddle_y(float middle_y) {
		this.middle_y = middle_y;
		this.object_y = middle_y - object_height/2;
	}	
}
