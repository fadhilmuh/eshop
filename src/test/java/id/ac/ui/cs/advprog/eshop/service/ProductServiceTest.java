package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Product Testing");
        product.setProductQuantity(100);
        
        Product res = productService.create(product);
        assertEquals(product.getProductId(), res.getProductId());
        assertEquals(product.getProductName(), res.getProductName());
        assertEquals(product.getProductQuantity(), res.getProductQuantity());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Product Testing");
        product.setProductQuantity(100);
        productService.create(product);
        
        Product editedProduct = new Product();
        editedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedProduct.setProductName("Product Testing 2");
        editedProduct.setProductQuantity(200);
        
        Product res = productService.edit(editedProduct);
        assertEquals(editedProduct.getProductId(), res.getProductId());
        assertEquals(editedProduct.getProductName(), res.getProductName());
        assertEquals(editedProduct.getProductQuantity(), res.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Product Testing");
        product.setProductQuantity(100);
        productService.create(product);
        
        productService.delete(product.getProductId());
        assertNull(productService.findById(product.getProductId()));
    }

    @Test
    void testFindByIdIfNotFound() {
        Product res = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(res);
    }

    @Test
    void testFindAll() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        when(productRepository.findAll()).thenReturn(List.of(product).iterator());
        Iterator<Product> foundProducts = productService.findAll().iterator();

        assertTrue(foundProducts.hasNext());
        Product savedProduct = foundProducts.next();

        assertEquals(product, savedProduct);
        verify(productRepository, times(1)).findAll();
    }
}
