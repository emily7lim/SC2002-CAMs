package model;

import java.io.Serializable;
import java.util.UUID;

import model.enums.SuggestionStatus;

public class Suggestion implements Serializable {
    protected static final long serialVersionUID = 4L;
	private String suggestionId;
	private String campId;
	private String creatorId;
	private String responderId;
	private String message;
	private SuggestionStatus status;

	public Suggestion(String campId, String creatorId, String message) {
		this.suggestionId = UUID.randomUUID().toString();
		this.campId = campId;
		this.creatorId = creatorId;
		this.responderId = null;
		this.message = message;
		this.status = SuggestionStatus.PENDING;
	}

	public Suggestion(String campId, String creatorId, String responderId, String message, SuggestionStatus status) {
		this.suggestionId = UUID.randomUUID().toString();
		this.campId = campId;
		this.creatorId = creatorId;
		this.responderId = responderId;
		this.message = message;
		this.status = status;
	}

	
	/** 
	 * @return String
	 */
	public String getSuggestionId() {
		return suggestionId;
	}

	public void setSuggestionId(String suggestionId) {
		this.suggestionId = suggestionId;
	}

	public String getCampId() {
		return campId;
	}

	public void setCampId(String campId) {
		this.campId = campId;
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

	public SuggestionStatus getStatus() {
		return status;
	}

	public void setStatus(SuggestionStatus status) {
		this.status = status;
	}
}
