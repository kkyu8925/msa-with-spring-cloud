package com.example.orderservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    // 주문 조회 - 주문Id
    OrderEntity findByOrderId(String orderId);

    // 주문 조회 - 유저Id
    Iterable<OrderEntity> findByUserId(String userId);
}
