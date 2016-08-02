package com.example.test;

import java.util.Timer;
import java.util.TimerTask;

import org.achartengine.GraphicalView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class ViewanalysisActivity extends Activity  {
    private LinearLayout mLeftCurveLayout;//´æ·Å×óÍ¼±íµÄ²¼¾ÖÈÝÆ÷
    private LinearLayout mRightCurveLayout;//´æ·ÅÓÒÍ¼±íµÄ²¼¾ÖÈÝÆ÷
    private GraphicalView mView, mView2;//×óÓÒÍ¼±í
    private ChartService mService, mService2;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_main);

        mLeftCurveLayout = (LinearLayout) findViewById(R.id.left_temperature_curve);
        mRightCurveLayout = (LinearLayout) findViewById(R.id.right_temperature_curve);

        mService = new ChartService(this);
        mService.setXYMultipleSeriesDataset("Income in 2014");
        mService.setXYMultipleSeriesRenderer(12, 10, "Income in 2014", "month", "income(w yuan)",
                Color.BLACK, Color.GRAY, Color.RED, Color.BLACK);
        mView = mService.getGraphicalView();

        mService2 = new ChartService(this);
        mService2.setXYMultipleSeriesDataset("Income in 2015");
        mService2.setXYMultipleSeriesRenderer(12, 10, "Income in 2015", "month", "income(w yuan)",
                Color.BLACK, Color.GRAY, Color.RED, Color.BLACK);
        mView2 = mService2.getGraphicalView();

        //½«×óÓÒÍ¼±íÌí¼Óµ½²¼¾ÖÈÝÆ÷ÖÐ
        mLeftCurveLayout.addView(mView, new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mRightCurveLayout.addView(mView2, new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendMessage(handler.obtainMessage());
            }
        }, 10, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private int t = 0;
    private Handler handler = new Handler() {
        @Override
        //¶¨Ê±¸üÐÂÍ¼±í
        public void handleMessage(Message msg) {
            double[] A = new double[]{2.3, 2.5, 2.9, 3.8, 7.6, 5.9, 6.3, 7.3, 3.9, 2.2, 3.3, 2.7};
            double[] B = new double[]{3.4, 2.7, 3.5, 4.6, 6.8, 4.6, 5.2, 5.4, 4.2, 2.5, 2.6, 2.5};
            for (int o = 0; o < 12; o++) {
                mService.updateChart(t, A[o]);
                mService2.updateChart(t, B[o]);
                t+=1;
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

}
