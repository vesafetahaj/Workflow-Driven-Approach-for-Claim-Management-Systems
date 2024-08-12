package com.claims.claims.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/process")
public class ProcessController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/start")
    public String startProcess(@RequestBody Map<String, Object> formData) {
        runtimeService.startProcessInstanceByKey("formProcess", formData);
        return "Process started";
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(@RequestParam String userId) {
        return taskService.createTaskQuery().taskCandidateUser(userId).list();
    }

    @PostMapping("/tasks/{taskId}/complete")
    public String completeTask(@PathVariable String taskId, @RequestBody Map<String, Object> formData) {
        taskService.complete(taskId, formData);
        return "Task completed";
    }
}
