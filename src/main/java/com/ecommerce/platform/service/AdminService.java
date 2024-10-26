package com.ecommerce.platform.service;

import com.ecommerce.platform.model.Order;
import com.ecommerce.platform.model.Product;
import com.ecommerce.platform.model.User;
import com.ecommerce.platform.repository.AdminRepository;
import com.ecommerce.platform.repository.OrderRepository;
import com.ecommerce.platform.repository.ProductRepository;
import com.ecommerce.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}