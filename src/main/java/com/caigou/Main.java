package com.caigou;

import com.caigou.core.*;
import com.caigou.msg_format.CommonChannelUserPayload;
import com.caigou.msg_format.ParsedPacket;
import com.caigou.msg_format.broadcast.ChannelMsgBroadcastPayload;
import com.caigou.msg_format.request.LoginRequestPlayload;
import com.caigou.msg_format.response.ChannelInfo;



import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        ChatClient client = new ChatClient("172.22.2.101",10088);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        // 发送登录消息包
        client.sendData(PacketUtils.buildPacket(Protocol.LOGIN_REQUEST_PROTOCOL_NUMBER, new LoginRequestPlayload(userName, password)));
        ParsedPacket parsedPacket = client.receivePacket();
        System.out.println(parsedPacket);
        BroadcastHandler.handle(parsedPacket);

        ReceivedThread receivedThread = new ReceivedThread(client,true);
        receivedThread.start();

        String line;
        while (true){
            System.out.println("当前频道：" + ChatClient.infoStorage.getCurrentChannel());
            System.out.println("发送信息输入1");
            System.out.println("切换频道输入2");
            System.out.println("添加频道输入3");
            System.out.println("展示已加入的频道列表输入4");
            System.out.println("展示所有频道列表输入5");
            System.out.println("展示当前频道成员列表输入6");

            System.out.println("请输入你的选择：");
            line = scanner.nextLine();
            byte[] packet;
            switch (Integer.valueOf(line).intValue()){
                case 1:
                    System.out.println("请输入消息：");
                    line = scanner.nextLine();
                    packet = PacketUtils.buildPacket(Protocol.MSG_REQUEST_PROTOCOL_NUMBER, new ChannelMsgBroadcastPayload(userName, ChatClient.infoStorage.getCurrentChannel(), line));
                    client.sendData(packet);
                    break;

                case 2:
                    System.out.println("请输入切换的频道名字：");
                    line = scanner.nextLine();
                    if (ChatClient.infoStorage.getChannels().containsKey(line)){
                        ChatClient.infoStorage.setCurrentChannel(line);
                    } else {
                        System.out.println("频道不存在：");
                    }
                    break;

                case 3:
                    System.out.println("请输入添加的频道名字");
                    line = scanner.nextLine();
                    packet = PacketUtils.buildPacket(Protocol.CHANNEL_CREATE_REQUEST_PROTOCOL_NUMBER, new CommonChannelUserPayload(ChatClient.infoStorage.getCurrentUser(), line));
                    client.sendData(packet);
                    break;

                case 4:
                    System.out.println("已加入的频道如下：");
                    String currentUser = ChatClient.infoStorage.getCurrentUser();
                    for (Map.Entry<String,ChannelInfo> entry : ChatClient.infoStorage.getChannels().entrySet()){
                        if (entry.getValue().getMembers().contains(currentUser)){
                            System.out.println(entry.getValue().getChannel_name());
                        }
                    }
                    break;

                case 5:
                    System.out.println("全部频道如下：");
                    for (String channelName : ChatClient.infoStorage.getChannels().keySet()){
                        System.out.println(channelName);
                    }
                    break;

                case 6:
                    System.out.println("[" + ChatClient.infoStorage.getCurrentChannel() + "]频道成员列表如下：");
                    ChannelInfo channelInfo = ChatClient.infoStorage.getChannels().get(ChatClient.infoStorage.getCurrentChannel());
                    for (String member : channelInfo.getMembers()){
                        System.out.println(member);
                    }
                    break;

                default:
                    System.out.println("无此选项");
            }
        }

    }
}