package com.jdriven.jdkworkshop.exercises.collections;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import com.jdriven.jdkworkshop.util.TODO;

public class TaskService {
    private final List<Task> tasks;

    public TaskService(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an Unmodifiable copy of the tasks
     * @return an Unmodifiable copy of the tasks
     */
    public List<Task> getTasks() {
        return TODO.implementMe();
    }

    /**
     * Returns an Unmodifiable list of all tasks that take less than 10 minutes
     * @return an Unmodifiable list of all tasks that take less than 10 minutes
     */
    public List<Task> getShortTasks() {
        return TODO.implementMe();
    }

    /**
     * Returns an Unmodifiable map of tasks mapped by their name
     * @return an Unmodifiable map of tasks mapped by their name
     */
    public Map<String, Task> getTasksByName() {
        return TODO.implementMe();
    }

    /**
     * Returns the average task duration in minutes.
     *
     * TIP: use the {@link Collectors#teeing(Collector, Collector, BiFunction)} method.
     *
     * @return the average task duration in minutes
     */
    public long getAverageTaskDurationInMinutes() {
        return TODO.implementMe();
    }
}
