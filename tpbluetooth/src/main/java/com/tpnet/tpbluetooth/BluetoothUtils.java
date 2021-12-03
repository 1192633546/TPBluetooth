package com.tpnet.tpbluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * author : dingqb
 * e-mail : dingqb@sany.com.cn
 * date   : 2021/10/28 5:30 PM
 * desc   :
 * version: 1.0
 */
public class BluetoothUtils {
    /**
     * 判断蓝牙是否连接
     *
     * @param device
     * @return
     */
    public static boolean isConnected(BluetoothDevice device) {
        Method isConnectedMethod = null;
        boolean isConnected = false;
        try {
            isConnectedMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", (Class[]) null);
            isConnectedMethod.setAccessible(true);
            isConnected = (boolean) isConnectedMethod.invoke(device, (Object[]) null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return isConnected;

    }

    public static final String TAG = "BluetoothUtils";

    /**
     * 获取已连接的蓝牙设备
     */
    public static BluetoothDevice getConnectedBtDevice() {
        Log.e(TAG, "getConnectedBtDevice: ");
        long t1 = System.currentTimeMillis(), t2;
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Class<BluetoothAdapter> bluetoothAdapterClass = BluetoothAdapter.class;//得到BluetoothAdapter的Class对象
        try {
            //得到连接状态的方法
            Method method = bluetoothAdapterClass.getDeclaredMethod("getConnectionState", (Class[]) null);
            //打开权限
            method.setAccessible(true);
            int state = (int) method.invoke(adapter, (Object[]) null);
            if (state != BluetoothAdapter.STATE_CONNECTED) {
                return null;
            }
            Log.i(TAG, "BluetoothAdapter.STATE_CONNECTED");
            //集合里面包括已绑定的设备和已连接的设备
            Set<BluetoothDevice> devices = adapter.getBondedDevices();
            Log.i(TAG, "devices:" + devices.size());
            for (BluetoothDevice device : devices) {
                Method isConnectedMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", (Class[]) null);
                method.setAccessible(true);
                boolean isConnected = (boolean) isConnectedMethod.invoke(device, (Object[]) null);
                //根据状态来区分是已连接的还是已绑定的，isConnected为true表示是已连接状态。
                if (isConnected) {
                    t2 = System.currentTimeMillis();
                    Log.i(TAG, "connected:name==" + device.getName() + ",dx==" + (t2 - t1));
                    return device;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
