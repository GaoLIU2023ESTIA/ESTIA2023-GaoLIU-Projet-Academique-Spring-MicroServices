package com.estia.mbds.mscart.controllers;

import com.estia.mbds.mscart.model.Cart;
import com.estia.mbds.mscart.model.CartItem;
import com.estia.mbds.mscart.repositories.CartItemRepository;
import com.estia.mbds.mscart.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @PostMapping(value = "/cart")
    public ResponseEntity<Cart> createNewCart() {
        Cart cart;
        try {
            cart = cartRepository.save(new Cart());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new cart");
        }

        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

    @GetMapping(value = "/cart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent())
            return new ResponseEntity<Cart>(cartOptional.get(), HttpStatus.CREATED);

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified cart doesn't exist");
    }

    @PostMapping(value = "/cart/{id}/addProduct")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long id, @RequestBody CartItem cartItem) {
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if (!cartOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified cart doesn't exist");

        Cart cart = cartOptional.get();
        cart.addItem(cartItem);

        try {
            cartRepository.save(cart);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new cart");
        }

        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/cart/{id}/dropAll")
    public ResponseEntity<Cart> deleteAll(@PathVariable Long id)
    {
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if (!cartOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified cart doesn't exist");

        Cart cart = cartOptional.get();
        cart.dropItems(id);

        try {
            cartRepository.save(cart);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new cart");
        }

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);

    }

}
