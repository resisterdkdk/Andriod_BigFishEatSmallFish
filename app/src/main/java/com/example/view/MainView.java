package com.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import com.example.constant.ConstantUtil;
import com.example.factory.GameObjectFactory;
import com.example.mybeatplane.R;
import com.example.object.BigPlane;
import com.example.object.BossPlane;
import com.example.object.EnemyPlane;
import com.example.object.GameObject;
import com.example.object.MiddlePlane;
import com.example.object.MyPlane;
import com.example.object.SmallPlane;
import com.example.object.stopGood;
import com.example.sounds.GameSoundPool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
/*游戏进行的主界面*/
public class MainView extends BaseView{
	Timer mTimer = new Timer();
	private int first_four;
	private int first_six;
	private int i = 0;
	private int TIME = 1000;
	private Boolean[] fitstUp = new Boolean[8];
	private int middlePlaneScore;	// 中型敌机的积分
	private int bigPlaneScore;		// 大型敌机的积分
	private int bossPlaneScore;		// boss型敌机的积分
	private int GScore;
	private int sumScore;			// 游戏总得分
	private int speedTime;			// 游戏速度的倍数
	private float bg_y;				// 图片的坐标
	private float bg_y2;
	private float play_bt_w;
	private float play_bt_h;	 
	private float missile_bt_y;		 	
	private boolean isPlay;			// 标记游戏运行状态
	private boolean isTouchPlane;	// 判断玩家是否按下屏幕
	private Bitmap background; 		// 背景图片
	private Bitmap playButton; 		// 开始/暂停游戏的按钮图片
	private Bitmap background1;				// 背景图片
	private Bitmap background2;				// 背景图片
	private Bitmap background3;				// 背景图片
	private Bitmap background4;				// 背景图片
	private Bitmap background5;				// 背景图片
	private Bitmap background6;				// 背景图片
	private Bitmap background7;				// 背景图片
	private Bitmap background8;				// 背景图片
	private Bitmap background9;				// 背景图片
	private Bitmap background10;				// 背景图片
	private Bitmap background11;				// 背景图片
	private Bitmap background12;				// 背景图片
	private Bitmap background13;				// 背景图片
	private Bitmap background14;				// 背景图片
	private Bitmap background15;				// 背景图片
	private Bitmap background16;				// 背景图片
	private Bitmap background17;				// 背景图片
	private Bitmap background18;				// 背景图片
	private Bitmap background19;				// 背景图片
	private stopGood StopGood;
	private Bitmap missile_bt;		// 导弹按钮图标
	private MyPlane myPlane;		// 玩家的飞机
	private BossPlane bossPlane;	// boss飞机
	private List<EnemyPlane> enemyPlanes;
	private GameObjectFactory factory;
	public MainView(Context context,GameSoundPool sounds) {
		super(context,sounds);
		// TODO Auto-generated constructor stub
		isPlay = true;
		speedTime = 2;
		fitstUp[0]=false;
		fitstUp[1]=false;
		fitstUp[2]=false;
		fitstUp[3]=false;
		fitstUp[4]=false;
		fitstUp[5]=false;
		fitstUp[6]=false;
		factory = new GameObjectFactory();						  //工厂类
		enemyPlanes = new ArrayList<EnemyPlane>();
		myPlane = (MyPlane) factory.createMyPlane(getResources());//生产玩家的飞机
		myPlane.setMainView(this);
		for(int i = 0;i < SmallPlane.sumCount;i++){
			//生产小型敌机，控制在一定的数量sumCount
			SmallPlane smallPlane = (SmallPlane) factory.createSmallPlane(getResources());
			enemyPlanes.add(smallPlane);
		}
		for(int i = 0;i < MiddlePlane.sumCount;i++){
			//生产中型敌机，控制在一定的数量sumCount
			MiddlePlane middlePlane = (MiddlePlane) factory.createMiddlePlane(getResources());
			enemyPlanes.add(middlePlane);
		}
		for(int i = 0;i < BigPlane.sumCount;i++){
			//生产大型敌机，控制在一定的数量sumCount
			BigPlane bigPlane = (BigPlane) factory.createBigPlane(getResources());
			enemyPlanes.add(bigPlane);
		}
		//生产BOSS敌机
		/*bossPlane = (BossPlane)factory.createBossPlane(getResources());
		bossPlane.setMyPlane(myPlane);
		enemyPlanes.add(bossPlane);*/
		StopGood = (stopGood) factory.createStopGood(getResources());
		thread = new Thread(this);	
	}


