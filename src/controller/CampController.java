package controller;

import model.Camp;
import model.enums.Faculty;

import java.util.ArrayList;
import java.util.Date;

import database.CampDAO;

public class CampController {
    /**
     * Creates a new Camp and add to the database
     * 
     * @param name                  The name of the new Camp
     * @param startDate             The start date of the new Camp
     * @param endDate               The end date of the new Camp
     * @param registrationCloseDate The date that registration closes for the new
     *                              Camp
     * @param userGroup             The user group of the new Camp
     * @param location              The location of the new Camp
     * @param totalSlots            The total number of slots for the new Camp
     * @param commSlots             The number of committee member slots for the new
     *                              Camp
     * @param description           The description of the new Camp
     * @param staffInCharge         The staff in charge of the new Camp
     * @return String The Camp ID of the newly created Camp
     */
    public static String createCamp(String name, Date startDate, Date endDate, Date registrationCloseDate,
            Faculty userGroup, String location, int totalSlots, int commSlots, String description,
            String staffInCharge) {
        Camp camp = new Camp(name, startDate, endDate, registrationCloseDate, userGroup, location,
                totalSlots, commSlots, description, staffInCharge);
        CampDAO.createCamp(camp);

        return camp.getCampId();
    }

    /**
     * Retrieves a list of all Camps from the database
     * 
     * @return ArrayList<Camp> The list of all the Camps
     */
    public static ArrayList<Camp> getAllCamps() {
        return CampDAO.getAllCamps();
    }

    /**
     * Retrieves a list of all Camps from the database created by the Staff using
     * the User ID
     * 
     * @param userId The User ID of the Staff in Charge
     * @return ArrayList<Camp> The list of all the Camps with corresponsing Staff in
     *         Charge
     */
    public static ArrayList<Camp> getStaffCamps(String userId) {
        return CampDAO.getCampsByCreatorId(userId);
    }

    /**
     * Retrieves a list of all future Camps from the database created by the Staff
     * using the User ID
     * 
     * @param userId The User ID of the Staff in Charge
     * @return ArrayList<Camp> The list of all the Camps with corresponsing Staff in
     *         Charge
     */
    public static ArrayList<Camp> getStaffFutureCamps(String userId) {
        return CampDAO.getCampsByCreatorIdAfterDate(userId, new Date());
    }

    /**
     * Retrieves a list of all Camps with Committee Member using User ID
     * 
     * @param userId The User ID of the Committee Member
     * @return ArrayList<Camp> The list of all the Camps with corresponding
     *         Committee Member
     */
    public static ArrayList<Camp> getCommitteeCamps(String userId) {
        return CampDAO.getCampsByCommitteeId(userId);
    }

    /**
     * Retrieves a list of all past Camps with Committee Member using User ID
     * 
     * @param userId The User ID of the Committee Member
     * @return ArrayList<Camp> The list of all the Camps with the corresponsing
     *         Committee Member
     */
    public static ArrayList<Camp> getCommitteePastCamps(String userId) {
        return CampDAO.getCampsByCommitteeIdBeforeDate(userId, new Date());
    }

    /**
     * Retireves the current Camp of Committee Member using User ID
     * 
     * @param userId The User ID of the Committee Member
     * @return Camp The current Camp of the Committee Member
     */
    public static Camp getCommitteeCurrentCamp(String userId) {
        return CampDAO.getCampsByCommitteeIdAfterDate(userId, new Date());
    }

    /**
     * Retrieves a list of all available Camps for the Student using Faculty
     * 
     * @param faculty The Faculty of the User
     * @return ArrayList<Camp> The list of all the Camps with corresponsing Faculty
     */
    public static ArrayList<Camp> getStudentAvailableCamps(Faculty faculty) {
        return CampDAO.getCampsByUserGroupAndDateAndVisibility(faculty, new Date(), true);
    }

