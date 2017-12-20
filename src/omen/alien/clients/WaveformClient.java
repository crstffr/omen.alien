package omen.alien.clients;

import com.google.gson.Gson;
import omen.alien.Const;
import omen.alien.interf.WsMessage;
import processing.data.JSONObject;
import com.neovisionaries.ws.client.*;

import java.util.ArrayList;

public class WaveformClient {

    WebSocket ws;
    Gson g = new Gson();

    public WaveformClient() {

        WaveformClient _this = this;

        String address = "ws://localhost:" + Const.WS_WAVEFORM_PORT;

        try {

            this.ws = new WebSocketFactory().createSocket(address);

            this.ws.addListener(new WebSocketAdapter() {
                public void onTextMessage(WebSocket ws, String payload) {
                    WsMessage msg = g.fromJson(payload, WsMessage.class);
                    System.out.println(payload);

                    switch (msg.type) {
                        case "datGenerated":
                            System.out.println("now what?");
                            break;
                    }
                }
            });

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

    public void generateDat(String id) {
        JSONObject opts = new JSONObject();
        opts.put("id", id);
        JSONObject json = new JSONObject();
        json.put("type", "generateDat");
        json.put("opts", opts);
        this.ws.sendText(json.format(0).toString().replace("\n", ""));
    }

}