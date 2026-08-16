package com.prathamesh.mock;

import com.prathamesh.mock.controllers.TaskController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    private final TaskController controller = new TaskController();

    @Test
    void shouldReturnTasks() {
        assertFalse(controller.getTasks().isEmpty());
    }

    @Test
    void shouldReturnHealthStatus() {
        assertEquals("Application is healthy", controller.health());
    }

    @Test
    void shouldFindTaskById() {
        assertNotNull(controller.getTask(1L));
    }

    @Test
    void shouldReturnNullForUnknownTask() {
        assertNull(controller.getTask(999L));
    }
}