    /**
     * Retrieves a list of all available ungeristered Camps for the Student by
     * Faculty and User ID
     * 
     * @param faculty       The Faculty of the User
     * @param participantId The User ID of the Student
     * @return ArrayList<Camp> The list of all the Camps with corresponsing Faculty
     *         that the Student has not registered for
     */
    public static ArrayList<Camp> getStudentAvailableUnregisteredCamps(Faculty faculty, String participantId) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : CampDAO.getCampsByUserGroupAndDateAndVisibility(faculty, new Date(), true))
            if (!(camp.getRegistrationCloseDate().before(new Date()) || camp.getCommitteeIds().contains(participantId)
                    || camp.getParticipantIds().contains(participantId)
                    || (camp.getRemainingCommitteeSlots() == 0 && camp.getRemainingParticipantSlots() == 0)))
                camps.add(camp);

        return camps;
    }

    /**
     * Retrieves a list of all past registered Camps of a Student from the database
     * by User ID
     * 
     * @param userId The User ID of the Student
     * @return ArrayList<Camp> The list of all the past Camps the Student has
     *         registered for
     */
    public static ArrayList<Camp> getStudentPastRegisteredCamps(String userId) {
        return CampDAO.getCampsByParticipantIdBeforeDate(userId, new Date());
    }

    /**
     * Retrieves a list of all future registered Camps of a Student from the
     * database by User ID
     * 
     * @param userId The User ID of the Student
     * @return ArrayList<Camp> The list of all the future Camps the Student has
     *         registered for
     */
    public static ArrayList<Camp> getStudentFutureRegisteredCamps(String userId) {
        return CampDAO.getCampsByParticipantIdAfterDate(userId, new Date());
    }

    /**
     * Retrieves a list of all future Camps of a Student registered as a participant
     * using User ID
     * 
     * @param userId The User ID of the Student
     * @return ArrayList<Camp> The list of all the future Camps the Student has
     *         registered for as a participant
     */
    public static ArrayList<Camp> getParticipantFutureRegisteredCamps(String userId) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : CampDAO.getCampsByParticipantIdAfterDate(userId, new Date()))
            if (camp.getParticipantIds().contains(userId))
                camps.add(camp);

        return camps;
    }

    /**
     * Retrieves a list of all the Camps that a Student has withdrawn from
     * 
     * @param userId The User ID of the Student
     * @return ArrayList<Camp> The list of all the Camps that the Student has
     *         withdrawn from
     */
    public static ArrayList<Camp> getParticipantWithdrawnCamps(String userId) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getWithdrawnParticipantIds().contains(userId))
                camps.add(camp);

        return camps;
    }

    /**
     * Finds a Camp from the database by the Camp ID
     * 
     * @param campId The Camp ID of the Camp
     * @return The corresponding Camp object, NULL if not found
     */
    public static Camp getCampById(String campId) {
        return CampDAO.getCampbyId(campId);
    }

    /**
     * Updates the name of a Camp
     * 
     * @param campId The Camp ID of the Camp
     * @param name   The new name of the Camp
     * @return boolean Whether the Camp name is successfully updated
     */
    public static boolean updateCampName(String campId, String name) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp();
        camp.setName(name);
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the start date of a Camp
     * 
     * @param campId    The Camp ID of the Camp
     * @param startDate The new start date of the Camp
     * @return boolean Whether the Camp start date is successfully updated
     */
    public static boolean updateCampStartDate(String campId, Date startDate) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp();
        camp.setStartDate(startDate);
        ;
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the end date of a Camp
     * 
     * @param campId  The Camp ID of the Camp
     * @param endDate The new end date of the Camp
     * @return boolean Whether the Camp end date is successfully updated
     */
    public static boolean updateCampEndDate(String campId, Date endDate) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp();
        camp.setEndDate(endDate);
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the date that registration closes for a Camp
     * 
     * @param campId                The Camp ID of the Camp
     * @param registrationCloseDate The new date that registration closes for the
     *                              Camp
     * @return boolean Whether the Camp registration close date is successfully
     *         updated
     */
    public static boolean updateCampRegistrationCloseDate(String campId, Date registrationCloseDate) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp();
        camp.setRegistrationCloseDate(registrationCloseDate);
        ;
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the user group of a Camp
     * 
     * @param campId    The Camp ID of the Camp
     * @param userGroup The new user group of the Camp
     * @return boolean Whether the Camp user group is successfully updated
     */
    public static boolean updateCampUserGroup(String campId, Faculty userGroup) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp();
        camp.setUserGroup(userGroup);
        ;
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the location of a Camp
     * 
     * @param campId   The Camp ID of the Camp
     * @param location The new location of the Camp
     * @return boolean Whether the Camp location is successfully updated
     */
    public static boolean updateCampLocation(String campId, String location) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp();
        camp.setLocation(location);
        ;
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the total number of slots for a Camp
     * 
     * @param campId     The Camp ID of the Camp
     * @param totalSlots The new total number of slots for the Camp
     * @return boolean Whether the total number of slots for the Camp is
     *         successfully updated
     */
    public static boolean updateCampTotalSlots(String campId, int totalSlots) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp();
        camp.setTotalSlots(totalSlots);
        ;
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the number of committee member slots of a Camp
     * 
     * @param campId    The Camp ID of the Camp
     * @param commSlots The new number of committee member slots for the Camp
     * @return boolean Whether the total number of committee member slots of the
     *         Camp is successfully updated
     */
    public static boolean updateCampCommSlots(String campId, int commSlots) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp();
        camp.setCommSlots(commSlots);
        ;
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the description of a Camp
     * 
     * @param campId      The Camp ID of the Camp
     * @param description The new description of the Camp
     * @return boolean Whether the Camp description is successfully updated
     */
    public static boolean updateCampDescription(String campId, String description) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp();
        camp.setDescription(description);
        ;
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the visibility of the Camp
     * 
     * @param campId  The Camp ID of the Camp
     * @param visible The visibility of the Camp
     * @return boolean Whether the Camp visibility is successfully updated
     */
    public static boolean updateCampVisiblity(String campId, boolean visible) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.updateCampVisiblity(campId, visible);
        return true;
    }

    /**
     * Filter the name of a Camp
     * 
     * @param name The name that user inputs
     * @return boolean Whether the Camp name matches
     */
    public static boolean filterCampName(String name) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getName().equalsIgnoreCase(name))
                return true;
        return false;
    }

    /**
     * Filter the location of a Camp
     * 
     * @param location The location that user inputs
     * @return boolean Whether the Camp location matches
     */
    public static boolean filterCampLocation(String location) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getLocation().equalsIgnoreCase(location))
                return true;
        return false;
    }

    /**
     * Filter the startDate of a Camp
     * 
     * @param startDate The startDate that user inputs
     * @return boolean Whether the Camp startDate matches
     */
    public static boolean filterCampStart(Date startDate) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getStartDate().equals(startDate))
                return true;
        return false;
    }

    /**
     * Filter the endDate of a Camp
     * 
     * @param endDate The endDate that user inputs
     * @return boolean Whether the Camp endDate matches
     */
    public static boolean filterCampEnd(Date endDate) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getEndDate().equals(endDate))
                return true;
        return false;
    }

    /**
     * Filter the deadline of a Camp
     * 
     * @param deadline The deadline that user inputs
     * @return boolean Whether the Camp deadline matches
     */
    public static boolean filterCampDeadline(Date deadline) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getRegistrationCloseDate().equals(deadline))
                return true;
        return false;
    }

    /**
     * Filter the faculty of a Camp
     * 
     * @param faculty The faculty that user inputs
     * @return boolean Whether the Camp faculty matches
     */
    public static boolean filterCampFaculty(String faculty) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getUserGroup().toString().equalsIgnoreCase(faculty))
                return true;
        return false;
    }

    /**
     * Filter the totalSlots of a Camp
     * 
     * @param totalSlots The totalSlots that user inputs
     * @return boolean Whether the Camp totalSlots matches
     */
    public static boolean filterCampTotalSlots(int totalSlots) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getTotalSlots() == totalSlots)
                return true;
        return false;
    }

    /**
     * Filter the commSlots of a Camp
     * 
     * @param commSlots The commSlots that user inputs
     * @return boolean Whether the Camp commSlots matches
     */
    public static boolean filterCampCommSlot(int commSlots) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getCommSlots() == commSlots)
                return true;
        return false;
    }

    public static boolean filterAttendee(String name) {

        for (int i = 0; i < CampDAO.getAllCamps().size(); i++) {
            if (CampDAO.getAllCamps().get(i).getParticipantIds().size() == 0)
                return false;
            if (CampDAO.getAllCamps().get(i).getParticipantIds().get(i).equalsIgnoreCase(name))
                return true;
        }

        return false;
    }

    /**
     * Adds a Student to the list of participants for a Camp
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the Student
     * @return boolean Whether the Student is successfully added as a Camp
     *         Participant
     */
    public static boolean addParticipant(String campId, String userId) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.updateCampParticipants(campId, userId, true);
        return true;
    }

    /**
     * Removes a Student from the list of participants for a Camp
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the Student
     * @return boolean Whether the Student is succesfully removed as a Camp
     *         Participant
     */
    public static boolean removeParticipant(String campId, String userId) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.updateCampParticipants(campId, userId, false);
        return true;
    }

    /**
     * Adds a Student to the list of committee members for a Camp
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the Student
     * @return boolean Whether the Student is successfully added as a Camp Committee
     */
    public static boolean addCommittee(String campId, String userId) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.updateCampCommittee(campId, userId);
        return true;
    }

    /**
     * Adds a Enquiry to the list of Enquiry for a Camp
     * 
     * @param campId    The Camp ID of the Camp
     * @param enquiryId The ID of the Enquiry
     * @return boolean Whether the Enquiry is successfully updated
     */
    public static boolean addEnquiry(String campId, String enquiryId) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.updateEnquiry(campId, enquiryId, true);
        return true;
    }

    /**
     * Removes a Enquiry to the list of Enquiry for a Camp
     * 
     * @param campId    The Camp ID of the Camp
     * @param enquiryId The ID of the Enquiry
     * @return boolean Whether the Enquiry is successfully updated
     */
    public static boolean removeEnquiry(String campId, String enquiryId) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.updateEnquiry(campId, enquiryId, false);
        return true;
    }

    /**
     * Adds a Suggestion to the list of Suggestion for a Camp
     * 
     * @param campId       The Camp ID of the Camp
     * @param suggestionId The ID of the Suggestion
     * @return boolean Whether the Suggestion is successfully updated
     */
    public static boolean addSuggestion(String campId, String suggestionId) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.updateEnquiry(campId, suggestionId, true);
        return true;
    }

    /**
     * Removes a Suggestion to the list of Suggestion for a Camp
     * 
     * @param campId       The Camp ID of the Camp
     * @param suggestionId The ID of the Suggestion
     * @return boolean Whether the Suggestion is successfully updated
     */
    public static boolean removeSuggestion(String campId, String suggestionId) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.updateEnquiry(campId, suggestionId, false);
        return true;
    }

    /**
     * Deletes a Camp from the database
     * 
     * @param campId The Camp ID of the Camp
     * @return boolean Whether the Camp was successfully deleted
     */
    public static boolean deleteCamp(String campId) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.deleteCamp(campId);
        return true;
    }

    /**
     * Check if the Camp with Camp ID exists in the database
     * 
     * @param campId The Camp ID of the Camp
     * @return boolean Whether the Camp exists in the database
     */
    public static boolean checkCampExists(String campId) {
        return CampDAO.checkCamp(campId);
    }

    /**
     * Check if a User with User ID has previously withdrawn from the Camp with Camp
     * ID
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the User
     * @return boolean Whether the User has previously withdrawn from the Camp
     */
    public static boolean checkCampParticipantWithdrawn(String campId, String userId) {
        return CampDAO.checkWithdrawnParticipant(campId, userId);
    }

    /**
     * Check if a User with User ID has registered for the Camp with Camp ID
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the User
     * @return boolean Whether the User has registered for the Camp
     */
    public static boolean checkCampParticipant(String campId, String userId) {
        if (!checkCampExists(campId))
            return false;
        return CampDAO.checkCampParticipant(campId, userId);
    }

    /**
     * Check if a User with User ID has registered as Committee for the Camp with
     * Camp ID
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the User
     * @return boolean Whether the User has registered as Committee for the Camp
     */
    public static boolean checkCampCommittee(String campId, String userId) {
        if (!checkCampExists(campId))
            return false;
        return CampDAO.checkCampCommittee(campId, userId);
    }

    /**
     * Check if a User with User ID is currently a Committee for any Camp in the
     * database
     * 
     * @param userId The User ID of the User
     * @return boolean Whether the User is currently a Committee for a Camp
     */
    public static boolean checkCurrentCampCommittee(String userId) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getCommitteeIds().contains(userId) && camp.getEndDate().after(new Date()))
                return true;

        return false;
    }

    /**
     * Check if a User with User ID is already registered for another Camp during a
     * time frame
     * 
     * @param userId    The User ID of the User
     * @param startDate The Start Date of the Camp
     * @param endDate   The End Date of the Camp
     * @return boolean Whether the User is already registered for another Camp
     *         during a time frame
     */
    public static boolean checkCampParticipant(String userId, Date startDate, Date endDate) {
        for (Camp camp : CampDAO.getAllCamps())
            if ((camp.getParticipantIds().contains(userId) || camp.getCommitteeIds().contains(userId))
                    && !(endDate.before(camp.getStartDate()) || startDate.after(camp.getEndDate())))
                return true;

        return false;
    }

    /**
     * @param campId
     * @param name
     * @return boolean
     */
    public static boolean checkCampNameUnique(String campId, String name) {
        for (Camp camp : CampDAO.getAllCamps())
            if (camp.getName().equalsIgnoreCase(name) && !camp.getCampId().equals(campId))
                return false;

        return true;
    }
}
