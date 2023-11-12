package database;

import java.util.ArrayList;

import model.Camp;

public class CampDAO {
    /**
     * Adds a Camp to the database
     * @param camp The Camp Object to save in database
     */
    public static void createCamp(Camp camp) {
        Database.CAMPS.put(camp.getCampId(), camp);
    }

    /**
     * Retrieve all Camps from the database
     * @return ArrayList<Camp> The list of all Camps
     */
    public static ArrayList<Camp> getAllCamps() {
        return new ArrayList<Camp>(Database.CAMPS.values());
    }

    /**
     * Finds a Camp from the database using the ID
     * @param campId The Camp ID of the Camp
     * @return Camp The corresponding Camp object, NULL if not found
     */
    public static Camp getCampbyId(String campId) {
        return Database.CAMPS.get(campId);
    }

    /**
     * Check if a Camp exists in the database using the ID
     * @param campId The Camp ID of the Camp
     * @return boolean Whether the Camp exists
     */
    public static boolean checkCamp(String campId) {
        return Database.CAMPS.containsKey(campId);
    }

    /**
     * Check if a User has withdrawn from the Camp in the database using the ID
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the User
     * @return boolean Whether the User has withdrawn from the Camp
     */
    public static boolean checkWithdrawnParticipant(String campId, String userId) {
        return Database.CAMPS.get(campId).getWithdrawnParticipantIds().contains(userId);
    }

    /**
     * Updates the information of a Camp in the database using the ID
     * @param campId The Camp ID of the Camp
     * @param campInfo Camp object with new Camp information
     */
    public static void updateCampInformation(String campId, Camp campInfo) {
        Camp camp = Database.CAMPS.get(campId);
        camp.setName(campInfo.getName());
        camp.setStartDate(campInfo.getStartDate());
        camp.setEndDate(campInfo.getEndDate());
        camp.setRegistrationCloseDate(campInfo.getRegistrationCloseDate());
        camp.setUserGroup(campInfo.getUserGroup());
        camp.setLocation(campInfo.getLocation());
        camp.setTotalSlots(campInfo.getTotalSlots());
        camp.setCommSlots(campInfo.getCommSlots());
        camp.setDescription(campInfo.getDescription());
    }

    /**
     * Updates the visibility of a Camp in the database using the ID
     * @param campId The Camp ID of the Camp
     * @param visible The new visibility of the Camp
     */
    public static void updateCampVisiblity(String campId, boolean visible) {
        Database.CAMPS.get(campId).setVisible(visible);
    }

    /**
     * Updates the participants of a Camp in the database using the ID
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the new participant
     * @param add If function should add or remove a User
     */
    public static void updateCampParticipants(String campId, String userId, boolean add) {
        if (add) Database.CAMPS.get(campId).addParticipant(userId);
        else {
            Database.CAMPS.get(campId).removeParticipant(userId);
            Database.CAMPS.get(campId).addWithdrawnParticipant(userId);
        }
    }

    /**
     * Updates the committee members of a Camp in the database using the ID
     * @param campId The Camp ID of the Camp
     * @param userId The User ID of the new committee member
     */
    public static void updateCampCommittee(String campId, String userId) {
        Database.CAMPS.get(campId).addCommittee(userId);
    }

    /**
     * Removes a Camp from the database using the ID
     * @param campId The Camp ID of the Camp
     */
    public static void deleteCamp(String campId) {
        Database.CAMPS.remove(campId);
    }
}
