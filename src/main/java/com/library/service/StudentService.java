package com.library.service;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.util.DbConnection;  // Import the DbConnection utility
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO;

    // Constructor without parameters (uses default connection)
    public StudentService() {
        try {
            // Obtain a Connection object using DbConnection
            Connection connection = DbConnection.getConnection();
            // Pass the connection to the StudentDAO constructor
            this.studentDAO = new StudentDAO(); // Pass the connection
        } catch (SQLException e) {
            System.err.println("Error while connecting to the database: " + e.getMessage());
        }
    }

    // Constructor with StudentDAO passed as parameter (for dependency injection)
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    // Add a student
    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    // Display all students
    public void displayStudents() {
        List<Student> students = studentDAO.getAllStudents();
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + " | Name: " + student.getName());
        }
    }

    // Find a student by ID
    public Student findStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    // Update student details
    public boolean updateStudent(int id, String name) {
        // Fetch student by ID
        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found with ID: " + id);
            return false; // Indicate that the student wasn't found
        }

        // Update student details
        student.setName(name);

        // Save the updated student back to the database
        studentDAO.updateStudent(student);
        return true; // Indicate success
    }

    // Delete a student
    public void deleteStudent(int id) {
        boolean deleted = studentDAO.deleteStudent(id);

        if (deleted) {
            System.out.println("Student with ID " + id + " was deleted successfully.");
        } else {
            System.out.println("No student found with ID " + id + " to delete.");
        }
    }
}
