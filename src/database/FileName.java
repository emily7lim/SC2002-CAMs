package database;

/**
 * FileName
 * 
 * @author Owen, Chloie
 * @version 1.3.1
 * @since 2023-10-30
 */
public enum FileName {
    /** Users */
    USERS("Users"),
    /** Camps */
    CAMPS("Camps"),
    /** Enquiries */
    ENQUIRIES("Enquiries"),
    /** Suggestions */
    SUGGESTIONS("Suggestions");

    /** The display name of the File Name */
    private final String fileNameStr;

    /**
     * Constructs and initializes a FileName object with File Name
     * 
     * @param fileNameStr The Name of the file
     */
    private FileName(String fileNameStr) {
        this.fileNameStr = fileNameStr;
    }

    /**
     * Gets the Name of the File
     * 
     * @return String The Name of the File
     */
    public String getFileNameStr() {
        return fileNameStr;
    }
}
