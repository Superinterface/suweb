package com.sujianan.bean.system;

public class District {
    private Integer id;

    private Integer pid;

    private String district;

    private Short tLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Short gettLevel() {
        return tLevel;
    }

    public void settLevel(Short tLevel) {
        this.tLevel = tLevel;
    }
}