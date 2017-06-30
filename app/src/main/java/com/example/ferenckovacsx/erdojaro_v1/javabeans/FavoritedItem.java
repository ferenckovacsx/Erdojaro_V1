package com.example.ferenckovacsx.erdojaro_v1.javabeans;

/**
 * Created by ferenckovacsx on 2017-06-30.
 */

public class FavoritedItem {

    String Type;
    String Name;
    int ImageId;

    public FavoritedItem(String type, String name, int imageId) {
        Type = type;
        Name = name;
        ImageId = imageId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    @Override
    public String toString() {
        return "FavoritedItem{" +
                "Type='" + Type + '\'' +
                ", Name='" + Name + '\'' +
                ", ImageId=" + ImageId +
                '}';
    }
}
