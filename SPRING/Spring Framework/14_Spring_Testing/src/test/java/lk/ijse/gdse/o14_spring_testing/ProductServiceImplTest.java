package lk.ijse.gdse.o14_spring_testing;

import lk.ijse.gdse.o14_spring_testing.entity.Product;
import lk.ijse.gdse.o14_spring_testing.repository.ProductRepository;
import lk.ijse.gdse.o14_spring_testing.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;

    /*fake data will get from this annotation*/
    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        // user builder pattern to create a products....
        // this will be used in all test cases to avoid code duplication
        product = Product.builder()
                .id(1L)
                .name("TEST Product")
                .price(100.00)
                .quantity(1000)
                .build();
    }
/*
    @AfterEach --- after the test case execution this method will be executed
*/

    @Test
    void shouldSaveProduct(){
        // arrange data --- data preparation
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // action --- write test logic
        Product savedProduct = productService.saveProduct(product);

        // assert --- verify the result
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals(product, savedProduct);
        Assertions.assertEquals(product.getId(), savedProduct.getId());
        verify(productRepository,times(1)).save(any(Product.class));
    }

    @Test
    void shouldUpdateProduct(){
        when(productRepository.getProductById(1L)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        product.setName("Updated Product");
        Product updatedProduct = productService.updateProduct(product);

        Assertions.assertNotNull(updatedProduct);
        Assertions.assertEquals("Updated Product", updatedProduct.getName());
        verify(productRepository, times(1)).getProductById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldGetAll(){
        when(productRepository.findAll()).thenReturn(java.util.Arrays.asList(product));

        List products = productService.getAllProducts();
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(product, products.get(0));

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void shouldDeleteProduct(){
        when(productRepository.getProductById(1L)).thenReturn(product);
        doNothing().when(productRepository).delete(any(Product.class));

        productService.deleteProduct(1L);

        verify(productRepository, times(
                1))
                .getProductById(1L);
        verify(productRepository, times(
                1))
                .delete(any(Product.class));
    }

    @Test
    void shouldGetProductById() {
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));
        Product foundProduct = productService.getProduct(1L);

        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals(product, foundProduct);
        Assertions.assertEquals(product.getId(), foundProduct.getId());

        verify(productRepository, times(1)).findById(1L);
    }
}
