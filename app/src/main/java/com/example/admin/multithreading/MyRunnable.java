package com.example.admin.multithreading;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Admin on 10/3/2017.
 */

public class MyRunnable implements Runnable {

    private static final String TAG = "MyRunnable";
    TextView textView;
    Handler handler = new Handler( Looper.getMainLooper() );
    int i;

    public MyRunnable( TextView textView ) {
        this.textView = textView;
    }

    @Override
    public void run() {
        for( i = 0; i < 5; i++ ) {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText( String.valueOf( i ));
                }
            });

            Log.d(TAG, "run: " + i + ", Current Thread: " + Thread.currentThread());

            try {
                Thread.sleep( 1000 ); //1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
