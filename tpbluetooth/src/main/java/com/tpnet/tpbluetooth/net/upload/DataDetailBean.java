package com.tpnet.tpbluetooth.net.upload;

import java.io.Serializable;

/**
 * Create by dingqb
 * On 2019-10-17
 * Description:
 */
public class DataDetailBean implements Serializable {
    /**
     * data : string
     * receiveTime : 0
     */

    private String data;
    private long receiveTime;
    private int block;
    private int slotCountInt;
    private int slotCountFloat;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getSlotCountInt() {
        return slotCountInt;
    }

    public void setSlotCountInt(int slotCountInt) {
        this.slotCountInt = slotCountInt;
    }

    public int getSlotCountFloat() {
        return slotCountFloat;
    }

    public void setSlotCountFloat(int slotCountFloat) {
        this.slotCountFloat = slotCountFloat;
    }

    @Override
    public String toString() {
        return "DataDetailBean{" +
                "data='" + data + '\'' +
                ", receiveTime=" + receiveTime +
                ", block=" + block +
                ", slotCountInt=" + slotCountInt +
                ", slotCountFloat=" + slotCountFloat +
                '}';
    }
}

