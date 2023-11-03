package model;

import java.io.Serializable;

import model.enums.EnquiryStatus;

public class Enquiry implements Serializable {
	protected static final long serialVersionUID = 3L;
	private String enquiryId;
	private String creatorId;
	private String responderId;
	private String message;
	private String reply;
	private EnquiryStatus status;

	public Enquiry(String enquiryId, String creatorId, String message) {
		this.enquiryId = enquiryId;
		this.creatorId = creatorId;
		this.responderId = null;
		this.message = message;
		this.reply = null;
		this.status = EnquiryStatus.PENDING;
	}

	public Enquiry(String enquiryId, String creatorId, String responderId, String message, String reply, EnquiryStatus status) {
		this.enquiryId = enquiryId;
		this.creatorId = creatorId;
		this.responderId = responderId;
		this.message = message;
		this.reply = reply;
		this.status = status;
	}

	public String getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(String enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getResponderId() {
		return responderId;
	}

	public void setResponderId(String responderId) {
		this.responderId = responderId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public EnquiryStatus getStatus() {
		return status;
	}

	public void setStatus(EnquiryStatus status) {
		this.status = status;
	}
}
