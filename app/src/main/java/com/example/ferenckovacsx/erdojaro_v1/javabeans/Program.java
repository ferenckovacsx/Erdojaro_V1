package com.example.ferenckovacsx.erdojaro_v1.javabeans;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */

public class Program {
    public String name;
    public int imageID;

    public Program(String name, int imageID) {
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    @Override
    public String toString() {
        return "Program{" +
                "Name='" + name + '\'' +
                ", ImageUrl=" + imageID +
                '}';
    }
}
