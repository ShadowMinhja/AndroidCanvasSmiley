package com.example.minh.smiley;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {
    private RelativeLayout mainLayout;
    private float dimen = 50f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(new SmileyView(this, 50, 100, 100, 0, -50));
        //Load Smiley
        mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
//        View buttonView = new View(this);
        SmileyView buttonView = new SmileyView(this, 25, 80, 55, 0, -50);
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int iDimen = (int)(metrics.density * dimen);
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(iDimen, iDimen);
        buttonParams.topMargin = 0;
        Resources res = getResources();
        Drawable shape1 = res. getDrawable(R.drawable.smiley);
        buttonView.setBackground(shape1);
        LayerDrawable sd = (LayerDrawable) buttonView.getBackground().mutate();
        Drawable face = sd.findDrawableByLayerId(R.id.faceLayer);
        face.setColorFilter(getResources().getColor(R.color.translucent_yellow), PorterDuff.Mode.MULTIPLY);
        sd.invalidateSelf();
        mainLayout.addView(buttonView, buttonParams);

        RelativeLayout.LayoutParams buttonParams2 = new RelativeLayout.LayoutParams(iDimen, iDimen);
        buttonParams2.topMargin = 110;
//        View buttonView2 = new View(this);
        SmileyView buttonView2 = new SmileyView(this, 25, 80, 55, 0, 0);
        Drawable shape2 = res. getDrawable(R.drawable.smiley);
        buttonView2.setBackground(shape2);
        LayerDrawable sd2 = (LayerDrawable) buttonView2.getBackground().mutate();
        Drawable face2 = sd2.findDrawableByLayerId(R.id.faceLayer);
        face2.setColorFilter(getResources().getColor(R.color.translucent_red), PorterDuff.Mode.MULTIPLY);
        sd2.invalidateSelf();
        mainLayout.addView(buttonView2, buttonParams2);
//
        RelativeLayout.LayoutParams buttonParams3 = new RelativeLayout.LayoutParams(iDimen, iDimen);
        buttonParams3.topMargin = 220;
//        View buttonView3 = new View(this);
        SmileyView buttonView3 = new SmileyView(this, 25, 60, 55, 0, 50);
        Drawable shape3 = res.getDrawable(R.drawable.smiley);
        buttonView3.setBackground(shape3);
        LayerDrawable sd3 = (LayerDrawable) buttonView3.getBackground().mutate();
        Drawable face3 = sd3.findDrawableByLayerId(R.id.faceLayer);
        face3.setColorFilter(getResources().getColor(R.color.translucent_blue), PorterDuff.Mode.MULTIPLY);
        sd2.invalidateSelf();
        mainLayout.addView(buttonView3, buttonParams3);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class SmileyView extends View {

        private int origin_x;
        private int origin_y;
        private int final_x;
        private int final_y;
        private int curve_y;

        // CONSTRUCTOR
        public SmileyView(Context context, int origin_x, int origin_y, int final_x, int final_y, int curve_y) {
            super(context);
            setFocusable(true);
            this.origin_x = origin_x;
            this.origin_y = origin_y;
            this.final_x = final_x;
            this.final_y = final_y;
            this.curve_y = curve_y;
        }

        @Override
        protected void onDraw(Canvas canvas) {
//            canvas.drawColor(Color.CYAN);
            Paint p = new Paint();
            // smooths
            p.setAntiAlias(true);
            p.setColor(Color.BLACK);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(5);
            p.setStrokeCap(Paint.Cap.ROUND);
            // opacity
            //p.setAlpha(0x80); //

            final Path path = new Path();
            path.moveTo(this.origin_x, this.origin_y);
            int cx = this.origin_x;
            //Smile
            //path.rQuadTo(45, 75, x2, y2);
            //Sad
            path.rQuadTo(cx, this.curve_y, this.final_x, this.final_y);
            canvas.drawPath(path, p);


            //Smile
//            RectF rectF = new RectF(50, 20, 100, 80);
//            canvas.drawArc (rectF, 0, 180, true, p);
            //Sadness
//            RectF rectF = new RectF(50, 20, 100, 80);
//            canvas.drawArc (rectF, 180, 90, true, p);

        }

    }
}