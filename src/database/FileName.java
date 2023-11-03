package database;

public enum FileName {
    USERS ("Users"),
    CAMPS ("Camps"),
    ENQUIRIES ("Enquiries"),
    SUGGESTIONS ("Suggestions");

    private final String fileNameStr;

    private FileName(String fileNameStr) {
        this.fileNameStr = fileNameStr;
    }

    public String getFileNameStr() {
        return fileNameStr;
    }
}
