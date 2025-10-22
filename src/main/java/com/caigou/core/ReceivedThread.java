package com.caigou.core;

import com.caigou.msg_format.ParsedPacket;

import java.io.IOException;

/**
 *  接受线程负责处理来自服务器的数据
 */
public class ReceivedThread extends Thread{

    private ChatClient chatClient;

    private volatile boolean running = true;

    public ReceivedThread() {
    }

    public ReceivedThread(ChatClient chatClient, boolean running) {
        this.chatClient = chatClient;
        this.running = running;
    }

    @Override
    public void run() {

        try {
            while (running) {
                ParsedPacket packet = chatClient.receivePacket();
                BroadcastHandler.handle(packet);
            }
        } catch (IOException e) {
            System.out.println("连接已断开或读取错误: " + e.getMessage());
        } finally {
            stopReceiving();
        }
    }

    public void stopReceiving() {
        running = false;
    }

    public void stopAndClose() {
        running = false;
    }




}
