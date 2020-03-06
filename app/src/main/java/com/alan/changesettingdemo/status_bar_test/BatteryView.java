package com.alan.changesettingdemo.status_bar_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Alan
 * Date: 2020/3/6
 */
@SuppressWarnings("FieldCanBeLocal")
public class BatteryView extends View {

    private Paint mBatteryHeadPaint, mBatteryBodyPaint, mBatteryPowerPaint;
    private RectF mBatteryHeadRect, mBatteryBodyRect, mBatteryPowerRect;
    //电池头部宽高
    private float mBatteryHeadHeight = 8.0f;
    private float mBatteryHeadWidth = 2.0f;
    //电池厚度
    private float mBatterySize = 2.0f;
    //电池边框与电池之间的间隙
    private float mGapOfBatteryPower = 3.0f;
    //满电宽度
    private float mFullPowerWidth;
    //目前电量
    private int mCurrentPower;
    //电池圆角
    private int mBatteryRoundCorner = 8;



    public BatteryView(Context context) {
        this(context, null);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initRectF();
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initRectF();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //控件宽高
        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int specHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        //设置电池外框
        mBatteryBodyRect.right = specWidthSize - mBatterySize - mBatteryHeadWidth;
        mBatteryBodyRect.bottom = specHeightSize - mBatterySize;

        //设置电池盖矩形
        mBatteryHeadRect.left = mBatteryBodyRect.right;
        mBatteryHeadRect.top = (float) specHeightSize / 2 - mBatteryHeadHeight / 2;
        mBatteryHeadRect.right = specWidthSize;
        mBatteryHeadRect.bottom = (float) specHeightSize / 2 + mBatteryHeadHeight / 2;

        //设置电池体
        mBatteryPowerRect.left = mBatteryBodyRect.left +  mGapOfBatteryPower;
        mBatteryPowerRect.top = mBatteryBodyRect.top + mGapOfBatteryPower;
        mBatteryPowerRect.bottom = mBatteryBodyRect.bottom - mGapOfBatteryPower;

        mFullPowerWidth = mBatteryBodyRect.right - mGapOfBatteryPower - mBatteryPowerRect.left;
        setMeasuredDimension(specWidthSize, specHeightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据电量计算电池体的宽度
        mBatteryPowerRect.right = (float) mCurrentPower / 100 * mFullPowerWidth + mBatteryPowerRect.left;
        canvas.drawRoundRect(mBatteryBodyRect, mBatteryRoundCorner, mBatteryRoundCorner, mBatteryBodyPaint);
        canvas.drawRoundRect(mBatteryHeadRect, 1, 1, mBatteryHeadPaint);
        canvas.drawRoundRect(mBatteryPowerRect, mBatteryRoundCorner, mBatteryRoundCorner, mBatteryPowerPaint);

    }

    public void setBatteryPower(int power){
        mCurrentPower = power > 100 ? 100 : Math.max(power, 1);
        invalidate();
    }

    public int getBatteryPower(){
        return mCurrentPower;
    }

    private void initPaint(){
        //设置画笔样式
        mBatteryBodyPaint = new Paint();
        mBatteryBodyPaint.setColor(Color.parseColor("#999999"));
        mBatteryBodyPaint.setAntiAlias(true);
        mBatteryBodyPaint.setStyle(Paint.Style.STROKE);
        mBatteryBodyPaint.setStrokeWidth(2.6f);

        mBatteryHeadPaint = new Paint();
        mBatteryHeadPaint.setColor(Color.parseColor("#999999"));
        mBatteryHeadPaint.setStyle(Paint.Style.FILL);
        mBatteryHeadPaint.setAntiAlias(true);

        mBatteryPowerPaint = new Paint();
        mBatteryPowerPaint.setColor(Color.parseColor("#000000"));
        mBatteryPowerPaint.setAntiAlias(true);
        mBatteryPowerPaint.setStyle(Paint.Style.FILL);

    }

    private void initRectF(){
        mBatteryHeadRect = new RectF();
        mBatteryHeadRect.left = 15f;
        mBatteryHeadRect.top = 6f;

        mBatteryBodyRect = new RectF();
        mBatteryPowerRect = new RectF();
    }








}
