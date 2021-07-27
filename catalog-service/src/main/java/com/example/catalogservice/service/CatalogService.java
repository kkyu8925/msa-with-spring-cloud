package com.example.catalogservice.service;

import com.example.catalogservice.jpa.CatalogEntity;

public interface CatalogService {
    // 모든 상품 조회
    Iterable<CatalogEntity> getAllCatalogs();
}
