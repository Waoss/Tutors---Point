package com.mnnit.tutorspoint.core.video;

public class VideoCategory {

    private String name;
    private int rating;
    private String parentName;

    public VideoCategory(final String name, final int rating, final String parentName) {
        this.name = name;
        this.rating = rating;
        this.parentName = parentName;
    }

    public VideoCategory(final String name, final int rating) {
        this.name = name;
        this.rating = rating;
    }

    public VideoCategory() {
    }

    public VideoCategory(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "VideoCategory{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", parentName='" + parentName + '\'' +
                '}';
    }

    public int getRating() {
        return rating;
    }

    public void setRating(final int rating) {
        this.rating = rating;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(final String parentName) {
        this.parentName = parentName;
    }
}
