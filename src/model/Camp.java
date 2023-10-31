package model;

import java.util.Date;

public class Camp {
    private int campId;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date registrationCloseDate;
    private String userGroup;
    private String location;
    private int totalSlots;
    private int campCommSlots;
    private String description;
    private String staffIC;

    public Camp(int campId, String name, Date startDate, Date endDate, Date registrationCloseDate,
            String userGroup, String location, int totalSlots, int campCommSlots, String description, String staffIC) {
        this.campId = campId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationCloseDate = registrationCloseDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommSlots = campCommSlots;
        this.description = description;
        this.staffIC = staffIC;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRegistrationCloseDate() {
        return registrationCloseDate;
    }

    public void setRegistrationCloseDate(Date registrationCloseDate) {
        this.registrationCloseDate = registrationCloseDate;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getCampCommSlots() {
        return campCommSlots;
    }

    public void setCampCommSlots(int campCommSlots) {
        this.campCommSlots = campCommSlots;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStaffIC() {
        return staffIC;
    }

    public void setStaffIC(String staffIC) {
        this.staffIC = staffIC;
    }

}
