package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.jpa.OrderEntity;

public interface OrderService {

    // 주문 생성
    OrderDTO createOrder(OrderDTO orderDetails);

    // 주문 조회 - 주문Id
    OrderDTO getOrderByOrderId(String orderId);

    // 주문 조회 - 유저Id
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
