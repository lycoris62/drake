package com.ksw.drake.dto;

import java.time.LocalDateTime;

public class ScheduleDTO {
    private String memberId;
    private LocalDateTime targetDate;
    private String scheduleName;

    public ScheduleDTO(String memberId, LocalDateTime targetDate, String scheduleName) {
        this.memberId = memberId;
        this.targetDate = targetDate;
        this.scheduleName = scheduleName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDateTime targetDate) {
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
                ", targetDate=" + targetDate +
                ", scheduleName='" + scheduleName + '\'' +
                '}';
    }
}
