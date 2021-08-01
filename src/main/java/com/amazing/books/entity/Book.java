package com.amazing.books.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.amazing.books.utils.NameCategory;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, updatable = true)
    private String name;

    @Column(name="description", nullable=true, updatable = true)
    private String description;

    @Column(name="authors", nullable=false, updatable = true)
    private String authors;

    @Column(name="gender", nullable=false, updatable = true)
    private String gender;

    @Column(name="edition", nullable=false, updatable = true)
    private String edition;

    @Column(name="price", nullable=false, updatable = false)
    private Double price;

    @Column(name = "category", nullable = false, updatable = true)
    @Enumerated(value = EnumType.STRING)
    private NameCategory category;


    @Column(name = "inventorycode", nullable = false, updatable = false, unique = true)
    private String inventoryCode;

    public Book(){}

    public Book(Long id, String name, String description, String authors, String gender, String edition, Double price,
            NameCategory category, String inventoryCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authors = authors;
        this.gender = gender;
        this.edition = edition;
        this.price = price;
        this.category = category;
        this.inventoryCode = inventoryCode;
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the authors
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * @return String return the edition
     */
    public String getEdition() {
        return edition;
    }

    /**
     * @param edition the edition to set
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }


    /**
     * @return NameCategory return the category
     */
    public NameCategory getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(NameCategory category) {
        this.category = category;
    }


    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return Double return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return String return the inventoryCode
     */
    public String getInventoryCode() {
        return inventoryCode;
    }

    /**
     * @param inventoryCode the inventoryCode to set
     */
    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

}
