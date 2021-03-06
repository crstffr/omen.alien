package omen.alien.clients;

import com.google.gson.Gson;
import omen.alien.Const;
import omen.alien.definition.WsMessage;
import processing.data.JSONObject;
import com.neovisionaries.ws.client.*;

import java.util.HashMap;

public class WaveformClient {

    WebSocket ws;
    Gson g = new Gson();

    public HashMap<String, WsMessage> datResults = new HashMap<>();
    public HashMap<String, WsMessage> pngResults = new HashMap<>();

    public WaveformClient() {

        String address = "ws://localhost:" + Const.WS_WAVEFORM_PORT;

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

    public void generateDat(String id, Runnable cb) {

        JSONObject opts = new JSONObject();
        opts.put("id", id);
        JSONObject json = new JSONObject();
        json.put("type", "generateDat");
        json.put("opts", opts);

        this.ws.sendText(json.format(0).replace("\n", ""));

        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("datGenerated") && msg.id.equals(id)) {
                    datResults.put(msg.id, msg);
                    ws.removeListener(this);
                    cb.run();
                }
            }
        });
    }

    public void generatePng(String id, Integer zoom, Runnable cb) {
        JSONObject opts = new JSONObject();
        opts.put("id", id);
        opts.put("zoom", zoom);
        JSONObject json = new JSONObject();
        json.put("type", "generatePng");
        json.put("opts", opts);
        this.ws.sendText(json.format(0).replace("\n", ""));
        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("pngGenerated") && msg.id.equals(id) && msg.zoom.equals(zoom)) {
                    pngResults.put(msg.id, msg);
                    ws.removeListener(this);
                    cb.run();
                }
            }
        });
    }

}