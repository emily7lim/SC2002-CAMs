package model.enums;

/**
 * Faculties in NTU
 * 
 * @author Chloie
 * @version 1.1.4
 * @since 2023-10-23
 */
public enum Faculty {
    NTU("Nanyang Technological University"),
    NBS("Nanyang Business School"),
    ADM("School of Art, Design and Media"),
    SBS("School of Biological Sciences"),
    SCBE("School of Chemical and Biomedical Engineering"),
    CEE("School of Civil and Environmental Engineering"),
    SCSE("School of Computer Science and Engineering"),
    EEE("School of Electrical and Electronic Engineering"),
    SoH("School of Humanities"),
    SSS("School of Social Sciences"),
    SPMS("School of Physical and Mathematical Sciences"),
    WKWSCI("Wee Kim Wee School of Communication and Information");

    private final String facultyStr;

    /**
     * Constructor of the Faculty Enum
     * 
     * @param facultyStr The Faculty in NTU
     */
    private Faculty(String facultyStr) {
        this.facultyStr = facultyStr;
    }

    /**
     * Gets the display name of a Faculty in NTU
     * 
     * @return The display name of a Faculty in NTU
     */
    public String getFaculty() {
        return facultyStr;
    }
}
