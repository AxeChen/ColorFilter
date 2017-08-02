package com.mg.axe.colorfilter.bean;

/**
 * @Author Zaifeng
 * @Create 2017/7/21 0021
 * @Description Content
 */

public class FilterBean {
    public String name;
    public float[] filterFloats;

    public FilterBean(String name, float[] filterFloats) {
        this.name = name;
        this.filterFloats = filterFloats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getFilterFloats() {
        return filterFloats;
    }

    public void setFilterFloats(float[] filterFloats) {
        this.filterFloats = filterFloats;
    }
}
