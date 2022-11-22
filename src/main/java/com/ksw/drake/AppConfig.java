package com.ksw.drake;

import com.ksw.drake.repository.JDBCMemoRepository;
import com.ksw.drake.repository.JDBCScheduleRepository;
import com.ksw.drake.repository.MemoRepository;
import com.ksw.drake.repository.ScheduleRepository;
import com.ksw.drake.service.MemoService;
import com.ksw.drake.service.MemoServiceImpl;
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

    @Bean
    public MemoService memoService() {
        return new MemoServiceImpl(memoRepository());
    }

    @Bean
    public MemoRepository memoRepository() {
        return new JDBCMemoRepository(dataSource);
    }

}
