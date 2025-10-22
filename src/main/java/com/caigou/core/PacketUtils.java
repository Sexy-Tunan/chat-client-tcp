package com.caigou.core;

import com.caigou.msg_format.ParsedPacket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PacketUtils {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static byte[] buildPacket(Short protoId, Object body) {

        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = jsonStr.getBytes(StandardCharsets.UTF_8);
        Integer packetLength = bytes.length + 2;

        ByteBuffer buffer = ByteBuffer.allocate(packetLength + 4);
        buffer.putInt(packetLength);
        buffer.putShort(protoId);
        buffer.put(bytes);

        return buffer.array();
    }



    public static ParsedPacket parseBytes2ParsedPacket(ByteBuffer bytes) {
        int packetLength = bytes.getInt();   // 4字节
        short protocolId = bytes.getShort(); // 2字节

        // 剩余字节是payload
        byte[] payload = new byte[bytes.remaining()];
        bytes.get(payload);

        ParsedPacket packet = new ParsedPacket(packetLength, protocolId, payload);
        return packet;
    }


}
