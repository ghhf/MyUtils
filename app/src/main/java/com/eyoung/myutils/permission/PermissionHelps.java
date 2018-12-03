package com.eyoung.myutils.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.List;

/**
 * Author: created by ghappy on 2018/11/8 13:27
 * <p>
 * Description: 权限管理 封装  实现方式：反射+注解
 */
public class PermissionHelps {

    private Object mObject;
    private int mRequestCode;
    private String[] mRequestPermission;

    public PermissionHelps(Object object) {
        this.mObject = object;
    }

    // 2.已什么的方式传参数
    // 2.1 直接传参数
//    public static void  requestPermission(Activity activity,int requestCode,String[] permissions){
//        PermissionHelps.with(activity).requestCode(requestCode).
//                requestPermission(permissions).request();
//    }
//
//    public static void  requestPermission(Fragment fragment, int requestCode, String[] permissions){
//        PermissionHelps.with(fragment).requestCode(requestCode).
//                requestPermission(permissions).request();
//    }

    // 2.2 链式的方式传
    // 传Activity
    public static PermissionHelps with(Activity activity){
        return new PermissionHelps(activity);
    }

    // 传Fragment
    public static PermissionHelps with(Fragment fragment){
        return new PermissionHelps(fragment);
    }

    // 添加一个请求码
    public PermissionHelps requestCode(int requestCode){
        this.mRequestCode = requestCode;
        return this;
    }

    // 添加请求的权限数组
    public PermissionHelps requestPermission(String... permissions){
        this.mRequestPermission = permissions;
        return this;
    }

    /**
     * 3.1 真正判断和发起请求权限
     */
//    public void request() {
//        // 3.2 首先判断当前的版本是不是6.0 及以上
//        if(!PermissionUtils.isOverMarshmallow()){
//            // 3.3 如果不是6.0以上  那么直接执行方法   反射获取执行方法
//            // 执行什么方法并不确定 那么我们只能采用注解的方式给方法打一个标记，
//            // 然后通过反射去执行。  注解 + 反射  执行Activity里面的callPhone
//            PermissionUtils.executeSucceedMethod(mObject,mRequestCode);
//            return;
//        }
//
//        // 3.3 如果是6.0以上  那么首先需要判断权限是否授予
//        // 需要申请的权限中 获取没有授予过得权限
//        List<String> deniedPermissions = PermissionUtils.getDeniedPermissions(mObject,mRequestPermission);
//
//        // 3.3.1 如果授予了 那么我们直接执行方法   反射获取执行方法
//        if(deniedPermissions.size() == 0){
//            // 全部都是授予过的
//            PermissionUtils.executeSucceedMethod(mObject,mRequestCode);
//        }else {
//            // 3.3.2 如果没有授予 那么我们就申请权限  申请权限
//            ActivityCompat.requestPermissions(PermissionUtils.getActivity(mObject),
//                    deniedPermissions.toArray(new String[deniedPermissions.size()]),
//                    mRequestCode);
//        }
//    }
    /**
     * 检查所有权限是否都已授权
     * @param context
     * @param permissions
     * @return
     */
//    private boolean checkPermissions(Context context,String[] permissions) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            return true;
//        }
//
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(context, permission) !=
//                    PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }

}
