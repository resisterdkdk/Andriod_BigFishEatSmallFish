package com.example.object;

import java.util.ArrayList;
import java.util.List;
import com.example.constant.ConstantUtil;
import com.example.factory.GameObjectFactory;
import com.example.mybeatplane.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
/*BOSS�л�����*/
public class BossPlane extends EnemyPlane{
	private static int currentCount = 0;	 //	����ǰ������
	private static int sumCount = 1;
	private Bitmap boosPlane;
	private Bitmap boosPlaneBomb;
	private int direction;			//�ƶ��ķ���
	private float leftBorder;		//�ɻ����ƶ�����߽�
	private float rightBorder;		//�ɻ����ƶ����ұ߽�
	private MyPlane myplane;
	public BossPlane(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		this.power = 10;
		this.score = 10000;
		//������
		GameObjectFactory factory = new GameObjectFactory();
	}
	public void setMyPlane(MyPlane myplane){
		this.myplane = myplane;
	}
	//��ʼ������
	@Override
	public void setScreenWH(float screen_width,float screen_height){
		super.setScreenWH(screen_width, screen_height);
		leftBorder = -object_width/2;
		rightBorder = screen_width - object_width/2;
	}
	//��ʼ������
	@Override
	public void initial(int arg0,float arg1,float arg2){
		isAlive = true;	
		isVisible = true;
		speed = 25;
		bloodVolume = 200;
		blood = bloodVolume;
		direction = ConstantUtil.DIR_TOP;
		object_x = screen_width/2 - object_width/2;
		object_y = -object_height * (arg0*2 + 1);
		currentCount++;
		if(currentCount >= sumCount){
			currentCount = 0;
		}
	}
	//��ʼ��ͼƬ
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		boosPlane = BitmapFactory.decodeResource(resources, R.drawable.boss_1);
		boosPlaneBomb = BitmapFactory.decodeResource(resources, R.drawable.bossplanebomb);
		object_width = boosPlane.getWidth();		//���ÿһ֡λͼ�Ŀ�
		object_height = boosPlane.getHeight();		//���ÿһ֡λͼ�ĸ�
	}
	//��ͼ����
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		if(isAlive){
			if(!isExplosion){
				int y = (int) (currentFrame * object_height); // ��õ�ǰ֡�����λͼ��Y����
				canvas.save();
				canvas.clipRect(object_x,object_y,object_x + object_width,object_y + object_height);
				canvas.drawBitmap(boosPlane, object_x, object_y ,paint);
				canvas.restore();
				logic();
			}
			else{
					isExplosion = false;
					isAlive = false;
				}
		}	
	}
	//�ͷ���Դ
	@Override
	public void release() {
		// TODO Auto-generated method stub
		if(!boosPlane.isRecycled()){
			boosPlane.recycle();
		}
		if(!boosPlaneBomb.isRecycled()){
			boosPlaneBomb.recycle();
		}
	}
	// �����ײ
	@Override
	public boolean isCollide(GameObject obj) {
		return super.isCollide(obj);
	}
	//������߼�����
	@Override
	public void logic(){
		if (object_y < screen_height) {
			object_y += speed;
		}
		else {
			isAlive = false;
		}
		if(object_y + object_height > 0){
			isVisible = true;
		}
		else{
			isVisible = false;
		}
        /*
			if(object_x > leftBorder && direction == ConstantUtil.DIR_LEFT){
				object_x -= speed;
				if(object_x <= leftBorder){
					direction = ConstantUtil.DIR_RIGHT;
				}
			}
			if(object_x < rightBorder && direction == ConstantUtil.DIR_RIGHT){
				object_x += speed;
				if(object_x >= rightBorder){
					direction = ConstantUtil.DIR_LEFT;
				}
			}*/
	}

}
