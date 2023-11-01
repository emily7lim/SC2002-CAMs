package database;

import model.Student;

public class StudentDAO {
    public static Student getUserbyId(String studentId) {
        return Database.STUDENTS.get(studentId);
    }
    
    public static void createUser(Student student) {
        Database.STUDENTS.put(student.getUserId(), student);
    }
}
