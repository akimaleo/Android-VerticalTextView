package com.letit0or1.verticaltextview;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

//Thanks for http://stackoverflow.com/a/17001995/5859376 for idea ))

public class VerticalTextView extends TextView {

    private int _width, _height;

    public VerticalTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalTextView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams lp = getLayoutParams();

        _height = heightMeasureSpec;
        _width = widthMeasureSpec;

        if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            _height = (int) (text().length() * getTextSize());
        }
        if (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            _width = (int) (getTextSize() + 0.5f);
        }

        setMeasuredDimension(_width, _height);

    }

    public float getTextHeight() {
        return text().toString().length() * getTextSize();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float topStep = 0, leftStep = 0;

        if (getGravity() == Gravity.CENTER_HORIZONTAL) {
            leftStep = getWidth() / 2 - getTextSize() / 2;
        } else if (getGravity() == Gravity.CENTER_VERTICAL) {
            topStep = getHeight() / 2 - getTextHeight() / 2;
        } else if (getGravity() == Gravity.BOTTOM) {
            topStep = getHeight() / 2 - getTextHeight() / 2;
        } else if (getGravity() == Gravity.CENTER) {
            topStep = getHeight() / 2 - getTextHeight() / 2;
            leftStep = getWidth() / 2 - getTextSize() / 2;
        }
        TextPaint paint = getPaint();
        paint.setColor(getTextColors().getDefaultColor());

        char[] text = text().toCharArray();

        float textSize = getTextSize();
        float yPos, paddingLeft = getPaddingLeft(), paddingTop = getPaddingTop();
        for (int i = 0; i < text.length; i++) {
            yPos = i * textSize + paddingTop + textSize + topStep;
            canvas.drawText(text[i] + "", paddingLeft + leftStep, yPos, paint);
        }
    }

    private String text() {
        return super.getText().toString();
    }
}