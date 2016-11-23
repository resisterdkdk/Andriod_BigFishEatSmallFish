package com.example.object;

import java.util.Random;

import com.example.constant.ConstantUtil;
import com.example.mybeatplane.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
/*С�͵л�����*/
public class SmallPlane extends EnemyPlane{
	private static int currentCount = 0;	 //	����ǰ������
	private Bitmap smallFish; // ����ͼƬ
	private Bitmap smallFish1; // ����ͼƬ
	public static int sumCount = 8;	 	 	 //	�����ܵ�����
	public SmallPlane(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		this.power = 1;
		this.score = 100;		// Ϊ�������÷���
	}
	//��ʼ������
	@Override
	public void initial(int arg0,float arg1,float arg2){
		super.initial(arg0,arg1,arg2);
		isAlive = true;
		bloodVolume = 1;
		blood = bloodVolume;
		Random ran = new Random();
		speed = ran.nextInt(8) + 8 * arg0;
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
	// ��ʼ��ͼƬ��Դ
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		smallFish = BitmapFactory.decodeResource(resources, R.drawable.small_1);
		smallFish1 = BitmapFactory.decodeResource(resources, R.drawable.small_2);
		object_width = smallFish.getWidth();			//���ÿһ֡λͼ�Ŀ�
		object_height = smallFish.getHeight();		//���ÿһ֡λͼ�ĸ�
	}
	// ����Ļ�ͼ����
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		//�жϵл��Ƿ�����״̬
		if (isAlive) {
			//�жϵл��Ƿ�Ϊ��ը״̬
			if (!isExplosion) {
				if (isVisible) {
					canvas.save();
					canvas.clipRect(object_x, object_y, object_x + object_width, object_y + object_height);
					if(this.getDirection2()==0)
					canvas.drawBitmap(smallFish, object_x, object_y, paint);
					if(this.getDirection2()==1)
						canvas.drawBitmap(smallFish1, object_x, object_y, paint);
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
		if(!smallFish.isRecycled()){
			smallFish.recycle();
		}
		if(!smallFish1.isRecycled()){
			smallFish1.recycle();
		}
	}
}
