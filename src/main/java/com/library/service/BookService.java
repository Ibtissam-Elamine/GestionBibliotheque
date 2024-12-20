package com.library.service;

import com.library.dao.BookDAO;  // Importation de BookDAO
import com.library.model.Book;   // Importation de Book
import java.util.List;

public class BookService {
    private BookDAO bookDAO;  // Utilisation de DAO pour la gestion des livres

    // Constructeur qui initialise l'objet BookDAO
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    // Constructeur par défaut
    public BookService() {
        this.bookDAO = new BookDAO(); // Initialisation de BookDAO si aucun paramètre n'est fourni
    }

    // Ajouter un livre
    public void addBook(Book book) {
        bookDAO.add(book);  // Appel de la méthode add du DAO
    }

    // Afficher tous les livres
    public void displayBooks() {
        List<Book> books = bookDAO.getAllBooks();  // Récupération de la liste des livres
        for (Book book : books) {
            System.out.println(book);  // Affichage de chaque livre
        }
    }

    // Trouver un livre par ID
    public Book findBookById(int id) {
        return bookDAO.getBookById(id);  // Appel de la méthode getBookById du DAO
    }

    // Supprimer un livre par ID
    public void deleteBook(int id) {
        bookDAO.delete(id);  // Appel de la méthode delete du DAO
    }

    // Mise à jour des informations d'un livre
    public void updateBook(int id, String title, String author, boolean isAvailable) {
        Book book = bookDAO.getBookById(id);  // Récupération du livre par son ID
        if (book != null) {  // Vérification si le livre existe
            book.setTitle(title);  // Mise à jour du titre
            book.setAuthor(author);  // Mise à jour de l'auteur
            bookDAO.update(book);  // Appel de la méthode update du DAO pour enregistrer les changements
        } else {
            System.out.println("Livre non trouvé avec l'ID : " + id);  // Message si le livre n'existe pas
        }
    }
}
