package com.library;

import com.library.dao.BookDAO;
import com.library.dao.StudentDAO;
import com.library.dao.BorrowDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.BorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BorrowServiceTest {
    private BorrowService borrowService;
    private BookDAO bookDAO;
    private StudentDAO studentDAO;
    private BorrowDAO borrowDAO;

    @BeforeEach
    void setUp() throws SQLException {
        bookDAO = new BookDAO();
        studentDAO = new StudentDAO();
        borrowDAO = new BorrowDAO();
        borrowService = new BorrowService(borrowDAO); // Utilisation du constructeur avec BorrowDAO
// Remove existing students before setup
        studentDAO.deleteAllStudents(); // Assuming a method to clear all students
        // Ajouter des étudiants
        studentDAO.addStudent(new Student(1, "Alice"));
        studentDAO.addStudent(new Student(2, "Bob"));

        // Ajouter des livres
        bookDAO.add(new Book("Java Programming", "John Doe", "TechPress", 2022, "1234567890", 2022));
        bookDAO.add(new Book("Advanced Java", "Jane Doe", "CodeBooks", 2023, "0987654321", 2023));
    }

    /*@Test
    void testBorrowBook() {
        // Créer un emprunt avec un objet Borrow en utilisant les IDs
        Student student = studentDAO.getStudentById(1);
        Book book = bookDAO.getBookById(1);

        assertNotNull(student, "Étudiant non trouvé");
        assertNotNull(book, "Livre non trouvé");

        // Créer l'objet Borrow avec les IDs de l'étudiant et du livre
        Borrow borrow = new Borrow(student, book, new Date());

        // Emprunter le livre
        borrowService.borrowBook(borrow);

        // Vérifier que le livre est désormais emprunté (vérification dans la base de données ou via l'absence d'un emprunt)
        Borrow existingBorrow = borrowDAO.getBorrowById(book.getId());
        assertNotNull(existingBorrow, "L'emprunt du livre n'a pas été effectué"); // Vérifier qu'il y a bien un emprunt enregistré pour ce livre
    }

    @Test
    void testReturnBook() {
        // Emprunter un livre
        Student student = studentDAO.getStudentById(1);
        Book book = bookDAO.getBookById(1);
        Borrow borrow = new Borrow(student, book, new Date());
        borrowService.borrowBook(borrow);

        // Retourner le livre
        assertEquals("Livre retourné avec succès!", borrowService.returnBook(1, 1));

        // Vérifier que l'emprunt est supprimé de la base de données
        Borrow existingBorrow = borrowDAO.getBorrowById(book.getId());
        assertNull(existingBorrow, "L'emprunt n'a pas été supprimé après le retour");  // L'emprunt devrait être supprimé
    }

    @Test
    void testBorrowBookNotAvailable() {
        // Emprunter un livre qui est déjà emprunté
        Student student1 = studentDAO.getStudentById(1);
        Student student2 = studentDAO.getStudentById(2);
        Book book = bookDAO.getBookById(1);

        assertNotNull(student1, "Étudiant 1 non trouvé");
        assertNotNull(student2, "Étudiant 2 non trouvé");
        assertNotNull(book, "Livre non trouvé");

        // Premier emprunt
        borrowService.borrowBook(new Borrow(student1, book, new Date()));

        // Tentative de deuxième emprunt pour le même livre
        Borrow existingBorrow = borrowDAO.getBorrowById(book.getId());
        assertNotNull(existingBorrow, "Le livre est déjà emprunté"); // Le livre est toujours emprunté
    }

    @Test
    void testBorrowBookStudentNotFound() {
        // Tenter de créer un emprunt avec un étudiant inexistant
        Student student = studentDAO.getStudentById(3);  // ID inexistant
        Book book = bookDAO.getBookById(1);

        // Assert that student is null
        assertNull(student, "L'étudiant avec ID 3 ne devrait pas exister");

        // Ensure the book is found
        assertNotNull(book, "Livre non trouvé");

        if (student != null && book != null) {
            Borrow borrow = new Borrow(student, book, new Date());
            borrowService.borrowBook(borrow);
        } else {
            System.out.println("Étudiant ou livre non trouvé.");
        }
    }


    @Test
    void testBorrowWithBorrowObject() {
        // Créer un emprunt avec un objet Borrow
        Date borrowDate = new Date();
        Borrow borrow = new Borrow(new Student("Alice"), new Book("Java Programming", "John Doe"), borrowDate);
        assertNotNull(borrow);
        assertEquals("Alice", borrow.getStudent().getName());
        assertEquals("Java Programming", borrow.getBook().getTitle());
        assertEquals(borrowDate, borrow.getBorrowDate());
    }*/
}
