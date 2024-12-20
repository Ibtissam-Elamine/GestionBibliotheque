package com.library;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;
    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() throws SQLException {
        studentDAO = new StudentDAO(); // Utilisation de StudentDAO sans connexion, car elle est gérée dans StudentService
        studentService = new StudentService(studentDAO); // Injection du DAO dans StudentService
        // Clear the existing students from the database before each test
        studentDAO.deleteAllStudents();
    }

    @Test
    void testAddStudent() {
        // Création d'un objet Student sans ID spécifié
        Student student = new Student(1,"Alice");
        studentService.addStudent(student);

        // Vérification si l'étudiant a bien été ajouté
        assertEquals(1, studentDAO.getAllStudents().size());  // 1 étudiant ajouté
        assertEquals("Alice", studentDAO.getStudentById(student.getId()).get().getName());
    }


    @Test
    void testUpdateStudent() {
        // Ajout d'un étudiant à la base de données
        Student student = new Student(6, "Alice");
        studentService.addStudent(student);

        // Mise à jour de l'étudiant
        studentService.updateStudent(6, "Alice Smith");
        
        // Vérification que le nom a été mis à jour
        assertEquals("Alice Smith", studentDAO.getStudentById(6).get().getName());
    }

    @Test
    void testDeleteStudent() {
        // Adding a student to the database
        Student student = new Student(18, "Alice");
        studentService.addStudent(student);

        // Deleting the student
        studentService.deleteStudent(18);

        // Check that the student has been deleted
        Student deletedStudent = studentDAO.getStudentById(18);
        assertNull(deletedStudent);  // Assert that the student is null, meaning they were deleted
    }


    @Test
    void testGetAllStudents() {
        // Add multiple students to the database
        Student student1 = new Student(22, "Alice");
        Student student2 = new Student(25, "Bob");
        studentService.addStudent(student1);
        studentService.addStudent(student2);

        // Check that the number of students is correct
        // Adjust the expected value if there are other students already in the database
        assertEquals(2, studentDAO.getAllStudents().size());  // Ensure it matches the actual number of students
    }

}
