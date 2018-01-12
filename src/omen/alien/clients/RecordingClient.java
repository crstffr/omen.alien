package omen.alien.clients;

import omen.alien.App;
import omen.alien.Const;
import java.util.ArrayList;
import com.google.gson.Gson;
import processing.data.JSONObject;
import omen.alien.interf.WsMessage;
import com.neovisionaries.ws.client.*;


public class RecordingClient {

    WebSocket ws;
    Gson g = new Gson();

    public boolean busy;
    WsMessage savedResult;

    public RecordingClient() {

        String address = "ws://localhost:" + Const.WS_RECORDING_PORT;

        try {
            this.ws = new WebSocketFactory().createSocket(address);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public WsMessage getResult() {
        return savedResult;
    }

    public void connect() {
        try {
            this.ws.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void record(Integer channels, Integer sampleRate, Runnable cb) {
        JSONObject opts = new JSONObject();
        opts.put("channels", channels);
        opts.put("sampleRate", sampleRate);
        JSONObject json = new JSONObject();
        json.put("type", "record");
        json.put("opts", opts);

        busy = true;
        this.ws.sendText(json.format(0).replace("\n", ""));

        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("started")) {
                    ws.removeListener(this);
                    busy = false;
                    cb.run();
                }
            }
        });
    }

    public void stop() {
        JSONObject json = new JSONObject();
        json.put("type", "stop");
        this.ws.sendText(json.format(0).replace("\n", ""));
        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("stopped")) {
                    ws.removeListener(this);
                    busy = false;
                }
            }
        });
    }

    public void save(String filename, Runnable cb) {
        JSONObject opts = new JSONObject();
        opts.put("filename", filename);
        JSONObject json = new JSONObject();
        json.put("type", "save");
        json.put("opts", opts);
        this.ws.sendText(json.format(0).replace("\n", ""));
        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("saved")) {
                    ws.removeListener(this);
                    savedResult = msg;
                    busy = false;
                    cb.run();
                }
            }
        });
    }

    public void discard() {
        JSONObject json = new JSONObject();
        json.put("type", "discard");
        this.ws.sendText(json.format(0).replace("\n", ""));
        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("discarded")) {
                    ws.removeListener(this);
                    busy = false;
                }
            }
        });
    }

}