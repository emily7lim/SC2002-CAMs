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
    public void createEnquiry(String campId, String creatorId, String message) {
        Enquiry enquiry = new Enquiry(campId, creatorId, message);
        EnquiryDAO.createEnquiry(enquiry);
    }

    /**
     * Retrieves a list of all Enquiries from the database
     * @return ArrayList<Enquiry> The list of all the Enquiries
     */
    public ArrayList<Enquiry> getAllEnquiries() {
        return EnquiryDAO.getAllEnquiries();
    }

    /**
     * Finds an Enquiry from the database by the Enquiry ID 
     * @param enquiryId The Enquiry Id of the Enquiry
     * @return Enquiry The corresponding Enquiry object, NULL if not found
     */
    public Enquiry getEnquiryById(String enquiryId) {
        return EnquiryDAO.getEnquirybyId(enquiryId);
    }

    /**
     * Updates the message of an Enquiry
     * @param enquiryId The Enquiry ID of the Enquiry
     * @param message The new message of the Enquiry
     * @return boolean Whether the Enquiry message was successfully updated
     */
    public boolean updateEnquiryMessage(String enquiryId, String message) {
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
    public boolean replyEnquiry(String enquiryId, String reply, String responderId) {
        if (!checkEnquiryExists(enquiryId)) return false;

        EnquiryDAO.updateEnquiryResponse(enquiryId, reply, EnquiryStatus.CLOSED, responderId);
        return true;
    }

    /**
     * Deletes an Enquiry from the database
     * @param enquiryId The Enquiry ID of the Enquiry
     * @return boolean Whether the Enquiry was successfully deleted
     */
    public boolean deleteEnquiry(String enquiryId) {
        if (!checkEnquiryExists(enquiryId)) return false;

        EnquiryDAO.deleteEnquiry(enquiryId);
        return true;
    }

    /**
     * Check if the Enquiry with Enquiry ID exists in the database
     * @param enquiryId The Enquiry ID of the Enquiry
     * @return boolean Whether the Enquiry exists in the database
     */
    public boolean checkEnquiryExists(String enquiryId) {
        return EnquiryDAO.checkEnquiry(enquiryId);
    }
}
