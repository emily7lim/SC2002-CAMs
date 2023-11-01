package database;

import model.Camp;

public class CampDAO {
    public static Camp getUserbyId(Integer campId) {
        return Database.CAMPS.get(campId);
    }
    
    public static void createUser(Camp camp) {
        Database.CAMPS.put(camp.getCampId(), camp);
    }
}
