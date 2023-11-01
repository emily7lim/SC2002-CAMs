package controller;

import model.Enquiry;

import java.util.ArrayList;
import java.util.List;

public class EnquiryController {
    private List<Enquiry> enquiries;

    public EnquiryController() {
        enquiries = new ArrayList<>();
    }

    // Create a new enquiry and add it to the list
    public Enquiry createEnquiry(int enquiryId, String sender, String receiver, String message, String status) {
        Enquiry enquiry = new Enquiry(enquiryId, sender, receiver, message, status);
        enquiries.add(enquiry);
        return enquiry;
    }

    // Get a list of all enquiries
    public List<Enquiry> getAllEnquiries() {
        return enquiries;
    }

    // Find an enquiry by its ID
    public Enquiry findEnquiryById(int enquiryId) {
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getEnquiryId() == enquiryId) {
                return enquiry; //Enquiry found
            }
        }
        return null; //No enquiry with such ID
    }

    // Update enquiry information
    public boolean updateEnquiryInformation(int enquiryId, String newMessage, String newStatus) {
        Enquiry enquiry = findEnquiryById(enquiryId);
        if (enquiry != null) {
            enquiry.setMessage(newMessage);
            enquiry.setStatus(newStatus);
            return true;
        }
        return false; // Enquiry not found
    }

    // Remove an enquiry from the list
    public boolean deleteEnquiry(int enquiryId) {
        Enquiry enquiry = findEnquiryById(enquiryId);
        if (enquiry != null) {
            enquiries.remove(enquiry);
            return true;
        }
        return false; // Enquiry not found
    }
}
