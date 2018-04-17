package com.my.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.show_tv)
    TextView show_tv;
    BindService.MyBinder myBInder;
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //获取Service的onBond()方法返回的MyBinder
            myBInder= (BindService.MyBinder) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("onServiceDisconnected()方法");
        }
    };
    @OnClick({R.id.show_tv,R.id.startservice_bt,R.id.stopservice_bt,R.id.bindservice_bt,R.id.unbindservice_bt})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.startservice_bt:
//                getArrayJson("[[15,20,20,25,25,30,30,35,35,40,40,45,45,50,50,55,55,60,60,65,65,70,70,75,75,80,80,85,85,90,90,95,95,100,100,105,105,110,110,115,115,120,120,125,125,130,130,135,135,140,140,145,145,150,150,155,155,160,160,165,165,170,170,175,175,180,180,185,185,190,190,195,1\n" +
//                        "95,200,200,205,205,210,210,215,215,220,220,225,225,230,230,235,235,240,240,245,245,250,250,255,255,260,260,265],[5,7,7,9,9,11,11,13,13,15,15,17,17,19,19,21,21,23,23,25,25,27,27,29,29,31,31,33,33,35,35,37,37,39,39,41,41,43,43,45,45,47,47,49,49,51,51,53,53,55,55,57,57,59,59,61,61,63,63,65,65,67,67,69,\n" +
//                        "69,71,71,73,73,75,75,77,77,79,79,81,81,83,83,85,85,87,87,89,89,91,91,93,93,95,95,97,97,99,99,101,101,103,103,105]]");

                final Intent intent=new Intent(MainActivity.this,StartService.class);
                intent.setAction("com.my.serviceproject.FIRST_SERVICE");
                startService(intent);
                Toast.makeText(this,"开始开始",Toast.LENGTH_LONG).show();
                break;
            case R.id.stopservice_bt:
                final Intent intent1=new Intent(MainActivity.this,StartService.class);
                intent1.setAction("com.my.serviceproject.FIRST_SERVICE");
                stopService(intent1);
                Toast.makeText(this,"停止停止",Toast.LENGTH_LONG).show();
                break;
            case R.id.bindservice_bt:
//                bindService(intent);
                final Intent intent2=new Intent(MainActivity.this,BindService.class);
                intent2.setAction("com.my.serviceproject.Second_SERVICE");
                bindService(intent2,connection,BIND_AUTO_CREATE);

                Toast.makeText(this,"开始bindService（）",Toast.LENGTH_LONG).show();
                break;
            case R.id.unbindservice_bt:
                unbindService(connection);
                break;
            case R.id.show_tv:
                Toast.makeText(MainActivity.this,"count=="+myBInder.getCount()+"",Toast.LENGTH_LONG).show();
                show_tv.setText(myBInder.getCount()+"");
                break;
            default:
                break;
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

    }

//    public int[][] getArrayJson(String str){
//        Log.e("xsm","11111111111111");
//        JSONObject json = json = JSONObject.get(str);
//        int[][] arrar2 = null;
//
//        JSONArray json1= null;//解析出来的一维数组
//
//        try {
//            json1 = json.getJSONArray(str);
//
//        Log.e("xsm","json1.length()=="+json1.length());
//            arrar2=new int[json1.length()][];
//            for (int i=0;i<json1.length();i++){
//                JSONArray json2= json1.getJSONArray(i);
//                for (int j=0;j<json2.length();j++){
//                    Log.e("xsm","json2.length()=="+json2.length());
//                    arrar2[json1.length()][j] = json2.getInt(j);
//                    Log.e("xsm",arrar2[json1.length()][j]+"");
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return arrar2;
//    }
}
