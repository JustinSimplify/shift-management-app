package org.acme.Entity;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ShiftDTO {
    private UUID id;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private String hierarchy;
    private String location;
    private String timeZone;

    public ShiftDTO() {
    }

    public ShiftDTO(OffsetDateTime startTime, UUID id, OffsetDateTime endTime, String hierarchy, String location, String timeZone) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.hierarchy = hierarchy;
        this.location = location;
        this.timeZone = timeZone;
        this.id = id;
    }

    public ShiftDTO(Shift shift) {
        this.startTime = shift.getStartTime();
        this.endTime = shift.getEndTime();
        this.hierarchy = shift.getHierarchy();
        this.location = shift.getLocation();
        this.timeZone = shift.getTimeZone();
        this.id = shift.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

}
