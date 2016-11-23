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
/*��Ϸ���е�������*/
public class MainView extends BaseView{
	Timer mTimer = new Timer();
	private int first_four;
	private int first_six;
	private int i = 0;
	private int TIME = 1000;
	private Boolean[] fitstUp = new Boolean[8];
	private int middlePlaneScore;	// ���͵л��Ļ���
	private int bigPlaneScore;		// ���͵л��Ļ���
	private int bossPlaneScore;		// boss�͵л��Ļ���
	private int GScore;
	private int sumScore;			// ��Ϸ�ܵ÷�
	private int speedTime;			// ��Ϸ�ٶȵı���
	private float bg_y;				// ͼƬ������
	private float bg_y2;
	private float play_bt_w;
	private float play_bt_h;	 
	private float missile_bt_y;		 	
	private boolean isPlay;			// �����Ϸ����״̬
	private boolean isTouchPlane;	// �ж�����Ƿ�����Ļ
	private Bitmap background; 		// ����ͼƬ
	private Bitmap playButton; 		// ��ʼ/��ͣ��Ϸ�İ�ťͼƬ
	private Bitmap background1;				// ����ͼƬ
	private Bitmap background2;				// ����ͼƬ
	private Bitmap background3;				// ����ͼƬ
	private Bitmap background4;				// ����ͼƬ
	private Bitmap background5;				// ����ͼƬ
	private Bitmap background6;				// ����ͼƬ
	private Bitmap background7;				// ����ͼƬ
	private Bitmap background8;				// ����ͼƬ
	private Bitmap background9;				// ����ͼƬ
	private Bitmap background10;				// ����ͼƬ
	private Bitmap background11;				// ����ͼƬ
	private Bitmap background12;				// ����ͼƬ
	private Bitmap background13;				// ����ͼƬ
	private Bitmap background14;				// ����ͼƬ
	private Bitmap background15;				// ����ͼƬ
	private Bitmap background16;				// ����ͼƬ
	private Bitmap background17;				// ����ͼƬ
	private Bitmap background18;				// ����ͼƬ
	private Bitmap background19;				// ����ͼƬ
	private stopGood StopGood;
	private Bitmap missile_bt;		// ������ťͼ��
	private MyPlane myPlane;		// ��ҵķɻ�
	private BossPlane bossPlane;	// boss�ɻ�
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
		factory = new GameObjectFactory();						  //������
		enemyPlanes = new ArrayList<EnemyPlane>();
		myPlane = (MyPlane) factory.createMyPlane(getResources());//������ҵķɻ�
		myPlane.setMainView(this);
		for(int i = 0;i < SmallPlane.sumCount;i++){
			//����С�͵л���������һ��������sumCount
			SmallPlane smallPlane = (SmallPlane) factory.createSmallPlane(getResources());
			enemyPlanes.add(smallPlane);
		}
		for(int i = 0;i < MiddlePlane.sumCount;i++){
			//�������͵л���������һ��������sumCount
			MiddlePlane middlePlane = (MiddlePlane) factory.createMiddlePlane(getResources());
			enemyPlanes.add(middlePlane);
		}
		for(int i = 0;i < BigPlane.sumCount;i++){
			//�������͵л���������һ��������sumCount
			BigPlane bigPlane = (BigPlane) factory.createBigPlane(getResources());
			enemyPlanes.add(bigPlane);
		}
		//����BOSS�л�
		/*bossPlane = (BossPlane)factory.createBossPlane(getResources());
		bossPlane.setMyPlane(myPlane);
		enemyPlanes.add(bossPlane);*/
		StopGood = (stopGood) factory.createStopGood(getResources());
		thread = new Thread(this);	
	}


	// ��ͼ�ı�ķ���
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		super.surfaceChanged(arg0, arg1, arg2, arg3);
	}
	// ��ͼ�����ķ���
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		super.surfaceCreated(arg0);
		initBitmap(); // ��ʼ��ͼƬ��Դ
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
	// ��ͼ���ٵķ���
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		super.surfaceDestroyed(arg0);
		release();
	}
	// ��Ӧ�����¼��ķ���
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
					synchronized(thread){  //��֤��ͬһʱ�����ֻ��һ���߳�ִ�иöδ���
						thread.notify();
					}
				}
				return true;
			}
			//�ж���ҷɻ��Ƿ񱻰���
			else if(x > myPlane.getObject_x() && x < myPlane.getObject_x() + myPlane.getObject_width() 
					&& y > myPlane.getObject_y() && y < myPlane.getObject_y() + myPlane.getObject_height()){
				if(isPlay){
					isTouchPlane = true;
				}
				return true;
			}

		}
		//��Ӧ��ָ����Ļ�ƶ����¼�
		else if(event.getAction() == MotionEvent.ACTION_MOVE && event.getPointerCount() == 1){
			//�жϴ������Ƿ�Ϊ��ҵķɻ�
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
	// ��ʼ��ͼƬ��Դ����
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub

		mTimer.schedule(task,24000, 24000); //��ʱ1000ms��ִ�У�1000msִ��һ��
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
		scalex = screen_width / background.getWidth();   //���ű�
		scaley = screen_height / background.getHeight();
		play_bt_w = playButton.getWidth();
		play_bt_h = playButton.getHeight()/2;
		bg_y = 0;
		bg_y2 = bg_y - screen_height;
	}
	//��ʼ����Ϸ����
	public void initObject(){
		Random r = new Random();
		for(EnemyPlane obj:enemyPlanes){	
			//��ʼ��С�͵л�	
			if(obj instanceof SmallPlane){
				if(!obj.isAlive()){
					obj.initial(speedTime,0,0);
					obj.setDirection2(r.nextInt(2));
					break;
				}	
			}
			//��ʼ�����͵л�
			else if(obj instanceof MiddlePlane){
				if(middlePlaneScore > 1000){
					if(!obj.isAlive()){
						obj.initial(speedTime,0,0);
						obj.setDirection2(r.nextInt(2));
						break;
					}	
				}
			}
			//��ʼ�����͵л�
			else if(obj instanceof BigPlane){
				if(bigPlaneScore >= 2000){
					if(!obj.isAlive()){
						obj.initial(speedTime,0,0);
						obj.setDirection2(r.nextInt(2));
						break;
					}	
				}
			}
			//��ʼ��BOSS�л�
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

		//�����ٶ�
		if(sumScore >= speedTime*100000 && speedTime < 6){
			speedTime++;	
		}
		//����power
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
		//��ʼ���ӵ���Ʒ
		if(GScore >= 2000){
			if(!StopGood.isAlive()){
				StopGood.initial(0,0,0);
				GScore = 0;
			}
		}
	}
	// �ͷ�ͼƬ��Դ�ķ���
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
		mTimer.cancel(); //�˳���ʱ��
	}
	// ��ͼ����
	@Override
	public void drawSelf() {
		// TODO Auto-generated method stub
		try {
			canvas = sfh.lockCanvas(); //��������SurfaceView���󣬻�ȡ��Surface�ϵ�Canvas
			canvas.drawColor(Color.BLACK); // ���Ʊ���ɫ
			canvas.save();
			// ���㱳��ͼƬ����Ļ�ı���
			canvas.scale(scalex, scaley, 0, 0);
			if(currentFrame2 == 0)
				canvas.drawBitmap(background, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 1)
				canvas.drawBitmap(background1, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 2)
				canvas.drawBitmap(background2, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 3)
				canvas.drawBitmap(background3, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 4)
				canvas.drawBitmap(background4, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 5)
				canvas.drawBitmap(background5, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 6)
				canvas.drawBitmap(background6, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 7)
				canvas.drawBitmap(background7, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 8)
				canvas.drawBitmap(background8, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 9)
				canvas.drawBitmap(background9, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 10)
				canvas.drawBitmap(background10, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 11)
				canvas.drawBitmap(background11, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 12)
				canvas.drawBitmap(background12, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 13)
				canvas.drawBitmap(background13, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 14)
				canvas.drawBitmap(background14, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 15)
				canvas.drawBitmap(background15, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 16)
				canvas.drawBitmap(background16, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 17)
				canvas.drawBitmap(background17, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 18)
				canvas.drawBitmap(background18, 0, 0, paint); 		// ���Ʊ���ͼ
			if(currentFrame2 == 19)
				canvas.drawBitmap(background19, 0, 0, paint); 		// ���Ʊ���ͼ
			currentFrame2++;
			if(currentFrame2>=20)
				currentFrame2=0;
			canvas.restore();
			//���ư�ť����ʼ����ͣ
			canvas.save();
			canvas.clipRect(10, 10, 10 + play_bt_w,10 + play_bt_h);//(int left, int top, int right, int bottom)ѡȡҪ�ڻ����ϻ��ƣ�ˢ�£���������ͼ��(x, y)Ϊ������ꡢ��w����h������
			if(isPlay){
				canvas.drawBitmap(playButton, 10, 10, paint);			 
			}
			else{
				canvas.drawBitmap(playButton, 10, 10 - play_bt_h, paint);
			}
			canvas.restore();

//�����ӵ���Ʒ
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
			//���Ƶл�
			for(EnemyPlane obj:enemyPlanes){		
				if(obj.isAlive()){
					obj.drawSelf(canvas);					
					//���л��Ƿ�����ҵķɻ���ײ					
					if(obj.isCanCollide() && myPlane.isAlive()){		
						if(obj.isCollide(myPlane)){
							if(myPlane.getPower()<obj.getPower())
							{
								sounds.playSound(5,0);
								myPlane.setAlive(false);
							}
							else
							{
								addGameScore(obj.getScore());// ��÷���
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
				sounds.playSound(4, 0);			//�ɻ�ը�ٵ���Ч
			}
			myPlane.drawSelf(canvas);	//������ҵķɻ�
			//���ƻ�������
			paint.setTextSize(30);
			paint.setColor(Color.rgb(235, 161, 1));
			canvas.drawText("����:"+String.valueOf(sumScore), 30 + play_bt_w, 40, paint);		//��������
			canvas.drawText("�ȼ� X "+(myPlane.getPower()/2), screen_width - 150, 40, paint); //��������
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas);      //�ͷ�canvas����������ʾ��ͼ
		}



	}
	TimerTask task = new TimerTask(){
		public void run() {
			sounds.playSound(1, 1);	  //�ӵ���Ч
		}
	};
	// �����ƶ����߼�����
	public void viewLogic(){

	}
	// ������Ϸ�����ķ��� 
	public void addGameScore(int score){
		middlePlaneScore += score;	// ���͵л��Ļ���
		bigPlaneScore += score;		// ���͵л��Ļ���
		bossPlaneScore += score;	// boss�͵л��Ļ���
		GScore +=score;
		sumScore += score;			// ��Ϸ�ܵ÷�
	}
	// ������Ч
	public void playSound(int key){
		sounds.playSound(key, 0);
	}
	// �߳����еķ���
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (threadFlag) {	
			long startTime = System.currentTimeMillis();
			initObject();
			drawSelf();	
			viewLogic();		//�����ƶ����߼�	
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
