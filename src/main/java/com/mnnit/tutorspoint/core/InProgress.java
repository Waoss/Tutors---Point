package com.mnnit.tutorspoint.core;

import com.mnnit.tutorspoint.core.video.VideoCategory;

public class InProgress {
    private VideoCategory category;
    private String student;

    public VideoCategory getCategory() {
        return category;
    }

    public void setCategory(final VideoCategory category) {
        this.category = category;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(final String student) {
        this.student = student;
    }
}
