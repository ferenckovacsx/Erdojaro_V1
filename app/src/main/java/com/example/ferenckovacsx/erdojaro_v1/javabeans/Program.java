package com.example.ferenckovacsx.erdojaro_v1.javabeans;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */

public class Program {
    public int Id;
    public String Title;
    public String SubTitle;
    public int ImageId;
    public String Description;

    public Program(int id, String title, String subTitle, int imageId, String description) {
        Id = id;
        Title = title;
        SubTitle = subTitle;
        ImageId = imageId;
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        this.SubTitle = subTitle;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        this.ImageId = imageId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Program{" +
                "Title='" + Title + '\'' +
                ", SubTitle='" + SubTitle + '\'' +
                ", ImageId=" + ImageId +
                ", Description='" + Description + '\'' +
                '}';
    }
}
