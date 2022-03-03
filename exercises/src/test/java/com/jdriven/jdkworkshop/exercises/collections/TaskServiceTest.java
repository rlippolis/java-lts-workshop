package com.jdriven.jdkworkshop.exercises.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Prepare Workshop",
                "Prepare a JDK Workshop by creating slides and exercises.", Duration.ofDays(2)));
        tasks.add(new Task("Shopping", "Pick up some groceries", Duration.ofHours(1)));
        tasks.add(new Task("Work", "Do some work at the office", Duration.ofHours(8)));
        tasks.add(new Task("Dishes", "Put the dishes in the dish washer", Duration.ofMinutes(9)));
        tasks.add(new Task("Prepare Lunch", "Prepare lunch for today", Duration.ofMinutes(3)));
        taskService = new TaskService(tasks);
    }

    @Test
    public void testTaskListIsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class,
                () -> taskService.getTasks().clear()
        );
    }

    @Test
    public void testShortTaskListIsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class,
                () -> taskService.getShortTasks().clear()
        );
    }

    @Test
    public void testMapIsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class,
                () -> taskService.getTasksByName().clear()
        );
    }

    @Test
    public void testAverageTaskDuration() {
        assertEquals(686L, taskService.getAverageTaskDurationInMinutes());
    }
}
