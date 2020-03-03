package com.alan.changesettingdemo.internet_test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Alan
 * Date: 2020/3/2
 */

@SuppressWarnings("WeakerAccess")
@SuppressLint({"MissingPermission", "ObsoleteSdkInt"})
public class InternetUtils {

    /**
     * Network Type
     */
    public static final int TYPE_NONE = -1;
    public static final int TYPE_WIFI = 0;
    public static final int TYPE_MOBILE = 1;
    public static final int TYPE_ETHERNET = 2;
    private static final String TAG = "InternetUtils";

    /**
     * 判断网络是否连接
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }

    /**
     * 返回网络类型 none wifi mobile（移动网络） ethernet(以太网)
     *
     * @param context
     * @return
     */
    public static int getNetWorkType(Context context) {
        int internetType = TYPE_NONE;
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info == null || !info.isAvailable()) {
                return internetType;
            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                internetType = TYPE_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                internetType = TYPE_MOBILE;
            } else if (info.getType() == ConnectivityManager.TYPE_ETHERNET) {
                internetType = TYPE_ETHERNET;
            }
        }
        return internetType;
    }

    /**
     * 开关移动网络连接 (仅限SDK_INT > 26)
     * android.Manifest.permission.MODIFY_PHONE_STATE
     */
    public static void changeMobileNetWorkState(Context context, boolean enable) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) telephonyManager.setDataEnabled(enable);
    }

    /**
     * 开关wifi连接
     * need permissions android.permission.CHANGE_WIFI_STATE
     *
     */
    public static void changeWifiNetWorkState(Context context, boolean enable) {
        WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        manager.setWifiEnabled(enable);
    }


    /**
     * 获取网络运营商信息
     */
    public static String getMobileInfo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSimOperatorName();
    }

    /**
     * 获取设备唯一标识码 IMEI
     * need Permissions Manifest.permission.READ_PHONE_STATE
     */
    public static String getMobileImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return telephonyManager.getImei();
        }
        return "";
    }


    /**
     * 获取移动网络信号强度
     * Get signal level as an int from 0..4
     * need Permissions Manifest.permission.ACCESS_COARSE_LOCATION
     */
    public static int getMobileSignalLevel(Context context) {
        int level = 0;
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
        if (cellInfoList != null) {
            for (CellInfo cellInfo : cellInfoList) {
                if (cellInfo instanceof CellInfoGsm) {
                    //全球通网络
                    CellSignalStrengthGsm strengthGsm = ((CellInfoGsm) cellInfo).getCellSignalStrength();
                    level = strengthGsm.getLevel();
                    Log.d(TAG, "getMobileSignalLevel: Gsm " + level);
                } else if (cellInfo instanceof CellInfoCdma) {
                    //2G网络
                    CellSignalStrengthCdma strengthCdma = ((CellInfoCdma) cellInfo).getCellSignalStrength();
                    level = strengthCdma.getLevel();
                    Log.d(TAG, "getMobileSignalLevel: Cdma " + level);
                } else if (cellInfo instanceof CellInfoWcdma) {
                    //3G网络
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        CellSignalStrengthWcdma strengthWcdma = ((CellInfoWcdma) cellInfo).getCellSignalStrength();
                        level = strengthWcdma.getLevel();
                        Log.d(TAG, "getMobileSignalLevel: Wcdma " + level);
                    }
                } else if (cellInfo instanceof CellInfoLte) {
                    //4G网络
                    CellSignalStrengthLte strengthLte = ((CellInfoLte) cellInfo).getCellSignalStrength();
                    level = strengthLte.getLevel();
                    Log.d(TAG, "getMobileSignalLevel: Lte " + level);
                }
            }
        }
        return level;
    }

    /**
     * 获取移动网络信号强度
     * Get the signal strength as dBm
     * need Permissions Manifest.permission.ACCESS_COARSE_LOCATION
     */
    @SuppressLint("ObsoleteSdkInt")
    public static int getMobileDbm(Context context) {
        int dbm = -1;
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
        if (cellInfoList != null) {
            for (CellInfo cellInfo : cellInfoList) {
                if (cellInfo instanceof CellInfoGsm) {
                    //2G全球通网络
                    CellSignalStrengthGsm strengthGsm = ((CellInfoGsm) cellInfo).getCellSignalStrength();
                    dbm = strengthGsm.getDbm();
                    Log.d(TAG, "getMobileDbm: Gsm " + dbm);
                } else if (cellInfo instanceof CellInfoCdma) {
                    //2G网络
                    CellSignalStrengthCdma strengthCdma = ((CellInfoCdma) cellInfo).getCellSignalStrength();
                    dbm = strengthCdma.getDbm();
                    Log.d(TAG, "getMobileDbm: Cdma " + dbm);
                } else if (cellInfo instanceof CellInfoWcdma) {
                    //3G网络
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        CellSignalStrengthWcdma strengthWcdma = ((CellInfoWcdma) cellInfo).getCellSignalStrength();
                        dbm = strengthWcdma.getDbm();
                        Log.d(TAG, "getMobileDbm: Wcdma " + dbm);
                    }
                } else if (cellInfo instanceof CellInfoLte) {
                    //4G网络
                    CellSignalStrengthLte strengthLte = ((CellInfoLte) cellInfo).getCellSignalStrength();
                    dbm = strengthLte.getDbm();
                }
            }
        }
        return dbm;
    }

    /**
     * 获取Wifi信号等级 0-4 int
     * android.permission.ACCESS_WIFI_STATE
     *
     * @param context
     * @return
     */
    public static int getWifiSignalLevel(Context context) {
        WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        Log.d(TAG, "getWifiSignalLevel: " + WifiManager.calculateSignalLevel(info.getRssi(), 5));
        return WifiManager.calculateSignalLevel(info.getRssi(), 5);
    }

    /**
     * wifi信号强度
     * android.permission.ACCESS_WIFI_STATE
     *
     * @param context
     * @return
     */
    public static int getWifiRssi(Context context) {
        WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        Log.d(TAG, "getWifiRssi: " + info.getRssi());
        return info.getRssi();
    }

    /**
     * 获取已连接的wifi信息
     *
     * @param context
     * @return
     */
    @SuppressLint("HardwareIds")
    public static Map<String, String> getConnectedWifiInfo(Context context) {
        Map<String, String> wifiInfoMap = new HashMap<>();
        WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        if (!TextUtils.isEmpty(info.getSSID())) wifiInfoMap.put("ssid", info.getSSID());
        if (0 != info.getIpAddress()) wifiInfoMap.put("ipAddress", String.valueOf(info.getIpAddress()));
        if (!TextUtils.isEmpty(info.getMacAddress())) wifiInfoMap.put("macAddress", info.getMacAddress());
        wifiInfoMap.put("rssi", String.valueOf(info.getRssi()));
        return wifiInfoMap;
    }


}
