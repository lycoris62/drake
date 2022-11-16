package com.ksw.drake.service;

import com.ksw.drake.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {

    public ScheduleDTO save(ScheduleDTO user);

    public ScheduleDTO findById(Long id);

    public List<ScheduleDTO> findAll();
}
