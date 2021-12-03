package com.tpnet.tpbluetooth.inter;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;

/**
 * 连接服务相关的回调接口
 * Created by litp on 2017/6/1.
 */

 
@SuppressLint("NewApi")
public interface  BlueServerListener {

    default void onStartListener(){};

    default void onListenering(){};

    default void onFinishListener(){};

    default void onServerError(Exception e){};

    default void onGetClient(BluetoothDevice text){};

    default void onRemoveClient(BluetoothDevice device){};

    default void onCloseClient(BluetoothDevice device){};

}
