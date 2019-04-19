package com.bk.soft.servertest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bk.soft.servertest.test.MyExceptionHanlder;

public class MainActivity extends AppCompatActivity {
    private ImyBinder imyBinder;
    private Intent intent;
    private MyCoon coon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册未caught异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHanlder(this));
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 启动服务的点击事件
     *
     * @param view
     */
    public void start(View view) {
        intent = new Intent(this,MyServer.class);
        coon = new MyCoon();
        //绑定服务，
        // 第一个参数是intent对象，表面开启的服务。
        // 第二个参数是绑定服务的监听器
        // 第三个参数一般为BIND_AUTO_CREATE常量，表示自动创建bind
        bindService(intent,coon,BIND_AUTO_CREATE);
    }

    /**
     * 调用服务的点击事件
     *
     * @param view
     */
    public void invoke(View view) {

        imyBinder.invokeMethodInMyService();
    }

    private void initViews() {

    }
    private class MyCoon implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //iBinder为服务里面onBind()方法返回的对象，所以可以强转为IMyBinder类型
            imyBinder = (ImyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
