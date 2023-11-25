package model.enums;

/**
 * The Status of an Eqnuiry
 */
public enum EnquiryStatus {
    PENDING("Pending"),
    CLOSED("Closed");

    private final String enquiryStatusStr;

    /**
     * Constructor of the EnquiryStatus Enum
     * 
     * @param enquiryStatusStr The Status of an Enquiry
     */
    private EnquiryStatus(String enquiryStatusStr) {
        this.enquiryStatusStr = enquiryStatusStr;
    }

    /**
     * Gets the display name of an Enquiry Status
     * 
     * @return The display name of an Enquiry Status
     */
    public String getEnquiryStatus() {
        return enquiryStatusStr;
    }
}
