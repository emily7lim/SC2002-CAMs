package model.enums;

public enum EnquiryStatus {
    PENDING ("Pending"),
    CLOSED ("Closed");

    private final String enquiryStatusStr;

    private EnquiryStatus(String enquiryStatusStr) {
        this.enquiryStatusStr = enquiryStatusStr;
    }

    public String getEnquiryStatus() {
        return enquiryStatusStr;
    }
}
