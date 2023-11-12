package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import model.enums.Faculty;

public class Camp implements Serializable {
    protected static final long serialVersionUID = 2L;
    private String campId;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date registrationCloseDate;
    private Faculty userGroup;
    private String location;
    private int totalSlots;
    private int commSlots;
    private String description;
    private String staffInCharge;
    private ArrayList<String> withdrawnParticipantIds;
    private ArrayList<String> participantIds;
    private ArrayList<String> committeeIds;
    private boolean visible;

    // Updating camp information
    public Camp(String name, Date startDate, Date endDate, Date registrationCloseDate, Faculty userGroup,
            String location, int totalSlots, int commSlots, String description) {
        this.campId = UUID.randomUUID().toString();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationCloseDate = registrationCloseDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.commSlots = commSlots;
        this.description = description;
    }

    public Camp(String name, Date startDate, Date endDate, Date registrationCloseDate, Faculty userGroup,
            String location, int totalSlots, int commSlots, String description, String staffInCharge) {
        this.campId = UUID.randomUUID().toString();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationCloseDate = registrationCloseDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.commSlots = commSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.participantIds = new ArrayList<>();
        this.committeeIds = new ArrayList<>();
        this.visible = true;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
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

    public Faculty getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Faculty userGroup) {
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

    public int getCommSlots() {
        return commSlots;
    }

    public void setCommSlots(int commSlots) {
        this.commSlots = commSlots;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    public ArrayList<String> getWithdrawnParticipantIds() {
        return withdrawnParticipantIds;
    }

    public void setWithdrawnParticipantIds(ArrayList<String> withdrawnParticipantIds) {
        this.withdrawnParticipantIds = withdrawnParticipantIds;
    }

    public ArrayList<String> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(ArrayList<String> participantIds) {
        this.participantIds = participantIds;
    }

    public ArrayList<String> getCommitteeIds() {
        return committeeIds;
    }

    public void setCommitteeIds(ArrayList<String> committeeIds) {
        this.committeeIds = committeeIds;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void addWithdrawnParticipant(String userId) {
        this.withdrawnParticipantIds.add(userId);
    }

    public void addParticipant(String userId) {
        this.participantIds.add(userId);
    }

    public void removeParticipant(String userId) {
        this.participantIds.remove(userId);
    }

    public void addCommittee(String userId) {
        this.committeeIds.add(userId);
    }
}
