package com.tpnet.tpbluetooth.inter;

import android.bluetooth.BluetoothDevice;

/**
 * 蓝牙状态监听器
 * Created by litp on 2017/6/1.
 */

public interface  BlueStateListener {


    default void onOpen(){};

    default void onClose(){};

    default void onOpening(){};

    default void onClosing(){};

    default void onConnected(){};
    default void onConnected(BluetoothDevice device){};

    default void onConnecting(){};

    default void onDisconnected(){};

    default void onDisconnecting(){};

}
