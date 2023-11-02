package database;

import model.Enquiry;

public class EnquiryDAO {
    /** 
     * Finds a Enquiry from the database using the id
     * @param enquiryId The Enquiry id of the Enquiry  
     * @return Enquiry The corresponding Enquiry object, NULL if not found
     */
    public static Enquiry getEnquirybyId(Integer enquiryId) {
        return Database.ENQUIRYS.get(enquiryId);
    }
    
    /** 
     * Adds a Enquiry to the database
     * @param enquiry The Enquiry Object to save
     */
    public static void createEnquiry(Enquiry enquiry) {
        Database.ENQUIRYS.put(enquiry.getEnquiryId(), enquiry);
    }

    /** 
     * Removes a Enquiry from the database using the id
     * @param enquiryId The Enquiry id of the Enquiry
     */
    public static void deleteEnquiry(Integer enquiryId) {
        Database.ENQUIRYS.remove(enquiryId);
    }
}
