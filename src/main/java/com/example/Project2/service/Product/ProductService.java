package com.example.Project2.service.Product;


import com.example.Project2.domain.Cart;
import com.example.Project2.domain.CartDetail;
import com.example.Project2.domain.Product;
import com.example.Project2.domain.User;
import com.example.Project2.repository.CartDetailRepository;
import com.example.Project2.repository.CartRepository;
import com.example.Project2.repository.ProductRepository;
import com.example.Project2.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private UserService userService;
    private CartRepository cartRepository;
    private CartDetailRepository cartDetailRepository;

    public ProductService(ProductRepository productRepository, UserService userService, CartRepository cartRepository, CartDetailRepository cartDetailRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
    }

    public void hanldeSaveProduct(Product product) {
        this.productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void DeleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(long id, String email, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.getCartByUser(user);

            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);

                cart = this.cartRepository.save(newCart);
            }

            Product currentProduct = this.productRepository.findById(id);
            if (currentProduct != null) {
                CartDetail currentCartDetail = this.cartDetailRepository.findCartDetailByCartAndProduct(cart, currentProduct);
                if (currentCartDetail == null) {
                    currentCartDetail = new CartDetail();
                    currentCartDetail.setCart(cart);
                    currentCartDetail.setProduct(currentProduct);
                    currentCartDetail.setPrice(currentProduct.getPrice());
                    currentCartDetail.setQuanlity(1);
                    session.setAttribute("sum", cart.getSum() + 1);
                    cart.setSum(cart.getSum() + 1);
                } else {
                    currentCartDetail.setQuanlity(currentCartDetail.getQuanlity() + 1);
                }
                this.cartDetailRepository.save(currentCartDetail);
            }
        }

    }
}
