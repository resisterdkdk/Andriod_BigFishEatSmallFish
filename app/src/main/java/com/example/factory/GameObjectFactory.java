package com.example.factory;

import android.content.res.Resources;

import com.example.object.BigPlane;
import com.example.object.GameObject;
import com.example.object.MiddlePlane;
import com.example.object.MyPlane;
import com.example.object.SmallPlane;
import com.example.object.stopGood;

/*��Ϸ����Ĺ�����*/
public class GameObjectFactory {
	//����С�͵л��ķ���
	public GameObject createSmallPlane(Resources resources){
		return new SmallPlane(resources);
	}
	//�������͵л��ķ���
	public GameObject createMiddlePlane(Resources resources){
		return new MiddlePlane(resources);
	}
	//�������͵л��ķ���
	public GameObject createBigPlane(Resources resources){
		return new BigPlane(resources);
	}
	//����BOSS�л��ķ���
	/*public GameObject createBossPlane(Resources resources){
		return new BossPlane(resources);
	}*/
	//������ҷɻ��ķ���
	public GameObject createMyPlane(Resources resources){
		return new MyPlane(resources);
	}
    public stopGood  createStopGood(Resources resources){
		return new stopGood(resources);
	}
	}


