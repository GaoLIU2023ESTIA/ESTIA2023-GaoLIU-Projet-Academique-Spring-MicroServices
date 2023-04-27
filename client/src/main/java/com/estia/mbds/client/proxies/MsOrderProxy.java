package com.estia.mbds.client.proxies;

import com.estia.mbds.client.beans.OrderBean;
import com.estia.mbds.client.beans.OrderItemBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-order", url = "localhost:8093")
public interface MsOrderProxy {

    @PostMapping(value = "/order")
    public ResponseEntity<OrderBean> createNewOrder(@RequestBody OrderBean orderData);

    @GetMapping(value = "/order/{id}")
    public ResponseEntity<OrderBean> getOrder(@PathVariable Long id);

    @PostMapping(value = "/order/{id}/addProduct")
    public ResponseEntity<OrderBean> addProductToOrder(@PathVariable Long id, @RequestBody OrderItemBean orderItem);

}
