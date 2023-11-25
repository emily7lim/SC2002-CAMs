package model;

import java.io.Serializable;
import java.util.UUID;

import model.enums.SuggestionStatus;

/**
 * Suggestion of a Committee Member
 * 
 * @author Deuel, Chloie, Owen
 * @version 2.3.2
 * @since 2023-10-31
 */
public class Suggestion implements Serializable {
	protected static final long serialVersionUID = 4L; // Serializable Unique Identifier
	private String suggestionId; // ID of the Suggestion
	private String campId; // ID of the Suggestion Camp
	private String creatorId; // ID of the Suggestion Creator
	private String responderId; // ID of the Suggestion Responder
	private String message; // Message of the Suggestion
	private SuggestionStatus status; // Status of the Suggestion

	/**
	 * Constructs and initializes a Suggestion object with Camp ID, Creator ID and
	 * Message
	 * 
	 * @param campId    The Camp ID of the Camp the Suggestion is for
	 * @param creatorId The User ID of the Committee who wrote the Suggestion
	 * @param message   The message of the Suggestion
	 */
	public Suggestion(String campId, String creatorId, String message) {
		this.suggestionId = UUID.randomUUID().toString();
		this.campId = campId;
		this.creatorId = creatorId;
		this.responderId = null;
		this.message = message;
		this.status = SuggestionStatus.PENDING;
	}

	/**
	 * Constructs and initializes a Suggestion object with Camp ID, Creator ID,
	 * Responder ID, Message and Status
	 * 
	 * @param campId      The Camp ID of the Camp the Suggestion is for
	 * @param creatorId   The User ID of the Committee who wrote the Suggestion
	 * @param responderId The User ID of the Staff who approves/rejects the
	 *                    Suggestion
	 * @param message     The message of the Suggestion
	 * @param status      The status of the Suggestion
	 */
	public Suggestion(String campId, String creatorId, String responderId, String message, SuggestionStatus status) {
		this.suggestionId = UUID.randomUUID().toString();
		this.campId = campId;
		this.creatorId = creatorId;
		this.responderId = responderId;
		this.message = message;
		this.status = status;
	}

	/**
	 * Gets the ID of the Suggestion
	 * 
	 * @return String The ID of the Suggestion
	 */
	public String getSuggestionId() {
		return suggestionId;
	}

	/**
	 * Sets the ID of the Suggestion
	 * 
	 * @param suggestionId The ID of the Suggestion
	 */
	public void setSuggestionId(String suggestionId) {
		this.suggestionId = suggestionId;
	}

	/**
	 * Gets the Camp ID of the Camp the Suggestion is for
	 * 
	 * @return String The Camp ID of the Camp the Suggestion is for
	 */
	public String getCampId() {
		return campId;
	}

	/**
	 * Sets the Camp ID of the Camp the Suggestion is for
	 * 
	 * @param campId The Camp ID of the Camp the Suggestion is for
	 */
	public void setCampId(String campId) {
		this.campId = campId;
	}

	/**
	 * Gets the User ID of the Committee who wrote the Suggestion
	 * 
	 * @return String The User ID of the Committee who wrote the Suggestion
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * Sets the User ID of the Committee who wrote the Suggestion
	 * 
	 * @param creatorId The User ID of the Committee who wrote the Suggestion
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * Gets the User ID of the Staff who approves/rejects the Suggestion
	 * 
	 * @return String The User ID of the Staff who approves/rejects the Suggestion
	 */
	public String getResponderId() {
		return responderId;
	}

	/**
	 * Sets the User ID of the Staff who approves/rejects the Suggestion
	 * 
	 * @param responderId The User ID of the Staff who approves/rejects the
	 *                    Suggestion
	 */
	public void setResponderId(String responderId) {
		this.responderId = responderId;
	}

	/**
	 * Gets the Message of the Suggestion
	 * 
	 * @return String The Message of the Suggestion
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the Message of the Suggestion
	 * 
	 * @param message The Message of the Suggestion
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the Status of the Suggestion
	 * 
	 * @return SuggestionStatus The Status of the Suggestion
	 */
	public SuggestionStatus getStatus() {
		return status;
	}

	/**
	 * Sets the Status of the Suggestion
	 * 
	 * @param status The Status of the Suggestion
	 */
	public void setStatus(SuggestionStatus status) {
		this.status = status;
	}
}
