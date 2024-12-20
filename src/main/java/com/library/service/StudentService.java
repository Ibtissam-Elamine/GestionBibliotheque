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
            this.studentDAO = new StudentDAO(connection);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    // Constructor with StudentDAO passed as parameter
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
            System.out.println("ID: " + student.getId() + " | Nom: " + student.getName());
        }
    }

    // Find a student by ID
    public Student findStudentById(int id) {
        return studentDAO.getStudentById(id);
    }
}