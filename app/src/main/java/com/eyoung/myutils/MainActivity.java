package com.eyoung.myutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eyoung.myutils.deviceInfo.DeviceInfoUtil;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.text);
        tv.setText("app使用流量 = " + DeviceInfoUtil.getCurAppFlow(MainActivity.this) +
                "\n设备使用流量 = " + DeviceInfoUtil.getDeviceFlow(MainActivity.this) +
                "\nuid = " + DeviceInfoUtil.getUid(MainActivity.this) +
                "\nandroidID = " + DeviceInfoUtil.getAndroidId(MainActivity.this) +
                "\nImei = " + DeviceInfoUtil.getDeviceImei(MainActivity.this) +
                "\nIMSi = " + DeviceInfoUtil.getDeviceImsi(MainActivity.this) +
                "\nBrand = " + DeviceInfoUtil.getDeviceBrand() +
                "\n厂商 = " + DeviceInfoUtil.getDeviceManufacturer() +
                "\n设备型号 = " + DeviceInfoUtil.getDeviceModel() +
                "\nproduct = " + DeviceInfoUtil.getDeviceProduct()
        );
        // test commit git
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
