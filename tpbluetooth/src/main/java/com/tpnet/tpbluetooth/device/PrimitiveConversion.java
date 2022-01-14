package com.tpnet.tpbluetooth.device;


import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 数据转换
 * 高位在前 低位在后
 * Note: within all method, byte0 stand for high end byte, byte3 stand for low end byte
 * 00    01   02     03 = value = 0x00010203
 * byte0 byte1 byte2 byte3
 */
public final class PrimitiveConversion {
    private static Charset charset = StandardCharsets.UTF_8;

    //位数组
    public static int[] BitArray = {0x0001, 0x0003, 0x0007, 0x000f,
            0x001f, 0x003f, 0x007f, 0x00ff,
            0x01ff, 0x03ff, 0x07ff, 0x0fff,
            0x1fff, 0x3fff, 0x7fff, 0xffff//16λ
    };

    /**
     * 获取整数某位开始的N位对应的整型
     *
     * @param value      整型量
     * @param startIndex 起始下标
     * @param len        长度
     * @return int 整型量
     */
    public static int getIntFromBits(int value, int startIndex, int len) {
        if (len > 16) {
            return 0;
        }
        if (startIndex < 0 || startIndex > 31) {
            return 0;
        }
        value = value >> startIndex;
        int temp = value & BitArray[len - 1];

        return temp;
    }

    /**
     * byte转Float
     *
     * @param byte0 high byte
     * @param byte1
     * @param byte2
     * @param byte3 low byte
     * @return
     */
    public static float getFloatFromByte(byte byte0, byte byte1, byte byte2, byte byte3) {
        long ret;
        ret = byte3;
        ret &= 0xff;
        ret |= ((long) byte2 << 8);
        ret &= 0xffff;
        ret |= ((long) byte1 << 16);
        ret &= 0xffffff;
        ret |= ((long) byte0 << 24);
        return Float.intBitsToFloat((int) ret);
    }


    /**
     * byte 转整型
     *
     * @param byte0
     * @return
     */
    public static int getIntFromByte(byte byte0) {
        int ret = 0xFF & byte0;
        return ret;
    }

    /**
     * byte 转整型
     *
     * @param byte0 high byte
     * @param byte1 low byte
     * @return
     */
    public static int getIntFromByte(byte byte0, byte byte1) {
        int ret;
        ret = byte1;
        ret &= 0xff;
        ret |= ((long) byte0 << 8);
        ret &= 0xffff;
        return ret;
    }

    /**
     * byte 转整型
     *
     * @param byte0 high byte
     * @param byte1
     * @param byte2
     * @param byte3 low byte
     * @return
     */
    public static int getIntFromByte(byte byte0, byte byte1, byte byte2, byte byte3) {
        //03 20 00 00
        int ret;
        ret = byte3;
        ret &= 0xff;
        ret |= ((long) byte2 << 8);
        ret &= 0xffff;
        ret |= ((long) byte1 << 16);
        ret &= 0xffffff;
        ret |= ((long) byte0 << 24);
        return ret;
    }

    /**
     * byte 转整型
     *
     * @param byte0 high byte
     * @param byte1 low byte
     * @return
     */
    public static Short getShortFromByte(byte byte0, byte byte1) {
        //03 20 00 00
        short ret;
        ret = byte1;
        ret &= 0xff;
        ret |= ((long) byte0 << 8);
        ret &= 0xffff;
        return ret;
    }

    /**
     * byte 转长整型
     *
     * @param byte0 H
     * @param byte1
     * @param byte2
     * @param byte3 L
     * @return
     */
    public static long getInt32FromByte(byte byte0, byte byte1, byte byte2, byte byte3) {
        long ret;
        ret = byte3;
        ret &= 0xff;
        ret |= ((long) byte2 << 8);
        ret &= 0xffff;
        ret |= ((long) byte1 << 16);
        ret &= 0xffffff;
        ret |= ((long) byte0 << 24);
        return ret;
    }

