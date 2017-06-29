package com.example.ferenckovacsx.erdojaro_v1.javabeans;

/**
 * Created by ferenckovacsx on 2017-05-24.
 */

public class OptionListItem {

    public String title;
    public int iconID;

    public OptionListItem(String title, int iconID) {
        this.title = title;
        this.iconID = iconID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    @Override
    public String toString() {
        return "OptionListItem{" +
                "Title='" + title + '\'' +
                ", iconID=" + iconID +
                '}';
    }
}
