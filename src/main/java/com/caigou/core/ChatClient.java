package com.caigou.core;

import com.caigou.msg_format.ParsedPacket;
import lombok.Data;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;


@Data
public class ChatClient {

    public static InfoStorage infoStorage = new InfoStorage();

    private Socket socket;
    private String host;
    private int port;
    private DataInputStream in;
    private DataOutputStream out;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public ParsedPacket receivePacket() throws IOException {
        int packetLength = in.readInt();
        short protocolId = in.readShort();
        byte[] data = new byte[packetLength - 2];
        in.readFully(data);

        return new ParsedPacket(packetLength,protocolId,data);
    }

    public void sendData(byte[] data){
        try {
            out.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

