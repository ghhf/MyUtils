package com.eyoung.myutils.permission;

import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Author: created by ghappy on 2018/11/8 13:42
 * <p>
 * Description:
 */
class PermissionUtils {

    /**
     * 执行成功的方法
     */
//    public static void executeSucceedMethod(Object reflectObject, int requestCode) {
//        // 获取class中多有的方法
//        Method[] methods = reflectObject.getClass().getDeclaredMethods();
//
//        // 遍历找我们打了标记的方法
//        for (Method method:methods){
//            Log.e("TAG",method+"");
//            // 获取该方法上面有没有打这个成功的标记
//            PermissionSucceed succeedMethod =  method.getAnnotation(PermissionSucceed.class);
//            if(succeedMethod != null){
//                // 代表该方法打了标记
//                // 并且我们的请求码必须 requestCode 一样
//                int methodCode = succeedMethod.requestCode();
//                if(methodCode == requestCode){
//                    // 这个就是我们要找的成功方法
//                    // 反射执行该方法
//                    Log.e("TAG","找到了该方法 ："+method);
//                    executeMethod(reflectObject,method);
//                }
//            }
//        }
//    }
//
//    /**
//     * 反射执行该方法
//     */
//    private static void executeMethod(Object reflectObject,Method method) {
//        // 反射执行方法  第一个是传该方法是属于哪个类   第二个参数是反射方法的参数
//        try {
//            method.setAccessible(true); // 允许执行私有方法
//            method.invoke(reflectObject,new Object[]{});
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static boolean isOverMarshmallow() {
//        return false;
//    }
//
//    private static class PermissionSucceed {
//    }
}
