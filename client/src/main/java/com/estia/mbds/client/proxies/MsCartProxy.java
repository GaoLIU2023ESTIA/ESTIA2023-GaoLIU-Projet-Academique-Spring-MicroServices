package com.estia.mbds.client.proxies;

import com.estia.mbds.client.beans.CartBean;
import com.estia.mbds.client.beans.CartItemBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "ms-cart", url = "localhost:8092")
public interface MsCartProxy {
    @PostMapping(value = "/cart")
    public ResponseEntity<CartBean> createNewCart(@RequestBody CartBean cartData);

    @GetMapping(value = "/cart/{id}")
    public ResponseEntity<CartBean> getCart(@PathVariable Long id);

    @PostMapping(value = "/cart/{id}/addProduct")
    public ResponseEntity<CartBean> addProductToCart(@PathVariable Long id, @RequestBody CartItemBean cartItem);

    @DeleteMapping(value = "/cart/{id}/dropAll")
    public ResponseEntity<CartBean> deleteAll(@PathVariable Long id);
}
