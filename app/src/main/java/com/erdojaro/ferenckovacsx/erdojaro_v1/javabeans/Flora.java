package com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans;

/**
 * Created by ferenckovacsx on 2017-05-24.
 */

public class Flora {

    public int Id;
    public String Name;
    public String LatinName;
    public int ImageID;
    public String Description;

    public Flora(int id, String name, String latinName, int imageID, String description) {
        Id = id;
        Name = name;
        LatinName = latinName;
        ImageID = imageID;
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLatinName() {
        return LatinName;
    }

    public void setLatinName(String latinName) {
        LatinName = latinName;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Flora{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", LatinName='" + LatinName + '\'' +
                ", ImageID=" + ImageID +
                ", Description='" + Description + '\'' +
                '}';
    }
}