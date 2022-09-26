package com.example.timing.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private int m_id;
    private String m_name;
    private String m_personalSign;
    private ArrayList<Integer> m_concernAccountId;
    private ArrayList<Integer> m_likeAccountId;
    private ArrayList<Integer> m_joinCommunityId;
    private List<ShowCard> m_likeCards;
    private List<ShowCard> m_collectCards;
    private List<ShowCard> m_createCards;

    public Account(){

    }

    public String getM_name() {
        return m_name;
    }

    public String getM_personalSign() {
        return m_personalSign;
    }

    public ArrayList<Integer> getM_concernAccountId() {
        return m_concernAccountId;
    }

    public ArrayList<Integer> getM_likeAccountId() {
        return m_likeAccountId;
    }

    public ArrayList<Integer> getM_joinCommunityId() {
        return m_joinCommunityId;
    }

    public List<ShowCard> getM_likeCards() {
        return m_likeCards;
    }

    public List<ShowCard> getM_collectCards() {
        return m_collectCards;
    }

    public List<ShowCard> getM_createCards() {
        return m_createCards;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public void setM_personalSign(String m_personalSign) {
        this.m_personalSign = m_personalSign;
    }

    public void setM_collectCards(List<ShowCard> m_collectCards) {
        this.m_collectCards = m_collectCards;
    }

    public void setM_likeCards(List<ShowCard> m_likeCards) {
        this.m_likeCards = m_likeCards;
    }

    public void setM_concernAccountId(ArrayList<Integer> m_concernAccountId) {
        this.m_concernAccountId = m_concernAccountId;
    }

    public void setM_createCards(List<ShowCard> m_createCards) {
        this.m_createCards = m_createCards;
    }

    public void setM_joinCommunityId(ArrayList<Integer> m_joinCommunityId) {
        this.m_joinCommunityId = m_joinCommunityId;
    }

    public void setM_likeAccountId(ArrayList<Integer> m_likeAccountId) {
        this.m_likeAccountId = m_likeAccountId;
    }
}
