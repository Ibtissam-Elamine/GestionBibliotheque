package com.library.dao;

import com.library.model.Borrow;
import com.library.model.Student;
import com.library.model.Book;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {

    // Récupérer tous les emprunts
    public List<Borrow> getAllBorrows() {
        List<Borrow> borrows = new ArrayList<>();
        String query = "SELECT * FROM borrows";
        try (Connection connection = DbConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Créer un objet Student à partir du champ "member"
                Student student = new Student();
                student.setName(rs.getString("member"));

                // Créer un objet Book à partir du champ "book"
                Book book = new Book();
                book.setTitle(rs.getString("book"));

                // Créer un objet Borrow
                Borrow borrow = new Borrow(
                        rs.getInt("id"),
                        student,
                        book,
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                );
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    // Ajouter un nouvel emprunt
    public void addBorrow(Borrow borrow) {
        String query = "INSERT INTO borrows (member, book, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Insérer le nom de l'étudiant dans le champ "member"
            stmt.setString(1, borrow.getStudent().getName());

            // Insérer le titre du livre dans le champ "book"
            stmt.setString(2, borrow.getBook().getTitle());

            // Insérer les dates
            stmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(4, new java.sql.Date(borrow.getReturnDate().getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour sauvegarder un emprunt
    public void save(Borrow borrow) {
        // Exemple : Implémentation personnalisée si nécessaire
        System.out.println("Méthode 'save' à implémenter.");
    }
    // Mettre à jour un emprunt existant
    public void updateBorrow(Borrow borrow) {
        String query = "UPDATE borrows SET member = ?, book = ?, borrow_date = ?, return_date = ? WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Mettre à jour le nom de l'étudiant
            stmt.setString(1, borrow.getStudent().getName());

            // Mettre à jour le titre du livre
            stmt.setString(2, borrow.getBook().getTitle());

            // Mettre à jour les dates
            stmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(4, new java.sql.Date(borrow.getReturnDate().getTime()));

            // Spécifier l'ID de l'emprunt à mettre à jour
            stmt.setInt(5, borrow.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un emprunt par ID
    public void deleteBorrow(int borrowId) {
        String query = "DELETE FROM borrows WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Spécifier l'ID de l'emprunt à supprimer
            stmt.setInt(1, borrowId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Trouver un emprunt par ID
    public Borrow getBorrowById(int borrowId) {
        Borrow borrow = null;
        String query = "SELECT * FROM borrows WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Spécifier l'ID de l'emprunt à rechercher
            stmt.setInt(1, borrowId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Créer un objet Student à partir du champ "member"
                    Student student = new Student();
                    student.setName(rs.getString("member"));

                    // Créer un objet Book à partir du champ "book"
                    Book book = new Book();
                    book.setTitle(rs.getString("book"));

                    // Créer un objet Borrow
                    borrow = new Borrow(
                            rs.getInt("id"),
                            student,
                            book,
                            rs.getDate("borrow_date"),
                            rs.getDate("return_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrow;
    }

}