    /**
     * byte 转长整型
     *
     * @param data
     * @return
     */
    public static Long getLongFromByte(byte[] data) {
        long ret = 0;
        if (data == null) {
            return 0L;
        }
        int i = (data.length - 1) > 7 ? 7 : (data.length - 1);
        for (byte b : data) {
            int a = b & 0xff;
            ret |= ((long) a << (i * 8));
            i--;
        }
        return ret;
    }

    /**
     * 整型转byte
     *
     * @param value
     * @return byte
     */
    public static byte getByteFromInt(int value) {
        byte ret = (byte) (0x000000FF & value);
        return ret;
    }

    /**
     * 整型转byte
     *
     * @param value
     * @param arrayNumber byte 数组长度
     * @return
     */
    public static byte[] getBytesFromInt(int value, int arrayNumber) {
        if (arrayNumber < 1 || arrayNumber > 4) {
            return null;
        }
        byte[] ret = new byte[arrayNumber];
        switch (arrayNumber) {
            case 1:
                ret[0] = (byte) (value & 0x000000FF);
                break;
            case 2:
                ret[0] = (byte) (value >>> 8 & 0x000000FF);
                ret[1] = (byte) (value & 0x000000FF);
                break;
            case 3:
                ret[0] = (byte) (value >>> 16 & 0x000000FF);
                ret[1] = (byte) (value >>> 8 & 0x000000FF);
                ret[2] = (byte) (value & 0x000000FF);
                break;
            case 4:
                ret[0] = (byte) (value >>> 24 & 0x000000FF);
                ret[1] = (byte) (value >>> 16 & 0x000000FF);
                ret[2] = (byte) (value >>> 8 & 0x000000FF);
                ret[3] = (byte) (value & 0x000000FF);
                break;
            default:
                break;
        }
        return ret;
    }

    /**
     * 长整型转byte
     *
     * @param value
     * @param arrayNumber byte数组长度
     * @return
     */
    public static byte[] getBytesFromInt(long value, int arrayNumber) {
        if (arrayNumber < 1 || arrayNumber > 4) {
            return null;
        }

        byte[] ret = new byte[arrayNumber];
        switch (arrayNumber) {
            case 1:
                ret[0] = (byte) (value & 0x000000FF);
                break;
            case 2:
                ret[0] = (byte) (value >>> 8 & 0x000000FF);
                ret[1] = (byte) (value & 0x000000FF);
                break;
            case 3:
                ret[0] = (byte) (value >>> 16 & 0x000000FF);
                ret[1] = (byte) (value >>> 8 & 0x000000FF);
                ret[2] = (byte) (value & 0x000000FF);
                break;
            case 4:
                ret[0] = (byte) (value >>> 24 & 0x000000FF);
                ret[1] = (byte) (value >>> 16 & 0x000000FF);
                ret[2] = (byte) (value >>> 8 & 0x000000FF);
                ret[3] = (byte) (value & 0x000000FF);
                break;
            default:
                break;
        }
        return ret;
    }

    /**
     * 浮点转byte
     *
     * @param fvalue
     * @param arrayNumber 数组长度
     * @return byte[]
     */
    public static byte[] getBytesFromFloat(float fvalue, int arrayNumber) {
        if (arrayNumber < 1 || arrayNumber > 4) {
            return null;
        }

        int value = Float.floatToIntBits(fvalue);
        byte[] ret = new byte[arrayNumber];
        switch (arrayNumber) {
            case 1:
                ret[0] = (byte) (value & 0x000000FF);
                break;
            case 2:
                ret[0] = (byte) (value >>> 8 & 0x000000FF);
                ret[1] = (byte) (value & 0x000000FF);
                break;
            case 3:
                ret[0] = (byte) (value >>> 16 & 0x000000FF);
                ret[1] = (byte) (value >>> 8 & 0x000000FF);
                ret[2] = (byte) (value & 0x000000FF);
                break;
            case 4:
                ret[0] = (byte) (value >>> 24 & 0x000000FF);
                ret[1] = (byte) (value >>> 16 & 0x000000FF);
                ret[2] = (byte) (value >>> 8 & 0x000000FF);
                ret[3] = (byte) (value & 0x000000FF);
                break;
            default:
                break;
        }
        return ret;
    }

