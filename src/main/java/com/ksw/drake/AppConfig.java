package com.ksw.drake;

import com.ksw.drake.repository.JDBCScheduleRepository;
import com.ksw.drake.repository.ScheduleRepository;
import com.ksw.drake.service.ScheduleService;
import com.ksw.drake.service.ScheduleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    private DataSource dataSource;

    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ScheduleRepository scheduleRepository() {
        return new JDBCScheduleRepository(dataSource);
    }

    @Bean
    public ScheduleService scheduleService() {
        return new ScheduleServiceImpl(scheduleRepository());
    }

}
