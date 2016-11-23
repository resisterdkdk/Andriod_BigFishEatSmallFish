package com.example.object;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.constant.ConstantUtil;
import com.example.sounds.GameSoundPool;

/*�л���*/
public class EnemyPlane extends GameObject{
	protected int score;						 // ����ķ�ֵ
	protected int blood; 						 // ����ĵ�ǰѪ��
	protected int bloodVolume; 					 // �����ܵ�Ѫ��

	private int direction1;			//�ƶ��ķ���
	private int direction2;			//�ƶ��ķ���
	private float TopBorder;		//�ɻ����ƶ�����߽�
	private float ButtomBorder;		//�ɻ����ƶ����ұ߽�
	public boolean isExplosion;   			 // �ж��Ƿ�ը
	protected boolean isVisible;		 		 //	 �����Ƿ�Ϊ�ɼ�״̬
	public EnemyPlane(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		initBitmap();			// ��ʼ��ͼƬ��Դ
	}
	//��ʼ������
	@Override
	public void initial(int arg0,float arg1,float arg2){
		direction1 = ConstantUtil.DIR_TOP;
	}
	@Override
	public void setScreenWH(float screen_width,float screen_height){
		super.setScreenWH(screen_width, screen_height);
		TopBorder = -object_height/2;
		ButtomBorder = screen_height - object_height/2;
	}
	// ��ʼ��ͼƬ��Դ
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
	
	}
	// ����Ļ�ͼ����
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		//�жϵл��Ƿ�����״̬
		
	}
	// �ͷ���Դ
	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}
	// ������߼�����
	@Override
	public void logic() {
		// TODO Auto-generated method stub
		if(direction2 == 0)
		{
			if (object_x + screen_width>0) {
				object_x -= speed;
				isVisible = true;
			}
			else {
				isAlive = false;
				isVisible = false;
			}
		}
		if(direction2 == 1)
		{
			if (object_x -object_width < screen_width) {
				object_x += speed;
				isVisible = true;
			}
			else {
				isAlive = false;
				isVisible = false;
			}
		}
		/*
		if(object_y > TopBorder && direction == ConstantUtil.DIR_TOP){
			object_y -= speed;
			if(object_y <= TopBorder){
				direction = ConstantUtil.DIR_BUTTOM;
			}
		}
		if(object_y < ButtomBorder && direction == ConstantUtil.DIR_BUTTOM){
			object_y += speed;
			if(object_y >= ButtomBorder){
				direction = ConstantUtil.DIR_TOP;
			}
		}*/
	}
	// �����ײ
	@Override
	public boolean isCollide(GameObject obj) {
		// ����1λ�ھ���2�����
		if (object_x -object_width/5<= obj.getObject_x()
				&& object_x + object_width-object_width/5<= obj.getObject_x()) {
			return false;
		}
		// ����1λ�ھ���2���Ҳ�
		else if (obj.getObject_x() <= object_x +object_width/5
				&& obj.getObject_x() + obj.getObject_width() <= object_x+object_width/5) {
			return false;
		}
		// ����1λ�ھ���2���Ϸ�
		else if (object_y -object_height/5<= obj.getObject_y()
				&& object_y + object_height-object_height/5 <= obj.getObject_y()) {
			return false;
		}
		// ����1λ�ھ���2���·�
		else if (obj.getObject_y() <= object_y+object_height/5
				&& obj.getObject_y() + obj.getObject_height() <= object_y+object_height/5) {
			return false;
		}


		return true;
	}
	// �ж��ܷ񱻼����ײ
	public boolean isCanCollide() {
		// TODO Auto-generated method stub
		return isAlive && !isExplosion && isVisible;
	}
	//getter��setter����
	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}
	public void setScore(int score) {
		// TODO Auto-generated method stub
		this.score = score;
	}
	public int getBlood() {
		// TODO Auto-generated method stub
		return blood;
	}
	public void setBlood(int blood) {
		// TODO Auto-generated method stub
		this.blood = blood;
	}
	public int getBloodVolume() {
		// TODO Auto-generated method stub
		return bloodVolume;
	}
	public void setBloodVolume(int bloodVolume) {
		// TODO Auto-generated method stub
		this.bloodVolume = bloodVolume;
	}
	public boolean isExplosion() {
		// TODO Auto-generated method stub
		return isExplosion;
	}

	public int getDirection2() {
		return direction2;
	}

	public void setDirection2(int direction2) {
		this.direction2 = direction2;
	}
}

