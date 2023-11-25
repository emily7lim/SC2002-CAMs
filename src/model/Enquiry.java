package model;

import java.io.Serializable;
import java.util.UUID;

import model.enums.EnquiryStatus;

/**
 * Suggestion of a Student
 * 
 * @author Deuel, Chloie, Owen
 * @version 2.2.2
 * @since 2023-10-31
 */
public class Enquiry implements Serializable {
	/** Serializable Unique Identifier */
	protected static final long serialVersionUID = 3L; 	 
    /** The ID of the Enquiry */
	private String enquiryId; 						     
	/** The ID of the Enquiry Camp */
	private String campId; 								 
	/** The ID of the Enquiry Creator */
	private String creatorId; 							 
	/** The ID of the Enquiry Responder */
	private String responderId; 						 
	/** The Message of the Enquiry */
	private String message; 							 
	/** The Reply of the Enquiry */
	private String reply; 								 
	/** The Status of the Enquiry */
	private EnquiryStatus status; 						 

	/**
	 * Constructs and initializes an Enquiry object with Camp ID, Creator ID and
	 * Message
	 * 
	 * @param campId    The Camp ID of the Camp the Enquiry is for
	 * @param creatorId The User ID of the Student who wrote the Enquiry
	 * @param message   The message of the Enquiry
	 */
	public Enquiry(String campId, String creatorId, String message) {
		this.enquiryId = UUID.randomUUID().toString();
		this.campId = campId;
		this.creatorId = creatorId;
		this.responderId = null;
		this.message = message;
		this.reply = null;
		this.status = EnquiryStatus.PENDING;
	}

	/**
	 * Constructs and initializes an Enquiry object with Camp ID, Creator ID,
	 * Responder ID, Message, Reply and Status
	 * 
	 * @param campId      The Camp ID of the Camp the Enquiry is for
	 * @param creatorId   The User ID of the Student who wrote the Enquiry
	 * @param responderId The User ID of the Staff/Committee who replies to the
	 *                    Enquiry
	 * @param message     The message of the Enquiry
	 * @param reply       The reply of the Enquiry
	 * @param status      The status of the Enquiry
	 */
	public Enquiry(String campId, String creatorId, String responderId, String message, String reply,
			EnquiryStatus status) {
		this.enquiryId = UUID.randomUUID().toString();
		this.campId = campId;
		this.creatorId = creatorId;
		this.responderId = responderId;
		this.message = message;
		this.reply = reply;
		this.status = status;
	}

	/**
	 * Gets the ID of the Enquiry
	 * 
	 * @return String The ID of the Enquiry
	 */
	public String getEnquiryId() {
		return enquiryId;
	}

	/**
	 * Sets the ID of the Enquiry
	 * 
	 * @param enquiryId The ID of the Enquiry
	 */
	public void setEnquiryId(String enquiryId) {
		this.enquiryId = enquiryId;
	}

	/**
	 * Gets the Camp ID of the Camp the Enquiry is for
	 * 
	 * @return String The Camp ID of the Camp the Enquiry is for
	 */
	public String getCampId() {
		return campId;
	}

	/**
	 * Sets the Camp ID of the Camp the Enquiry is for
	 * 
	 * @param campId The Camp ID of the Camp the Enquiry is for
	 */
	public void setCampId(String campId) {
		this.campId = campId;
	}

	/**
	 * Gets the User ID of the Student who wrote the Enquiry
	 * 
	 * @return String The User ID of the Student who wrote the Enquiry
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * Sets the User ID of the Student who wrote the Enquiry
	 * 
	 * @param creatorId The User ID of the Student who wrote the Enquiry
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * Gets the User ID of the Staff/Committee who replies to the Enquiry
	 * 
	 * @return String The User ID of the Staff/Committee who replies to the Enquiry
	 */
	public String getResponderId() {
		return responderId;
	}

	/**
	 * Sets the User ID of the Staff/Committee who replies to the Enquiry
	 * 
	 * @param responderId The User ID of the Staff/Committee who replies to the
	 *                    Enquiry
	 */
	public void setResponderId(String responderId) {
		this.responderId = responderId;
	}

	/**
	 * Gets the message of the Enquiry
	 * 
	 * @return String The message of the Enquiry
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message of the Enquiry
	 * 
	 * @param message The message of the Enquiry
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the reply of the Enquiry
	 * 
	 * @return String The reply of the Enquiry
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * Sets the reply of the Enquiry
	 * 
	 * @param reply The reply of the Enquiry
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}

	/**
	 * Gets the status of the Enquiry
	 * 
	 * @return EnquiryStatus The status of the Enquiry
	 */
	public EnquiryStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status of the Enquiry
	 * 
	 * @param status The status of the Enquiry
	 */
	public void setStatus(EnquiryStatus status) {
		this.status = status;
	}
}
