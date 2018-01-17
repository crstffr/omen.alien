package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.Layer;
import omen.alien.component.layer.StageLayer;

import java.util.ArrayList;

public class ConfirmDialog {

    ArrayList<Runnable> onCancelHandlers = new ArrayList<>();
    ArrayList<Runnable> onConfirmHandlers = new ArrayList<>();

    Layer headerLayer;
    Layer bodyLayer;
    Layer boxLayer;
    Layer bgLayer;

    int color = 255;

    String headerText = "";
    String bodyText = "";

    int x = Const.CONFIRM_DIALOG_X;
    int y = Const.CONFIRM_DIALOG_Y;
    int w = Const.CONFIRM_DIALOG_W;
    int h = Const.CONFIRM_DIALOG_H;

    public ConfirmDialog() {
        bgLayer = new StageLayer();
        boxLayer = new Layer(x, y, w, h, Const.RENDERER2D);
        bodyLayer = new Layer(x + 10, y + 110, w - 20, h - 120, Const.RENDERER2D);
        headerLayer = new Layer(x + 10, y + 10, w - 20, 100, Const.RENDERER2D);
    }

    public void setColor(int _color) {
        if (_color != color) {
            color = _color;
        }
    }

    public void setHeaderText(String text) {
        headerText = text;
    }

    public void setBodyText(String text) {
        bodyText = text;
    }

    public void confirm() {
        for (Runnable fn : onConfirmHandlers) fn.run();
    }

    public void onConfirm(Runnable fn) {
        onConfirmHandlers.add(fn);
    }

    public void cancel() {
        for (Runnable fn : onCancelHandlers) fn.run();
    }

    public void onCancel(Runnable fn) {
        onCancelHandlers.add(fn);
    }

    public void draw() {
        bgLayer.init();
        boxLayer.init();
        bodyLayer.init();
        headerLayer.init();

        bgLayer.canvas.fill(Const.DIALOG_BACKGROUND);
        bgLayer.canvas.rect(0, 0, bgLayer.w, bgLayer.h);

        boxLayer.canvas.fill(Const.GRAY);
        boxLayer.canvas.stroke(color);
        boxLayer.canvas.rect(0, 0, boxLayer.w - 1, boxLayer.h - 1);

        headerLayer.canvas.fill(color);
        headerLayer.canvas.textFont(App.font, 35);
        headerLayer.canvas.textAlign(Const.CENTER, Const.CENTER);
        headerLayer.canvas.text(headerText, headerLayer.mid_x, headerLayer.mid_y);

        bodyLayer.canvas.fill(color);
        bodyLayer.canvas.textFont(App.font, 22);
        bodyLayer.canvas.textAlign(Const.CENTER, Const.TOP);
        bodyLayer.canvas.text(bodyText, 0, 0, bodyLayer.w, bodyLayer.h);

        bgLayer.draw();
        boxLayer.draw();
        bodyLayer.draw();
        headerLayer.draw();
    }

}
