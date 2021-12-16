package com.tpnet.tpbluetooth.net;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.google.gson.Gson;
import com.tpnet.tpbluetooth.BluetoothUtils;
import com.tpnet.tpbluetooth.net.upload.DataDetailBean;
import com.tpnet.tpbluetooth.net.upload.UploadDataBean;

import java.util.ArrayList;
import java.util.List;

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
        /**
         * 00202600000000759e010000000000759e759e0000751375db000010008a160000000000000000e703000000000000000000000000000000000000000000000000000000401c4500803b4500007a4400751e4700751e4700803b45acb1314500000000b4a2913b000000000000000000000000000000000000000080853b5c00000041000048420000000000000000000000000000c8420000c84200000000000000000000000000000000000000000000c84200000000000000001bf72770c1c99fdf0000000000000000000000000000000077772740e4b8bf41
         */
        OkhttpManager.getInstance().upload(requestBody);
    }

    public String uploadLog(String data) {
        data = "00202600000000759e010000000000759e759e0000751375db000010008a160000000000000000e703000000000000000000000000000000000000000000000000000000401c4500803b4500007a4400751e4700751e4700803b45acb1314500000000b4a2913b000000000000000000000000000000000000000080853b5c00000041000048420000000000000000000000000000c8420000c84200000000000000000000000000000000000000000000c84200000000000000001bf72770c1c99fdf0000000000000000000000000000000077772740e4b8bf41";
        int intLen = 32;
        int floatLen = 38;
        int version = 18;
        String serialNo = "SY05500000444";
        int fcp = 1;
        String loginid = serialNo;
        return uploadLog(data, floatLen, version, intLen, serialNo, fcp, loginid);
    }


    public String uploadLog(String data, int floatLen, int version, int intLen, String serialNo, int fcp, String loginid) {
//        String data = intStr + floatStr;
        long createTime = System.currentTimeMillis();
        List<DataDetailBean> dataList = new ArrayList<>();
        DataDetailBean dateDetailBean = new DataDetailBean();
        dateDetailBean.setData(data);
        dateDetailBean.setReceiveTime(createTime);
        dateDetailBean.setSlotCountInt(intLen);
        dateDetailBean.setSlotCountFloat(floatLen);
        dateDetailBean.setBlock(IBlock.BLOCK_1);
        dataList.add(dateDetailBean);
        List<List<DataDetailBean>> lists = new ArrayList<>();
        lists.add(dataList);
        UploadDataBean bean = new UploadDataBean();
        bean.setCreateTime(createTime);
        bean.setGprsFloatLeng(floatLen);
        bean.setGprsIntLeng(intLen);
        bean.setGprsVersion(version);
        bean.setDataList(lists);
        bean.setSerialNo(serialNo);
        bean.setFcp(fcp);
        bean.setLoginId(loginid);
        bean.setBlock(IBlock.BLOCK_1);
        Gson gson = new Gson();
        String dataLog = gson.toJson(bean);
        return dataLog;
    }

    interface IBlock {
        int BLOCK_0 = 0;
        int BLOCK_1 = 1;
        int BLOCK_2 = 2;
        int BLOCK_3 = 3;
        int BLOCK_4 = 4;
    }


}