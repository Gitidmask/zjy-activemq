package com.zjy.activemq.result;

/**
 * @author zhangjiuyang
 * @create 2018/7/24
 * @since 1.0.0
 */
public class ResultResponse {
    private static final long serialVersionUID = 1L;

    private boolean success = true;

    public ResultResponse(){}

    public ResultResponse(boolean success,String msg){
        this.success = success;
        this.msg = msg;
    }

    private Object data;

    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
