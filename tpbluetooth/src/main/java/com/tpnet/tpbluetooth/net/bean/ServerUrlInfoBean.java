package com.tpnet.tpbluetooth.net.bean;

/**
 * author : dingqb
 * e-mail : dingqb@sany.com.cn
 * date   : 2021/12/16 4:21 PM
 * desc   :
 * version: 1.0
 */
public class ServerUrlInfoBean   {

    /**
     * status : 200
     * result : 1
     * dataResult : 1
     * message : Successful request
     * data : {"srvId":4,"srvName":"易眼逻辑服务器","srvAddress":"https://eviapptest.sanyevi.cn/eiapp/","isStatus":1,"saltIndex":3131,"secretTime":1602572259000,"dtTime":1639643863058}
     */

    private int status;
    private int result;
    private int dataResult;
    private String message;
    /**
     * srvId : 4
     * srvName : 易眼逻辑服务器
     * srvAddress : https://eviapptest.sanyevi.cn/eiapp/
     * isStatus : 1
     * saltIndex : 3131
     * secretTime : 1602572259000
     * dtTime : 1639643863058
     */

    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getDataResult() {
        return dataResult;
    }

    public void setDataResult(int dataResult) {
        this.dataResult = dataResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int srvId;
        private String srvName;
        private String srvAddress;
        private int isStatus;
        private int saltIndex;
        private long secretTime;
        private long dtTime;

        public int getSrvId() {
            return srvId;
        }

        public void setSrvId(int srvId) {
            this.srvId = srvId;
        }

        public String getSrvName() {
            return srvName;
        }

        public void setSrvName(String srvName) {
            this.srvName = srvName;
        }

        public String getSrvAddress() {
            return srvAddress;
        }

        public void setSrvAddress(String srvAddress) {
            this.srvAddress = srvAddress;
        }

        public int getIsStatus() {
            return isStatus;
        }

        public void setIsStatus(int isStatus) {
            this.isStatus = isStatus;
        }

        public int getSaltIndex() {
            return saltIndex;
        }

        public void setSaltIndex(int saltIndex) {
            this.saltIndex = saltIndex;
        }

        public long getSecretTime() {
            return secretTime;
        }

        public void setSecretTime(long secretTime) {
            this.secretTime = secretTime;
        }

        public long getDtTime() {
            return dtTime;
        }

        public void setDtTime(long dtTime) {
            this.dtTime = dtTime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "srvId=" + srvId +
                    ", srvName='" + srvName + '\'' +
                    ", srvAddress='" + srvAddress + '\'' +
                    ", isStatus=" + isStatus +
                    ", saltIndex=" + saltIndex +
                    ", secretTime=" + secretTime +
                    ", dtTime=" + dtTime +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ServerUrlInfoBean{" +
                "status=" + status +
                ", result=" + result +
                ", dataResult=" + dataResult +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
