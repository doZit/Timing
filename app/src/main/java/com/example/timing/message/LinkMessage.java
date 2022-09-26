package com.example.timing.message;

import java.io.Serializable;

public class LinkMessage implements Serializable {
    private String m_sender;
    private String m_latestMessage;
    private String m_send_time;

    public LinkMessage(String sender, String latestMessage, String send_time){
        m_sender=sender;
        m_latestMessage=latestMessage;
        m_send_time=send_time;
    }

    public String getM_sender() {
        return m_sender;
    }

    public String getM_latestMessage() {
        return m_latestMessage;
    }

    public String getM_send_time() {
        return m_send_time;
    }
}
