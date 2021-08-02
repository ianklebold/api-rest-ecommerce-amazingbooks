package com.amazing.books.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.amazing.books.utils.StateCart;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cart{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="createdDate", nullable=false, updatable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    
    @Column(name="total", nullable=true, updatable = true)
    private Double total;

    @Column(name = "state", nullable = false, updatable = true)
    @Enumerated(value = EnumType.STRING)
    private StateCart state;

    @OneToOne(fetch = FetchType.LAZY,cascade = {})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {})
    private List<Book> listBooks;


    Cart(){
        
    }

    




    public Cart(Long id, Date createdDate, Double total, StateCart state, User user, List<Book> listBooks) {
        this.id = id;
        this.createdDate = createdDate;
        this.total = total;
        this.state = state;
        this.user = user;
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
     * @return Double return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return StateCart return the state
     */
    public StateCart getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(StateCart state) {
        this.state = state;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return List<Book> return the listBooks
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
