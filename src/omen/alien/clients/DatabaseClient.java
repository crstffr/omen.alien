package omen.alien.clients;

import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;
import omen.alien.Const;
import omen.alien.interf.WsMessage;
import processing.data.JSONObject;

public class DatabaseClient {

    WebSocket ws;
    Gson g = new Gson();

    public boolean busy;
    WsMessage savedResult;

    public DatabaseClient() {

        String address = "ws://localhost:" + Const.WS_DATA_PORT;

        try {
            this.ws = new WebSocketFactory().createSocket(address);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void connect() {
        try {
            this.ws.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void renameSample(String id, String name, Runnable cb) {
        JSONObject opts = new JSONObject();
        opts.put("id", id);
        opts.put("name", name);

        JSONObject json = new JSONObject();
        json.put("type", "renameSample");
        json.put("opts", opts);

        busy = true;
        this.ws.sendText(json.format(0).replace("\n", ""));

        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("sampleRenamed")) {
                    ws.removeListener(this);
                    savedResult = msg;
                    busy = false;
                    cb.run();
                }
            }
        });
    }

}
