package com.estia.mbds.mscart.repositories;

import com.estia.mbds.mscart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>
{
}
