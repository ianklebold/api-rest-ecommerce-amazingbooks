package com.amazing.books.repository;

import com.amazing.books.entity.Book;
import com.amazing.books.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{}
