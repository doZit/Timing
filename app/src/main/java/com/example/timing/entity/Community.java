package com.example.timing.entity;

import java.io.Serializable;

public class Community implements Serializable {
    private String m_name;
    private String m_address;

    public Community(String name,String address){
        m_name=name;
        m_address=address;
    }

    public String getM_name() {
        return m_name;
    }

    public String getM_address() {
        return m_address;
    }
}
