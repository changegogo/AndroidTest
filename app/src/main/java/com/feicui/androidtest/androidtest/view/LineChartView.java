package com.feicui.androidtest.androidtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.feicui.androidtest.androidtest.entity.WeekData;
import com.feicui.androidtest.androidtest.util.CommonUtil;

/**
 * Author: dlw on 2016/10/18 09:54
 * Email: dailongshao@126.com
 */
public class LineChartView extends View {
    private final String TAG = LineChartView.class.getName();
    private Paint mPaint;
    private int[] offset = new int[7];
    private Point mWidthHeight;
    private int distance = 100;
    private int mOffset;
    private int mHeightSize;


    public LineChartView(Context context) {
        this(context,null);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
        mWidthHeight = CommonUtil.getScreenWidthHeight(getContext());
        mOffset = mWidthHeight.x - 7 * distance;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeightSize = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i=0;i<6;i++){
            canvas.drawLine((mOffset / 2)+i*distance,mHeightSize-offset[i]*10,(mOffset / 2)+(i+1)*distance,mHeightSize-offset[i+1]*10,mPaint);
            canvas.drawText(offset[i]+"",100+100*i,100,mPaint);
        }
        canvas.drawText(offset[6]+"",100+100*6,100,mPaint);
    }

    public void setData(WeekData.DataBean dataBean){
        offset[0] = dataBean.getMon();
        offset[1] = dataBean.getFri();
        offset[2] = dataBean.getWed();
        offset[3] = dataBean.getThu();
        offset[4] = dataBean.getTue();
        offset[5] = dataBean.getSat();
        offset[6] = dataBean.getSun();
        //重画
        postInvalidate();
    }
}
