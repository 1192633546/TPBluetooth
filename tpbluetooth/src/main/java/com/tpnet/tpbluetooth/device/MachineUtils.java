package com.tpnet.tpbluetooth.device;

/**
 * author : dingqb
 * e-mail : dingqb@sany.com.cn
 * date   : 2022/1/13 8:09 AM
 * desc   :
 * version: 1.0
 */
public class MachineUtils {
    public static String getSerialNo(byte[] bytes) {
        if (bytes == null || bytes.length < 13) {
            return "";
        }
        SerialNoBean.Builder builder = new SerialNoBean.Builder();
        builder.machineNum1(String.valueOf((char) bytes[0]));
        builder.machineNum2(String.valueOf((char) bytes[1]));
        builder.machineNum3(String.valueOf((char) bytes[2]));
        builder.machineNum4(String.valueOf((char) bytes[3]));
        builder.machineNum5(String.valueOf((char) bytes[4]));
        builder.machineNum6(String.valueOf((char) bytes[5]));
        builder.machineNum7(String.valueOf((char) bytes[6]));
        builder.machineNum8(String.valueOf((char) bytes[7]));
        builder.machineNum9(String.valueOf((char) bytes[8]));
        builder.machineNum10(String.valueOf((char) bytes[9]));
        builder.machineNum11(String.valueOf((char) bytes[10]));
        builder.machineNum12(String.valueOf((char) bytes[11]));
        builder.machineNum13(String.valueOf((char) bytes[12]));
        SerialNoBean serialNoBean = builder.build();
        String serialNo = serialNoBean.getSerialNo();
        return serialNo;
    }
}
