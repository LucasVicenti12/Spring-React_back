package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ModelProduct;

@Repository
public interface RepositoryProduct extends CrudRepository<ModelProduct, Long> {
    
}
