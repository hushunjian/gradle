package com.hushunjian.gradle.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.hushunjian.gradle.entity.TaskCorn;
import com.hushunjian.gradle.repo.TaskCornRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
//@EnableScheduling
public class TaskTest implements SchedulingConfigurer {
	
	@Autowired
	private TaskCornRepo taskCornRepo;
	
	private String getTaskCorn(){
		String cron = "0/5 * * * * ?";
		TaskCorn taskCorn = taskCornRepo.findByTaskName("taskName");
		cron = taskCorn == null ? cron : taskCorn.getTaskCorn();
		return cron;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				log.info("执行定时任务");
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String corn = getTaskCorn();
				log.info("获取定时任务执行时间");
				CronTrigger trigger = new CronTrigger(corn);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
			}
		});
	}

}
