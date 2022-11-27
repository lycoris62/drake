package com.ksw.drake.dto;

public class ScheduleResponseDTO {
    private String targetDate;
    private String scheduleName;

    public ScheduleResponseDTO(String targetDate, String scheduleName) {
        this.targetDate = targetDate;
        this.scheduleName = scheduleName;
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
}
