package com.example.object;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.mybeatplane.R;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class stopGood extends GameGoods {

    public stopGood(Resources resources) {
        super(resources);
    }
    // 初始化图片资源的
    @Override
    protected void initBitmap() {
        // TODO Auto-generated method stub
        bmp = BitmapFactory.decodeResource(resources, R.drawable.good);
        object_width = bmp.getWidth();
        object_height = bmp.getHeight();
    }
}
