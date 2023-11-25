package model.enums;

/**
 * Status of a Suggestion
 * 
 * @author Chloie
 * @version 1.1.3
 * @since 2023-11-04
 */
public enum SuggestionStatus {
    /** Pending Suggestion Status */
    PENDING("Pending"),
    /** Accepted Suggestion Status */
    ACCEPTED("Accepted"),
    /** Rejected Suggestion Status */
    REJECTED("Rejected");

    /** The display name of the Suggestion Status */
    private final String suggestionStatusStr;

    /**
     * Constructor of the SuggestionStaus Enum
     * 
     * @param suggestionStatusStr The Status of a Suggestion
     */
    private SuggestionStatus(String suggestionStatusStr) {
        this.suggestionStatusStr = suggestionStatusStr;
    }

    /**
     * Gets the display name of the Suggestion Status
     * 
     * @return The display name of the Suggestion Status
     */
    public String getSuggestionStatus() {
        return suggestionStatusStr;
    }
}
