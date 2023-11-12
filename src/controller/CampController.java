package controller;

import model.Camp;
import model.enums.Faculty;

import java.util.ArrayList;
import java.util.Date;

import database.CampDAO;

public class CampController {
    /**
     * Creates a new Camp and add to the database
     * @param campId The Camp ID of the new Camp
     * @param name The name of the new Camp
     * @param startDate The start date of the new Camp
     * @param endDate The end date of the new Camp
     * @param registrationCloseDate The date that registration closes for the new Camp
     * @param userGroup The user group of the new Camp
     * @param location The location of the new Camp
     * @param totalSlots The total number of slots for the new Camp
     * @param commSlots The number of committee member slots for the new Camp
     * @param description The description of the new Camp
     * @param staffInCharge The staff in charge of the new Camp
     */
    public void createCamp(String name, Date startDate, Date endDate, Date registrationCloseDate,
            Faculty userGroup, String location, int totalSlots, int commSlots, String description,
            String staffInCharge) {
        Camp camp = new Camp(name, startDate, endDate, registrationCloseDate, userGroup, location,
                totalSlots, commSlots, description, staffInCharge);
        CampDAO.createCamp(camp);
    }

    /**
     * Retrieves a list of all Camps from the database
     * @return ArrayList<Camp> The list of all the Camps
     */
    public ArrayList<Camp> getAllCamps() {
        return CampDAO.getAllCamps();
    }

    /**
     * Finds a Camp from the database by the Camp ID 
     * @param campId The Camp ID of the Camp
     * @return The corresponding Camp object, NULL if not found
     */
    public Camp getCampById(String campId) {
        return CampDAO.getCampbyId(campId);
    }

    /**
     * Updates the information of a Camp
     * @param campId The Camp ID of the Camp
     * @param name The new name of the Camp
     * @param startDate The new start date of the Camp
     * @param endDate The new end date of the Camp
     * @param registrationCloseDate The new date that registration closes for the Camp
     * @param userGroup The new user group of the Camp
     * @param location The new location of the Camp
     * @param totalSlots The new total number of slots for the Camp
     * @param commSlots The new number of committee member slots for the Camp
     * @param description The new description of the Camp
     * @return boolean Whether the Camp information is successfully updated
     */
    public boolean updateCampInformation(String campId, String name, Date startDate, Date endDate,
            Date registrationCloseDate, Faculty userGroup, String location, int totalSlots, int commSlots,
            String description) {
        if (!checkCampExists(campId))
            return false;

        Camp camp = new Camp(name, startDate, endDate, registrationCloseDate, userGroup, location, totalSlots,
                commSlots, description);
        CampDAO.updateCampInformation(campId, camp);
        return true;
    }

    /**
     * Updates the visibility of the Camp
     * @param campId The Camp ID of the Camp
     * @param visible The visibility of the Camp
     * @return boolean Whether the Camp visibility is successfully updated
     */
    public boolean updateCampVisiblity(String campId, boolean visible) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.updateCampVisiblity(campId, visible);
        return true;
    }

    /**
     * Adds a User to the list of participants for a Camp
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the participant
     * @return boolean Whether the Camp participants is successfully updated
     */
    public boolean addParticipant(String campId, String userId) {
        if (!checkCampExists(campId)) return false;

        CampDAO.updateCampParticipants(campId, userId, true);
        return true;
    }

    /**
     * Removes a User from the list of participants for a Camp
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the participant
     * @return boolean Whether the Camp participants is successfully updated
     */
    public boolean removeParticipant(String campId, String userId) {
        if (!checkCampExists(campId)) return false;

        CampDAO.updateCampParticipants(campId, userId, false);
        return true;
    }

    /**
     * Adds a User to the list of committee members for a Camp
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the committee cember
     * @return boolean Whether the Camp committee members is successfully updated
     */
    public boolean addCommittee(String campId, String userId) {
        if (!checkCampExists(campId)) return false;

        CampDAO.updateCampCommittee(campId, userId);
        return true;
    }

    /**
     * Deletes a Camp from the database
     * @param campId The Camp ID of the Camp
     * @return boolean Whether the Camp was successfully deleted
     */
    public boolean deleteCamp(String campId) {
        if (!checkCampExists(campId))
            return false;

        CampDAO.deleteCamp(campId);
        return true;
    }

    /**
     * Check if the Camp with Camp ID exists in the database
     * @param campId The Camp ID of the Camp
     * @return boolean Whether the Camp exists in the database
     */
    public boolean checkCampExists(String campId) {
        return CampDAO.checkCamp(campId);
    }

    /**
     * Check if a User with User ID has previously withdrawn from the Camp with Camp ID
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the User
     * @return boolean Whether the User has previously withdrawn from the Camp
     */
    public boolean checkCampParticipantWithdrawn(String campId, String userId) {
        return CampDAO.checkWithdrawnParticipant(campId, userId);
    }
}
