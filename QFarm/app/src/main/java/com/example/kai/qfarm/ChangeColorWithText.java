package com.example.kai.qfarm;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import static android.support.v7.appcompat.R.attr.alpha;

/**
 * Created by kai on 2017/5/3.
 */

public class ChangeColorWithText extends View {

    private int mColor = 0xff45c01a;

    private Bitmap mIconBitmap;
    private String mText = "哈哈";
    private int mTextSize = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics());


    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint  mPaint;

    private  float mAlpha;
    private Rect mIconRect;
    private Rect mTextBound;
    private Paint mTextPaint;



    public ChangeColorWithText(Context context) {
        this(context,null);
    }

    public ChangeColorWithText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChangeColorWithText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ChangeColorWithText);

        int n =a.getIndexCount();

        for(int i=0;i< n; i ++){
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ChangeColorWithText_icon:
                    BitmapDrawable drawable = (BitmapDrawable)a.getDrawable(attr);
                    mIconBitmap=drawable.getBitmap();
                    break;

                case R.styleable.ChangeColorWithText_color:
                    mColor = a.getColor(attr,0xff45c01a);
                    break;
                case R.styleable.ChangeColorWithText_text:
                    mText = a.getString(attr);
                    break;
                case R.styleable.ChangeColorWithText_text_size:
                    mTextSize = (int) a.getDimension(attr,TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics()));
                    break;

            }
        }
        a.recycle();

        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xff55555);
        mTextPaint.getTextBounds(mText,0,mText.length(),mTextBound);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int iconWidth = Math.min(getMeasuredWidth()-getPaddingLeft()-getPaddingRight(),
                getMeasuredHeight()-getPaddingTop()-getPaddingBottom()-mTextBound.height());
        int left = getMeasuredWidth()/2-iconWidth/2;
        int top = (getMeasuredHeight()-mTextBound.height())/2-iconWidth/2;

        mIconRect = new Rect(left,top,left+iconWidth,top+iconWidth);


    }

    @Override
    protected void onDraw(Canvas canvas) {
         canvas.drawBitmap(mIconBitmap,null,mIconRect,null);

        int alpha = (int)Math.ceil(mAlpha*255);

        //绘制图标
        setupTargetBitmap(alpha);


       //1.绘制原文本  2.绘制变色的文本
        drawSourceText();
        drawTargetText(canvas,alpha);

        canvas.drawBitmap(mBitmap,0,0,null);


    }

    private void drawTargetText(Canvas canvas,int alpha) {
        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(alpha);
        int x = getMeasuredWidth()/2-mTextBound.width()/2;
        int y = mIconRect.bottom + mTextBound.height();
        mCanvas.drawText(mText,x,y,mTextPaint);

    }

    private void drawSourceText() {

        mTextPaint.setColor(0xff333333);
        mTextPaint.setAlpha(255-alpha);
        int x = getMeasuredWidth()/2-mTextBound.width()/2;
        int y = mIconRect.bottom + mTextBound.height();
        mCanvas.drawText(mText,x,y,mTextPaint);
    }

    private void setupTargetBitmap(int alpha) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint  = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        mCanvas.drawRect(mIconRect,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap,null,mIconRect,mPaint);
    }
    public void setIconAlpha(float alpha){
        this.mAlpha=alpha;
        invilidateView();

    }

    private void invilidateView() {
        if (Looper.getMainLooper()==Looper.myLooper()){
            invalidate();
        }else {
            postInvalidate();
        }
    }

}
