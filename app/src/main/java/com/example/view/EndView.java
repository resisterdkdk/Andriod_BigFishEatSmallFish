package com.example.view;
import com.example.constant.ConstantUtil;
import com.example.mybeatplane.MainActivity;
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

/*��Ϸ����ʱ��ʾ�Ľ���*/
public class EndView extends BaseView{
    Timer mTimer = new Timer();
	private int score;
	private float button_x;
	private float button_y;
	private float button_y2;
	private float strwid;
	private float strhei;
	private boolean isBtChange;				// ��ťͼƬ�ı�ı��
	private boolean isBtChange2;
	private String startGame = "RESTART";	// ��ť������
	private String exitGame = "EXIT";
	private Bitmap button;					// ��ťͼƬ
	private Bitmap button2;					// ��ťͼƬ
	private Bitmap background;				// ����ͼƬ
	private Rect rect;						// �������ֵ�����
	private MainActivity mainActivity;
	public EndView(Context context,GameSoundPool sounds) {
		super(context,sounds);
		// TODO Auto-generated constructor stub
		this.mainActivity = (MainActivity)context;
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
		if(event.getAction() == MotionEvent.ACTION_DOWN && event.getPointerCount() == 1){
			float x = event.getX();
			float y = event.getY();
			//�жϵ�һ����ť�Ƿ񱻰���
			if(x > button_x && x < button_x + button.getWidth() 
					&& y > button_y && y < button_y + button.getHeight())
			{
				sounds.playSound(7, 0);
				isBtChange = true;
				drawSelf();
				mainActivity.getHandler().sendEmptyMessage(ConstantUtil.TO_MAIN_VIEW);
			}
			//�жϵڶ�����ť�Ƿ񱻰���
			else if(x > button_x && x < button_x + button.getWidth() 
					&& y > button_y2 && y < button_y2 + button.getHeight())
			{
				sounds.playSound(7, 0);
				isBtChange2 = true;
				drawSelf();
				threadFlag = false;
				mainActivity.getHandler().sendEmptyMessage(ConstantUtil.END_GAME);
			}
			return true;
		}
		else if(event.getAction() == MotionEvent.ACTION_MOVE){
			float x = event.getX();
			float y = event.getY();
			if(x > button_x && x < button_x + button.getWidth() 
					&& y > button_y && y < button_y + button.getHeight())
			{
				isBtChange = true;
			}
			else{
				isBtChange = false;
			}
			if(x > button_x && x < button_x + button.getWidth() 
					&& y > button_y2 && y < button_y2 + button.getHeight())
			{
				isBtChange2 = true;
			}
			else{
				isBtChange2 = false;
			}
			return true;
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
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
		mTimer.schedule(task,24000, 25000); //��ʱ1000ms��ִ�У�1000msִ��һ��
		background = BitmapFactory.decodeResource(getResources(),R.drawable.bg_1);
		button = BitmapFactory.decodeResource(getResources(), R.drawable.bt_restart);
		button2 = BitmapFactory.decodeResource(getResources(),R.drawable.bt_exit);
		scalex = screen_width / background.getWidth();
		scaley = screen_height / background.getHeight();
		button_x = screen_width / 2 - button.getWidth() / 2;
		button_y = screen_height / 2 + button.getHeight();
		button_y2 = button_y + button.getHeight() + 40;
		// ���ذ�Χ�����ַ�������С��һ��Rect����
		paint.setTextSize(40);   
		paint.getTextBounds(startGame, 0, startGame.length(), rect);
		strwid = rect.width();
		strhei = rect.height();
	}
	// �ͷ�ͼƬ��Դ�ķ���
	@Override
	public void release() {
		// TODO Auto-generated method stub
		if (!button.isRecycled()) {
			button.recycle();
		}
		if (!button2.isRecycled()) {
			button2.recycle();
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
			canvas.drawBitmap(background, 0, 0, paint); 		// ���Ʊ���ͼ
			canvas.restore();
			//����ָ������ťʱ�任ͼƬ
				canvas.drawBitmap(button, button_x, button_y, paint);
				canvas.drawBitmap(button2, button_x, button_y2, paint);
	/*		paint.setTextSize(40);
			//���ذ�Χ�����ַ�������С��һ��Rect����     
			paint.getTextBounds(startGame, 0, startGame.length(), rect);    		   
			canvas.drawText(startGame, screen_width/2 - strwid/2, button_y + button.getHeight()/2 + strhei/2, paint);
			canvas.drawText(exitGame, screen_width/2 - strwid/2, button_y2 + button.getHeight()/2 + strhei/2, paint);*/
			paint.setTextSize(60);
			float textlong = paint.measureText("TOTAL SCORE:"+String.valueOf(score));
			canvas.drawText("TOTAL SCORE:"+String.valueOf(score), screen_width/2 - textlong/2, screen_height/2 - 100, paint);
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
	public void setScore(int score) {
		this.score = score;
	}
}
