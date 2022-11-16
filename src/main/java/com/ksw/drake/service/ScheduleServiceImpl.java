package com.ksw.drake.service;

import com.ksw.drake.dto.ScheduleDTO;
import com.ksw.drake.repository.ScheduleRepository;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService{

    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDTO save(ScheduleDTO user) {
        return scheduleRepository.save(user);
    }

    @Override
    public ScheduleDTO findById(Long id) {
        return null;
    }

    @Override
    public List<ScheduleDTO> findAll() {
        return null;
    }
}
