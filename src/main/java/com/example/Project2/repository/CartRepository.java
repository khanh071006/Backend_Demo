package com.example.Project2.repository;

import com.example.Project2.domain.Cart;
import com.example.Project2.domain.CartDetail;
import com.example.Project2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    Cart getCartByUser(User user);

    Cart deleteCartById(long id);

    Cart findCartById(long id);
}
