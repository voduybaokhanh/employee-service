package com.example.employee_service.event;

import com.example.employee_service.entity.Task;
import org.springframework.context.ApplicationEvent;

public class TaskAssignedEvent extends ApplicationEvent {
	private final Task task;
	private final String assignedBy;

	public TaskAssignedEvent(Object source, Task task, String assignedBy) {
		super(source);
		this.task = task;
		this.assignedBy = assignedBy;
	}

	public Task getTask() {
		return task;
	}

	public String getAssignedBy() {
		return assignedBy;
	}
}
