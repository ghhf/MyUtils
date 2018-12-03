package com.eyoung.myutils.deviceInfo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.TrafficStats;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.eyoung.myutils.util.SharedPreferencesHelps;
import com.eyoung.myutils.util.TimeUtils;

import static android.content.Context.NETWORK_STATS_SERVICE;

/**
 * Author: created by ghappy on 2018/11/7 13:41
 * <p>
 * Description: 获取手机信息工具类
 */
public class DeviceInfoUtil {


    /**
     * 手机唯一标识码
     *
     * @param context
     */
    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

    }

    @SuppressLint("WrongConstant")
    public static int getUid(Context context) {
        PackageManager pm = context.getPackageManager();
        ApplicationInfo ap = null;
        try {
            ap = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return ap.uid;
    }

    /**
     * 获取该应用使用的流量  开机后使用的流量
     * <p>
     * <p>
     * // TrafficStats 获取的是 手机开机后到获取流量 这段时间所使用的流量 不是本月的流量
     *
     * @param context
     */
    public static float getAppFlow(Context context) {
        long tx = TrafficStats.getUidTxBytes(getUid(context)); // 获取指定uid的接收字节数
        long rx = TrafficStats.getUidRxBytes(getUid(context)); // 获取指定uid的发送字节数

//        long tx = TrafficStats.getUidTxPackets(getUid(context)); // 手机网络上传的总流量 包括wifi
//        long rx = TrafficStats.getUidRxPackets(getUid(context)); // 手机网络下载的总流量 包括wifi
        return (tx + rx) / 1024f / 1024f;
    }

    /**
     * 获取该手机使用的流量 不包括wifi
     * 开机后使用的流量
     *
     * @param context
     */
    public static float getDeviceFlow(Context context) {
        long tx = TrafficStats.getMobileTxBytes(); // 手机2g/3g/4g网络上传的总流量
        long rx = TrafficStats.getMobileRxBytes(); // 手机2g/3g/4g网络下载的总流量

//        long tx = TrafficStats.getTotalTxBytes(); // 手机网络上传的总流量 包括wifi
//        long rx = TrafficStats.getTotalRxBytes(); // 手机网络下载的总流量 包括wifi

        return (tx + rx) / 1024f / 1024f;
    }

    /**
     * 获取本月应用使用的数据流量
     *
     * @param context
     * @return
     */
    public static float getCurAppFlow(Context context) {

        float appFlow = 0.0f;

        Log.e("appFlow", "uid:" + getUid(context));

        // todo 6.0之上
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.PACKAGE_USAGE_STATS)
                    == PackageManager.PERMISSION_GRANTED) {

                NetworkStatsManager networkStatsManager = (NetworkStatsManager) context
                        .getSystemService(NETWORK_STATS_SERVICE);

                NetworkStats.Bucket bucket = new NetworkStats.Bucket();

                try {
                    NetworkStats networkStats = networkStatsManager
                            .queryDetailsForUid(ConnectivityManager.TYPE_MOBILE, "",
                                    TimeUtils.getTimesMonthMorning(),
                                    System.currentTimeMillis(), getUid(context));

                    do {
                        networkStats.getNextBucket(bucket);
                        int summaryUid = bucket.getUid();
                        if (getUid(context) == summaryUid) {

                            appFlow = bucket.getRxBytes() + bucket.getTxBytes();

                        }
                        Log.i("SDK>6.0 appFlow", "uid:" + bucket.getUid() + " rx:" + bucket.getRxBytes() +
                                " tx:" + bucket.getTxBytes());

                    } while (networkStats.hasNextBucket());

                } catch (Exception e) {
                    Log.i("SDK>5.0 appFlow", "为获取到流量信息");
                    return -1;

                }
            } else {

                getAppFlow(context);
                Log.i("SDK<5.0 appFlow", "无权限，无法获取应用流量信息");

            }

        } else {

            appFlow = getAppFlow(context);

            if (SharedPreferencesHelps.getAppFlow(context) < appFlow) {
                //说明用户手机关机过
                appFlow = appFlow + SharedPreferencesHelps.getAppFlow(context);
                Log.i("SDK<5.0 appFlow", "数据流量" + getAppFlow(context));
            }
        }

        return appFlow;
    }

    /**
     * 获取本月手机使用的数据流量
     *
     * @param context
     * @return
     */
    public static float getCurDeviceFlow(Context context) {

        float deviceFlow = 0.0f;

        // 6.0之上
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED) {

                NetworkStatsManager networkStatsManager = (NetworkStatsManager) context
                        .getSystemService(NETWORK_STATS_SERVICE);

                NetworkStats.Bucket bucket = new NetworkStats.Bucket();

                try {
                    NetworkStats networkStats = networkStatsManager
                            .querySummary(ConnectivityManager.TYPE_MOBILE, "",
                                    TimeUtils.getTimesMonthMorning(),
                                    System.currentTimeMillis());

                    do {
                        networkStats.getNextBucket(bucket);
                        int summaryUid = bucket.getUid();
                        if (getUid(context) == summaryUid) {

                            deviceFlow = bucket.getRxBytes() + bucket.getTxBytes();

                        }
                        Log.i("SDK>5.0 DeviceFlow", "uid:" + bucket.getUid() + " rx:" + bucket.getRxBytes() +
                                " tx:" + bucket.getTxBytes());

                    } while (networkStats.hasNextBucket());

                } catch (Exception e) {
                    Log.i("SDK>5.0 DeviceFlow", "为获取到流量信息");
                    return -1;

                }
            } else {
                Log.i("SDK>5.0 DeviceFlow", "无权限，无法获取应用流量信息");

            }

        } else {

            deviceFlow = getDeviceFlow(context);

            if (SharedPreferencesHelps.getDeviceFlow(context) < deviceFlow) {
                //说明用户手机关机过
                deviceFlow = deviceFlow + SharedPreferencesHelps.getDeviceFlow(context);
                Log.i("SDK<5.0 DeviceFlow", "数据流量" + getDeviceFlow(context));
            }
        }

        return deviceFlow;
    }

    /**
     * 获取厂商品牌
     *
     * @return
     */
    public static String getDeviceManufacturer() {
        return Build.BOARD + Build.MANUFACTURER;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getDeviceProduct() {
        return Build.PRODUCT;
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取品牌 手机型号
     *
     * @return
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机imei
     *
     * @return
     */
    public static String getDeviceImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // 没有权限 返回空
            return "无查看权限";
        }
        return telephonyManager.getDeviceId();
    }

    /**
     * IMSI
     *
     * @param context
     * @return
     */
    public static String getDeviceImsi(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // 没有权限 返回空
            return "无查看权限";
        }
        return telephonyManager.getSubscriberId();
    }

    /**
     * 获取手机服务商信息
     *
     * @param context
     * @return
     */
    public static String getProviderName(Context context) {
        String providerName = "";

        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        String imsi = getDeviceImsi(context);

        if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
            providerName = "中国移动";

        } else if (imsi.startsWith("46001")) {
            providerName = "中国联通";

        } else if (imsi.startsWith("46003")) {
            providerName = "中国电信";

        }

        return providerName;
    }

    /**
     * 获取手机系统版本号 Android5.0
     * @return
     */
    public static String getAndroidVersion() {

        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return
     */
    public static long getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                return manager.getPackageInfo(context.getPackageName(), 0).getLongVersionCode();
            } else {
                return manager.getPackageInfo(context.getPackageName(), 0).versionCode;

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取应用版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            return manager.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "null";
        }
    }
}
