package database;

import java.util.ArrayList;

import model.Enquiry;
import model.enums.EnquiryStatus;

public class EnquiryDAO {
    /**
     * Adds an Enquiry to the database
     * and Updates corresponding camp
     * 
     * @param enquiry The Enquiry Object to save to database
     */
    public static void createEnquiry(Enquiry enquiry) {
        Database.ENQUIRIES.put(enquiry.getEnquiryId(), enquiry);
        CampDAO.updateSuggestion(enquiry.getCampId(), enquiry.getCampId(), true);
    }

    /**
     * Retrieve all Enquiries from the database
     * 
     * @return ArrayList<Enquiry> The list of all Enquiries
     */
    public static ArrayList<Enquiry> getAllEnquiries() {
        return new ArrayList<Enquiry>(Database.ENQUIRIES.values());
    }

    /**
     * Retrieve all Enquiries from the database using Camp ID
     * 
     * @param campId The Camp ID of the Enquiry
     * @return ArrayList<Enquiry> The list of all Enquiries
     */
    public static ArrayList<Enquiry> getEnquiriesByCampId(String campId) {
        ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();

        for (Enquiry enquiry : Database.ENQUIRIES.values())
            if (enquiry.getCampId().equals(campId))
                enquiries.add(enquiry);
        
        return enquiries;
    }

    /**
     * Retrieve all Enquiries from the database using Camp ID and Status
     * 
     * @param campId The Camp ID of the Enquiry
     * @param status The Status of the Enquiry
     * @return ArrayList<Enquiry> The list of all Enquiries
     */
    public static ArrayList<Enquiry> getEnquiriesByCampIdAndStatus(String campId, EnquiryStatus status) {
        ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();

        for (Enquiry enquiry : Database.ENQUIRIES.values())
            if (enquiry.getCampId().equals(campId) && enquiry.getStatus() == status)
                enquiries.add(enquiry);
        
        return enquiries;
    }

    /**
     * Finds a Enquiry from the database using the ID
     * 
     * @param enquiryId The Enquiry ID of the Enquiry
     * @return Enquiry The corresponding Enquiry object, NULL if not found
     */
    public static Enquiry getEnquirybyId(String enquiryId) {
        return Database.ENQUIRIES.get(enquiryId);
    }

    /**
     * Check if a Enquiry exists in the database using the ID
     * 
     * @param enquiryId The Enquiry ID of the Enquiry
     * @return boolean Whether the Enquiry exists
     */
    public static boolean checkEnquiry(String enquiryId) {
        return Database.ENQUIRIES.containsKey(enquiryId);
    }

    /**
     * Update the message of a Enquiry in the database using the ID
     * 
     * @param enquiryId The Enquiry ID of the Enquiry
     * @param message   The new message of the Enquiry
     */
    public static void updateEnquiryMessage(String enquiryId, String message) {
        Database.ENQUIRIES.get(enquiryId).setMessage(message);
    }

    /**
     * Update the status of a Enquiry in the database using the ID
     * 
     * @param enquiryId   The Enquiry ID of the Enquiry
     * @param reply       The reply to the Enquiry
     * @param status      The new status of the Enquiry
     * @param responderId The User ID of the User responding to the Enquiry
     */
    public static void updateEnquiryResponse(String enquiryId, String reply, EnquiryStatus status, String responderId) {
        Enquiry enquiry = Database.ENQUIRIES.get(enquiryId);
        enquiry.setReply(reply);
        enquiry.setStatus(status);
        enquiry.setResponderId(responderId);
    }

    /**
     * Removes a Enquiry from the database using the ID
     * and Updates corresponding camp
     * 
     * @param enquiryId The Enquiry ID of the Enquiry
     */
    public static void deleteEnquiry(String enquiryId) {
        Enquiry deleted = Database.ENQUIRIES.remove(enquiryId);
        if (deleted != null)
            CampDAO.updateSuggestion(deleted.getCampId(), deleted.getCampId(), false);
    }
}
