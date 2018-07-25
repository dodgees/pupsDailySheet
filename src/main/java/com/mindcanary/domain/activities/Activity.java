package com.mindcanary.domain.activities;

import java.time.LocalDateTime;
import java.util.List;

public class Activity {

    private LocalDateTime timeOccurred;

    private String information;

    private List<String> base64Pictures;

    private ActivityType activityType;

    public LocalDateTime getTimeOccurred() {

        return timeOccurred;
    }

    public void setTimeOccurred(LocalDateTime timeOccurred) {
        this.timeOccurred = timeOccurred;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<String> getBase64Pictures() {
        return base64Pictures;
    }

    public void setBase64Pictures(List<String> base64Pictures) {
        this.base64Pictures = base64Pictures;
    }
}
