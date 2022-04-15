package com.tpnet.tpbluetooth.device;

import java.util.Arrays;

/**
 * author : dingqb
 * e-mail : dingqb@sany.com.cn
 * date   : 2022/1/12 5:12 PM
 * desc   :
 * version: 1.0
 */
public class DataBeanUtils {
    /**
     * [设备编号] [gprsVersion通信协议版本][block][slotCountInt][slotCountFloat][工况数据data]
     */
    /**
     * 53 52 31 35 35 43 43 42 31 38 36 31 38
     * 13
     * 00
     * 20
     * 26
     * 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 E7 03 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 33 57 3B 45 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 C3 61 5C 00 00 00 41 00 00 00 00 DE B5 32 45 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 C8 42 00 00 00 00 00 00 00 00 1B F7 27 70 C1 C9 9F DF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 AB AA 8A 3F
     */
    String serialNo;
    int version;
    int block;
    int intLen;
    int floatLen;
    int fcp = 1;
    byte[] dataBytes;
    // 工况数据
    byte[] baseBytes;


    public DataBeanUtils(byte[] data) {
        dataBytes = data;
//        for (int i = 0; i < dataBytes.length; i++) {
//            dataBytes[i] = (byte) Integer.parseInt(dataBytes[i] + "", 16);
//        }
    }

    public String getSerialNo() {
        serialNo = MachineUtils.getSerialNo(dataBytes);
        return serialNo;
    }

    public int getVersion() {
        if (dataBytes == null) {
            return -1;
        }
        version = (char) dataBytes[13];
        return version;
    }

    public int getBlock() {
        if (dataBytes == null) {
            return -1;
        }
        block = dataBytes[14];
        return block;
    }

    public int getIntLen() {
        if (dataBytes == null) {
            return -1;
        }
        intLen = dataBytes[15];
        return intLen;
    }

    public int getFloatLen() {
        if (dataBytes == null) {
            return -1;
        }
        floatLen = dataBytes[16];
        return floatLen;
    }

    public int getFcp() {
        return fcp;
    }

    public byte[] getBaseBytes() {
        if (dataBytes == null) {
            return new byte[0];
        }
        baseBytes = new byte[dataBytes.length - 17];
        System.arraycopy(dataBytes, 17, baseBytes, 0, baseBytes.length);
        return baseBytes;
    }

    public byte[] getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    @Override
    public String toString() {
        return "DataBeanUtils{" +
                ", serialNo='" + serialNo + '\'' +
                ", version=" + version +
                ", block=" + block +
                ", intLen=" + intLen +
                ", floatLen=" + floatLen +
                ", fcp=" + fcp +
                ", dataBytes=" + Arrays.toString(dataBytes) +
                ", baseBytes=" + Arrays.toString(baseBytes) +
                '}';
    }
}
