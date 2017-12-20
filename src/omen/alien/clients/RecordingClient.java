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

    ArrayList<Runnable> onStartHandlers = new ArrayList<>();
    ArrayList<Runnable> onStopHandlers = new ArrayList<>();
    ArrayList<Runnable> onSaveHandlers = new ArrayList<>();

    public RecordingClient() {

        RecordingClient _this = this;

        String address = "ws://localhost:" + Const.WS_RECORDING_PORT;

        try {

            this.ws = new WebSocketFactory().createSocket(address);

            this.ws.addListener(new WebSocketAdapter() {
                public void onTextMessage(WebSocket ws, String payload) {
                    WsMessage msg = g.fromJson(payload, WsMessage.class);
                    System.out.println(payload);

                    switch (msg.type) {
                        case "started":
                            recordingStarted();
                            break;
                        case "stopped":
                            recordingStopped();
                            break;
                        case "saved":
                            App.waveformClient.generateDat(msg.id);
                            recordingSaved();
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

    public void record(Integer channels, Integer sampleRate) {
        JSONObject opts = new JSONObject();
        opts.put("channels", channels);
        opts.put("sampleRate", sampleRate);
        JSONObject json = new JSONObject();
        json.put("type", "record");
        json.put("opts", opts);
        this.ws.sendText(json.format(0).toString().replace("\n", ""));
    }

    public void stop() {
        JSONObject json = new JSONObject();
        json.put("type", "stop");
        this.ws.sendText(json.format(0).toString().replace("\n", ""));
    }

    public void save(String filename) {
        JSONObject opts = new JSONObject();
        opts.put("filename", filename);
        JSONObject json = new JSONObject();
        json.put("type", "save");
        json.put("opts", opts);
        this.ws.sendText(json.format(0).toString().replace("\n", ""));
    }

    public void discard() {
        JSONObject json = new JSONObject();
        json.put("type", "discard");
        this.ws.sendText(json.format(0).toString().replace("\n", ""));
    }


    public void recordingStarted() {
        for (Runnable fn : onStartHandlers) fn.run();
    }

    public void recordingStopped() {
        for (Runnable fn : onStopHandlers) fn.run();
    }

    public void recordingSaved() {
        for (Runnable fn : onSaveHandlers) fn.run();
    }

    public void onRecordingStarted(Runnable fn) {
        onStartHandlers.add(fn);
    }

    public void onRecordingStopped(Runnable fn) {
        onStopHandlers.add(fn);
    }

    public void onRecordingSaved(Runnable fn) {
        onSaveHandlers.add(fn);
    }

}