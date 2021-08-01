package com.amazing.books.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cart{
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long id;

    @Column(name="createdDate", nullable=false, updatable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    
    @ManyToMany(cascade = {})
    private List<Book> listBooks;



    public Cart(Long id, Date createdDate, List<Book> listBooks) {
        this.id = id;
        this.createdDate = createdDate;
        this.listBooks = listBooks;
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
     * @return Date return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }



    /**
     * @return ArrayList<Book> return the listBooks
     */
    public List<Book> getListBooks() {
        return listBooks;
    }

    /**
     * @param listBooks the listBooks to set
     */
    public void setListBooks(List<Book> listBooks) {
        this.listBooks = listBooks;
    }

}
