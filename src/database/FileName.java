package database;

public enum FileName {
    USERS ("Users"),
    STUDENTS ("Students"),
    STAFFS ("Staffs"),
    CAMPS ("Camps"),
    ENQUIRYS ("Enquirys"),
    SUGGESTIONS ("Suggestions");

    private final String fileNameStr;

    private FileName(String fileNameStr) {
        this.fileNameStr = fileNameStr;
    }

    public String getFileNameStr() {
        return fileNameStr;
    }
}
