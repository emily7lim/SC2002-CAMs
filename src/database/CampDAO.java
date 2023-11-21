package database;

import java.util.ArrayList;
import java.util.Date;

import model.Camp;
import model.enums.Faculty;

public class CampDAO {
    /**
     * Adds a Camp to the database
     * 
     * @param camp The Camp Object to save in database
     */
    public static void createCamp(Camp camp) {
        Database.CAMPS.put(camp.getCampId(), camp);
    }

    /**
     * Retrieve all Camps from the database
     * 
     * @return ArrayList<Camp> The list of all Camps
     */
    public static ArrayList<Camp> getAllCamps() {
        return new ArrayList<Camp>(Database.CAMPS.values());
    }

    /**
     * Retrieve all Camps from the database using Creator ID
     * 
     * @param creatorId The User ID of the Staff in Charge
     * @return ArrayList<Camp> The list of all Camps with Creator ID
     */
    public static ArrayList<Camp> getCampsByCreatorId(String creatorId) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : Database.CAMPS.values())
            if (camp.getStaffInCharge().equals(creatorId))
                camps.add(camp);
        
        return camps;
    }

    /**
     * Retrieve all Camps from the database using Creator ID
     * 
     * @param creatorId The User ID of the Staff in Charge
     * @param date The minimum Date required
     * @return ArrayList<Camp> The list of all Camps with Creator ID
     */
    public static ArrayList<Camp> getCampsByCreatorIdAfterDate(String creatorId, Date date) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : Database.CAMPS.values())
            if (camp.getStaffInCharge().equals(creatorId) && camp.getStartDate().after(date))
                camps.add(camp);
        
        return camps;
    }

    /**
     * Retrieve all Camps from the database using User Group
     * 
     * @param userGroup The User Group of the Camp
     * @return ArrayList<Camp> The list of all Camps with User Group
     */
    public static ArrayList<Camp> getCampsByUserGroupAndDateAndVisibility(Faculty userGroup, Date date, boolean visible) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : Database.CAMPS.values())
            if ((camp.getUserGroup().equals(userGroup) || camp.getUserGroup() == Faculty.NTU) && camp.getEndDate().after(date) && camp.isVisible() == visible)
                camps.add(camp);
        
        return camps;
    }

    
    /** 
     * @param participantId
     * @param date
     * @return ArrayList<Camp>
     */
    public static ArrayList<Camp> getCampsByParticipantIdBeforeDate(String participantId, Date date) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : Database.CAMPS.values())
            if ((camp.getParticipantIds().contains(participantId) || camp.getCommitteeIds().contains(participantId)) && camp.getStartDate().before(date))
                camps.add(camp);
        
        return camps;
    }

    /**
     * Retrieve all Camps from the database using Participant ID
     * 
     * @param participantId The User ID of the Participant
     * @return ArrayList<Camp> The list of all Camps with Participant
     */
    public static ArrayList<Camp> getCampsByParticipantIdAfterDate(String participantId, Date date) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : Database.CAMPS.values())
            if ((camp.getParticipantIds().contains(participantId) || camp.getCommitteeIds().contains(participantId)) && camp.getStartDate().after(date))
                camps.add(camp);
        
        return camps;
    }

    public static ArrayList<Camp> getCampsByCommitteeId(String committeeId) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : Database.CAMPS.values())
            if (camp.getCommitteeIds().contains(committeeId))
                camps.add(camp);
        
        return camps;
    }

    public static ArrayList<Camp> getCampsByCommitteeIdBeforeDate(String committeeId, Date date) {
        ArrayList<Camp> camps = new ArrayList<>();

        for (Camp camp : Database.CAMPS.values())
            if (camp.getCommitteeIds().contains(committeeId) && camp.getEndDate().before(date))
                camps.add(camp);
        
        return camps;
    }

    public static Camp getCampsByCommitteeIdAfterDate(String committeeId, Date date) {
        for (Camp camp : Database.CAMPS.values())
            if (camp.getCommitteeIds().contains(committeeId) && camp.getEndDate().after(date))
                return camp;
        
        return null;
    }

    /**
     * Finds a Camp from the database using the ID
     * 
     * @param campId The Camp ID of the Camp
     * @return Camp The corresponding Camp object, NULL if not found
     */
    public static Camp getCampbyId(String campId) {
        return Database.CAMPS.get(campId);
    }

    /**
     * Check if a Camp exists in the database using the ID
     * 
     * @param campId The Camp ID of the Camp
     * @return boolean Whether the Camp exists
     */
    public static boolean checkCamp(String campId) {
        return Database.CAMPS.containsKey(campId);
    }

    /**
     * Check if a User has withdrawn from the Camp in the database using the ID
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the User
     * @return boolean Whether the User has withdrawn from the Camp
     */
    public static boolean checkWithdrawnParticipant(String campId, String userId) {
        return Database.CAMPS.get(campId).getWithdrawnParticipantIds().contains(userId);
    }

    /**
     * Check if a User has registered for the Camp in the database using the ID
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the User
     * @return boolean Whether the User has registered for the Camp
     */
    public static boolean checkCampParticipant(String campId, String userId) {
        return Database.CAMPS.get(campId).getParticipantIds().contains(userId);
    }

    /**
     * Check if a User has registered as Committee for the Camp in the database
     * using the ID
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the User
     * @return boolean Whether the User has registered as Committee for the Camp
     */
    public static boolean checkCampCommittee(String campId, String userId) {
        return Database.CAMPS.get(campId).getCommitteeIds().contains(userId);
    }

    /**
     * Updates the information of a Camp in the database using the ID
     * 
     * @param campId   The Camp ID of the Camp
     * @param campInfo Camp object with new Camp information
     */
    public static void updateCampInformation(String campId, Camp campInfo) {
        Camp camp = Database.CAMPS.get(campId);
        if (campInfo.getName() != null)
            camp.setName(campInfo.getName());
        if (campInfo.getStartDate() != null)
            camp.setStartDate(campInfo.getStartDate());
        if (campInfo.getEndDate() != null)
            camp.setEndDate(campInfo.getEndDate());
        if (campInfo.getRegistrationCloseDate() != null)
            camp.setRegistrationCloseDate(campInfo.getRegistrationCloseDate());
        if (campInfo.getUserGroup() != null)
            camp.setUserGroup(campInfo.getUserGroup());
        if (campInfo.getLocation() != null)
            camp.setLocation(campInfo.getLocation());
        if (campInfo.getTotalSlots() != 0)
            camp.setTotalSlots(campInfo.getTotalSlots());
        if (campInfo.getCommSlots() != 0)
            camp.setCommSlots(campInfo.getCommSlots());
        if (campInfo.getDescription() != null)
            camp.setDescription(campInfo.getDescription());
    }

    /**
     * Updates the visibility of a Camp in the database using the ID
     * 
     * @param campId  The Camp ID of the Camp
     * @param visible The new visibility of the Camp
     */
    public static void updateCampVisiblity(String campId, boolean visible) {
        Database.CAMPS.get(campId).setVisible(visible);
    }

    /**
     * Updates the participants of a Camp in the database using the ID
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the new participant
     * @param add    If function should add or remove a User
     */
    public static void updateCampParticipants(String campId, String userId, boolean add) {
        if (add)
            Database.CAMPS.get(campId).addParticipant(userId);
        else {
            Database.CAMPS.get(campId).removeParticipant(userId);
            Database.CAMPS.get(campId).addWithdrawnParticipant(userId);
        }
    }

    /**
     * Updates the committee members of a Camp in the database using the ID
     * 
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the new committee member
     */
    public static void updateCampCommittee(String campId, String userId) {
        Database.CAMPS.get(campId).addCommittee(userId);
    }

    /**
     * Updates an enquiry to a Camp in the database using the ID
     * 
     * @param campId    The Camp ID of the Camp
     * @param enquiryId The ID of the new enquiry
     * @param add       If function should add or remove a User
     */
    public static void updateEnquiry(String campId, String enquiryId, boolean add) {
        if (add) {
            Database.CAMPS.get(campId).addEnquiry(enquiryId);
        } else {
            Database.CAMPS.get(campId).removeEnquiry(enquiryId);
        }
    }

    /**
     * Updates a suggestion to a Camp in the database using the ID
     * 
     * @param campId       The Camp ID of the Camp
     * @param suggestionId The ID of the new suggestion
     * @param add          If function should add or remove a User
     * 
     */
    public static void updateSuggestion(String campId, String suggestionId, boolean add) {
        if (add) {
            Database.CAMPS.get(campId).addSuggestion(suggestionId);
        } else {
            Database.CAMPS.get(campId).removeSuggestion(suggestionId);
        }
    }

    /**
     * Removes a Camp from the database using the ID
     * 
     * @param campId The Camp ID of the Camp
     */
    public static void deleteCamp(String campId) {
        Database.CAMPS.remove(campId);
        // should the corresponding suggestions and enquiry be removed?
    }
}
