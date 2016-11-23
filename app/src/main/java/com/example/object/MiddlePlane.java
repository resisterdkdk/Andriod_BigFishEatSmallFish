package com.example.object;

import java.util.Random;
import com.example.mybeatplane.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
/*中型敌机的类*/
public class MiddlePlane extends EnemyPlane{
	private static int currentCount = 0;	 //	对象当前的数量
	private Bitmap middlefish;// 对象图片
	private Bitmap middlefish1;// 对象图片
	public static int sumCount = 4;	 	 	 //	对象总的数量
	public MiddlePlane(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		this.power = 3;
		this.score = 1000;		// 为对象设置分数
	}
	//初始化数据
	@Override
	public void initial(int arg0,float arg1,float arg2){
		super.initial(arg0,arg1,arg2);
		isAlive = true;
		bloodVolume = 15;
		blood = bloodVolume;
		Random ran = new Random();
		speed = ran.nextInt(30) + 6 * arg0;
		if(this.getDirection2()==0)
		{
			object_x = screen_width+object_width * (currentCount*2 + 1);
			object_y = ran.nextInt((int)(screen_height - object_height));
		}
		if(this.getDirection2()==1)
		{
			object_x = -object_width * (currentCount*2 + 1);
			object_y = ran.nextInt((int)(screen_height - object_height));
		}
		currentCount++;
		if(currentCount >= sumCount){
			currentCount = 0;
		}
	}
	// 初始化图片资源
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		middlefish = BitmapFactory.decodeResource(resources, R.drawable.middle_1);
		middlefish1 = BitmapFactory.decodeResource(resources, R.drawable.middle_2);
		object_width = middlefish.getWidth();			//获得每一帧位图的宽
		object_height = middlefish.getHeight();		//获得每一帧位图的高
	}
	// 对象的绘图函数
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		if(isAlive){
			if(!isExplosion){
				if(isVisible){
					canvas.save();
					canvas.clipRect(object_x,object_y,object_x + object_width,object_y + object_height);//规定4个角
					if(this.getDirection2()==0)
						canvas.drawBitmap(middlefish, object_x, object_y, paint);
					if(this.getDirection2()==1)
						canvas.drawBitmap(middlefish1, object_x, object_y, paint);
					//canvas.clipRect(x1, y1, x1 + w, y1 + h);
					//canvas.drawBitmap(bitmap, x2, y2, paint);
					//2、clipRect()截取画布中的一个区域；
					//3、drawBitmap()绘制图片到（x2, y2)上，则绿色部分刚好绘制到(x1, y1)上，而没有被clip的区域则不会绘图
					canvas.restore();
				}	
				logic();
			}
			else{
					isExplosion = false;
					isAlive = false;
				}
			}
	}
	// 释放资源
	@Override
	public void release() {
		// TODO Auto-generated method stub
		if(!middlefish.isRecycled()){
			middlefish.recycle();
		}
		if(!middlefish1.isRecycled()){
			middlefish1.recycle();
		}
	}
}
