package com.ksw.drake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTest {

    @Test
    @DisplayName("날짜/시간 체크")
    void dateCheck() {
//        LocalDate localDate = LocalDate.of(2022, 11, 23);
//        LocalTime localTime = LocalTime.of(21, 43, 7);
//        LocalDateTime localDateTimeShort = LocalDateTime.of(localDate, localTime);
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 23, 21,43,7);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Seoul"));
        String localDateTimePattern = localDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        System.out.println("zonedDateTime = " + zonedDateTime);
        System.out.println("localDateTimePattern = " + localDateTimePattern);
    }

}
