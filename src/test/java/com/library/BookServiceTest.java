package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService bookService;
    private BookDAO bookDAO;

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize the BookDAO and BookService
        bookDAO = new BookDAO();
        bookService = new BookService(bookDAO);

        // Ensure that the book collection is empty before each test
        bookDAO.deleteAllBooks();


    }


    @Test
    void testAddBook() {
        // Arrange: Create a new book and add it
        Book book = new Book("Java Programming", "John Doe", "Publisher", 2020, "123456789", 2020);
        bookService.addBook(book);

        // Act: Check the number of books after addition
        assertEquals(1, bookDAO.getAllBooks().size(), "There should be one book in the collection after adding a book.");

        // Assert: Fetch the first book and verify it was added
        Book addedBook = bookDAO.getAllBooks().get(0); // Get the first book added
        assertNotNull(addedBook);
        assertEquals("Java Programming", addedBook.getTitle());
    }


    /*@Test
    void testUpdateBook() {
        // Arrange: Create a new book and add it
        Book book = new Book("Java Programming", "John Doe", "Publisher", 2020, "123456789", 2020);
        bookService.addBook(book);

        // Act: Update the book's title and author
        bookService.updateBook(book.getId(), "Advanced Java", "Jane Doe", false);

        // Assert: Fetch the updated book and verify its details
        Book updatedBook = bookDAO.getBookById(book.getId());  // Retrieve the updated book by its ID
        assertNotNull(updatedBook, "The updated book should not be null.");
        assertEquals("Advanced Java", updatedBook.getTitle(), "The title of the book should be updated.");
        assertEquals("Jane Doe", updatedBook.getAuthor(), "The author of the book should be updated.");
    }*/



    @Test
    void testDeleteBook() {
        Book book = new Book("Java Programming", "John Doe", "Publisher", 2020, "123456789", 2020);
        bookService.addBook(book);

        // Suppression du livre
        bookService.deleteBook(1);
        
        // Vérification que le livre a bien été supprimé
        assertNull(bookDAO.getBookById(1));
    }
}
