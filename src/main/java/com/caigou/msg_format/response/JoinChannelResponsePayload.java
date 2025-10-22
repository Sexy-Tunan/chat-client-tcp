package com.caigou.msg_format.response;

import java.util.List;

public class JoinChannelResponsePayload {

    private String state;

    private ChannelInfo data;

    public JoinChannelResponsePayload() {
    }

    public JoinChannelResponsePayload(String state, ChannelInfo data) {
        this.state = state;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ChannelInfo getData() {
        return data;
    }

    public void setData(ChannelInfo data) {
        this.data = data;
    }
}
