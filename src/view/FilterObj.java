package view;

import java.util.ArrayList;
import java.util.List;

/**
 * The FilterObj class is used to filter what details to print in a report.
 * It allows users to specify which information should be included in the report
 * by selecting or deselecting various filtering options.
 * 
 * @author Choh Lit Han Owen
 * @version 1.0
 * @since 2023-11-13
 */
public class FilterObj {
    private boolean selectedAttendee;
    private ArrayList<String> matchAttendeeName;
    private boolean selectedCampCommittee;
    private ArrayList<String> matchCampCommitteeName;
    

    /**
     * Constructs a new FilterObj with default selection status (all set to false).
     */
    public FilterObj() {
        this(false);
    }

    /**
     * Constructs a new FilterObj with specified initial selection status.
     *
     * @param init true to initialize all filters as selected, false for deselected
     */
    public FilterObj(boolean init) {
        this.selectedAttendee = this.selectedCampCommittee = init;
        this.matchAttendeeName = new ArrayList<String>();
        this.matchCampCommitteeName = new ArrayList<String>();
    }

    /**
     * Constructs a new FilterObj with custom initial selection status for each filter.
     *
     * @param selectedAttendee true to select attendee information, false to deselect
     * @param selectedCampCommittee true to select camp committee information, false to deselect
     * @param selectedRoles true to select roles information, false to deselect
     */
    public FilterObj(boolean selectedAttendee, boolean selectedCampCommittee, boolean selectedRoles) {
        this.selectedAttendee = selectedAttendee;
        this.selectedCampCommittee = selectedCampCommittee;
        this.matchAttendeeName = new ArrayList<String>();
        this.matchCampCommitteeName = new ArrayList<String>();
    }

    /**
     * Gets the current selection status for attendee information.
     *
     * @return true if attendee information is selected, false otherwise
     */
    public boolean isSelectedAttendee() {
        return selectedAttendee;
    }

    /**
     * Sets the selection status for attendee information.
     *
     * @param selectedAttendee true to select attendee information, false to deselect
     */
    public void setSelectedAttendee(boolean selectedAttendee) {
        this.selectedAttendee = selectedAttendee;
    }

    /**
     * Toggles the selection status for attendee information.
     * If it was selected, it will be deselected, and vice versa.
     */
    public void toggleSelectedAttendee() {
        this.selectedAttendee = !this.selectedAttendee;
    }
    
    /**
     * Gets the current selection status for camp committee information.
     *
     * @return true if camp committee information is selected, false otherwise
     */
    public boolean isSelectedCampCommittee() {
        return selectedCampCommittee;
    }

    /**
     * Sets the selection status for camp committee information.
     *
     * @param selectedCampCommittee true to select camp committee information, false to deselect
     */
    public void setSelectedCampCommittee(boolean selectedCampCommittee) {
        this.selectedCampCommittee = selectedCampCommittee;
    }

    /**
     * Toggles the selection status for camp committee information.
     * If it was selected, it will be deselected, and vice versa.
     */
    public void toggleSelectedCampCommittee() {
        this.selectedCampCommittee = !this.selectedCampCommittee;
    }
    
    /**
     * Returns the strings to match with camp attendee name.
     *
     * @return Arraylist<String> of strings to match with camp attendee name.
     */
    public ArrayList<String> getMatchAttendeeName() {
        return matchAttendeeName;
    }

    /**
     * Sets the strings to match for camp attendees.
     *
     * @param matchAttendeeName the list of names to match
     */
    public void setMatchAttendeeName(ArrayList<String> matchAttendeeName) {
        this.matchAttendeeName = matchAttendeeName == null ? new ArrayList<String>() : matchAttendeeName;
    }

    /**
     * Adds the parameter to the camp attendee name arraylist.
     *
     * @param matchAttendeeName the new string to match
     */
    public void addMatchAttendeeName(String matchAttendeeName) {
        if(this.matchAttendeeName == null) this.matchAttendeeName = new ArrayList<String>();
        this.matchAttendeeName.add(matchAttendeeName);
    }

    /**
     * Remove the specified string from the camp attendee name arraylist.
     *
     * @param matchAttendeeName the string to remove
     */
    public void removeMatchAttendeeName(String matchAttendeeName) {
        if(this.matchAttendeeName == null) return;
        this.matchAttendeeName.remove(matchAttendeeName);
    }

    /**
     * Returns the strings to match with camp committee name.
     *
     * @return Arraylist<String> of strings to match with camp committee name.
     */
    public ArrayList<String> getMatchCampCommitteeName() {
        return matchCampCommitteeName;
    }
/**
     * Sets the strings to match for camp committee.
     *
     * @param matchAttendeeName the list of names to match
     */
    public void setMatchCampCommitteeName(ArrayList<String> matchCampCommitteeName) {
        this.matchCampCommitteeName = matchCampCommitteeName == null ? new ArrayList<String>() : matchCampCommitteeName;
    }

    /**
     * Adds the parameter to the camp committee name arraylist.
     *
     * @param matchAttendeeName the new string to match
     */
    public void addMatchCampCommitteeName(String matchCampCommitteeName) {
        if(this.matchCampCommitteeName == null) this.matchCampCommitteeName = new ArrayList<String>();
        this.matchCampCommitteeName.add(matchCampCommitteeName);
    }

    /**
     * Remove the specified string from the camp committee name arraylist.
     *
     * @param matchAttendeeName the string to remove
     */
    public void removeMatchCampCommitteeName(String matchCampCommitteeName) {
        if(this.matchCampCommitteeName == null) return;
        this.matchCampCommitteeName.remove(matchCampCommitteeName);
    }

    /**
     * Sets multiple variables relevant to staff view case 5
     * "generate a report of a list of students attending each camp the staff created."
     */
    public void setAllCase5(boolean all) {
        this.setSelectedAttendee(all);
        this.setSelectedCampCommittee(all);
    }

    /**
     * Checks multiple variables if any is true.
     * Variables relevant to staff view case 5
     * "generate a report of a list of students attending each camp the staff created."
     *
     * @return true if any filtering option is selected, false otherwise
     */
    public boolean isAnyCase5() {
        return this.selectedAttendee || this.selectedCampCommittee;
    }
}
