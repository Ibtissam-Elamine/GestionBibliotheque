package com.library.dao;

import com.library.model.Book;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private Connection connection;

    // Constructor to initialize the connection
    public BookDAO() {
        // Utilisation de la classe DbConnection pour obtenir la connexion
        try {
            this.connection = DbConnection.getConnection(); // Appel à la méthode getConnection
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    // Méthode pour ajouter un livre
    public void add(Book book) {
        String query = "INSERT INTO books (title, author, publisher, year, isbn, publishedYear) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYear());
            statement.setString(5, book.getIsbn());
            statement.setInt(6, book.getPublishedYear());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
    }

    // Méthode pour récupérer un livre par ID
    public Book getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Book(resultSet.getString("title"), resultSet.getString("author"),
                            resultSet.getString("publisher"), resultSet.getInt("year"),
                            resultSet.getString("isbn"), resultSet.getInt("publishedYear"));
                } else {
                    System.out.println("Livre non trouvé avec l'ID : " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du livre : " + e.getMessage());
        }
        return null;
    }

    // Méthode pour récupérer tous les livres
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                books.add(new Book(resultSet.getString("title"), resultSet.getString("author"),
                        resultSet.getString("publisher"), resultSet.getInt("year"),
                        resultSet.getString("isbn"), resultSet.getInt("publishedYear")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des livres : " + e.getMessage());
        }
        return books;
    }

    // Méthode pour mettre à jour un livre
    public void update(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, publisher = ?, year = ?, isbn = ?, publishedYear = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYear());
            statement.setString(5, book.getIsbn());
            statement.setInt(6, book.getPublishedYear());
            statement.setInt(7, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du livre : " + e.getMessage());
        }
    }

    // Méthode pour supprimer un livre
    public void delete(int id) {
        String query = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du livre : " + e.getMessage());
        }
    }
    // Method to delete all books from the database
    public void deleteAllBooks() throws SQLException {
        String sql = "DELETE FROM books"; // Assuming your table is called "books"
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate(); // Executes the delete query
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting all books", e);
        }
    }
}
