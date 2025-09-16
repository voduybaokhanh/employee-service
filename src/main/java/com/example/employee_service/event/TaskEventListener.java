package com.example.employee_service.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskEventListener {
	@Async
	@EventListener
	public void handleTaskAssigned(TaskAssignedEvent event) {
		// Notification logic (email, message queue, etc.) would be here
		log.info("Async notification: Task {} assigned to {} by {}", event.getTask().getId(),
			event.getTask().getEmployee().getId(), event.getAssignedBy());
	}
}
