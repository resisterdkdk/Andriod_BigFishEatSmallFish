package com.example.object;

import java.util.ArrayList;
import java.util.List;
import com.example.factory.GameObjectFactory;
import com.example.interfaces.IMyPlane;
import com.example.mybeatplane.R;
import com.example.view.MainView;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
/*��ҷɻ�����*/
public class MyPlane extends GameObject implements IMyPlane{
	private float middle_x;			 // �ɻ�����������
	private float middle_y;
	protected float object_width_2; 	// ����Ŀ��
	protected float object_height_2; 	// ����ĸ߶�
	protected float object_width_3; 	// ����Ŀ��
	protected float object_height_3; 	// ����ĸ߶�
    protected float object_width_4; 	// ����Ŀ��
    protected float object_height_4; 	// ����ĸ߶�
    protected float object_width_5; 	// ����Ŀ��
    protected float object_height_5; 	// ����ĸ߶�
    protected float object_width_6; 	// ����Ŀ��
    protected float object_height_6; 	// ����ĸ߶�
    protected float object_width_7; 	// ����Ŀ��
    protected float object_height_7; 	// ����ĸ߶�
	private long startTime;	 	 	 // ��ʼ��ʱ��
	private long endTime;	 	 	 // ������ʱ��
	private boolean isChangeBullet;  // ��Ǹ������ӵ�
	private Bitmap myfish1;			 // �ɻ�����ʱ��ͼƬ
	private Bitmap myfish12;		 // �ɻ���ըʱ��ͼƬ
	private Bitmap myfish2;			 // �ɻ�����ʱ��ͼƬ
	private Bitmap myfish22;		 // �ɻ���ըʱ��ͼƬ
	private Bitmap myfish3;			 // �ɻ�����ʱ��ͼƬ
	private Bitmap myfish32;		 // �ɻ���ըʱ��ͼƬ
	private Bitmap myfish4;			 // �ɻ�����ʱ��ͼƬ
	private Bitmap myfish42;		 // �ɻ���ըʱ��ͼƬ
	private Bitmap myfish5;			 // �ɻ�����ʱ��ͼƬ
	private Bitmap myfish52;		 // �ɻ���ըʱ��ͼƬ
	private Bitmap myfish6;			 // �ɻ�����ʱ��ͼƬ
	private Bitmap myfish62;		 // �ɻ���ըʱ��ͼƬ
	private Bitmap myfish7;			 // �ɻ�����ʱ��ͼƬ
	private Bitmap myfish72;		 // �ɻ���ըʱ��ͼƬ



