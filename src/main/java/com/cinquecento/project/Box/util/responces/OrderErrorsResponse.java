package com.cinquecento.project.Box.util.responces;

import java.time.LocalDateTime;

public class OrderErrorsResponse {
    private String name;
    private LocalDateTime timeStamp;

    public OrderErrorsResponse(String name, LocalDateTime timeStamp) {
        this.name = name;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
