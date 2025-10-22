package com.caigou.msg_format.response;

import java.util.List;

public class ChannelInfo {
    private String channel_name;
    private String creator;
    private List<String> members;

    public ChannelInfo() {
    }

    public ChannelInfo(String channel_name, String creator, List<String> members) {
        this.channel_name = channel_name;
        this.creator = creator;
        this.members = members;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "ChannelInfo{" +
                "channel_name='" + channel_name + '\'' +
                ", creator='" + creator + '\'' +
                ", members=" + members +
                '}';
    }
}
