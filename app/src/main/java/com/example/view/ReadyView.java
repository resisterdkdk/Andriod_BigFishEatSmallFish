package com.example.view;

import com.example.constant.ConstantUtil;
import com.example.mybeatplane.R;
import com.example.sounds.GameSoundPool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.Timer;
import java.util.TimerTask;

/*��Ϸ��ʼǰ�Ľ�����*/
public class ReadyView extends BaseView{
	private float fly_x;					// ͼƬ������
	Timer mTimer = new Timer();
	private float fly_y;
	private float bg_x;					// ����������
	private float bg_y;
	private float bg_wight;					// ����ͼƬ�Ĵ�С
	private float bg_hight;
	private float fly_height;
	private float text_x;
	private float text_y;
	private float button_x;
	private float button_y;
	private float button_y2;
	private float strwid;
	private float strhei;
	private boolean isBtChange;				// ��ťͼƬ�ı�ı��
	private boolean isBtChange2;
	private String startGame = "START";	// ��ť������
	private String exitGame = "EXIT";
	private Bitmap text;					// ����ͼƬ
	private Bitmap button;					// ��ťͼƬ
	private Bitmap button2;					// ��ťͼƬ
	private Bitmap planefly;				// �ɻ����е�ͼƬ
	private Bitmap background;				// ����ͼƬ
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
	private Bitmap background20;				// ����ͼƬ
	private Rect rect;						// �������ֵ�����
	public ReadyView(Context context,GameSoundPool sounds) {
		super(context,sounds);
		// TODO Auto-generated constructor stub
		paint.setTextSize(40);
		rect = new Rect();
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
		initBitmap(); 
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
		if (event.getAction() == MotionEvent.ACTION_DOWN
				&& event.getPointerCount() == 1) {
			float x = event.getX();
			float y = event.getY();
			//�жϵ�һ����ť�Ƿ񱻰���
			if (x > button_x && x < button_x + button.getWidth()
					&& y > button_y && y < button_y + button.getHeight()) {
				sounds.playSound(7, 0);
				isBtChange = true;
				drawSelf();
				mainActivity.getHandler().sendEmptyMessage(ConstantUtil.TO_MAIN_VIEW);
			}
			//�жϵڶ�����ť�Ƿ񱻰���
			else if (x > button_x && x < button_x + button.getWidth()
					&& y > button_y2 && y < button_y2 + button.getHeight()) {
				sounds.playSound(7, 0);
				isBtChange2 = true;
				drawSelf();
				mTimer.cancel(); //�˳���ʱ��
				mainActivity.getHandler().sendEmptyMessage(ConstantUtil.END_GAME);
			}
			return true;
		} 
		//��Ӧ��Ļ�����ƶ�����Ϣ
		else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			float x = event.getX();
			float y = event.getY();
			if (x > button_x && x < button_x + button.getWidth()
					&& y > button_y && y < button_y + button.getHeight()) {
				isBtChange = true;
			} else {
				isBtChange = false;
			}
			if (x > button_x && x < button_x + button.getWidth()
					&& y > button_y2 && y < button_y2 + button.getHeight()) {
				isBtChange2 = true;
			} else {
				isBtChange2 = false;
			}
			return true;
		} 
		//��Ӧ��ָ�뿪��Ļ����Ϣ
		else if (event.getAction() == MotionEvent.ACTION_UP) {
			isBtChange = false;
			isBtChange2 = false;
			return true;
		}
		return false;
	}
	// ��ʼ��ͼƬ��Դ����
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub


		mTimer.schedule(task,1000, 24000); //��ʱ1000ms��ִ�У�1000msִ��һ��

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

		text = BitmapFactory.decodeResource(getResources(), R.drawable.text);
		planefly = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
		button = BitmapFactory.decodeResource(getResources(), R.drawable.bt_start);
		button2 = BitmapFactory.decodeResource(getResources(),R.drawable.bt_exit);
		bg_x = 0;
		bg_y = 0;
		bg_wight = background.getWidth();
		bg_hight = background.getHeight()/4;
		scalex = screen_width / background.getWidth();
		scaley = screen_height / background.getHeight();
		text_x = screen_width / 2 - text.getWidth() / 2;
		text_y = screen_height / 2 - text.getHeight();
		fly_x = screen_width / 2 - planefly.getWidth() / 2;
		fly_height = planefly.getHeight() / 4;
		fly_y = text_y - fly_height ;
		button_x = screen_width / 2 - button.getWidth() / 2;
		button_y = screen_height / 2 + button.getHeight();
		button_y2 = button_y + button.getHeight() + 40;
		// ���ذ�Χ�����ַ�������С��һ��Rect����
		paint.getTextBounds(startGame, 0, startGame.length(), rect);
		strwid = rect.width();
		strhei = rect.height();
	}
	// �ͷ�ͼƬ��Դ�ķ���
	@Override
	public void release() {
		// TODO Auto-generated method stub
		if (!text.isRecycled()) {
			text.recycle();
		}
		if (!button.isRecycled()) {
			button.recycle();
		}
		if (!button2.isRecycled()) {
			button2.recycle();
		}	
		if (!planefly.isRecycled()) {
			planefly.recycle();
		}
		if (!background.isRecycled()) {
			background.recycle();
		}
		mTimer.cancel(); //�˳���ʱ��
	}
	// ��ͼ����
	@Override
	public void drawSelf() {
		// TODO Auto-generated method stub
		try {
			canvas = sfh.lockCanvas();
			canvas.drawColor(Color.BLACK); 						// ���Ʊ���ɫ
			canvas.save();
			canvas.scale(scalex, scaley, 0, 0);					// ���㱳��ͼƬ����Ļ�ı���
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
			canvas.drawBitmap(text, text_x, text_y, paint);		// ��������ͼƬ
			//����ָ������ťʱ�任ͼƬ
			canvas.drawBitmap(button, button_x, button_y, paint);
			canvas.drawBitmap(button2, button_x, button_y2, paint);

			//�ɻ����еĶ���
			canvas.save();
			canvas.clipRect(fly_x, fly_y-20, fly_x + planefly.getWidth(), fly_y + fly_height+20);
			canvas.drawBitmap(planefly, fly_x, fly_y - currentFrame * fly_height+20,paint);
			currentFrame++;
			if (currentFrame >= 4) {
				currentFrame = 0;
			}
			canvas.restore();
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}
	TimerTask task = new TimerTask(){
		public void run() {
			sounds.playSound(1, 1);	  //�ӵ���Ч
		}
	};
	// �߳����еķ���
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (threadFlag) {
			long startTime = System.currentTimeMillis();
			drawSelf();
			long endTime = System.currentTimeMillis();
			try {
				if (endTime - startTime < 400)
					Thread.sleep(400 - (endTime - startTime));
			} catch (InterruptedException err) {
				err.printStackTrace();
			}
		}
	}
}
