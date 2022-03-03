package com.jdriven.jdkworkshop.exercises.collections;

import java.time.Duration;

public class Task {
    private final String name;
    private final String description;
    private final Duration duration;

    public Task(String name, String description, Duration duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Duration getDuration() {
        return duration;
    }
}
