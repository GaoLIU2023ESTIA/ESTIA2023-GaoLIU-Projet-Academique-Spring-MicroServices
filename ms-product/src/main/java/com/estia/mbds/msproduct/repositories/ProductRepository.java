package com.estia.mbds.msproduct.repositories;

import com.estia.mbds.msproduct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
