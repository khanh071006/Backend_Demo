package com.example.Project2.repository;

import com.example.Project2.domain.Cart;
import com.example.Project2.domain.CartDetail;
import com.example.Project2.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, String> {

    CartDetail findCartDetailByCartAndProduct(Cart cart, Product product);

    List<CartDetail> findCartDetailByCart(Cart cart);

    void deleteById(long id);

    CartDetail findCartDetailById(long id);
}
