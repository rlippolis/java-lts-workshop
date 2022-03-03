package com.jdriven.jdkworkshop.exercises.collections;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        return List.copyOf(tasks);
    }

    /**
     * Returns an Unmodifiable list of all tasks that take less than 10 minutes
     * @return an Unmodifiable list of all tasks that take less than 10 minutes
     */
    public List<Task> getShortTasks() {
        return tasks.stream()
                .filter(task -> task.getDuration().minus(Duration.ofMinutes(10)).isNegative())
                .toList(); // or: .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Returns an Unmodifiable map of tasks mapped by their name
     * @return an Unmodifiable map of tasks mapped by their name
     */
    public Map<String, Task> getTasksByName() {
        return tasks.stream()
                .collect(Collectors.toUnmodifiableMap(Task::getName, Function.identity()));
    }

    /**
     * Returns the average task duration in minutes.
     *
     * TIP: use the {@link Collectors#teeing(Collector, Collector, BiFunction)} method.
     *
     * @return the average task duration in minutes
     */
    public long getAverageTaskDurationInMinutes() {
        return tasks.stream().collect(Collectors.teeing(Collectors.summingLong(t -> t.getDuration().toMinutes()), Collectors.counting(), (sum, count) -> sum / count));
    }
}
