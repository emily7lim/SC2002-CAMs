package controller;

import java.util.ArrayList;

import model.Suggestion;
import model.enums.SuggestionStatus;
import database.CampDAO;
import database.SuggestionDAO;

/**
 * SuggestionController class to manage Suggestions
 * 
 * @author Chloie, Owen
 * @version 1.1.8
 * @since 2023-11-04
 */
public class SuggestionController {
    /**
     * Creates a new Suggestion and adds to database
     * 
     * @param suggestionId The Suggestion ID of the new Suggestion
     * @param creatorId    The User ID of the creator of the new Suggestion
     * @param message      The message of the new Suggestion
     */
    public static String createSuggestion(String campId, String creatorId, String message) {
        Suggestion suggestion = new Suggestion(campId, creatorId, message);
        SuggestionDAO.createSuggestion(suggestion);
        CampDAO.updateSuggestion(campId, suggestion.getSuggestionId(), true);

        return suggestion.getSuggestionId();
    }

    /**
     * Retrieves a list of all Suggestions from the database
     * 
     * @return ArrayList<Suggestion> The list of all the Suggestions
     */
    public static ArrayList<Suggestion> getAllSuggestions() {
        return SuggestionDAO.getAllSuggestions();
    }

    /**
     * Retrieves a list of all Suggestion from the database by the Camp ID
     * 
     * @param campId The Camp ID of the Suggestion
     * @return ArrayList<Suggestion> The list of all the Suggestions with Camp ID
     */
    public static ArrayList<Suggestion> getSuggestionsByCampId(String campId) {
        return SuggestionDAO.getSuggestionsbyCampId(campId);
    }

    /**
     * Retrieves a list of all approved/ or rejected Suggestion from the database by the Camp ID
     * 
     * @param campId The Camp ID of the Suggestion
     * @return ArrayList<Suggestion> The list of all the Suggestions with Camp ID
     */
    public static ArrayList<Suggestion> getApprovedRejectedSuggestionsByCampId(String campId) {
        ArrayList<Suggestion> suggestions = SuggestionDAO.getSuggestionsbyCampIdAndStatus(campId,
                SuggestionStatus.ACCEPTED);
        suggestions.addAll(SuggestionDAO.getSuggestionsbyCampIdAndStatus(campId, SuggestionStatus.REJECTED));

        return suggestions;
    }

    /**
     * Retrieves a list of all pending Suggestion from the database by the Camp ID
     * 
     * @param campId The Camp ID of the Suggestion
     * @return ArrayList<Suggestion> The list of all the Suggestions with Camp ID
     */
    public static ArrayList<Suggestion> getPendingSuggestionsByCampId(String campId) {
        return SuggestionDAO.getSuggestionsbyCampIdAndStatus(campId, SuggestionStatus.PENDING);
    }

    /**
     * Retrieves a list of all Suggestion from the database by the Camp ID and creator ID
     * 
     * @param campId
     * @param creatorId
     * @return ArrayList<Suggestion>
     */
    public static ArrayList<Suggestion> getSuggestionsbyCampIdAndCreatorId(String campId, String creatorId) {
        return SuggestionDAO.getSuggestionsbyCampIdAndCreatorId(campId, creatorId);
    }

    /**
     * Retrieves a list of approved or rejected Suggestion from the database by the Camp ID and creator ID
     * 
     * @param campId
     * @param creatorId
     * @return ArrayList<Suggestion>
     */
    public static ArrayList<Suggestion> getApprovedRejectedSuggestionsByCampIdAndCreatorId(String campId,
            String creatorId) {
        ArrayList<Suggestion> suggestions = SuggestionDAO.getSuggestionsbyCampIdAndStatusAndCreatorId(campId,
                SuggestionStatus.ACCEPTED, creatorId);
        suggestions.addAll(SuggestionDAO.getSuggestionsbyCampIdAndStatusAndCreatorId(campId, SuggestionStatus.REJECTED,
                creatorId));

        return suggestions;
    }

    /**
     * Finds a Suggestion from the database by the Suggestion ID
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @return Suggestion The corresponsing Suggestion object, NULL if not found
     */
    public static Suggestion getSuggestionById(String suggestionId) {
        return SuggestionDAO.getSuggestionbyId(suggestionId);
    }

    /**
     * Updates the message of a Suggestion
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @param message      The new message of the Suggestion
     * @return boolean Whether the Suggestion message was successfully updated
     */
    public static boolean updateSuggestionMessage(String suggestionId, String message) {
        if (!checkSuggestionExists(suggestionId))
            return false;

        SuggestionDAO.updateSuggestionMessage(suggestionId, message);
        return true;
    }

    /**
     * Updates the status of a Suggestion to accepted
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @param responderId  The User ID of the User accepting the Suggestion
     * @return boolean Whether the Suggestion status was successfully updated
     */
    public static boolean acceptSuggestion(String suggestionId, String responderId, String creatorId) {
        if (!checkSuggestionExists(suggestionId))
            return false;

        SuggestionDAO.updateSuggestionResponse(suggestionId, SuggestionStatus.ACCEPTED, responderId);
        return true;
    }

    /**
     * Updates the status of a Suggestion to rejected
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @param responderId  The User ID of the User rejecting the Suggestion
     * @return boolean Whether the Suggestion status was successfully updated
     */
    public static boolean rejectSuggestion(String suggestionId, String responderId) {
        if (!checkSuggestionExists(suggestionId))
            return false;

        SuggestionDAO.updateSuggestionResponse(suggestionId, SuggestionStatus.REJECTED, responderId);
        return true;
    }

    /**
     * Deletes a Suggestion from the database
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @return boolean Whether the Suggestion was successfully deleted
     */
    public static boolean deleteSuggestion(String suggestionId) {
        if (!checkSuggestionExists(suggestionId))
            return false;

        SuggestionDAO.deleteSuggestion(suggestionId);
        return true;
    }

    /**
     * Check if Suggestion with Suggestion ID exists in the database
     * 
     * @param suggestionId The Suggestion ID of the Suggestion
     * @return boolean Whether the Suggestion exists in the database
     */
    public static boolean checkSuggestionExists(String suggestionId) {
        return SuggestionDAO.checkSuggestion(suggestionId);
    }
}
