package model;

public class Suggestion 
{

	private int suggestionId;
	private String sender;
	private String receiver;
	private String message;
	private String status;
	
	public Suggestion(int suggestionId, String sender, String receiver, String message, String status)
	{
		this.suggestionId = suggestionId;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.status = status;
	}
	
	public int getSuggestionId() {
		return suggestionId;
	}

	public void setSuggestionId(int suggestionId) {
		this.suggestionId = suggestionId;
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
	
	public void processSuggestion()
	{
        
    }
    
}
