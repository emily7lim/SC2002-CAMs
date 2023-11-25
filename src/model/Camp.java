package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import model.enums.Faculty;

/**
 * Camp created by Staff
 * 
 * @author Deuel, Owen, Chloie
 * @version 3.1.3
 * @since 2023-10-30
 */
public class Camp implements Serializable {
    protected static final long serialVersionUID = 2L; // Serializable Unique Identifier
    private String campId; // ID of the Camp
    private String name; // Name of the Camp
    private Date startDate; // Start Date of the Camp
    private Date endDate; // End Date of the Camp
    private Date registrationCloseDate; // Registration Close Date of the Camp
    private Faculty userGroup; // User Group of the Camp
    private String location; // Location of the Camp
    private int totalSlots; // Total Slots of the Camp
    private int commSlots; // Committee Slots of the Camp
    private String description; // Description of the Camp
    private String staffInCharge; // Staff in Charge of the Camp
    private ArrayList<String> withdrawnParticipantIds; // IDs of the Camp's Withdrawn Participants
    private ArrayList<String> participantIds; // IDs of the Camp's Participants
    private ArrayList<String> committeeIds; // IDs of the Camp's Committee
    private ArrayList<String> enquiryIds; // IDs of the Camp's Enquiries
    private ArrayList<String> suggestionIds; // IDs of the Camp's Suggestions
    private boolean visible; // Visibility of the Camp

    /**
     * Default constructor
     */
    public Camp() {
        this.name = null;
        this.startDate = null;
        this.endDate = null;
        this.registrationCloseDate = null;
        this.userGroup = null;
        this.location = null;
        this.totalSlots = 0;
        this.commSlots = 0;
        this.description = null;
    }

