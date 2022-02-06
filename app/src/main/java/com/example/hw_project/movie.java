package com.example.hw_project;

public class movie {
    private String Key, Title, Category, Description, Thumbnail;
    private int Rate;


    public movie() {
    }

    public movie(String key, String title, String category, String description, String thumbnail, int rate) {
        Key=key;
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
        Rate = rate;
    }
    public String getKey() {return Key;}

    public int getRate() {return Rate;}

    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setKey(String key) { Key= key;}

    public void setRate(int rate) {Rate = rate;}

    public void setTitle(String title) {
        Title = title;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
}
