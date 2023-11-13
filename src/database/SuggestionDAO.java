package database;

import java.util.ArrayList;

import model.Enquiry;
import model.Suggestion;
import model.enums.SuggestionStatus;

public class SuggestionDAO {
    /**
     * Adds a Suggestion to the database
     * and Updates corresponding camp
     * 
     * @param suggestion The Suggestion Object to save
     */
    public static void createSuggestion(Suggestion suggestion) {
        Database.SUGGESTIONS.put(suggestion.getSuggestionId(), suggestion);
        CampDAO.updateSuggestion(suggestion.getCampId(), suggestion.getCampId(), true);
    }

    /**
     * Retrieve all Suggestions from the database
     * 
     * @return ArrayList<Suggestion> The list of all Suggestions
     */
    public static ArrayList<Suggestion> getAllSuggestions() {
        return new ArrayList<Suggestion>(Database.SUGGESTIONS.values());
    }

    /**
     * Finds a Suggestion from the database using the ID
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @return Suggestion The corresponding Suggestion object, NULL if not found
     */
    public static Suggestion getSuggestionbyId(String suggestionId) {
        return Database.SUGGESTIONS.get(suggestionId);
    }

    /**
     * Check if a Suggestion exists in the database using the ID
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @return boolean Whether the Suggestion exists
     */
    public static boolean checkSuggestion(String suggestionId) {
        return Database.SUGGESTIONS.containsKey(suggestionId);
    }

    /**
     * Update the message of a Suggestion in the database using the ID
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @param message      The new message of the Suggestion
     */
    public static void updateSuggestionMessage(String suggestionId, String message) {
        Database.SUGGESTIONS.get(suggestionId).setMessage(message);
    }

    /**
     * Update the response and status of a Suggestion in the database using the ID
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @param status       The new status of the Suggestion
     * @param responderId  The User ID of the User responding to the Suggestion
     */
    public static void updateSuggestionResponse(String suggestionId, SuggestionStatus status, String responderId) {
        Suggestion suggestion = Database.SUGGESTIONS.get(suggestionId);
        suggestion.setStatus(status);
        suggestion.setresponderId(responderId);
    }

    /**
     * Removes a Suggestion from the database using the ID
     * and Updates corresponding camp
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     */
    public static void deleteSuggestion(String suggestionId) {
        Suggestion deleted = Database.SUGGESTIONS.remove(suggestionId);
        if (deleted != null)
            CampDAO.updateSuggestion(deleted.getCampId(), deleted.getCampId(), false);
    }
}