	private Bitmap myMidfish;			 // �ɻ�����ʱ��ͼƬ
	private Bitmap myMidfish2;		 // �ɻ���ըʱ��ͼƬ
	private Bitmap myBigfish;			 // �ɻ�����ʱ��ͼƬ
	private Bitmap myBigfish2;		 // �ɻ���ըʱ��ͼƬ
	private MainView mainView;
	private GameObjectFactory factory;
	public MyPlane(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		initBitmap();
		this.speed = 100;
		this.dir = 1;
		this.power = 2;
		factory = new GameObjectFactory();
	}
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	// ������Ļ��Ⱥ͸߶�
	@Override
	public void setScreenWH(float screen_width, float screen_height) {
		super.setScreenWH(screen_width, screen_height);
		object_x = screen_width/2 - object_width/2;;
		object_y = screen_height/2 - object_height/2;
		middle_x = object_x + object_width/2;
		middle_y = object_y + object_height/2;
	}
	// ��ʼ��ͼƬ��Դ��
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		myfish1 =  BitmapFactory.decodeResource(resources, R.drawable.myfish1_1);
		myfish12 = BitmapFactory.decodeResource(resources, R.drawable.myfish1_2);
		myfish2 =  BitmapFactory.decodeResource(resources, R.drawable.myfish2_1);
		myfish22 = BitmapFactory.decodeResource(resources, R.drawable.myfish2_2);
		myfish3 =  BitmapFactory.decodeResource(resources, R.drawable.myfish3_1);
		myfish32 = BitmapFactory.decodeResource(resources, R.drawable.myfish3_2);
		myfish4 =  BitmapFactory.decodeResource(resources, R.drawable.myfish4_1);
		myfish42 = BitmapFactory.decodeResource(resources, R.drawable.myfish4_2);
		myfish5 =  BitmapFactory.decodeResource(resources, R.drawable.myfish5_1);
		myfish52 = BitmapFactory.decodeResource(resources, R.drawable.myfish5_2);
		myfish6 =  BitmapFactory.decodeResource(resources, R.drawable.myfish6_1);
		myfish62 = BitmapFactory.decodeResource(resources, R.drawable.myfish6_2);
		myfish7 =  BitmapFactory.decodeResource(resources, R.drawable.myfish7_1);
		myfish72 = BitmapFactory.decodeResource(resources, R.drawable.myfish7_2);

	}
	// ����Ļ�ͼ����
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		if(this.power/2==1) {
			object_width = myfish1.getWidth(); // ���ÿһ֡λͼ�Ŀ�
			object_height = myfish1.getHeight();    // ���ÿһ֡λͼ�ĸ�
		}
		if(this.power/2==2) {
			object_height = myfish2.getHeight();
			object_width = myfish2.getWidth();
		}
		if(this.power/2==3) {
			object_height = myfish3.getHeight();
			object_width = myfish3.getWidth();
		}
		if(this.power/2==4) {
			object_height = myfish4.getHeight();
			object_width = myfish4.getWidth();
		}
		if(this.power/2==5) {
			object_height = myfish5.getHeight();
			object_width = myfish5.getWidth();
		}
		if(this.power/2==6) {
			object_height = myfish6.getHeight();
			object_width = myfish6.getWidth();
		}
		if(this.power/2==7) {
			object_height = myfish7.getHeight();
			object_width = myfish7.getWidth();
		}
		if(isAlive){
			int x = (int) (currentFrame * object_width); // ��õ�ǰ֡�����λͼ��X����
			if(dir == 1)
			{
				canvas.save();
				if(this.getPower()/2==1)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish1, object_x, object_y, paint);
				}

				if(this.getPower()/2==2)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish2, object_x, object_y, paint);
				}

				if(this.getPower()/2==3)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish3, object_x, object_y, paint);
				}
				if(this.getPower()/2==4)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish4, object_x, object_y, paint);
				}
				if(this.getPower()/2==5)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish5, object_x, object_y, paint);
				}
				if(this.getPower()/2==6)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish6, object_x, object_y, paint);
				}
				if(this.getPower()/2==7)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish7, object_x, object_y, paint);
				}
				canvas.restore();
			}
			else
			{
				canvas.save();

				if(this.getPower()/2==1)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish12, object_x, object_y, paint);
				}

				if(this.getPower()/2==2)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish22, object_x, object_y, paint);
				}

				if(this.getPower()/2==3)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish32, object_x, object_y, paint);
				}
				if(this.getPower()/2==4)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish42, object_x, object_y, paint);
				}
				if(this.getPower()/2==5)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish52, object_x, object_y, paint);
				}
				if(this.getPower()/2==6)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish62, object_x, object_y, paint);
				}
				if(this.getPower()/2==7)
				{
					canvas.clipRect(object_x, object_y, object_x+ object_width, object_y+ object_height);
					canvas.drawBitmap(myfish72, object_x, object_y, paint);
				}
				canvas.restore();
			}

		}
	}
	// �ͷ���Դ�ķ���
	@Override
	public void release() {
		// TODO Auto-generated method stub
		if(!myfish1.isRecycled()){
			myfish1.recycle();
		}
		if(!myfish12.isRecycled()){
			myfish12.recycle();
		}
		if(!myfish2.isRecycled()){
			myfish2.recycle();
		}
		if(!myfish22.isRecycled()){
			myfish22.recycle();
		}
		if(!myfish3.isRecycled()){
			myfish3.recycle();
		}
		if(!myfish32.isRecycled()){
			myfish32.recycle();
		}
		if(!myfish4.isRecycled()){
			myfish4.recycle();
		}
		if(!myfish42.isRecycled()){
			myfish42.recycle();
		}
		if(!myfish5.isRecycled()){
			myfish5.recycle();
		}
		if(!myfish52.isRecycled()){
			myfish52.recycle();
		}
		if(!myfish6.isRecycled()){
			myfish6.recycle();
		}
		if(!myfish62.isRecycled()){
			myfish62.recycle();
		}
		if(!myfish7.isRecycled()){
			myfish7.recycle();
		}
		if(!myfish72.isRecycled()){
			myfish72.recycle();
		}
	}

	//getter��setter����
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	@Override
	public float getMiddle_x() {
		return middle_x;
	}
	@Override
	public void setMiddle_x(float middle_x) {
		this.middle_x = middle_x;
		this.object_x = middle_x - object_width/2;
	}
	@Override
	public float getMiddle_y() {
		return middle_y;
	}
	@Override
	public void setMiddle_y(float middle_y) {
		this.middle_y = middle_y;
		this.object_y = middle_y - object_height/2;
	}	
}
