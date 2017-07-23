package com.example.suraj.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText first, second;
    TextView mResult;
    Button mMultiply;
    MultiplyInterface multiplyInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = (EditText) findViewById(R.id.numOne);
        second = (EditText) findViewById(R.id.numTwo);
        mResult = (TextView) findViewById(R.id.result);
        mMultiply = (Button) findViewById(R.id.btnMultiply);

        mMultiply.setOnClickListener(MainActivity.this);

        bindService(new Intent(MainActivity.this, MultiplicationSevice.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            multiplyInterface = MultiplyInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    public void onClick(View v) {
        int f, s, r;
        f = Integer.parseInt(first.getText().toString());
        s = Integer.parseInt(second.getText().toString());
        try {
            r= multiplyInterface.MultiplyTwoNumbers(f,s);
            mResult.setText(String.valueOf(r));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
