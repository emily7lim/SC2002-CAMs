package model.enums;

public enum Faculty {
    NTU ("Nanyang Technological University"),
    NBS ("Nanyang Business School"),
    ADM ("School of Art, Design and Media"),
    SBS ("School of Biological Sciences"),
    SCBE ("School of Chemical and Biomedical Engineering"),
    CEE ("School of Civil and Environmental Engineering"),
    SCSE ("School of Computer Science and Engineering"),
    EEE ("School of Electrical and Electronic Engineering"),
    SoH ("School of Humanities"),
    SSS ("School of Social Sciences"),
    SPMS ("School of Physical and Mathematical Sciences"),
    WKWSCI ("Wee Kim Wee School of Communication and Information");

    private final String facultyStr;

    private Faculty(String facultyStr) {
        this.facultyStr = facultyStr;
    }

    public String getFacultyStr() {
        return facultyStr;
    }
}
