package com.tpnet.tpbluetooth.net;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.tpnet.tpbluetooth.EIBackHaulBluetooth;
import com.tpnet.tpbluetooth.event.ReveiverEvent;
import com.tpnet.tpbluetooth.inter.BlueBondListener;
import com.tpnet.tpbluetooth.inter.BlueClientListener;
import com.tpnet.tpbluetooth.inter.BlueFindListener;
import com.tpnet.tpbluetooth.inter.BlueMessageListener;
import com.tpnet.tpbluetooth.inter.BlueStateListener;

import org.greenrobot.eventbus.EventBus;

/**
 * 蓝牙连接管理类
 * 当蓝牙连接成功后上传数据
 * 00
 * 99
 */
public class BluetoothConnectManager {
    public static final String TAG = "BluetoothConnectManager";
    private static BluetoothConnectManager instance;
    private EIBackHaulBluetooth mBlueControl;

    private BluetoothConnectManager() {
        initClassisBt();
        setListener();
    }

    public static BluetoothConnectManager getInstance() {
        if (instance == null) {
            instance = new BluetoothConnectManager();
        }
        return instance;
    }

    /**
     * 初始化蓝牙
     */
    private void initClassisBt() {
        mBlueControl = EIBackHaulBluetooth.getInstance();
        //初始化传统蓝牙模式
        mBlueControl.initClassicBluetooth();
    }

    /**
     * 连接
     */
    public void connect(BluetoothDevice device) {
        Log.e(TAG, "connect: ");
        if (device != null) {
            //连接服务器
            //mBlueControl.disConnect();
            mBlueControl.connect(device);
        }
    }

    private void retryConnect(BluetoothDevice device) {
        Log.e(TAG, "retryConnect: " );
        connect(device);
    }

    private void setListener() {
        setOnClientListener();
        setOnMessageListener();
        setOnBlueFindListener();
        setOnBlueBondListener();
        setOnBlueStateListener();
    }

    /**
     * 连接回调监听
     */
    private void setOnClientListener() {
        mBlueControl.setOnClientListener(new BlueClientListener() {
            @Override
            public void onStartConnect() {
                super.onStartConnect();
                Log.e(TAG, "onStartConnect: 正在连接");
            }

            @Override
            public void onFinishConnect() {
                super.onFinishConnect();
                Log.e(TAG, "onFinishConnect: 已连接");

            }

            @Override
            public void onConnecting() {
                super.onConnecting();
                Log.e(TAG, "onConnecting: 已连接");

            }

            @Override
            public void onClientError(Exception e) {
                super.onClientError(e);
                Log.e(TAG, "onClientError: 连接失败" + e.getMessage());
            }
        });
    }

    /**
     * 数据回调监听
     */
    private void setOnMessageListener() {
        mBlueControl.setOnMessageListener(new BlueMessageListener() {
            @Override
            public void onReceiveMessage(BluetoothDevice device, String data) {
                Log.e(TAG, "onReceiveMessage: t==" + Thread.currentThread().getName());
                Log.e(TAG, "onReceiveMessage: " + data);
                NetManager.getInstance().upLoadData(data);
                ReveiverEvent event=new ReveiverEvent(data);
                EventBus.getDefault().post(event);
            }
        });
    }

    /**
     * 状态监听
     */
    private void setOnBlueStateListener() {
        mBlueControl.setOnBlueStateListener(new BlueStateListener() {
            @Override
            public void onOpen() {
                Log.e(TAG, "onOpen: ");
            }

            @Override
            public void onClose() {
                Log.e(TAG, "onClose: ");

            }

            @Override
            public void onConnected() {
                Log.e(TAG, "onConnected: ");
            }

            @Override
            public void onConnected(BluetoothDevice device) {
                Log.e(TAG, "onConnected: " + device);
                retryConnect(device);
            }

            @Override
            public void onDisconnected() {
                //断开连接服务器
                Log.e(TAG, "onDisconnected: ");
            }
        });

    }


    /**
     * 绑定监听
     */
    void setOnBlueBondListener() {
        mBlueControl.setOnBlueBondListener(new BlueBondListener() {
            @Override
            public void onBonded(BluetoothDevice device) {
                super.onBonded(device);
                Log.e(TAG, "onBonded: 配对成功" + device.getName());
            }

            @Override
            public void onBonding(BluetoothDevice device) {
                super.onBonding(device);
                Log.e(TAG, "onBonding: " + "正在和" + device.getName() + "进行配对");
            }

            @Override
            public void onCancleBond(BluetoothDevice device) {
                super.onCancleBond(device);
                Log.e(TAG, "onCancleBond: 取消配对");

            }
        });

    }

    /**
     * 发现监听
     */
    void setOnBlueFindListener() {
        mBlueControl.setOnBlueFindListener(new BlueFindListener() {
            @Override
            public void onStartDiscovery() {
                super.onStartDiscovery();
            }

            @Override
            public void onFinishDiscovery() {
                super.onFinishDiscovery();
            }

            @Override
            public void onFound(BluetoothDevice device) {
                super.onFound(device);
                if (device != null) {
                    Log.e(TAG, "添加到设备列表" + device.getName());
                }
            }

            @Override
            public void onModeConnectableDiscoverable() {
                super.onModeConnectableDiscoverable();

            }

            @Override
            public void onModeConnectable() {
                super.onModeConnectable();

            }

            @Override
            public void onModeClose() {
                super.onModeClose();

            }
        });
    }
}