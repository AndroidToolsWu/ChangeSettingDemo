package com.alan.changesettingdemo.internet_test;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alan.changesettingdemo.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;

/**
 * Created by Alan
 * Date: 2020/3/3
 */
public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    private RxPermissions mRxPermissions;
    private String[] permissionStr = {Manifest.permission.ACCESS_COARSE_LOCATION , Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        mRxPermissions = new RxPermissions(this);
        Disposable disposable = mRxPermissions
                .request(permissionStr)
                .subscribe( granted ->{
                    if (granted){
                        requestNet();
                    }
                });


    }

    private void requestNet(){
        InternetUtils.isNetWorkConnected(this);
        Log.d(TAG, "onCreate: network type" + InternetUtils.getNetWorkType(this));
        if (InternetUtils.getNetWorkType(this) == InternetUtils.TYPE_WIFI){
            Log.d(TAG, "onCreate: getConnectedWifiInfo " + InternetUtils.getConnectedWifiInfo(this));
            Log.d(TAG, "onCreate: getWifiRssi" + InternetUtils.getWifiRssi(this));
            Log.d(TAG, "onCreate: getWifiSignalLevel" + InternetUtils.getWifiSignalLevel(this));

        }else if (InternetUtils.getNetWorkType(this) == InternetUtils.TYPE_MOBILE){
            Log.d(TAG, "onCreate: getMobileDbm" +  InternetUtils.getMobileDbm(this));
            Log.d(TAG, "onCreate: getMobileSignalLevel" +  InternetUtils.getMobileSignalLevel(this));
            Log.d(TAG, "requestNet:  getMobileImei " + InternetUtils.getMobileImei(this));
            Log.d(TAG, "requestNet:  getMobileInfo " + InternetUtils.getMobileInfo(this));

        }
    }

}
