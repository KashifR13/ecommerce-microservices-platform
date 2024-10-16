package com.ecommerce.platform.controller;

import com.ecommerce.platform.model.Product;
import com.ecommerce.platform.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public String updateProduct(@RequestBody Product product, @PathVariable Long productId) {
        return productService.modifyProductDetails(product, productId);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

}