package com.my.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;


/**
 * Created by AOW on 2018/4/16.
 * 通过bindService()和unBindService()方法启动和关闭Service,实现访问者和Service之间可以进行方法调用和数据交换，
 *
 * bindService()方法的完整签名：bingService(Intent intent,ServiceConnection conn,int flags),参数解释：
 *     intent:指定要启动的service;
 *     conn:该对象用于监听访问者与Service之间的连接情况，当访问者与service之间连接成功时将会回调该ServiceConnection
 *     对象的onServiceConnection(ComponentName name,IBinder service)方法，当访问者与Service之间断开连接时，
 *     将会回调该ServiceConnection对象的onServiceDisconnected(ComponentName name)方法；
 *     flags:指绑定时是否自动创建Service；
 *  注意：ServiceContect对象的onServiceConnected方法中有一个IBinder,该对象即可实现与绑定Service之间的通信
 *  开发Service类时，该Service类必须提供一个IBinder onBind(Intent intent)方法，在绑定本地Service的情况下，
 *  onBind(Intent intent)方法返回的IBinder对象，将会传给ServiceConnection对象里
 *  onServiceConnection(ComponentName name,IBinder ibinder)方法的ibinder参数,这样访问者就可通过IBinder对象与Service通信
 *
 * 实现IBinder对象的方法：通常采用继承Binder(IBinder的实现类)的方式来实现
 */

public class BindService extends Service {
    private int count=0;
    private boolean flag;

    private MyBinder myBinder=new MyBinder();
    //通过继承Binder  实现IBinder对象传给ServiceConnected对象的onServiceConnected()方法中的第二个参数
    public class MyBinder extends Binder {
        public int getCount(){
            System.out.println("MyBinder类");
            return count;
        }
    }

    //   必须要实现的方法
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind()方法");
        return myBinder;
    }

    //    service初次被创建时，回调该方法
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (!flag){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind()方法");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy()方法");
        super.onDestroy();
        this.flag=true;

    }


}
