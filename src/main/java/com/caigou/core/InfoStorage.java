package com.caigou.core;

import com.caigou.msg_format.response.ChannelInfo;

import java.util.Map;

public class InfoStorage {

    private String currentChannel;

    private String currentUser;

    private Map<String, ChannelInfo> channels;

    public InfoStorage() {
    }

    public InfoStorage(String currentChannel, String currentUser, Map<String, ChannelInfo> channels) {
        this.currentChannel = currentChannel;
        this.currentUser = currentUser;
        this.channels = channels;
    }

    public String getCurrentChannel() {
        return currentChannel;
    }

    public void setCurrentChannel(String currentChannel) {
        this.currentChannel = currentChannel;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public Map<String, ChannelInfo> getChannels() {
        return channels;
    }

    public void setChannels(Map<String, ChannelInfo> channels) {
        this.channels = channels;
    }
}
