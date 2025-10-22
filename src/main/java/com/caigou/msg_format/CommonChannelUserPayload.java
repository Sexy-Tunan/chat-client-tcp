package com.caigou.msg_format;

/**
 * 创建频道，删除频道，加入频道，退出频道通用请求和广播处理
 */
public class CommonChannelUserPayload {

    private String user;

    private String channel;

    public CommonChannelUserPayload() {
    }

    public CommonChannelUserPayload(String user, String channel) {
        this.user = user;
        this.channel = channel;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
