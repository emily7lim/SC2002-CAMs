package database;

public enum FileName {
    USERS ("Users"),
    STUDENTS ("Students"),
    STAFFS ("Staffs"),
    CAMPS ("Camps");

    private final String fileNameStr;

    private FileName(String fileNameStr) {
        this.fileNameStr = fileNameStr;
    }

    public String getFileNameStr() {
        return fileNameStr;
    }
}
