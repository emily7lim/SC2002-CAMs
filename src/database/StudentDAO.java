package database;

import model.Student;

public class StudentDAO {
    /** 
     * Finds a student from the database using the id
     * @param studentId The Student id of the student  
     * @return Student The corresponding student object, NULL if not found
     */
    public static Student getUserbyId(String studentId) {
        return Database.STUDENTS.get(studentId);
    }

    /** 
     * Adds a student to the database
     * @param student The Student Object to save
     */
    public static void createUser(Student student) {
        Database.STUDENTS.put(student.getUserId(), student);
    }

    /** 
     * Removes a student from the database using the id
     * @param studentId The Student id of the student
     */
    public static void deleteUser(String staffId) {
        Database.STAFFS.remove(staffId);
    }
}
