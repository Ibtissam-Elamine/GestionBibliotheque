package com.library.model;

import java.util.Date;

public class Borrow {
    private int id;
    private Student student;
    private Book book;
    private Date borrowDate;
    private Date returnDate;

    // Constructeur complet
    public Borrow(int id, Student student, Book book, Date borrowDate, Date returnDate) {
        this.id = id;
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    // Simplified constructor
    public Borrow(String studentName, String bookTitle, Date borrowDate) {
        // Create the student object using the studentName string
        this.student = new Student(studentName);
        // Create the book object using the bookTitle string
        this.book = new Book(bookTitle, null, null, 0);  // Default values for book fields
        this.borrowDate = borrowDate;
        this.returnDate = null;  // Default value for returnDate
    }

    // Constructor with Student and Book objects
    public Borrow(Student student, Book book, Date borrowDate) {
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = null;  // Default returnDate
    }
    public Borrow(Student student, Book book, Date borrowDate, Date returnDate) {
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
