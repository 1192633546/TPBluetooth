package com.tpnet.tpbluetooth.net.upload;

import java.io.Serializable;
import java.util.List;

/**
 * Create by dingqb
 * On 2019-10-17
 * Description:
 */
public class UploadDataBean implements Serializable {

    /**
     * dataList : [{"data":"string","receiveTime":0}]
     * gprsFloatLeng : 0
     * gprsIntLeng : 0
     * gprsVersion : 0
     * serialNo : string
     */

    private int gprsFloatLeng;
    private int gprsIntLeng;
    private int gprsVersion;
    private String serialNo;
    private List<List<DataDetailBean>> dataList;
    private int fcp;
    private String loginId;
    private int block;
    // local
    private long createTime;

    public int getGprsFloatLeng() {
        return gprsFloatLeng;
    }

    public void setGprsFloatLeng(int gprsFloatLeng) {
        this.gprsFloatLeng = gprsFloatLeng;
    }

    public int getGprsIntLeng() {
        return gprsIntLeng;
    }

    public void setGprsIntLeng(int gprsIntLeng) {
        this.gprsIntLeng = gprsIntLeng;
    }

    public int getGprsVersion() {
        return gprsVersion;
    }

    public void setGprsVersion(int gprsVersion) {
        this.gprsVersion = gprsVersion;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public List<List<DataDetailBean>> getDataList() {
        return dataList;
    }

    public void setDataList(List<List<DataDetailBean>> dataList) {
        this.dataList = dataList;
    }

    public int getFcp() {
        return fcp;
    }

    public void setFcp(int fcp) {
        this.fcp = fcp;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "UploadDataBean{" +
                "gprsFloatLeng=" + gprsFloatLeng +
                ", gprsIntLeng=" + gprsIntLeng +
                ", gprsVersion=" + gprsVersion +
                ", serialNo='" + serialNo + '\'' +
                ", dataList=" + dataList +
                ", fcp=" + fcp +
                ", loginId='" + loginId + '\'' +
                ", block=" + block +
                ", createTime=" + createTime +
                '}';
    }
}