    /**
     * 获取 byte数组16进制字符串形式,无空格间隔
     *
     * @param bytes
     * @param everyHas0x 是否添加“0x”
     * @return
     */
    public static String getHexStringFromBytesWithoutSpace(byte[] bytes, boolean everyHas0x) {
        return getHexStringFromBytes(bytes, everyHas0x, false);
    }

    /**
     * 获取 byte数组16进制字符串形式,有空格间隔
     *
     * @param bytes
     * @param everyHas0x 是否添加“0x”
     * @return
     */
    public static String getHexStringFromBytes(byte[] bytes, boolean everyHas0x) {
        return getHexStringFromBytes(bytes, everyHas0x, true);
    }

    /**
     * 获取 byte数组16进制字符串形式,
     *
     * @param bytes
     * @param everyHas0x 是否添加“0x”
     * @param space      空格间隔标识
     * @return
     */
    public static String getHexStringFromBytes(byte[] bytes, boolean everyHas0x, boolean space) {
        if (bytes == null) {
            return ""; //$NON-NLS-1$
        }
        StringBuffer strbuf = new StringBuffer();
        int size = bytes.length;
        for (int i = 0; i < size; i++) {
            if (everyHas0x) {
                strbuf.append("0x");
            }
            strbuf.append(getByteHexStr(bytes[i], space));
        }
        return strbuf.toString();
    }

    /**
     * 获取 byte数组16进制字符串形式,
     *
     * @param bytes
     * @param length     长度
     * @param everyHas0x 是否添加“0x”
     * @return Hex String
     */
    public static String getHexStringFromBytes(byte[] bytes, int length, boolean everyHas0x) {
        if (bytes == null) {
            return ""; //$NON-NLS-1$
        }

        StringBuffer strbuf = new StringBuffer();
        int size = bytes.length > length ? length : bytes.length;
        for (int i = 0; i < size; i++) {
            if (everyHas0x) {
                strbuf.append("0x");
            }
            strbuf.append(getByteHexStr(bytes[i], true));
        }
        return strbuf.toString();
    }

    /**
     * 获取 byte链表16进制字符串形式,
     *
     * @param bytes
     * @param everyHas0x 是否添加“0x”
     * @return Hex String
     */
    public static String getHexStringFromBytes(List<Byte> bytes, boolean everyHas0x) {
        if (bytes == null)
            return ""; //$NON-NLS-1$

        StringBuffer strbuf = new StringBuffer();
        for (byte b : bytes) {
            if (everyHas0x) {
                strbuf.append("0x");
            }
            strbuf.append(getByteHexStr(b, true));
        }
        return strbuf.toString();
    }

    /**
     * 获取 int 整型16进制字符串形式,
     *
     * @param value
     * @param everyHas0x
     * @return Hex String
     */
    public static String getHexStringFromInt(int value, boolean everyHas0x) {
        byte[] bytes = getBytesFromInt(value, 4);
        return getHexStringFromBytes(bytes, everyHas0x, true);
    }

    /**
     * 获取 Float 浮点16进制字符串形式,
     *
     * @param receiveData
     * @return byte[]
     */
    public static byte[] getBytesFromHexString(List<String> receiveData) {
        byte[] ret = new byte[receiveData.size()];

        for (int i = 0; i < receiveData.size(); i++) {
            String s = receiveData.get(i).replace(" ", "");
            ret[i] = (byte) (Integer.parseInt(s, 16) & 0x00FF);
        }
        return ret;
    }

