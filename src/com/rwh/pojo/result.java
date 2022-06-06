package com.rwh.pojo;

public class result {
    private Object data;
    private int code;
    private String desc;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public result() {
    }

    public result(Object data, int code, String desc) {
        this.data = data;
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "result{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}