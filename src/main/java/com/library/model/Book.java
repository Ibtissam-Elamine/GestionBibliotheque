package com.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String isbn; // Nouveau champ
    private int publishedYear; // Nouveau champ

    // Constructeur par défaut
    public Book() {
    }

    // Constructeur simplifié
    public Book(String title, String author, String publisher, int year) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    // Constructeur complet
    public Book(String title, String author, String publisher, int year, String isbn, int publishedYear) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
    }
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.publisher = "Unknown";  // Default value
        this.year = 0;  // Default value
    }


    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn; // Getter pour isbn
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn; // Setter pour isbn
    }

    public int getPublishedYear() {
        return publishedYear; // Getter pour publishedYear
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear; // Setter pour publishedYear
    }
}
