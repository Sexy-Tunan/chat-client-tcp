package com.caigou.core;

public class Protocol {

    // CLIENT --> SERVER
    public static final short LOGIN_REQUEST_PROTOCOL_NUMBER = 10001;
    public static final short MSG_REQUEST_PROTOCOL_NUMBER = 11001;
    public static final short CHANNEL_CREATE_REQUEST_PROTOCOL_NUMBER = 12001;
    public static final short CHANNEL_DELETE_REQUEST_PROTOCOL_NUMBER = 12002;
    public static final short JOIN_CHANNEL_REQUEST_PROTOCOL_NUMBER = 13001;
    public static final short QUIT_CHANNEL_REQUEST_PROTOCOL_NUMBER = 13002;


    //SERVER --> CLIENT
    public static final short Login_RESPONSE_PROTOCOL_NUMBER = 20001;
    public static final short MSG_BROADCAST_PROTOCOL_NUMBER = 21001;
    public static final short JOIN_CHANNEL_RESPONSE_PROTOCOL_NUMBER = 22003;
    public static final short CREATE_CHANNEL_BROADCAST_PROTOCOL_NUMBER = 22001;
    public static final short DELETE_CHANNEL_BROADCAST_PROTOCOL_NUMBER = 22002;
    public static final short JOIN_CHANNEL_BROADCAST_PROTOCOL_NUMBER = 23001;
    public static final short QUIT_CHANNEL_BROADCAST_PROTOCOL_NUMBER = 23002;

    public static final short LIMIT_WORLD_SEND_PROTOCOL_NUMBER = 30001;


}

