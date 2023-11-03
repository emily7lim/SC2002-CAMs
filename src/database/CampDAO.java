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
     * Update the information of a Camp in the database using the ID
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
     * Update the visibility of a Camp in the database using the ID
     * @param campId The Camp ID of the Camp
     * @param visible The new visibility of the Camp
     */
    public static void updateCampVisiblity(String campId, boolean visible) {
        Database.CAMPS.get(campId).setVisible(visible);
    }

    /**
     * Removes a Camp from the database using the ID
     * @param campId The Camp ID of the Camp
     */
    public static void deleteCamp(String campId) {
        Database.CAMPS.remove(campId);
    }
}
