package com.ksw.drake.repository;

import com.ksw.drake.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleRepository {

    public ScheduleDTO save(ScheduleDTO user);

    public ScheduleDTO findById(Long id);

    public List<ScheduleDTO> findAll();
}
