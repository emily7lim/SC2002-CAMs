package view;

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
    private boolean selectedCampCommittee;
    private boolean selectedRoles;

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
        this.selectedAttendee = this.selectedCampCommittee = this.selectedRoles = init;
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
        this.selectedRoles = selectedRoles;
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
     * Gets the current selection status for roles information.
     *
     * @return true if roles information is selected, false otherwise
     */
    public boolean isSelectedRoles() {
        return selectedRoles;
    }

    /**
     * Sets the selection status for roles information.
     *
     * @param selectedRoles true to select roles information, false to deselect
     */
    public void setSelectedRoles(boolean selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    /**
     * Toggles the selection status for roles information.
     * If it was selected, it will be deselected, and vice versa.
     */
    public void toggleSelectedRoles() {
        this.selectedRoles = !this.selectedRoles;
    }

    /**
     * Sets multiple variables relevant to staff view case 5
     * "generate a report of a list of students attending each camp the staff created."
     */
    public void setAllCase5(boolean all) {
        this.setSelectedAttendee(all);
        this.setSelectedCampCommittee(all);
        this.setSelectedRoles(all);
    }

    /**
     * Checks multiple variables if any is true.
     * Variables relevant to staff view case 5
     * "generate a report of a list of students attending each camp the staff created."
     *
     * @return true if any filtering option is selected, false otherwise
     */
    public boolean isAnyCase5() {
        return this.selectedAttendee || this.selectedCampCommittee || this.selectedRoles;
    }
}
