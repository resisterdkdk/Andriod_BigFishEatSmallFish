package com.example.object;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.constant.ConstantUtil;
import com.example.sounds.GameSoundPool;

/*敌机类*/
public class EnemyPlane extends GameObject{
	protected int score;						 // 对象的分值
	protected int blood; 						 // 对象的当前血量
	protected int bloodVolume; 					 // 对象总的血量

	private int direction1;			//移动的方向
	private int direction2;			//移动的方向
	private float TopBorder;		//飞机能移动的左边界
	private float ButtomBorder;		//飞机能移动的右边界
	public boolean isExplosion;   			 // 判断是否爆炸
	protected boolean isVisible;		 		 //	 对象是否为可见状态
	public EnemyPlane(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		initBitmap();			// 初始化图片资源
	}
	//初始化数据
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
	// 初始化图片资源
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
	
	}
	// 对象的绘图函数
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		//判断敌机是否死亡状态
		
	}
	// 释放资源
	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}
	// 对象的逻辑函数
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
	// 检测碰撞
	@Override
	public boolean isCollide(GameObject obj) {
		// 矩形1位于矩形2的左侧
		if (object_x -object_width/5<= obj.getObject_x()
				&& object_x + object_width-object_width/5<= obj.getObject_x()) {
			return false;
		}
		// 矩形1位于矩形2的右侧
		else if (obj.getObject_x() <= object_x +object_width/5
				&& obj.getObject_x() + obj.getObject_width() <= object_x+object_width/5) {
			return false;
		}
		// 矩形1位于矩形2的上方
		else if (object_y -object_height/5<= obj.getObject_y()
				&& object_y + object_height-object_height/5 <= obj.getObject_y()) {
			return false;
		}
		// 矩形1位于矩形2的下方
		else if (obj.getObject_y() <= object_y+object_height/5
				&& obj.getObject_y() + obj.getObject_height() <= object_y+object_height/5) {
			return false;
		}


		return true;
	}
	// 判断能否被检测碰撞
	public boolean isCanCollide() {
		// TODO Auto-generated method stub
		return isAlive && !isExplosion && isVisible;
	}
	//getter和setter方法
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

