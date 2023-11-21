package controller;

import java.util.ArrayList;

import model.Enquiry;
import model.enums.EnquiryStatus;
import database.EnquiryDAO;

public class EnquiryController {
    /**
     * Creates a new Enquiry and add to the database
     * @param enquiryId The Enquiry ID of the new Enquiry
     * @param creatorId The User ID of the creator of the new Enquiry
     * @param message The message of the new Enquiry
     */
    public static String createEnquiry(String campId, String creatorId, String message) {
        Enquiry enquiry = new Enquiry(campId, creatorId, message);
        EnquiryDAO.createEnquiry(enquiry);

        return enquiry.getEnquiryId();
    }

    /**
     * Retrieves a list of all Enquiries from the database
     * @return ArrayList<Enquiry> The list of all the Enquiries
     */
    public static ArrayList<Enquiry> getAllEnquiries() {
        return EnquiryDAO.getAllEnquiries();
    }

    /**
     * Retrieves a list of all Enquiries from the database with Camp ID
     * @param campId The Camp ID of the Enquiry
     * @return ArrayList<Enquiry> The list of all the Enquiries
     */
    public static ArrayList<Enquiry> getEnquiriesByCampId(String campId) {
        return EnquiryDAO.getEnquiriesByCampId(campId);
    }

    /**
     * Retrieves a list of all pending Enquiries from the database with Camp ID
     * @param campId The Camp ID of the Enquiry
     * @return ArrayList<Enquiry> The list of all the Enquiries
     */
    public static ArrayList<Enquiry> getPendingEnquiriesByCampId(String campId) {
        return EnquiryDAO.getEnquiriesByCampIdAndStatus(campId, EnquiryStatus.PENDING);
    }

    /**
     * Retrieves a list of all replied Enquiries from the database with Camp ID
     * @param campId The Camp ID of the Enquiry
     * @return ArrayList<Enquiry> The list of all the Enquiries
     */
    public static ArrayList<Enquiry> getRepliedEnquiriesByCampId(String campId) {
        return EnquiryDAO.getEnquiriesByCampIdAndStatus(campId, EnquiryStatus.CLOSED);
    }

    /**
     * Retrieves a list of all Enquiries from the database with Creator ID
     * @param creatorId The User ID of the Creator of the Enquiry
     * @return ArrayList<Enquiry> The list of all the Enquiries
     */
    public static ArrayList<Enquiry> getEnquiriesByCreatorIdAndCampId(String creatorId, String campId) {
        return EnquiryDAO.getEnquiriesByCreatorIdAndCampId(creatorId, campId);
    }

    
    /** 
     * @param creatorId
     * @param campId
     * @return ArrayList<Enquiry>
     */
    public static ArrayList<Enquiry> getPendingEnquiriesByCreatorIdAndCampId(String creatorId, String campId) {
        return EnquiryDAO.getEnquiriesByCreatorIdAndCampIdAndStatus(creatorId, campId, EnquiryStatus.PENDING);
    }

    public static ArrayList<Enquiry> getRepliedEnquiriesByCreatorIdAndCampId(String creatorId, String campId) {
        return EnquiryDAO.getEnquiriesByCreatorIdAndCampIdAndStatus(creatorId, campId, EnquiryStatus.CLOSED);
    }

    /**
     * Finds an Enquiry from the database by the Enquiry ID 
     * @param enquiryId The Enquiry Id of the Enquiry
     * @return Enquiry The corresponding Enquiry object, NULL if not found
     */
    public static Enquiry getEnquiryById(String enquiryId) {
        return EnquiryDAO.getEnquirybyId(enquiryId);
    }

    /**
     * Updates the message of an Enquiry
     * @param enquiryId The Enquiry ID of the Enquiry
     * @param message The new message of the Enquiry
     * @return boolean Whether the Enquiry message was successfully updated
     */
    public static boolean updateEnquiryMessage(String enquiryId, String message) {
        if (!checkEnquiryExists(enquiryId)) return false;

        EnquiryDAO.updateEnquiryMessage(enquiryId, message);
        return true;
    }

    /**
     * Updates the reply to an Enquiry
     * @param enquiryId The Enquiry ID of the Enquiry
     * @param reply The reply to the Enquiry
     * @param responderId The User ID of the User adding the reply
     * @return boolean Whether the Enquiry reply was successfully updated
     */
    public static boolean replyEnquiry(String enquiryId, String reply, String responderId) {
        if (!checkEnquiryExists(enquiryId)) return false;

        EnquiryDAO.updateEnquiryResponse(enquiryId, reply, EnquiryStatus.CLOSED, responderId);
        return true;
    }

    /**
     * Deletes an Enquiry from the database
     * @param enquiryId The Enquiry ID of the Enquiry
     * @return boolean Whether the Enquiry was successfully deleted
     */
    public static boolean deleteEnquiry(String enquiryId) {
        if (!checkEnquiryExists(enquiryId)) return false;

        EnquiryDAO.deleteEnquiry(enquiryId);
        return true;
    }

    /**
     * Check if the Enquiry with Enquiry ID exists in the database
     * @param enquiryId The Enquiry ID of the Enquiry
     * @return boolean Whether the Enquiry exists in the database
     */
    public static boolean checkEnquiryExists(String enquiryId) {
        return EnquiryDAO.checkEnquiry(enquiryId);
    }
}