    /**
     * Constructs and initializes a Camp object with Name, Start Date, End Date,
     * Registration Close Date, User Group, Location, Total Slots, Committee Slots,
     * Description and Staff in Charge
     * 
     * @param name                  The Name of the Camp
     * @param startDate             The Start Date of the Camp
     * @param endDate               The End Date of the Camp
     * @param registrationCloseDate The Registration Close Date of the Camp
     * @param userGroup             The Faculty that the Camp is open to
     * @param location              The Location of the Camp
     * @param totalSlots            The total number of slots of the Camp
     * @param commSlots             The number of committee slots of the Camp
     * @param description           The Description of the Camp
     * @param staffInCharge         The User ID of the Staff that created the Camp
     */
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
        this.withdrawnParticipantIds = new ArrayList<>();
        this.participantIds = new ArrayList<>();
        this.committeeIds = new ArrayList<>();
        this.enquiryIds = new ArrayList<>();
        this.suggestionIds = new ArrayList<>();
        this.visible = true;
    }

    /**
     * Gets the ID of the Camp
     * 
     * @return String The ID of the Camp
     */
    public String getCampId() {
        return campId;
    }

    /**
     * Sets the ID of the Camp
     * 
     * @param campId The ID of the Camp
     */
    public void setCampId(String campId) {
        this.campId = campId;
    }

    /**
     * Gets the Name of the Camp
     * 
     * @return String The Name of the Camp
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name of the Camp
     * 
     * @param name The Name of the Camp
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Start Date of the Camp
     * 
     * @return Date The Start Date of the Camp
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the Start Date of the Camp
     * 
     * @param startDate The Start Date of the Camp
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the End Date of the Camp
     * 
     * @return Date The End Date of the Camp
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the End Date of the Camp
     * 
     * @param endDate The End Date of the Camp
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the Registration Closing Date of the Camp
     * 
     * @return Date The Registration Closing Date of the Camp
     */
    public Date getRegistrationCloseDate() {
        return registrationCloseDate;
    }

    /**
     * Sets the Registration Closing Date of the Camp
     * 
     * @param registrationCloseDate The Registration Closing Date of the Camp
     */
    public void setRegistrationCloseDate(Date registrationCloseDate) {
        this.registrationCloseDate = registrationCloseDate;
    }

    /**
     * Gets the Faculty that the Camp is open to
     * 
     * @return Faculty The Faculty that the Camp is open to
     */
    public Faculty getUserGroup() {
        return userGroup;
    }

    /**
     * Sets the Faculty that the Camp is open to
     * 
     * @param userGroup The Faculty that the Camp is open to
     */
    public void setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
    }

    /**
     * Gets the Location of the Camp
     * 
     * @return String The Location of the Camp
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the Location of the Camp
     * 
     * @param location The Location of the Camp
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the total number of slots of the Camp
     * 
     * @return int The total number of slots of the Camp
     */
    public int getTotalSlots() {
        return totalSlots;
    }

    /**
     * Sets the total number of slots of the Camp
     * 
     * @param totalSlots The total number of slots of the Camp
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    /**
     * Gets the number of committee slots of the Camp
     * 
     * @return int The number of committee slots of the Camp
     */
    public int getCommSlots() {
        return commSlots;
    }

    /**
     * Sets the number of committee slots of the Camp
     * 
     * @param commSlots The number of committee slots of the Camp
     */
    public void setCommSlots(int commSlots) {
        this.commSlots = commSlots;
    }

    /**
     * Gets the Description of the Camp
     * 
     * @return String The Description of the Camp
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Description of the Camp
     * 
     * @param description The Description of the Camp
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the User ID of the Staff that created the Camp
     * 
     * @return String The User ID of the Staff that created the Camp
     */
    public String getStaffInCharge() {
        return staffInCharge;
    }

    /**
     * Sets the User ID of the Staff that created the Camp
     * 
     * @param staffInCharge The User ID of the Staff that created the Camp
     */
    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    /**
     * Gets the list of User IDs of Students who have withdrawn from the Camp
     * 
     * @return ArrayList<String> The list User IDs of Students who have withdrawn
     *         from the Camp
     */
    public ArrayList<String> getWithdrawnParticipantIds() {
        return withdrawnParticipantIds;
    }

    /**
     * Sets the list User IDs of Students who have withdrawn from the Camp
     * 
     * @param withdrawnParticipantIds The list User IDs of Students who have
     *                                withdrawn from the Camp
     */
    public void setWithdrawnParticipantIds(ArrayList<String> withdrawnParticipantIds) {
        this.withdrawnParticipantIds = withdrawnParticipantIds;
    }

    /**
     * Gets the list of User IDs of Students who are registered for the Camp as a
     * participant
     * 
     * @return ArrayList<String> The list of User IDs of Students who are registered
     *         for the Camp as a participant
     */
    public ArrayList<String> getParticipantIds() {
        return participantIds;
    }

    /**
     * Sets the list of User IDs of Students who are registered for the Camp as a
     * participant
     * 
     * @param participantIds The list of User IDs of Students who are registered for
     *                       the Camp as a participant
     */
    public void setParticipantIds(ArrayList<String> participantIds) {
        this.participantIds = participantIds;
    }

    /**
     * Gets the list of User IDs of Students who are registered for the Camp as a
     * committee member
     * 
     * @return ArrayList<String> The list of User IDs of Students who are registered
     *         for the Camp as a committee member
     */
    public ArrayList<String> getCommitteeIds() {
        return committeeIds;
    }

    /**
     * Sets the list of User IDs of Students who are registered for the Camp as a
     * committee member
     * 
     * @param committeeIds The list of User IDs of Students who are registered for
     *                     the Camp as a committee member
     */
    public void setCommitteeIds(ArrayList<String> committeeIds) {
        this.committeeIds = committeeIds;
    }

    /**
     * Gets the list of IDs of Enquiries of the Camp
     * 
     * @return ArrayList<String> The list of IDs of Enquiries of the Camp
     */
    public ArrayList<String> getEnquiryIds() {
        return enquiryIds;
    }

    /**
     * Sets the list of IDs of Enquiries of the Camp
     * 
     * @param enquiryIds The list of IDs of Enquiries of the Camp
     */
    public void setEnquiryIds(ArrayList<String> enquiryIds) {
        this.enquiryIds = enquiryIds;
    }

    /**
     * Gets the list of IDs of Suggestions of the Camp
     * 
     * @return ArrayList<String> The list of IDs of Suggestions of the Camp
     */
    public ArrayList<String> getSuggestionIds() {
        return suggestionIds;
    }

    /**
     * Sets the list of IDs of Suggestions of the Camp
     * 
     * @param suggestionIds The list of IDs of Suggestions of the Camp
     */
    public void setSuggestionIds(ArrayList<String> suggestionIds) {
        this.suggestionIds = suggestionIds;
    }

    /**
     * Gets the visibility of the Camp
     * 
     * @return boolean Whether the Camp is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the Visibility of the Camp
     * 
     * @param visible Whether the Camp is visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Gets the number of participant slots of the Camp
     * 
     * @return int The number of participant slots of the Camp
     */
    public int getParticipantSlots() {
        return totalSlots - commSlots;
    }

    /**
     * Gets the number of remaining participant slots of the Camp
     * 
     * @return int The number of remaining participant slots of the Camp
     */
    public int getRemainingParticipantSlots() {
        return totalSlots - commSlots - participantIds.size();
    }

    /**
     * Gets the number of remaining committee slots of the Camp
     * 
     * @return int The number of remaining committee slots of the Camp
     */
    public int getRemainingCommitteeSlots() {
        return commSlots - committeeIds.size();
    }

    /**
     * Adds the User ID of a Student to the list of Withdrawn Participants of the
     * Camp
     * 
     * @param userId The User ID of the Student that withdrew from the Camo
     */
    public void addWithdrawnParticipant(String userId) {
        this.withdrawnParticipantIds.add(userId);
    }

    /**
     * Adds the User ID of a Student to the list of Participants of the Camp
     * 
     * @param userId The User ID of the Student registering for the Camp as a
     *               participant
     */
    public void addParticipant(String userId) {
        this.participantIds.add(userId);
    }

    /**
     * Removes the User ID of a Student from the list of Participants of the Camp
     * 
     * @param userId The User ID of the Student
     */
    public void removeParticipant(String userId) {
        this.participantIds.remove(userId);
    }

    /**
     * Adds the User ID of a Student to the list of Committee Members of the Camp
     * 
     * @param userId The User ID of the Student registering for the Camp as a
     *               Committee Member
     */
    public void addCommittee(String userId) {
        this.committeeIds.add(userId);
    }

    /**
     * Adds the ID of an Enquiry to the list of Enquiries of the Camp
     * 
     * @param enquiryIds The ID of the Enquiry of the Camp
     */
    public void addEnquiry(String enquiryIds) {
        if (this.enquiryIds == null)
            this.enquiryIds = new ArrayList<>();
        this.enquiryIds.add(enquiryIds);
    }

    /**
     * Removes the ID of an Enquiry from the list of Enquiries of the Camp
     * 
     * @param enquiryId The ID of the Enquiry of the Camp
     */
    public void removeEnquiry(String enquiryId) {
        if (this.enquiryIds == null)
            this.enquiryIds = new ArrayList<>();
        else
            this.enquiryIds.remove(enquiryId);
    }

    /**
     * Adds the ID of a Suggestion to the list of Suggestions of the Camp
     * 
     * @param suggestionId The ID of the Suggestion of the Camp
     */
    public void addSuggestion(String suggestionId) {
        if (this.suggestionIds == null)
            this.suggestionIds = new ArrayList<>();
        this.suggestionIds.add(suggestionId);
    }

    /**
     * Removes the ID of a Suggestion to the list of Suggestions of the Camp
     * 
     * @param suggestionId The ID of the Suggestion of the Camp
     */
    public void removeSuggestion(String suggestionId) {
        if (this.suggestionIds == null)
            this.suggestionIds = new ArrayList<>();
        else
            this.suggestionIds.remove(suggestionId);
    }
}
