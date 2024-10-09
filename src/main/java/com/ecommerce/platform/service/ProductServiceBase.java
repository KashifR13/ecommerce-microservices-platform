package com.ecommerce.platform.service;

import com.ecommerce.platform.model.Product;
import com.ecommerce.platform.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ProductServiceBase {

    protected final ProductRepository productRepository;

    @Autowired
    public ProductServiceBase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    protected String getValidationMessage(Product existingProduct) {
        if (isProductNotFound(existingProduct)) {
            return "Product not found";
        }
        return null;
    }

    protected boolean isProductNotFound(Product product) {
        return product == null;
    }

}