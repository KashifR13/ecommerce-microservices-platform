package com.ecommerce.platform.service;

import com.ecommerce.platform.model.Product;
import com.ecommerce.platform.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ProductServiceBase {

    public ProductService(ProductRepository productRepository) {
        super(productRepository);
    }

    public String addProduct(Product product) {
        if (productRepository.findById(product.getProductId()).isPresent()) {
            return "Product already exists";
        }
        productRepository.save(product);
        return "Product added to the cart successfully";
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }

    public String modifyProductDetails(Product product) {
        Product existingProduct = productRepository.findByName(product.getName());
        String validationMessage = getValidationMessage(product, existingProduct);
        if (validationMessage != null) return validationMessage;
        existingProduct.setCategory(product.getCategory());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());
        productRepository.save(existingProduct);
        return "Product details modified successfully";
    }

}