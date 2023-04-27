package com.estia.mbds.msorder.controllers;

import com.estia.mbds.msorder.model.Order;
import com.estia.mbds.msorder.model.OrderItem;
import com.estia.mbds.msorder.repositories.OrderItemRepository;
import com.estia.mbds.msorder.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @PostMapping(value = "/order")
    public ResponseEntity<Order> createNewOrder()
    {
        Order order;
        try
        {
            order = orderRepository.save(new Order());
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new order");
        }
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping(value = "/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent())
            return new ResponseEntity<Order>(orderOptional.get(), HttpStatus.CREATED);

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified order doesn't exist");
    }

    @PostMapping(value = "/order/{id}/addProduct")
    public ResponseEntity<Order> addProductToOrder(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (!orderOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified order doesn't exist");

        Order order = orderOptional.get();
        order.addOrderItems(orderItem);

        try {
            orderRepository.save(order);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new order");
        }

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }
}
