package com.alan.changesettingdemo.switch_btn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Alan
 * Date: 2020/4/3
 */
public class SwitchButton extends View {

    private boolean mToggle = false;
    private int mBackgroundCheckedColor = Color.parseColor("#aa99dd");
    private int mBackgroundUncheckedColor = Color.parseColor("#aa99dd");
    private int mOvalButtonColor = Color.parseColor("#ffffff");

    private Paint mBackgroundPaint = new Paint();
    private RectF mBackgroundRectF = new RectF();

    private Paint mOvalButtonPaint = new Paint();
    private RectF mOvalButtonRectF = new RectF();

    private int mSwitchCorner = 15;



    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(mBackgroundUncheckedColor);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mOvalButtonPaint.setAntiAlias(true);
        mOvalButtonPaint.setColor(mOvalButtonColor);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("SwitchButton", "widthMode = " + widthMode + "heightMode = " + heightMode);
        Log.d("SwitchButton", "widthSize = " + widthSize + "heightSize = " + heightSize);
//        mBackgroundRectF.right
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(mBackgroundRectF, mSwitchCorner, mSwitchCorner, mBackgroundPaint);
        canvas.drawOval(mOvalButtonRectF, mOvalButtonPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
        mToggle = !mToggle;
        setSwitchState(mToggle);
    }

    private void setSwitchState(boolean toggle){

    }

    public boolean getToggle() {
        return mToggle;
    }

    public void setToggle(boolean toggle) {
        mToggle = toggle;
    }

}
