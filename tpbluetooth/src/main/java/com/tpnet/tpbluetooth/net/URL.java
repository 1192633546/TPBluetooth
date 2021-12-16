package com.tpnet.tpbluetooth.net;


/**
 * author: duan
 * date: 2018/12/4 17:01
 * description:
 */
public class URL {
    //https://eviapptest.sanyevi.cn/common/service1/AppV3/server/info?srvIzd=4
    public static final String LAUNCHER_INFO = "AppV3/server/info?srvId=4";
    public static final String LAUNCHER_ONLINE = "https://eviapp.sanyevi.cn/common/service1/" + LAUNCHER_INFO;
    public static final String LAUNCHER_TEST = "https://eviapptest.sanyevi.cn/common/service1/" + LAUNCHER_INFO;
    public static final String LAUNCHER_PRE = "https://eviapptest.sanyevi.cn/preserver/common/service1/" + LAUNCHER_INFO;
    /**
     * 工况数据上传
     */
    public static final String UPLOAD_PATH = "AppV1/wkData/upload/block/uncheck";
}
