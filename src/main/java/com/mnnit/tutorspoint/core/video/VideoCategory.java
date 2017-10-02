package com.mnnit.tutorspoint.core.video;

public class VideoCategory {

    String name;
    int rating;

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
        return name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(final int rating) {
        this.rating = rating;
    }
}
