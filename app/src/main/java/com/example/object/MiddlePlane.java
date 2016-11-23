package com.example.object;

import java.util.Random;
import com.example.mybeatplane.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
/*���͵л�����*/
public class MiddlePlane extends EnemyPlane{
	private static int currentCount = 0;	 //	����ǰ������
	private Bitmap middlefish;// ����ͼƬ
	private Bitmap middlefish1;// ����ͼƬ
	public static int sumCount = 4;	 	 	 //	�����ܵ�����
	public MiddlePlane(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		this.power = 3;
		this.score = 1000;		// Ϊ�������÷���
	}
	//��ʼ������
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
	// ��ʼ��ͼƬ��Դ
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		middlefish = BitmapFactory.decodeResource(resources, R.drawable.middle_1);
		middlefish1 = BitmapFactory.decodeResource(resources, R.drawable.middle_2);
		object_width = middlefish.getWidth();			//���ÿһ֡λͼ�Ŀ�
		object_height = middlefish.getHeight();		//���ÿһ֡λͼ�ĸ�
	}
	// ����Ļ�ͼ����
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		if(isAlive){
			if(!isExplosion){
				if(isVisible){
					canvas.save();
					canvas.clipRect(object_x,object_y,object_x + object_width,object_y + object_height);//�涨4����
					if(this.getDirection2()==0)
						canvas.drawBitmap(middlefish, object_x, object_y, paint);
					if(this.getDirection2()==1)
						canvas.drawBitmap(middlefish1, object_x, object_y, paint);
					//canvas.clipRect(x1, y1, x1 + w, y1 + h);
					//canvas.drawBitmap(bitmap, x2, y2, paint);
					//2��clipRect()��ȡ�����е�һ������
					//3��drawBitmap()����ͼƬ����x2, y2)�ϣ�����ɫ���ָպû��Ƶ�(x1, y1)�ϣ���û�б�clip�������򲻻��ͼ
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
	// �ͷ���Դ
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
