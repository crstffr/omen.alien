package omen.alien.clients;

import omen.alien.Const;
import com.google.gson.Gson;
import processing.data.JSONObject;
import omen.alien.definition.WsMessage;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

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

    public WsMessage getResult() {
        return savedResult;
    }

    public void getSampleData(String id, Runnable cb) {
        busy = true;
        JSONObject opts = new JSONObject();
        opts.put("id", id);
        JSONObject json = new JSONObject();
        json.put("type", "getSampleData");
        json.put("opts", opts);
        this.ws.sendText(json.format(0).replace("\n", ""));
        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("sampleData") && msg.id.equals(id)) {
                    ws.removeListener(this);
                    savedResult = msg;
                    busy = false;
                    cb.run();
                }
            }
        });
    }

    public void fetchAllSamples(Runnable cb) {
        busy = true;
        JSONObject json = new JSONObject();
        json.put("type", "fetchAllSamples");
        this.ws.sendText(json.format(0).replace("\n", ""));
        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("allSamples")) {
                    ws.removeListener(this);
                    savedResult = msg;
                    busy = false;
                    cb.run();
                }
            }
        });
    }

    public void renameSample(String id, String name, Runnable cb) {
        busy = true;
        JSONObject opts = new JSONObject();
        opts.put("id", id);
        opts.put("name", name);
        JSONObject json = new JSONObject();
        json.put("type", "renameSample");
        json.put("opts", opts);
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

    public void deleteSample(String id, Runnable cb) {
        busy = true;
        JSONObject opts = new JSONObject();
        opts.put("id", id);
        JSONObject json = new JSONObject();
        json.put("type", "deleteSample");
        json.put("opts", opts);
        this.ws.sendText(json.format(0).replace("\n", ""));
        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("sampleDeleted")) {
                    ws.removeListener(this);
                    savedResult = msg;
                    busy = false;
                    cb.run();
                }
            }
        });
    }

}
