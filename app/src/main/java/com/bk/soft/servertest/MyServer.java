package com.bk.soft.servertest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * <pre>
 *     author : yyh
 *     time :  2019/4/10 14:15
 *     version: 1.0
 *     desc   : 描述XXXX
 * </pre>
 */
public class MyServer extends Service {

    public MyServer() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        //返回MyBinder对象
        return new MyBinder();
    }

    /**
     * 该类用于在onBind方法执行后返回的对象，
     * 该对象对外提供了该服务里的方法
     */
    public class MyBinder extends Binder implements ImyBinder {

        @Override
        public void invokeMethodInMyService() {
            methodInMyService();
        }
    }

    private void methodInMyService() {


        Toast.makeText(getApplicationContext(), "服务里的方法执行了。。。",
                Toast.LENGTH_SHORT).show();
    }
}
