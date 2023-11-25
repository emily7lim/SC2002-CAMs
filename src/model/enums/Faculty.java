package model.enums;

/**
 * Faculties in NTU
 * 
 * @author Chloie
 * @version 1.1.4
 * @since 2023-10-23
 */
public enum Faculty {
    /** All Faculties (NTU) */
    NTU("Nanyang Technological University"),
    /** Nanyang Business School Faculty (NBS) */
    NBS("Nanyang Business School"),
    /** School of Art, Design and Media Faculty (ADM) */
    ADM("School of Art, Design and Media"),
    /** School of Biological Sciences Faculty (SBS) */
    SBS("School of Biological Sciences"),
    /** School of Chemical and Biomedical Engineering Faculty (SCBE) */
    SCBE("School of Chemical and Biomedical Engineering"),
    /** School of Civil and Environmental Engineering Faculty (CEE) */
    CEE("School of Civil and Environmental Engineering"),
    /** School of Computer Science and Engineering Faculty (SCSE) */
    SCSE("School of Computer Science and Engineering"),
    /** School of Electrical and Electronic Engineering Faculty (EEE) */
    EEE("School of Electrical and Electronic Engineering"),
    /** School of Humanities Faculty (SoH) */
    SoH("School of Humanities"),
    /** School of Social Sciences Faculty (SSS) */
    SSS("School of Social Sciences"),
    /** School of Physical and Mathematical Sciences Faculty (SPMS) */
    SPMS("School of Physical and Mathematical Sciences"),
    /** Wee Kim Wee School of Communication and Information Faculty (WKWSCI) */
    WKWSCI("Wee Kim Wee School of Communication and Information");

    /** The display name of a Faculty in NTU */
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
