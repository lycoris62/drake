package com.ksw.drake.controller;

import com.ksw.drake.dto.ScheduleDTO;
import com.ksw.drake.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/api/schedule")
    public ScheduleDTO save(@RequestParam String name) {
        ScheduleDTO schedule = new ScheduleDTO();
        schedule.setScheduleName(name);
        schedule.setMemberId("1");
        schedule.setTargetDate(new Date());
        return scheduleService.save(schedule);
    }
}