    /**
     * 16进制 byte字符串转byte数组
     *
     * @param receiveData
     * @return byte[]
     */
    public static byte[] getBytesFromHexString(String[] receiveData) {
        if (receiveData == null) {
            return null;
        }
        int len = receiveData.length;
        byte[] ret = new byte[len];

        for (int i = 0; i < len; i++) {
            String s = receiveData[i].replace(" ", "");
            s = s.toUpperCase().replace("0X", "");
            ret[i] = (byte) (Integer.parseInt(s, 16) & 0x00FF);
        }
        return ret;
    }

    /**
     * 长整型转byte
     *
     * @param l
     * @param length
     * @return byte[]
     */
    private static byte[] numberToByte(long l, int length) {
        byte[] bts = new byte[length];
        for (int i = 0; i < length; i++) {
            bts[i] = (byte) (l >> ((length - i - 1) * 8));
        }
        return bts;
    }

    /**
     * byte转长整型
     *
     * @param b
     * @return long
     */
    public static long toLong(byte... b) {
        int mask = 0xff;
        int temp = 0;
        long res = 0;
        int byteslen = b.length;
        if (byteslen > 8) {
            return Long.valueOf(0L);
        }
        for (int i = 0; i < byteslen; i++) {
            res <<= 8;
            temp = b[i] & mask;
            res |= temp;
        }
        return res;
    }

    /**
     * byte转整型
     *
     * @param b
     * @return
     */
    public static int toInt(byte... b) {
        return (int) toLong(b);
    }

    /**
     * byte转短整型
     *
     * @param b
     * @return
     */
    public static short toShort(byte... b) {
        return (short) toLong(b);
    }

    /**
     * 整型转byte
     *
     * @param i
     * @return
     */
    public static byte[] shortToByte(int i) {
        return numberToByte(i, 2);
    }

    /**
     * 整型转byte
     *
     * @param i
     * @return
     */
    public static byte[] intToByte(int i) {
        return numberToByte(i, 4);
    }

    /**
     * @param i
     * @return
     */
    public static byte[] longToByte(long i) {
        return numberToByte(i, 8);
    }

    public static byte[] longToByte(long i, int len) {
        return numberToByte(i, len);
    }

    /**
     * @param bytes
     * @return String
     */
    public static String formatBytes(byte... bytes) {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (byte byt : bytes) {
            sb.append(String.format("%02X ", byt)); //$NON-NLS-1$
        }
        return sb.toString();
    }

    /**
     * @param str
     * @return ByteBuffer
     */
    public static ByteBuffer encode(String str) {
        return charset.encode(str);
    }

    /**
     * @param str
     * @return
     */
    public static byte[] toByte(String str) throws Exception {
        if (str != null) {
            return str.getBytes(StandardCharsets.UTF_8);
        }
        return null;
    }

    public static byte[] toByte(String str, String charset) throws Exception {
        if (str != null) {
            return str.getBytes(charset);
        }
        return null;
    }

    /**
     * @param str
     * @return
     */
    public static byte[] toNormalByte(String str) {
        if (str != null) {
            return str.getBytes();
        }
        return null;
    }

    /**
     * @param bb
     * @return String
     */
    public static String decode(ByteBuffer bb) {
        return charset.decode(bb).toString();
    }

    /**
     * bytes 转String UTF-8
     *
     * @param bb
     * @return
     * @throws Exception
     */
    public static String getStringFromBytes(byte[] bb) {
        return new String(bb, StandardCharsets.UTF_8);
    }


    private static String getByteHexStr(byte b, boolean space) {
        StringBuffer strbuf = new StringBuffer();
        int v = b & 0xff;
        String hv = Integer.toHexString(v);
        if (hv.length() < 2) {
            strbuf.append(0);
        }
        strbuf.append(hv);
        strbuf.append(space ? " " : "");
        return strbuf.toString();
    }
}
