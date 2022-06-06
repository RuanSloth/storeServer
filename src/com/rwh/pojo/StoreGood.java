package com.rwh.pojo;

public class StoreGood {
    private Integer gid;
    private String gname;
    private String sg_status;
    private int inventory;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getSg_status() {
        return sg_status;
    }

    public void setSg_status(String sg_status) {
        this.sg_status = sg_status;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "StoreGood{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", sg_status='" + sg_status + '\'' +
                ", inventory=" + inventory +
                '}';
    }

    public StoreGood() {
    }

    public StoreGood(Integer gid, String gname, String sg_status, int inventory) {
        this.gid = gid;
        this.gname = gname;
        this.sg_status = sg_status;
        this.inventory = inventory;
    }
}
