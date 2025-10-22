package com.caigou.msg_format.request;


public class ChatMessage {

    private String userName;
    private String channel;
    private String msg;

    public ChatMessage() {
    }

    public ChatMessage(String userName, String msg) {
        this.userName = userName;
        this.msg = msg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
