package controller;

import model.Student;
import model.enums.Faculty;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private List<Student> students;

    public StudentController() {
        students = new ArrayList<>();
    }

    // Create a new student profile and add it to the list
    public Student createStudent(String userId, String name, String password, Faculty faculty) {
        Student student = new Student(userId, name, password, faculty);
        students.add(student);
        return student;
    }

    // Get a list of all student profiles
    public List<Student> getAllStudents() {
        return students;
    }

    // Find a student profile by their user ID
    public Student findStudentByUserId(String userId) {
        for (Student student : students) {
            if (student.getUserId().equals(userId)) {
                return student;
            }
        }
        return null; //Student with that ID not found
    }

    // Update student information, e.g., points or camp IDs
    public boolean updateStudentInformation(String userId, int newPoints, ArrayList<String> newCampIds) {
        Student student = findStudentByUserId(userId);
        if (student != null) {
            student.setPoints(newPoints);
            student.setCampIds(newCampIds);
            return true;
        }
        return false; // Student not found
    }

    // Remove a student profile from the list
    public boolean deleteStudent(String userId) {
        Student student = findStudentByUserId(userId);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false; // Student not found
    }
}
