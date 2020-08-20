package com.example.silavetra;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class SocketManager {

  //  final byte[] ipAddr = new byte[]{(byte)176, (byte)15, (byte)174, (byte)137};
    final int port = 533;

    public WebSocket Connect()
    {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("ws://mrblackdog.ddns.net:5000/ws")
                .build();

        WebSocketGolos wsc = new WebSocketGolos();
        WebSocket ws = client.newWebSocket(request, wsc);
       // ws.send("State:" + "Client:"  + MainActivity.model);
        return ws;
    }
}
