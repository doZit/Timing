package com.example.timing.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Video implements Serializable {
    private int duration;
    private String path;
    private int videoId;
    private long size;
    private String name;
    private Bitmap foreground;
    private String thumbPath;

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public int getDuration() {
        return duration;
    }

    public int getVideoId() {
        return videoId;
    }

    public String getName() {
        return name;
    }

    public Bitmap getForeground() {
        return foreground;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setForeground(Bitmap foreground) {
        this.foreground = foreground;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }
}
