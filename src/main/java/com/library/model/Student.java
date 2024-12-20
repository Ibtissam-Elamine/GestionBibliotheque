package com.library.model;

public class Student {
    private int id;
    private String name;

    // Constructeur par défaut
    public Student() {
    }

    // Constructeur avec un seul paramètre (correction pour Main.java)
    public Student(String name) {
        this.name = name;
    }

    // Constructeur complet
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Méthode pour récupérer l'objet actuel
    public Student get() {
        return this;
    }

    // Méthode pour vérifier si l'objet est "vide"
    public boolean isEmpty() {
        return (this.name == null || this.name.isEmpty()) && this.id == 0;
    }
}
