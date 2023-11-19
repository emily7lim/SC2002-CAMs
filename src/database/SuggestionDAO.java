package database;

import java.util.ArrayList;

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
     * Retrieve all Suggestions from the database using the Camp ID
     * 
     * @param campId The Camp ID of the Suggestion
     * @return ArrayList<Suggestion> The list of all Suggestions with Camp ID
     */
    public static ArrayList<Suggestion> getSuggestionsbyCampId(String campId) {
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        for (Suggestion suggestion : Database.SUGGESTIONS.values())
            if (suggestion.getCampId().equals(campId))
                suggestions.add(suggestion);
        
        return suggestions;
    }

    public static ArrayList<Suggestion> getSuggestionsbyCampIdAndCreatorId(String campId, String creatorId) {
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        for (Suggestion suggestion : Database.SUGGESTIONS.values())
            if (suggestion.getCampId().equals(campId) && suggestion.getCreatorId().equals(creatorId))
                suggestions.add(suggestion);
        
        return suggestions;
    }


    /**
     * Retrieve all Suggestions from the database using the Camp ID and Status
     * 
     * @param campId The Camp ID of the Suggestion
     * @param status The Status of the Suggestion
     * @return ArrayList<Suggestion> The list of all Suggestions with Camp ID and Status
     */
    public static ArrayList<Suggestion> getSuggestionsbyCampIdAndStatus(String campId, SuggestionStatus status) {
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        for (Suggestion suggestion : Database.SUGGESTIONS.values())
            if (suggestion.getCampId().equals(campId) && suggestion.getStatus() == status)
                suggestions.add(suggestion);
        
        return suggestions;
    }

    public static ArrayList<Suggestion> getSuggestionsbyCampIdAndStatusAndCreatorId(String campId, SuggestionStatus status, String creatorId) {
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        for (Suggestion suggestion : Database.SUGGESTIONS.values())
            if (suggestion.getCampId().equals(campId) && suggestion.getStatus() == status && suggestion.getCreatorId().equals(creatorId))
                suggestions.add(suggestion);
        
        return suggestions;
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
        suggestion.setResponderId(responderId);
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
