package com.tpnet.tpbluetooth.net;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.google.gson.Gson;
import com.tpnet.tpbluetooth.BluetoothUtils;
import com.tpnet.tpbluetooth.device.DataBeanUtils;
import com.tpnet.tpbluetooth.device.PrimitiveConversion;
import com.tpnet.tpbluetooth.net.upload.DataDetailBean;
import com.tpnet.tpbluetooth.net.upload.UploadDataBean;

import java.util.ArrayList;
import java.util.List;

public class NetManager {
    public static final String TAG = "NetManager";
    private static NetManager instance;

    private NetManager() {
    }

    public static NetManager getInstance() {
        if (instance == null) {
            instance = new NetManager();
        }
        return instance;
    }

    public void initUrl() {
        OkhttpManager.getInstance().init();
        BluetoothDevice device = BluetoothUtils.getConnectedBtDevice();
        if (device != null) {
            Log.e(TAG, "initUrl: device==" + device);
        } else {
            Log.e(TAG, "initUrl: device==" + device);
        }
        BluetoothConnectManager.getInstance().connect(device);
    }

    public void upLoadData(String s) {
        // TODO: 4/15/22
        String requestBody = uploadLog(s);
        OkhttpManager.getInstance().upload(requestBody);
    }

    static byte[] str2Bytes(String s) {
        char[] cc = s.toCharArray();
        byte[] bytes = new byte[cc.length / 2];
        int t = 0;
        for (int i = 0; i < cc.length; i = i + 2) {
            bytes[t] = (byte) (Integer.parseInt(cc[i] + "" + cc[i + 1], 16) & 0xff);
            t++;
        }
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
        return bytes;
    }

    private String uploadLog(String s) {
        byte[] data = str2Bytes(s);
        DataBeanUtils utils = new DataBeanUtils(data);
        String serialNo = utils.getSerialNo();
        String test = "SY03650000444";
//        serialNo = test;
        int intLen = utils.getIntLen();
        int floatLen = utils.getFloatLen();
        int version = utils.getVersion();
        int fcp = utils.getFcp();
        String loginid = serialNo;
        byte[] baseBytes = utils.getBaseBytes();
        byte[] getDataBytes = utils.getDataBytes();
        String workHourData = PrimitiveConversion.getHexStringFromBytes(baseBytes, false, false);
        String getDataByteS = PrimitiveConversion.getHexStringFromBytes(getDataBytes, false, false);
        Log.e(TAG, "uploadLog: getDataByteS==" + getDataByteS);
//        workHourData="000000000000000000000000000000000000000000000000000000000000000000000000e703000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000033573b4500000000000000000000000000000000000000000000000000000000805c645c0000004100000000deb532450000000000000000000000000000000000000000000000000000000000000000000000000000c84200000000000000001bf72770c1c99fdf000000000000000000000000000000000000000000000000";
        // 32*4+38+8=432
        Log.e(TAG, "uploadLog: workHourData== length=" + workHourData.length());
        return uploadLog(workHourData, floatLen, version, intLen, serialNo, fcp, loginid);
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

    public String uploadLogBlock2(String dataBlock1, int intLen1, int floatLen1, String dataBlock2, int intLen2, int floatLen2, int version, String serialNo, int fcp, String loginid) {

        long createTime = System.currentTimeMillis();
        List<DataDetailBean> dataList = new ArrayList<>();

        DataDetailBean block1 = new DataDetailBean();
        block1.setData(dataBlock1);
        block1.setReceiveTime(createTime);
        block1.setBlock(IBlock.BLOCK_1);
        block1.setSlotCountInt(intLen1);
        block1.setSlotCountFloat(floatLen1);

        DataDetailBean block2 = new DataDetailBean();
        block2.setData(dataBlock2);
        block2.setReceiveTime(createTime);
        block2.setBlock(IBlock.BLOCK_2);
        block2.setSlotCountInt(intLen2);
        block2.setSlotCountFloat(floatLen2);

        dataList.add(block1);
        dataList.add(block2);

        List<List<DataDetailBean>> listlist = new ArrayList<>();
        listlist.add(dataList);
        UploadDataBean bean = new UploadDataBean();
        bean.setCreateTime(createTime);
        bean.setGprsIntLeng(intLen1 + intLen2);
        bean.setGprsFloatLeng(floatLen1 + floatLen2);
        bean.setGprsVersion(version);
        bean.setDataList(listlist);
        bean.setBlock(IBlock.BLOCK_2);

        bean.setSerialNo(serialNo);
        bean.setFcp(fcp);
        bean.setLoginId(loginid);
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