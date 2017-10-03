package com.example.admin.multithreading;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById( R.id.tvResult );
    }

    public void createThread(View view) {

        switch( view.getId() ) {
            case R.id.btnUsingRunnable:

                MyRunnable runnable = new MyRunnable( result );
                Thread t = new Thread( runnable );
                t.start();

                break;
            case R.id.btnUsingThread:

                Handler h = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        String total = String.valueOf( message.getData().getInt( "total" ));
                        result.setText( total );
                        return false;
                    }
                }); // can also have class implement Handler.Callback

                MyThread thread = new MyThread( h );
                thread.start();

                break;
            case R.id.btnUsingAsyncTask:

                MyAsyncTask asyncTask = new MyAsyncTask( result );
                asyncTask.execute();
                break;
        }
    }

    @Subscribe( threadMode = ThreadMode.MAIN )
    public void onHelloEvent( HelloEvent helloEvent ) {
        Toast.makeText(this, "Eventbus: " + helloEvent.getData(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register( this );
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister( this );
    }
}

//https://github.com/greenrobot/EventBus