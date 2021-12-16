package com.tpnet.tpbluetooth.net.bean;

/**
 * author : dingqb
 * e-mail : dingqb@sany.com.cn
 * date   : 2021/12/16 4:22 PM
 * desc   :
 * version: 1.0
 */
public class BaseBean<T> {


    /**
     * status : 200
     * result : 1
     * dataResult : 1
     * message : Successful request
     * data : {}
     */

    private int status;
    private int result;
    private int dataResult;
    private String message;
    private T data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
