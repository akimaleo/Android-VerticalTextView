package com.letit0or1.verticaltextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
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
        // vise versa
        _height = (int) (text().length() * getTextSize());
        _width = getMeasuredHeight();
        setMeasuredDimension(_width, _height);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        TextPaint paint = getPaint();
        paint.setColor(getTextColors().getDefaultColor());

        char[] text = text().toCharArray();

        float textSize = getTextSize();
        float yPos, paddingLeft = getPaddingLeft(), paddingTop = getPaddingTop();
        for (int i = 0; i < text.length; i++) {
            yPos = i * textSize + paddingTop + textSize;
            canvas.drawText(text[i] + "", paddingLeft, yPos, paint);
        }
    }

    private String text() {
        return super.getText().toString();
    }
}