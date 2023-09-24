package com.bitcoin.autotrading.order.domain.service;

import com.bitcoin.autotrading.order.domain.Order;
import com.bitcoin.autotrading.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class OrderService{

    private final OrderRepository repository;

    public Order findById(String id) {
        return repository.findById(id).orElse(new Order());
    }


    public Order save(Order tradeOrder) {
        return repository.save(tradeOrder);
    }


    public List<Order> saveAll(Iterable<Order> iterable) {
        return repository.saveAll(iterable);
    }


    public void delete(Order entity) {
        repository.delete(entity);
    }


    public void deleteAll(Iterable<Order> iterable) {
        repository.deleteAll(iterable);
    }

}

