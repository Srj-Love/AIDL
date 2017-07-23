package com.example.suraj.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MultiplicationSevice extends Service {

    public MultiplicationSevice() {
    }

    @Override
    public IBinder onBind(Intent intent) {


        return myBinder;
    }

    // ceate an Object of MyInterface
    MultiplyInterface.Stub myBinder = new MultiplyInterface.Stub() {
        @Override
        public int MultiplyTwoNumbers(int first, int second) throws RemoteException {
            return first * second;
        }
    };
}
