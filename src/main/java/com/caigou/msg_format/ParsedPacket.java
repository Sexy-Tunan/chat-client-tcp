package com.caigou.msg_format;

import java.util.Arrays;

public class ParsedPacket {

    private int packetLength;
    private short protocolId;
    private byte[] data;

    public ParsedPacket() {
    }

    public ParsedPacket(int packetLength, short protocolId, byte[] data) {
        this.packetLength = packetLength;
        this.protocolId = protocolId;
        this.data = data;
    }

    public int getPacketLength() {
        return packetLength;
    }

    public void setPacketLength(int packetLength) {
        this.packetLength = packetLength;
    }

    public short getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(short protocolId) {
        this.protocolId = protocolId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ParsedPacket{" +
                "packetLength=" + packetLength +
                ", protocolId=" + protocolId +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}