	// 视图改变的方法
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		super.surfaceChanged(arg0, arg1, arg2, arg3);
	}
	// 视图创建的方法
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		super.surfaceCreated(arg0);
		initBitmap(); // 初始化图片资源
		for(GameObject obj:enemyPlanes){			
			obj.setScreenWH(screen_width,screen_height);
		}
		StopGood.setScreenWH(screen_width, screen_height);
		myPlane.setScreenWH(screen_width,screen_height);
		myPlane.setAlive(true);
		if(thread.isAlive()){
			thread.start();
		}
		else{
			thread = new Thread(this);
			thread.start();
		}
	}
	// 视图销毁的方法
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		super.surfaceDestroyed(arg0);
		release();
	}
	// 响应触屏事件的方法
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_UP){
			isTouchPlane = false;
			return true;
		}
		else if(event.getAction() == MotionEvent.ACTION_DOWN){
			float x = event.getX();
			float y = event.getY();
			if(x > 10 && x < 10 + play_bt_w && y > 10 && y < 10 + play_bt_h){
				if(isPlay){
					isPlay = false;
				}		
				else{
					isPlay = true;	
					synchronized(thread){  //保证在同一时刻最多只有一个线程执行该段代码
						thread.notify();
					}
				}
				return true;
			}
			//判断玩家飞机是否被按下
			else if(x > myPlane.getObject_x() && x < myPlane.getObject_x() + myPlane.getObject_width() 
					&& y > myPlane.getObject_y() && y < myPlane.getObject_y() + myPlane.getObject_height()){
				if(isPlay){
					isTouchPlane = true;
				}
				return true;
			}

		}
		//响应手指在屏幕移动的事件
		else if(event.getAction() == MotionEvent.ACTION_MOVE && event.getPointerCount() == 1){
			//判断触摸点是否为玩家的飞机
			if(isTouchPlane){
				float x = event.getX();
				float y = event.getY();
				if(x > myPlane.getMiddle_x() + 20){
					if(myPlane.getMiddle_x() + myPlane.getSpeed() <= screen_width){
						myPlane.setMiddle_x(myPlane.getMiddle_x() + myPlane.getSpeed());
						myPlane.setDir(1);
					}					
				}
				else if(x < myPlane.getMiddle_x() - 20){
					if(myPlane.getMiddle_x() - myPlane.getSpeed() >= 0){
						myPlane.setMiddle_x(myPlane.getMiddle_x() - myPlane.getSpeed());
						myPlane.setDir(2);
					}				
				}
				if(y > myPlane.getMiddle_y() + 20){
					if(myPlane.getMiddle_y() + myPlane.getSpeed() <= screen_height){
						myPlane.setMiddle_y(myPlane.getMiddle_y() + myPlane.getSpeed());
					}		
				}
				else if(y < myPlane.getMiddle_y() - 20){
					if(myPlane.getMiddle_y() - myPlane.getSpeed() >= 0){
						myPlane.setMiddle_y(myPlane.getMiddle_y() - myPlane.getSpeed());
					}
				}
				return true;
			}	
		}
		return false;
	}
	// 初始化图片资源方法
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub

		mTimer.schedule(task,24000, 24000); //延时1000ms后执行，1000ms执行一次
		playButton = BitmapFactory.decodeResource(getResources(),R.drawable.play);
		background = BitmapFactory.decodeResource(getResources(),R.drawable.bg_1);
		background1 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_2);
		background2 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_3);
		background3 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_4);
		background4 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_5);
		background5 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_6);
		background6 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_7);
		background7 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_8);
		background8 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_9);
		background9 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_10);
		background10 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_11);
		background11 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_12);
		background12 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_13);
		background13 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_14);
		background14 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_15);
		background15 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_16);
		background16 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_17);
		background17 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_18);
		background18 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_19);
		background19 = BitmapFactory.decodeResource(getResources(),R.drawable.bg_20);
		scalex = screen_width / background.getWidth();   //缩放比
		scaley = screen_height / background.getHeight();
		play_bt_w = playButton.getWidth();
		play_bt_h = playButton.getHeight()/2;
		bg_y = 0;
		bg_y2 = bg_y - screen_height;
	}
	//初始化游戏对象
	public void initObject(){
		Random r = new Random();
		for(EnemyPlane obj:enemyPlanes){	
			//初始化小型敌机	
			if(obj instanceof SmallPlane){
				if(!obj.isAlive()){
					obj.initial(speedTime,0,0);
					obj.setDirection2(r.nextInt(2));
					break;
				}	
			}
			//初始化中型敌机
			else if(obj instanceof MiddlePlane){
				if(middlePlaneScore > 1000){
					if(!obj.isAlive()){
						obj.initial(speedTime,0,0);
						obj.setDirection2(r.nextInt(2));
						break;
					}	
				}
			}
			//初始化大型敌机
			else if(obj instanceof BigPlane){
				if(bigPlaneScore >= 2000){
					if(!obj.isAlive()){
						obj.initial(speedTime,0,0);
						obj.setDirection2(r.nextInt(2));
						break;
					}	
				}
			}
			//初始化BOSS敌机
			else{
				if(bossPlaneScore >= 3000){
					if(!obj.isAlive()){
						obj.initial(0,0,0);
						obj.setDirection2(r.nextInt(2));
						break;
					}
				}
			}

		}

		//提升速度
		if(sumScore >= speedTime*100000 && speedTime < 6){
			speedTime++;	
		}
		//提升power
        if(sumScore >=3000&&!fitstUp[0])
		{
			fitstUp[0] = true;
			myPlane.setPower(4);
			sounds.playSound(6,1);
		}
		else if(sumScore >=6000&&!fitstUp[1])
		{
			fitstUp[1] = true;
			myPlane.setPower(6);
			sounds.playSound(6,1);
		}
		else if(sumScore >=9000&&!fitstUp[2])
		{
			fitstUp[2] = true;
			myPlane.setPower(8);
			sounds.playSound(6,1);
		}
		else if(sumScore >=12000&&!fitstUp[3])
		{
			fitstUp[3] =true;
			myPlane.setPower(10);
			sounds.playSound(6,1);
			myPlane.setObject_x(screen_width/2 - myPlane.getObject_width()/2);
			myPlane.setObject_y(screen_height/2 - myPlane.getObject_height()/2);
		}
		else if(sumScore >=24000&& !fitstUp[4])
		{
			fitstUp[4] =true;
			myPlane.setPower(12);
			sounds.playSound(6,1);
			myPlane.setObject_x(screen_width/2 - myPlane.getObject_width()/2);
			myPlane.setObject_y(screen_height/2 - myPlane.getObject_height()/2);
		}
		else if(sumScore >=50000&&!fitstUp[5])
		{
			fitstUp[5]=true;
			myPlane.setPower(14);
			sounds.playSound(5,1);
			myPlane.setAlive(false);
		}
		//初始化子弹物品
		if(GScore >= 2000){
			if(!StopGood.isAlive()){
				StopGood.initial(0,0,0);
				GScore = 0;
			}
		}
	}
	// 释放图片资源的方法
	@Override
	public void release() {
		// TODO Auto-generated method stub
		for(GameObject obj:enemyPlanes){			
			obj.release();
		}
		StopGood.release();
		myPlane.release();
		if(!playButton.isRecycled()){
			playButton.recycle();
		}
		if(!background.isRecycled()){
			background.recycle();
		}
		mTimer.cancel(); //退出计时器
	}
	// 绘图方法
	@Override
	public void drawSelf() {
		// TODO Auto-generated method stub
		try {
			canvas = sfh.lockCanvas(); //锁定整个SurfaceView对象，获取该Surface上的Canvas
			canvas.drawColor(Color.BLACK); // 绘制背景色
			canvas.save();
			// 计算背景图片与屏幕的比例
			canvas.scale(scalex, scaley, 0, 0);
			if(currentFrame2 == 0)
				canvas.drawBitmap(background, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 1)
				canvas.drawBitmap(background1, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 2)
				canvas.drawBitmap(background2, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 3)
				canvas.drawBitmap(background3, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 4)
				canvas.drawBitmap(background4, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 5)
				canvas.drawBitmap(background5, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 6)
				canvas.drawBitmap(background6, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 7)
				canvas.drawBitmap(background7, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 8)
				canvas.drawBitmap(background8, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 9)
				canvas.drawBitmap(background9, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 10)
				canvas.drawBitmap(background10, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 11)
				canvas.drawBitmap(background11, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 12)
				canvas.drawBitmap(background12, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 13)
				canvas.drawBitmap(background13, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 14)
				canvas.drawBitmap(background14, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 15)
				canvas.drawBitmap(background15, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 16)
				canvas.drawBitmap(background16, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 17)
				canvas.drawBitmap(background17, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 18)
				canvas.drawBitmap(background18, 0, 0, paint); 		// 绘制背景图
			if(currentFrame2 == 19)
				canvas.drawBitmap(background19, 0, 0, paint); 		// 绘制背景图
			currentFrame2++;
			if(currentFrame2>=20)
				currentFrame2=0;
			canvas.restore();
			//绘制按钮，开始和暂停
			canvas.save();
			canvas.clipRect(10, 10, 10 + play_bt_w,10 + play_bt_h);//(int left, int top, int right, int bottom)选取要在画布上绘制（刷新）的区域，如图以(x, y)为起点坐标、宽w、高h的区域
			if(isPlay){
				canvas.drawBitmap(playButton, 10, 10, paint);			 
			}
			else{
				canvas.drawBitmap(playButton, 10, 10 - play_bt_h, paint);
			}
			canvas.restore();

//绘制子弹物品
			if(StopGood.isAlive()){
				if(StopGood.isCollide(myPlane)) {
                    StopGood.setAlive(false);
                    Random r = new Random();
                    int a = r.nextInt(2);
                    if(a==0)
                    {
                        for (EnemyPlane obj : enemyPlanes) {
                            if (obj.isAlive()&&obj.getObject_x()-obj.getObject_width()/3>0&&obj.getObject_y()-obj.getObject_height()/3>50) {
                                obj.setSpeed(0);
                            }
                            sounds.playSound(6, 0);
                        }
                    }
                    else
                    if(a==1){
                        for (EnemyPlane obj : enemyPlanes) {
                            if (obj.isAlive()) {
                                obj.setAlive(false);
                            }
                            sounds.playSound(6, 0);
                        }
                    }

                }
				else
                    StopGood.drawSelf(canvas);
			}
			//绘制敌机
			for(EnemyPlane obj:enemyPlanes){		
				if(obj.isAlive()){
					obj.drawSelf(canvas);					
					//检测敌机是否与玩家的飞机碰撞					
					if(obj.isCanCollide() && myPlane.isAlive()){		
						if(obj.isCollide(myPlane)){
							if(myPlane.getPower()<obj.getPower())
							{
								sounds.playSound(5,0);
								myPlane.setAlive(false);
							}
							else
							{
								addGameScore(obj.getScore());// 获得分数
								if(obj instanceof SmallPlane){
									playSound(2);
								}
								else if(obj instanceof MiddlePlane){
									playSound(3);
								}
								else if(obj instanceof BigPlane){
									playSound(4);
								}
								else{
									playSound(5);
								}
								obj.isExplosion =true;
							}
						}
					}
				}	
			}
			if(!myPlane.isAlive()){
				threadFlag = false;
				sounds.playSound(4, 0);			//飞机炸毁的音效
			}
			myPlane.drawSelf(canvas);	//绘制玩家的飞机
			//绘制积分文字
			paint.setTextSize(30);
			paint.setColor(Color.rgb(235, 161, 1));
			canvas.drawText("积分:"+String.valueOf(sumScore), 30 + play_bt_w, 40, paint);		//绘制文字
			canvas.drawText("等级 X "+(myPlane.getPower()/2), screen_width - 150, 40, paint); //绘制文字
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas);      //释放canvas锁，并且显示视图
		}



	}
	TimerTask task = new TimerTask(){
		public void run() {
			sounds.playSound(1, 1);	  //子弹音效
		}
	};
	// 背景移动的逻辑函数
	public void viewLogic(){

	}
	// 增加游戏分数的方法 
	public void addGameScore(int score){
		middlePlaneScore += score;	// 中型敌机的积分
		bigPlaneScore += score;		// 大型敌机的积分
		bossPlaneScore += score;	// boss型敌机的积分
		GScore +=score;
		sumScore += score;			// 游戏总得分
	}
	// 播放音效
	public void playSound(int key){
		sounds.playSound(key, 0);
	}
	// 线程运行的方法
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (threadFlag) {	
			long startTime = System.currentTimeMillis();
			initObject();
			drawSelf();	
			viewLogic();		//背景移动的逻辑	
			long endTime = System.currentTimeMillis();	
			if(!isPlay){
				synchronized (thread) {  
				    try {  
				    	thread.wait();  
				    } catch (InterruptedException e) {  
				        e.printStackTrace();  
				    }  
				}  		
			}	
			try {
				if (endTime - startTime < 100)
					Thread.sleep(100 - (endTime - startTime));
			} catch (InterruptedException err) {
				err.printStackTrace();
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message message = new Message();   
		message.what = 	ConstantUtil.TO_END_VIEW;
		message.arg1 = Integer.valueOf(sumScore);
		mainActivity.getHandler().sendMessage(message);
	}
}
