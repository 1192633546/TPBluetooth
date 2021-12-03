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

    public ReveiverEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
