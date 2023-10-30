package database;

public enum FileName {
    USERS ("Users"),
    CAMPS ("Camps");

    private final String fileNameStr;

    private FileName(String fileNameStr) {
        this.fileNameStr = fileNameStr;
    }

    public String getFileNameStr() {
        return fileNameStr;
    }
}
