package model.enums;

public enum SuggestionStatus {
    PENDING ("Pending"),
    ACCEPTED ("Accepted"),
    REJECTED ("REJECTED");

    private final String suggestionStatusStr;

    private SuggestionStatus(String suggestionStatusStr) {
        this.suggestionStatusStr = suggestionStatusStr;
    }

    public String getSuggestionStatus() {
        return suggestionStatusStr;
    }
}
