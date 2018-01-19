package omen.alien.clients;

import com.neovisionaries.ws.client.WebSocketAdapter;
import omen.alien.Const;
import com.google.gson.Gson;
import processing.data.JSONObject;
import omen.alien.definition.WsMessage;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

public class PlaybackClient {

    WebSocket ws;
    Gson g = new Gson();

    public boolean busy;
    WsMessage savedResult;

    public PlaybackClient() {
        String address = "ws://localhost:" + Const.WS_PLAYBACK_PORT;
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

    public void registerPreview(String id, Runnable cb) {
        busy = true;
        JSONObject opts = new JSONObject();
        opts.put("id", id);
        JSONObject json = new JSONObject();
        json.put("type", "registerPreview");
        json.put("opts", opts);
        this.ws.sendText(json.format(0).replace("\n", ""));
        this.ws.addListener(new WebSocketAdapter() {
            public void onTextMessage(WebSocket ws, String payload) {
                WsMessage msg = g.fromJson(payload, WsMessage.class);
                if (msg.type.equals("previewRegistered") && msg.id.equals(id)) {
                    ws.removeListener(this);
                    busy = false;
                    cb.run();
                }
            }
        });
    }

    public void playPreview() {
        if (!busy) {
            JSONObject json = new JSONObject();
            json.put("type", "playPreview");
            this.ws.sendText(json.format(0).replace("\n", ""));
        }
    }

    public void stopPreview() {
        if (!busy) {
            JSONObject json = new JSONObject();
            json.put("type", "stopPreview");
            this.ws.sendText(json.format(0).replace("\n", ""));
        }
    }

}
