package com.caigou.msg_format.broadcast;

public class ChannelMsgBroadcastPayload {

    private String sender;
    private String channel;
    private String message;

    public ChannelMsgBroadcastPayload() {
    }

    public ChannelMsgBroadcastPayload(String sender, String channel, String message) {
        this.sender = sender;
        this.channel = channel;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
