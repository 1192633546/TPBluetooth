package com.tpnet.tpbluetooth.net;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.tpnet.tpbluetooth.BluetoothUtils;

public class NetManager {
    public static final String TAG = "NetManager";
    private static NetManager instance;
    public String url;

    private NetManager() {
    }

    public static NetManager getInstance() {
        if (instance == null) {
            instance = new NetManager();
        }
        return instance;
    }

    public void initUrl(String url) {
        this.url = url;
        OkhttpManager.getInstance().init(url);

        BluetoothDevice device = BluetoothUtils.getConnectedBtDevice();
        if (device != null) {
            Log.e(TAG, "initUrl: device==" + device);
        } else {
            Log.e(TAG, "initUrl: device==" + device);
        }
        BluetoothConnectManager.getInstance().connect(device);
    }

    public void upLoadData(String requestBody) {
        OkhttpManager.getInstance().upload(requestBody);
    }
}