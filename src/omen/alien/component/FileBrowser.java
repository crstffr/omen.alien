package omen.alien.component;

import omen.alien.Const;
import omen.alien.component.layer.Layer;
import omen.alien.definition.SampleCollectionItem;

import java.util.*;

public class FileBrowser {

    Layer layer;
    int offset = 0;
    int maxnum = 19;
    int selected = 0;
    int color = 255;
    boolean ready = false;

    ArrayList<SampleCollectionItem> items = new ArrayList<>();
    ArrayList<FileBrowserRow> rows = new ArrayList<>();

    public FileBrowser(int _x, int _y, int _w, int _h) {
        layer = new Layer(_x, _y, _w, _h, Const.RENDERER2D);
    }

    public void setColor(int _color) {
        if (_color != color) {
            color = _color;
        }
    }

    public void addItem(SampleCollectionItem item) {
        items.add(item);
    }

    public SampleCollectionItem getSelectedItem() {
        return items.get(selected);
    }

    public Integer getItemCount() {
        return items.size();
    }

    public void scrollUp() {
        setOffset(offset - 1);
    }

    public void scrollDown() {
        setOffset(offset + 1);
    }

    public void setOffset(int _offset) {
        if (offset != _offset) {
            if (_offset >= 0 && _offset <= items.size() - maxnum) {
                offset = _offset;
                clearRows();
                renderRows();
            }
        }
    }

    public void selectPrev() {
        if (selected > 0) {
            selectItem(selected - 1);
            if (selected < offset) {
                scrollUp();
            }
        }
    }

    public void selectNext() {
        if (selected < items.size() - 1) {
            selectItem(selected + 1);
            if (selected >= offset + maxnum) {
                scrollDown();
            }
        }
    }

    public void selectItem(int index) {
        items.get(selected).selected = false;
        items.get(index).selected = true;
        selected = index;
    }

    public void selectDefaultItem() {
        if (items.size() > 0) {
            selectItem(0);
        }
    }

    public void unselectItem() {
        items.get(selected).selected = false;
        selected = 0;
    }

    public synchronized void removeSelectedItem() {
        ready = false;
        items.remove(selected);
        if (items.size() > 0) {
            if (selected > items.size() - 1) {
                selected = items.size() - 1;
            }
            items.get(selected).selected = true;
            clearRows();
            renderRows();
        } else {
            clearAll();
        }
    }

    public void clearAll() {
        ready = false;
        clearRows();
        items.clear();
        selected = 0;
        offset = 0;
    }

    public void resetRows() {
        ready = false;
        unselectItem();
        clearRows();
        offset = 0;
    }

    public synchronized void clearRows() {
        rows.forEach((FileBrowserRow row) -> {
            row.clear();
        });
        rows.clear();
    }

    public synchronized void renderRows() {
        for (int i = offset; i < offset + maxnum; i++) {
            if (i < items.size()) {
                SampleCollectionItem item = items.get(i);
                FileBrowserRow row = new FileBrowserRow(layer, rows.size(), item);
                row.setColor(color);
                rows.add(row);
            }
        }
        ready = true;
    }

    public void sortBy(String field, Boolean asc) {
        if (items.size() > 0) {
            resetRows();
            switch (field) {
                case "ALPHA":
                    Collections.sort(items, new Comparator<SampleCollectionItem>() {
                        public int compare(SampleCollectionItem a, SampleCollectionItem b) {
                            return asc ? a.name.compareTo(b.name)
                                    : b.name.compareTo(a.name);
                        }
                    });
                    break;
                case "DATE":
                    Collections.sort(items, new Comparator<SampleCollectionItem>() {
                        public int compare(SampleCollectionItem a, SampleCollectionItem b) {
                            return asc ? a.created.compareTo(b.created)
                                    : b.created.compareTo(a.created);
                        }
                    });
                    break;
                case "LENGTH":
                    Collections.sort(items, new Comparator<SampleCollectionItem>() {
                        public int compare(SampleCollectionItem a, SampleCollectionItem b) {
                            return asc ? a.length.compareTo(b.length)
                                    : b.length.compareTo(a.length);
                        }
                    });
                    break;
            }
            renderRows();
            selectDefaultItem();
        }
    }

    public synchronized void draw() {
        if (ready) {
            rows.forEach((FileBrowserRow row) -> {
                row.draw();
            });
        }
    }

}
