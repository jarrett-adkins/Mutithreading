package com.example.admin.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Admin on 10/3/2017.
 */

public class MyThread extends Thread {

    private static final String TAG = "MyThread";
    Handler handler;

    public MyThread( Handler h ) {
        handler = h;
    }

    @Override
    public void run() {
        int total = 0;

        for( int i = 0; i < 5; i++ ) {
            Log.d(TAG, "run: " + i + ", Current Thread: " + Thread.currentThread());

            try {
                Thread.sleep( 1000 ); //1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            total += i;
        }

        //send the results back to the UI thread using a handler message.
        Bundle bundle = new Bundle();
        bundle.putInt( "total", total );

        //add bundle to message object
        Message message = new Message();
        message.setData( bundle );

        handler.sendMessage( message );

        //send message with eventbus
        EventBus.getDefault().post( new HelloEvent( String.valueOf( total )));
    }
}
