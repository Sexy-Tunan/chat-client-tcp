package com.caigou.msg_format.response;

import java.util.List;

public class LoginResponsePayload {

    private boolean state;
    private String user;
    private List<ChannelInfo> data;
    private String reason;

    public LoginResponsePayload() {
    }

    public LoginResponsePayload(boolean state, String user, List<ChannelInfo> data, String reason) {
        this.state = state;
        this.user = user;
        this.data = data;
        this.reason = reason;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<ChannelInfo> getData() {
        return data;
    }

    public void setData(List<ChannelInfo> data) {
        this.data = data;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
