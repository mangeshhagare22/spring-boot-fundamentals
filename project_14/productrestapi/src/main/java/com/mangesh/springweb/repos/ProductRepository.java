package com.mangesh.springweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mangesh.springweb.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
