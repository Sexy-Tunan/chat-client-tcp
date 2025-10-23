package com.caigou.msg_format;

public class CommonErrorPayload {

    private Boolean state;

    private String reason;

    public CommonErrorPayload() {
    }

    public CommonErrorPayload(Boolean state, String reason) {
        this.state = state;
        this.reason = reason;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
