package database;

import model.Camp;

public class CampDAO {
    /** 
     * Finds a Camp from the database using the id
     * @param campId The Camp id of the Camp  
     * @return Camp The corresponding Camp object, NULL if not found
     */
    public static Camp getCampbyId(Integer campId) {
        return Database.CAMPS.get(campId);
    }
    
    /** 
     * Adds a Camp to the database
     * @param camp The Camp Object to save
     */
    public static void createCamp(Camp camp) {
        Database.CAMPS.put(camp.getCampId(), camp);
    }

    /** 
     * Removes a Camp from the database using the id
     * @param campId The Camp id of the Camp
     */
    public static void deleteCamp(Integer campId) {
        Database.CAMPS.remove(campId);
    }
}
