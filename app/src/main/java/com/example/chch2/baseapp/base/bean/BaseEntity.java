package com.example.chch2.baseapp.base.bean;


/**
 * Created by gyg on 2016/8/28.
 */
public class BaseEntity<T> {
    // 接口请求返回带状态码
    public int code;
    // 服务器返回的提示信息
    public String msg;
    //data数据
    public T data;


    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


    public void setMessage(String message) {
        this.msg = message;
    }

    public String getMessage() {
        return msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
