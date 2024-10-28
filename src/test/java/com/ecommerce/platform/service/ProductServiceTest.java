package com.ecommerce.platform.service;

import com.ecommerce.platform.model.Product;
import com.ecommerce.platform.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void initializeProduct() {
        product = new Product();
        product.setProductId(1L);
        product.setCategory("Electronics");
        product.setDescription("Smartphone");
        product.setPrice(699.99);
        product.setStockQuantity(50);
    }

    @Test
    public void shouldAddProductSuccessfully() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());
        String result = productService.addProduct(product);
        assertEquals("Product added to the cart successfully", result, "Product should be added successfully");
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void shouldNotAddProductIfAlreadyExists() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
        String result = productService.addProduct(product);
        assertEquals("Product already exists", result, "Product should not be added if it already exists");
    }

    @Test
    public void shouldGetProductByIdSuccessfully() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
        Product result = productService.getProductById(product.getProductId());
        assertEquals(product, result, "Product should be retrieved successfully");
    }

    @Test
    public void shouldGetAllProductsSuccessfully() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<Product> result = productService.getAllProducts();
        assertEquals(List.of(product), result, "All products should be retrieved successfully");
    }

    @Test
    public void shouldReturnNullIfProductNotFound() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());
        Product result = productService.getProductById(product.getProductId());
        assertNull(result, "Should return null if product is not found");
    }

    @Test
    public void shouldDeleteProductSuccessfully() {
        doNothing().when(productRepository).deleteById(product.getProductId());
        String result = productService.deleteProduct(product.getProductId());
        assertEquals("Product deleted successfully", result, "Product should be deleted successfully");
        verify(productRepository, times(1)).deleteById(product.getProductId());
    }

    @Test
    public void shouldModifyProductDetailsSuccessfully() {
        Product existingProduct = new Product();
        existingProduct.setProductId(1L);
        existingProduct.setCategory("Electronics");
        existingProduct.setDescription("Old Smartphone");
        existingProduct.setPrice(599.99);
        existingProduct.setStockQuantity(30);

        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(existingProduct));
        String result = productService.modifyProductDetails(product, product.getProductId());
        assertEquals("Product details modified successfully", result, "Product details should be modified successfully");
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void shouldNotModifyProductDetailsIfProductNotFound() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());
        String result = productService.modifyProductDetails(product, product.getProductId());
        assertEquals("Product not found", result, "Should not modify product details if product is not found");
    }
}