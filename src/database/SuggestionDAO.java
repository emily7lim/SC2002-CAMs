package database;

import model.Suggestion;

public class SuggestionDAO {
    /** 
     * Finds a Suggestion from the database using the id
     * @param suggestionId The Suggestion id of the Suggestion  
     * @return Suggestion The corresponding Suggestion object, NULL if not found
     */
    public static Suggestion getSuggestionbyId(Integer suggestionId) {
        return Database.SUGGESTIONS.get(suggestionId);
    }
    
    /** 
     * Adds a Suggestion to the database
     * @param suggestion The Suggestion Object to save
     */
    public static void createSuggestion(Suggestion suggestion) {
        Database.SUGGESTIONS.put(suggestion.getSuggestionId(), suggestion);
    }

    /** 
     * Removes a Suggestion from the database using the id
     * @param suggestionId The Suggestion id of the Suggestion
     */
    public static void deleteSuggestion(Integer suggestionId) {
        Database.SUGGESTIONS.remove(suggestionId);
    }
}
