package com.example.object;

import java.util.Random;
import com.example.mybeatplane.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
/*���͵л�����*/
public class BigPlane extends EnemyPlane{
	private static int currentCount = 0;	 //	����ǰ������
	public static int sumCount = 2;	 	 	 //	�����ܵ�����
	private Bitmap bigFish; // ����ͼƬ
	private Bitmap bigFish1; // ����ͼƬ
	private Bitmap BossFish; // ����ͼƬ
	private Bitmap BossFish1; // ����ͼƬ
	int type;
	private int object_width1;
	private int object_height1;
	public BigPlane(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		this.power = 6;
		this.score = 3000;		// Ϊ�������÷���
	}
	//��ʼ������
	@Override
	public void initial(int arg0,float arg1,float arg2){
		super.initial(arg0,arg1,arg2);
		isAlive = true;
		bloodVolume = 30;
		blood = bloodVolume;
		Random ran = new Random();
		speed = ran.nextInt(20) + 4 * arg0;
		 type = ran.nextInt(2);
		if(this.getDirection2()==0)
		{
			if(type == 0)
			{
				object_x = screen_width+object_width * (currentCount*2 + 1);
				object_y = ran.nextInt((int)(screen_height - object_height));
			}
			else
			{
				object_x = screen_width+object_width1 * (currentCount*2 + 1);
				object_y = ran.nextInt((int)(screen_height - object_height1));
			}

		}
		if(this.getDirection2()==1)
		{
			if(type == 0)
			{
				object_x = -object_width * (currentCount*2 + 1);
				object_y = ran.nextInt((int)(screen_height - object_height));
			}else
			{
				object_x = -object_width1 * (currentCount*2 + 1);
				object_y = ran.nextInt((int)(screen_height - object_height1));
			}

		}
		currentCount++;
		if(currentCount >= sumCount){
			currentCount = 0;
		}
	}
	// ��ʼ��ͼƬ��Դ	
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		bigFish = BitmapFactory.decodeResource(resources, R.drawable.big_1);
		bigFish1 = BitmapFactory.decodeResource(resources, R.drawable.big_2);
		BossFish =BitmapFactory.decodeResource(resources, R.drawable.boss_2);
		BossFish1 =BitmapFactory.decodeResource(resources, R.drawable.boss_1);
		object_width = bigFish.getWidth();			//���ÿһ֡λͼ�Ŀ�
		object_height = bigFish.getHeight();		//���ÿһ֡λͼ�ĸ�
		object_width1 = BossFish.getWidth();
		object_height1 = BossFish.getHeight();
	}
	// ����Ļ�ͼ����
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		if (isAlive) {
			if (!isExplosion) {
				if (isVisible) {
					canvas.save();
					if(type == 0)
					    canvas.clipRect(object_x, object_y, object_x + object_width, object_y + object_height);
					else
						canvas.clipRect(object_x, object_y, object_x + object_width1, object_y + object_height1);
					if(this.getDirection2()==0)
						if(type == 0)
						     canvas.drawBitmap(bigFish, object_x, object_y, paint);
					    else
							canvas.drawBitmap(BossFish, object_x, object_y, paint);
					if(this.getDirection2()==1)
						if(type == 0)
						    canvas.drawBitmap(bigFish1, object_x, object_y, paint);
					    else
							canvas.drawBitmap(BossFish1, object_x, object_y, paint);
					canvas.restore();
				}
				logic();
			} else {
				isExplosion = false;
				isAlive = false;
			}
		}
	}
	// �ͷ���Դ
	@Override
	public void release() {
		// TODO Auto-generated method stub
		if(!bigFish.isRecycled()){
			bigFish.recycle();
		}
		if(!bigFish1.isRecycled()){
			bigFish1.recycle();
		}
	}
}
