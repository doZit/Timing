package com.example.timing.entity;

import java.io.Serializable;

public class Photo implements Serializable {
    private String name;//名称
    private long date;//日期
    private String path;//路径
    private int size;
    private boolean isSelected=false;

    public Photo() {
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getDate() {
        return date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }
}
