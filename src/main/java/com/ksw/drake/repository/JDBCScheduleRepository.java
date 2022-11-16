package com.ksw.drake.repository;

import com.ksw.drake.dto.ScheduleDTO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JDBCScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JDBCScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleDTO save(ScheduleDTO schedule) {
        String query = "INSERT INTO public.\"Schedule\"(schedule_name, target_date, member_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, schedule.getScheduleName(), schedule.getTargetDate(), schedule.getMemberId());
        String sql = "Select schedule_name, target_date, member_id from public.\"Schedule\" order by schedule_id DESC limit 1";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ScheduleDTO scheduleDB = new ScheduleDTO();
            scheduleDB.setScheduleName(rs.getString("schedule_name"));
            scheduleDB.setTargetDate(rs.getDate("target_date"));
            scheduleDB.setMemberId(rs.getString("member_id"));
            return scheduleDB;
        }).get(0);
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
