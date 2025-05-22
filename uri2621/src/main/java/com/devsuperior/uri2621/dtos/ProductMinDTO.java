package com.devsuperior.uri2621.dtos;

import com.devsuperior.uri2621.projections.ProductMinProjection;

public class ProductMinDTO {

    private String name;

    public ProductMinDTO() {
    }

    public ProductMinDTO(ProductMinProjection projection) {
        name = projection.getName();
    }

    public ProductMinDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProductMinDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
