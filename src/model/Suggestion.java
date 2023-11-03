package model;

import java.io.Serializable;

import model.enums.SuggestionStatus;

public class Suggestion implements Serializable {
    protected static final long serialVersionUID = 4L;
	private String suggestionId;
	private String creatorId;
	private String responderId;
	private String message;
	private SuggestionStatus status;

	public Suggestion(String suggestionId, String creatorId, String message) {
		this.suggestionId = suggestionId;
		this.creatorId = creatorId;
		this.responderId = null;
		this.message = message;
		this.status = SuggestionStatus.PENDING;
	}

	public Suggestion(String suggestionId, String creatorId, String responderId, String message, SuggestionStatus status) {
		this.suggestionId = suggestionId;
		this.creatorId = creatorId;
		this.responderId = responderId;
		this.message = message;
		this.status = status;
	}

	public String getSuggestionId() {
		return suggestionId;
	}

	public void setSuggestionId(String suggestionId) {
		this.suggestionId = suggestionId;
	}

	public String getcreatorId() {
		return creatorId;
	}

	public void setcreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getresponderId() {
		return responderId;
	}

	public void setresponderId(String responderId) {
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
