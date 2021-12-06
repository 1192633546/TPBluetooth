package com.tpnet.tpbluetooth.receiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tpnet.tpbluetooth.BluetoothUtils;
import com.tpnet.tpbluetooth.inter.BlueStateListener;

import java.util.Set;

/**
 * 接受系统蓝牙广播
 */

public class BlueStateReceiver extends BroadcastReceiver {
    public static final String TAG = "BlueStateReceiver";

    private BlueStateListener listener;

    public BlueStateReceiver(BlueStateListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e(TAG, "onReceive: " + action);
        switch (action) {
            case BluetoothDevice.ACTION_ACL_DISCONNECTED: {
                if (listener != null) {
                    listener.onDisconnected();
                }
            }
            break;
            case BluetoothDevice.ACTION_ACL_CONNECTED: {
                BluetoothDevice bluetoothDevice=act_connected();
                if (listener != null) {
                    listener.onConnected(bluetoothDevice);
                }

            }
            break;
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                switch (state) {
                    case BluetoothAdapter.STATE_ON:
                        Log.e(TAG, "蓝牙开启了");
                        if (listener != null) {
                            listener.onOpen();
                        }
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        Log.e(TAG, "蓝牙关闭了");
                        if (listener != null) {
                            listener.onClose();
                        }
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.e(TAG, "蓝牙正在开启中。。。");
                        if (listener != null) {
                            listener.onOpening();
                        }

                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.e(TAG, "蓝牙正在关闭中。。。");
                        if (listener != null) {
                            listener.onClosing();
                        }
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        Log.e(TAG, "蓝牙已经连接上设备");
                        if (listener != null) {
                            listener.onConnected();
                        }

                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Log.e(TAG, "蓝牙正在连接设备中。。。");
                        if (listener != null) {
                            listener.onConnecting();
                        }
                        break;
                    case BluetoothAdapter.STATE_DISCONNECTED:
                        Log.e(TAG, "蓝牙断开连接了");
                        if (listener != null) {
                            listener.onDisconnected();
                        }
                        break;
                    case BluetoothAdapter.STATE_DISCONNECTING:
                        Log.e(TAG, "蓝牙正在断开连接中。。。");
                        if (listener != null) {
                            listener.onDisconnecting();
                        }
                        break;

                }
                break;
        }

    }

    private BluetoothDevice act_connected() {
        Set<BluetoothDevice> bondSet = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        BluetoothDevice bluetoothDevice = null;
        for (BluetoothDevice device : bondSet) {
            boolean connected = BluetoothUtils.isConnected(device);
            if (connected) {
                Log.e(TAG, "onReceive: name===" + device.getName() + ",isConnected==" + connected);
                bluetoothDevice = device;
                break;
            }
        }
        return bluetoothDevice;

    }

}
