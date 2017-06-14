package com.example.ferenckovacsx.erdojaro_v1.javabeans;

/**
 * Created by ferenckovacsx on 2017-05-24.
 */

public class Flora {

    public String name;
    public int imageID;
    public String description;

    public Flora(String name, int imageID, String description) {
        this.name = name;
        this.imageID = imageID;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Flora{" +
                "Name='" + name + '\'' +
                ", ImageUrl=" + imageID +
                ", Description='" + description + '\'' +
                '}';
    }
}
