package com.bk.soft.servertest;

/**
 * <pre>
 *     author : yyh
 *     time :  2019/4/10 14:18
 *     version: 1.0
 *     desc   : 描述:自定义的MyBinder接口用于保护服务中不想让外界访问的方法。
 * </pre>
 */
public interface ImyBinder {
    void invokeMethodInMyService();
}
