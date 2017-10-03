package com.example.admin.multithreading;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Admin on 10/3/2017.
 */

public class MyAsyncTask extends AsyncTask<String, Integer, String> {

    private static final String TAG = "MyAsyncTask";
    TextView textView;

    public MyAsyncTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: " + Thread.currentThread());

        textView.setText( "Setting up the Task" );
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: " + Thread.currentThread());

        for( int i = 0; i < 5; i++ ) {
            publishProgress(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "Completed the task.";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + Thread.currentThread());

        textView.setText( String.valueOf( values[0] ) );
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: " + Thread.currentThread());

        textView.setText( s );
    }
}
