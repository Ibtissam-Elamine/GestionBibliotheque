
package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.StudentDAO;
import com.library.dao.BorrowDAO;
import com.library.model.Borrow;

public class BorrowService {

    private BorrowDAO borrowDAO;
        private BookDAO bookDAO;
                private StudentDAO studentDAO;
            
                // Constructeur avec BorrowDAO
                public BorrowService(BorrowDAO borrowDAO) {
                    this.borrowDAO = borrowDAO;
                }
            
                    // Constructeur avec BookDAO et StudentDAO
                    public BorrowService(BookDAO bookDAO, StudentDAO studentDAO) {
                        this.bookDAO = bookDAO;
                    this.studentDAO = studentDAO;
            this.borrowDAO = new BorrowDAO(); // Crée une instance de BorrowDAO (si nécessaire)
        }

    // Méthode pour emprunter un livre
    public void borrowBook(Borrow borrow) {
        // Sauvegarde de l'emprunt dans la base de données
        borrowDAO.save(borrow);
    }

    // Afficher les emprunts (méthode fictive, à adapter)
    public void displayBorrows() {
        System.out.println("Liste des emprunts...");
        // Afficher les emprunts enregistrés (adapté selon votre DAO)
    }

     // Méthode pour retourner un livre
    public String returnBook(int studentId, int bookId) {
        // Récupérer tous les emprunts
        for (Borrow borrow : borrowDAO.getAllBorrows()) {
            // Vérifier si l'emprunt correspond à l'étudiant et au livre
            if (borrow.getStudent().getId() == studentId && borrow.getBook().getId() == bookId) {
                // Si un emprunt est trouvé, le supprimer
                borrowDAO.deleteBorrow(borrow.getId());
                return "Livre retourné avec succès!";
            }
        }
        // Si aucun emprunt correspondant n'est trouvé
        return "Aucun emprunt trouvé pour cet étudiant et ce livre.";
    }
}
