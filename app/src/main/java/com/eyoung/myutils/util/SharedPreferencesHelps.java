package com.eyoung.myutils.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eyoung.myutils.constant.BaseConfig;

/**
 * Author: created by ghappy on 2018/11/7 15:25
 * <p>
 * Description:
 */
public class SharedPreferencesHelps {
    private static SharedPreferences preferences = null;

    private static SharedPreferences getPreferences(Context context) {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return preferences;
    }

    // 获取手机流量
    public synchronized static float getAppFlow(Context context) {
        return getPreferences(context).getFloat(BaseConfig.APP_FLOW, 0.0f);
    }

    // 保存手机流量
    public synchronized static void setAppFlow(Context context,float appFlow) {
        getPreferences(context).edit().putFloat(BaseConfig.APP_FLOW, appFlow).apply();
    }

    // 获取手机流量
    public synchronized static float getDeviceFlow(Context context) {
        return getPreferences(context).getFloat(BaseConfig.APP_FLOW, 0.0f);
    }

    // 保存手机流量
    public synchronized static void setDeviceFlow(Context context,float deviceFlow) {
        getPreferences(context).edit().putFloat(BaseConfig.APP_FLOW, deviceFlow).apply();
    }

}
