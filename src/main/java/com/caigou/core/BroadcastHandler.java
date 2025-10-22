package com.caigou.core;

import com.caigou.msg_format.CommonChannelUserPayload;
import com.caigou.msg_format.ParsedPacket;
import com.caigou.msg_format.broadcast.ChannelMsgBroadcastPayload;
import com.caigou.msg_format.response.ChannelInfo;
import com.caigou.msg_format.response.JoinChannelResponsePayload;
import com.caigou.msg_format.response.LoginResponsePayload;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BroadcastHandler {



    private static ObjectMapper mapper = new ObjectMapper();
    public static void handle(ParsedPacket parsedPacket) throws IOException {

        switch (parsedPacket.getProtocolId()) {
            case Protocol.Login_RESPONSE_PROTOCOL_NUMBER:
                // 处理 "登录响应数据"，非广播消息，需要将用户登录返回的数据保存到infoStorage中
                LoginResponsePayload loginResponsePayload = mapper.readValue(parsedPacket.getData(), LoginResponsePayload.class);
                if (!loginResponsePayload.isState()){
                    System.out.println("登录失败：失败原因 --》" + loginResponsePayload.getReason());
                } else {
                    handleLoginResponse(loginResponsePayload);
                }
                break;


            case Protocol.JOIN_CHANNEL_RESPONSE_PROTOCOL_NUMBER:
                // 处理 "加入频道后的响应信息，非广播消息"
                JoinChannelResponsePayload joinChannelResponsePayload = mapper.readValue(parsedPacket.getData(), JoinChannelResponsePayload.class);
                break;


            case Protocol.MSG_BROADCAST_PROTOCOL_NUMBER:
                // 处理 频道内的广播消息 == 消息发送
                ChannelMsgBroadcastPayload channelMsgBroadcastPayload = mapper.readValue(parsedPacket.getData(), ChannelMsgBroadcastPayload.class);
                if (!channelMsgBroadcastPayload.getSender().equals(ChatClient.infoStorage.getCurrentUser()) && channelMsgBroadcastPayload.getChannel().equals(ChatClient.infoStorage.getCurrentChannel())){
                    System.out.println("[" + channelMsgBroadcastPayload.getSender() + "]: " + channelMsgBroadcastPayload.getMessage());
                }
                break;


            case Protocol.CREATE_CHANNEL_BROADCAST_PROTOCOL_NUMBER:
                // 处理 频道内的广播消息 == 创建频道
                CommonChannelUserPayload createChannelInfo = mapper.readValue(parsedPacket.getData(), CommonChannelUserPayload.class);
                handleCreateChannel(createChannelInfo);
                break;


            case Protocol.DELETE_CHANNEL_BROADCAST_PROTOCOL_NUMBER:
                // 处理 频道内的广播消息 == 删除频道
                CommonChannelUserPayload deleteChannelInfo = mapper.readValue(parsedPacket.getData(), CommonChannelUserPayload.class);
                handleDeleteChannel(deleteChannelInfo);
                break;


            case Protocol.JOIN_CHANNEL_BROADCAST_PROTOCOL_NUMBER:
                // 处理 频道内的广播消息 == 加入频道
                CommonChannelUserPayload joinChannelInfo = mapper.readValue(parsedPacket.getData(), CommonChannelUserPayload.class);
                handleJoinChannel(joinChannelInfo);
                break;


            case Protocol.QUIT_CHANNEL_BROADCAST_PROTOCOL_NUMBER:
                // 处理 频道内的广播消息 == 退出频道
                CommonChannelUserPayload quitChannelInfo = mapper.readValue(parsedPacket.getData(), CommonChannelUserPayload.class);
                handleQuitChannel(quitChannelInfo);
                break;

            default:
        }
    }



    private static void handleLoginResponse(LoginResponsePayload loginResponsePayload){
        ChatClient.infoStorage.setCurrentUser(loginResponsePayload.getUser());
        ChatClient.infoStorage.setCurrentChannel("world"); //初始化时默认频道为世界频道，可在控制台切换
        Map<String, ChannelInfo> channels = new HashMap<>();
        for (ChannelInfo channelInfo : loginResponsePayload.getData()){
            channels.put(channelInfo.getChannel_name(),channelInfo);
        }
        ChatClient.infoStorage.setChannels(channels);
    }

    private static void handleJoinResponse(JoinChannelResponsePayload payload){
        ChannelInfo channelInfo = payload.getData();
        ChatClient.infoStorage.getChannels().put(channelInfo.getChannel_name(),channelInfo);
    }


    private static void handleCreateChannel(CommonChannelUserPayload payload){
        // 保存新创建的频道信息到infoStorage
        Map<String, ChannelInfo> channels = ChatClient.infoStorage.getChannels();
        List<String> members = new ArrayList<>();
        members.add(payload.getUser());
        channels.put(payload.getChannel(), new ChannelInfo(payload.getChannel(),payload.getUser(),members));
    }

    private static void handleDeleteChannel(CommonChannelUserPayload payload){
        // 清除删除的频道信息
        ChatClient.infoStorage.getChannels().remove(payload.getChannel());
    }

    private static void handleJoinChannel(CommonChannelUserPayload payload){
        // 本地存储信息中改频道增加新来的成员
        ChannelInfo channelInfo = ChatClient.infoStorage.getChannels().get(payload.getChannel());
        channelInfo.getMembers().add(payload.getUser());
    }

    private static void handleQuitChannel(CommonChannelUserPayload payload){
        ChannelInfo channelInfo = ChatClient.infoStorage.getChannels().get(payload.getChannel());
        channelInfo.getMembers().remove(payload.getUser());
    }

}

