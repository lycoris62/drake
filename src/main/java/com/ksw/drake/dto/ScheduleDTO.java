package com.ksw.drake.dto;

import java.time.LocalDateTime;

public class ScheduleDTO {
    private String memberId;
    private LocalDateTime localDateTime;
    private String targetDate;
    private String scheduleName;

    public ScheduleDTO(String memberId, LocalDateTime localDateTime, String targetDate, String scheduleName) {
        this.memberId = memberId;
        this.localDateTime = localDateTime;
        this.targetDate = targetDate;
        this.scheduleName = scheduleName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "memberId='" + memberId + '\'' +
                ", localDateTime=" + localDateTime +
                ", targetDate='" + targetDate + '\'' +
                ", scheduleName='" + scheduleName + '\'' +
                '}';
    }
}
