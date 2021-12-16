package com.tpnet.bluedemo;

import android.app.Application;
import android.content.pm.PackageManager;

import com.tpnet.tpbluetooth.EIBackHaulBluetooth;


/**
 *
 */

public class BaseApplication extends Application {

    private static BaseApplication application;

    public static BaseApplication getInstance() {
        return application;
    }

    /**
     * 正式
     */
    public static final String LAUNCHER_ONLINE = "https://eviapp.sanyevi.cn/eiapp/official/";
    /**
     * 测试
     */
    public static final String LAUNCHER_TEST = "https://eviapptest.sanyevi.cn/eiapp/";
    public static final String UPLOAD_PATH = "AppV1/wkData/upload/block/uncheck";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //初始化蓝牙控制器
        String url = LAUNCHER_TEST + UPLOAD_PATH;
        EIBackHaulBluetooth.init(this, url);
    }


    //获取版本号
    public static int getVersionCode() {
        try {
            return application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

}