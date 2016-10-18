package com.feicui.androidtest.androidtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.feicui.androidtest.androidtest.entity.WeekData;

/**
 * Author: dlw on 2016/9/25 18:30
 * Email: dailongshao@126.com
 * 绘制折线图
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder mholder;
    private Canvas mCanvas;
    private Paint mPaint;
    private int[] offset;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mholder = this.getHolder();
        mholder.addCallback(this);
        this.setFocusable(true);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
    }

    public void setData(WeekData.DataBean dataBean){
        offset = new int[7];
        offset[0] = dataBean.getMon();
        offset[1] = dataBean.getFri();
        offset[2] = dataBean.getWed();
        offset[3] = dataBean.getThu();
        offset[4] = dataBean.getTue();
        offset[5] = dataBean.getSat();
        offset[6] = dataBean.getSun();
        //重画
        draw();
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }


    private void draw(){
        mCanvas = mholder.lockCanvas();
        for(int i=0;i<6;i++){
            mCanvas.drawLine(200+i*80,offset[i]*10,200+(i+1)*80,offset[i+1]*10,mPaint);
        }
        mholder.unlockCanvasAndPost(mCanvas);
    }
}
