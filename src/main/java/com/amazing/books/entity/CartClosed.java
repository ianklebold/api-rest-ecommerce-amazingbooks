package com.amazing.books.entity;

import java.util.List;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class CartClosed extends Cart{

    public CartClosed(Long id, Date createdDate, List<Book> listBooks){
        super(id, createdDate, listBooks);
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {})
    private User user;

    

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

}
