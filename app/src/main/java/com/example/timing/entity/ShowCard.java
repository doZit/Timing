package com.example.timing.entity;

import android.content.SharedPreferences;
import android.media.Image;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ShowCard implements Serializable {
    private String m_name;     //card发布者昵称
    private int m_cardId;      //card的id号
//    private SharedPreferences m_headPic;        //card发布者的头像
    private List<Integer> m_picPath;          //card图片
    private String m_content;    //card内容
    private List<String> m_lover;    //card的点赞人
    private List<String> m_collector;     //card的收藏人
//    private Map<String,Map<String,List<String>>> m_marker;     //card的评论人及其内容

    public ShowCard(String name,int carId,List<Integer> picPath,
                    String content,List<String> lover,List<String> collector){
        this.m_name=name;
        this.m_cardId=carId;
//        this.m_headPic=headPic;
        this.m_picPath=picPath;
        this.m_content=content;
        this.m_lover=lover;
        this.m_collector=collector;
    }

    public String getM_name(){
        return m_name;
    }

    public int getM_cardId(){
        return m_cardId;
    }

//    public SharedPreferences getM_headPic(){
//        return m_headPic;
//    }

    public String getM_content() {
        return m_content;
    }

    public List<Integer> getM_picPath() {
        return m_picPath;
    }

    public List<String> getM_lover() {
        return m_lover;
    }

    public List<String> getM_collector() {
        return m_collector;
    }
}
