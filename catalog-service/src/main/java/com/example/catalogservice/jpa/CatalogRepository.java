package com.example.catalogservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<CatalogEntity, Long> {
    // 상품 조회 - 상품 id
    CatalogEntity findByProductId(String productId);
}
