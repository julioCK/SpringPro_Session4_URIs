package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dtos.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT p.name "
            + "FROM products p "
            + "INNER JOIN providers s ON p.id_providers = s.id "
            + "WHERE p.amount BETWEEN 10 AND 20 "
            + "AND UPPER(s.name) LIKE UPPER(CONCAT(:providerIn, '%'))")
    List<ProductMinProjection> search1(String providerIn);


            // SELECT new com.devsuperior.uri2621.dtos.ProductMinDTO(obj.name)  ->   aciona o construtor do DTO passando o Product.name como paramentro.
    @Query("SELECT new com.devsuperior.uri2621.dtos.ProductMinDTO(obj.name) "
            + "FROM Product obj "
            + "WHERE obj.amount BETWEEN :min AND :max "
            + "AND UPPER(obj.provider.name) LIKE UPPER(CONCAT(:providerIn, '%'))")
    List<ProductMinDTO> search2(Integer min, Integer max, String providerIn);
}
