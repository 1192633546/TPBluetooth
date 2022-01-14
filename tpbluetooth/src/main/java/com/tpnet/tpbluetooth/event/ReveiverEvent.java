package com.tpnet.tpbluetooth.event;

/**
 * author : dingqb
 * e-mail : dingqb@sany.com.cn
 * date   : 2021/12/2 4:28 PM
 * desc   :
 * version: 1.0
 */
public class ReveiverEvent {
    private String msg;
    private byte[] data;

    public ReveiverEvent(String msg, byte[] data) {
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
