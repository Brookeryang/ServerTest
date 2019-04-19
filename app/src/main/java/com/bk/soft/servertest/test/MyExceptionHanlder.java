package com.bk.soft.servertest.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * <pre>
 *     author : yyh
 *     time :  2019/4/11 14:41
 *     version: 1.0
 *     desc   : 描述XXXX
 * </pre>
 */
public class MyExceptionHanlder implements UncaughtExceptionHandler {
    private Context mContext = null;

    public MyExceptionHanlder(Context context) {
        this.mContext = context;
        // this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang
     * .Thread, java.lang.Throwable)
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (ex != null) {
            Log.i("UncaughtException", ex.getMessage());
            StringBuilder sb = new StringBuilder();
            String temp = ex.getMessage();
            if (temp != null) {
                sb.append(temp);
            }
            sb.append("\r\n");
            sb.append(thread.getName());
            sb.append(" Trace: \r\n");
            StackTraceElement[] elements = ex.getStackTrace();
            if (elements != null) {
                for (StackTraceElement element : elements) {
                    temp = element.toString();
                    if (temp != null) {
                        sb.append(temp);
                    }
                    sb.append("\r\n");
                }
            }

            // if the exception was thrown in a background thread inside
            // AsyncTask, then the actual exception can be found with getCause
            sb.append("Cause: ");
            Throwable theCause = ex.getCause();
            if (theCause != null) {
                temp = theCause.toString();
                if (temp != null) {
                    sb.append(temp);
                }
            }
            sb.append("\r\nCause Stack:\r\n");
            theCause = ex.getCause();
            if (theCause != null) {
                elements = theCause.getStackTrace();
                if (elements != null) {
                    for (StackTraceElement element : elements) {
                        temp = element.toString();
                        if (temp != null) {
                            sb.append(temp);
                        }
                        sb.append("\r\n");
                    }
                }
            }
            // you can write this content to file if necessary

            Intent intent = new Intent(mContext, ReportPage.class);
            intent.putExtra(ReportPage.REPORT_CONTENT,
                    sb.toString());
            mContext.startActivity(intent);
            crash(mContext);

        }
    }

    public void uncaughtException(Exception e) {
        uncaughtException(Thread.currentThread(), e);
    }

    private boolean crash(Context context) {
        if (context == null) {
            return false;
        }

        if (context instanceof Activity) {
            ((Activity) context).finish();
        }

        Process.killProcess(Process.myPid());
        return true;
    }
}
