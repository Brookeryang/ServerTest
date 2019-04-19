//package com.bk.soft.servertest;
//
//import android.content.Context;
//
//import com.bk.soft.servertest.test.AppAction;
//
//import java.lang.Thread.UncaughtExceptionHandler;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//
//import static java.time.chrono.ThaiBuddhistChronology.INSTANCE;
//
///**
// * <pre>
// *     author : yyh
// *     time :  2019/4/11 15:15
// *     version: 1.0
// *     desc   : 描述XXXX
// * </pre>
// */
//public class CrashHandler implements UncaughtExceptionHandler {
//    public static final String TAG = "CrashHandler";
//    // CrashHandler 实例
//    private static CrashHandler INSTANCE = new CrashHandler();
//    // 程序的 Context 对象
//    private Context mContext;
//    private AppAction app;
//    // 系统默认的 UncaughtException 处理类  
//    private Thread.UncaughtExceptionHandler mDefaultHandler;
//    // 用来存储设备信息和异常信息
//    private Map<String, String> infos = new HashMap<String, String>();
//    // 用于格式化日期,作为日志文件名的一部分
//    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//
//    /**
//     * 保证只有一个 CrashHandler 实例
//     */
//    private CrashHandler() {
//    }
//
//    /**
//     * 获取 CrashHandler 实例 ,单例模式
//     */
//    public static CrashHandler getInstance() {
//        return INSTANCE;
//    }
//
//    public void init(Context context, AppAction app) {
//        // 传入app对象，为完美终止app
//        this.app = app;
//        mContext = context;
//        // 获取系统默认的 UncaughtException 处理器
//        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
//        // 设置该 CrashHandler 为程序的默认处理器
//        Thread.setDefaultUncaughtExceptionHandler(this);
//
//    }
//    /**
//     * 当 UncaughtException 发生时会转入该函数来处理
//     */
//    @Override
//    public void uncaughtException(Thread t, Throwable e) {
//        if (!handleException(e) && mDefaultHandler != null) {
//            // 如果用户没有处理则让系统默认的异常处理器来处理
//           mDefaultHandler.uncaughtException(t, e);
//    }else {
//         // 释放资源不能像常规的那样在activity的onDestroy方法里面执行，因为如果出现全局异常捕获，activity的关闭有时候是不会再走相关的生命周期函数的（onDesktroy,onStop,onPause等）。  
//        // 这里是博主在退出app之前需要释放掉的一些资源，通过之前讲的AppActivityManager来拿到对应的实例activity释放里面的资源，然后调用AppExit退出应用程序  
//            Ma activitys = (ProductActivity) AppActivityManager  
//                    .getAppActivityManager().getActivity(ProductActivity.class);  
//            activitys.offline(); 
//        }
//    }
//
//
//
//}
