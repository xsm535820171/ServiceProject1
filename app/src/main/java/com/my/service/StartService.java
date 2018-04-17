package com.my.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by AOW on 2018/4/16.
 * 继承service的子类
 */

public class StartService extends Service {
    //  该方法是Service子类必须实现的方法，该方法返回一个IBinder对象，应用程序可通过该对象与Service组件通信
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("xsm","onbind");
        return null;
    }

    //    当该Service第一次被创建后将立即回调的方法
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("xsm","onCreate");
    }
    //    每次客户端调用startService(intent)方法启动该Service时，都会回调该方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("xsm","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //    当该Service被关闭之前，将会回调该方法
    @Override
    public void onDestroy() {
        Log.e("xsm","onDestroy");
        super.onDestroy();
    }

    //    当该Service上绑定的所有客户端都断开连接时，将会回调按该方法
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("xsm","onUnbind");
        return super.onUnbind(intent);
    }
}
