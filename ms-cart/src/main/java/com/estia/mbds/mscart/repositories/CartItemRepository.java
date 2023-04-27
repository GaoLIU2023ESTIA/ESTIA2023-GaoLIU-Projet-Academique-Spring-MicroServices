package com.estia.mbds.mscart.repositories;

import com.estia.mbds.mscart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>
{
}
