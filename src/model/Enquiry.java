package model;

public class Enquiry {
    
    private int enquiryId;
	private String sender;
	private String receiver;
	private String message;
	private String status;
	
	public Enquiry(int enquiryId, String sender, String receiver, String message, String status)
	{
		this.enquiryId = enquiryId;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.status = status;
	}
	
	public int getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void replyToEnquiry()
	{
		
	}
    
}
