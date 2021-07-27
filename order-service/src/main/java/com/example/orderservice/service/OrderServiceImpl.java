package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.jpa.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        orderDTO.setOrderId(UUID.randomUUID().toString());
        orderDTO.setTotalPrice(orderDTO.getQty() * orderDTO.getUnitPrice());

        // mapper 객체로 OrderDTO -> OrderEntity 변환
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderEntity orderEntity = mapper.map(orderDTO, OrderEntity.class);

        orderRepository.save(orderEntity);

        // mapper 객체로 OrderEntity -> OrderDTO 변환
        return mapper.map(orderEntity, OrderDTO.class);
    }

    @Override
    public OrderDTO getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);

        // mapper 객체로 OrderEntity -> OrderDTO 변환
        return new ModelMapper().map(orderEntity, OrderDTO.class);
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
