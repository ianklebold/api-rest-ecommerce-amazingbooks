package com.amazing.books.repository;

import com.amazing.books.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
}
